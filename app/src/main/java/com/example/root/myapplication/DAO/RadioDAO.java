package com.example.root.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.root.myapplication.R.id.radio;

/**
 * Created by cyrilstern1 on 25/02/2017.
 */

public class RadioDAO implements IDAO<Radio, Long>, DAOConstant {
    public final String[] ALL = new String[]{_ID,
            NOM_COLONNE_CHANNEL,
            NOM_COLONNE_NOM,
            NOM_COLONNE_URL,};

    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    private Context context;

    public RadioDAO(Context context) {
        this.context = context;
        this.dbHelper = new RadioHelper(context);
    }

    @Override
    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();

    }

    @Override
    public void close() {
        Log.i("message", "sqlclose");
        dbHelper.close();
    }

    @Override
    public void create(Radio radio) throws Exception {
        ContentValues values = new ContentValues();
        values.put(NOM_COLONNE_CHANNEL, radio.getChannel());
        values.put(NOM_COLONNE_NOM, radio.getName());
        values.put(NOM_COLONNE_URL, radio.getUrl());
        db.insert(TABLE_RADIO, null, values);
    }

    @Override
    public Radio retrieve(Long aLong) throws Exception {
        Cursor cursor = db.query(TABLE_RADIO, ALL, _ID + " = " + aLong, null, null, null, null);
        cursor.moveToFirst();
        String Name = cursor.getString(cursor.getColumnIndex(NOM_COLONNE_NOM));
        String Channel = cursor.getString(cursor.getColumnIndex(NOM_COLONNE_CHANNEL));
        String Url = cursor.getString(cursor.getColumnIndex(NOM_COLONNE_URL));
        cursor.close();
        return new Radio(null, Name, Url, Channel);
    }

    @Override
    public List<Radio> findAll() throws Exception {
        List<Radio> radioList = new ArrayList<Radio>();
        Cursor cursor = db.query(TABLE_RADIO, ALL, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(cursor.getColumnIndex(NOM_COLONNE_NOM));
            String url = cursor.getString(cursor.getColumnIndex(NOM_COLONNE_URL));
            String channel = cursor.getString(cursor.getColumnIndex(NOM_COLONNE_CHANNEL));
            Radio p = new Radio(null, name, url, channel);
            radioList.add(p);
            cursor.moveToNext();
        }
        cursor.close();
        return radioList;
    }

    @Override
    public void update(Radio radio) throws Exception {
        // db.delete(TABLE_RADIO, _ID + " = " + aLong,null);

    }

    @Override
    public void delete(Long aLong) throws Exception {
        this.dbHelper.onUpgrade(db, 1, 2);
    }
}
