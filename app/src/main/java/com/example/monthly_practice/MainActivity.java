package com.example.monthly_practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Adapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.monthly_practice.adapter.DesignerAdapter;
import com.example.monthly_practice.adapter.DetailAdapter;
import com.example.monthly_practice.bean.BannerBean;
import com.example.monthly_practice.bean.CatagoryBean;
import com.example.monthly_practice.bean.DesignerBean;
import com.example.monthly_practice.bean.ProductBean;
import com.example.monthly_practice.contract.HomeContract;
import com.example.monthly_practice.presenter.HomePresenter;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HomeContract.View, XBanner.XBannerAdapter {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.mXbanner)
    XBanner mXbanner;
    @BindView(R.id.home_designer_rv)
    RecyclerView homeDesignerRv;
    @BindView(R.id.home_product_rv)
    RecyclerView homeProductRv;
    private HomePresenter mHomePresenter;
    private List<String> imgesUrl;
    List<DesignerBean.DataBean.DisplayBean> mDesplay = new ArrayList<>();
    List<CatagoryBean.DataBean> mCatagory = new ArrayList<>();
    private DesignerAdapter myAdapter;
    private DetailAdapter mDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //获取引用
        homeDesignerRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        homeProductRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mHomePresenter = new HomePresenter(this);
        mHomePresenter.requestBanner();

    }

    @Override
    public void onBanner(final List<BannerBean.DataBean> bannerBeans) {
       imgesUrl = new ArrayList<>();
        for (int i = 0; i <bannerBeans.size() ; i++) {
            String icon = bannerBeans.get(i).getIcon();
            imgesUrl.add(icon);
            Log.i(TAG, "onBanner: "+imgesUrl.get(i));
        }
        mXbanner.setData(imgesUrl,null);
        mXbanner.setmAdapter(this);



        mXbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                String url = bannerBeans.get(position).getUrl();
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });

        mHomePresenter.requestDesigner(1.7+"");
    }

    @Override
    public void onError() {

    }

    @Override
    public void onDesigner(List<DesignerBean.DataBean.DisplayBean> displayBeans) {

        Log.i(TAG, "onDesigner: "+displayBeans.size());
             if(displayBeans.size()>0){
                     mDesplay.clear();
               }
               mDesplay.addAll(displayBeans);
              Log.i(TAG, "onDesigner: "+mDesplay.size());

              myAdapter = new DesignerAdapter(this,mDesplay);
              homeDesignerRv.setAdapter(myAdapter);

              mHomePresenter.requestCatagory();
    }

    @Override
    public void onDetail(List<ProductBean.DataBean> products) {

    }

    @Override
    public void onCatagory(List<CatagoryBean.DataBean> dataBeans) {
        Log.i(TAG, "onCatagory: "+dataBeans.size());
                    if(dataBeans.size()>0){
                        mCatagory.clear();
                    }mCatagory.addAll(dataBeans);
                   mDetailAdapter = new DetailAdapter(this, mCatagory);

                   homeProductRv.setAdapter(mDetailAdapter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        mXbanner.stopAutoPlay();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mXbanner.startAutoPlay();
    }

    @Override
    public void loadBanner(XBanner banner, Object model, View view, int position) {
        Glide.with(this).load(imgesUrl.get(position)).into((ImageView)view);
    }
}
