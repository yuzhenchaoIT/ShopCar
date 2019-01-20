package com.liu.get.e_commerceproject.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.adapter.ViewPager_Adapter_Shelf;
import com.liu.get.e_commerceproject.ui.fragment.CircleFragment;
import com.liu.get.e_commerceproject.ui.fragment.HomeFragment;
import com.liu.get.e_commerceproject.ui.fragment.ListFragment;
import com.liu.get.e_commerceproject.ui.fragment.MineFragment;
import com.liu.get.e_commerceproject.ui.fragment.ShoppingcartFragment;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;

/**
 * 全局展示的一个切换   View pager和Fragment + Tablayout
 */
public class ShelfActivity extends AppCompatActivity {

    private ViewPager shelf_viewpager_switch;
    private TabLayout shelf_tab_switch;
    private ViewPager_Adapter_Shelf adapter;
    private ArrayList<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf);

        //屏幕适配
        ScreenAdapterTools.getInstance().reset(this);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        initView();

        initData();

        shelf_tab_switch.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TabChaged(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void TabChaged(TabLayout.Tab tab) {
        ImageView icon1 = shelf_tab_switch.getTabAt(0).getCustomView().findViewById(R.id.tab_icon_1);
        ImageView icon2 = shelf_tab_switch.getTabAt(1).getCustomView().findViewById(R.id.tab_icon_2);
        ImageView icon4 = shelf_tab_switch.getTabAt(3).getCustomView().findViewById(R.id.tab_icon_4);
        ImageView icon5 = shelf_tab_switch.getTabAt(4).getCustomView().findViewById(R.id.tab_icon_5);

        if (tab == shelf_tab_switch.getTabAt(0)) {
            icon1.setImageResource(R.mipmap.common_tab_btn_home_s);
            icon2.setImageResource(R.mipmap.common_tab_btn_circle_n);
            icon4.setImageResource(R.mipmap.common_tab_btn_list_n);
            icon5.setImageResource(R.mipmap.common_tab_btn_my_n);
        } else if (tab == shelf_tab_switch.getTabAt(1)) {
            icon1.setImageResource(R.mipmap.common_tab_btn_home_n);
            icon2.setImageResource(R.mipmap.common_tab_btn_circle_s);
            icon4.setImageResource(R.mipmap.common_tab_btn_list_n);
            icon5.setImageResource(R.mipmap.common_tab_btn_my_n);
        }else if (tab == shelf_tab_switch.getTabAt(2)) {
            icon1.setImageResource(R.mipmap.common_tab_btn_home_n);
            icon2.setImageResource(R.mipmap.common_tab_btn_circle_n);
            icon4.setImageResource(R.mipmap.common_tab_btn_list_n);
            icon5.setImageResource(R.mipmap.common_tab_btn_my_n);
        }else if (tab == shelf_tab_switch.getTabAt(3)) {
            icon1.setImageResource(R.mipmap.common_tab_btn_home_n);
            icon2.setImageResource(R.mipmap.common_tab_btn_circle_n);
            icon4.setImageResource(R.mipmap.common_tab_btn_list_s);
            icon5.setImageResource(R.mipmap.common_tab_btn_my_n);
        }else if (tab == shelf_tab_switch.getTabAt(4)) {
            icon1.setImageResource(R.mipmap.common_tab_btn_home_n);
            icon2.setImageResource(R.mipmap.common_tab_btn_circle_n);
            icon4.setImageResource(R.mipmap.common_tab_btn_list_n);
            icon5.setImageResource(R.mipmap.common_tab_btn_my_s);
        }
    }

    private void initData() {

    }

    private void initView() {
        shelf_viewpager_switch = (ViewPager) findViewById(R.id.shelf_viewpager_switch);
        shelf_tab_switch = (TabLayout) findViewById(R.id.shelf_tab_switch);

        list = new ArrayList<>();

        list.add(new HomeFragment());
        list.add(new CircleFragment());
        list.add(new ShoppingcartFragment());
        list.add(new ListFragment());
        list.add(new MineFragment());

        adapter = new ViewPager_Adapter_Shelf(getSupportFragmentManager(), list);

        shelf_viewpager_switch.setAdapter(adapter);

        for (int i = 0; i < list.size(); i++) {
            shelf_tab_switch.addTab(shelf_tab_switch.newTab());
        }

        shelf_tab_switch.setupWithViewPager(shelf_viewpager_switch,true);

        shelf_tab_switch.getTabAt(0).setCustomView(R.layout.tab_icon1);
        shelf_tab_switch.getTabAt(1).setCustomView(R.layout.tab_icon2);
        shelf_tab_switch.getTabAt(2).setCustomView(R.layout.tab_icon3);
        shelf_tab_switch.getTabAt(3).setCustomView(R.layout.tab_icon4);
        shelf_tab_switch.getTabAt(4).setCustomView(R.layout.tab_icon5);
        shelf_tab_switch.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
