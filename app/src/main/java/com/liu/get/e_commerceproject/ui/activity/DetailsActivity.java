package com.liu.get.e_commerceproject.ui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.adapter.RecycleView_Adapter_DetailsComments;
import com.liu.get.e_commerceproject.adapter.RecycleView_Adapter_shoppingcar;
import com.liu.get.e_commerceproject.adapter.ViewPager_Adapter_DetailsBanner;
import com.liu.get.e_commerceproject.base.BaseActivity;
import com.liu.get.e_commerceproject.bean.DetailsBeans;
import com.liu.get.e_commerceproject.bean.DetailsCommentsBeans;
import com.liu.get.e_commerceproject.bean.ShoppingCarBeans;
import com.liu.get.e_commerceproject.net.AllUrl;
import com.liu.get.e_commerceproject.presenter.MyPLoginInterface;
import com.liu.get.e_commerceproject.presenter.MyPersenter;
import com.liu.get.e_commerceproject.ui.myview.MyScrollView;
import com.liu.get.e_commerceproject.ui.myview.MyView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 详情页
 *  用以展示数据和展示评论
 */
public class DetailsActivity extends BaseActivity {

    private SharedPreferences mRemberPwd;
    private int userId;
    private String sessionId;

    private ViewPager details_viewpager_show;

    private ImageView details_image_return;
    private TextView details_textview_shownum;
    private TextView details_textview_price;
    private TextView details_textview_sold;
    private TextView details_textview_title;
    private TextView details_textview_Weight;
    private TextView details_textview_describe;
    private TextView details_textview_comments;
    private SimpleDraweeView details_Image_details;
    private SimpleDraweeView details_Image_describe;

    private RecyclerView details_recview_comments;
    private RelativeLayout details_relative_addshoppingcar;
    private RelativeLayout details_relative_pay;
    private RelativeLayout details_relative_changer;

    private RelativeLayout details_relat_changecolor;
    private MyScrollView details_scroll_changecolor;

    private int count;
    private DetailsBeans detailsBeans;
    private DetailsCommentsBeans commentsBeans;

    private TextView details_text_goodsT;
    private TextView details_text_goods;
    private TextView details_text_detailsT;
    private TextView details_text_details;
    private TextView details_text_commentsT;
    private TextView details_text_comments;

    private MyPersenter mMyPersenter;
    private String id;
    private int page = 1;

    Gson gson = new Gson();

