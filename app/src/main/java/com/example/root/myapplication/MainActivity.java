package com.example.root.myapplication;

import android.animation.ValueAnimator;
import android.app.DialogFragment;
import android.app.IntentService;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.root.myapplication.connexion.ConnexionThread;
import com.example.root.myapplication.connexion.ConnexionThreadRadio;
import com.example.root.myapplication.connexion.UrlConnection;
import com.example.root.myapplication.myapplication.ListActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

import static com.example.root.myapplication.connexion.ConnctionInterface.URLPATH;


public class MainActivity extends AppCompatActivity {
    private static final String START_STRING = "START";
    private final int bigsize = 11;
    private final int littlesize = 0;
    public Bitmap mybitmap,newbmp,bitmap,bmp;
    private Handler handler;
    private FrameLayout fl,fl1,fl2,fl3,fl4,fl5;
    private ImageView iw,iw1,iw2,iw3,iw4,iw5;
    private Boolean start, setplayerfirstlaunch, setplayerfirstlaunch2;
    private LinearLayout linearLayout;
    private boolean firstlaunch;
    private ImageView imageview;
    private ActionBar bar;
    private int i, j, h, constM, constSD, constDM, constH, constDH;
    private String savediseconde, savediminute, savehour, savediminu, savehou;
    private SplitBitmatInTwo splitBit;
    private FrameLayout frameret;
    private FlipNumber FlipNumberS1, FlipNumberS2, FlipNumberS4, FlipNumberS3, FlipNumberSD1, FlipNumberSD2, FlipNumberSD3, FlipNumberSD4;
    private FlipNumber FlipNumberM1, FlipNumberM2, FlipNumberM3, FlipNumberM4, FlipNumberMD1, FlipNumberMD2, FlipNumberMD3, FlipNumberMD4;
    private FlipNumber FlipNumberH1, FlipNumberH2, FlipNumberH3, FlipNumberH4, FlipNumberHD1, FlipNumberHD2, FlipNumberHD3, FlipNumberHD4;
    private Button btalrm;
    private MediaPlayer mplayer, mplayer2;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        btalrm = (Button) findViewById(R.id.alarmButton);
        savediseconde = START_STRING;
        savediminute = START_STRING;
        savehour = START_STRING;
        savediminu = START_STRING;
        savehou = START_STRING;
        setplayerfirstlaunch = true;
        setplayerfirstlaunch2 = true;

        fl = (FrameLayout) findViewById(R.id.seconde);
        FlipNumberS1 = new FlipNumber(this, 0, bigsize);
        FlipNumberS4 = new FlipNumber(this, 0, bigsize);
        FlipNumberS2 = new FlipNumber(this, 0, bigsize);
        FlipNumberS3 = new FlipNumber(this, 0, bigsize);
        fl.addView(FlipNumberS4);
        fl.addView(FlipNumberS3);
        fl.addView(FlipNumberS2);
        fl.addView(FlipNumberS1);

