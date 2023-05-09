package com.example.harmonicatulashop.ui.account.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.ui.account.viewmodels.SignInViewModel;
import com.example.harmonicatulashop.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    public static SignInActivity Instance;

    private ActivitySignInBinding binding;
    private SignInViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        viewModel = new ViewModelProvider(this).get(SignInViewModel.class);

        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        binding.setViewModel(viewModel);
        binding.executePendingBindings();
        setContentView(binding.getRoot());

        Instance = this;

    }
}