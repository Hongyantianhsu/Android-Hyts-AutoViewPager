package com.autoviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.autoviewpager.widget.AutoViewPager;
import com.autoviewpager.widget.CircleIndicator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunyan on 17/4/17.
 */

public class Activity02 extends AppCompatActivity {

    private List<Integer> resIns = new ArrayList<>();
    private AutoViewPager vp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity02_viewpager);

        resIns.add(R.mipmap.zhanxin01);
        resIns.add(R.mipmap.zhanxin02);
        resIns.add(R.mipmap.zhanxin03);
        resIns.add(R.mipmap.zhanxin04);

        vp = (AutoViewPager) findViewById(R.id.vp);
        vp.setAdapter(new AutoPagerAdapter());
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        CircleIndicator indicator = new CircleIndicator(this,vp,ll,resIns.size());
        vp.startAutoScroll();
        vp.addOnPageChangeListener(indicator);
    }

    private class AutoPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = View.inflate(Activity02.this,R.layout.item_vp,null);
            ImageView iv = (ImageView) v.findViewById(R.id.iv);
            int index = position % resIns.size();
            Picasso.with(Activity02.this).load(resIns.get(index)).into(iv);
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vp.stopAutoScroll();
    }
}
