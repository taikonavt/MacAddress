package com.example.macapp.mvp.model;

import com.example.macapp.App;

public class Repository extends ResponseObservable {

    public void sendMac(Long macAddress){
        App.getInstance().getDatabaseReference().setValue(macAddress);
    }
}
