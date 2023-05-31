package com.example.harmonicatulashop.ui.account.viewmodels;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.database.account.current.room.CurrentRepository;
import com.example.harmonicatulashop.database.account.room.AccountRoomDatabase;
import com.example.harmonicatulashop.databinding.ActivitySignInBinding;
import com.example.harmonicatulashop.models.account.Admin;
import com.example.harmonicatulashop.models.account.User;
import com.example.harmonicatulashop.ui.account.activities.SignInActivity;

import java.util.concurrent.Exchanger;

public class SignInViewModel extends ViewModel {
    private ActivitySignInBinding binding;

    public void setBinding(ActivitySignInBinding binding) {
        this.binding = binding;
    }

    public void onClickSignInButton() {

        assert binding.loginRed != null;
        assert binding.passwordRed != null;

        TextView loginRed = binding.loginRed;
        TextView passwordRed = binding.passwordRed;

        EditText editTextLogin = binding.editTextLogin;
        EditText editTextPassword = binding.editTextPassword;

        loginRed.setVisibility(View.GONE);
        passwordRed.setVisibility(View.GONE);

        String login = String.valueOf(editTextLogin.getText());

        if (login.equals("")) {
            loginRed.setVisibility(View.VISIBLE);
            return;
        }

        loginRed.setVisibility(View.GONE);

        if (editTextPassword.length() == 0) {
            passwordRed.setVisibility(View.VISIBLE);
            return;
        }

        passwordRed.setVisibility(View.GONE);

        Admin admin;
        User user;

        Exchanger<Admin> adminExchanger = new Exchanger<>();
        Exchanger<User> userExchanger = new Exchanger<>();

        new Thread(new GetAdmin(adminExchanger, login)).start();
        new Thread(new GetUser(userExchanger, login)).start();

        try {
            admin = adminExchanger.exchange(null);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            user = userExchanger.exchange(null);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (admin != null) {

            long password = editTextPassword.getText().toString().hashCode();

            if (password == admin.getHashPassword()) {

                new CurrentRepository(SignInActivity.INSTANCE.getApplication()).setCurrentAdmin(admin);
                MainActivity.currentAdmin = admin;
                SignInActivity.INSTANCE.finish();

            } else {

                passwordRed.setText(MainActivity.INSTANCE.getResources().getString(R.string.wrong_password));
                passwordRed.setVisibility(View.VISIBLE);
                editTextPassword.setText("");

            }
        } else if (user != null) {

            long password = editTextPassword.getText().toString().hashCode();

            if (password == user.getHashPassword()) {

                new CurrentRepository(SignInActivity.INSTANCE.getApplication()).setCurrentUser(user);
                MainActivity.currentUser = user;
                SignInActivity.INSTANCE.finish();

            } else {

                passwordRed.setText(MainActivity.INSTANCE.getResources().getString(R.string.wrong_password));
                passwordRed.setVisibility(View.VISIBLE);
                editTextPassword.setText("");

            }
        } else {

            loginRed.setText("Введен неверный логин");
            loginRed.setVisibility(View.VISIBLE);

        }
    }

    private static class GetAdmin implements Runnable {

        Exchanger<Admin> exchanger;
        Admin admin;
        String login;

        public GetAdmin(Exchanger<Admin> exchanger, String login) {
            this.exchanger = exchanger;
            this.login = login;
        }

        @Override
        public void run() {
            try {
                admin = AccountRoomDatabase.getDatabase(SignInActivity.INSTANCE.getApplication()).adminDao().getAdminByLogin(login);
                admin = exchanger.exchange(admin);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class GetUser implements Runnable {

        Exchanger<User> exchanger;
        User user;
        String login;

        public GetUser(Exchanger<User> exchanger, String login) {
            this.exchanger = exchanger;
            this.login = login;
        }

        @Override
        public void run() {
            try {
                user = AccountRoomDatabase.getDatabase(SignInActivity.INSTANCE.getApplication()).userDao().getUserByLogin(login);
                user = exchanger.exchange(user);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}