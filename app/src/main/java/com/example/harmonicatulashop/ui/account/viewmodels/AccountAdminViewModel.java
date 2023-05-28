package com.example.harmonicatulashop.ui.account.viewmodels;

import static com.example.harmonicatulashop.MainActivity.GALLERY_REQUEST_FOR_ADMIN;

import android.content.Intent;
import android.widget.PopupMenu;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.database.account.current.room.CurrentRepository;
import com.example.harmonicatulashop.databinding.FragmentAccountAdminBinding;
import com.example.harmonicatulashop.ui.account.fragments.AccountAdminFragment;
import com.example.harmonicatulashop.ui.account.fragments.AddAccordionFragment;
import com.example.harmonicatulashop.ui.account.fragments.AddBayanFragment;
import com.example.harmonicatulashop.ui.account.fragments.AddHarmonicaFragment;
import com.example.harmonicatulashop.ui.account.fragments.AdminSettingsFragment;
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

        MainActivity.INSTANCE.setFragment(AdminSettingsFragment.class,
                R.id.account_admin_layout, null,
                MainActivity.INSTANCE.getResources().getString(R.string.settings),
                AccountAdminFragment.class);

    }

    public void addHarmonica() {

        PopupMenu popupMenu = new PopupMenu(MainActivity.INSTANCE, binding.addHarmonicaToCatalog);
        popupMenu.inflate(R.menu.popup_menu);

        popupMenu.setOnMenuItemClickListener(item -> {

            switch (item.getItemId()) {
                case R.id.add_harmonica:
                    MainActivity.INSTANCE.setFragment(AddHarmonicaFragment.class,
                            R.id.account_admin_layout, null,
                            "Добавить гармонь",
                            AccountAdminFragment.class);
                    return true;
                case R.id.add_bayan:
                    MainActivity.INSTANCE.setFragment(AddBayanFragment.class,
                            R.id.account_admin_layout, null,
                            "Добавить баян",
                            AccountAdminFragment.class);
                    return true;
                case R.id.add_accordion:
                    MainActivity.INSTANCE.setFragment(AddAccordionFragment.class,
                            R.id.account_admin_layout, null,
                            "Добавить аккордеон",
                            AccountAdminFragment.class);
                    return true;
                default:
                    return false;
            }
        });

        popupMenu.show();
    }

    public void exit() {

        MainActivity.currentAdmin = null;
        new CurrentRepository(MainActivity.INSTANCE.getApplication()).deleteCurrentAdmin();
        MainActivity.INSTANCE.load();

    }
}