package com.example.harmonicatulashop;

import java.util.ArrayList;

public class Harmonica {

    private final String type;

    private final ArrayList<String> options;

    public Harmonica(String type, ArrayList<String> options) {
        this.type = type;
        this.options = options;
    }

    public String getType() {
        return type;
    }

    public ArrayList<String> getOptions() {
        return options;
    }
}
