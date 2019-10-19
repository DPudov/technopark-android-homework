package com.dpudov.homeworkandroidapp.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.dpudov.homeworkandroidapp.data.db.AppDatabase;
import com.dpudov.homeworkandroidapp.data.db.NumberEntity;

import java.util.List;

public class NumberRepository {
    private static NumberRepository sInstance;
    private final AppDatabase mDatabase;
    private MediatorLiveData<List<NumberEntity>> mObservableNumbers;

    public static NumberRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (NumberRepository.class) {
                if (sInstance == null) {
                    sInstance = new NumberRepository(database);
                }
            }
        }
        return sInstance;
    }

    private NumberRepository(final AppDatabase database) {
        mDatabase = database;

        mObservableNumbers = new MediatorLiveData<>();
        mObservableNumbers.addSource(mDatabase.numberDao().getAll(), numberEntities -> {
            if (mDatabase.getDatabaseCreated().getValue() != null) {
                mObservableNumbers.postValue(numberEntities);
            }
        });
    }

    public LiveData<List<NumberEntity>> getNumbers() {
        return mObservableNumbers;
    }
}
