package com.example.harmonicatulashop.ui.account.activities;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.databinding.ActivitySignUpBinding;
import com.example.harmonicatulashop.ui.account.viewmodels.SignUpViewModel;

public class SignUpActivity extends AppCompatActivity {

    public static SignUpActivity INSTANCE;

    private ActivitySignUpBinding binding;
    private SignUpViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        setContentView(binding.getRoot());

        viewModel.setBinding(binding);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        INSTANCE = this;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();

        return super.onSupportNavigateUp();
    }
}