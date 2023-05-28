package com.example.harmonicatulashop.ui.account.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.databinding.ActivitySignInBinding;
import com.example.harmonicatulashop.models.account.Admin;
import com.example.harmonicatulashop.ui.account.viewmodels.SignInViewModel;

import java.util.List;

public class SignInActivity extends AppCompatActivity {

    public static SignInActivity INSTANCE;

    private ActivitySignInBinding binding;
    private SignInViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(SignInViewModel.class);

        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.setBinding(binding);

        setContentView(binding.getRoot());

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