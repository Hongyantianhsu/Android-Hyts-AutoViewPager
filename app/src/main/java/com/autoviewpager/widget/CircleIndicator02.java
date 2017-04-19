package com.autoviewpager.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * 轮播图圆点指示器111
 * Created by sunyan on 17/4/17.
 */

public class CircleIndicator02 extends View {

    private Paint paint;
    private int count = 3;
    private int select = 0;
    public CircleIndicator02(Context context) {
        super(context);
        init();
    }

    public CircleIndicator02(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        int width = getWidth();
        int height = getHeight();
        int r = 10;//圆半径
        int y = height/2;//圆y坐标
        int padding = 10;//圆点之间的间隔
        int startX = (width - 2*r*(count+1) - padding*count)/2;
        for (int i = 0 ; i < count ; i++){
            if (select == i){
                paint.setColor(Color.RED);
                canvas.drawCircle(startX+r+(2*r+padding)*i , y , r*1.5f , paint);
            }else{
                paint.setColor(Color.GRAY);
                canvas.drawCircle(startX+r+(2*r+padding)*i , y , r , paint);
            }
        }
        canvas.restore();
    }

    public void setViewPager(ViewPager vp , final int size){
        int count = size;
        this.count = count;
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                select = position % size;
                invalidate();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
