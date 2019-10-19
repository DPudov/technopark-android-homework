package com.dpudov.homeworkandroidapp.data.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.dpudov.homeworkandroidapp.AppConstants;
import com.dpudov.homeworkandroidapp.data.NumberGenerator;

import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = NumberEntity.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance;

    public abstract NumberDao numberDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext());
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private static AppDatabase buildDatabase(final Context appContext) {
        return Room.databaseBuilder(appContext, AppDatabase.class, AppConstants.DB_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        AppDatabase database = getInstance(appContext);
                        Executors.newSingleThreadScheduledExecutor().execute(() -> {
                            List<NumberEntity> numbers = NumberGenerator.generateNumbers();
                            getInstance(appContext).populateData(numbers);
                        });
                        database.setDatabaseCreated();
                    }
                })
                .build();
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(AppConstants.DB_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    private void populateData(final List<NumberEntity> numbers) {
        sInstance.runInTransaction(() -> sInstance.numberDao().insertAll(numbers));
    }
}
