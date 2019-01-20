package com.liu.get.e_commerceproject.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.net.AllUrl;
import com.liu.get.e_commerceproject.presenter.MyPLoginInterface;
import com.liu.get.e_commerceproject.presenter.MyPersenter;

import java.util.HashMap;
import java.util.Map;

public class MyAddressChangeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText myaddress_update_name;
    private EditText myaddress_update_phone;
    private EditText myaddress_update_address;
    private EditText myaddress_update_zip;
    private Button myaddress_update_startchange;
    private String id,realName,phone,address,zipCode;
    private MyPersenter mMyPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address_change);
        initView();
        initData();
    }

    private void initView() {
        myaddress_update_name = (EditText) findViewById(R.id.myaddress_update_name);
        myaddress_update_phone = (EditText) findViewById(R.id.myaddress_update_phone);
        myaddress_update_address = (EditText) findViewById(R.id.myaddress_update_address);
        myaddress_update_zip = (EditText) findViewById(R.id.myaddress_update_zip);
        myaddress_update_startchange = (Button) findViewById(R.id.myaddress_update_startchange);

        myaddress_update_startchange.setOnClickListener(this);
    }

    private void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        realName = intent.getStringExtra("realName");
        phone = intent.getStringExtra("phone");
        address = intent.getStringExtra("address");
        zipCode = intent.getStringExtra("zipCode");

        myaddress_update_name.setText(realName);
        myaddress_update_phone.setText(phone);
        myaddress_update_address.setText(address);
        myaddress_update_zip.setText(zipCode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myaddress_update_startchange:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String getname = myaddress_update_name.getText().toString().trim();
        String getphone = myaddress_update_phone.getText().toString().trim();
        String getaddress = myaddress_update_address.getText().toString().trim();
        String getzip = myaddress_update_zip.getText().toString().trim();
        if (TextUtils.isEmpty(getname)) {
            Toast.makeText(this, "请输入收货人姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        //正则验证
        final String REGEX_MOBILE ="^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        if (TextUtils.isEmpty(getphone)) {
            Toast.makeText(this, "请输入收货人手机号码", Toast.LENGTH_SHORT).show();
            return;
        }else if(!getphone.matches(REGEX_MOBILE)){
            Toast.makeText(this, "请输入正确格式手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(getaddress)) {
            Toast.makeText(this, "请输入收货地址", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(getzip)) {
            Toast.makeText(this, "请输入邮政编码", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences remberPwd = getSharedPreferences("RemberPwd", MODE_PRIVATE);
        String sessionId = remberPwd.getString("sessionId", "");
        int userId = remberPwd.getInt("userId", 0);

        mMyPersenter = new MyPersenter();
        Map<String, String> map = new HashMap<>();
        map.put("id",id);
        map.put("realName",getname);
        map.put("phone",getphone);
        map.put("address",getaddress);
        map.put("zipCode",getzip);

        mMyPersenter.PutHttp(AllUrl.UPDATEADDRESS_URL, map, userId + "", sessionId, new MyPLoginInterface() {
            @Override
            public void HttpFailure() {
                Toast.makeText(MyAddressChangeActivity.this,"请求服务器失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void HttpResponse(String json) {
                if(json.equals("{\"message\":\"修改成功\",\"status\":\"0000\"}")){
                    Toast.makeText(MyAddressChangeActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(MyAddressChangeActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMyPersenter=null;
    }
}
