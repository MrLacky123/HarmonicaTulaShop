package com.example.harmonicatulashop.ui.account.viewmodels;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.ui.account.activities.SignInActivity;
import com.example.harmonicatulashop.ui.account.activities.SignUpActivity;

public class AccountViewModel extends ViewModel {

    public void signUp() {

        Intent intent = new Intent(MainActivity.INSTANCE, SignUpActivity.class);
        MainActivity.INSTANCE.startActivity(intent);

    }

    public void signIn() {

        Intent intent = new Intent(MainActivity.INSTANCE, SignInActivity.class);
        MainActivity.INSTANCE.startActivity(intent);

    }
}