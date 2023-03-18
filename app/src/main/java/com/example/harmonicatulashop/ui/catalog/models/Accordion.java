package com.example.harmonicatulashop.ui.catalog.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class Accordion implements Parcelable {

    public static final String NAME = "Аккордеон";

    private String iconPath;

    private String range;

    private ArrayList<String> options;

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public Accordion() {}


    protected Accordion(Parcel in) {
        iconPath = in.readString();
        range = in.readString();
        options = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iconPath);
        dest.writeString(range);
        dest.writeStringList(options);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
