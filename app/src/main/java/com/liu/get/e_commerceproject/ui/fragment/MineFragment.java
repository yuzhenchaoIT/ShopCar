package com.liu.get.e_commerceproject.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liu.get.e_commerceproject.ui.activity.LoginActivity;
import com.liu.get.e_commerceproject.ui.activity.MyAddressActivity;
import com.liu.get.e_commerceproject.ui.activity.MyWalletActivity;
import com.liu.get.e_commerceproject.base.BaseFragment;
import com.liu.get.e_commerceproject.ui.activity.InformationActivity;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.ui.activity.MyCircleActivity;
import com.liu.get.e_commerceproject.ui.activity.MyFootprintActivity;
import com.liu.get.e_commerceproject.ui.activity.SetActivity;


/**
 * A simple {@link Fragment} subclass.
 * 个人页面
 *      展示  个人的头像和名字
 *      然后是点击进入详细信息
 *            点击进入个圈子
 *            点击进入个人足迹
 *            点击进入个人钱包等
 *            实现过程  页面跳转
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {


    private SimpleDraweeView mine_image_bg;
    private SimpleDraweeView mine_image_head;
    private TextView mine_text_name;
    private RelativeLayout mine_layout_Information;
    private RelativeLayout mine_layout_Circle;
    private RelativeLayout mine_layout_Footprint;
    private RelativeLayout mine_layout_Wallet;
    private RelativeLayout mine_layout_Address;
    private SharedPreferences mSpd;
    private RelativeLayout me_setting;


    public void initView(View view) {
        me_setting = view.findViewById(R.id.me_setting);
        mine_image_bg = (SimpleDraweeView) view.findViewById(R.id.mine_image_bg);
        mine_image_head = (SimpleDraweeView) view.findViewById(R.id.mine_image_head);
        mine_text_name = (TextView) view.findViewById(R.id.mine_text_name);
        mine_layout_Information = (RelativeLayout) view.findViewById(R.id.mine_layout_Information);
        mine_layout_Circle = (RelativeLayout) view.findViewById(R.id.mine_layout_Circle);
        mine_layout_Footprint = (RelativeLayout) view.findViewById(R.id.mine_layout_Footprint);
        mine_layout_Wallet = (RelativeLayout) view.findViewById(R.id.mine_layout_Wallet);
        mine_layout_Address = (RelativeLayout) view.findViewById(R.id.mine_layout_Address);
        //个人信息
        mine_layout_Information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), InformationActivity.class));
            }
        });
        //圈子
        mine_layout_Circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MyCircleActivity.class));
            }
        });
        //足迹
        mine_layout_Footprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MyFootprintActivity.class));
            }
        });
        //用户钱包
        mine_layout_Wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MyWalletActivity.class));
            }
        });
        //收货地址
        mine_layout_Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MyAddressActivity.class));
            }
        });
        me_setting.setOnClickListener(this);
    }
    @Override
    public void initData(View view) {
        mSpd = getActivity().getSharedPreferences("RemberPwd", Context.MODE_PRIVATE);
        mine_image_bg.setImageURI(mSpd.getString("HeadPic", "http://img3.imgtn.bdimg.com/it/u=2519824424,1132423651&fm=26&gp=0.jpg"));
        mine_image_head.setImageURI(mSpd.getString("HeadPic", "http://img3.imgtn.bdimg.com/it/u=2519824424,1132423651&fm=26&gp=0.jpg"));
        mine_text_name.setText("" + mSpd.getString("nickName", "请登录"));
    }

    @Override
    public int getContent() {
        return R.layout.fragment_mine;
    }


    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(),SetActivity.class));
    }
}
