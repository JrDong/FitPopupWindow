# FitPopupWindow
根据点击位置自适应弹出位置的PopupWindow,仿网易新闻/今日头条,"不感兴趣"弹框

## 效果图
![PopupWindow](/popupwindow.gif)

## 用法


	/**
	 * @param anchorView 目标view
	 * 
	 */	
	private void initPopup(View anchorView) {
        FitPopupUtil fitPopupUtil = new FitPopupUtil(this);
        fitPopupUtil.setOnClickListener(new FitPopupUtil.OnCommitClickListener() {
            @Override
            public void onClick(String reason) {
                Toast.makeText(MainActivity.this,reason,Toast.LENGTH_SHORT).show();
            }
        });
        fitPopupUtil.showPopup(anchorView);
    }
