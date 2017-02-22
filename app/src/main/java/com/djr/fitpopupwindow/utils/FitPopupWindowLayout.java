package com.djr.fitpopupwindow.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by DongJr on 2017/2/21.
 * <p>
 * 气泡
 */

public class FitPopupWindowLayout extends RelativeLayout {

    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;
    public static final int DOWN = 4;

    private int mHorizontal = LEFT;
    private int mVertical = DOWN;


    private Paint mPaint;
    public static final int mSharpWidth = 50;
    public static final int mSharpHeight = (int) (mSharpWidth * 1.0f);
    private static final int RECT_CORNER = 20;

    private int mXoffset = 20;

    private Path mPath = new Path();
    private Path mSharpPath = new Path();
//    private Matrix matrix = new Matrix();


    public FitPopupWindowLayout(Context context) {
        this(context, null);
    }

    public FitPopupWindowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FitPopupWindowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.TRANSPARENT);

        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

    }


    private Path makeSharpPath() {
        mSharpPath.moveTo(mXoffset, getMeasuredHeight() - mSharpHeight);
        mSharpPath.cubicTo(mXoffset, getMeasuredHeight(), mXoffset, getMeasuredHeight() - mSharpHeight,
                mSharpWidth + mXoffset, getMeasuredHeight() - mSharpHeight);
        return mSharpPath;
    }



    @Override
    protected void onDraw(Canvas canvas) {

        mPath.addRoundRect(new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight() - mSharpHeight)
                , RECT_CORNER, RECT_CORNER, Path.Direction.CW);
        mPath.addPath(makeSharpPath());
        canvas.drawPath(mPath, mPaint);

        if (mHorizontal == LEFT && mVertical == UP) {
            setScaleX(1);
            setScaleY(1);
        } else if (mHorizontal == LEFT && mVertical == DOWN) {
            setScaleX(1);
            setScaleY(-1);
            scaleChild(1, -1);
        } else if (mHorizontal == RIGHT && mVertical == UP) {
            setScaleX(-1);
            setScaleY(1);
            scaleChild(-1, 1);
        } else if (mHorizontal == RIGHT && mVertical == DOWN) {
            setScaleX(-1);
            setScaleY(-1);
            scaleChild(-1, -1);
        }

    }

    private void scaleChild(float scaleX, float scaleY) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setScaleX(scaleX);
            getChildAt(i).setScaleY(scaleY);
        }
    }

    public void setOrientation(int horizontal, int vertical, int xOffset) {
        mHorizontal = horizontal;
        mVertical = vertical;
        mXoffset = xOffset + 20;
        invalidate();
    }

}
