package com.example.macclient.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.macclient.R;
import com.example.macclient.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity
    implements MainView{

    MainPresenter presenter;

    EditText macInputEt1;
    EditText macInputEt2;
    EditText macInputEt3;
    EditText macInputEt4;
    EditText macInputEt5;
    EditText macInputEt6;
    Button requestBtn;
    TextView responseTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
        findViews();
        setOnRequestBtnClickListener();
        setOnEtChangeListeners();
        startInput();
    }

    private void startInput() {
        macInputEt1.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    private void findViews() {
        macInputEt1 = findViewById(R.id.activity_main_mac_input_et1);
        macInputEt2 = findViewById(R.id.activity_main_mac_input_et2);
        macInputEt3 = findViewById(R.id.activity_main_mac_input_et3);
        macInputEt4 = findViewById(R.id.activity_main_mac_input_et4);
        macInputEt5 = findViewById(R.id.activity_main_mac_input_et5);
        macInputEt6 = findViewById(R.id.activity_main_mac_input_et6);
        requestBtn = findViewById(R.id.activity_main_send_btn);
        responseTv = findViewById(R.id.activity_main_response_tv);
    }

    private void setOnRequestBtnClickListener() {
        requestBtn.setOnClickListener(view -> presenter.onRequestBtnClick());
    }

    private void setOnEtChangeListeners(){
        macInputEt1.addTextChangedListener(new MyTextWatcher() {
            @Override
            void checkLength(Editable s) {
                if (s.length() == 2){
                    macInputEt2.requestFocus();
                }
            }
        });

        macInputEt2.addTextChangedListener(new MyTextWatcher() {
            @Override
            void checkLength(Editable s) {
                if (s.length() == 2){
                    macInputEt3.requestFocus();
                }
            }
        });

        macInputEt3.addTextChangedListener(new MyTextWatcher() {
            @Override
            void checkLength(Editable s) {
                if (s.length() == 2){
                    macInputEt4.requestFocus();
                }
            }
        });

        macInputEt4.addTextChangedListener(new MyTextWatcher() {
            @Override
            void checkLength(Editable s) {
                if (s.length() == 2){
                    macInputEt5.requestFocus();
                }
            }
        });

        macInputEt5.addTextChangedListener(new MyTextWatcher() {
            @Override
            void checkLength(Editable s) {
                if (s.length() == 2){
                    macInputEt6.requestFocus();
                }
            }
        });
    }


    private abstract class MyTextWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            checkLength(s);
        }

        abstract void checkLength(Editable s);
    }
}
