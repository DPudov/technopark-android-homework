package com.dpudov.homeworkandroidapp.data;

public class NumberModel {
    private int mNumber;

    public NumberModel(int number) {
        this.mNumber = number;
    }

    public int getNumber() {
        return mNumber;
    }

    public void setNumber(int mNumber) {
        this.mNumber = mNumber;
    }

    @Override
    public String toString() {
        return String.valueOf(mNumber);
    }

    public boolean isOdd() {
        return mNumber % 2 == 1;
    }
}
