package com.liu.get.e_commerceproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.bean.OrderBeans;
import com.liu.get.e_commerceproject.ui.myview.MyView_Add;

import java.util.ArrayList;

/**
 * date:2018/12/6
 * author:刘振国(Liu)
 * function:
 * 足迹的展示页  用于展示足迹RecycleView
 * 非多条目
 */
public class RecycleView_Adapter_OrderChild extends RecyclerView.Adapter<RecycleView_Adapter_OrderChild.MyHolder> {
    ArrayList<OrderBeans.OrderListBean.DetailListBean> list;
    private int mPage;

    public RecycleView_Adapter_OrderChild() {
        list = new ArrayList<>();

    }

    public void setList(ArrayList<OrderBeans.OrderListBean.DetailListBean> beans, int page) {
        mPage = page;
        list.clear();
        list.addAll(beans);
        notifyDataSetChanged();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        public SimpleDraweeView childorde_item_image;
        public TextView childorde_item_name;
        public TextView childorde_item_price;
        public MyView_Add childorde_item_addsub;

        public MyHolder(View itemView) {
            super(itemView);
            this.childorde_item_image = (SimpleDraweeView) itemView.findViewById(R.id.childorde_item_image);
            this.childorde_item_name = (TextView) itemView.findViewById(R.id.childorde_item_name);
            this.childorde_item_price = (TextView) itemView.findViewById(R.id.childorde_item_price);
            this.childorde_item_addsub = (MyView_Add) itemView.findViewById(R.id.childorde_item_addsub);
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.allorder_child_item, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        String Pictures = list.get(position).getCommodityPic().trim();
        String[] split = Pictures.split(",");
        holder.childorde_item_image.setImageURI(split[0]);

        holder.childorde_item_name.setText(list.get(position).getCommodityName());
        holder.childorde_item_price.setText("" + list.get(position).getCommodityPrice());
        holder.childorde_item_addsub.setNums(list.get(position).getCommodityCount());
        if(mPage>1){
            holder.childorde_item_addsub.setVisibility(View.GONE);
        }
        holder.childorde_item_addsub.setNumChanger(new MyView_Add.NumChanger() {
            @Override
            public void getNumS_(int num) {
                list.get(position).setCommodityCount(num);
                AllNumAndAllMoney();
            }
        });
        AllNumAndAllMoney();
    }

    private void AllNumAndAllMoney() {
        int commodityCount = 0;
        int allMoney = 0;
        for (int i = 0; i < list.size(); i++) {
            commodityCount += list.get(i).getCommodityCount();
            allMoney += list.get(i).getCommodityCount() * list.get(i).getCommodityPrice();

            mChangedData.change(commodityCount, allMoney);
        }

    }

    ChangedData mChangedData;

    public void setChangedData(ChangedData changedData) {
        mChangedData = changedData;
    }

    public interface ChangedData {
        void change(int commodityCount, int allMoney);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
