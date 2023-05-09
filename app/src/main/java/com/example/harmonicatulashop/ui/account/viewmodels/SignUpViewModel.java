package com.example.harmonicatulashop.ui.account.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.harmonicatulashop.ui.account.activities.SignUpActivity;

public class SignUpViewModel extends ViewModel {

    public void onClickSignUpButton() {

        SignUpActivity.Instance.finish();
    }
}
