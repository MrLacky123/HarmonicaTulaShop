package com.example.harmonicatulashop;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

//@Entity(tableName = "harmonicas",indices = @Index(value = {"id"},unique = true))
public class Harmonica implements Parcelable {

    public int id;

    public String iconUrl;

    public String type;

    public ArrayList<String> options;

    public Harmonica() {}

    public Harmonica(int id, String iconUrl, String type, ArrayList<String> options) {
        this.id = id;
        this.iconUrl = iconUrl;
        this.type = type;
        this.options = options;
    }

    protected Harmonica(Parcel in) {
        id = in.readInt();
        iconUrl = in.readString();
        type = in.readString();
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
        dest.writeInt(id);
        dest.writeString(iconUrl);
        dest.writeString(type);
        dest.writeStringList(options);
    }
}
