package com.example.macapp.ui.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.example.macapp.R;
import com.example.macapp.mvp.presenter.ServerPresenter;
import com.example.macapp.mvp.view.ServerView;

import java.util.Set;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ServerActivity extends AppCompatActivity
    implements ServerView {

    ServerPresenter presenter;

    TextView macListTv;
    TextView requestTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        findViews();
        presenter = new ServerPresenter(this);
    }

    private void findViews() {
        macListTv = (TextView) findViewById(R.id.activity_server_mac_list_tv);
        requestTv = (TextView) findViewById(R.id.activity_server_request_tv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void setMacAddresses(Set<Long> macAddresses) {
        macListTv.setText(macAddresses.toString());
    }

    @Override
    public void setRequest(String string) {
        requestTv.setText(string);
    }
}
