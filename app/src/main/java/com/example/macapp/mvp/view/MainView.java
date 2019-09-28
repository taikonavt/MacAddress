package com.example.macapp.mvp.view;

public interface MainView {
    String getMacAddress();

    void setResponse(String macAddressString);

    void setWrongMacAddress();
}
