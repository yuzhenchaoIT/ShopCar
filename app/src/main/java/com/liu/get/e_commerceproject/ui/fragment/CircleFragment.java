package com.liu.get.e_commerceproject.ui.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.adapter.RecycleView_Adapter_Circle;
import com.liu.get.e_commerceproject.base.BaseFragment;
import com.liu.get.e_commerceproject.bean.CircleBeans;
import com.liu.get.e_commerceproject.net.AllUrl;
import com.liu.get.e_commerceproject.presenter.MyPLoginInterface;
import com.liu.get.e_commerceproject.presenter.MyPersenter;
import com.yatoooon.screenadaptation.ScreenAdapterTools;


/**
 * A simple {@link Fragment} subclass.
 * 圈子页面是，仅仅是展示
 */
public class CircleFragment extends BaseFragment {

    XRecyclerView circle_xrec_show;

    private MyPersenter mMyPersenter;
    private int page=1;
    private RecycleView_Adapter_Circle adapter;

    @Override
    public void initView(final View view) {
        //屏幕适配
        ScreenAdapterTools.getInstance().loadView(view);

        circle_xrec_show = view.findViewById(R.id.circle_xrec_show);
        circle_xrec_show.setPullRefreshEnabled(true);
        circle_xrec_show.setLoadingMoreEnabled(true);
        circle_xrec_show.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecycleView_Adapter_Circle(getActivity());
        circle_xrec_show.setAdapter(adapter);
        circle_xrec_show.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                initData(view);
            }

            @Override
            public void onLoadMore() {
                page++;
                initData(view);
            }
        });
    }

    @Override
    public void initData(View view) {

        SharedPreferences remberPwd = getActivity().getSharedPreferences("RemberPwd", Context.MODE_PRIVATE);
        String sessionId = remberPwd.getString("sessionId", "");
        int userId = remberPwd.getInt("userId",0);
        mMyPersenter = new MyPersenter();
        mMyPersenter.HeadGET(AllUrl.CIRCLE_URL + "?page=" + page + "&count=5",
                ""+userId, sessionId, new MyPLoginInterface() {
            @Override
            public void HttpFailure() {
                Toast.makeText(getActivity(),"获取圈子失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void HttpResponse(String json) {
                if(json.equals("{\"message\":\"请先登陆\",\"status\":\"0001\"}")){
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    return;
                }
                Gson gson = new Gson();
                CircleBeans circleBeans = gson.fromJson(json, CircleBeans.class);
                adapter.setList(circleBeans.getResult(),page);
                circle_xrec_show.refreshComplete();
            }
        });
    }

    @Override
    public int getContent() {
        return R.layout.fragment_circle;
    }

}
