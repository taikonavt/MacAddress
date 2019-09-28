package com.example.macapp;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class App extends Application {

    static private App instance;

    private FirebaseDatabase database;
    private DatabaseReference databaseRequestReference;
    private DatabaseReference databaseResponseReference;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        database = FirebaseDatabase.getInstance();
        databaseRequestReference = database.getReference("request");
        databaseResponseReference = database.getReference("response");
    }

    public static App getInstance(){
        return instance;
    }

    public DatabaseReference getDatabaseRequestReference() {
        return databaseRequestReference;
    }

    public DatabaseReference getDatabaseResponseReference() {
        return databaseResponseReference;
    }
}
