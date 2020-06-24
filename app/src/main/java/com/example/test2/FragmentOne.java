package com.example.test2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class FragmentOne extends Fragment {

    private static final String TAG = "FragmentOne";
    TextChangeListener listener;
    EditText editText;
    TextView textView, textView2;
    Button button;


    public void setTextChangeOne(TextChangeListener listn1) {
        listener = listn1;
    }

    public void setTextOne(String param) {
        textView.setText(param);

    }

    private void sendBroadCast(String text){

        Intent intent = new Intent("custom-eventone-name");
        intent.putExtra("message",text);
        LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent);

    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getStringExtra("message");
            Log.d("receiver", "Got message: " + message);

            setTextOne(message);
        }
    };



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragone, container, false);
        Log.d(TAG, "onCreateView: ");
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-eventTwo-name"));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.edittxt1);
        textView = view.findViewById(R.id.txt1);
        textView2 = view.findViewById(R.id.txt2);
        button = view.findViewById(R.id.btn);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               // listener.onTextchangeOne(s.toString());
                sendBroadCast(s.toString());


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");

                textView2.setText(textView.getText());
            }
        });


    }

    @Override
    public void onDestroy() {
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }
}
