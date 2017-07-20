package com.example.root.myapplication.connexion;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.root.myapplication.DAO.GenearateCounter;
import com.example.root.myapplication.DAO.Radio;
import com.example.root.myapplication.DAO.RadioDAO;
import com.example.root.myapplication.myapplication.connexion.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ConnectionWebserviceApi extends IntentService implements com.example.root.myapplication.myapplication.connexion.ConnctionInterface {
    protected ArrayList<Radio> radioListes;
    protected GenearateCounter gC;
    private int counter = 1;
    private RadioDAO radioDAO;
    public ConnectionWebserviceApi() {
        super("ConnectionWebserviceApi");
        this.radioListes = new ArrayList<>();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    private String readStream(InputStream in, Boolean activJsonDecode) throws IOException {
        InputStreamReader isw = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        return (activJsonDecode) ? jsonDecode(sb.toString()) : sb.toString();
    }

    private String jsonDecode(String string) {
        String result = "";
        try {
            JSONObject jsonObject = new JSONObject(string);
            result = jsonObject.getString("value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        radioDAO = new RadioDAO(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(ConnctionInterface.URL_GETTOKENAPI).openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("password", ConnctionInterface.PASSWORD_API);
                    httpURLConnection.setRequestProperty("Accept", "application/json");
                    httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter("login", ConnctionInterface.LOGIN_API)
                            .appendQueryParameter("password", ConnctionInterface.PASSWORD_API);
                    String query = builder.build().getEncodedQuery();

                    OutputStream os = httpURLConnection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(query);
                    writer.flush();
                    writer.close();
                    os.close();
                    InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
                    String token = readStream(in, true);
                    httpURLConnection = (HttpURLConnection) new URL(ConnctionInterface.URL_API).openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("X-Auth-Token", token);
                    httpURLConnection.setRequestProperty("Accept", "application/json");
                    os = httpURLConnection.getOutputStream();
                    os.close();
                    in = new BufferedInputStream(httpURLConnection.getInputStream());
                    String liste = readStream(in, false);
                    Log.i("showmessage", "run: radiodao " + counter);
                    counter++;
                    radioDAO.open();
                    try {
                        JSONArray array = new JSONArray(liste);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject radio = array.getJSONObject(i);
                            radioDAO.create(new Radio(radio.getString("id"), radio.getString("name"), radio.getString("path"), String.valueOf(GenearateCounter.counter())));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        radioDAO.close();
                    }
                    Log.i(TAG, "run: " + liste);
                    httpURLConnection.disconnect();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        return START_NOT_STICKY;
    }
}
