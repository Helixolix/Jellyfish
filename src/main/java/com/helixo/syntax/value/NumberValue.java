package com.helixo.syntax.value;

public class NumberValue implements Value {

    public static final NumberValue ZERO = new NumberValue(0);

    private double value;

    public NumberValue(boolean value) {
        this.value = value ? 1 : 0;
    }

    public NumberValue(double value) {
        this.value = value;
    }

    @Override
    public double asNumber() {
        return value;
    }

    @Override
    public String asString() {
        return Double.toString(value);
    }

    @Override
    public String toString() {
        return asString();
    }
}
