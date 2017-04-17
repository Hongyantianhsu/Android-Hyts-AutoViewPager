package com.autoviewpager.widget;

/**
 * Created by sunyan on 17/4/17.
 */

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * CustomDurationScroller
 */
public class CustomDurationScroller extends Scroller {

    private int mScrollDuration = 1;

    public CustomDurationScroller(Context context) {
        super(context);
    }

    public CustomDurationScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    /**
     * Set the factor by which the duration will change
     */
    public void setScrollDurationFactor(int duration) {
        this.mScrollDuration = duration;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration);
    }
}

