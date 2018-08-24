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
import com.example.monthly_practice.bean.ProductBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context mContext;
    private List<ProductBean.DataBean> bean;

    public ProductAdapter(Context activity, List<ProductBean.DataBean> mData) {
        this.mContext = activity;
        this.bean = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(mContext).inflate(R.layout.detail_item, null, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String images = bean.get(position).getImages();
        String[] split = images.split("\\|");
        holder.detailItemSdv.setImageURI(split[1]);
        holder.detailItemTvName.setText(bean.get(position).getSubhead());

  /*      holder.clickview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProductActivity.class);
                mContext.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.detail_item_sdv)
        SimpleDraweeView detailItemSdv;
        @BindView(R.id.detail_item_tv_name)
        TextView detailItemTvName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
