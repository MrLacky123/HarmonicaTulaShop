package com.example.harmonicatulashop.models.account.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.harmonicatulashop.models.account.User;
import com.example.harmonicatulashop.ui.account.viewholders.UserViewHolder;

public class UserAdapter extends ListAdapter<User, UserViewHolder> {

    protected UserAdapter(@NonNull DiffUtil.ItemCallback<User> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

    }
}
