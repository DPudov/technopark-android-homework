package com.dpudov.homeworkandroidapp.data.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.dpudov.homeworkandroidapp.data.NumberModel;

@Entity(tableName = "numbers")
public class NumberEntity {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    public void setValue(int value) {
        this.value = value;
    }

    @ColumnInfo(name = "value")
    private int value;

    public NumberEntity(NumberModel model) {
        this.value = model.getNumber();
    }

    public NumberEntity(int uid, int value) {
        this.uid = uid;
        this.value = value;
    }

    public NumberEntity(int i) {
        this.value = i;
    }


    public int getValue() {
        return value;
    }

    public boolean isOdd() {
        return value % 2 == 1;
    }

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(getValue());
    }
}
