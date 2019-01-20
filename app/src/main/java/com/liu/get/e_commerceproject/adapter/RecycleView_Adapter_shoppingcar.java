package com.liu.get.e_commerceproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.bean.ShoppingCarBeans;
import com.liu.get.e_commerceproject.ui.myview.MyView_;
import com.liu.get.e_commerceproject.ui.myview.MyView_Add;

import java.util.ArrayList;

public class RecycleView_Adapter_shoppingcar extends RecyclerView.Adapter<RecycleView_Adapter_shoppingcar.MyHolder> {
    Context mContext;
    ArrayList<ShoppingCarBeans.ResultBean> list;
    ArrayList<Boolean> ischeckeds;

    public RecycleView_Adapter_shoppingcar(Context con, ArrayList<ShoppingCarBeans.ResultBean> beans) {
        mContext = con;
        list = new ArrayList<>();
        ischeckeds = new ArrayList<>();
        list.addAll(beans);
        for (int i = 0; i < list.size(); i++) {
            ischeckeds.add(false);
        }
    }

    public void setAllCheckedCheckBox(boolean isChecked) {
        for (int i = 0; i < ischeckeds.size(); i++) {
            ischeckeds.set(i,isChecked);
        }
        notifyDataSetChanged();
    }

    public ArrayList<ShoppingCarBeans.ResultBean> getCheckedData() {
        ArrayList<ShoppingCarBeans.ResultBean> checkdData = new ArrayList<>();
        for (int i = 0; i < ischeckeds.size(); i++) {
            if(ischeckeds.get(i)){
                checkdData.add(list.get(i));
            }
        }
        return checkdData;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private CheckBox shoppingcar_item_check;
        private SimpleDraweeView shoppingcar_item_image;
        private TextView shoppingcar_item_name;
        private TextView shoppingcar_item_price;
        private MyView_Add shoppingcar_item_addsub;

        public MyHolder(View itemView) {
            super(itemView);
            shoppingcar_item_check = itemView.findViewById(R.id.shoppingcar_item_check);
            shoppingcar_item_image = itemView.findViewById(R.id.shoppingcar_item_image);
            shoppingcar_item_name = itemView.findViewById(R.id.shoppingcar_item_name);
            shoppingcar_item_price = itemView.findViewById(R.id.shoppingcar_item_price);
            shoppingcar_item_addsub = itemView.findViewById(R.id.shoppingcar_item_addsub);
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.shoppingcar_item, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {
        Log.e("数量","onBindViewHolder"+list.get(position).getCount());
        holder.shoppingcar_item_check.setChecked(ischeckeds.get(position));
        holder.shoppingcar_item_image.setImageURI(list.get(position).getPic());
        holder.shoppingcar_item_name.setText(list.get(position).getCommodityName());
        holder.shoppingcar_item_price.setText("￥" + list.get(position).getPrice());
        holder.shoppingcar_item_addsub.setNums(list.get(position).getCount());
        //复选框选中状态变化
        holder.shoppingcar_item_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = holder.shoppingcar_item_check.isChecked();
                ischeckeds.set(position,isChecked);
                Method();
            }
        });
        //当数量发生变化的时候改变数量和总价
        holder.shoppingcar_item_addsub.setNumChanger(new MyView_Add.NumChanger() {
            @Override
            public void getNumS_(int num) {
                list.get(position).setCount(num);
                /*Log.e("数量",""+num);
                Log.e("数量","bean内的"+list.get(position).getCount());*/
                Method();
            }
        });
        Method();

    }

    private void Method() {
        //获取选中条目的总价
        int money=0;
        for (int i = 0; i < ischeckeds.size(); i++) {
            if(ischeckeds.get(i)){
                money += list.get(i).getPrice() * list.get(i).getCount();
            }
        }
        //判断是否否选中
        mOnCheckBoxClick.itemclick(money);
        boolean ische=false;
        for (int i = 0; i < ischeckeds.size(); i++) {
            if(ischeckeds.get(i)){
                ische=true;
            }else{
                ische=false;
                mAllChecked.checked(ische);
                return;
            }
        }
        mAllChecked.checked(ische);
    }

    //复选框选中状态发生变化 回调到购物车页面 更改总价
    OnCheckBoxClick mOnCheckBoxClick;

    public void setOnCheckBoxClick(OnCheckBoxClick onCheckBoxClick) {
        mOnCheckBoxClick = onCheckBoxClick;
    }

    public interface OnCheckBoxClick{
        void itemclick(int money);
    }
    //全选与否
    public void setAllChecked(AllChecked allChecked) {
        mAllChecked = allChecked;
        Method();
    }

    AllChecked mAllChecked;

    public interface AllChecked{
        void checked(boolean checked);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

}
