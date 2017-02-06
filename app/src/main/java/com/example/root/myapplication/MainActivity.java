package com.example.root.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private final int bigsize = 40;
    private final int littlesize = 0;
    public Bitmap mybitmap,newbmp,bitmap,bmp;
    private Handler handler;
    private FrameLayout fl,fl1,fl2,fl3,fl4,fl5;
    private ImageView iw,iw1,iw2,iw3,iw4,iw5;
    private Boolean start;
    private LinearLayout linearLayout;
    private boolean firstlaunch;
    private ImageView imageview;
    private ActionBar bar;
    private int i, j, h, constM, constSD, constDM, constH, constDH;
    private SplitBitmatInTwo splitBit;
    private FrameLayout frameret;
    private FlipNumber FlipNumberS1, FlipNumberS2, FlipNumberS4, FlipNumberS3, FlipNumberSD1, FlipNumberSD2, FlipNumberSD3, FlipNumberSD4;
    private FlipNumber FlipNumberM1, FlipNumberM2, FlipNumberM3, FlipNumberM4, FlipNumberMD1, FlipNumberMD2, FlipNumberMD3, FlipNumberMD4;
    private FlipNumber FlipNumberH1, FlipNumberH2, FlipNumberH3, FlipNumberH4, FlipNumberHD1, FlipNumberHD2, FlipNumberHD3, FlipNumberHD4;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        fl = (FrameLayout) findViewById(R.id.seconde);
        FlipNumberS1 = new FlipNumber(this, 0);
        FlipNumberS4 = new FlipNumber(this, 0);
        FlipNumberS2 = new FlipNumber(this, 0);
        FlipNumberS3 = new FlipNumber(this, 0);
        fl.addView(FlipNumberS4);
        fl.addView(FlipNumberS3);
        fl.addView(FlipNumberS2);
        fl.addView(FlipNumberS1);

        fl1 = (FrameLayout) findViewById(R.id.diseconde);
        FlipNumberSD1 = new FlipNumber(this, 0);
        FlipNumberSD4 = new FlipNumber(this, 0);
        FlipNumberSD2 = new FlipNumber(this, 0);
        FlipNumberSD3 = new FlipNumber(this, 0);
        fl1.addView(FlipNumberSD1);
        fl1.addView(FlipNumberSD2);
        fl1.addView(FlipNumberSD3);
        fl1.addView(FlipNumberSD4);

        fl2 = (FrameLayout) findViewById(R.id.minute);
        FlipNumberM1 = new FlipNumber(this, 0, bigsize);
        FlipNumberM4 = new FlipNumber(this, 0, bigsize);
        FlipNumberM3 = new FlipNumber(this, 0, bigsize);
        FlipNumberM2 = new FlipNumber(this, 0, bigsize);
        fl2.addView(FlipNumberM1);
        fl2.addView(FlipNumberM2);
        fl2.addView(FlipNumberM3);
        fl2.addView(FlipNumberM4);
        fl3 = (FrameLayout) findViewById(R.id.diminute);
        FlipNumberMD1 = new FlipNumber(this, 0, bigsize);
        FlipNumberMD4 = new FlipNumber(this, 0, bigsize);
        FlipNumberMD3 = new FlipNumber(this, 0, bigsize);
        FlipNumberMD2 = new FlipNumber(this, 0, bigsize);
        fl3.addView(FlipNumberMD1);
        fl3.addView(FlipNumberMD2);
        fl3.addView(FlipNumberMD3);
        fl3.addView(FlipNumberMD4);

        fl4 = (FrameLayout) findViewById(R.id.hour);
        FlipNumberH1 = new FlipNumber(this, 0, bigsize);
        FlipNumberH4 = new FlipNumber(this, 0, bigsize);
        FlipNumberH3 = new FlipNumber(this, 0, bigsize);
        FlipNumberH2 = new FlipNumber(this, 0, bigsize);
        fl4.addView(FlipNumberH1);
        fl4.addView(FlipNumberH2);
        fl4.addView(FlipNumberH3);
        fl4.addView(FlipNumberH4);

        fl5 = (FrameLayout) findViewById(R.id.dihour);
        FlipNumberHD1 = new FlipNumber(this, 0, bigsize);
        FlipNumberHD4 = new FlipNumber(this, 0, bigsize);
        FlipNumberHD3 = new FlipNumber(this, 0, bigsize);
        FlipNumberHD2 = new FlipNumber(this, 0, bigsize);
        fl5.addView(FlipNumberHD1);
        fl5.addView(FlipNumberHD2);
        fl5.addView(FlipNumberHD3);
        fl5.addView(FlipNumberHD4);
        init();


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init() {
        for (int i = 0; i < 5; i++) {
            String second, disecond, minute, diminute, hour, dihour;

            Integer secondint = new Date().getSeconds();
            Integer minuteint = new Date().getMinutes();
            Integer hourint = new Date().getHours();
            second = String.valueOf(secondint.toString().charAt(0));
            minute = String.valueOf(minuteint.toString().charAt(0));
            hour = String.valueOf(hourint.toString().charAt(0));

            if (hourint.toString().length() < 2) {
                dihour = "0";
            } else dihour = String.valueOf(hourint.toString().charAt(1));
            if (minuteint.toString().length() < 2) {
                diminute = "0";
            } else diminute = String.valueOf(minuteint.toString().charAt(1));
            if (secondint.toString().length() < 2) {
                disecond = "0";
            } else disecond = String.valueOf(secondint.toString().charAt(1));


            this.FlipNumberS1.activActionPositonFlip(Integer.valueOf(second));
            if (i > 0) FlipNumberS2.activActionPositonFlip(Integer.valueOf(second));
            if (i > 1) FlipNumberS3.activActionPositonFlip(Integer.valueOf(second));
            if (i > 2) FlipNumberS4.activActionPositonFlip(Integer.valueOf(second));

            this.FlipNumberSD1.activActionPositonFlip(Integer.valueOf(disecond));
            if (i > 0) FlipNumberSD2.activActionPositonFlip(Integer.valueOf(disecond));
            if (i > 1) FlipNumberSD3.activActionPositonFlip(Integer.valueOf(disecond));
            if (i > 2) FlipNumberSD4.activActionPositonFlip(Integer.valueOf(disecond));

            this.FlipNumberM1.activActionPositonFlip(Integer.valueOf(minute));
            if (i > 0) FlipNumberM2.activActionPositonFlip(Integer.valueOf(minute));
            if (i > 1) FlipNumberM3.activActionPositonFlip(Integer.valueOf(minute));
            if (i > 2) FlipNumberM4.activActionPositonFlip(Integer.valueOf(minute));

            this.FlipNumberMD1.activActionPositonFlip(Integer.valueOf(diminute));
            if (i > 0) FlipNumberMD2.activActionPositonFlip(Integer.valueOf(diminute));
            if (i > 1) FlipNumberMD3.activActionPositonFlip(Integer.valueOf(diminute));
            if (i > 2) FlipNumberMD4.activActionPositonFlip(Integer.valueOf(diminute));

            this.FlipNumberH1.activActionPositonFlip(Integer.valueOf(hour));
            if (i > 0) FlipNumberH2.activActionPositonFlip(Integer.valueOf(hour));
            if (i > 1) FlipNumberH3.activActionPositonFlip(Integer.valueOf(hour));
            if (i > 2) FlipNumberH4.activActionPositonFlip(Integer.valueOf(hour));

            this.FlipNumberHD1.activActionPositonFlip(Integer.valueOf(dihour));
            if (i > 0) FlipNumberHD2.activActionPositonFlip(Integer.valueOf(dihour));
            if (i > 1) FlipNumberHD3.activActionPositonFlip(Integer.valueOf(dihour));
            if (i > 2) FlipNumberHD4.activActionPositonFlip(Integer.valueOf(dihour));

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        String second, disecond, minute, diminute, hour, dihour;
        Integer secondint = new Date().getSeconds();
        Integer minuteint = new Date().getMinutes();
        Integer hourint = new Date().getHours();
        second = String.valueOf(secondint.toString().charAt(0));
        minute = String.valueOf(minuteint.toString().charAt(0));
        hour = String.valueOf(hourint.toString().charAt(0));

        if (hourint.toString().length() < 2) {
            dihour = "0";
        } else dihour = String.valueOf(hourint.toString().charAt(1));
        if (minuteint.toString().length() < 2) {
            diminute = "0";
        } else diminute = String.valueOf(minuteint.toString().charAt(1));
        if (secondint.toString().length() < 2) {
            disecond = "0";
        } else disecond = String.valueOf(secondint.toString().charAt(1));

        handler = new Handler(){
            boolean zeroToNine = true;


            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                if (bundle.getBoolean("add") == true) {
                    String second, disecond, minute, diminute, hour, dihour;
                    Integer secondint = new Date().getSeconds();
                    Integer minuteint = new Date().getMinutes();
                    Integer hourint = new Date().getHours();
                    second = String.valueOf(secondint.toString().charAt(0));
                    minute = String.valueOf(minuteint.toString().charAt(0));
                    hour = String.valueOf(hourint.toString().charAt(0));

                    if (hourint.toString().length() < 2) {
                        dihour = "0";
                    } else dihour = String.valueOf(hourint.toString().charAt(1));
                    if (minuteint.toString().length() < 2) {
                        diminute = "0";
                    } else diminute = String.valueOf(minuteint.toString().charAt(1));
                    if (secondint.toString().length() < 2) {
                        disecond = "0";
                    } else disecond = String.valueOf(secondint.toString().charAt(1));
                    if (secondint >= 0 && secondint <= 9) {
                        second = String.valueOf(secondint.toString().charAt(0));
                        disecond = "0";
                        FlipNumberS1.activActionPositonFlip(Integer.valueOf(second));
                        FlipNumberS2.activActionPositonFlip(Integer.valueOf(second));
                        FlipNumberS3.activActionPositonFlip(Integer.valueOf(second));
                        FlipNumberS4.activActionPositonFlip(Integer.valueOf(second));


                    }else{

                        FlipNumberS1.activActionPositonFlip(Integer.valueOf(disecond));
                        FlipNumberS2.activActionPositonFlip(Integer.valueOf(disecond));
                        FlipNumberS3.activActionPositonFlip(Integer.valueOf(disecond));
                        FlipNumberS4.activActionPositonFlip(Integer.valueOf(disecond));


                        if ("" == "1") {
                            FlipNumberSD1.activActionPositonFlip(Integer.valueOf(disecond));
                            if (j > 0)
                                FlipNumberSD2.activActionPositonFlip(Integer.valueOf(disecond));
                            if (j > 1)
                                FlipNumberSD3.activActionPositonFlip(Integer.valueOf(disecond));
                            if (j > 2)
                                FlipNumberSD4.activActionPositonFlip(Integer.valueOf(disecond));
                            //disecondtest = Integer.valueOf(disecond);
                        }

                    }
                    if (secondint == 0 || firstlaunch) {
//                        if(minuteint >=0 && minuteint <=9){
//                            minute = String.valueOf(minuteint.toString().charAt(0));
//                            diminute = "0";
//                            FlipNumberM1.activActionPositonFlip(Integer.valueOf(minute));
//                            if(h>0)FlipNumberM2.activActionPositonFlip(Integer.valueOf(minute));
//                            if(h>1)FlipNumberM3.activActionPositonFlip(Integer.valueOf(minute));
//                            if(h>2)FlipNumberM4.activActionPositonFlip(Integer.valueOf(minute));
//                            if(h<3)h++;
//
//
//                        }else{
//                            minute = String.valueOf(minuteint.toString().charAt(1));
//                            diminute = String.valueOf(minuteint.toString().charAt(0));
//                            FlipNumberM1.activActionPositonFlip(Integer.valueOf(minute));
//                            if(i>0)FlipNumberM2.activActionPositonFlip(Integer.valueOf(minute));
//                            if(i>1)FlipNumberM3.activActionPositonFlip(Integer.valueOf(minute));
//                            if(i>2)FlipNumberM4.activActionPositonFlip(Integer.valueOf(minute));
//                            if(i<3)i++;
//
//                            FlipNumberMD1.activActionPositonFlip(Integer.valueOf(diminute));
//                            if(i>0)FlipNumberMD2.activActionPositonFlip(Integer.valueOf(diminute));
//                            if(i>1)FlipNumberMD3.activActionPositonFlip(Integer.valueOf(diminute));
//                            if(i>2)FlipNumberMD4.activActionPositonFlip(Integer.valueOf(diminute));
//                            if(i<3)i++;
//
//
//                        }
                    }
                    if (minuteint == 0 || firstlaunch) {
//                        if(hourint >=0 && hourint <=9){
//                            hour = String.valueOf(hourint.toString().charAt(0));
//                            dihour = "0";
//                            FlipNumberH1.activActionPositonFlip(Integer.valueOf(hour));
//                            if(i>0)FlipNumberH2.activActionPositonFlip(Integer.valueOf(hour));
//                            if(i>1)FlipNumberH3.activActionPositonFlip(Integer.valueOf(hour));
//                            if(i>2)FlipNumberH4.activActionPositonFlip(Integer.valueOf(hour));
//                            if(i<3)i++;
//
//                            FlipNumberHD1.activActionPositonFlip(Integer.valueOf(dihour));
//                            if(i>0)FlipNumberHD2.activActionPositonFlip(Integer.valueOf(dihour));
//                            if(i>1)FlipNumberHD3.activActionPositonFlip(Integer.valueOf(dihour));
//                            if(i>2)FlipNumberHD4.activActionPositonFlip(Integer.valueOf(dihour));
//                            if(i<3)i++;
//                         }else{
//                            hour = String.valueOf(hourint.toString().charAt(1));
//                            dihour = String.valueOf(hourint.toString().charAt(0));
//                            FlipNumberH1.activActionPositonFlip(Integer.valueOf(hour));
//                            if(i>0)FlipNumberH2.activActionPositonFlip(Integer.valueOf(hour));
//                            if(i>1)FlipNumberH3.activActionPositonFlip(Integer.valueOf(hour));
//                            if(i>2)FlipNumberH4.activActionPositonFlip(Integer.valueOf(hour));
//                            if(i<3)i++;
//
//                            FlipNumberHD1.activActionPositonFlip(Integer.valueOf(dihour));
//                            if(i>0)FlipNumberHD2.activActionPositonFlip(Integer.valueOf(dihour));
//                            if(i>1)FlipNumberHD3.activActionPositonFlip(Integer.valueOf(dihour));
//                            if(i>2)FlipNumberHD4.activActionPositonFlip(Integer.valueOf(dihour));
//                            if(i<3)i++;
//                           }
                    }
                    firstlaunch = false;

                }


            }
        };

        Messenger messager = new Messenger(handler);
        Intent intent = new Intent(getApplicationContext(),ThreadServiceTimer.class);
        intent.putExtra("messager",messager);
        start = startService(intent) !=null;
    }



}
