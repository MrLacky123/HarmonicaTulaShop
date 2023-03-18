package com.example.harmonicatulashop.ui.catalog.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Bayan implements Parcelable {

    protected Bayan(Parcel in) {
    }

    public static final Creator<Bayan> CREATOR = new Creator<Bayan>() {
        @Override
        public Bayan createFromParcel(Parcel in) {
            return new Bayan(in);
        }

        @Override
        public Bayan[] newArray(int size) {
            return new Bayan[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
    }
}
