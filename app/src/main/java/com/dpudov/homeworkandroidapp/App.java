package com.dpudov.homeworkandroidapp;

import android.app.Application;

import com.dpudov.homeworkandroidapp.data.NumberRepository;
import com.dpudov.homeworkandroidapp.data.db.AppDatabase;

public class App extends Application {
    public AppDatabase getAppDatabase() {
        return AppDatabase.getInstance(this);
    }

    public NumberRepository getRepository() {
        return NumberRepository.getInstance(getAppDatabase());
    }
}
