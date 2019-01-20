package com.liu.get.e_commerceproject.ui.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.adapter.RecycleView_Adapter_ShowOrder;
import com.liu.get.e_commerceproject.base.BaseFragment;
import com.liu.get.e_commerceproject.bean.OrderBeans;
import com.liu.get.e_commerceproject.net.AllUrl;
import com.liu.get.e_commerceproject.presenter.MyPLoginInterface;
import com.liu.get.e_commerceproject.presenter.MyPersenter;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * 待收货页面
 */
public class WaitReceivedFragment extends BaseFragment {

    /*private XRecyclerView waitreceiver_recycler_show;*/
    private RecyclerView waitreceiver_recycler_show;
    private MyPersenter mMyPersenter;
    int page = 1;
    private RecycleView_Adapter_ShowOrder recycleView_adapter_showOrder;
    private int userId;
    private String sessionId;

    @Override
    public void initView(final View view) {
        //屏幕适配
        ScreenAdapterTools.getInstance().loadView(view);
        waitreceiver_recycler_show = view.findViewById(R.id.waitreceiver_recycler_show);
       // Button btn = view.findViewById(R.id.waitreceiver_button_refresh);
        waitreceiver_recycler_show.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView_adapter_showOrder = new RecycleView_Adapter_ShowOrder();
        waitreceiver_recycler_show.setAdapter(recycleView_adapter_showOrder);
        /*waitreceiver_recycler_show.setPullRefreshEnabled(true);
        waitreceiver_recycler_show.setLoadingMoreEnabled(true);*/

        /*waitreceiver_recycler_show.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                initData(view);
            }

            @Override
            public void onLoadMore() {
                page++;
                initData(view);
            }
        });*/
       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData(view);
            }
        });*/
        recycleView_adapter_showOrder.setPayOrder(new RecycleView_Adapter_ShowOrder.PayOrder() {
            @Override
            public void pay(OrderBeans.OrderListBean orderId, int moneyey, String s) {
                Map<String,String> map = new HashMap<>();
                map.put("orderId",orderId.getOrderId());
                mMyPersenter.PutHttp(AllUrl.RECRIPT_URL, map, userId + "", sessionId, new MyPLoginInterface() {
                    @Override
                    public void HttpFailure() {
                        Toast.makeText(getActivity(),"请求服务器失败",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void HttpResponse(String json) {
                        if(json.equals("{\"message\":\"请先登陆\",\"status\":\"0001\"}")){
                            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(json.equals("{\"message\":\"确认收货成功\",\"status\":\"0000\"}")){
                            Toast.makeText(getActivity(),"确认收货成功",Toast.LENGTH_SHORT).show();
                            initData(view);
                        }else{
                            Toast.makeText(getActivity(),"确认收货失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void initData(View view) {
        mMyPersenter = new MyPersenter();
        SharedPreferences remberPwd = getActivity().getSharedPreferences("RemberPwd", Context.MODE_PRIVATE);
        sessionId = remberPwd.getString("sessionId", "");
        userId = remberPwd.getInt("userId",0);
        mMyPersenter.HeadGET(AllUrl.ORDER_ALL_URL + "?status=2&page=" + page + "&count=5", "" + userId, sessionId, new MyPLoginInterface() {
            @Override
            public void HttpFailure() {
                Toast.makeText(getActivity(),"请求失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void HttpResponse(String json) {
                if(json.equals("{\"message\":\"请先登陆\",\"status\":\"0001\"}")){
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    return;
                }
                /* Log.e("AllOrderFragment","网络成功"+json);*/
                /*Toast.makeText(getActivity(),""+json,Toast.LENGTH_SHORT).show();*/
                Gson gson = new Gson();
                OrderBeans orderBeans = gson.fromJson(json, OrderBeans.class);
                if(orderBeans.getOrderList()!=null){
                    recycleView_adapter_showOrder.setList(orderBeans.getOrderList(),1);
                    /*waitreceiver_recycler_show.refreshComplete();*/
                }
            }
        });
    }

    @Override
    public int getContent() {
        return R.layout.fragment_wait_received;
    }

}
