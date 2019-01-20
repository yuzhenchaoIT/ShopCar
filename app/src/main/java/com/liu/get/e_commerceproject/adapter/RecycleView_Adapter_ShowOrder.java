package com.liu.get.e_commerceproject.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liu.get.e_commerceproject.R;
import com.liu.get.e_commerceproject.bean.OrderBeans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class RecycleView_Adapter_ShowOrder extends RecyclerView.Adapter<RecycleView_Adapter_ShowOrder.MyHolder> {
    ArrayList<OrderBeans.OrderListBean> list;
    private int mMoneyey;

    public RecycleView_Adapter_ShowOrder() {
        list = new ArrayList<>();
    }

    public void setList(ArrayList<OrderBeans.OrderListBean> beans, int page) {
        if (page == 1) {
            list.clear();
        }
        list.addAll(beans);
        notifyDataSetChanged();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView allorder_item_num;
        public TextView allorder_item_date;
        public RecyclerView allorder_item_showRec;
        public TextView allorder_item_allmoney;
        public TextView allorder_item_allnum;
        public Button allorder_item_alldelete;
        public Button allorder_item_allpay;
        public TextView allorder_item_autoname;
        public TextView allorder_item_autonum;
        public RelativeLayout allorder_item_showauto;

        public MyHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            this.allorder_item_num = (TextView) rootView.findViewById(R.id.allorder_item_num);
            this.allorder_item_date = (TextView) rootView.findViewById(R.id.allorder_item_date);
            this.allorder_item_showRec = (RecyclerView) rootView.findViewById(R.id.allorder_item_showRec);
            this.allorder_item_allmoney = (TextView) rootView.findViewById(R.id.allorder_item_allmoney);
            this.allorder_item_allnum = (TextView) rootView.findViewById(R.id.allorder_item_allnum);
            this.allorder_item_alldelete = (Button) rootView.findViewById(R.id.allorder_item_alldelete);
            this.allorder_item_allpay = (Button) rootView.findViewById(R.id.allorder_item_allpay);
            this.allorder_item_autoname = (TextView) rootView.findViewById(R.id.allorder_item_autoname);
            this.allorder_item_autonum = (TextView) rootView.findViewById(R.id.allorder_item_autonum);
            this.allorder_item_showauto = (RelativeLayout) rootView.findViewById(R.id.allorder_item_showauto);
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.allorder_item, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {
        holder.allorder_item_num.setText("订单号:" + list.get(position).getOrderId());
        holder.allorder_item_autoname.setText("快递公司 : " + list.get(position).getExpressCompName());
        holder.allorder_item_autonum.setText("快递单号 : " + list.get(position).getExpressSn());
        //获取当前时间
        Date date = new Date();
        long time = date.getTime();
        long createTime = time;
        java.sql.Date date010 = new java.sql.Date(createTime);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sd.format(date010);
        holder.allorder_item_date.setText(format + "");
        ArrayList<OrderBeans.OrderListBean.DetailListBean> detailList = list.get(position).getDetailList();

        holder.allorder_item_showRec.setNestedScrollingEnabled(false);
        RecycleView_Adapter_OrderChild recycleView_adapter_orderChild = new RecycleView_Adapter_OrderChild();
        holder.allorder_item_showRec.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayout.VERTICAL));
        holder.allorder_item_showRec.setAdapter(recycleView_adapter_orderChild);


        recycleView_adapter_orderChild.setChangedData(new RecycleView_Adapter_OrderChild.ChangedData() {

            @Override
            public void change(int commodityCount, final int allMoney) {
                mMoneyey = allMoney;
                holder.allorder_item_allnum.setText("共计" + commodityCount + "件商品,需要付款");
                holder.allorder_item_allmoney.setText("￥" + allMoney);
                holder.allorder_item_allpay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //支付订单
                        mPayOrder.pay(list.get(position), allMoney,holder.allorder_item_allpay.getText().toString());
                        //Log.e("allorder_item_allpay",holder.allorder_item_allpay.getText().toString());
                    }
                });
            }
        });
        switch (list.get(position).getOrderStatus()) {
            case 0:
                holder.allorder_item_allpay.setText("去支付");
                recycleView_adapter_orderChild.setList(list.get(position).getDetailList(), 0);
                break;
            case 1:
                holder.allorder_item_allpay.setText("去支付");
                recycleView_adapter_orderChild.setList(list.get(position).getDetailList(), 0);
                break;
            case 2:
                holder.allorder_item_showauto.setVisibility(View.VISIBLE);
                holder.allorder_item_alldelete.setVisibility(View.GONE);
                holder.allorder_item_allpay.setText("确认收货");
                recycleView_adapter_orderChild.setList(list.get(position).getDetailList(), 1);
                break;
            case 3:
                holder.allorder_item_alldelete.setVisibility(View.GONE);
                holder.allorder_item_allpay.setText("去评价");
                recycleView_adapter_orderChild.setList(list.get(position).getDetailList(), 2);
                break;
            case 9:
                holder.allorder_item_allpay.setVisibility(View.GONE);
                holder.allorder_item_alldelete.setVisibility(View.GONE);
                holder.allorder_item_allnum.setVisibility(View.GONE);
                holder.allorder_item_allmoney.setVisibility(View.GONE);
                recycleView_adapter_orderChild.setList(list.get(position).getDetailList(), 3);
                break;
        }

        holder.allorder_item_alldelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除订单
                mDeleteOrder.Remove(list.get(position).getOrderId());
            }
        });

    }

    /**
     * 支付
     *
     * @param payOrder
     */
    public void setPayOrder(PayOrder payOrder) {
        mPayOrder = payOrder;
    }

    PayOrder mPayOrder;

    public interface PayOrder {
        void pay(OrderBeans.OrderListBean order, int moneyey, String s);
    }

    /**
     * 删除订单
     *
     * @return
     */

    public void setDeleteOrder(DeleteOrder deleteOrder) {
        mDeleteOrder = deleteOrder;
    }

    DeleteOrder mDeleteOrder;

    public interface DeleteOrder {
        void Remove(String orderId);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
