package com.example.harmonicatulashop;

import java.util.ArrayList;
import java.util.List;

public class Harmonica {

    private final HarmonicaType type;
    private final char tone;
    private final String color;
    private final List<Integer> range;

    private final ArrayList<String> options;

    public Harmonica(HarmonicaType type, char tone, String color, List<Integer> range, ArrayList<String> options) {
        this.type = type;
        this.tone = tone;
        this.color = color;
        this.range = range;
        this.options = options;
    }

    public HarmonicaType getType() {
        return type;
    }

    public char getTone() {
        return tone;
    }

    public String getColor() {
        return color;
    }

    public List<Integer> getRange() {
        return range;
    }

    public ArrayList<String> getOptions() {
        return options;
    }
}
