package com.example.root.myapplication;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cyrilstern1 on 13/01/2017.
 */

public class SplitBitmatInTwo {
    private Bitmap bitmapReception;
    private List<Bitmap> listOfBitmaps;
    public SplitBitmatInTwo() {

    }
    public void setbitmapReception(Bitmap bitmap){

        this.bitmapReception = bitmap;
    }
    public void  getTwoPartOfImage(){
        listOfBitmaps = new ArrayList<Bitmap>();
        Bitmap bitmap0 = Bitmap.createBitmap(this.bitmapReception,0,0,this.bitmapReception.getWidth(),bitmapReception.getHeight()/2);
        Bitmap bitmap1 = Bitmap.createBitmap(this.bitmapReception,0,this.bitmapReception.getHeight()/2,this.bitmapReception.getWidth(),bitmapReception.getHeight()/2);
        listOfBitmaps.add(bitmap0);
        listOfBitmaps.add(bitmap1);

    }



    public Bitmap returnBitmatnumber(int i){
        getTwoPartOfImage();
        Bitmap returnBitmaps = this.listOfBitmaps.get(i);

        return returnBitmaps;
    }
}
