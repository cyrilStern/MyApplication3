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

    public static int[] getPath(int integer, Context context) {
        int drawableResourceIdtop = context.getResources().getIdentifier("top" + integer, "drawable", context.getPackageName());
        int drawableResourceIdbottom = context.getResources().getIdentifier("bottom" + integer, "drawable", context.getPackageName());
        int[] draw = {drawableResourceIdtop, drawableResourceIdbottom};
        return draw;
    }
}
