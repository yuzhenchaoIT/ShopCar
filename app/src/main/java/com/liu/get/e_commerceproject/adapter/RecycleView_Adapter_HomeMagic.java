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

/**
 * date:2018/12/6
 * author:刘振国(Liu)
 * function:
 *         主页 magic栏目的展示
 *         非多条目
 */
public class RecycleView_Adapter_HomeMagic extends RecyclerView.Adapter<RecycleView_Adapter_HomeMagic.MyHolder> {
    Context mContext;
    ArrayList<HomeRec_DataBean.ResultBean.MlssBean.CommodityListBeanXX> list;
    public RecycleView_Adapter_HomeMagic(Context con, ArrayList<HomeRec_DataBean.ResultBean.MlssBean.CommodityListBeanXX> beans){
        mContext = con;
        list = new ArrayList<>();
        list.addAll(beans);
    }

    class MyHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView home_hot_simple;
        TextView home_magic_name, home_magic_price,home_magic_id;
        public MyHolder(View itemView) {
            super(itemView);
            home_hot_simple = (SimpleDraweeView)itemView.findViewById(R.id.home_magic_simple);
            home_magic_name = (TextView)itemView.findViewById(R.id.home_magic_name);
            home_magic_price = (TextView)itemView.findViewById(R.id.home_magic_price);
            home_magic_id = (TextView)itemView.findViewById(R.id.home_magic_id);
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext,R.layout.home_magic_item, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.home_hot_simple.setImageURI(list.get(position).getMasterPic());
        holder.home_magic_name.setText(list.get(position).getCommodityName());
        holder.home_magic_price.setText("￥"+list.get(position).getPrice());
        holder.home_magic_id.setText(list.get(position).getCommodityId()+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyleViewItenClickListening.ItenClickListening(list.get(position).getCommodityId()+"",
                        list.get(position).getCommodityName(),
                        list.get(position).getPrice()+"");
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
