package com.example.root.myapplication.myapplication.audio;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.root.myapplication.MainActivity;
import com.example.root.myapplication.R;

import java.util.zip.Inflater;

/**
 * Created by cyrilstern1 on 05/03/2017.
 */

public class ImageShowRadio extends ImageView implements Runnable {
    private static ImageShowRadio INSTANCE = null;
    private String imagePAth = "iconradio";
    private FrameLayout nameRelativeLayout;
    private Context cont;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ImageShowRadio(Context context, FrameLayout fl) {
        super(context);
        this.cont = context;
        this.nameRelativeLayout = fl;
        nameRelativeLayout.addView(this);
        this.setImageResource(getResources().getIdentifier(imagePAth, "drawable", cont.getPackageName()));

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static ImageShowRadio getInstance(Context context, FrameLayout fl) {

        if (INSTANCE == null) {
            INSTANCE = new ImageShowRadio(context, fl);
        }
        return INSTANCE;
    }

    public void changeRadioLogo(String imagePAth) {
        this.imagePAth = imagePAth;
        this.run();

    }

    @Override
    public void setImageResource(@DrawableRes int resId) {
        super.setImageResource(resId);
    }

    @Override
    public void run() {
        if (INSTANCE instanceof ImageShowRadio) {
            this.setImageResource(android.R.color.transparent);
        }
        Log.i("runimage", "run: ");
        this.setImageResource(android.R.color.holo_green_light);

        Animation fadeInAnimation = AnimationUtils.loadAnimation(this.getContext(), R.anim.fadein);
        AnimationSet animation = new AnimationSet(false); //change to false
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fadeout);
        animation.addAnimation(fadeInAnimation);
        //animation.addAnimation(fadeOutAnimation);
        this.setAnimation(animation);
    }
}
