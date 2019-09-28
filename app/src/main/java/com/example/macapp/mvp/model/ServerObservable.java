package com.example.macapp.mvp.model;

import com.example.macapp.App;
import com.example.macapp.mvp.presenter.ServerObserver;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashSet;
import java.util.Iterator;

import androidx.annotation.NonNull;

public class ServerObservable {

    private HashSet<ServerObserver> set;

    ServerObservable() {
        set = new HashSet<ServerObserver>();
        listenRequest();
    }

    public void subscribeRequest(ServerObserver observer){
        set.add(observer);
    }

    public void unsubscribeRequest(ServerObserver observer){
        set.remove(observer);
    }

    private void notifySubscribers(Long value){
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            ServerObserver observer = (ServerObserver) iterator.next();
            observer.request(value);
        }
        App.getInstance().getDatabaseRequestReference().setValue(null);
    }

    private void listenRequest() {
        App.getInstance().getDatabaseRequestReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Long value = dataSnapshot.getValue(Long.class);
                notifySubscribers(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


}
