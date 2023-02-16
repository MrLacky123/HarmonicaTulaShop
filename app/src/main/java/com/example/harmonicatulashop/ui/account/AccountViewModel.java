package com.example.harmonicatulashop.ui.account;

import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccountViewModel extends ViewModel {

    private final MutableLiveData<Button> signUpButton;

    public AccountViewModel() {
        signUpButton = new MutableLiveData<>();
    }

    public LiveData<Button> getButton() {
        return signUpButton;
    }

}
