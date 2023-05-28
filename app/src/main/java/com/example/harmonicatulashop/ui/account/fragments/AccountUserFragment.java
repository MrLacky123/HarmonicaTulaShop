package com.example.harmonicatulashop.ui.account.fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.databinding.FragmentAccountUserBinding;
import com.example.harmonicatulashop.models.account.User;
import com.example.harmonicatulashop.ui.account.viewmodels.AccountUserViewModel;

public class AccountUserFragment extends Fragment {

    private AccountUserViewModel viewModel;
    private FragmentAccountUserBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(MainActivity.INSTANCE).get(AccountUserViewModel.class);

        binding = FragmentAccountUserBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        bindView(MainActivity.currentUser);
        super.onStart();
    }

    public void bindView(User user) {

        if (user == null) {
            return;
        }

        binding.loginUser.setText(user.getLogin());
        binding.nameUser.setText(user.getFirstname());
        binding.lastnameUser.setText(user.getLastname());

        byte[] image = user.getAvatarImage();

        if (image == null) {
            return;
        }

        binding.profileImageUser.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));

    }
}
