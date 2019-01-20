package com.liu.get.e_commerceproject.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.adapter.ViewPager_Adapter_Main;
import com.liu.get.e_commerceproject.base.BaseActivity;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;

/**
 * 第一次进入app的页面
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager welcome_viewpager;
    private Button welcome_start;

    public void initView() {
        //屏幕适配
        ScreenAdapterTools.getInstance().reset(this);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        welcome_viewpager = (ViewPager) findViewById(R.id.welcome_viewpager);
        welcome_start = (Button) findViewById(R.id.welcome_start);
        welcome_start.setOnClickListener(this);

        welcome_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 2) {
                    welcome_start.setVisibility(View.VISIBLE);
                } else {
                    welcome_start.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        welcome_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jump();
            }
        });
    }

    public void initData() {

        SharedPreferences first_time_into = getSharedPreferences("First_time_into", MODE_PRIVATE);

        if (first_time_into.getBoolean("isFirst", false)) {
            Jump();
        } else {
            SharedPreferences.Editor edit = first_time_into.edit();
            edit.putBoolean("isFirst", true);
           edit.commit();
        }

        ArrayList<Integer> list = new ArrayList<>();

        list.add(R.drawable.welcome1);
        list.add(R.drawable.welcome2);
        list.add(R.drawable.welcome3);

        welcome_viewpager.setAdapter(new ViewPager_Adapter_Main(MainActivity.this, list));

    }

    @Override
    public int getContent() {
        return R.layout.activity_main;
    }


    private void Jump() {
        startActivity(new Intent(MainActivity.this, GuideActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.welcome_start:

                break;
        }
    }
}
