package com.ihaterunning.domain.enumeration;

/**
 * The Distance enumeration.
 */
public enum Distance {
    FIVEK("5K"),
    TENK("10K"),
    HALF("Half marathon"),
    MARATHON("Marathon"),
    OTHER("Other");

    private final String value;

    Distance(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
