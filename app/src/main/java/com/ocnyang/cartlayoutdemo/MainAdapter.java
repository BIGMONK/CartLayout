package com.ocnyang.cartlayoutdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.ocnyang.cartlayout.CartAdapter;
import com.ocnyang.cartlayout.viewholder.CartViewHolder;
import com.ocnyang.cartlayoutdemo.bean.GoodsBean;
import com.ocnyang.cartlayoutdemo.bean.ShopBean;
import com.ocnyang.cartlayoutdemo.viewholder.ChildViewHolder;
import com.ocnyang.cartlayoutdemo.viewholder.GroupViewHolder;

import java.util.List;

public class MainAdapter extends CartAdapter<CartViewHolder> {

    public MainAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    protected CartViewHolder getNormalViewHolder(View itemView) {
        return null;
    }

    @Override
    protected CartViewHolder getGroupViewHolder(View itemView) {
        return (CartViewHolder) new GroupViewHolder(itemView, R.id.checkbox);
    }

    @Override
    protected CartViewHolder getChildViewHolder(View itemView) {
        return (CartViewHolder) (new ChildViewHolder(itemView, R.id.checkbox){
            @Override
            public void onNeedCalculate() {
                if (onCheckChangeListener!=null){
                    onCheckChangeListener.onCalculateChanged(null);
                }
            }
        });
    }

    @Override
    protected int getChildItemLayout() {
        return R.layout.acitvity_main_item_child;
    }

    @Override
    protected int getGroupItemLayout() {
        return R.layout.acitvity_main_item_group;
    }

    @Override
    protected int getNormalItemLayout() {
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof ChildViewHolder) {
            ChildViewHolder childViewHolder = (ChildViewHolder) holder;
            childViewHolder.textView.setText(((GoodsBean) mDatas.get(position)).getGoods_name());
            childViewHolder.textViewPrice.setText(
                    mContext.getString(R.string.rmb_X, ((GoodsBean) mDatas.get(position)).getGoods_price()));
            childViewHolder.textViewNum.setText(String.valueOf(((GoodsBean) mDatas.get(position)).getGoods_amount()));
        } else if (holder instanceof GroupViewHolder) {
            GroupViewHolder groupViewHolder = (GroupViewHolder) holder;
            groupViewHolder.textView.setText(((ShopBean) mDatas.get(position)).getShop_name());
        }
    }
}