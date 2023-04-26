package com.example.harmonicatulashop.account.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.account.SignInActivity;

public class SignInViewModel extends ViewModel {

    public void onClickSignInButton() {

        SignInActivity.Instance.finish();
    }
}
