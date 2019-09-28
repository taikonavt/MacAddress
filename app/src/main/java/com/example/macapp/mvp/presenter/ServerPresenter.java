package com.example.macapp.mvp.presenter;

import com.example.macapp.mvp.model.ServerRepository;
import com.example.macapp.mvp.view.ServerView;

import java.util.Set;

public class ServerPresenter implements ServerObserver{

    private ServerView serverView;
    private ServerRepository repository;

    Set macSet;

    public ServerPresenter(ServerView serverView){
        this.serverView = serverView;
        this.repository = new ServerRepository();
    }

    public void onResume() {
        macSet = repository.getMacAddresses();
        serverView.setMacAddresses(macSet);
        repository.subscribeRequest(this);
    }

    public void onPause() {
        repository.unsubscribeRequest(this);
    }

    @Override
    public void request(Long value) {
        if (value != null) {
            serverView.setRequest(value.toString());
            repository.sendResponse(macSet.contains(value));
        }
    }
}
