package com.example.root.myapplication.connexion;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.example.root.myapplication.MainActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.jar.Manifest;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import static java.security.AccessController.getContext;

/**
 * Created by cyrilstern1 on 23/02/2017.
 */

public class UrlConnection extends HttpURLConnection implements ConnctionInterface {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 225;

    /**
     * Creates an <code>HttpsURLConnection</code> using the
     * URL specified.
     *
     * @param url the URL
     */
    public UrlConnection(URL url, Context c, Activity a) {
        super(url);
//        if (ContextCompat.checkSelfPermission(c, android.Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED) {
//            WifiManager wifi;
//            wifi = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
//            wifi.setWifiEnabled(true);
//        } else {
//            ActivityCompat.requestPermissions(a,
//                    new String[]{android.Manifest.permission.ACCESS_WIFI_STATE},
//                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
//        }


    }


    @Override
    public void disconnect() {

    }

    @Override
    public boolean usingProxy() {
        return false;
    }

    @Override
    public void connect() throws IOException {

    }
}
