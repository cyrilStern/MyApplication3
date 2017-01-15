package com.example.root.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
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
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public Bitmap mybitmap,newbmp,bitmap,bmp;
    private Handler handler;
    private FrameLayout fl,fl1,fl2,fl3,fl4,fl5;
    private ImageView iw,iw1,iw2,iw3,iw4,iw5;
    private Boolean start;
    private LinearLayout linearLayout;
    private boolean firstlaunch;
    private ImageView imageview;
    private ActionBar bar;
    private SplitBitmatInTwo splitBit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_main);
        fl = (FrameLayout) findViewById(R.id.dihour);
        fl1 = (FrameLayout) findViewById(R.id.hour);
        fl2 = (FrameLayout) findViewById(R.id.diminute);
        fl3 = (FrameLayout) findViewById(R.id.minute);
        fl4 = (FrameLayout) findViewById(R.id.diseconde);
        fl5 = (FrameLayout) findViewById(R.id.seconde);
        firstlaunch = true;
        bar = getSupportActionBar();
        //bar.hide();
        Canvas canvasreturn = new Canvas();
        Matrix m = new Matrix();
        m.setRotate(25f);
        //Bitmap btres = Bitmap.createBitmap(bitmapreturn,0,0,bitmapreturn.getWidth(),bitmapreturn.getHeight()/2,m,true);
        //koi.setThePath();
        //canvasreturn.drawBitmap(bitmapreturn,550,550,null);
        linearLayout = new LinearLayout(this);
        //linearLayout.setOrientation(LinearLayout.VERTICAL);
        FrameLayout.LayoutParams layoutParams= new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM;
        splitBit = new SplitBitmatInTwo();
        iw = new ImageView(getApplicationContext());
        iw1 = new ImageView(getApplicationContext());
        iw2 = new ImageView(getApplicationContext());
        iw3 = new ImageView(getApplicationContext());
        iw4 = new ImageView(getApplicationContext());
        iw5 = new ImageView(getApplicationContext());
        iw.setLayoutParams(layoutParams);
        iw1.setLayoutParams(layoutParams);
        fl.addView(iw5);
        fl1.addView(iw4);
        fl2.addView(iw3);
        fl3.addView(iw2);
        fl4.addView(iw1);
        fl5.addView(iw);



        //Button b = new Button(this);
        //b.setText("ferferf");
        //rl.addView(b);

        //RelativeLayout.LayoutParams params = new RelativeLayout().LayoutParams()


        Log.i("pass par le thread","declenche le service command");

    }

    @Override
    protected void onResume() {
        super.onResume();
        handler = new Handler(){
            final RelativeLayout  rl = (RelativeLayout) findViewById(R.id.activity_main);
            String second,disecond,minute,diminute,hour,dihour;

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                Integer secondint = new Date().getSeconds();
                Integer minuteint = new Date().getMinutes();
                Integer hourint = new Date().getHours();

                if(secondint >=0 && secondint <=9){
                    second = String.valueOf(secondint.toString().charAt(0));
                    disecond = "0";
                    splitBit.setbitmapReception(BitmapFactory.decodeResource(getApplication().getResources(),GetImagePathByNumber.getPath(Integer.valueOf(second),getApplicationContext())));
                    Bitmap bt = splitBit.returnBitmatnumber(0);

                    //r.setFillAfter(true); //HERE
                    final Animation myRotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation);
                    iw.startAnimation(myRotation);
                    iw.setImageBitmap(bt);


                    //iw.setImageBitmap(BitmapFactory.decodeResource(getApplication().getResources(),GetImagePathByNumber.getPath(Integer.valueOf(second),getApplicationContext())));
                    iw1.setImageBitmap(BitmapFactory.decodeResource(getApplication().getResources(),GetImagePathByNumber.getPath(Integer.valueOf(disecond),getApplicationContext())));

                }else{
                    second = String.valueOf(secondint.toString().charAt(1));
                    disecond = String.valueOf(secondint.toString().charAt(0));
                    splitBit.setbitmapReception(BitmapFactory.decodeResource(getApplication().getResources(),GetImagePathByNumber.getPath(Integer.valueOf(second),getApplicationContext())));

                   // iw.setImageBitmap(BitmapFactory.decodeResource(getApplication().getResources(),GetImagePathByNumber.getPath(Integer.valueOf(second),getApplicationContext())));
                    iw1.setImageBitmap(BitmapFactory.decodeResource(getApplication().getResources(),GetImagePathByNumber.getPath(Integer.valueOf(disecond),getApplicationContext())));
                    Bitmap bt = splitBit.returnBitmatnumber(0);
                    iw.setImageBitmap(bt);
                    final Animation myRotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation);
                    iw.startAnimation(myRotation);


                }
                if(secondint==0 || firstlaunch){
                    if(minuteint >=0 && minuteint <=9){
                        minute = String.valueOf(minuteint.toString().charAt(0));
                        diminute = "0";
                        iw2.setImageBitmap(BitmapFactory.decodeResource(getApplication().getResources(),GetImagePathByNumber.getPath(Integer.valueOf(minute),getApplicationContext())));
                        iw3.setImageBitmap(BitmapFactory.decodeResource(getApplication().getResources(),GetImagePathByNumber.getPath(Integer.valueOf(diminute),getApplicationContext())));

                    }else{
                        minute = String.valueOf(minuteint.toString().charAt(1));
                        diminute = String.valueOf(minuteint.toString().charAt(0));
                        iw2.setImageBitmap(BitmapFactory.decodeResource(getApplication().getResources(),GetImagePathByNumber.getPath(Integer.valueOf(minute),getApplicationContext())));
                        iw3.setImageBitmap(BitmapFactory.decodeResource(getApplication().getResources(),GetImagePathByNumber.getPath(Integer.valueOf(diminute),getApplicationContext())));
                    }
                }
                if(minuteint==0 || firstlaunch){
                    if(hourint >=0 && hourint <=9){
                        hour = String.valueOf(hourint.toString().charAt(0));
                        dihour = "0";
                        iw4.setImageBitmap(BitmapFactory.decodeResource(getApplication().getResources(),GetImagePathByNumber.getPath(Integer.valueOf(hour),getApplicationContext())));
                        iw5.setImageBitmap(BitmapFactory.decodeResource(getApplication().getResources(),GetImagePathByNumber.getPath(Integer.valueOf(dihour),getApplicationContext())));
                    }else{
                        hour = String.valueOf(hourint.toString().charAt(1));
                        dihour = String.valueOf(hourint.toString().charAt(0));
                        iw4.setImageBitmap(BitmapFactory.decodeResource(getApplication().getResources(),GetImagePathByNumber.getPath(Integer.valueOf(hour),getApplicationContext())));
                        iw5.setImageBitmap(BitmapFactory.decodeResource(getApplication().getResources(),GetImagePathByNumber.getPath(Integer.valueOf(dihour),getApplicationContext())));
                    }
                }
                firstlaunch = false;
                Toast.makeText(getApplicationContext(),"this is the number: "+second,Toast.LENGTH_SHORT);


            }
        };
        Messenger messager = new Messenger(handler);

        Intent intent = new Intent(getApplicationContext(),ThreadServiceTimer.class);
        intent.putExtra("messager",messager);
        start = startService(intent) !=null;
    }

    private class AddView extends View {
        public AddView(Context context) {
            super(context);
            Bitmap bitmap = init();
            Canvas canvasreturn = new Canvas();
            canvasreturn.drawColor(123548);
            canvasreturn.drawBitmap(bitmap,50,50,null);
            createtheview(canvasreturn);

        }
        private Bitmap init(){
           // Bitmap bitmapreturn = BitmapFactory.decodeResource(getResources(),R.drawable.flip4);

            return null;
        }
        private RelativeLayout createtheview(Canvas canvas){
            RelativeLayout framelayout = new RelativeLayout(getContext());
            ImageView iw = new ImageView(getContext());
            iw.draw(canvas);
            framelayout.addView(iw);
            return framelayout;
        }
    }

}
