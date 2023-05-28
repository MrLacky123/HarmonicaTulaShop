package com.example.harmonicatulashop.ui.account.viewmodels;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.database.account.room.AccountRepository;
import com.example.harmonicatulashop.database.account.room.AccountRoomDatabase;
import com.example.harmonicatulashop.databinding.ActivitySignUpBinding;
import com.example.harmonicatulashop.models.account.User;
import com.example.harmonicatulashop.ui.account.activities.SignUpActivity;

import java.util.concurrent.Exchanger;

public class SignUpViewModel extends ViewModel {

    private ActivitySignUpBinding binding;

    public void setBinding(ActivitySignUpBinding binding) {
        this.binding = binding;
    }

    public void onClickSignUpButton() {

        TextView allFieldsRed = binding.allFieldsRed;

        assert allFieldsRed != null;
        allFieldsRed.setText(R.string.fields_red);
        allFieldsRed.setVisibility(View.GONE);

        EditText inputLogin = binding.signUpLogin;
        EditText inputPassword = binding.signUpPassword;
        EditText repeatPassword = binding.signUpPasswordRepeat;

        if (inputLogin.getText().toString().equals("") ||
                inputPassword.getText().toString().equals("") ||
                repeatPassword.getText().toString().equals("")) {

            allFieldsRed.setVisibility(View.VISIBLE);

            return;
        }

        String login = inputLogin.getText().toString();

        User u;

        Exchanger<User> userExchanger = new Exchanger<>();

        new Thread(new GetUser(userExchanger, login)).start();


        try {
            u = userExchanger.exchange(null);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (u != null) {

            allFieldsRed.setVisibility(View.VISIBLE);
            allFieldsRed.setText("Этот логин уже занят!");

            return;

        }

        if (inputPassword.getText().toString().hashCode() != repeatPassword.getText().toString().hashCode()) {

            allFieldsRed.setVisibility(View.VISIBLE);
            allFieldsRed.setText("Пароли не совпадают!");

            return;
        }

        User user = new User(null, login, inputPassword.getText().toString().hashCode(), "", null, null);

        new AccountRepository(SignUpActivity.INSTANCE.getApplication()).insertUser(user);
        SignUpActivity.INSTANCE.setResult(Activity.RESULT_OK);
        SignUpActivity.INSTANCE.finish();
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
                user = AccountRoomDatabase.getDatabase(SignUpActivity.INSTANCE.getApplication()).userDao().getUserByLogin(login);
                user = exchanger.exchange(user);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}