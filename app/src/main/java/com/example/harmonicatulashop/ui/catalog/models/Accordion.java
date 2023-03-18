package com.example.harmonicatulashop.ui.catalog.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Accordion implements Parcelable {

    public static final String NAME = "Аккордеон";

    private String iconPath;

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }


    protected Accordion(Parcel in) {
    }

    public static final Creator<Accordion> CREATOR = new Creator<Accordion>() {
        @Override
        public Accordion createFromParcel(Parcel in) {
            return new Accordion(in);
        }

        @Override
        public Accordion[] newArray(int size) {
            return new Accordion[size];
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
