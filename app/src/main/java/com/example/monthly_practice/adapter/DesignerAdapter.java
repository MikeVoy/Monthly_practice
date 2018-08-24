package com.example.monthly_practice.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.monthly_practice.R;
import com.example.monthly_practice.bean.DesignerBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DesignerAdapter extends RecyclerView.Adapter<DesignerAdapter.ViewHolder> {

    private static final String TAG = DesignerAdapter.class.getSimpleName();
    private Context context;
    private List<DesignerBean.DataBean.DisplayBean> mList;

    public DesignerAdapter(Context activity, List<DesignerBean.DataBean.DisplayBean> mDesplay) {
        this.context = activity;
        this.mList = mDesplay;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.designer_item, null, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: "+mList.get(position).getAddress());
         holder.designerItemSdv.setImageURI(mList.get(position).getAvatar());
         holder.designerItemTvTitle.setText(mList.get(position).getNick_name());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.designer_item_sdv)
        SimpleDraweeView designerItemSdv;
        @BindView(R.id.designer_item_tv_title)
        TextView designerItemTvTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
