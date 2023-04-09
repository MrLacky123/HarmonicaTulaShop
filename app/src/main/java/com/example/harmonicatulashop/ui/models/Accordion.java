package com.example.harmonicatulashop.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Accordion extends Harmonicas {

    public static final String NAME = "Аккордеон";

    private String iconUri;

    private String range;

    private int price;

    private ArrayList<String> options;

    public String getIconUri() {
        return iconUri;
    }

    public void setIconUri(String iconUri) {
        this.iconUri = iconUri;
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

    public Accordion() {}
}
