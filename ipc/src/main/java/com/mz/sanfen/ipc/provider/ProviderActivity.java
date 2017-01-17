package com.mz.sanfen.ipc.provider;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mz.sanfen.ipc.R;

public class ProviderActivity extends AppCompatActivity {
    private static final String TAG = "ProviderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        Log.e(TAG, "onCreate: main");

        findViewById(R.id.Provider).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.mz.sanfen.ipc.provider.BookProvider/book");
                getContentResolver().query(uri, null, null, null, null);
            }
        });


    }
}
