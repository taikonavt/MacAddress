package com.example.macapp.server.mvp.presenter;

import com.example.macapp.server.mvp.model.ServerRepository;
import com.example.macapp.server.mvp.view.ServerView;

import java.util.Set;

public class ServerPresenter implements ServerObserver{

    private ServerView serverView;
    private ServerRepository repository;

    private Set macAddressesSet;

    public ServerPresenter(ServerView serverView){
        this.serverView = serverView;
        this.repository = new ServerRepository();
    }

    public void onResume() {
        repository.getMacAddresses(result -> {
            macAddressesSet = result;
            Object[] macAddressesArray = macAddressesSet.toArray();
            String macAddressString = new String();
            for (int i = 0; i < macAddressesArray.length; i++) {
                macAddressString = macAddressString + macAddressesArray[i] + "\n";
            }
            serverView.setMacAddresses(macAddressString);
        });
        repository.subscribeRequest(this);
    }

    public void onPause() {
        repository.unsubscribeRequest(this);
    }

    @Override
    public void onRequest(Long value) {
        if (value != null && macAddressesSet != null) {
            serverView.setRequest(value.toString());
            repository.sendResponse(macAddressesSet.contains(value));
        }
    }
}
