package com.example.harmonicatulashop;

import java.util.List;

public class Harmonica {

    private final HarmonicaType type;
    private final char tone;
    private final List<Integer> range;

    public Harmonica(HarmonicaType type, char tone, List<Integer> range) {
        this.type = type;
        this.tone = tone;
        this.range = range;
    }

    public HarmonicaType getType() {
        return type;
    }

    public char getTone() {
        return tone;
    }

    public List<Integer> getRange() {
        return range;
    }
}
