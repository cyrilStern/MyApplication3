package com.example.root.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Date;


public class MainActivity extends AppCompatActivity {
    public Bitmap mybitmap,newbmp,bitmap,bmp;
    private Handler handler;
    private ImageView iw;
    private Boolean start;
    ImageView imageview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_main);
        //Bitmap bitmapreturn = BitmapFactory.decodeResource(getResources(),R.drawable.flip4);
        Canvas canvasreturn = new Canvas();
        Matrix m = new Matrix();
        m.setRotate(25f);
        //Bitmap btres = Bitmap.createBitmap(bitmapreturn,0,0,bitmapreturn.getWidth(),bitmapreturn.getHeight()/2,m,true);
        LayoutAddHalfImageToView koi = new LayoutAddHalfImageToView(this);
        //koi.setThePath();

        //canvasreturn.drawBitmap(bitmapreturn,550,550,null);
        canvasreturn.drawColor(Color.BLUE);

        //Button b = new Button(this);
        //b.setText("ferferf");
        //rl.addView(b);
        handler = new Handler(){
            final RelativeLayout  rl = (RelativeLayout) findViewById(R.id.activity_main);
            String number;

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                Integer hour = new Date().getSeconds();
                if(hour>=0 && hour <=9){
                     number = String.valueOf(hour.toString().charAt(0));
                }else{
                     number = String.valueOf(hour.toString().charAt(1));

                }

                Log.i("thisistheend",String.valueOf(number));
                Toast.makeText(getApplicationContext(),"this is the number: "+number,Toast.LENGTH_SHORT);
                iw = new ImageView(getApplicationContext());
                Log.i("passeParleHandler",String.valueOf(hour));
                iw.setImageBitmap(BitmapFactory.decodeResource(getApplication().getResources(),GetImagePathByNumber.getPath(Integer.valueOf(number),getApplicationContext())));
                rl.addView(iw);

            }
        };
        //RelativeLayout.LayoutParams params = new RelativeLayout().LayoutParams()
        Messenger messager = new Messenger(handler);

        Intent intent = new Intent(getApplicationContext(),ThreadServiceTimer.class);
        intent.putExtra("messager",messager);
        start = startService(intent) !=null;

        Log.i("pass par le thread","declenche le service command");

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
