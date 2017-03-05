package com.example.root.myapplication.myapplication.audio;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
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

public class ImageShowRadio extends ImageView {
    private String imagePAth;
    private String nameRelativeLayout;
    private FrameLayout rl;
    private ImageShowRadio cont;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ImageShowRadio(Context context, FrameLayout nameRelativeLaoyout, String imagePath) {
        super(context);
        this.imagePAth = imagePath;
        this.rl = nameRelativeLaoyout;
        this.cont = this;
        Log.i("radio", imagePath);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void init(Context context) {
        this.setBackground(context.getResources().getDrawable(R.drawable.rmclogo));//context.getResources().getIdentifier(imagePAth,"drawable",context.getApplicationContext().getPackageName())));
        rl.addView(this);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.fadein);
        AnimationSet animation = new AnimationSet(false); //change to false
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fadeout);
        animation.addAnimation(fadeInAnimation);
        animation.addAnimation(fadeOutAnimation);
        this.setAnimation(animation);
        Handler mHandler = new Handler();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                rl.removeView(cont);
            }
        });

    }

}
