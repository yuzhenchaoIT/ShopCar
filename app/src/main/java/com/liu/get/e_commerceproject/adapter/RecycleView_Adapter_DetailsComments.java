package com.liu.get.e_commerceproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.bean.DetailsCommentsBeans;
import com.liu.get.e_commerceproject.bean.HomeRec_DataBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class RecycleView_Adapter_DetailsComments extends RecyclerView.Adapter<RecycleView_Adapter_DetailsComments.MyHolder> {
    Context mContext;
    ArrayList<DetailsCommentsBeans.ResultBean> list;

    public RecycleView_Adapter_DetailsComments(Context con, ArrayList<DetailsCommentsBeans.ResultBean> beans) {
        mContext = con;
        list = new ArrayList<>();
        list.addAll(beans);
    }

    @Override
    public int getItemViewType(int position) {
        String images = list.get(position).getImage().trim();
        String[] split = images.split(",");
        if (images.equals("url")) {
            return 0;
        } else if (split.length == 1) {
            return 1;
        } else if (split.length == 2) {
            return 2;
        } else if (split.length == 3) {
            return 3;
        } else {
            return 0;
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView details_comments_item_simpe, details_comments_item_image_1, details_comments_item_image_2, details_comments_item_image_3;
        TextView details_comments_item_name, details_comments_item_date, details_comments_item_data;

        public MyHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == 0) {
                details_comments_item_simpe = itemView.findViewById(R.id.details_comments_item_simpe);

                details_comments_item_name = itemView.findViewById(R.id.details_comments_item_name);
                details_comments_item_date = itemView.findViewById(R.id.details_comments_item_date);
                details_comments_item_data = itemView.findViewById(R.id.details_comments_item_data);
            } else if (viewType == 1) {
                details_comments_item_simpe = itemView.findViewById(R.id.details_comments_item_simpe);

                details_comments_item_name = itemView.findViewById(R.id.details_comments_item_name);
                details_comments_item_date = itemView.findViewById(R.id.details_comments_item_date);
                details_comments_item_data = itemView.findViewById(R.id.details_comments_item_data);

                details_comments_item_image_1 = itemView.findViewById(R.id.details_comments_item_image_1);
            } else if (viewType == 2) {
                details_comments_item_simpe = itemView.findViewById(R.id.details_comments_item_simpe);

                details_comments_item_name = itemView.findViewById(R.id.details_comments_item_name);
                details_comments_item_date = itemView.findViewById(R.id.details_comments_item_date);
                details_comments_item_data = itemView.findViewById(R.id.details_comments_item_data);

                details_comments_item_image_1 = itemView.findViewById(R.id.details_comments_item_image_1);
                details_comments_item_image_2 = itemView.findViewById(R.id.details_comments_item_image_2);
            } else if (viewType == 3) {
                details_comments_item_simpe = itemView.findViewById(R.id.details_comments_item_simpe);

                details_comments_item_name = itemView.findViewById(R.id.details_comments_item_name);
                details_comments_item_date = itemView.findViewById(R.id.details_comments_item_date);
                details_comments_item_data = itemView.findViewById(R.id.details_comments_item_data);

                details_comments_item_image_1 = itemView.findViewById(R.id.details_comments_item_image_1);
                details_comments_item_image_2 = itemView.findViewById(R.id.details_comments_item_image_2);
                details_comments_item_image_3 = itemView.findViewById(R.id.details_comments_item_image_3);
            }
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 0) {
            view = View.inflate(mContext, R.layout.details_comments_item_0, null);
        } else if (viewType == 1) {
            view = View.inflate(mContext, R.layout.details_comments_item_1, null);
        } else if (viewType == 2) {
            view = View.inflate(mContext, R.layout.details_comments_item_2, null);
        } else if (viewType == 3) {
            view = View.inflate(mContext, R.layout.details_comments_item_3, null);
        }
        MyHolder myHolder = new MyHolder(view, viewType);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String images = list.get(position).getImage().trim();
        String[] split = images.split(",");

        long createTime = (long) list.get(position).getCreateTime();
        java.sql.Date date010 = new java.sql.Date(createTime);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sd.format(date010);
        Log.e("WD", "createTime---" + format);
        holder.details_comments_item_simpe.setImageURI(list.get(position).getHeadPic());

        holder.details_comments_item_name.setText(list.get(position).getNickName());
        holder.details_comments_item_date.setText(format + "");
        holder.details_comments_item_data.setText(list.get(position).getContent());
        if(images.equals("url")){
            return;
        }else if (split.length == 1) {
            holder.details_comments_item_image_1.setImageURI(split[0]);
        } else if (split.length == 2) {
            holder.details_comments_item_image_1.setImageURI(split[0]);
            holder.details_comments_item_image_2.setImageURI(split[1]);
        } else if (split.length == 3) {
            holder.details_comments_item_image_1.setImageURI(split[0]);
            holder.details_comments_item_image_2.setImageURI(split[1]);
            holder.details_comments_item_image_3.setImageURI(split[1]);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
