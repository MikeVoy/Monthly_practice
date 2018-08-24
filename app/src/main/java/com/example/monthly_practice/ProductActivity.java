package com.example.monthly_practice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.monthly_practice.adapter.ProductAdapter;
import com.example.monthly_practice.bean.BannerBean;
import com.example.monthly_practice.bean.CatagoryBean;
import com.example.monthly_practice.bean.DesignerBean;
import com.example.monthly_practice.bean.ProductBean;
import com.example.monthly_practice.contract.HomeContract;
import com.example.monthly_practice.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductActivity extends AppCompatActivity implements HomeContract.View {

    @BindView(R.id.product_rv)
    RecyclerView productRv;
    private HomePresenter homePresenter;
    private ProductAdapter productAdapter;
    List<ProductBean.DataBean> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);

        productRv.setLayoutManager(new LinearLayoutManager(ProductActivity.this,LinearLayoutManager.VERTICAL,false));
        homePresenter = new HomePresenter(this);
        homePresenter.requestDetail();
    }

    @Override
    public void onDetail(List<ProductBean.DataBean> products) {

        if(products.size()>0){
            mList.clear();
        }
        mList.addAll(products);

        productAdapter = new ProductAdapter(this,mList);
        Toast.makeText(this,mList.size()+"",Toast.LENGTH_SHORT).show();
         productRv.setAdapter(productAdapter);

    }

    @Override
    public void onBanner(List<BannerBean.DataBean> bannerBeans) {
    }

    @Override
    public void onError() {
    }

    @Override
    public void onDesigner(List<DesignerBean.DataBean.DisplayBean> displayBeans) {
    }


    @Override
    public void onCatagory(List<CatagoryBean.DataBean> dataBeans) {

    }
}
