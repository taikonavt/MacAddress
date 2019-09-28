package com.example.macapp.mvp.model;

import com.example.macapp.App;

import java.util.HashSet;
import java.util.Set;

public class ServerRepository extends ServerObservable{

    private final static int HEX_BIT = 16;

    private Set<Long> macSet;

    public ServerRepository(){
        getConnectedDevicesMac();
    }

    private void getConnectedDevicesMac() {
        macSet = getMockMacAddresses();
    }

    private Set<Long> getMockMacAddresses() {
        Set<Long> set = new HashSet<>();
        set.add(Long.parseLong("112233445566", HEX_BIT));
        set.add(Long.parseLong("11AA33BB5599", HEX_BIT));
        set.add(Long.parseLong("2322336755BC", HEX_BIT));
        set.add(Long.parseLong("45267348756B", HEX_BIT));
        set.add(Long.parseLong("CC223BC45BB6", HEX_BIT));
        return set;
    }

    public Set<Long> getMacAddresses() {
        return macSet;
    }

    public void sendResponse(boolean contains) {
        App.getInstance().getDatabaseResponseReference().setValue(contains);
    }
}
