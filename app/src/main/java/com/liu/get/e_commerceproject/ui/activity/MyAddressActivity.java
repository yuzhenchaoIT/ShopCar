package com.liu.get.e_commerceproject.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.adapter.RecycleView_Adapter_MyAddress;
import com.liu.get.e_commerceproject.base.BaseActivity;
import com.liu.get.e_commerceproject.bean.AddressBean;
import com.liu.get.e_commerceproject.net.AllUrl;
import com.liu.get.e_commerceproject.presenter.MyPLoginInterface;
import com.liu.get.e_commerceproject.presenter.MyPersenter;

import java.util.HashMap;
import java.util.Map;

/**
 * 我的收货地址  用于查询收货地址
 */
public class MyAddressActivity extends BaseActivity implements View.OnClickListener {

    private TextView myaddress_textview_ok;
    private RecyclerView myaddress_recycler_show;
    private Button myaddress_button_add;
    private MyPersenter mMyPersenter;

    public void initView() {
        myaddress_textview_ok = (TextView) findViewById(R.id.myaddress_textview_ok);
        myaddress_recycler_show = (RecyclerView) findViewById(R.id.myaddress_recycler_show);
        myaddress_button_add = (Button) findViewById(R.id.myaddress_button_add);

        myaddress_button_add.setOnClickListener(this);
        myaddress_textview_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
    }

    @Override
    public void initData() {
        //获取全局保存的主要信息
        SharedPreferences remberPwd = getSharedPreferences("RemberPwd", MODE_PRIVATE);
        final String sessionId = remberPwd.getString("sessionId", "");
        final int userId = remberPwd.getInt("userId", 0);
        //P层
        mMyPersenter = new MyPersenter();
        mMyPersenter.HeadGET(AllUrl.GETADDRESS_URL ,
                "" + userId, sessionId, new MyPLoginInterface() {
                    @Override
                    public void HttpFailure() {
                        Toast.makeText(MyAddressActivity.this, "获取收货地址失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void HttpResponse(String json) {
                        //Toast.makeText(MyFootprintActivity.this,""+json,Toast.LENGTH_SHORT).show();
                        Gson gson = new Gson();
                        AddressBean addressBean = gson.fromJson(json, AddressBean.class);
                        if(json.equals("{\"message\":\"请先登陆\",\"status\":\"0001\"}")||addressBean.getResult()==null){
                            Toast.makeText(MyAddressActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(addressBean.getResult()!=null){
                            RecycleView_Adapter_MyAddress recycleView_adapter_myAddress = new RecycleView_Adapter_MyAddress(MyAddressActivity.this, addressBean.getResult());
                            myaddress_recycler_show.setLayoutManager(new LinearLayoutManager(MyAddressActivity.this));
                            myaddress_recycler_show.setAdapter(recycleView_adapter_myAddress);
                            //删除
                            recycleView_adapter_myAddress.setDeletethisData(new RecycleView_Adapter_MyAddress.DeletethisData() {
                                @Override
                                public void Delete(int id) {
                                    Toast.makeText(MyAddressActivity.this,"后台并没有提供删除接口",Toast.LENGTH_SHORT).show();
                                }
                            });
                            //修改默认
                            recycleView_adapter_myAddress.setMorenData(new RecycleView_Adapter_MyAddress.MyGetMorenData() {
                                @Override
                                public void MorenData(int id) {
                                    //
                                    Toast.makeText(MyAddressActivity.this,"修改为默认",Toast.LENGTH_SHORT).show();
                                    Map<String,String> map = new HashMap<>();
                                    map.put("id",""+id);
                                    mMyPersenter.PostHeatHttp(AllUrl.CHANGEGETADDRESS_URL, map, ""+userId, sessionId, new MyPLoginInterface() {
                                        @Override
                                        public void HttpFailure() {
                                            Toast.makeText(MyAddressActivity.this,"请求服务器失败",Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void HttpResponse(String json) {
                                            Log.e("修改收货地址",""+json);
                                            if(json.equals("{\"message\":\"设置成功\",\"status\":\"0000\"}")){
                                                Toast.makeText(MyAddressActivity.this,"设置成功",Toast.LENGTH_SHORT).show();
                                            }else{
                                                Toast.makeText(MyAddressActivity.this,"设置失败"+json,Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            });
                            //修改
                            recycleView_adapter_myAddress.setUpdateAddress(new RecycleView_Adapter_MyAddress.UpdateAddress() {
                                @Override
                                public void MorenData(AddressBean.ResultBean beans) {
                                    Intent intent = new Intent(MyAddressActivity.this,MyAddressChangeActivity.class);
                                    intent.putExtra("id",beans.getId()+"");
                                    Log.e("收货地址",beans.getId()+"");
                                    intent.putExtra("realName",beans.getRealName()+"");
                                    intent.putExtra("phone",beans.getPhone()+"");
                                    intent.putExtra("address",beans.getAddress()+"");
                                    intent.putExtra("zipCode",beans.getZipCode()+"");
                                    startActivity(intent);
                                }
                            });
                        }else{
                            Toast.makeText(MyAddressActivity.this,"还没有收货地址，快去添加吧",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public int getContent() {
        return R.layout.activity_my_address;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myaddress_button_add:
                startActivity(new Intent(MyAddressActivity.this,AddAddressActivity.class));
                break;
        }
    }
}
