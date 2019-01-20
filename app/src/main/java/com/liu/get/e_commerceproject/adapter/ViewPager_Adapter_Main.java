package com.liu.get.e_commerceproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.util.ArrayList;


public class ViewPager_Adapter_Main extends PagerAdapter {
    Context mContext;
    ArrayList<Integer> list;
    public ViewPager_Adapter_Main(Context con, ArrayList<Integer> images){
        mContext = con;
        list =new ArrayList<>();
        list.addAll(images);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);

        //imageView.setImageResource(list.get(position));

       // imageView.setScaleType(ImageView.ScaleType.FIT_XY);

       // container.addView(imageView);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView .setLayoutParams(layoutParams);
        (container).addView(imageView);
        Glide.with(mContext)
                .load(list.get(position))
                .centerCrop()
                .crossFade()
                .into(imageView);
        return imageView;



    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ImageView imageView = (ImageView) object;
        if (imageView == null)
            return;
        Glide.clear(imageView);		//核心，解决OOM
        ((ViewPager) container).removeView(imageView);

    }
}
