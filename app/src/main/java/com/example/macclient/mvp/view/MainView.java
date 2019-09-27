package com.example.macclient.mvp.view;

public interface MainView {
    String getMacAddress();

    void setResponse(String macAddressString);

    void setWrongMacAddress();
}
