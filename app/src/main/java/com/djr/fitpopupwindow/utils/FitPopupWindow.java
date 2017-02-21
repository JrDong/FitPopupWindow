package com.djr.fitpopupwindow.utils;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by DongJr on 2017/2/21.
 */

public class FitPopupWindow extends PopupWindow implements PopupWindow.OnDismissListener {

    private View contentView;

    private Activity context;

    private int mWidth;

    private static final int PADDING = 20;


    public FitPopupWindow(Activity context, View contentView) {
        init(context, contentView, ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public FitPopupWindow(Activity context, View contentView, int width, int height) {
        mWidth = width;
        init(context, contentView, width, height);
    }


    public FitPopupWindow(Activity context, int layoutId) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View contentView = inflater.inflate(layoutId, null);
        init(context, contentView, ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    private void init(Activity context, View contentView, int width, int height) {
        this.contentView = contentView;
        this.context = context;
        //popupwindow会默认忽略最外层的大小,所以应该再嵌套一层
        setWidth(width);
        setHeight(height);
        setContentView(contentView);
        setBackgroundDrawable(new ColorDrawable(0x00000000));
        setOutsideTouchable(true);
        setFocusable(true);
        setOnDismissListener(this);
    }

    public void show(View anchorView) {
        int windowPos[] = calculatePopWindowPos(anchorView, contentView);

        showAtLocation(anchorView, Gravity.TOP | Gravity.END
                , windowPos[0], windowPos[1]);

        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = 0.7f;
        context.getWindow().setAttributes(lp);

    }

    /**
     * @param anchorView  弹出window的view
     * @param contentView window的内容布局
     * @return window显示的左上角的xOff, yOff坐标
     */
    protected int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        final int anchorWidth = anchorView.getWidth();
        // 获取屏幕的高宽
        final int screenHeight = ScreenUtils.getScreenHeight(anchorView.getContext());
        final int screenWidth = ScreenUtils.getScreenWidth(anchorView.getContext());
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        int windowHeight = contentView.getMeasuredHeight();
        int windowWidth = mWidth > 0 ? mWidth : contentView.getMeasuredWidth();

        // 判断需要向上弹出还是向下弹出,如果要改变弹出策略,改变此处即可
        // 目前是根据屏幕的一半进行判断
        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < screenHeight / 2);

        // 判断需要向左弹出还是向右弹出
//        final boolean isNeedShowLeft = (anchorLoc[0] > windowWidth);

//        windowPos[0] = isNeedShowLeft ?
//                anchorLoc[0] - windowWidth : anchorLoc[0] + anchorWidth;

        windowPos[0] = (screenWidth - windowWidth) / 2;

        windowPos[1] = isNeedShowUp ?
                anchorLoc[1] - windowHeight - PADDING : anchorLoc[1] + anchorHeight + PADDING;

        return windowPos;
    }

    @Override
    public void onDismiss() {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = 1f;
        context.getWindow().setAttributes(lp);
    }
}
