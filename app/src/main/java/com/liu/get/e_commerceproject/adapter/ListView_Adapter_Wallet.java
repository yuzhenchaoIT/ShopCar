package com.liu.get.e_commerceproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.bean.MyWalletBeans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ListView_Adapter_Wallet extends BaseAdapter {
    Context mContext;
    ArrayList<MyWalletBeans.ResultBean.Redata> list;

    public ListView_Adapter_Wallet(Context context, ArrayList<MyWalletBeans.ResultBean.Redata> beans) {
        mContext = context;
        list = new ArrayList<>();
        list.addAll(beans);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.wallet_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.setData(list.get(position));

        return convertView;
    }

    public static class ViewHolder {
        public TextView wallet_item_money;
        public ImageView wallet_item_why;
        public TextView wallet_item_date;

        public ViewHolder(View rootView) {
            this.wallet_item_money = (TextView)rootView.findViewById(R.id.wallet_item_money);
            this.wallet_item_why = (ImageView)rootView.findViewById(R.id.wallet_item_why);
            this.wallet_item_date = (TextView)rootView.findViewById(R.id.wallet_item_date);
        }

        public void setData(MyWalletBeans.ResultBean.Redata bean) {
            wallet_item_money.setText("￥"+bean.getAmount());
            long createTime = bean.getCreateTime();
            java.sql.Date date010 = new java.sql.Date(createTime);
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sd.format(date010);
            Log.e("WD", "createTime---" + format);
            wallet_item_date.setText(format + "");

        }
    }
}
