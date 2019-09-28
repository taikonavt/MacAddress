package com.example.macapp.mvp.model;

import com.example.macapp.App;
import com.example.macapp.mvp.presenter.ClientObserver;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashSet;
import java.util.Iterator;

import androidx.annotation.NonNull;

public class ClientObservable {

    private HashSet<ClientObserver> set;

    ClientObservable() {
        set = new HashSet<ClientObserver>();
        listenResponse();
    }

    public void subscribeResponse(ClientObserver observer){
        set.add(observer);
    }

    public void unsubscribeResponse(ClientObserver observer){
        set.remove(observer);
    }

    private void notifySubscribers(Boolean value){
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            ClientObserver observer = (ClientObserver) iterator.next();
            observer.update(value);
        }
        App.getInstance().getDatabaseResponseReference().setValue(null);
    }

    private void listenResponse() {
        App.getInstance().getDatabaseResponseReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean value = dataSnapshot.getValue(Boolean.class);
                notifySubscribers(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
