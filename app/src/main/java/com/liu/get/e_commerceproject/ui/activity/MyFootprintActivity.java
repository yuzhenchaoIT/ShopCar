package com.liu.get.e_commerceproject.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.adapter.RecycleView_Adapter_Footprint;
import com.liu.get.e_commerceproject.adapter.RecyleViewItenClickListening;
import com.liu.get.e_commerceproject.base.BaseActivity;
import com.liu.get.e_commerceproject.bean.FootprintBeans;
import com.liu.get.e_commerceproject.net.AllUrl;
import com.liu.get.e_commerceproject.presenter.MyPLoginInterface;
import com.liu.get.e_commerceproject.presenter.MyPersenter;

/**
 * 我的足迹页面  仅仅用于展示足迹
 *          可以刷新加载
 */
public class MyFootprintActivity extends BaseActivity {

    private XRecyclerView myfoot_recycler_show;
    private MyPersenter mMyPersenter;
    private RecycleView_Adapter_Footprint adapter;
    int page=1;


    public void initView() {
        mMyPersenter = new MyPersenter();
        myfoot_recycler_show = (XRecyclerView) findViewById(R.id.myfoot_recycler_show);
        myfoot_recycler_show.setLoadingMoreEnabled(true);
        myfoot_recycler_show.setPullRefreshEnabled(true);
        myfoot_recycler_show.setLayoutManager(new GridLayoutManager(this,2));
        adapter = new RecycleView_Adapter_Footprint(this);
        myfoot_recycler_show.setAdapter(adapter);
        myfoot_recycler_show.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                initData();
            }

            @Override
            public void onLoadMore() {
                page++;
                initData();
            }
        });
    }

    public void initData() {//数据的加载和刷新   判断条件page
        //Toast.makeText(MyFootprintActivity.this,"initData",Toast.LENGTH_SHORT).show();
        SharedPreferences remberPwd = getSharedPreferences("RemberPwd", MODE_PRIVATE);
        String sessionId = remberPwd.getString("sessionId", "");
        int userId = remberPwd.getInt("userId",0);
        mMyPersenter.HeadGET(AllUrl.FOOTPRINT_URL + "?page=" + 1 + "&count=5",
                ""+userId, sessionId, new MyPLoginInterface() {
                    @Override
                    public void HttpFailure() {
                        Toast.makeText(MyFootprintActivity.this,"获取足迹失败",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void HttpResponse(String json) {
                        //Toast.makeText(MyFootprintActivity.this,""+json,Toast.LENGTH_SHORT).show();
                        Gson gson = new Gson();
                        FootprintBeans beans = gson.fromJson(json, FootprintBeans.class);
                        if(json.equals("{\"message\":\"请先登陆\",\"status\":\"0001\"}")||beans.getResult()==null){
                            Toast.makeText(MyFootprintActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        adapter.setList(beans.getResult(),page);
                        myfoot_recycler_show.refreshComplete();

                        adapter.setRecyleViewItenClickListening(new RecyleViewItenClickListening() {
                            @Override
                            public void ItenClickListening(String id, String name, String price) {
                                Intent intent = new Intent(MyFootprintActivity.this,DetailsActivity.class);
                                intent.putExtra("id",id);
                                startActivity(intent);
                            }
                        });
                    }
                });
    }

    @Override
    public int getContent() {
        return R.layout.activity_my_footprint;
    }
}
