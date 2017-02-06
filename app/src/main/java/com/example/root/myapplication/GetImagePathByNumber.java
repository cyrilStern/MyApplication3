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

    public static int getPath(int integer, String position, Context context) {
        int drawableResourceId;
        if (position.matches("top")) {
            drawableResourceId = context.getResources().getIdentifier("top" + integer, "drawable", context.getPackageName());
        } else {
            drawableResourceId = context.getResources().getIdentifier("botton" + integer, "drawable", context.getPackageName());
        }
        return drawableResourceId;
    }
}
