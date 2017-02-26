package com.example.root.myapplication.connexion;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.example.root.myapplication.DAO.Radio;
import com.example.root.myapplication.DAO.RadioDAO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ConnexionThreadRadio extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.example.root.myapplication.connexion.action.FOO";
    private static final String ACTION_BAZ = "com.example.root.myapplication.connexion.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.root.myapplication.connexion.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.root.myapplication.connexion.extra.PARAM2";
    private RadioDAO radioDAO;

    public ConnexionThreadRadio() {
        super("ConnexionThreadRadio");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        radioDAO = new RadioDAO(this);
        new Thread(new Runnable() {
            public void run() {
                URL url;
                Message myMessage;
                //myMessage = handler.obtainMessage();
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL("http://192.168.1.10:3000/radio");

                    urlConnection = (HttpURLConnection) url.openConnection();
                    if (urlConnection.getResponseMessage().toString().equals("OK")) {
                        Log.i("huhiuhiu", "jijoijio");
                        Log.i("haaaaaiu", urlConnection.getResponseMessage().toString());
                        urlConnection = (HttpURLConnection) url.openConnection();
                    } else {
                        urlConnection.disconnect();
                    }

                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    InputStreamReader isw = new InputStreamReader(in);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"), 8);
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                        // Read line by line
                    }
                    JSONArray jsonArray = new JSONArray(sb.toString());
                    RadioDAO radioDAO = new RadioDAO(getApplicationContext());
                    radioDAO.open();
                    for (int i = 0; i < jsonArray.length() - 1; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.getString("radioStationName");
                        String urlRadio = jsonObject.getString("radioUrl");
                        String channel = String.valueOf(Math.floor(Math.random() * 10));
                        radioDAO.create(new Radio(null, name, urlRadio, channel));
                    }

                    radioDAO.close();
                    Bundle b = new Bundle();
                    b.putString("collection", sb.toString());
                    // envoyer le message au Hanlder
                    //  myMessage.setData(b);
                    //  handler.sendMessage(myMessage);
                    //Object obj = parser.parse(;
                } catch (Exception e) {
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                        RadioDAO radioDAO = new RadioDAO(getApplicationContext());
                        try {
                            radioDAO.open();
                            final Radio r = radioDAO.retrieve(Long.valueOf(1));
                            Log.i("ouifranchement", r.getUrl());
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    MediaPlayer at = new MediaPlayer();
                                    at.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                    try {
                                        at.setDataSource(String.valueOf(r.getUrl()));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        at.prepare();
                                        at.start();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
        return START_NOT_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
