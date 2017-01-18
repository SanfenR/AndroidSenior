package com.mz.sanfen.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mz.sanfen.view.custom.TextButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextButton btn = new TextButton(this);

    }
}
