package com.liu.get.e_commerceproject.ui.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.bean.LevelBean;
import com.liu.get.e_commerceproject.ui.activity.SearchActivity;
import com.liu.get.e_commerceproject.adapter.RecycleView_Adapter_HomeHot;
import com.liu.get.e_commerceproject.adapter.RecycleView_Adapter_HomeMagic;
import com.liu.get.e_commerceproject.adapter.RecycleView_Adapter_HomeQuality;
import com.liu.get.e_commerceproject.adapter.RecyleViewItenClickListening;
import com.liu.get.e_commerceproject.adapter.ViewPager_Adapter_Home;
import com.liu.get.e_commerceproject.base.BaseFragment;
import com.liu.get.e_commerceproject.bean.Banner_DataBean;
import com.liu.get.e_commerceproject.bean.HomeRec_DataBean;
import com.liu.get.e_commerceproject.net.AllUrl;
import com.liu.get.e_commerceproject.presenter.MyPLoginInterface;
import com.liu.get.e_commerceproject.presenter.MyPersenter;
import com.liu.get.e_commerceproject.ui.activity.ClassificationActivity;
import com.liu.get.e_commerceproject.ui.activity.DetailsActivity;
import com.liu.get.e_commerceproject.util.ZoomOutPageTransformer;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.List;

/**
 * 主页
 *      一个viewpager+三个RecycleView的展示
 */
public class HomeFragment extends BaseFragment {

