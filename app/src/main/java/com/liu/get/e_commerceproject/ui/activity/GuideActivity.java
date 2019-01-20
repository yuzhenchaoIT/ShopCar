package com.liu.get.e_commerceproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.base.BaseActivity;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

/**
 * 每一次进入应用都展示   此页面可以做为一个广告页面的展示
 */
public class GuideActivity extends BaseActivity {

    private ImageView guide_img_advertising;
    private TextView guide_text_countdown;
    int time = 6;

    private SharedPreferences into;

    public void initView() {
        guide_img_advertising = (ImageView) findViewById(R.id.guide_img_advertising);
        guide_text_countdown = (TextView) findViewById(R.id.guide_text_countdown);
        mHandler.sendEmptyMessageDelayed(1,0);
        //直接进入程序
        guide_text_countdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                into = getSharedPreferences("into", MODE_PRIVATE);

                if (into.getBoolean("isInto", false)) {
                    startActivity(new Intent(GuideActivity.this,ShelfActivity.class));
                    finish();
                    mHandler.removeCallbacksAndMessages(null);
                } else {
                    startActivity(new Intent(GuideActivity.this,LoginActivity.class));
                    finish();
                    mHandler.removeCallbacksAndMessages(null);
                    SharedPreferences.Editor edit = into.edit();
                    edit.putBoolean("isInto", true);
                    edit.commit();
                }




            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public int getContent() {
        return R.layout.activity_guide;
    }
    //程序倒计时  5S
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(time != 0){
                time--;
                guide_text_countdown.setText(time+"S");
                mHandler.sendEmptyMessageDelayed(1,1000);
            }else{
                into = getSharedPreferences("into", MODE_PRIVATE);
                if (into.getBoolean("isInto", false)) {
                    startActivity(new Intent(GuideActivity.this,ShelfActivity.class));
                    finish();
                    mHandler.removeCallbacksAndMessages(null);
                } else {
                    startActivity(new Intent(GuideActivity.this,LoginActivity.class));
                    finish();
                    mHandler.removeCallbacksAndMessages(null);
                    SharedPreferences.Editor edit = into.edit();
                    edit.putBoolean("isInto", true);
                    edit.commit();
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mHandler !=null){
            mHandler.removeCallbacksAndMessages(null);
            mHandler=null;
        }
    }
}
