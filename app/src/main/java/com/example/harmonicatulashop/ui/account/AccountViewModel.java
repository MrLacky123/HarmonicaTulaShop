package com.example.harmonicatulashop.ui.account;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

public class AccountViewModel extends ViewModel {

    public ObservableField<AccountFragment> fragment = new ObservableField<>();

    public AccountViewModel(ObservableField<AccountFragment> fragment) {
        fragment.set(new AccountFragment());
    }

    public void signUp(){

    }

    public void signIn(){

    }
}
