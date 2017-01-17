package com.mz.sanfen.ipc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mz.sanfen.ipc.messenger.MessengerActivity;
import com.mz.sanfen.ipc.provider.ProviderActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.AIDL).setOnClickListener(this);
        findViewById(R.id.Messenger).setOnClickListener(this);
        findViewById(R.id.Provider).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.AIDL:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.Messenger:
                Intent intent1 = new Intent(this, MessengerActivity.class);
                startActivity(intent1);
                break;
            case R.id.Provider:
                Intent intent2 = new Intent(this, ProviderActivity.class);
                startActivity(intent2);
                break;
            default:
        }
    }
}
