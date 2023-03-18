package com.example.harmonicatulashop.ui.catalog.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.harmonicatulashop.databinding.ItemHarmonicaBinding;

import java.util.ArrayList;

public class Harmonica implements Parcelable {

    private ItemHarmonicaBinding binding;

    public static final String NAME = "Гармонь";

    private String iconUrl;

    private String type;

    private String tone;

    private ArrayList<String> options;

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }

    public String getTone() {
        return tone;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public Harmonica() {}

    public Harmonica(String iconUrl, String type, String tone, ArrayList<String> options) {
        this.iconUrl = iconUrl;
        this.type = type;
        this.tone = tone;
        this.options = options;
    }

    protected Harmonica(Parcel in) {
        iconUrl = in.readString();
        type = in.readString();
        tone = in.readString();
        options = in.createStringArrayList();
    }

    public static final Creator<Harmonica> CREATOR = new Creator<Harmonica>() {
        @Override
        public Harmonica createFromParcel(Parcel in) {
            return new Harmonica(in);
        }

        @Override
        public Harmonica[] newArray(int size) {
            return new Harmonica[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(iconUrl);
        dest.writeString(type);
        dest.writeString(tone);
        dest.writeStringList(options);
    }
}
