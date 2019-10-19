package com.dpudov.homeworkandroidapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.dpudov.homeworkandroidapp.App;
import com.dpudov.homeworkandroidapp.data.NumberRepository;
import com.dpudov.homeworkandroidapp.data.db.NumberEntity;

import java.util.List;

public class NumbersListViewModel extends AndroidViewModel {
    private final MediatorLiveData<List<NumberEntity>> mObservableNumbers;
    private final NumberRepository mRepository;

    public NumbersListViewModel(@NonNull Application application) {
        super(application);
        mObservableNumbers = new MediatorLiveData<>();
        mRepository = ((App) application).getRepository();
        LiveData<List<NumberEntity>> numbers = mRepository.getNumbers();
        mObservableNumbers.addSource(numbers, mObservableNumbers::setValue);
    }

    public LiveData<List<NumberEntity>> getNumbers() {
        return mObservableNumbers;
    }

    public void insert(NumberEntity number) {
        mRepository.insert(number);
    }
}
