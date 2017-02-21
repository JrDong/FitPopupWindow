package com.djr.fitpopupwindow;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.djr.fitpopupwindow.utils.SharpCornerView;

/**
 * Created by DongJr on 2017/2/21.
 */

public class ListHolder extends RecyclerView.ViewHolder {

    SharpCornerView ivRemove;

    public ListHolder(View itemView) {
        super(itemView);
        ivRemove = (SharpCornerView) itemView.findViewById(R.id.iv_remove);
    }


}
