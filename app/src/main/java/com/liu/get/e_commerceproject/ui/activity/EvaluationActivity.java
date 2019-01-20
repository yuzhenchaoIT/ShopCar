package com.liu.get.e_commerceproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.net.AllUrl;
import com.liu.get.e_commerceproject.presenter.MyPLoginInterface;
import com.liu.get.e_commerceproject.presenter.MyPersenter;

import java.util.HashMap;
import java.util.Map;

public class EvaluationActivity extends AppCompatActivity implements View.OnClickListener {

    private SimpleDraweeView evaluation_simple_image;
    private TextView evaluation_text_name;
    private TextView evaluation_text_price;
    private EditText evaluation_edit_evaluation;
    private CheckBox evaluation_check_Circle;
    private Button evaluation_btn_published;
    private String orderId;
    private String commodityId;
    private String commodityPic;
    private String commodityName;
    private String money;
    private MyPersenter mMyPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);
        initView();
        iniData();
    }

    private void initView() {
        evaluation_simple_image = (SimpleDraweeView) findViewById(R.id.evaluation_simple_image);
        evaluation_text_name = (TextView) findViewById(R.id.evaluation_text_name);
        evaluation_text_price = (TextView) findViewById(R.id.evaluation_text_price);
        evaluation_edit_evaluation = (EditText) findViewById(R.id.evaluation_edit_evaluation);
        evaluation_check_Circle = (CheckBox) findViewById(R.id.evaluation_check_Circle);
        evaluation_btn_published = (Button) findViewById(R.id.evaluation_btn_published);

        evaluation_btn_published.setOnClickListener(this);
    }

    private void iniData() {
        mMyPersenter = new MyPersenter();

        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        commodityId = intent.getStringExtra("commodityId");
        commodityPic = intent.getStringExtra("commodityPic");
        commodityName = intent.getStringExtra("commodityName");
        money = intent.getStringExtra("money");
        Log.e("评论", "orderId-" + orderId + "-commodityId-" + commodityId + "-commodityPic-" +
                commodityPic + "-commodityName-" + commodityName + "-money-" + money);
        String[] split = commodityPic.trim().split(",");
        evaluation_simple_image.setImageURI(split[0]);
        evaluation_text_name.setText(commodityName);
        evaluation_text_price.setText("￥" + money);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.evaluation_btn_published:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String evaluation = evaluation_edit_evaluation.getText().toString().trim();
        if (TextUtils.isEmpty(evaluation)) {
            Toast.makeText(this, "请在此处输入您对商品的评价", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences remberPwd = getSharedPreferences("RemberPwd", Context.MODE_PRIVATE);
        String sessionId = remberPwd.getString("sessionId", "");
        int userId = remberPwd.getInt("userId", 0);
        Map<String, String> map = new HashMap<>();
        map.put("commodityId", commodityId + "");
        map.put("orderId", orderId + "");
        map.put("content", evaluation + "");
        Log.e("评论", "" + map);
        mMyPersenter.PostHeatHttp(AllUrl.EVALUATION_URL, map, userId + "", sessionId, new MyPLoginInterface() {
            @Override
            public void HttpFailure() {
                Toast.makeText(EvaluationActivity.this, "请求服务器失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void HttpResponse(String json) {
                Log.e("评论", "" + json);
                Toast.makeText(EvaluationActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        if (evaluation_check_Circle.isChecked()) {
            mMyPersenter.PostHeatHttp(AllUrl.PUBLISH_CIRCLE_URL, map, userId + "", sessionId, new MyPLoginInterface() {
                @Override
                public void HttpFailure() {
                    Toast.makeText(EvaluationActivity.this, "请求服务器失败", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void HttpResponse(String json) {
                    Log.e("发布", "" + json);
                    Toast.makeText(EvaluationActivity.this, "发布圈子成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMyPersenter = null;
    }
}
