package com.example.root.myapplication;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.lang.reflect.Array;

/**
 * Created by cyrilstern1 on 14/01/2017.
 */

public class FlipNumber extends ImageView {

    private final static String LAYOUT_VIEW_POSITION_TOP = "top";
    private final static String LAYOUT_VIEW_POSITION_BOTTOM = "bottom";
    private final int CAMERA_DISTANCE = 100;
    private final int MIN_FLIP_DURATION = 400;
    private final int VELOCITY_TO_DURATION_CONST = 1;
    private final int MAX_FLIP_DURATION = 400;
    private final int ANTIALIAS_BORDER = 1;
    private int number = 0;
    private Matrix horizontFlipmatrix;
    private BitmapDrawable mFrontBitmapDrawable;
    private boolean mInFrontShowing = true;
    private boolean mIsHorizontakkyFlipped = false;
    private BitmapDrawable mCurrentBitmapDrawable;
    private int position;
    private int size = 0;
    private boolean onlyone;
    private String TAG = "testlongueur";
    private float scale;
    private FrameLayout.LayoutParams imageViewParams;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public FlipNumber(Context context, int position) {
        super(context);
        this.position = position;
        initCtx(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public FlipNumber(Context context, int position, int size) {
        super(context);
        this.position = position;
        this.size = size;
        initCtx(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public FlipNumber(Context context, AttributeSet attributeSet) {
        super(context);

        initCtx(context);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initCtx(Context context){
        this.setPivotY(this.getBottom());

        scale = context.getResources().getDisplayMetrics().density;
        int getressource0 = GetImagePathByNumber.getPath(number, FlipNumber.LAYOUT_VIEW_POSITION_TOP, getContext());
        mFrontBitmapDrawable = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), getressource0));
        horizontFlipmatrix = new Matrix();
        updateDrawableBitmap();
        this.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        imageViewParams = (FrameLayout.LayoutParams) this.getLayoutParams();
        if (size == 1)
            imageViewParams.height = this.getResources().getDisplayMetrics().heightPixels / 7;

        //imageViewParams.width = this.getResources().getDisplayMetrics().heightPixels/4;
        this.setLayoutParams(imageViewParams);
//setCameraDistance(CAMERA_DISTANCE);

    }

    private BitmapDrawable bitmapDrawablewithBorder(BitmapDrawable bitmapDrawable){
        Bitmap bitmapDrawablewithBorder = Bitmap.createBitmap(bitmapDrawable.getIntrinsicWidth() +
                ANTIALIAS_BORDER * 2,bitmapDrawable.getIntrinsicHeight() + ANTIALIAS_BORDER * 2, Bitmap.Config.ARGB_8888);
        return new BitmapDrawable(getResources(),bitmapDrawablewithBorder);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    BitmapDrawable flip(BitmapDrawable d, boolean sense) {

        Matrix m = new Matrix();
        if (sense) {
            m.preScale(1, -1);
        } else {
            m.preScale(1, 1);
        }
        Bitmap src = d.getBitmap();
        Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
        //dst.setDensity(DisplayMetrics.DENSITY_DEVICE_STABLE);
        return new BitmapDrawable(this.getResources(), dst);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void activActionPositonFlip(int timeNumber) {
        this.number = timeNumber;
        switch (position) {
            case 0:
                int getressource0 = GetImagePathByNumber.getPath(number, FlipNumber.LAYOUT_VIEW_POSITION_TOP, getContext());
                mFrontBitmapDrawable = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), getressource0));
                updateDrawableBitmap();
                this.position = 1;
                this.setZ(4f);

                break;
            case 1:
                this.setZ(6f);
                this.onlyone = true;
                this.position = 2;
                this.flipToBotton(1, 1, true);
                break;
            case 2:
                this.setZ(3f);
                this.position = 3;
                break;
            case 3:
                this.onlyone = true;
                this.flipToBotton(1, 1, false);
                this.position = 0;
                this.setZ(2f);
                break;
        }
    }

    public void flipToBotton(int numberPile, int velocity, boolean retour) {
        flipVertical(retour, velocity);
    }

    public int getPositonLayer() {
        return this.position;
    }

    public void flipVertical(boolean clockwise, int velocity) {
        PropertyValuesHolder rotation = PropertyValuesHolder.ofFloat(View.ROTATION_X, clockwise ? 180.0f : 360.0f);
        PropertyValuesHolder xOffset = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 0f);
        PropertyValuesHolder yOffset;
        if (clockwise) {
            yOffset = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, size != 0 ? size : 0f);
        } else {
            yOffset = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, -0f);
        }
        ObjectAnimator timeAnimator = ObjectAnimator.ofPropertyValuesHolder(this,rotation,xOffset,yOffset);
        if (clockwise) {
            timeAnimator.setDuration(500);
        } else {
            timeAnimator.setDuration(500);
        }
        timeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (valueAnimator.getAnimatedFraction() > 0.5 && onlyone) {
                    if (position == 2) {
                        int getressource2 = GetImagePathByNumber.getPath(number, FlipNumber.LAYOUT_VIEW_POSITION_BOTTOM, getContext());
                        mFrontBitmapDrawable = flip(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), getressource2)), true);
                        updateDrawableBitmap();
                        onlyone = false;
                    }
                    if (position == 0) {
                        int getressource2 = GetImagePathByNumber.getPath(number, FlipNumber.LAYOUT_VIEW_POSITION_TOP, getContext());
                        mFrontBitmapDrawable = flip(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), getressource2)), false);
                        onlyone = false;
                    }
                }
            }
        });
        timeAnimator.start();
        Keyframe shadowKeyFrameStart = Keyframe.ofFloat(0,0);
        Keyframe shadowKeyFrameMid = Keyframe.ofFloat(0.5f,1);
        Keyframe shadowKeyFrameEnd = Keyframe.ofFloat(1,1);
        PropertyValuesHolder shadowsValueHolder = PropertyValuesHolder.ofKeyframe("shadow",shadowKeyFrameStart,shadowKeyFrameMid,shadowKeyFrameEnd);
        ObjectAnimator objanimatorColor = ObjectAnimator.ofPropertyValuesHolder(this,shadowsValueHolder);
        AnimatorSet set = new AnimatorSet();
        int duration = MAX_FLIP_DURATION - Math.abs(velocity)/VELOCITY_TO_DURATION_CONST;
        duration = duration < MIN_FLIP_DURATION ? MIN_FLIP_DURATION :duration;
        set.setDuration(duration);
        set.playTogether(timeAnimator,objanimatorColor);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                // updateDrawableBitmap();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.start();

    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.setPivotY(this.getBottom());

        //this.setLayoutParams(imageViewParams);
        //this.setBackgroundColor(Color.parseColor("#ffffff"));
        //if (position == 2 || position == 3) horizontFlipmatrix.setScale(-1, -1, w / 2, h / 2);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void updateDrawableBitmap(){
        this.setBackground(mFrontBitmapDrawable);
    }


}
