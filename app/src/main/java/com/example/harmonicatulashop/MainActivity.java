package com.example.harmonicatulashop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.harmonicatulashop.databinding.ActivityMainBinding;
import com.example.harmonicatulashop.models.account.Admin;
import com.example.harmonicatulashop.models.account.User;
import com.example.harmonicatulashop.ui.catalog.fragments.CatalogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.common.base.Optional;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static MainActivity INSTANCE;

    public static User currentUser;

    public static Admin currentAdmin;

    private ActivityMainBinding binding;

    private FragmentManager fragmentManager;

    private List<Fragment> fragments = new ArrayList<>();

    public static HashMap<Integer, Bitmap> harmonicaImages = new HashMap<>();

    public static HashMap<Integer, Bitmap> accordionImages = new HashMap<>();

    public static HashMap<Integer, Bitmap> bayanImages = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        INSTANCE = this;

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_catalog, R.id.navigation_favourite, R.id.navigation_cart, R.id.navigation_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public <T extends Fragment> void setFragment(Class<T> customClass, int id, @Nullable Bundle args, String title) {

        Optional<Fragment> fragment = Iterables.tryFind(fragments, frag -> frag.getClass().isInstance(customClass));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (!fragment.isPresent()){

            fragmentManager.beginTransaction()
                    .replace(id, customClass, args)
                    .addToBackStack("open fragment")
                    .commit();

            Fragment newFragment = fragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main);
            Log.i("INFO", "Created fragment: " + newFragment.getClass().getName());
            fragments.add(newFragment);

            getSupportActionBar().setTitle(title);

            return;

        }

        fragment.get().setArguments(args);

        fragmentManager.beginTransaction()
                .replace(id, fragment.get())
                .addToBackStack("open fragment")
                .commit();

        getSupportActionBar().setTitle(title);

    }

    @Override
    public boolean onSupportNavigateUp() {
        setFragment(CatalogFragment.class, R.id.catalog_layout, null, getResources().getString(R.string.title_catalog));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        return super.onSupportNavigateUp();
    }
}
