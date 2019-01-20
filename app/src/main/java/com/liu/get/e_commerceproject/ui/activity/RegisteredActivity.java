package com.liu.get.e_commerceproject.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.base.BaseActivity;
import com.liu.get.e_commerceproject.bean.EventBusData;
import com.liu.get.e_commerceproject.net.AllUrl;
import com.liu.get.e_commerceproject.presenter.MyPLoginInterface;
import com.liu.get.e_commerceproject.presenter.MyPersenter;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;




/**
 * 注册页面
 *         注册的逻辑
 */
public class RegisteredActivity extends BaseActivity implements View.OnClickListener {
    //控件
    private ImageView registered_image_return;
    private EditText registered_edit_number;
    private EditText registered_edit_code;
    private TextView registered_text_getcode;
    private EditText registered_edit_pwd;
    private ImageView registered_image_eye;
    private Button registered_button_registered;
    private MyPersenter myPersenter;
    private TextView registered_text_return;



    public void initView() {
        //屏幕适配
        ScreenAdapterTools.getInstance().reset(this);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        //P层
        myPersenter = new MyPersenter();

        registered_image_return = (ImageView) findViewById(R.id.registered_image_return);
        registered_edit_number = (EditText) findViewById(R.id.registered_edit_number);
        registered_edit_code = (EditText) findViewById(R.id.registered_edit_code);
        registered_text_getcode = (TextView) findViewById(R.id.registered_text_getcode);
        registered_edit_pwd = (EditText) findViewById(R.id.registered_edit_pwd);
        registered_image_eye = (ImageView) findViewById(R.id.registered_image_eye);
        registered_button_registered = (Button) findViewById(R.id.registered_button_registered);

        registered_button_registered.setOnClickListener(this);
        registered_text_return = (TextView) findViewById(R.id.registered_text_return);
        registered_text_return.setOnClickListener(this);
        registered_image_return.setOnClickListener(this);
        registered_text_getcode.setOnClickListener(this);
        //使得密码可见
        registered_image_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registered_edit_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
        });
    }

    public void initData() {

    }

    @Override
    public int getContent() {
        return R.layout.activity_registered;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registered_button_registered:
                Register();
                break;
            case R.id.registered_text_getcode:
                //发送短信验证码
                SenMSM();
                break;
            case R.id.registered_image_return:
                //返回登陆页
                finish();
                break;
            case R.id.registered_text_return:
                //返回登陆页
                finish();
                break;
        }
    }

    private void Register() {
        final String number = registered_edit_number.getText().toString();
        final String pwd = registered_edit_pwd.getText().toString();
        if (myPersenter.submit(number, pwd)) {
            String code = registered_edit_code.getText().toString().trim();
            if(!TextUtils.isEmpty(code)){
                //判断短信验证码

            }else{
                Toast.makeText(this,"验证码不能为空",Toast.LENGTH_SHORT).show();
                return;
            }
            Map<String,String> map = new HashMap<>();
            map.put("phone",number);
            map.put("pwd",pwd);
            myPersenter.LoginAndRegistere(AllUrl.Registered_Url, map, new MyPLoginInterface() {
                @Override
                public void HttpFailure() {
                    Toast.makeText(RegisteredActivity.this, "服务器连接失败", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void HttpResponse(String json) {
                    Log.e("WD",""+json);
                    if(json.equals("{\"message\":\"该手机号已注册，不能重复注册！\",\"status\":\"1001\"}")){
                        Toast.makeText(RegisteredActivity.this, "该手机号已注册", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(RegisteredActivity.this, "欢迎使用", Toast.LENGTH_SHORT).show();
                        EventBus.getDefault().post(new EventBusData(number,pwd));
                    }
                }
            });

        } else {
            Toast.makeText(this, "请写入正确格式的账号密码", Toast.LENGTH_SHORT).show();
        }
    }

    private void SenMSM() {
        //防止用户多次点击
        if (TextUtils.isEmpty(registered_edit_code.getText().toString().trim())) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
    }





}
