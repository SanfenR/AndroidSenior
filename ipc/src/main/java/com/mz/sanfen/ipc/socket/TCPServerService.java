package com.mz.sanfen.ipc.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServerService extends Service {

    private static final String TAG = "TCPServerService";

    private boolean mIsServiceDestoryed = false;

    public TCPServerService() {
    }

    private String[] mDefinedMessages = new String[]{
            "你好",
            "你叫什么名字？"
    };


    @Override
    public void onCreate() {
        new Thread(new TcpServer()).start();
        super.onCreate();
    }

    @Override
    public void onDestroy() {

        mIsServiceDestoryed = true;

        super.onDestroy();
    }

    public class TcpServer implements Runnable {

        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8888);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                while (!mIsServiceDestoryed) {
                    final Socket client = serverSocket.accept();
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void responseClient(Socket client) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        out.println("欢迎来到工作室！");
        while(!mIsServiceDestoryed) {
            String str = in.readLine();
            Log.e(TAG, "responseClient: " + str);
            if (str == null) {
                break;
            }

            int i = new Random().nextInt(mDefinedMessages.length);

            String msg = mDefinedMessages[i];

            out.println(msg);
            Log.e(TAG, "responseClient: send" + msg);
        }
        
        in.close();
        out.close();
        client.close();
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
