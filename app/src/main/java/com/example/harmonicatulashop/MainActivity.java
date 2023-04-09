package com.example.harmonicatulashop;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.harmonicatulashop.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.common.base.Optional;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static MainActivity Instance;
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private List<Fragment> fragments = new ArrayList<>();
    public static HashMap<Integer, Bitmap> images = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Instance = this;

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getBaseContext().deleteDatabase("harmonica_list_database");
        getBaseContext().deleteDatabase("bayan_list_database");

        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_catalog, R.id.navigation_favourite, R.id.navigation_cart)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        fragmentManager = getSupportFragmentManager();
    }

    public <T extends Fragment> void setFragment(Class<T> customClass, int id, @Nullable Bundle args) {

        Optional<Fragment> fragment = Iterables.tryFind(fragments, frag -> frag.getClass().isInstance(customClass));

        if (!fragment.isPresent()){

            fragmentManager.beginTransaction()
                    .replace(id, customClass, args)
                    .addToBackStack("open fragment")
                    .commit();

            Fragment newFragment = fragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main);
            Log.i("INFO", "Created fragment: " + newFragment.getClass().getName());
            fragments.add(newFragment);
            return;

        }

        fragment.get().setArguments(args);

        fragmentManager.beginTransaction()
                .replace(id, fragment.get())
                .addToBackStack("open fragment")
                .commit();

    }
}
