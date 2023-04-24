package com.example.harmonicatulashop.database.account.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.harmonicatulashop.models.account.Admin;
import com.example.harmonicatulashop.ui.account.viewholders.AdminViewHolder;

public class AdminAdapter extends ListAdapter<Admin, AdminViewHolder> {

    protected AdminAdapter(@NonNull DiffUtil.ItemCallback<Admin> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewHolder holder, int position) {

    }
}
