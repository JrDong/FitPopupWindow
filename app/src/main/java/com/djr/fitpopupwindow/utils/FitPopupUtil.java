package com.djr.fitpopupwindow.utils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.djr.fitpopupwindow.R;


/**
 * Created by DongJr on 2017/2/27.
 */

public class FitPopupUtil implements View.OnClickListener {

    private View contentView;

    private Activity context;

    private TextView reason1;
    private TextView reason2;
    private TextView reason3;

    private TextView btnCommit;

    private boolean reason1Selected;
    private boolean reason2Selected;
    private boolean reason3Selected;

    private FitPopupWindow mPopupWindow;

    private OnCommitClickListener listener;

    public FitPopupUtil(Activity context) {

        this.context = context;

        LayoutInflater inflater = LayoutInflater.from(context);
        contentView = inflater.inflate(R.layout.layout_popupwindow, null);
        reason1 = (TextView) contentView.findViewById(R.id.tv_reason1);
        reason2 = (TextView) contentView.findViewById(R.id.tv_reason2);
        reason3 = (TextView) contentView.findViewById(R.id.tv_reason3);
        btnCommit = (TextView) contentView.findViewById(R.id.btn_commit);


        reason1.setOnClickListener(this);
        reason2.setOnClickListener(this);
        reason3.setOnClickListener(this);


    }

    public void setOnClickListener(OnCommitClickListener listener) {
        this.listener = listener;
    }

    /**
     * 弹出自适应位置的popupwindow
     *
     * @param anchorView 目标view
     */
    public View showPopup(View anchorView) {
        if (mPopupWindow == null) {
            mPopupWindow = new FitPopupWindow(context,
                    ScreenUtils.getScreenWidth(context) - DensityUtils.dp2px(20),
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }

        mPopupWindow.setView(contentView, anchorView);
        mPopupWindow.show();
        return contentView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_reason1:
                reason1Selected = !reason1Selected;
                reason1.setSelected(reason1Selected);
                break;
            case R.id.tv_reason2:
                reason2Selected = !reason2Selected;
                reason2.setSelected(reason2Selected);
                break;
            case R.id.tv_reason3:
                reason3Selected = !reason3Selected;
                reason3.setSelected(reason3Selected);
                break;
            case R.id.btn_commit:
                if (listener != null) {
                    listener.onClick(getReason());
                }
                mPopupWindow.dismiss();
                break;
        }

        if (reason1Selected || reason2Selected || reason3Selected) {
            btnCommit.setOnClickListener(this);
            btnCommit.setText("确定");
        } else {
            btnCommit.setOnClickListener(null);
            btnCommit.setText("不感兴趣");
        }
    }

    public String getReason() {
        String content1 = reason1Selected ? reason1.getText().toString() + "," : "";
        String content2 = reason2Selected ? reason2.getText().toString() + "," : "";
        String content3 = reason3Selected ? reason3.getText().toString() + "," : "";

        String s = content1 + content2 + content3;
        return s.substring(0, s.length() - 1);

    }

    public interface OnCommitClickListener {
        void onClick(String reason);
    }

}
