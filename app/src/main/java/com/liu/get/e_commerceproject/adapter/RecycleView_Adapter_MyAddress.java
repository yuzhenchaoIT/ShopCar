package com.liu.get.e_commerceproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.bean.AddressBean;

import java.util.ArrayList;

/**
 * date:2018/12/6
 * author:刘振国(Liu)
 * function:
 * 主页 hot栏目的展示
 * 非多条目
 */
public class RecycleView_Adapter_MyAddress extends RecyclerView.Adapter<RecycleView_Adapter_MyAddress.MyHolder> {
    Context mContext;
    ArrayList<AddressBean.ResultBean> list;

    public RecycleView_Adapter_MyAddress(Context con, ArrayList<AddressBean.ResultBean> beans) {
        mContext = con;
        list = new ArrayList<>();
        list.addAll(beans);
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView myaddress_item_name;
        private TextView myaddress_item_phone;
        private TextView myaddress_item_address;
        private RadioButton myaddress_item_moren;
        private Button myaddress_item_delete;
        private Button myaddress_item_update;

        public MyHolder(View itemView) {
            super(itemView);
            myaddress_item_name = itemView.findViewById(R.id.myaddress_item_name);
            myaddress_item_phone = itemView.findViewById(R.id.myaddress_item_phone);
            myaddress_item_address = itemView.findViewById(R.id.myaddress_item_address);
            myaddress_item_moren = itemView.findViewById(R.id.myaddress_item_moren);
            myaddress_item_delete = itemView.findViewById(R.id.myaddress_item_delete);
            myaddress_item_update = itemView.findViewById(R.id.myaddress_item_update);
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.myaddress_item, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.myaddress_item_name.setText(list.get(position).getRealName());
        holder.myaddress_item_phone.setText(list.get(position).getPhone());
        holder.myaddress_item_address.setText(list.get(position).getAddress());
        int whetherDefault = list.get(position).getWhetherDefault();
        if(whetherDefault == 1){
            holder.myaddress_item_moren.setChecked(true);
        }else{
            holder.myaddress_item_moren.setChecked(false);
        }
        //删除
        holder.myaddress_item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeletethisData.Delete(list.get(position).getId());
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        //修改默认
        holder.myaddress_item_moren.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (int i = 0; i < list.size(); i++) {
                    if(position == i){
                        list.get(position).setWhetherDefault(1);
                    }else{
                        list.get(position).setWhetherDefault(2);
                    }
                }
                mMyGetMorenData.MorenData(list.get(position).getWhetherDefault());
            }
        });
        holder.myaddress_item_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpdateAddress.MorenData(list.get(position));
            }
        });
    }
    //删除
    DeletethisData mDeletethisData;
    public void setDeletethisData(DeletethisData deletethisData) {
        mDeletethisData = deletethisData;
    }
    public interface DeletethisData{
        void Delete(int id);
    }
    /**
     * 设置默认
     */
    MyGetMorenData mMyGetMorenData;
    public void setMorenData(MyGetMorenData morenData) {
        mMyGetMorenData = morenData;
    }
    public interface MyGetMorenData{
        void MorenData(int id);
    }
    /**
     * 修改按钮
     */
    UpdateAddress mUpdateAddress;
    public void setUpdateAddress(UpdateAddress updateAddress) {
        mUpdateAddress = updateAddress;
    }
    public interface UpdateAddress{
        void MorenData(AddressBean.ResultBean beans);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
