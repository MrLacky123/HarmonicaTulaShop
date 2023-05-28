package com.example.harmonicatulashop.ui.account.viewmodels;

import static com.example.harmonicatulashop.MainActivity.currentAdmin;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.database.account.current.room.CurrentRepository;
import com.example.harmonicatulashop.database.account.room.AccountRepository;
import com.example.harmonicatulashop.databinding.FragmentAdminSettingsBinding;
import com.example.harmonicatulashop.ui.account.fragments.AccountAdminFragment;
import com.google.common.collect.Iterables;

public class AdminSettingsViewModel extends ViewModel {

    private FragmentAdminSettingsBinding binding;

    public void saveChanges() {

        binding.wrongPassword.setVisibility(View.GONE);

        if (binding.confirmChangesAdmin.getText().toString().hashCode() != currentAdmin.getHashPassword()) {
            binding.wrongPassword.setVisibility(View.VISIBLE);
            return;
        }

        String login = binding.editAdminLogin.getText().toString();
        if (login.equals("")) {
            Toast.makeText(MainActivity.INSTANCE.getApplication(), "Поле логин не должно быть пустым!", Toast.LENGTH_SHORT).show();
            return;
        }

        String lastLogin = currentAdmin.getLogin();

        if (!login.equals(currentAdmin.getLogin())) {
            currentAdmin.setLogin(login);
        }

        String name = binding.editAdminName.getText().toString();
        if (name.equals("")) {
            Toast.makeText(MainActivity.INSTANCE.getApplication(), "Поле имя должно быть заполнено!", Toast.LENGTH_SHORT).show();
        }

        if (!name.equals(currentAdmin.getFirstname())) {
            currentAdmin.setFirstname(name);
        }

        String lastname = binding.editAdminLastname.getText().toString();
        if (!lastname.equals("") && !lastname.equals(currentAdmin.getLastname())) {
            currentAdmin.setLastname(lastname);
        }

        if (!binding.editAdminPassword.getText().toString().equals("")) {
            if (binding.editAdminPassword.getText().toString().hashCode() == binding.repeatEditPassword.getText().toString().hashCode()) {
                currentAdmin.setHashPassword(binding.editAdminPassword.getText().toString().hashCode());
            } else {
                Toast.makeText(MainActivity.INSTANCE.getApplication(), "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        new CurrentRepository(MainActivity.INSTANCE.getApplication()).setCurrentAdmin(currentAdmin);
        new AccountRepository(MainActivity.INSTANCE.getApplication()).replaceAdmin(lastLogin, currentAdmin);

        MainActivity.INSTANCE.setFragment(AccountAdminFragment.class,
                MainActivity.currentLayout, null,
                Iterables.getLast(MainActivity.previousTitles),
                null);
    }

    public void setBinding(FragmentAdminSettingsBinding binding) {
        this.binding = binding;
    }
}