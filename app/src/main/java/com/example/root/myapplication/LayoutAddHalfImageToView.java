package com.example.root.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by cyrilstern1 on 11/01/2017.
 */

public class LayoutAddHalfImageToView  {
    private Context context;
    private int ressourcePath;
    private ImageView iV;

    public LayoutAddHalfImageToView(Context context) {
        ImageView iV = new ImageView(context);
    }
    public void setThePath(int PathRessource,Boolean upDown){
        this.ressourcePath = PathRessource;
        iV.setImageBitmap(createthehalfBimat(upDown));

    }

    public Bitmap createthehalfBimat(Boolean upDown){
        Bitmap bitmapResult = null;
        Bitmap bitmapmodele;
        if (ressourcePath != 0){
            bitmapmodele = BitmapFactory.decodeResource(context.getResources(),ressourcePath);
            if(upDown){
                bitmapResult = Bitmap.createBitmap(bitmapmodele,0,0, bitmapmodele.getWidth(),bitmapmodele.getHeight()/2);

            }else {
                bitmapResult = Bitmap.createBitmap(bitmapmodele,0,bitmapmodele.getHeight()/2,bitmapmodele.getWidth(),bitmapmodele.getHeight()/2);
            }
        }

        return bitmapResult;
    }
    public ImageView returnViewLaouy(){
        return iV;
    }


}
