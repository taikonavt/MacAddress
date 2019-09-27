package com.example.macclient.view;

public interface MainView {
    String getMacAddress();

    void setResponse(String macAddressString);

    void setWrongMacAddress();
}
