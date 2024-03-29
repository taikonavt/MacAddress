package com.example.macapp.client.ui.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.macapp.R;
import com.example.macapp.client.mvp.presenter.ClientPresenter;
import com.example.macapp.client.mvp.view.ClientView;

public class ClientFragment extends Fragment implements ClientView {

    private ClientPresenter presenter;

    private EditText macInputEt1;
    private EditText macInputEt2;
    private EditText macInputEt3;
    private EditText macInputEt4;
    private EditText macInputEt5;
    private EditText macInputEt6;
    private Button requestBtn;
    private TextView responseTv;

    private static final String KEY_RESPONSE = "response";

    public ClientFragment(){
        presenter = new ClientPresenter(this);
    }

    public static ClientFragment getInstance(){
        ClientFragment fragment = new ClientFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        setRetainInstance(true);

        if (getActivity() != null && ((AppCompatActivity)getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.client_title);
        }

        setViews(view);
        setOnEtChangeListeners();
        setOnRequestBtnClickListener();
        startInput();

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
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
    public void setResponse(Boolean macAddressIsConnected) {
        if (getArguments() != null) {
            getArguments().putBoolean(KEY_RESPONSE, macAddressIsConnected);
        }
        if (macAddressIsConnected){
            responseTv.setText(getString(R.string.device_connected));
        } else {
            responseTv.setText(getString(R.string.device_disconnected));
        }
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
