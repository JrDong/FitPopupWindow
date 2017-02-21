package com.djr.fitpopupwindow.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by DongJr on 2017/2/21.
 * 尖角View
 */

public class SharpCornerView extends View {

    private Paint mPaint;

    public static final int UP_LEFT = 1;
    public static final int UP_RIGHT = 2;
    public static final int DOWN_LEFT = 3;
    public static final int DOWN_RIGHT = 4;
    public static final int UP = 5;
    public static final int DOWN = 6;

    private int mOrientation = DOWN_LEFT;

    private static final int VIEW_WIDTH = 50;

    private static final int VIEW_HEIGHT = (int) (VIEW_WIDTH * 1.5);

    public SharpCornerView(Context context) {
        this(context, null);
    }

    public SharpCornerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SharpCornerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {



        setMeasuredDimension(VIEW_WIDTH, VIEW_WIDTH);

    }

    public void setOrientation(int orientation) {
        mOrientation = orientation;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        //不要问我怎么算出来的,自己学着画下贝塞尔曲线就知道啦,哈哈
        path.moveTo(0, 0);
        path.cubicTo(0, VIEW_HEIGHT, 0, 0, VIEW_WIDTH, 0);
        canvas.drawPath(path, mPaint);

        if (mOrientation == UP_LEFT) {
            setScaleX(1);
            setScaleY(1);
        } else if (mOrientation == UP_RIGHT) {
            setScaleX(-1);
            setScaleY(1);
        } else if (mOrientation == DOWN_LEFT) {
            setScaleX(1);
            setScaleY(-1);
        } else if (mOrientation == DOWN_RIGHT) {
            setScaleX(-1);
            setScaleY(-1);
        }
    }

//    private void drawRect(Canvas canvas, int orientation) {
//        RectF rectF = new RectF();
//        if (orientation == DOWN) {
//            rectF.set(0, VIEW_HEIGHT, getMeasuredWidth(), getMeasuredHeight());
//        } else {
//            rectF.set(0, 0, getMeasuredWidth(), getMeasuredHeight() - VIEW_HEIGHT);
//        }
//
//        canvas.drawRoundRect(rectF, DensityUtils.dp2px(10)
//                , DensityUtils.dp2px(10), mPaint);
//    }

}
