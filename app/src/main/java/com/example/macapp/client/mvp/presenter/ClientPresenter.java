package com.example.macapp.client.mvp.presenter;


import com.example.macapp.client.mvp.model.ClientRepository;
import com.example.macapp.client.mvp.view.ClientView;

public class ClientPresenter implements ClientObserver {
    private ClientView clientView;
    private ClientRepository clientRepository;

    private final static int MAC_ADDRESS_HEX_LENGTH = 12;
    private final static int HEX_RADIX = 16;

    public ClientPresenter(ClientView clientView) {
        this.clientView = clientView;
        clientRepository = new ClientRepository();
    }

    public void onRequestBtnClick() {
        String macAddressString = clientView.getMacAddress();
        if (isMacAddressCorrect(macAddressString)){
            Long macAddressLong = Long.parseLong(macAddressString, HEX_RADIX);
            clientRepository.sendMac(macAddressLong);
        } else {
            clientView.setWrongMacAddress();
        }
    }

    private boolean isMacAddressCorrect(String macAddressString) {
        return macAddressString.length() == MAC_ADDRESS_HEX_LENGTH;
    }

    @Override
    public void update(Boolean value) {
        if (value != null) {
            clientView.setResponse(value.toString());
        }
    }

    public void onResume() {
        clientRepository.subscribeResponse(this);
    }

    public void onPause() {
        clientRepository.unsubscribeResponse(this);
    }
}
