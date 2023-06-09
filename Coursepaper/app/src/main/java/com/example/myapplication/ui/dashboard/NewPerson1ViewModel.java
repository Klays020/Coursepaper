package com.example.myapplication.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewPerson1ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NewPerson1ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Информация о герое");
    }

    public LiveData<String> getText() {
        return mText;
    }
}