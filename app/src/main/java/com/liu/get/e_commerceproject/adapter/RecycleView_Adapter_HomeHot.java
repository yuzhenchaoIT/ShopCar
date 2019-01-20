package com.liu.get.e_commerceproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.bean.HomeRec_DataBean;

import java.util.ArrayList;


public class RecycleView_Adapter_HomeHot extends RecyclerView.Adapter<RecycleView_Adapter_HomeHot.MyHolder> {
    Context mContext;
    ArrayList<HomeRec_DataBean.ResultBean.RxxpBean.CommodityListBean> list;

    public RecycleView_Adapter_HomeHot(Context con, ArrayList<HomeRec_DataBean.ResultBean.RxxpBean.CommodityListBean> beans) {
        mContext = con;
        list = new ArrayList<>();
        list.addAll(beans);
    }

    class MyHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView home_hot_simple;
        TextView home_hot_name, home_hot_price, home_hot_id;

        public MyHolder(View itemView) {
            super(itemView);
            home_hot_simple = (SimpleDraweeView) itemView.findViewById(R.id.home_hot_simple);
            home_hot_name = (TextView) itemView.findViewById(R.id.home_hot_name);
            home_hot_price = (TextView) itemView.findViewById(R.id.home_hot_price);
            home_hot_id = (TextView) itemView.findViewById(R.id.home_hot_id);
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.home_hot_item, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.home_hot_simple.setImageURI(list.get(position).getMasterPic());
        holder.home_hot_name.setText(list.get(position).getCommodityName());
        holder.home_hot_price.setText("￥" + list.get(position).getPrice() + "");
        holder.home_hot_id.setText(list.get(position).getCommodityId() + "");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyleViewItenClickListening.ItenClickListening(list.get(position).getCommodityId() + "",
                        list.get(position).getCommodityName(),
                        list.get(position).getPrice() + "");
            }
        });
    }

    RecyleViewItenClickListening mRecyleViewItenClickListening;

    public void setRecyleViewItenClickListening(RecyleViewItenClickListening recyleViewItenClickListening) {
        mRecyleViewItenClickListening = recyleViewItenClickListening;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
