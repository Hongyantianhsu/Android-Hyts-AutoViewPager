package com.autoviewpager.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;

import java.lang.reflect.Field;

/**
 * Created by sunyan on 17/4/17.
 */

public class AutoViewPager extends ViewPager {
    private int mScrollDuration = 2000;
    private Handler handler;

    public AutoViewPager(Context context) {
        super(context);
        init();
    }

    public AutoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private int PAGE = 0;
    public static final int AUTO_SCRLL = 0;
    private static final int DELAY_AUTOSCRLL = 2000;
    private void init() {
        setViewPagerScroller();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case AUTO_SCRLL:
                        setCurrentItem(PAGE);
                        PAGE++;
                        startAutoScroll();
                        break;
                }
            }
        };

    }

    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        super.onPageScrolled(position, offset, offsetPixels);
    }

    /**
     * 开始自动轮播
     */
    public void startAutoScroll(){
        handler.sendEmptyMessageDelayed(AUTO_SCRLL,DELAY_AUTOSCRLL);
    }

    /**
     * 停止轮播
     */
    public void stopAutoScroll(){
        handler.removeMessages(AUTO_SCRLL);
    }

    /**
     * 设置Viewpager切换滑动的时间
     */
    private void setViewPagerScroller(){
        try {
            Field mScroller =  ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            MyScroller scroller = new MyScroller(getContext(),new AccelerateInterpolator());
            scroller.setScrollDuration(2000);
            scroller.setScrollDuration(mScrollDuration);
            mScroller.set(this,scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置MyScroller的滑动时间
     * @param duration
     */
    public void setScrollDuration(int duration){
        this.mScrollDuration = duration;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                stopAutoScroll();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_CANCEL:
                startAutoScroll();
                break;
            case MotionEvent.ACTION_UP:
                startAutoScroll();
                break;
        }
        return super.onTouchEvent(ev);
    }
}
