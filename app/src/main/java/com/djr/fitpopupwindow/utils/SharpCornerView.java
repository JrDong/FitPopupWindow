package com.djr.fitpopupwindow.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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

    private int mOrientation = DOWN_RIGHT;

    private static final int VIEW_WIDTH = 50;

    private static final int VIEW_HEIGHT = 50;

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

        setMeasuredDimension((int) (VIEW_WIDTH * 1.2), VIEW_HEIGHT);

    }

    public void setOrientation(int orientation) {
        mOrientation = orientation;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {


        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);

        Path path = new Path();
        //不要问我怎么算出来的,自己学着画下贝塞尔曲线就知道啦,哈哈
        if (mOrientation == DOWN_LEFT) {

            path.moveTo(0, 0);
            path.cubicTo(0, VIEW_HEIGHT, 0, 0, VIEW_WIDTH, 0);

        } else if (mOrientation == DOWN_RIGHT) {
            path.moveTo(0, 0);
            path.cubicTo(VIEW_WIDTH, 0, VIEW_HEIGHT * 1.5f, VIEW_WIDTH, VIEW_WIDTH, 0);
        } else if (mOrientation == UP_LEFT) {
            path.moveTo(0, 50);
            path.cubicTo(0, 0, 0, 50, 30, 50);
        } else if (mOrientation == UP_RIGHT) {
            path.moveTo(0, 50);
            path.cubicTo(30, 50, 30, 0, 30, 50);
        }

        canvas.drawPath(path, mPaint);


    }
}
