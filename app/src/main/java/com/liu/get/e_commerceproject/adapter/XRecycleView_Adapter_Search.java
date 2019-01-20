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
import com.liu.get.e_commerceproject.bean.SearchBean;

import java.util.ArrayList;

/**
 * date:2018/12/6
 * author:刘振国(Liu)
 * function:
 *      XRecycleView 的adapter
 *      用于搜索页
 */
public class XRecycleView_Adapter_Search extends RecyclerView.Adapter<XRecycleView_Adapter_Search.MyHolder> {
    Context mContext;
    ArrayList<SearchBean.ResultBean> list;
    public XRecycleView_Adapter_Search(Context con){
        mContext = con;
        list = new ArrayList<>();
    }

    public void setList(ArrayList<SearchBean.ResultBean> beans,int page) {
        if(page == 1){
            list.clear();
        }
        list.addAll(beans);
        notifyDataSetChanged();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView home_quality_simple;
        TextView home_quality_name, home_quality_price,home_quality_id;
        public MyHolder(View itemView) {
            super(itemView);
            home_quality_simple = (SimpleDraweeView)itemView.findViewById(R.id.home_quqlity_simple);
            home_quality_name = (TextView)itemView.findViewById(R.id.home_quqlity_name);
            home_quality_price = (TextView)itemView.findViewById(R.id.home_quqlity_price);
            home_quality_id = (TextView)itemView.findViewById(R.id.home_quqlity_id);
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext,R.layout.home_quqlity_item, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.home_quality_simple.setImageURI(list.get(position).getMasterPic());
        holder.home_quality_name.setText(list.get(position).getCommodityName());
        holder.home_quality_price.setText("￥"+list.get(position).getPrice());
        holder.home_quality_id.setText(list.get(position).getCommodityId()+"");

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
