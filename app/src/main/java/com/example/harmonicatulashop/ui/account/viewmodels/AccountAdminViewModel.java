package com.example.harmonicatulashop.ui.account.viewmodels;

import static com.example.harmonicatulashop.MainActivity.GALLERY_REQUEST_FOR_ADMIN;
import static com.example.harmonicatulashop.MainActivity.GALLERY_REQUEST_FOR_USER;

import android.content.Intent;
import android.widget.PopupMenu;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.database.account.current.room.AdminRepositoryC;
import com.example.harmonicatulashop.databinding.FragmentAccountAdminBinding;
import com.example.harmonicatulashop.ui.account.fragments.AccountAdminFragment;
import com.example.harmonicatulashop.ui.account.fragments.OrderListFragment;

public class AccountAdminViewModel extends ViewModel {

    private FragmentAccountAdminBinding binding;

    public void setBinding(FragmentAccountAdminBinding binding) {
        this.binding = binding;
    }

    public void loadAvatarImage() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        MainActivity.INSTANCE.startActivityForResult(photoPickerIntent, GALLERY_REQUEST_FOR_ADMIN);

    }

    public void orders() {

        MainActivity.INSTANCE.setFragment(OrderListFragment.class,
                R.id.account_admin_layout,null,
                MainActivity.INSTANCE.getResources().getString(R.string.orders),
                AccountAdminFragment.class);

    }

    public void settings() {

    }

    public void addHarmonica() {

        PopupMenu popupMenu = new PopupMenu(MainActivity.INSTANCE, binding.addHarmonicaToCatalog);
        popupMenu.inflate(R.menu.popup_menu);

        popupMenu.setOnMenuItemClickListener(item -> {

            switch (item.getItemId()) {
                case R.id.add_harmonica:
                    return true;
                case R.id.add_bayan:
                    return true;
                case R.id.add_accordion:
                    return true;
                default:
                    return false;
            }
        });

        popupMenu.show();
    }

    public void exit() {

        MainActivity.currentAdmin = null;
        new AdminRepositoryC(MainActivity.INSTANCE.getApplication()).delete();
        MainActivity.INSTANCE.load();

    }
}