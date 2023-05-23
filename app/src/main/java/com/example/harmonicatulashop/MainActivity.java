package com.example.harmonicatulashop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.harmonicatulashop.database.account.current.room.AdminRepositoryC;
import com.example.harmonicatulashop.database.account.current.room.AdminRoomDatabaseC;
import com.example.harmonicatulashop.database.account.current.room.UserRepositoryC;
import com.example.harmonicatulashop.database.account.current.room.UserRoomDatabaseC;
import com.example.harmonicatulashop.database.account.room.AdminRepository;
import com.example.harmonicatulashop.database.account.room.UserRepository;
import com.example.harmonicatulashop.databinding.ActivityMainBinding;
import com.example.harmonicatulashop.models.account.Admin;
import com.example.harmonicatulashop.models.account.User;
import com.google.common.base.Optional;
import com.google.common.collect.Iterables;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Exchanger;

public class MainActivity extends AppCompatActivity {

    public static final int GALLERY_REQUEST_FOR_USER = 1;
    public static final int GALLERY_REQUEST_FOR_ADMIN = 2;

    public static MainActivity INSTANCE;

    public static User currentUser = null;
    public static Admin currentAdmin = null;

    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;

    private List<Fragment> fragments = new ArrayList<>();

    public static HashMap<String, Class<? extends Fragment>> previousFragmentMap = new HashMap<>();
    public static int currentLayout;
    public static List<String> previousTitles = new ArrayList<>();

    public static HashMap<Integer, Bitmap> harmonicaImages = new HashMap<>();
    public static HashMap<Integer, Bitmap> accordionImages = new HashMap<>();
    public static HashMap<Integer, Bitmap> bayanImages = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        INSTANCE = this;

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Exchanger<Admin> adminExchanger = new Exchanger<>();
        Exchanger<User> userExchanger = new Exchanger<>();

        new Thread(new GetAdmin(adminExchanger)).start();
        new Thread(new GetUser(userExchanger)).start();

        try {
            currentAdmin = adminExchanger.exchange(null);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            currentUser = userExchanger.exchange(null);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (currentAdmin != null) {
            loadForAdmin();
        } else if (currentUser != null) {
            loadForUser();
        } else {
            load();
        }
    }

    public <T extends Fragment> void setFragment(Class<T> customClass, int id, @Nullable Bundle args, String title, Class<? extends Fragment> instance) {

        Optional<Fragment> fragment = Iterables.tryFind(fragments, frag -> frag.getClass().isInstance(customClass));

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (instance == null) {

            assert actionBar.getTitle() != null;
            previousFragmentMap.remove(actionBar.getTitle().toString());

            previousTitles.remove(title);

        } else {

            assert actionBar.getTitle() != null;
            String previousTitle = actionBar.getTitle().toString();

            previousTitles.add(previousTitle);
            previousFragmentMap.put(title, instance);

        }

        if (!fragment.isPresent()) {

            fragmentManager.beginTransaction()
                    .replace(id, customClass, args)
                    .addToBackStack("open fragment")
                    .commit();

            Fragment newFragment = fragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main);
            assert newFragment != null;
            Log.i("INFO", "Created fragment: " + newFragment.getClass().getName());
            fragments.add(newFragment);

            getSupportActionBar().setTitle(title);

            return;

        }

        fragmentManager.beginTransaction()
                .replace(id, customClass, args)
                .addToBackStack("open fragment")
                .commit();

        getSupportActionBar().setTitle(title);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (currentAdmin != null) {
            loadForAdmin();
        } else if (currentUser != null) {
            loadForUser();
        } else {
            load();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap bitmap = null;
        ImageView imageView;

        if (imageReturnedIntent == null) {
            return;
        }

        Uri selectedImage = imageReturnedIntent.getData();

        switch(requestCode) {
            case GALLERY_REQUEST_FOR_USER:

                imageView = (ImageView) findViewById(R.id.profile_image_user);

                if (resultCode == RESULT_OK) {

                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    assert imageView != null;
                    imageView.setImageURI(selectedImage);

                    currentUser.setAvatarImage(bitmapToBlob(bitmap));

                    new UserRepositoryC(getApplication()).setCurrentUser(currentUser);
                    new UserRepository(getApplication()).replaceUser(currentUser.getLogin(), currentUser);

                }

                break;

            case GALLERY_REQUEST_FOR_ADMIN:

                imageView = (ImageView) findViewById(R.id.profile_image_admin);

                if (resultCode == RESULT_OK) {

                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    assert imageView != null;
                    imageView.setImageURI(selectedImage);

                    currentAdmin.setAvatarImage(bitmapToBlob(bitmap));

                    new AdminRepositoryC(getApplication()).setCurrentAdmin(currentAdmin);
                    new AdminRepository(getApplication()).replaceAdmin(currentAdmin.getLogin(), currentAdmin);

                }

                break;
        }
    }

    byte[] bitmapToBlob(Bitmap bitmap) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        bitmap = Bitmap.createScaledBitmap(bitmap, 400, 400, false);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] blob = bos.toByteArray();

        try {
            bos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return blob;
    }

    @Override
    public boolean onSupportNavigateUp() {

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        String title = (String) actionBar.getTitle();

        if (previousFragmentMap.get(title) == null) {
            Toast.makeText(getApplicationContext(), "Toast", Toast.LENGTH_SHORT).show();
        } else {
            setFragment(previousFragmentMap.get(title), currentLayout, null, Iterables.getLast(previousTitles), null);
        }

        if (previousTitles.isEmpty()) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        return super.onSupportNavigateUp();
    }

    private class GetAdmin implements Runnable {

        Exchanger<Admin> exchanger;
        Admin admin;

        public GetAdmin(Exchanger<Admin> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                admin = AdminRoomDatabaseC.getDatabase(getApplication()).adminDaoC().getAdmin();
                admin = exchanger.exchange(admin);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class GetUser implements Runnable {

        Exchanger<User> exchanger;
        User user;

        public GetUser(Exchanger<User> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                user = UserRoomDatabaseC.getDatabase(getApplication()).userDaoC().getUser();
                user = exchanger.exchange(user);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void loadForAdmin() {

        INSTANCE = this;

        setContentView(binding.getRoot());

        binding.containerUser.setVisibility(View.GONE);
        binding.container.setVisibility(View.GONE);
        binding.containerAdmin.setVisibility(View.VISIBLE);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_admin_account, R.id.navigation_admin_catalog)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main_admin);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navViewAdmin, navController);

        fragmentManager = getSupportFragmentManager();

    }

    public void loadForUser() {

        INSTANCE = this;

        binding.containerUser.setVisibility(View.VISIBLE);
        binding.container.setVisibility(View.GONE);
        binding.containerAdmin.setVisibility(View.GONE);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_user_account, R.id.navigation_user_catalog, R.id.navigation_user_favourite, R.id.navigation_user_cart)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main_user);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navViewUser, navController);

        fragmentManager = getSupportFragmentManager();

    }

    public void load() {

        binding.containerUser.setVisibility(View.GONE);
        binding.container.setVisibility(View.VISIBLE);
        binding.containerAdmin.setVisibility(View.GONE);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_catalog, R.id.navigation_cart, R.id.navigation_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        fragmentManager = getSupportFragmentManager();

    }
}
