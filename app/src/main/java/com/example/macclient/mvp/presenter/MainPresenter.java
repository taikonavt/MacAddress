package com.example.macclient.mvp.presenter;


import com.example.macclient.mvp.view.MainView;

public class MainPresenter {
    private MainView mainView;

    private final static int MAC_ADDRESS_HEX_LENGTH = 12;
    private final static int MAC_ADDRESS_BYTES = 6;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public void onRequestBtnClick() {
        String macAddressString = mainView.getMacAddress();
        if (isMacAddressCorrect(macAddressString)){
            Long macAddressLong = Long.parseLong(macAddressString, 16);



        } else {
            mainView.setWrongMacAddress();
        }
    }

    private boolean isMacAddressCorrect(String macAddressString) {
        return macAddressString.length() == MAC_ADDRESS_HEX_LENGTH;
    }
}
