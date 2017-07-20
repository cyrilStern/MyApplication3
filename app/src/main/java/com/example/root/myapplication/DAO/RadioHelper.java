package com.example.root.myapplication.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cyrilstern1 on 25/02/2017.
 */

public class RadioHelper extends SQLiteOpenHelper implements DAOConstant {
    private static final String CREATION_BD = "CREATE TABLE " + TABLE_RADIO + " (" +
            _ID + " integer primary key autoincrement, " +
            NOM_COLONNE_NOM + " text not null , " +
            NOM_COLONNE_URL + " text not null , " +
            NOM_COLONNE_CHANNEL + " text not null);";
    private static final String DELETE_DB = "DROP TABLE IF EXISTS " + TABLE_RADIO;


    public RadioHelper(Context context) {
        super(context, DAOConstant.DATABASE_NAME, null, DAOConstant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DELETE_DB);
        db.execSQL(CREATION_BD);
        //db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_DB);
        onCreate(db);
        //db.close();

    }
}
