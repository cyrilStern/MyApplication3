package com.example.root.myapplication;

import android.content.Context;
import android.support.v4.content.ContextCompat;

/**
 * Created by cyrilstern1 on 11/01/2017.
 */

public abstract class GetImagePathByNumber {
    Context c;
    public  GetImagePathByNumber(Context context) {
        this.c = context;
    }
    public  static int getPath(int integer,Context context){
        int drawableResourceId = context.getResources().getIdentifier("board_letters"+integer, "drawable", context.getPackageName());
        return drawableResourceId;
    }
}
