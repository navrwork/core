package com.navr.core.java8;

public class DoubleImpl implements DoubleFunction {
    @Override
    public long findDouble(int number) {
        return (long)2*number;
    }
}
