package com.example.myapplication.db;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    private static App instance;
    private AppDatabase database;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // Инициализация базы данных
        database = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "my-database")
                .build();
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
