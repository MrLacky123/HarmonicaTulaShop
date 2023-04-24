package com.example.harmonicatulashop.ui.account;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.account.SignInActivity;
import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.account.SignUpActivity;

public class AccountViewModel extends ViewModel {

    public void signUp() {

        Intent intent = new Intent(MainActivity.Instance, SignUpActivity.class);
        MainActivity.Instance.startActivity(intent);

    }

    public void signIn() {

        Intent intent = new Intent(MainActivity.Instance, SignInActivity.class);
        MainActivity.Instance.startActivity(intent);

    }
}