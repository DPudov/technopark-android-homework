package com.dpudov.homeworkandroidapp.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NumberDao {
    @Query("SELECT * FROM numbers")
    LiveData<List<NumberEntity>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<NumberEntity> numbers);
}
