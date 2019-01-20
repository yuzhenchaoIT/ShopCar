package com.liu.get.e_commerceproject.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.adapter.ViewPager_Adapter_Shelf;
import com.liu.get.e_commerceproject.base.BaseFragment;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * 所有订单也的信息
 */
public class ListFragment extends BaseFragment {


    private TabLayout list_tab_switch;
    private ViewPager list_viewpager_switch;
    private ViewPager_Adapter_Shelf adapter;
    private ArrayList<Fragment> list;

    public void initView(View view) {
        //屏幕适配
        ScreenAdapterTools.getInstance().loadView(view);

        list_tab_switch = (TabLayout) view.findViewById(R.id.list_tab_switch);
        list_viewpager_switch = (ViewPager) view.findViewById(R.id.list_viewpager_switch);

        list = new ArrayList<>();

        list.add(new AllOrderFragment());
        list.add(new WaitPayFragment());
        list.add(new WaitReceivedFragment());
        list.add(new WaitEvaluationFragment());
        list.add(new CompleteFragment());

        adapter = new ViewPager_Adapter_Shelf(getChildFragmentManager(), list);

        list_viewpager_switch.setAdapter(adapter);

        for (int i = 0; i < list.size(); i++) {
            list_tab_switch.addTab(list_tab_switch.newTab());
        }

        list_tab_switch.setupWithViewPager(list_viewpager_switch, true);

        list_tab_switch.getTabAt(0).setIcon(R.mipmap.common_icon_alist_nxxhdpi).setText("全部");
        list_tab_switch.getTabAt(1).setIcon(R.mipmap.common_icon_pay_n).setText("待付款");
        list_tab_switch.getTabAt(2).setIcon(R.mipmap.common_icon_receive_n).setText("待收货");
        list_tab_switch.getTabAt(3).setIcon(R.mipmap.commom_icon_comment_n).setText("待评价");
        list_tab_switch.getTabAt(4).setIcon(R.mipmap.common_icon_finish_n).setText("已完成");

    }

    @Override
    public void initData(View view) {

    }

    @Override
    public int getContent() {
        return R.layout.fragment_list;
    }
}