    private ImageView home_image_popupwindow;
    private EditText home_edit_search;
    private TextView home_text_search;
    private ViewPager home_viewpager_banner;
    private RecyclerView home_recyclerview_showhot,home_recyclerview_showmagic,home_recyclerview_showquality;
    private ViewPager_Adapter_Home adapter_home;
    private MyPersenter mMyPersenter;
    private RecycleView_Adapter_HomeHot mHotAdapter;
    private RecycleView_Adapter_HomeMagic mMagicAdapter;
    private RecycleView_Adapter_HomeQuality mQualityAdapter;
    private LevelBean titleBeans;
    private LinearLayout ll_layout;
    private ViewPager homefragment_viewpager;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int item = homefragment_viewpager.getCurrentItem();
            item++;
            homefragment_viewpager.setCurrentItem(item);
            mHandler.sendEmptyMessageDelayed(1, 2000);
        }
    };
    public void initView(View view) {
        //屏幕适配
        ScreenAdapterTools.getInstance().loadView(view);

        mMyPersenter = new MyPersenter();

        home_image_popupwindow = (ImageView) view.findViewById(R.id.home_image_popupwindow);
        home_edit_search = (EditText) view.findViewById(R.id.home_edit_search);
        home_text_search = (TextView) view.findViewById(R.id.home_text_search);
        homefragment_viewpager = view.findViewById(R.id.homefragment_viewpager);

        home_recyclerview_showhot = (RecyclerView)view.findViewById(R.id.home_recyclerview_showhot);
        home_recyclerview_showmagic = (RecyclerView)view.findViewById(R.id.home_recyclerview_showmagic);
        home_recyclerview_showquality = (RecyclerView)view.findViewById(R.id.home_recyclerview_showquality);
        ll_layout = (LinearLayout) view.findViewById(R.id.ll_layout);




        //搜索
        home_text_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = home_edit_search.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    Toast.makeText(getActivity(), "输入框内容不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    intent.putExtra("name",trim);
                    startActivity(intent);
                }
            }
        });
        //popupwindow 的点击显示
        home_image_popupwindow.setOnClickListener(new View.OnClickListener() {
            private RadioGroup popupwindow_item_class;
            private RadioGroup popupwindow_item_title;

            @Override
            public void onClick(View v) {
                final View popu = View.inflate(getActivity(), R.layout.popupwindow_item, null);
                PopupWindow window = new PopupWindow(popu, 1000, 300, true);
                window.setBackgroundDrawable(new ColorDrawable());
                window.setOutsideTouchable(true);
                window.setTouchable(true);
                window.showAsDropDown(home_image_popupwindow);
                //寻找控件
                popupwindow_item_title = popu.findViewById(R.id.popupwindow_item_title);
                popupwindow_item_class = popu.findViewById(R.id.popupwindow_item_class);

                popupwindow_item_title.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {

                    }
                });
                //popupwindow 的跳转
                popupwindow_item_class.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton child = popu.findViewById(checkedId);
                        Toast.makeText(getActivity(), "" + child.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), SearchActivity.class);
                        intent.putExtra("name", child.getText());
                        startActivity(intent);
                    }
                });
            }
        });

    }

    @Override
    public void initData(View view) {//初始化数据

        mMyPersenter.LoaderData_HTTPGET(AllUrl.BANNERDATA_URL, new MyPLoginInterface() {
            @Override
            public void HttpFailure() {
                Toast.makeText(getActivity(),"服务器连接失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void HttpResponse(String json) {
                Gson gson = new Gson();
                Banner_DataBean banner_dataBean = gson.fromJson(json, Banner_DataBean.class);
                adapter_home=new ViewPager_Adapter_Home(getActivity(),banner_dataBean.getResult());
                homefragment_viewpager.setAdapter(adapter_home);
                homefragment_viewpager.setPageMargin(20);
                homefragment_viewpager.setPageTransformer(true, new ZoomOutPageTransformer());//3D画廊模式

                //左右都有图
                homefragment_viewpager.setCurrentItem(1);
                mHandler.sendEmptyMessageDelayed(1,2000);

                //viewPager左右两边滑动无效的处理
                ll_layout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return homefragment_viewpager.dispatchTouchEvent(motionEvent);
                    }
                });


            }
        });
        mMyPersenter.LoaderData_HTTPGET(AllUrl.HOME_ADTA_URL, new MyPLoginInterface() {
            @Override
            public void HttpFailure() {
                Toast.makeText(getActivity(),"服务器连接失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void HttpResponse(String json) {
                if(json.equals("{\"message\":\"请先登陆\",\"status\":\"0001\"}")){
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    return;
                }
                Gson gson = new Gson();
                HomeRec_DataBean homeRec_dataBean = gson.fromJson(json, HomeRec_DataBean.class);
                //热门
                home_recyclerview_showhot.setLayoutManager(new StaggeredGridLayoutManager(1,LinearLayout.HORIZONTAL));
                mHotAdapter = new RecycleView_Adapter_HomeHot(getActivity(), homeRec_dataBean.getResult().getRxxp().get(0).getCommodityList());
                home_recyclerview_showhot.setAdapter(mHotAdapter);
                //魔幻
                home_recyclerview_showmagic.setLayoutManager(new LinearLayoutManager(getActivity()));
                mMagicAdapter = new RecycleView_Adapter_HomeMagic(getActivity(), homeRec_dataBean.getResult().getMlss().get(0).getCommodityList());
                home_recyclerview_showmagic.setAdapter(mMagicAdapter);
                //生活
                home_recyclerview_showquality.setLayoutManager(new GridLayoutManager(getActivity(),2));
                mQualityAdapter = new RecycleView_Adapter_HomeQuality(getActivity(), homeRec_dataBean.getResult().getPzsh().get(0).getCommodityList());
                home_recyclerview_showquality.setAdapter(mQualityAdapter);

                final Intent intent = new Intent(getActivity(),DetailsActivity.class);
                /**
                 * 条目点击
                 * 进入详情页
                 */
                mHotAdapter.setRecyleViewItenClickListening(new RecyleViewItenClickListening() {
                    @Override
                    public void ItenClickListening(String id, String name, String price) {
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }
                });

                mMagicAdapter.setRecyleViewItenClickListening(new RecyleViewItenClickListening() {
                    @Override
                    public void ItenClickListening(String id, String name, String price) {
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }
                });

                mQualityAdapter.setRecyleViewItenClickListening(new RecyleViewItenClickListening() {
                    @Override
                    public void ItenClickListening(String id, String name, String price) {
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }
                });

            }
        });
    }

    @Override
    public int getContent() {
        return R.layout.fragment_home;
    }

}
