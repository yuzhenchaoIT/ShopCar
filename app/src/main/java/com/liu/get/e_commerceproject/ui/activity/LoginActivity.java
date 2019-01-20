package com.liu.get.e_commerceproject.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.base.BaseActivity;
import com.liu.get.e_commerceproject.bean.EventBusData;
import com.liu.get.e_commerceproject.bean.Login_Data;
import com.liu.get.e_commerceproject.net.AllUrl;
import com.liu.get.e_commerceproject.presenter.MyPLoginInterface;
import com.liu.get.e_commerceproject.presenter.MyPersenter;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;





/**
 * 登陆页面
 *     登陆逻辑
 *     点击进入到注册页
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    //控件
    private EditText login_edit_number;
    private EditText login_edit_pwd;
    private ImageView login_image_eye;
    private CheckBox login_checkbox_rember;
    private TextView login_text_registered;
    private Button login_button_login;
    private MyPersenter myPersenter;

    private Gson gson;
    private SharedPreferences mSpf;

    public void initView() {


        //屏幕适配
        ScreenAdapterTools.getInstance().reset(this);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        //EventBus初始化
        EventBus.getDefault().register(this);

        //初始化SharedPreferences
        mSpf = this.getSharedPreferences("RemberPwd", MODE_PRIVATE);

        //初始化P层
        myPersenter = new MyPersenter();

        //发现控件
        login_edit_number = (EditText) findViewById(R.id.login_edit_number);
        login_edit_pwd = (EditText) findViewById(R.id.login_edit_pwd);
        login_image_eye = (ImageView) findViewById(R.id.login_image_eye);
        login_checkbox_rember = (CheckBox) findViewById(R.id.login_checkbox_rember);
        login_text_registered = (TextView) findViewById(R.id.login_text_registered);
        login_button_login = (Button) findViewById(R.id.login_button_login);

        login_button_login.setOnClickListener(this);
        login_text_registered.setOnClickListener(this);

       // 自动登陆
        if (mSpf.getBoolean("Rember", false)) {
            login_edit_number.setText(mSpf.getString("phone", ""));
            login_edit_pwd.setText(mSpf.getString("pwd", ""));
            login_checkbox_rember.setChecked(mSpf.getBoolean("Rember", false));
            LoginButton();

        } else {
            //Toast.makeText(this,""+mSpf.getBoolean("Rember",false),Toast.LENGTH_SHORT).show();
        }
        //密码不再隐藏
        login_image_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_edit_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
        });
    }

    public void initData() {//初始化数据
        gson = new Gson();
    }

    @Override
    public int getContent() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button_login:
                //登录按钮
                LoginButton();
                break;
            case R.id.login_text_registered:
                startActivity(new Intent(LoginActivity.this, RegisteredActivity.class));
                break;



        }
    }



    private void LoginButton() {
        //登陆   请求网络的逻辑
        String number = login_edit_number.getText().toString();
        String pwd = login_edit_pwd.getText().toString();
        if (myPersenter.submit(number, pwd)) {
            Map<String, String> map = new HashMap<>();
            map.put("phone", number);
            map.put("pwd", pwd);
            //判断  输入内容的正确性
            myPersenter.LoginAndRegistere(AllUrl.Login_Url, map, new MyPLoginInterface() {
                @Override
                public void HttpFailure() {
                    Toast.makeText(LoginActivity.this, "HttpFailure", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void HttpResponse(String json) {
                    Login_Data login_data = gson.fromJson(json, Login_Data.class);
                    if (login_data.getMessage().equals("登录成功")) {
                        editadd(login_data);
                        Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, ShelfActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "账号密码错误", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(this, "请写入正确格式的账号密码", Toast.LENGTH_SHORT).show();
        }
    }
    //EventBus  获取刚刚注册的账号密码
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(EventBusData data) {
        login_edit_number.setText(data.num);
        login_edit_pwd.setText(data.pwd);
    }
    //添加到SharedPreferences 以便于后边也免得使用
    private void editadd(Login_Data login_data) {
        SharedPreferences.Editor edit = mSpf.edit();
        Log.e("ASD", "" + login_data.getResult().toString());
        edit.putBoolean("Rember", login_checkbox_rember.isChecked());
        edit.putString("HeadPic", login_data.getResult().getHeadPic());
        edit.putString("nickName", login_data.getResult().getNickName());
        edit.putString("phone", login_data.getResult().getPhone());
        edit.putString("pwd", login_edit_pwd.getText().toString());
        edit.putString("sessionId", login_data.getResult().getSessionId());
        edit.putInt("sex", login_data.getResult().getSex());
        edit.putInt("userId", login_data.getResult().getUserId());
        edit.putString("status",login_data.getStatus());
        edit.commit();
    }


}
