package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    FragmentOne fragmentOne = new FragmentOne();
    FragmentTwo fragmentTwo = new FragmentTwo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextChangeListener textChangeListener = new TextChangeListener() {
            @Override
            public void onTextchangeOne(String text) {
                fragmentTwo.setTextTwo(text);
            }

        };

        TextChangeListener textChangeListener1 = new TextChangeListener() {
            @Override
            public void onTextchangeOne(String text) {
                fragmentOne.setTextOne(text);
            }

        };




        fragmentOne.setTextChangeOne(textChangeListener);
        fragmentTwo.setTextChangeTwo(textChangeListener1);



        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.firstframe, fragmentOne);
        transaction.replace(R.id.secondframe, fragmentTwo);
        transaction.commit();

    }
}