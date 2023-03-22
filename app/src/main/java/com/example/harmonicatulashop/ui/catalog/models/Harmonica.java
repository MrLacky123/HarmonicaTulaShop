package com.example.harmonicatulashop.ui.catalog.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "harmonica")
public class Harmonica implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public static final String NAME = "Гармонь";

    private String iconUri;

    @NonNull
    @ColumnInfo(name = "type")
    private String type;

    @NonNull
    @ColumnInfo(name = "tone")
    private String tone;

    @NonNull
    @ColumnInfo(name = "range")
    private String range;

    @NonNull
    @ColumnInfo(name = "price")
    private int price;

    @NonNull
    @ColumnInfo(name = "options")
    private ArrayList<String> options;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIconUrl(String iconUri) {
        this.iconUri = iconUri;
    }

    public String getIconUrl() {
        return iconUri;
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

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public Harmonica() {
    }

    public Harmonica(String iconUrl, String type, String tone, String range, int price, ArrayList<String> options) {
        this.iconUri = iconUrl;
        this.type = type;
        this.tone = tone;
        this.range = range;
        this.price = price;
        this.options = options;
    }

    protected Harmonica(Parcel in) {
        iconUri = in.readString();
        type = in.readString();
        tone = in.readString();
        range = in.readString();
        price = in.readInt();
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
        dest.writeString(iconUri);
        dest.writeString(type);
        dest.writeString(tone);
        dest.writeString(range);
        dest.writeInt(price);
        dest.writeStringList(options);
    }
}
