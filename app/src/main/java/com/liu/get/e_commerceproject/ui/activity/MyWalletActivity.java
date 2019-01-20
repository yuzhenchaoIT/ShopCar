package com.liu.get.e_commerceproject.ui.activity;

import android.content.SharedPreferences;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.adapter.ListView_Adapter_Wallet;
import com.liu.get.e_commerceproject.base.BaseActivity;
import com.liu.get.e_commerceproject.bean.MyWalletBeans;
import com.liu.get.e_commerceproject.net.AllUrl;
import com.liu.get.e_commerceproject.presenter.MyPLoginInterface;
import com.liu.get.e_commerceproject.presenter.MyPersenter;

import java.util.ArrayList;

public class MyWalletActivity extends BaseActivity {

    private TextView wall_text_have;
    private ListView wall_text_recording;
    private MyPersenter mMyPersenter;
    int page = 1;

    public void initView() {
        wall_text_have = (TextView) findViewById(R.id.wall_text_have);
        wall_text_recording = (ListView) findViewById(R.id.wall_text_recording);
    }

    @Override
    public void initData() {
        mMyPersenter = new MyPersenter();
        //获取全局保存的主要信息
        SharedPreferences remberPwd = getSharedPreferences("RemberPwd", MODE_PRIVATE);
        final String sessionId = remberPwd.getString("sessionId", "");
        final int userId = remberPwd.getInt("userId", 0);
        mMyPersenter.HeadGET(AllUrl.WALLET_URL + "?page=" + page + "&count=20", userId + "", sessionId, new MyPLoginInterface() {
            @Override
            public void HttpFailure() {
                Toast.makeText(MyWalletActivity.this,"请求服务器失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void HttpResponse(String json) {
                Gson gson = new Gson();
                MyWalletBeans myWalletBeans = gson.fromJson(json, MyWalletBeans.class);
                if(json.equals("{\"message\":\"请先登陆\",\"status\":\"0001\"}")||myWalletBeans.getResult()==null){
                    Toast.makeText(MyWalletActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                    return;
                }
                wall_text_have.setText(myWalletBeans.getResult().getBalance()+"");
                ArrayList<MyWalletBeans.ResultBean.Redata> detailList = myWalletBeans.getResult().getDetailList();
                if(detailList.size() == 0){
                    Toast.makeText(MyWalletActivity.this,"暂时没有消费记录去消费吧",Toast.LENGTH_SHORT).show();
                }else{
                    ListView_Adapter_Wallet adapter = new ListView_Adapter_Wallet(MyWalletActivity.this, detailList);
                    wall_text_recording.setAdapter(adapter);
                }
            }
        });
    }

    @Override
    public int getContent() {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMyPersenter = null;
    }
}
