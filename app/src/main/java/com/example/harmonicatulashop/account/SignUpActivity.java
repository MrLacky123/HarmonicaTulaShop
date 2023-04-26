package com.example.harmonicatulashop.account;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.account.viewmodels.SignUpViewModel;
import com.example.harmonicatulashop.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    public static SignUpActivity Instance;

    private ActivitySignUpBinding binding;
    private SignUpViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        Instance = this;
    }
}