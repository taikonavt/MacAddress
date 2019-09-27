package com.example.macclient.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.macclient.R;
import com.example.macclient.ui.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main_container, MainFragment.getInstance())
                    .commit();
        }
    }

}
