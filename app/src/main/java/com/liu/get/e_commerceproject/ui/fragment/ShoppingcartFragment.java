package com.liu.get.e_commerceproject.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.adapter.RecycleView_Adapter_shoppingcar;
import com.liu.get.e_commerceproject.base.BaseFragment;
import com.liu.get.e_commerceproject.bean.AddressBean;
import com.liu.get.e_commerceproject.bean.CircleBeans;
import com.liu.get.e_commerceproject.bean.CreateOrderBeans;
import com.liu.get.e_commerceproject.bean.ShoppingCarBeans;
import com.liu.get.e_commerceproject.net.AllUrl;
import com.liu.get.e_commerceproject.presenter.MyPLoginInterface;
import com.liu.get.e_commerceproject.presenter.MyPersenter;
import com.liu.get.e_commerceproject.ui.activity.DetailsActivity;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车页面
 */
public class ShoppingcartFragment extends BaseFragment {


    private RecyclerView shoppingcar_recycle_show;
    private CheckBox shoppingcar_checkbox_checkall;
    private TextView shoppingcar_text_allmoney;
    private Button shoppingcar_button_pay;
    private MyPersenter mMyPersenter;
    private RecycleView_Adapter_shoppingcar adapter_shoppingcar;
    private int mAllmoney;
    private int mAllMoney;
    private ImageButton btn_delete_n;

    @Override
    public void initView(View view) {
        //屏幕适配
        ScreenAdapterTools.getInstance().loadView(view);

        shoppingcar_recycle_show = view.findViewById(R.id.shoppingcar_recycle_show);
        shoppingcar_checkbox_checkall = view.findViewById(R.id.shoppingcar_checkbox_checkall);
        shoppingcar_text_allmoney = view.findViewById(R.id.shoppingcar_text_allmoney);
        shoppingcar_button_pay = view.findViewById(R.id.shoppingcar_button_pay);
        btn_delete_n = view.findViewById(R.id.btn_delete_n);
        shoppingcar_recycle_show.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void initData(View view) {
        SharedPreferences remberPwd = getActivity().getSharedPreferences("RemberPwd", Context.MODE_PRIVATE);
        final String sessionId = remberPwd.getString("sessionId", "");
        final int userId = remberPwd.getInt("userId", 0);
        mMyPersenter = new MyPersenter();
        mMyPersenter.HeadGET(AllUrl.GETSHOPPINGCAR_URL,
                "" + userId, sessionId, new MyPLoginInterface() {
                    @Override
                    public void HttpFailure() {
                        Toast.makeText(getActivity(), "请求服务器失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void HttpResponse(String json) {
                        Gson gson = new Gson();
                        ShoppingCarBeans shoppingCarBeans = gson.fromJson(json, ShoppingCarBeans.class);
                        if(json.equals("{\"message\":\"请先登陆\",\"status\":\"0001\"}")||shoppingCarBeans.getResult()==null){
                            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        adapter_shoppingcar = new RecycleView_Adapter_shoppingcar(getActivity(), shoppingCarBeans.getResult());
                        shoppingcar_recycle_show.setAdapter(adapter_shoppingcar);
                        adapter_shoppingcar.setOnCheckBoxClick(new RecycleView_Adapter_shoppingcar.OnCheckBoxClick() {

                            @Override
                            public void itemclick(int money) {
                                mAllmoney = money;
                                shoppingcar_text_allmoney.setText("合计:￥" + mAllmoney);
                            }
                        });
                        adapter_shoppingcar.setAllChecked(new RecycleView_Adapter_shoppingcar.AllChecked() {
                            @Override
                            public void checked(boolean checked) {
                                shoppingcar_checkbox_checkall.setChecked(checked);
                            }
                        });

                        shoppingcar_checkbox_checkall.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                boolean isChecked = shoppingcar_checkbox_checkall.isChecked();
                                adapter_shoppingcar.setAllCheckedCheckBox(isChecked);
                            }
                        });

                        shoppingcar_button_pay.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ArrayList<ShoppingCarBeans.ResultBean> checkedData = adapter_shoppingcar.getCheckedData();
                                if(checkedData == null || checkedData.size()==0){
                                    Toast.makeText(getActivity(),"请至少选择一个商品下单",Toast.LENGTH_SHORT).show();
                                }else{
                                    ///StringBuffer  线程安全
                                    StringBuffer stringBuilder = new StringBuffer();
                                    //总价格
                                    mAllMoney = 0;
                                    for (int i = 0; i < checkedData.size(); i++) {
                                        int commodityId = checkedData.get(i).getCommodityId();
                                        int count = checkedData.get(i).getCount();
                                        mAllMoney += count*checkedData.get(i).getPrice();
                                        Log.e("添加订单",""+mAllMoney);
                                        if(i==0){
                                            stringBuilder.append("{\"commodityId\":"+commodityId+",\"amount\":"+count+"}");
                                        }else{
                                            stringBuilder.append(",{\"commodityId\":"+commodityId+",\"amount\":"+count+"}");
                                        }
                                    }
                                    final String data = "["+stringBuilder.toString()+"]";
                                    Log.e("添加订单",""+data);
                                    mMyPersenter.HeadGET(AllUrl.GETADDRESS_URL,
                                            userId+"", sessionId, new MyPLoginInterface() {
                                                @Override
                                                public void HttpFailure() {
                                                    Toast.makeText(getActivity(),"请求服务器失败",Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void HttpResponse(String json) {
                                                    Gson gson = new Gson();
                                                    AddressBean addressBean = gson.fromJson(json, AddressBean.class);
                                                    if(addressBean.getResult()!=null){
                                                        Map<String,String> map = new HashMap<>();
                                                        map.put("orderInfo",data);
                                                        map.put("totalPrice", mAllMoney+".00");
                                                        map.put("addressId",addressBean.getResult().get(0).getId()+"");
                                                        mMyPersenter.PostHeatHttp(AllUrl.CREATEORDER_URL, map, userId + "", sessionId, new MyPLoginInterface() {
                                                            @Override
                                                            public void HttpFailure() {
                                                                Toast.makeText(getActivity(),"请求服务器失败",Toast.LENGTH_SHORT).show();
                                                            }

                                                            @Override
                                                            public void HttpResponse(String json) {
                                                                Gson gsons = new Gson();
                                                                CreateOrderBeans createOrderBeans = gsons.fromJson(json, CreateOrderBeans.class);
                                                                if(createOrderBeans.getMessage().equals("创建订单成功")){
                                                                    Toast.makeText(getActivity(),"创建订单成功",Toast.LENGTH_SHORT).show();

                                                                }else{
                                                                    Toast.makeText(getActivity(),"创建订单失败",Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                    }else{
                                                        Toast.makeText(getActivity(),"还没有收货地址，快去添加吧",Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }
                            }
                        });

                    }
                });
    }

    @Override
    public int getContent() {
        return R.layout.fragment_shoppingcart;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMyPersenter = null;
    }
}
