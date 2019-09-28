package com.example.macapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.macapp.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    Button startServerBtn;
    Button startClientBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        findViews();
        setListeners();
    }

    private void findViews() {
        startServerBtn = (Button) findViewById(R.id.activity_start_server_btn);
        startClientBtn = (Button) findViewById(R.id.activity_start_client_btn);
    }

    private void setListeners() {
        startServerBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, ServerActivity.class);
            startActivity(intent);
            finish();
        });
        startClientBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, ClientActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
