package com.example.macapp.mvp.presenter;


import com.example.macapp.mvp.model.Repository;
import com.example.macapp.mvp.view.MainView;

public class MainPresenter implements ResponseObserver{
    private MainView mainView;
    private Repository repository;

    private final static int MAC_ADDRESS_HEX_LENGTH = 12;
    private final static int HEX_BIT = 16;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        repository = new Repository();
    }

    public void onRequestBtnClick() {
        String macAddressString = mainView.getMacAddress();
        if (isMacAddressCorrect(macAddressString)){
            // TODO: 28.09.2019 проверить правильно ли я понимаю как работает метод
            Long macAddressLong = Long.parseLong(macAddressString, HEX_BIT);
            repository.sendMac(macAddressLong);
        } else {
            mainView.setWrongMacAddress();
        }
    }

    private boolean isMacAddressCorrect(String macAddressString) {
        return macAddressString.length() == MAC_ADDRESS_HEX_LENGTH;
    }

    @Override
    public void update(Long value) {
        mainView.setResponse(value.toString());
    }

    public void onResume() {
        repository.subscribeResponse(this);
    }

    public void onPause() {
        repository.unsubscribeResponse(this);
    }
}
