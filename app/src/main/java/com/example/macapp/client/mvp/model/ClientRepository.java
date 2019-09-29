package com.example.macapp.client.mvp.model;

import com.example.macapp.App;

public class ClientRepository extends ClientObservable {

    public void sendMac(Long macAddress){
        App.getInstance().getDatabaseRequestReference().setValue(macAddress);
    }
}
