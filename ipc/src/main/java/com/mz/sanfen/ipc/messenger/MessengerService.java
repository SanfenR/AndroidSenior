package com.mz.sanfen.ipc.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessengerService extends Service {

    private static final String TAG = "MessengerService";

    private static class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Contants.CLIENT:
                    Bundle data = msg.getData();
                    String msg1 = data.getString("msg");
                    Log.e(TAG, "handleMessage: " + msg1 );
                    Messenger client = msg.replyTo;
                    Message ret = Message.obtain(null, Contants.SERVICE);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply", "你的消息我接受到了！");
                    ret.setData(bundle);
                    try {
                        client.send(ret);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    }

    Messenger mMessenger = new Messenger(new MessengerHandler());

    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
