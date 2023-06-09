package com.example.myapplication.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewStoryViewModelFirst extends ViewModel {

    private MutableLiveData<String> mText;

    public NewStoryViewModelFirst() {
        mText = new MutableLiveData<>();
        mText.setValue("Привет, давай начнём! \n \nОбщий принцип построения текстового квеста заключается в создании сюжета, " +
                "\n описании персонажа и вариантов действий. \n Приложение позволяет настроить персонажа и настроить выбор пути персонажа, давайте сделаем небольшой пример." +
                "\n \n Далее будет представлена небольшая заготовка с которой вы сможете поработать. ");
    }

    public LiveData<String> getText() {
        return mText;
    }
}