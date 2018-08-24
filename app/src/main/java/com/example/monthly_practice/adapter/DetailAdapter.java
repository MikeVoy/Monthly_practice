package com.example.monthly_practice.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.monthly_practice.ProductActivity;
import com.example.monthly_practice.R;
import com.example.monthly_practice.bean.CatagoryBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private Context mContext;
    private List<CatagoryBean.DataBean> catagorybean;

    public DetailAdapter(Context activity, List<CatagoryBean.DataBean> mCatagory) {
        this.mContext = activity;
        this.catagorybean = mCatagory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(mContext).inflate(R.layout.catagory_item, null, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.catagoryItemSdv.setImageURI(catagorybean.get(position).getIcon());
        holder.catagoryItemTvName.setText(catagorybean.get(position).getName());

        holder.clickview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProductActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return catagorybean == null ? 0 : catagorybean.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.catagory_item_sdv)
        SimpleDraweeView catagoryItemSdv;
        @BindView(R.id.catagory_item_tv_name)
        TextView catagoryItemTvName;
        View clickview;

        ViewHolder(View view) {
            super(view);
            this.clickview = view;
            ButterKnife.bind(this, view);
        }
    }
}
