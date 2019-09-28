package com.example.macapp.mvp.model;

import com.example.macapp.App;
import com.example.macapp.mvp.presenter.ResponseObserver;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashSet;
import java.util.Iterator;

import androidx.annotation.NonNull;

public class ResponseObservable {

    private HashSet<ResponseObserver> set;

    ResponseObservable() {
        set = new HashSet<ResponseObserver>();
        listenMessages();
    }

    public void subscribeResponse(ResponseObserver observer){
        set.add(observer);
    }

    public void unsubscribeResponse(ResponseObserver observer){
        set.remove(observer);
    }

    private void notifySubscribers(Long value){
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            ResponseObserver observer = (ResponseObserver) iterator.next();
            observer.update(value);
        }
    }

    private void listenMessages() {
        App.getInstance().getDatabaseReference().addValueEventListener(new ValueEventListener() {
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
