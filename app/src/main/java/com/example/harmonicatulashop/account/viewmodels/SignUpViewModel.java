package com.example.harmonicatulashop.account.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.account.SignUpActivity;

public class SignUpViewModel extends ViewModel {

    public void onClickSignUpButton() {

        SignUpActivity.Instance.finish();
    }
}
