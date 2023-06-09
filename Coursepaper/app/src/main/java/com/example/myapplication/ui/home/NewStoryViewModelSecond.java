package com.example.myapplication.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewStoryViewModelSecond extends ViewModel {

    private MutableLiveData<String> text;

    public NewStoryViewModelSecond() {
        text = new MutableLiveData<>();
        text.setValue("Обратите внимание на содержание текста. \n Данные созданного персонажа были переданы в содержательную часть истории.");
    }

    public LiveData<String> getText() {
        return text;
    }
}
