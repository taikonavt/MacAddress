package com.example.macapp;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class App extends Application {

    static private App instance;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("request");
    }

    public static App getInstance(){
        return instance;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }
}
