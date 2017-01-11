package com.example.root.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public Bitmap mybitmap,newbmp,bitmap,bmp;
    ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_main);
        Bitmap bitmapreturn = BitmapFactory.decodeResource(getResources(),R.drawable.flip4);
        Canvas canvasreturn = new Canvas();
        Matrix m = new Matrix();
        m.setRotate(25f);
        Bitmap btres = Bitmap.createBitmap(bitmapreturn,0,0,bitmapreturn.getWidth(),bitmapreturn.getHeight()/2,m,true);
        LayoutAddHalfImageToView koi = new LayoutAddHalfImageToView(this);
        koi.setThePath();

        canvasreturn.drawBitmap(bitmapreturn,550,550,null);
        ImageView iw = new ImageView(this);
        canvasreturn.drawColor(Color.BLUE);
        iw.setImageBitmap(btres);

        rl.addView(iw);
        Button b = new Button(this);
        b.setText("ferferf");
        rl.addView(b);
        //RelativeLayout.LayoutParams params = new RelativeLayout().LayoutParams()
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
            Bitmap bitmapreturn = BitmapFactory.decodeResource(getResources(),R.drawable.flip4);

            return bitmapreturn;
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
