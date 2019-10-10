package com.dpudov.homeworkandroidapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class NumberModel implements Parcelable {
    private int mNumber;

    public NumberModel(int number) {
        this.mNumber = number;
    }

    protected NumberModel(Parcel in) {
        mNumber = in.readInt();
    }

    public static final Creator<NumberModel> CREATOR = new Creator<NumberModel>() {
        @Override
        public NumberModel createFromParcel(Parcel in) {
            return new NumberModel(in);
        }

        @Override
        public NumberModel[] newArray(int size) {
            return new NumberModel[size];
        }
    };

    public int getNumber() {
        return mNumber;
    }

    public void setNumber(int mNumber) {
        this.mNumber = mNumber;
    }

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(mNumber);
    }

    public boolean isOdd() {
        return mNumber % 2 == 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mNumber);
    }
}
