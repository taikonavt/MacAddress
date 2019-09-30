package com.example.macapp.server.mvp.model;

import com.example.macapp.App;

public class ServerRepository extends ServerObservable{

    public ServerRepository(){
    }

    public void getMacAddresses(ResultWraper resultWraper) {
        GetConnectedDevicesTask task = new GetConnectedDevicesTask();
        task.execute(resultWraper);
    }

    public void sendResponse(boolean contains) {
        App.getInstance().getDatabaseResponseReference().setValue(contains);
    }
}
