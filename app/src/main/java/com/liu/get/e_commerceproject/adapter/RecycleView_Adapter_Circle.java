package com.liu.get.e_commerceproject.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.bean.CircleBeans;
import com.liu.get.e_commerceproject.bean.DetailsCommentsBeans;
import com.liu.get.e_commerceproject.util.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class RecycleView_Adapter_Circle extends RecyclerView.Adapter<RecycleView_Adapter_Circle.MyHolder> {
    Context mContext;
    ArrayList<CircleBeans.ResultBean> list;
    boolean check=true;
    public void setShow() {
        check = true;
        notifyDataSetChanged();
    }

    public void setHind() {
        check = false;
        notifyDataSetChanged();
    }
    public RecycleView_Adapter_Circle(Context con) {
        mContext = con;
        list = new ArrayList<>();

    }

    public void setList(ArrayList<CircleBeans.ResultBean> beans,int page) {
        if(page == 1){
            list.clear();
        }
        list.addAll(beans);
        notifyDataSetChanged();
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
        Button del;
        CheckBox zan;
        public MyHolder(View itemView, int viewType) {
            super(itemView);
            del = itemView.findViewById(R.id.details_comments_item_check);
            zan = itemView.findViewById(R.id.details_comments_item_zan);
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
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {
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
        if(!check){
            holder.del.setVisibility(View.VISIBLE);
        }else{
            holder.del.setVisibility(View.GONE);
        }
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = list.get(position).getId();
                Log.e("WD",""+id);
                list.remove(position);
                notifyDataSetChanged();
                mMyCircleDelete.Delete(id);
            }
        });

        holder.zan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(holder.zan, "scaleX", 1f, 1.5f, 1f);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(holder.zan, "scaleY", 1f, 1.5f, 1f);
                scaleX.setDuration(700);
                scaleY.setDuration(700);
                AnimatorSet animSet = new AnimatorSet();
                animSet.playTogether(scaleX,scaleY);
                animSet.start();
            }
        });

    }
    MyCircleDelete mMyCircleDelete;
    public interface MyCircleDelete{
        void Delete(int id);
    }

    public void setMyCircleDelete(MyCircleDelete myCircleDelete) {
        mMyCircleDelete = myCircleDelete;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
