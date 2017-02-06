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
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by cyrilstern1 on 14/01/2017.
 */

public class FlipNumber extends ImageView {

    private final static String LAYOUT_VIEW_POSITION_TOP = "top";
    private final static String LAYOUT_VIEW_POSITION_BOTTOM = "bottom";
    private final int CAMERA_DISTANCE = 1000;
    private final int MIN_FLIP_DURATION = 400;
    private final int VELOCITY_TO_DURATION_CONST = 1;
    private final int MAX_FLIP_DURATION = 400;
    private final int ANTIALIAS_BORDER = 1;
    private int number = 0;
    private FrameLayout frameLayout;
    private Matrix horizontFlipmatrix;
    private BitmapDrawable mFrontBitmapDrawable;
    private boolean mInFrontShowing = true;
    private boolean mIsHorizontakkyFlipped = false;
    private BitmapDrawable mCurrentBitmapDrawable;
    private int position;
    private int size = 0;
    private boolean onlyone;


    public FlipNumber(Context context, int position) {
        super(context);
        this.position = position;
        initCtx(context);
    }

    public FlipNumber(Context context, int position, int size) {
        super(context);
        this.position = position;
        this.size = size;
        initCtx(context);
    }
    public FlipNumber(Context context, AttributeSet attributeSet) {
        super(context);
        initCtx(context);
    }

    private void initCtx(Context context){
        int getressource0 = GetImagePathByNumber.getPath(number, FlipNumber.LAYOUT_VIEW_POSITION_TOP, getContext());
        mFrontBitmapDrawable = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), getressource0));
        horizontFlipmatrix = new Matrix();
        setPivotY(mFrontBitmapDrawable.getBitmap().getHeight() + 10);
        setCameraDistance(CAMERA_DISTANCE);
        updateDrawableBitmap();
    }

    private BitmapDrawable bitmapDrawablewithBorder(BitmapDrawable bitmapDrawable){
        Bitmap bitmapDrawablewithBorder = Bitmap.createBitmap(bitmapDrawable.getIntrinsicWidth() +
                ANTIALIAS_BORDER * 2,bitmapDrawable.getIntrinsicHeight() + ANTIALIAS_BORDER * 2, Bitmap.Config.ARGB_8888);
        return new BitmapDrawable(getResources(),bitmapDrawablewithBorder);

    }

    BitmapDrawable flip(BitmapDrawable d) {
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        Bitmap src = d.getBitmap();
        Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
        dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        return new BitmapDrawable(this.getResources(), dst);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void activActionPositonFlip(int timeNumber) {
        this.number = timeNumber;
        switch (position) {
            case 0:
                this.setZ(3f);
                int getressource0 = GetImagePathByNumber.getPath(number, FlipNumber.LAYOUT_VIEW_POSITION_TOP, getContext());
                mFrontBitmapDrawable = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), getressource0));
                updateDrawableBitmap();
                this.position = 1;
                break;
            case 1:
                this.setZ(6f);
                int getressource1 = GetImagePathByNumber.getPath(number, FlipNumber.LAYOUT_VIEW_POSITION_TOP, getContext());
                mFrontBitmapDrawable = flip(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), getressource1)));
                this.onlyone = true;
                this.position = 2;
                this.flipToBotton(1, 1, true);

                break;
            case 2:
                int getressource2 = GetImagePathByNumber.getPath(number, FlipNumber.LAYOUT_VIEW_POSITION_TOP, getContext());
                mFrontBitmapDrawable = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), getressource2));
                this.setZ(3f);
                this.position = 3;

                break;
            case 3:
                this.flipToBotton(1, 1, false);
                this.setZ(2f);
                this.position = 0;
                flipToBotton(1, 1, false);
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
            yOffset = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, size != 0 ? size : 2f);
        } else {
            yOffset = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, -2f);
        }

        ObjectAnimator timeAnimator = ObjectAnimator.ofPropertyValuesHolder(this,rotation,xOffset,yOffset);
        if (clockwise) {
            timeAnimator.setDuration(500);
        } else {
            timeAnimator.setDuration(100);
        }
        timeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (valueAnimator.getAnimatedFraction() > 0.5 && onlyone) {
                    if (position == 2) {
                        int getressource2 = GetImagePathByNumber.getPath(number, FlipNumber.LAYOUT_VIEW_POSITION_BOTTOM, getContext());
                        mFrontBitmapDrawable = flip(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), getressource2)));
                        updateDrawableBitmap();
                        onlyone = false;
                    }
                    if (position == 0) {
                        int getressource2 = GetImagePathByNumber.getPath(number, FlipNumber.LAYOUT_VIEW_POSITION_TOP, getContext());
                        mFrontBitmapDrawable = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), getressource2));
                        updateDrawableBitmap();
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
                updateDrawableBitmap();
               // updateLayoutParams();

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
    public void toggleFrontShowing(){
        mInFrontShowing = !mInFrontShowing;
    }
    public void toggleIsHorizontalFlipped(){

        mIsHorizontakkyFlipped = !mIsHorizontakkyFlipped;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (position == 2 || position == 3) horizontFlipmatrix.setScale(-1, -1, w / 2, h / 2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.concat(horizontFlipmatrix);
        super.onDraw(canvas);
    }
    public void updateLayoutParams(){
        //RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
        // params.leftMargin = (int) (params.leftMargin + ((Math.abs(getRotationX())) %360/180)*(2*getPivotX() - getHeight()));
        // setRotationX(0);
        // setRotationY(0);
        //setLayoutParams(params);
    }

    public void updateDrawableBitmap(){
        setImageDrawable(mFrontBitmapDrawable);
    }

    public void updateTranslation(int numinpile){
        //setTranslationX();
    }

}
