package com.autoviewpager.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * 自定义Scroller，用于调节Viewpager切换时的速度，避免闪屏
 * Created by sunyan on 17/4/10.
 */

public class MyScroller extends Scroller {

    //切换界面滑动速度
    private int mScrollDuration = 1;
    public MyScroller(Context context) {
        super(context);
    }

    public MyScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public MyScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration);
    }

    public void setScrollDuration(int duration){
        this.mScrollDuration = duration;
    }

}
