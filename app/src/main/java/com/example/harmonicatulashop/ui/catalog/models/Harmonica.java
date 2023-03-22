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

    //@NonNull
    //@ColumnInfo(name = "options")
    private String options;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIconUri(String iconUri) {
        this.iconUri = iconUri;
    }

    public String getIconUri() {
        return iconUri;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setTone(@NonNull String tone) {
        this.tone = tone;
    }

    @NonNull
    public String getTone() {
        return tone;
    }

    @NonNull
    public String getRange() {
        return range;
    }

    public void setRange(@NonNull String range) {
        this.range = range;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @NonNull
    public String getOptions() {
        return options;
    }

    public void setOptions(@NonNull String options) {
        this.options = options;
    }

    public Harmonica() {
    }

    public Harmonica(String iconUrl, @NonNull String type, @NonNull String tone, @NonNull String range, int price, @NonNull String options) {
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
        options = in.readString();
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
        dest.writeString(options);
    }
}
