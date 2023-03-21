package com.example.harmonicatulashop.ui.catalog.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Bayan implements Parcelable {

    public static final String NAME = "Баян";

    private String range;

    private String iconUri;

    private ArrayList<String> options = new ArrayList<>();

    public Bayan() {}

    public Bayan(String range, String iconUri, ArrayList<String> options) {
        this.range = range;
        this.iconUri = iconUri;
        this.options = options;
    }

    protected Bayan(Parcel in) {
        range = in.readString();
        iconUri = in.readString();
        options = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(range);
        dest.writeString(iconUri);
        dest.writeStringList(options);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getIconUri() {
        return iconUri;
    }

    public void setIconUri(String iconUri) {
        this.iconUri = iconUri;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }
}
