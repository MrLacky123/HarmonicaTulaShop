package com.example.harmonicatulashop.ui.account.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.ui.account.activities.SignInActivity;

public class SignInViewModel extends ViewModel {

    public void onClickSignInButton() {

        SignInActivity.Instance.finish();
    }
}
