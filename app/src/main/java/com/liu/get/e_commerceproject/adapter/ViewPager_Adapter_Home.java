package com.liu.get.e_commerceproject.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.bean.Banner_DataBean;


import java.util.List;


public class ViewPager_Adapter_Home extends PagerAdapter {
    List<Banner_DataBean.ResultBean> list;
    Context mContext;
    public ViewPager_Adapter_Home(Context con,List<Banner_DataBean.ResultBean> resultBeans){
        mContext = con;
        list = resultBeans;
    }

    public void setList(List<Banner_DataBean.ResultBean> beans) {
        list.clear();
        list.addAll(beans);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 1000;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

       // SimpleDraweeView simpleDraweeView = new SimpleDraweeView(mContext);

       // simpleDraweeView.setImageURI(list.get(position).getImageUrl());
      //  container.addView(simpleDraweeView);

       // return simpleDraweeView;
        View view = View.inflate(mContext, R.layout.viewpage_show_item,null);

        SimpleDraweeView homefragment_viewpager_sim = view.findViewById(R.id.viewpage_item);
                            //list.get(position%list.size).getImageUrl()
        homefragment_viewpager_sim.setImageURI(Uri.parse(list.get(position%list.size()).getImageUrl()));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
