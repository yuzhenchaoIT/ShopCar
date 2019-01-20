package com.liu.get.e_commerceproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.net.AllUrl;
import com.liu.get.e_commerceproject.presenter.MyPLoginInterface;
import com.liu.get.e_commerceproject.presenter.MyPersenter;

import java.util.HashMap;
import java.util.Map;

public class PayActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup pay_radiogroup_group;
    private Button pay_radiogroup_btn;
    private TextView pay_textview_biaoshi;
    private MyPersenter mMyPersenter;
    private String sessionId;
    private int userId;
    private RadioButton pay_radiogroup_child1;
    private RadioButton pay_radiogroup_child2;
    private RadioButton pay_radiogroup_child3;
    private Button pay_layout_return;
    private Button pay_layout_fanhui;
    private LinearLayout pay_cheng_show;
    private Button pay_bai_return;
    private LinearLayout pay_bai_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        mMyPersenter = new MyPersenter();
        SharedPreferences remberPwd = getSharedPreferences("RemberPwd", Context.MODE_PRIVATE);
        sessionId = remberPwd.getString("sessionId", "");
        userId = remberPwd.getInt("userId", 0);
        Intent intent = getIntent();
        final String orderId = intent.getStringExtra("orderId");
        final String moneyey = intent.getStringExtra("moneyey");
        initView();
        pay_radiogroup_btn.setText("余额支付" + moneyey + "元");
        pay_textview_biaoshi.setText("1");
        pay_radiogroup_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.pay_radiogroup_child1:
                        pay_textview_biaoshi.setText("1");
                        pay_radiogroup_btn.setText("余额支付" + moneyey + "元");
                        break;
                    case R.id.pay_radiogroup_child2:
                        pay_textview_biaoshi.setText("2");
                        pay_radiogroup_btn.setText("微信支付" + moneyey + "元");
                        break;
                    case R.id.pay_radiogroup_child3:
                        pay_textview_biaoshi.setText("3");
                        pay_radiogroup_btn.setText("支付宝支付" + moneyey + "元");
                        break;
                }
            }
        });
        pay_radiogroup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int biao = Integer.valueOf(pay_textview_biaoshi.getText().toString().trim());
                switch (biao) {
                    case 1:
                        BalancePay(biao, orderId);
                        break;
                    case 2:
                        WechatPay(biao, orderId);
                        break;
                    case 3:
                        Alipay(biao, orderId);
                        break;
                }
            }
        });
        pay_layout_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pay_layout_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pay_bai_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pay_bai_show.setVisibility(View.GONE);
            }
        });
    }

    private void Alipay(int biao, String orderId) {
        Toast.makeText(PayActivity.this, "您还未开启支付宝支付", Toast.LENGTH_SHORT).show();
        pay_bai_show.setVisibility(View.VISIBLE);
    }

    private void WechatPay(int biao, String orderId) {
        Toast.makeText(PayActivity.this, "您还未开启微信支付", Toast.LENGTH_SHORT).show();
        pay_bai_show.setVisibility(View.VISIBLE);
    }

    private void BalancePay(int biao, String orderId) {
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderId);
        map.put("payType", biao + "");
        mMyPersenter.PostHeatHttp(AllUrl.PAYORDER_URL, map, userId + "", sessionId, new MyPLoginInterface() {
            @Override
            public void HttpFailure() {
                Toast.makeText(PayActivity.this, "请求服务器失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void HttpResponse(String json) {
                if (json.equals("{\"message\":\"支付成功\",\"status\":\"0000\"}")) {
                    Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    pay_cheng_show.setVisibility(View.VISIBLE);
                } else if (json.equals("{\"message\":\"该订单已支付\",\"status\":\"1001\"}")) {
                    Toast.makeText(PayActivity.this, "该订单已支付", Toast.LENGTH_SHORT).show();
                    pay_bai_show.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    pay_bai_show.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initView() {
        pay_radiogroup_group = (RadioGroup) findViewById(R.id.pay_radiogroup_group);
        pay_radiogroup_btn = (Button) findViewById(R.id.pay_radiogroup_btn);

        pay_radiogroup_btn.setOnClickListener(this);
        pay_textview_biaoshi = (TextView) findViewById(R.id.pay_textview_biaoshi);
        pay_radiogroup_child1 = (RadioButton) findViewById(R.id.pay_radiogroup_child1);
        pay_radiogroup_child1.setOnClickListener(this);
        pay_radiogroup_child2 = (RadioButton) findViewById(R.id.pay_radiogroup_child2);
        pay_radiogroup_child2.setOnClickListener(this);
        pay_radiogroup_child3 = (RadioButton) findViewById(R.id.pay_radiogroup_child3);
        pay_radiogroup_child3.setOnClickListener(this);
        pay_layout_return = (Button) findViewById(R.id.pay_layout_return);
        pay_layout_return.setOnClickListener(this);
        pay_layout_fanhui = (Button) findViewById(R.id.pay_layout_fanhui);
        pay_layout_fanhui.setOnClickListener(this);
        pay_cheng_show = (LinearLayout) findViewById(R.id.pay_cheng_show);
        pay_cheng_show.setOnClickListener(this);
        pay_bai_return = (Button) findViewById(R.id.pay_bai_return);
        pay_bai_return.setOnClickListener(this);
        pay_bai_show = (LinearLayout) findViewById(R.id.pay_bai_show);
        pay_bai_show.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pay_radiogroup_btn:

                break;
            case R.id.pay_layout_return:
                break;
            case R.id.pay_layout_fanhui:
                break;
            case R.id.pay_bai_return:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMyPersenter = null;
    }
}
