package com.example.macclient.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.macclient.R;
import com.example.macclient.mvp.presenter.MainPresenter;
import com.example.macclient.mvp.view.MainView;

public class MainFragment extends Fragment implements MainView {

    MainPresenter presenter;

    EditText macInputEt1;
    EditText macInputEt2;
    EditText macInputEt3;
    EditText macInputEt4;
    EditText macInputEt5;
    EditText macInputEt6;
    Button requestBtn;
    TextView responseTv;

    private static final String KEY_RESPONSE = "response";

    public MainFragment(){
        presenter = new MainPresenter(this);
    }

    public static MainFragment getInstance(){
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        setRetainInstance(true);

        setViews(view);
        setOnEtChangeListeners();
        setOnRequestBtnClickListener();
        startInput();

        return view;
    }

    private void startInput() {
        macInputEt1.requestFocus();
        if (getActivity() != null) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }

    private void setViews(View view) {
        macInputEt1 = view.findViewById(R.id.activity_main_mac_input_et1);
        macInputEt2 = view.findViewById(R.id.activity_main_mac_input_et2);
        macInputEt3 = view.findViewById(R.id.activity_main_mac_input_et3);
        macInputEt4 = view.findViewById(R.id.activity_main_mac_input_et4);
        macInputEt5 = view.findViewById(R.id.activity_main_mac_input_et5);
        macInputEt6 = view.findViewById(R.id.activity_main_mac_input_et6);
        requestBtn = view.findViewById(R.id.activity_main_send_btn);
        responseTv = view.findViewById(R.id.activity_main_response_tv);
        if (getArguments() != null){
            String string = getArguments().getString(KEY_RESPONSE);
            responseTv.setText(string);
        }
    }

    private void setOnRequestBtnClickListener() {
        requestBtn.setOnClickListener(view -> presenter.onRequestBtnClick());
    }

    private void setOnEtChangeListeners(){
        macInputEt1.addTextChangedListener(new MyTextWatcher() {
            @Override
            void checkLength(Editable editable) {
                if (editable.length() == 2){
                    macInputEt2.requestFocus();
                }
            }
        });

        macInputEt2.addTextChangedListener(new MyTextWatcher() {
            @Override
            void checkLength(Editable editable) {
                if (editable.length() == 2){
                    macInputEt3.requestFocus();
                }
            }
        });

        macInputEt3.addTextChangedListener(new MyTextWatcher() {
            @Override
            void checkLength(Editable editable) {
                if (editable.length() == 2){
                    macInputEt4.requestFocus();
                }
            }
        });

        macInputEt4.addTextChangedListener(new MyTextWatcher() {
            @Override
            void checkLength(Editable editable) {
                if (editable.length() == 2){
                    macInputEt5.requestFocus();
                }
            }
        });

        macInputEt5.addTextChangedListener(new MyTextWatcher() {
            @Override
            void checkLength(Editable editable) {
                if (editable.length() == 2){
                    macInputEt6.requestFocus();
                }
            }
        });
    }

    @Override
    public String getMacAddress() {
        return macInputEt1.getText().toString() +
                macInputEt2.getText() +
                macInputEt3.getText() +
                macInputEt4.getText() +
                macInputEt5.getText() +
                macInputEt6.getText();
    }

    @Override
    public void setResponse(String macAddressString) {
        if (getArguments() != null) {
            getArguments().putString(KEY_RESPONSE, macAddressString);
        }
        responseTv.setText(macAddressString);
    }

    @Override
    public void setWrongMacAddress() {
        String string = getString(R.string.wrong_mac_address);
        if (getArguments() != null) {
            getArguments().putString(KEY_RESPONSE, string);
        }
        responseTv.setText(string);
    }


    private abstract class MyTextWatcher implements TextWatcher {

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

        abstract void checkLength(Editable editable);
    }
}
