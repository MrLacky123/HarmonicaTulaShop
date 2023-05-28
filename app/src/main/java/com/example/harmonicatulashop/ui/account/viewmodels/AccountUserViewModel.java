package com.example.harmonicatulashop.ui.account.viewmodels;

import static com.example.harmonicatulashop.MainActivity.GALLERY_REQUEST_FOR_USER;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.database.account.current.room.CurrentRepository;
import com.example.harmonicatulashop.ui.account.fragments.AccountUserFragment;
import com.example.harmonicatulashop.ui.account.fragments.OrderListFragment;
import com.example.harmonicatulashop.ui.account.fragments.UserSettingsFragment;

public class AccountUserViewModel extends ViewModel {

    public void loadAvatarImage() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        MainActivity.INSTANCE.startActivityForResult(photoPickerIntent, GALLERY_REQUEST_FOR_USER);

    }

    public void orders() {

        MainActivity.INSTANCE.setFragment(OrderListFragment.class,
                R.id.account_user_layout,null,
                MainActivity.INSTANCE.getResources().getString(R.string.my_orders),
                AccountUserFragment.class);

    }

    public void settings() {

        MainActivity.INSTANCE.setFragment(UserSettingsFragment.class,
                R.id.account_user_layout, null,
                MainActivity.INSTANCE.getResources().getString(R.string.settings),
                AccountUserFragment.class);

    }

    public void exit() {

        MainActivity.currentUser = null;
        new CurrentRepository(MainActivity.INSTANCE.getApplication()).deleteCurrentUser();
        MainActivity.INSTANCE.load();

    }
}