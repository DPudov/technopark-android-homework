package com.dpudov.homeworkandroidapp.data;

import com.dpudov.homeworkandroidapp.data.db.NumberEntity;

import java.util.ArrayList;
import java.util.List;

public class NumberGenerator {
    public static List<NumberEntity> generateNumbers() {
        List<NumberEntity> result = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            result.add(new NumberEntity(i));
        }
        return result;
    }
}
