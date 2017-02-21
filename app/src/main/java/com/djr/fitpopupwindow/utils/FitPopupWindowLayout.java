package com.djr.fitpopupwindow.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.ActionBarContextView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.djr.fitpopupwindow.R;

/**
 * Created by DongJr on 2017/2/21.
 */

public class FitPopupWindowLayout extends RelativeLayout {

    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;
    public static final int DOWN = 4;

    private int mHorizontal = LEFT;
    private int mVertical = DOWN;


    private View contentView;
    private SharpCornerView mSharpView;

    public FitPopupWindowLayout(Context context) {
        this(context, null);
    }

    public FitPopupWindowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FitPopupWindowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.TRANSPARENT);


        setOrientation(UP, LEFT);
    }

    public void setOrientation(int horizontal, int vertical) {
        mHorizontal = horizontal;
        mVertical = vertical;
        initSharp();
        initBackGround();
        invalidate();
    }

    private void initBackGround() {
        contentView = new View(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , DensityUtils.dp2px(80));
        if (mVertical == UP) {
            params.bottomMargin = mSharpView.getMeasuredHeight();
        } else {
            params.topMargin = mSharpView.getMeasuredHeight();
        }
        contentView.setLayoutParams(params);
        addView(contentView);
    }

    private void initSharp() {
        mSharpView = new SharpCornerView(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);

        if (mHorizontal == LEFT && mVertical == UP) {
            mSharpView.setOrientation(SharpCornerView.UP_LEFT);
            params.addRule(ABOVE);
            params.addRule(LEFT);
        } else if (mHorizontal == LEFT && mVertical == DOWN) {
            mSharpView.setOrientation(SharpCornerView.DOWN_LEFT);
            params.addRule(BELOW);
        } else if (mHorizontal == RIGHT && mVertical == UP) {
            params.addRule(ABOVE);
            mSharpView.setOrientation(SharpCornerView.UP_RIGHT);
        } else if (mHorizontal == RIGHT && mVertical == DOWN) {
            mSharpView.setOrientation(SharpCornerView.DOWN_RIGHT);
            params.addRule(BELOW);

        }
        mSharpView.setLayoutParams(params);

        addView(mSharpView);
    }


}
