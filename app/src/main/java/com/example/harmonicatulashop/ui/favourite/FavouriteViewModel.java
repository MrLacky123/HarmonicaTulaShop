package com.example.harmonicatulashop.ui.favourite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavouriteViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FavouriteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}