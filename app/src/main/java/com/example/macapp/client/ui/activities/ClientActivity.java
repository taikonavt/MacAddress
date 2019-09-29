package com.example.macapp.client.ui.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.macapp.R;
import com.example.macapp.client.ui.fragments.ClientFragment;

public class ClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main_container, ClientFragment.getInstance())
                    .commit();
        }
    }

}