    @Override
    public void initView() {//初始化视图
        Intent intent = getIntent();//获取商品id
        id = intent.getStringExtra("id");
        mRemberPwd = this.getSharedPreferences("RemberPwd", this.MODE_PRIVATE);//的到全局的一个保存用户 主键的数据
        Log.e("Id", "" + id);
        /**
         * 初始化数据
         */
        details_image_return = findViewById(R.id.details_image_return);
        details_viewpager_show = findViewById(R.id.details_viewpager_show);
        details_textview_shownum = findViewById(R.id.details_textview_shownum);
        details_textview_price = findViewById(R.id.details_textview_sprice);
        details_textview_sold = findViewById(R.id.details_textview_sold);
        details_textview_title = findViewById(R.id.details_textview_title);
        details_textview_Weight = findViewById(R.id.details_textview_Weight);
        details_Image_details = findViewById(R.id.details_Image_details);
        details_textview_describe = findViewById(R.id.details_textview_describe);
        details_Image_describe = findViewById(R.id.details_Image_describe);
        details_recview_comments = findViewById(R.id.details_recview_comments);
        details_textview_comments = findViewById(R.id.details_textview_comments);
        details_scroll_changecolor = findViewById(R.id.details_scroll_changecolor);
        details_relat_changecolor = findViewById(R.id.details_relat_changecolor);
        details_relative_addshoppingcar = findViewById(R.id.details_relative_addshoppingcar);
        details_relative_pay = findViewById(R.id.details_relative_pay);

        details_text_goodsT = findViewById(R.id.details_text_goodsT);
        details_text_goods = findViewById(R.id.details_text_goods);
        details_text_detailsT = findViewById(R.id.details_text_detailsT);
        details_text_details = findViewById(R.id.details_text_details);
        details_text_commentsT = findViewById(R.id.details_text_commentsT);
        details_text_comments = findViewById(R.id.details_text_comments);
        details_relative_changer = findViewById(R.id.details_relative_changer);
        //点击返回按钮销毁当前页面
        details_image_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //加入购物车
        details_relative_addshoppingcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //做购物车的添加功能  首先需要从服务器拿到数据，因为要从客户端进行一个拼接，服务器并没有做拼接的工作
                /**
                 * 实现思路   ：
                 *          1.从服务器拿到自己曾经添加过的数据
                 *          2.遍历购物车中的集合
                 *          3.在遍历的过程中进行一个字符串的拼接
                 *          4.然后拿到要添加购物车的数据
                 *          5.同步购物车
                 */
                getAllCarData();
            }
        });
        //直接支付
        details_relative_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailsActivity.this,"请先加入购物车",Toast.LENGTH_SHORT).show();
            }
        });
        //商品banner的下标展示  以及改变
        details_viewpager_show.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                details_textview_shownum.setText((position + 1) + "/" + count);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //滑动变色  上方的标题
        details_scroll_changecolor.setScrollViewListener(new MyScrollView.ScrollViewListener() {
            @Override
            public void onScrollChange(MyScrollView scrollView, int l, int t, int oldl, int oldt) {
                if (t <= 0) {
                    //标题显示
                    details_relative_changer.setVisibility(View.GONE);
                    //设置标题所在的背景颜色为透明
                    details_relat_changecolor.setBackgroundColor(Color.argb(0, 0, 0, 0));
                } else if (t > 0 && t < 200) {
                    details_relative_changer.setVisibility(View.VISIBLE);
                    //获取ScrollView想下滑动,图片消失部分的比例
                    float scale = (float) t / 200;
                    //根据比例,让标题的颜色由浅入深
                    float alpha = 255 * scale;
                    //设置标题布局的颜色
                    details_relat_changecolor.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));

                }
                //更改选中状态
                if (0 < t && t < 700) {
                    details_text_goods.setBackgroundColor(Color.RED);
                    details_text_details.setBackgroundColor(Color.WHITE);
                    details_text_comments.setBackgroundColor(Color.WHITE);
                } else if (701 < t && t < 1500) {
                    details_text_goods.setBackgroundColor(Color.WHITE);
                    details_text_details.setBackgroundColor(Color.RED);
                    details_text_comments.setBackgroundColor(Color.WHITE);
                } else if (t > 1500) {
                    details_text_goods.setBackgroundColor(Color.WHITE);
                    details_text_details.setBackgroundColor(Color.WHITE);
                    details_text_comments.setBackgroundColor(Color.RED);
                }
            }
        });
        /**
         * 点击对应的文字跳到页面对应位置
         */
        details_text_goodsT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details_scroll_changecolor.setScrollY(0);
            }
        });
        details_text_detailsT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details_scroll_changecolor.setScrollY(702);
            }
        });
        details_text_commentsT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details_scroll_changecolor.setScrollY(1501);
            }
        });
    }

    private void getAllCarData() {
        SharedPreferences remberPwd = getSharedPreferences("RemberPwd", Context.MODE_PRIVATE);
        String sessionId = remberPwd.getString("sessionId", "");
        int userId = remberPwd.getInt("userId", 0);
        mMyPersenter = new MyPersenter();
        mMyPersenter.HeadGET(AllUrl.GETSHOPPINGCAR_URL,
                "" + userId, sessionId, new MyPLoginInterface() {
                    @Override
                    public void HttpFailure() {
                        Toast.makeText(DetailsActivity.this, "请求服务器失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void HttpResponse(String json) {
                        Gson gson = new Gson();
                        ShoppingCarBeans shoppingCarBeans = gson.fromJson(json, ShoppingCarBeans.class);
                        if(json.equals("{\"message\":\"请先登陆\",\"status\":\"0001\"}")||shoppingCarBeans.getResult()==null){
                            Toast.makeText(DetailsActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        ArrayList<ShoppingCarBeans.ResultBean> result = shoppingCarBeans.getResult();
                        ///StringBuffer  线程安全
                        StringBuffer stringBuilder = new StringBuffer();
                        for (int i = 0; i < result.size(); i++) {
                            int commodityId = result.get(i).getCommodityId();
                            int count = result.get(i).getCount();
                            if(i==0){
                                stringBuilder.append("{\"commodityId\":"+commodityId+",\"count\":"+count+"}");
                            }else{
                                stringBuilder.append(",{\"commodityId\":"+commodityId+",\"count\":"+count+"}");
                            }
                        }
                        String data = stringBuilder.toString();
                        SynchronousShoppingCar(data);
                    }
                });
    }

    private void SynchronousShoppingCar(final String cardata) {
        /*Log.e("Goodsid",""+cardata);*/
        final MyView myView = new MyView(DetailsActivity.this);
        Dialog dialog = new AlertDialog.Builder(DetailsActivity.this)
                .setTitle("选择数量")
                .setView(myView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int num = myView.getNums();
                        String data;
                        if(cardata.length()>0){
                            data="["+cardata+",{\"commodityId\":"+id+",\"count\":"+num+"}]";
                        }else{
                            data="[{\"commodityId\":"+id+",\"count\":"+num+"}]";
                        }
                        /*Log.e("Goodsid",""+data);*/
                        //mMyPersenter.PutHttp(AllUrl.ADDSHOPPINGCAR_URL,);
                        Map<String,String> map = new HashMap<>();
                        map.put("data",data);
                        mMyPersenter.PutHttp(AllUrl.ADDSHOPPINGCAR_URL, map, userId + "", sessionId, new MyPLoginInterface() {
                            @Override
                            public void HttpFailure() {
                                Toast.makeText(DetailsActivity.this,"请求服务器失败",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void HttpResponse(String json) {
                                if(json.equals("{\"message\":\"同步成功\",\"status\":\"0000\"}")){
                                    Toast.makeText(DetailsActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(DetailsActivity.this,"添加失败",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                })
                .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
        dialog.show();
    }

    @Override
    public void initData() {
        //获取保存的公共参数
        userId = mRemberPwd.getInt("userId", 0);
        sessionId = mRemberPwd.getString("sessionId", "");
        mMyPersenter = new MyPersenter();
        //网络请求    请求商品数据
        // http://172.17.8.100/small/commodity/v1/findCommodityDetailsById?userId=104&sessionId=1544148612203104&commodityId=6
        mMyPersenter.LoaderData_HTTPGET(AllUrl.DETAILS_URL + "?userId=" + userId + "&userId=" + sessionId + "&commodityId=" + id,
                new MyPLoginInterface() {
                    @Override
                    public void HttpFailure() {
                        Toast.makeText(DetailsActivity.this, "请求服务器失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void HttpResponse(String json) {
                        //解析数据
                        DetailsBeans detailsBeans = gson.fromJson(json, DetailsBeans.class);
                        //加载数据
                        LoadData(detailsBeans);
                    }
                });
        //网络请求    请求商品评论
        //http://172.17.8.100/small/commodity/v1/CommodityCommentList?commodityId=1&page=1&count=50
        mMyPersenter.LoaderData_HTTPGET(AllUrl.DETAILSCOMMENTS_URL + "?commodityId=" + id + "&page=" + page + "&count=50", new MyPLoginInterface() {
            @Override
            public void HttpFailure() {
                Toast.makeText(DetailsActivity.this, "请求服务器失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void HttpResponse(String json) {
                DetailsCommentsBeans commentsBeans = gson.fromJson(json, DetailsCommentsBeans.class);
                //判断是否由评论，没有评论则显示文字，有评论就显示评论
                if (commentsBeans.getResult().size() != 0) {
                    //显示评论和隐藏文字
                    details_recview_comments.setVisibility(View.VISIBLE);
                    details_textview_comments.setVisibility(View.GONE);
                    details_recview_comments.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
                    RecycleView_Adapter_DetailsComments commentsAdapter = new RecycleView_Adapter_DetailsComments(DetailsActivity.this, commentsBeans.getResult());
                    details_recview_comments.setAdapter(commentsAdapter);
                } else {
                    //显示文字和隐藏评论
                    details_recview_comments.setVisibility(View.GONE);
                    details_textview_comments.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void LoadData(DetailsBeans detailsBeans) {
        //展示数据
        details_textview_price.setText("￥" + detailsBeans.getResult().getPrice());
        details_textview_sold.setText("已售" + detailsBeans.getResult().getSaleNum() + "件");
        details_textview_title.setText(detailsBeans.getResult().getCommodityName());
        details_textview_Weight.setText(detailsBeans.getResult().getWeight() + "kg");
        details_textview_describe.setText(detailsBeans.getResult().getDescribe());

        String Pictures = detailsBeans.getResult().getPicture().trim();
        String[] split = Pictures.split(",");

        String details = detailsBeans.getResult().getDetails();
        Document doc = Jsoup.parse(details);

        Elements elementsByClass = doc.getElementsByClass("J-mer-bigImg");
        Log.e("DOC",elementsByClass+"");
        //拆分图片，对图片进行展示
        details_Image_details.setImageURI(split[0]);
        details_Image_describe.setImageURI(split[1]);

        ViewPager_Adapter_DetailsBanner adapter = new ViewPager_Adapter_DetailsBanner(this, split);
        count = adapter.getCount();
        details_viewpager_show.setAdapter(adapter);
    }

    @Override
    public int getContent() {
        return R.layout.activity_details;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放内存
        if (mMyPersenter != null) {
            mMyPersenter = null;
        }
    }

}
