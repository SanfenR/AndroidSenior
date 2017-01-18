package com.mz.sanfen.messagemechanism;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.threadlocal).setOnClickListener(this);

        mBooleanThreadLocal.set(true);
        Log.e(TAG, "onCreate: Thread#main " + mBooleanThreadLocal.get());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.threadlocal:
                startThread();
                break;
        }
    }

    private ThreadLocal<Boolean> mBooleanThreadLocal = new ThreadLocal<>();

    private void startThread(){
        new Thread("Thread#1"){
            @Override
            public void run() {
                mBooleanThreadLocal.set(false);
                Log.e(TAG, "run: Thread#1 " + mBooleanThreadLocal.get());
            }
        }.start();


        new Thread("Thread#2"){
            @Override
            public void run() {
                Log.e(TAG, "run: Thread#2 " + mBooleanThreadLocal.get());
            }
        }.start();
    }


}
