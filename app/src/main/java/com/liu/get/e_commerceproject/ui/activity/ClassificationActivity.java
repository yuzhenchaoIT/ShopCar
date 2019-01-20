package com.liu.get.e_commerceproject.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.base.BaseActivity;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

/**
 * 分类页面  尚未使用
 */
public class ClassificationActivity extends BaseActivity {

    @Override
    public void initView() {
        //屏幕适配
        ScreenAdapterTools.getInstance().reset(this);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Toast.makeText(this,"分类页"+name,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initData() {

    }

    @Override
    public int getContent() {
        return R.layout.activity_classification;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
