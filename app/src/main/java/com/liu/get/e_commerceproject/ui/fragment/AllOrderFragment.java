package com.liu.get.e_commerceproject.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.liu.get.e_commerceproject.ui.activity.EvaluationActivity;
import com.liu.get.e_commerceproject.ui.activity.PayActivity;
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
 * 所有的订单信息页
 */
public class AllOrderFragment extends BaseFragment {

    //private XRecyclerView alldrder_recycler_show;
    private RecyclerView alldrder_recycler_show;
    private MyPersenter mMyPersenter;
    int page = 1;
    private RecycleView_Adapter_ShowOrder recycleView_adapter_showOrder;
    private String sessionId;
    private int userId;

    @Override
    public void initView(final View view) {
        //屏幕适配
        Log.e("AllOrderFragment", "initView执行");
        ScreenAdapterTools.getInstance().loadView(view);
        alldrder_recycler_show = view.findViewById(R.id.alldrder_recycler_show);
        //Button btn = view.findViewById(R.id.alldrder_button_refresh);
        alldrder_recycler_show.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView_adapter_showOrder = new RecycleView_Adapter_ShowOrder();
        alldrder_recycler_show.setAdapter(recycleView_adapter_showOrder);
        /*alldrder_recycler_show.setPullRefreshEnabled(true);
        alldrder_recycler_show.setLoadingMoreEnabled(true);*/

        /*alldrder_recycler_show.setLoadingListener(new XRecyclerView.LoadingListener() {
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
        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData(view);
            }
        });*/
        recycleView_adapter_showOrder.setPayOrder(new RecycleView_Adapter_ShowOrder.PayOrder() {
            @Override
            public void pay(OrderBeans.OrderListBean orderId, int moneyey, String s) {
                if (s.equals("去支付")) {
                    Intent intent = new Intent(getActivity(), PayActivity.class);
                    intent.putExtra("orderId", orderId.getOrderId());
                    intent.putExtra("moneyey", "" + moneyey);
                    startActivity(intent);
                } else if (s.equals("确认收货")) {
                    Map<String, String> map = new HashMap<>();
                    map.put("orderId", orderId.getOrderId());
                    mMyPersenter.PutHttp(AllUrl.RECRIPT_URL, map, userId + "", sessionId, new MyPLoginInterface() {
                        @Override
                        public void HttpFailure() {
                            Toast.makeText(getActivity(), "请求服务器失败", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void HttpResponse(String json) {
                            if(json.equals("{\"message\":\"请先登陆\",\"status\":\"0001\"}")){
                                Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (json.equals("{\"message\":\"确认收货成功\",\"status\":\"0000\"}")) {
                                Toast.makeText(getActivity(), "确认收货成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "确认收货失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else if (s.equals("去评价")) {
                    Intent intent = new Intent(getActivity(), EvaluationActivity.class);
                    intent.putExtra("orderId", orderId.getOrderId() + "");
                    intent.putExtra("commodityId", orderId.getDetailList().get(0).getCommodityId() + "");
                    intent.putExtra("commodityPic", orderId.getDetailList().get(0).getCommodityPic() + "");
                    intent.putExtra("commodityName", orderId.getDetailList().get(0).getCommodityName() + "");
                    double allMoney = orderId.getDetailList().get(0).getCommodityPrice() * orderId.getDetailList().get(0).getCommodityCount();
                    intent.putExtra("money", allMoney + "");
                    startActivity(intent);
                }

                initData(view);

            }
        });
        recycleView_adapter_showOrder.setDeleteOrder(new RecycleView_Adapter_ShowOrder.DeleteOrder() {
            @Override
            public void Remove(String orderId) {
                /**
                 * 删除订单逻辑
                 */
                mMyPersenter.MyHttpDelet(AllUrl.DELETEORDER_URL + "?orderId=" + orderId, userId + "", sessionId, new MyPLoginInterface() {
                    @Override
                    public void HttpFailure() {
                        Toast.makeText(getActivity(), "请求服务器失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void HttpResponse(String json) {
                        Log.e("删除订单", json);
                        if(json.equals("{\"message\":\"请先登陆\",\"status\":\"0001\"}")){
                            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (json.equals("{\"message\":\"删除成功\",\"status\":\"0000\"}")) {
                            Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                            initData(view);
                        } else {
                            Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void initData(View view) {
        /*Log.e("AllOrderFragment","initData执行");*/
        mMyPersenter = new MyPersenter();
        SharedPreferences remberPwd = getActivity().getSharedPreferences("RemberPwd", Context.MODE_PRIVATE);
        sessionId = remberPwd.getString("sessionId", "");
        userId = remberPwd.getInt("userId", 0);
        /*Log.e("AllOrderFragment",sessionId+"获取公共参数"+userId);*/
        mMyPersenter.HeadGET(AllUrl.ORDER_ALL_URL + "?status=0&page=" + page + "&count=100", "" + userId, sessionId, new MyPLoginInterface() {
            @Override
            public void HttpFailure() {
                Log.e("AllOrderFragment", "网络失败");
                Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void HttpResponse(String json) {
                /* Log.e("AllOrderFragment","网络成功"+json);*/
                /*Toast.makeText(getActivity(),""+json,Toast.LENGTH_SHORT).show();*/
                if(json.equals("{\"message\":\"请先登陆\",\"status\":\"0001\"}")){
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    return;
                }
                Gson gson = new Gson();
                OrderBeans orderBeans = gson.fromJson(json, OrderBeans.class);
                if (orderBeans.getOrderList() != null) {
                    recycleView_adapter_showOrder.setList(orderBeans.getOrderList(), 1);
                    /*alldrder_recycler_show.refreshComplete();*/
                }
            }
        });
    }

    @Override
    public int getContent() {
        return R.layout.fragment_all_order;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMyPersenter = null;
    }
}