        fl1 = (FrameLayout) findViewById(R.id.diseconde);
        FlipNumberSD1 = new FlipNumber(this, 0, bigsize);
        FlipNumberSD4 = new FlipNumber(this, 0, bigsize);
        FlipNumberSD2 = new FlipNumber(this, 0, bigsize);
        FlipNumberSD3 = new FlipNumber(this, 0, bigsize);
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


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init() {
        for (int i = 0; i < 4; i++) {

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
            if (i > 0) this.FlipNumberS2.activActionPositonFlip(Integer.valueOf(second));
            if (i > 1) this.FlipNumberS3.activActionPositonFlip(Integer.valueOf(second));
            if (i > 2) this.FlipNumberS4.activActionPositonFlip(Integer.valueOf(second));

            this.FlipNumberSD1.activActionPositonFlip(Integer.valueOf(second));
            if (i > 0) this.FlipNumberSD2.activActionPositonFlip(Integer.valueOf(second));
            if (i > 1) this.FlipNumberSD3.activActionPositonFlip(Integer.valueOf(second));
            if (i > 2) this.FlipNumberSD4.activActionPositonFlip(Integer.valueOf(second));

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
        btalrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), ConnexionThreadRadio.class);
                Toast.makeText(getApplicationContext(), START_STRING, Toast.LENGTH_LONG).show();
                startService(intent);
                Intent intent2 = new Intent();
                intent2.setClass(getApplicationContext(), ListActivity.class);
                startActivity(intent2);
            }
        });
        mplayer = new MediaPlayer();
        mplayer2 = new MediaPlayer();


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();
        init();

        View cursorRadio = findViewById(R.id.testdisplay);
        cursorRadio.setOnTouchListener(new View.OnTouchListener() {
                                           @Override
                                           public boolean onTouch(View v, MotionEvent event) {

                                               int x = (int) event.getRawX();
                                               Log.i("position", String.valueOf(x));
                                               int y = (int) event.getY();
                                               View parent = (View) v.getParent();
                                               int Border = (x - parent.getWidth()) - v.getWidth();
                                               switch (event.getAction()) {
                                                   case MotionEvent.ACTION_DOWN:
                                                       break;
                                                   case MotionEvent.ACTION_MOVE:
                                                       if (setplayerfirstlaunch) {
                                                           AssetManager assetManager = getAssets();
                                                           AssetFileDescriptor descriptor = null;
                                                           try {
                                                               descriptor = assetManager.openFd("audio/RadioTune.mp3");
                                                               mplayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                                                               mplayer.prepare();
                                                               mplayer.start();
                                                               mplayer.setLooping(true);
                                                               setplayerfirstlaunch = false;
                                                           } catch (IOException e1) {
                                                               // TODO Auto-generated catch block
                                                               e1.printStackTrace();
                                                           }

                                                       }
                                                       if ((x > 100) && (x < 250) && setplayerfirstlaunch2) {
                                                           setplayerfirstlaunch2 = false;
                                                           Thread th = new Thread(new Runnable() {
                                                               @Override
                                                               public void run() {
                                                                   mplayer2.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                                                   try {
                                                                       mplayer2.setDataSource("http://stream.ouifm.fr/ouifm-high.mp3");
                                                                   } catch (IOException e) {
                                                                       e.printStackTrace();
                                                                   }
                                                                   try {
                                                                       mplayer2.prepare();
                                                                       mplayer2.start();
                                                                       while (mplayer2.isPlaying()) {
                                                                           //mplayer.stop();
                                                                       }
                                                                   } catch (IOException e) {
                                                                       e.printStackTrace();
                                                                   }
                                                               }
                                                           });
                                                           th.start();
                                                       }
                                                       FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(v.getLayoutParams());
                                                       lp.setMargins(x, 0, 0, Border);

                                                       lp.setMarginStart(x);
                                                       v.setLayoutParams(lp);
                                                       break;
                                                   case MotionEvent.ACTION_UP:
                                                       mplayer.stop();
                                                       mplayer.reset();

                                                       setplayerfirstlaunch = true;
                                                       break;

                                               }

                                               return true;
                                           }

                                       }
        );
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
                    if (secondint >= 0 && secondint <= 9) {
                        second = String.valueOf(secondint.toString().charAt(0));
                        disecond = "0";
                        FlipNumberS1.activActionPositonFlip(Integer.valueOf(second));
                        FlipNumberS2.activActionPositonFlip(Integer.valueOf(second));
                        FlipNumberS3.activActionPositonFlip(Integer.valueOf(second));
                        FlipNumberS4.activActionPositonFlip(Integer.valueOf(second));
                        if (!savediseconde.equals(disecond)) {
                            FlipNumberSD1.activActionPositonFlip(Integer.valueOf(disecond));
                            FlipNumberSD2.activActionPositonFlip(Integer.valueOf(disecond));
                            FlipNumberSD3.activActionPositonFlip(Integer.valueOf(disecond));
                            FlipNumberSD4.activActionPositonFlip(Integer.valueOf(disecond));
                            savediseconde = disecond;
                        }
                    }else{
                        second = String.valueOf(secondint.toString().charAt(1));
                        disecond = String.valueOf(secondint.toString().charAt(0));
                        FlipNumberS1.activActionPositonFlip(Integer.valueOf(second));
                        FlipNumberS2.activActionPositonFlip(Integer.valueOf(second));
                        FlipNumberS3.activActionPositonFlip(Integer.valueOf(second));
                        FlipNumberS4.activActionPositonFlip(Integer.valueOf(second));
                        if (!savediseconde.equals(disecond)) {
                            FlipNumberSD1.activActionPositonFlip(Integer.valueOf(disecond));
                            FlipNumberSD2.activActionPositonFlip(Integer.valueOf(disecond));
                            FlipNumberSD3.activActionPositonFlip(Integer.valueOf(disecond));
                            FlipNumberSD4.activActionPositonFlip(Integer.valueOf(disecond));
                            savediseconde = disecond;
                        }
                    }
                    if (minuteint >= 0 && minuteint <= 9) {
                        minute = String.valueOf(minuteint.toString().charAt(0));
                        diminute = "0";
                        if (!savediminu.equals(minute)) {
                            FlipNumberM1.activActionPositonFlip(Integer.valueOf(minute));
                            FlipNumberM2.activActionPositonFlip(Integer.valueOf(minute));
                            FlipNumberM3.activActionPositonFlip(Integer.valueOf(minute));
                            FlipNumberM4.activActionPositonFlip(Integer.valueOf(minute));
                            savediminu = minute;

                        }
                        if (!savediminute.equals(diminute)) {
                            FlipNumberMD1.activActionPositonFlip(Integer.valueOf(diminute));
                            FlipNumberMD2.activActionPositonFlip(Integer.valueOf(diminute));
                            FlipNumberMD3.activActionPositonFlip(Integer.valueOf(diminute));
                            FlipNumberMD4.activActionPositonFlip(Integer.valueOf(diminute));
                            savediminute = diminute;
                        }
                    } else {
                        minute = String.valueOf(minuteint.toString().charAt(1));
                        diminute = String.valueOf(minuteint.toString().charAt(0));
                        if (!savediminu.equals(minute)) {
                            FlipNumberM1.activActionPositonFlip(Integer.valueOf(minute));
                            FlipNumberM2.activActionPositonFlip(Integer.valueOf(minute));
                            FlipNumberM3.activActionPositonFlip(Integer.valueOf(minute));
                            FlipNumberM4.activActionPositonFlip(Integer.valueOf(minute));
                            savediminu = minute;
                        }
                        if (!savediminute.equals(diminute)) {
                            FlipNumberMD1.activActionPositonFlip(Integer.valueOf(diminute));
                            FlipNumberMD2.activActionPositonFlip(Integer.valueOf(diminute));
                            FlipNumberMD3.activActionPositonFlip(Integer.valueOf(diminute));
                            FlipNumberMD4.activActionPositonFlip(Integer.valueOf(diminute));
                            savediminute = diminute;
                        }
                    }
                    if (hourint >= 0 && hourint <= 9) {
                        hour = String.valueOf(hourint.toString().charAt(0));
                        dihour = "0";
                        if (!savehou.equals(hour)) {
                            FlipNumberH1.activActionPositonFlip(Integer.valueOf(hour));
                            FlipNumberH2.activActionPositonFlip(Integer.valueOf(hour));
                            FlipNumberH3.activActionPositonFlip(Integer.valueOf(hour));
                            FlipNumberH4.activActionPositonFlip(Integer.valueOf(hour));
                            savehou = hour;
                        }
                        if (!savehour.equals(dihour)) {
                            FlipNumberHD1.activActionPositonFlip(Integer.valueOf(dihour));
                            FlipNumberHD2.activActionPositonFlip(Integer.valueOf(dihour));
                            FlipNumberHD3.activActionPositonFlip(Integer.valueOf(dihour));
                            FlipNumberHD4.activActionPositonFlip(Integer.valueOf(dihour));
                            savehour = dihour;
                        }
                    } else {
                        hour = String.valueOf(hourint.toString().charAt(1));
                        dihour = String.valueOf(hourint.toString().charAt(0));
                        if (!savehou.equals(hour)) {
                            FlipNumberH1.activActionPositonFlip(Integer.valueOf(hour));
                            FlipNumberH2.activActionPositonFlip(Integer.valueOf(hour));
                            FlipNumberH3.activActionPositonFlip(Integer.valueOf(hour));
                            FlipNumberH4.activActionPositonFlip(Integer.valueOf(hour));
                            savehou = hour;
                        }
                        if (!savehour.equals(dihour)) {
                            FlipNumberHD1.activActionPositonFlip(Integer.valueOf(dihour));
                            FlipNumberHD2.activActionPositonFlip(Integer.valueOf(dihour));
                            FlipNumberHD3.activActionPositonFlip(Integer.valueOf(dihour));
                            FlipNumberHD4.activActionPositonFlip(Integer.valueOf(dihour));
                            savehour = dihour;
                        }
                    }
                }
            }
        };

        Messenger messager = new Messenger(handler);
        Intent intent = new Intent(this, ThreadServiceTimer.class);
        intent.putExtra("messager",messager);
        start = startService(intent) !=null;
    }



}
