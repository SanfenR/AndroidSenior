package com.mz.sanfen.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BookManager mBookManager = null;

    private boolean mBound = false;

    private List<Book> mBooks;

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook(v);
            }
        });
    }


    public void addBook(View view) {
        if (!mBound) {
            attemptToBindService();
            Toast.makeText(this, "当前与服务器处于未连接状态，正在尝试重新连接", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mBookManager == null) return;

        Book book = new Book();
        book.setName("App研发");
        book.setPrice("30");

        try {
            mBookManager.addBook(book);
            Log.e(TAG, "addBook: " + book.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    private void attemptToBindService() {
        Intent intent = new Intent();
        intent.setAction("com.mz.sanfen.ipc");
        intent.setPackage("com.mz.sanfen.ipc");
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBookManager = BookManager.Stub.asInterface(service);
            mBound = true;

            if (mBookManager != null) {
                try {
                    mBooks = mBookManager.getBooks();
                    Log.e(TAG, "onServiceConnected: " + mBooks.toString() );
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected" );
            mBound = false;
        }
    };


    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
    }
}
