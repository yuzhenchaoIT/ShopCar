package com.liu.get.e_commerceproject.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.adapter.RecycleView_Adapter_Circle;
import com.liu.get.e_commerceproject.base.BaseActivity;
import com.liu.get.e_commerceproject.bean.CircleBeans;
import com.liu.get.e_commerceproject.net.AllUrl;
import com.liu.get.e_commerceproject.presenter.MyPLoginInterface;
import com.liu.get.e_commerceproject.presenter.MyPersenter;

/**
 * 我的圈子页面
 *          圈子的展示
 *          圈子的删除
 *          刷新和加载
 */
public class MyCircleActivity extends BaseActivity {
    //控件
    private ImageView mycircle_image_delet;
    private XRecyclerView mycircle_recycler_show;
    private MyPersenter mMyPersenter;
    private RecycleView_Adapter_Circle adapter;
    int page = 1;
    int click = 1;

    public void initView() {//初始化view
        mycircle_image_delet = (ImageView) findViewById(R.id.mycircle_image_delet);
        mycircle_recycler_show = (XRecyclerView) findViewById(R.id.mycircle_recycler_show);
        mycircle_recycler_show.setPullRefreshEnabled(true);
        mycircle_recycler_show.setLoadingMoreEnabled(true);
        mycircle_recycler_show.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecycleView_Adapter_Circle(this);
        mycircle_recycler_show.setAdapter(adapter);
        //数据的加载和刷新
        mycircle_recycler_show.setLoadingListener(new XRecyclerView.LoadingListener() {
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
        //点击删除按钮  显示出条目的删除按钮
        mycircle_image_delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click++;
                if (click % 2 == 1) {
                    adapter.setShow();
                } else {
                    adapter.setHind();
                }
            }
        });
    }

    public void initData() {//初始化数据
        //获取全局保存的主要信息
        SharedPreferences remberPwd = getSharedPreferences("RemberPwd", MODE_PRIVATE);
        //P层
        mMyPersenter = new MyPersenter();
        final String sessionId = remberPwd.getString("sessionId", "");
        final int userId = remberPwd.getInt("userId", 0);
        mMyPersenter = new MyPersenter();
        //带请求头的GET请求
        mMyPersenter.HeadGET(AllUrl.MYCIRCLE_URL+ "?page=" + 1 + "&count=5",
                "" + userId, sessionId, new MyPLoginInterface() {
                    @Override
                    public void HttpFailure() {
                        Toast.makeText(MyCircleActivity.this, "获取圈子失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void HttpResponse(String json) {
                        Gson gson = new Gson();
                        //获取数据  展示数据
                        CircleBeans circleBeans = gson.fromJson(json, CircleBeans.class);
                        if(json.equals("{\"message\":\"请先登陆\",\"status\":\"0001\"}")||circleBeans.getResult()==null){
                            Toast.makeText(MyCircleActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        adapter.setList(circleBeans.getResult(), page);
                        mycircle_recycler_show.refreshComplete();
                        //删除的逻辑  用于删除的回调，从adapter回调的接口
                        adapter.setMyCircleDelete(new RecycleView_Adapter_Circle.MyCircleDelete() {
                            @Override
                            public void Delete(int id) {
                                mMyPersenter.MyHttpDelet(AllUrl.MYCIRCLEDELETE_URL + "?circleId=" + id, "" + userId, sessionId, new MyPLoginInterface() {
                                    @Override
                                    public void HttpFailure() {
                                        Toast.makeText(MyCircleActivity.this, "链接数据库失败", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void HttpResponse(String json) {
                                        Log.e("ASDASD",""+json);
                                        if (json.equals("{\"message\":\"删除成功\",\"status\":\"0000\"}")) {
                                            Toast.makeText(MyCircleActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(MyCircleActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
    }

    @Override
    public int getContent() {
        return R.layout.activity_my_circle;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放内存
        mMyPersenter=null;
    }
}
