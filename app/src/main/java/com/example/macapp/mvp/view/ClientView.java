package com.example.macapp.mvp.view;

public interface ClientView {
    String getMacAddress();

    void setResponse(String macAddressString);

    void setWrongMacAddress();
}
