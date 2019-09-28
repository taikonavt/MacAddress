package com.example.macapp.mvp.view;

import java.util.Set;

public interface ServerView {
    void setMacAddresses(Set<Long> macAddresses);

    void setRequest(String string);
}
