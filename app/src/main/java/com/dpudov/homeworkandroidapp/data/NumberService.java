package com.dpudov.homeworkandroidapp.data;

import java.util.ArrayList;
import java.util.List;

public class NumberService {
    private List<NumberModel> mData;

    private static final NumberService ourInstance = new NumberService();

    public static NumberService getInstance() {
        return ourInstance;
    }


    private NumberService() {
        mData = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            mData.add(new NumberModel(i));
        }
    }

    public List<NumberModel> getData() {
        return mData;
    }
}
