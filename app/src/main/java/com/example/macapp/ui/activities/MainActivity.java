package com.example.macapp.ui.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.macapp.R;
import com.example.macapp.ui.fragments.MainFragment;

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
