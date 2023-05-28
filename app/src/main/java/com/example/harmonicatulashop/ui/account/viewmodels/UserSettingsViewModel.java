package com.example.harmonicatulashop.ui.account.viewmodels;

import static com.example.harmonicatulashop.MainActivity.currentUser;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.database.account.current.room.CurrentRepository;
import com.example.harmonicatulashop.database.account.room.AccountRepository;
import com.example.harmonicatulashop.databinding.FragmentUserSettingsBinding;
import com.example.harmonicatulashop.ui.account.fragments.AccountUserFragment;
import com.google.common.collect.Iterables;

public class UserSettingsViewModel extends ViewModel {

    private FragmentUserSettingsBinding binding;

    public void saveChanges() {

        binding.wrongPassword.setVisibility(View.GONE);

        if (binding.confirmChangesUser.getText().toString().hashCode() != currentUser.getHashPassword()) {

            binding.wrongPassword.setVisibility(View.VISIBLE);
            return;

        }

        String login = binding.editUserLogin.getText().toString();
        if (login.equals("")) {
            Toast.makeText(MainActivity.INSTANCE.getApplication(), "Поле логин не должно быть пустым!", Toast.LENGTH_SHORT).show();
            return;
        }

        String lastLogin = currentUser.getLogin();

        if (!login.equals(currentUser.getLogin())) {
            currentUser.setLogin(login);
        }

        String name = binding.editUserName.getText().toString();
        if (name.equals("")) {
            Toast.makeText(MainActivity.INSTANCE.getApplication(), "Поле имя должно быть заполнено!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!name.equals(currentUser.getFirstname())) {
            currentUser.setFirstname(name);
        }

        String lastname = binding.editUserLastname.getText().toString();
        if (!lastname.equals("") && !lastname.equals(currentUser.getLastname())) {
            currentUser.setLastname(lastname);
        }

        String address = binding.editUserAddress.getText().toString();
        if (!address.equals("") && !address.equals(currentUser.getAddress())) {
            currentUser.setAddress(address);
        }

        if (!binding.editUserPassword.getText().toString().equals("")) {
            if (binding.editUserPassword.getText().toString().hashCode() == binding.repeatEditPassword.getText().toString().hashCode()) {
                currentUser.setHashPassword(binding.editUserPassword.getText().toString().hashCode());
            } else {
                Toast.makeText(MainActivity.INSTANCE.getApplication(), "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        new CurrentRepository(MainActivity.INSTANCE.getApplication()).setCurrentUser(currentUser);
        new AccountRepository(MainActivity.INSTANCE.getApplication()).replaceUser(lastLogin, currentUser);

        MainActivity.INSTANCE.setFragment(AccountUserFragment.class,
                MainActivity.currentLayout, null,
                Iterables.getLast(MainActivity.previousTitles),
                null);
    }

    public void setBinding(FragmentUserSettingsBinding binding) {
        this.binding = binding;
    }
}