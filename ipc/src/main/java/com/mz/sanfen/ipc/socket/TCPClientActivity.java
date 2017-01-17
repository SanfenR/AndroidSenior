package com.mz.sanfen.ipc.socket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mz.sanfen.ipc.R;

import java.net.Socket;

public class TCPClientActivity extends AppCompatActivity implements View.OnClickListener {

    Socket socket = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcpclient);

        findViewById(R.id.Socket).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

    }
}
