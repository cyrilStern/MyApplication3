package com.example.root.myapplication;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.SystemClock;
import android.support.annotation.BoolRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by cyrilstern1 on 11/01/2017.
 */

public class ThreadServiceTimer extends Service {
    private static Thread ticker;
    private Message msg;
    private Messenger messager;
    private Bundle extras;

    public ThreadServiceTimer() {
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        extras = intent.getExtras();
        messager = (Messenger) extras.get("messager");
        msg = Message.obtain();
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        if (ticker == null) {
        ticker = new Thread(new Ticker());
        ticker.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private class Ticker extends Thread {

        public void run() {
            while (!ticker.isInterrupted()) {
                try {
                    SystemClock.sleep(1000);
                    Bundle b = new Bundle();
                    b.putBoolean("add",true);
                    Message msg1 = Message.obtain();
                    msg1.setData(b);
                    messager.send(msg1);
                } catch (Exception e) {
                }
            }

        }
    }
}