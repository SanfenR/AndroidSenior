package com.mz.sanfen.fragement;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_replace).setOnClickListener(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment, OneFragment.newInstance(OneFragment.ARG_PARAM1, OneFragment.ARG_PARAM2));
        fragmentTransaction.commit();

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (v.getId()) {
            case R.id.btn_add:
                fragmentTransaction.add(R.id.fragment, SecondFragment.newInstance(OneFragment.ARG_PARAM1, OneFragment.ARG_PARAM2));

                break;

            case R.id.btn_replace:
                fragmentTransaction.replace(R.id.fragment, SecondFragment.newInstance(OneFragment.ARG_PARAM1, OneFragment.ARG_PARAM2));
                break;
        }

        fragmentTransaction.commit();
    }
}
