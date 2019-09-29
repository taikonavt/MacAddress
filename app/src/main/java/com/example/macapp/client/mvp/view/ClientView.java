package com.example.macapp.client.mvp.view;

public interface ClientView {
    String getMacAddress();

    void setResponse(String macAddressString);

    void setWrongMacAddress();
}
