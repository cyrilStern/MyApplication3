package com.example.root.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.BoolRes;
import android.support.annotation.Nullable;

/**
 * Created by cyrilstern1 on 11/01/2017.
 */

public class ThreadServiceTimer extends Service {
    private static Thread ticker;
    private Message msg;
    private Messenger messager;
    private Bundle extras;

    public ThreadServiceTimer() {
        ticker = new Thread(new Ticker());
    }
    public static void stopticker(){
        ticker.interrupt();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        extras = intent.getExtras();
        messager = (Messenger) extras.get("messager");
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

    private class Ticker implements Runnable {
        public void run() {
            while (!ticker.isInterrupted()) {
                try {
                    Thread.sleep(1000);
                    Bundle b = new Bundle();
                    b.putBoolean("add",true);
                    msg.setData(b);
                    messager.send(msg);
                } catch (Exception e) {
                    return;
                }
            }

        }
    }
}