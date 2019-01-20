package com.liu.get.e_commerceproject.ui.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.adapter.RecyleViewItenClickListening;
import com.liu.get.e_commerceproject.adapter.XRecycleView_Adapter_Search;
import com.liu.get.e_commerceproject.base.BaseActivity;
import com.liu.get.e_commerceproject.bean.SearchBean;
import com.liu.get.e_commerceproject.net.AllUrl;
import com.liu.get.e_commerceproject.presenter.MyPLoginInterface;
import com.liu.get.e_commerceproject.presenter.MyPersenter;

/**
 * 搜索页面
 *      从其他页面获取输入的类
 *      网络请求
 *      展示数据
 *      刷新加载
 */
public class SearchActivity extends BaseActivity {

    private ImageView search_image_return;
    private EditText search_edit_name;
    private TextView search_text_search;
    private XRecyclerView search_xrec_showgoods;
    private MyPersenter mMyPersenter;
    private RelativeLayout search_Rela_failedshow;
    int page = 1;
    private String name;
    private XRecycleView_Adapter_Search mAdapter_search;

    public void initView() {//初始化view

        Intent intent = getIntent();//获取其他页面的传来的商品名称
        name = intent.getStringExtra("name");

        mMyPersenter = new MyPersenter();

        search_image_return = (ImageView) findViewById(R.id.search_image_return);
        search_edit_name = (EditText) findViewById(R.id.search_edit_name);
        search_text_search = (TextView) findViewById(R.id.search_text_search);
        search_xrec_showgoods = (XRecyclerView) findViewById(R.id.search_xrec_showgoods);
        search_Rela_failedshow = findViewById(R.id.search_Rela_failedshow);
        //显示其他页面的传来的值
        search_edit_name.setText(name);

        mAdapter_search = new XRecycleView_Adapter_Search(SearchActivity.this);
        search_xrec_showgoods.setLayoutManager(new GridLayoutManager(SearchActivity.this,2));
        search_xrec_showgoods.setAdapter(mAdapter_search);
        search_xrec_showgoods.computeScroll();

        search_xrec_showgoods.setLoadingMoreEnabled(true);
        search_xrec_showgoods.setPullRefreshEnabled(true);
        //刷新和加载
        search_xrec_showgoods.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                initData();
            }

            @Override
            public void onLoadMore() {
                page++;
                initData();
            }
        });
        search_image_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//关闭当前页
            }
        });
        search_text_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();//重新加载新的索引数据
            }
        });

        final Intent intents = new Intent(SearchActivity.this,DetailsActivity.class);
        //进入详情页
        mAdapter_search.setRecyleViewItenClickListening(new RecyleViewItenClickListening() {
            @Override
            public void ItenClickListening(String id, String name, String price) {
                intents.putExtra("id",id);
                startActivity(intents);
            }
        });
    }

    @Override//初始化数据
    public void initData() {
        String name = search_edit_name.getText().toString();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"搜索内容不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        mMyPersenter.LoaderData_HTTPGET(AllUrl.SEARCH_URL+"?keyword="+name+"&page="+page+"&count=10", new MyPLoginInterface() {
            @Override
            public void HttpFailure() {
                Toast.makeText(SearchActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void HttpResponse(String json) {
                Gson gson = new Gson();
                SearchBean searchBean = gson.fromJson(json, SearchBean.class);
                if(searchBean.getResult().size()>0){
                    //是否有数据   进行展示
                    search_xrec_showgoods.setVisibility(View.VISIBLE);
                    search_Rela_failedshow.setVisibility(View.GONE);

                    mAdapter_search.setList(searchBean.getResult(),page);
                    search_xrec_showgoods.refreshComplete();
                }else{
                    search_xrec_showgoods.setLoadingMoreEnabled(false);
                    search_Rela_failedshow.setVisibility(View.VISIBLE);
                    search_xrec_showgoods.setVisibility(View.GONE);
                    Toast.makeText(SearchActivity.this,"暂时没有该商品",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getContent() {
        return R.layout.activity_search;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mMyPersenter != null){
            mMyPersenter = null;
        }
    }
}
