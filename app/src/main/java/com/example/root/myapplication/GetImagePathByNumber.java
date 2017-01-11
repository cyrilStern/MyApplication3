package com.example.root.myapplication;

import android.content.Context;
import android.support.v4.content.ContextCompat;

/**
 * Created by cyrilstern1 on 11/01/2017.
 */

public abstract class GetImagePathByNumber {
    Context c;
    public GetImagePathByNumber(Context context) {
        this.c = context;
    }
    public   int getPath(int integer){
        int drawableResourceId = c.getResources().getIdentifier("board_letters"+integer, "drawable", c.getPackageName());
        return drawableResourceId;
    }
}
