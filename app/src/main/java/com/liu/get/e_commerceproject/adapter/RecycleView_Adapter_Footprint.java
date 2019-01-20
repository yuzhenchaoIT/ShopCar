package com.liu.get.e_commerceproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.bean.FootprintBeans;

import java.util.ArrayList;

/**
 * date:2018/12/6
 * author:刘振国(Liu)
 * function:
 *         足迹的展示页  用于展示足迹RecycleView
 *         非多条目
 */
public class RecycleView_Adapter_Footprint extends RecyclerView.Adapter<RecycleView_Adapter_Footprint.MyHolder> {
    Context mContext;
    ArrayList<FootprintBeans.ResultBean> list;

    public RecycleView_Adapter_Footprint(Context con) {
        mContext = con;
        list = new ArrayList<>();

    }

    public void setList(ArrayList<FootprintBeans.ResultBean> beans,int page) {
        if(page == 1){
            list.clear();
        }
        list.addAll(beans);
        notifyDataSetChanged();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView home_hot_simple;
        TextView myfootprin_item_name, myfootprin_item_price, myfootprin_item_id,myfootprin_item_num;

        public MyHolder(View itemView) {
            super(itemView);
            home_hot_simple = (SimpleDraweeView) itemView.findViewById(R.id.myfootprin_item_simple);
            myfootprin_item_name = (TextView) itemView.findViewById(R.id.myfootprin_item_name);
            myfootprin_item_price = (TextView) itemView.findViewById(R.id.myfootprin_item_price);
            myfootprin_item_id = (TextView) itemView.findViewById(R.id.myfootprin_item_id);
            myfootprin_item_num = (TextView) itemView.findViewById(R.id.myfootprin_item_num);
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext,R.layout.myfootprin_xrecler_item,null);

        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.home_hot_simple.setImageURI(list.get(position).getMasterPic());
        holder.myfootprin_item_name.setText(list.get(position).getCommodityName());
        holder.myfootprin_item_price.setText("￥" + list.get(position).getPrice() + "");
        holder.myfootprin_item_id.setText(list.get(position).getCommodityId() + "");
        holder.myfootprin_item_num.setText("已浏览"+list.get(position).getBrowseNum()+"次");
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
