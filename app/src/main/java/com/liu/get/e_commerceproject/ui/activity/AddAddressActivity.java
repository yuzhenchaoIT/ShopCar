package com.liu.get.e_commerceproject.ui.activity;

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

/**
 * 新增收获地址
 *      添加新的收货地址
 */
public class AddAddressActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText add_address_name;
    private EditText add_address_phone;
    private EditText add_address_address;
    private EditText add_address_zip;
    private Button add_address_startchange;
    private MyPersenter mMyPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        initView();
    }

    private void initView() {
        add_address_name = (EditText) findViewById(R.id.add_address_name);
        add_address_phone = (EditText) findViewById(R.id.add_address_phone);
        add_address_address = (EditText) findViewById(R.id.add_address_address);
        add_address_zip = (EditText) findViewById(R.id.add_address_zip);
        add_address_startchange = (Button) findViewById(R.id.add_address_startchange);

        add_address_startchange.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_address_startchange:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String name = add_address_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入收货人姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        //正则验证
        final String REGEX_MOBILE ="^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        String phone = add_address_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入收货人手机号码", Toast.LENGTH_SHORT).show();
            return;
        }else if(!phone.matches(REGEX_MOBILE)){
            Toast.makeText(this, "请输入正确格式手机号码", Toast.LENGTH_SHORT).show();
            return;
        }

        String address = add_address_address.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "请输入收货地址", Toast.LENGTH_SHORT).show();
            return;
        }

        String zip = add_address_zip.getText().toString().trim();
        if (TextUtils.isEmpty(zip)) {
            Toast.makeText(this, "请输入邮政编码", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences remberPwd = getSharedPreferences("RemberPwd", MODE_PRIVATE);
        String sessionId = remberPwd.getString("sessionId", "");
        int userId = remberPwd.getInt("userId", 0);

        mMyPersenter = new MyPersenter();
        Map<String, String> map = new HashMap<>();
        map.put("realName",name);
        map.put("phone",phone);
        map.put("address",address);
        map.put("zipCode",zip);

        mMyPersenter.PostHeatHttp(AllUrl.ADDADDRESS_URL, map, userId + "", sessionId, new MyPLoginInterface() {
            @Override
            public void HttpFailure() {
                Toast.makeText(AddAddressActivity.this,"请求服务器失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void HttpResponse(String json) {
                if(json.equals("{\"message\":\"添加成功\",\"status\":\"0000\"}")){
                    Toast.makeText(AddAddressActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(AddAddressActivity.this,"添加失败",Toast.LENGTH_SHORT).show();
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
