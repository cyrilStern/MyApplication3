package com.example.root.myapplication.connexion;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.root.myapplication.connexion.ConnctionInterface.URLPATH;

/**
 * Created by cyrilstern1 on 25/02/2017.
 */

public class ConnexionThread extends IntentService {


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ConnexionThread(String name) {
        super(name);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("vtyvbuniuniunnuin", "fe;fre;fmler;fmlerf;");
        return START_NOT_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
