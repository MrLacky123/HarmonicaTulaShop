package com.example.harmonicatulashop.ui.account.viewmodels;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.database.account.room.UserRepository;
import com.example.harmonicatulashop.databinding.ActivitySignUpBinding;
import com.example.harmonicatulashop.models.account.User;
import com.example.harmonicatulashop.ui.account.activities.SignUpActivity;

import java.util.List;

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

        UserRepository userRepository = new UserRepository(SignUpActivity.INSTANCE.getApplication());

        userRepository.getUsers().observe(SignUpActivity.INSTANCE, users -> {

            for (User u: users) {
                if (u.getLogin().equals(login)) {

                    allFieldsRed.setVisibility(View.VISIBLE);
                    allFieldsRed.setText("Этот логин уже занят!");

                    return;
                }
            }

            if (inputPassword.getText().toString().hashCode() != repeatPassword.getText().toString().hashCode()) {

                allFieldsRed.setVisibility(View.VISIBLE);
                allFieldsRed.setText("Пароли не совпадают!");

                return;
            }

            User user = new User(null, login, inputPassword.getText().toString().hashCode(), "", null, null);

            userRepository.insert(user);
            SignUpActivity.INSTANCE.setResult(Activity.RESULT_OK);
            SignUpActivity.INSTANCE.finish();
        });
    }
}