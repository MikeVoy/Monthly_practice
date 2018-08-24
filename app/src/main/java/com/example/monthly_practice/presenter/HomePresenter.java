package com.example.monthly_practice.presenter;

import com.example.monthly_practice.bean.BannerBean;
import com.example.monthly_practice.bean.CatagoryBean;
import com.example.monthly_practice.bean.DesignerBean;
import com.example.monthly_practice.bean.ProductBean;
import com.example.monthly_practice.contract.HomeContract;
import com.example.monthly_practice.model.HomeModel;

import java.util.List;
import java.util.logging.Logger;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;
    private HomeModel model;

    public HomePresenter(HomeContract.View mView) {
        this.mView = mView;
        this.model = new HomeModel();
    }

    @Override
    public void requestBanner() {

        model.reqeustBanner(new HomeContract.Model.BannerListener() {
            @Override
            public void onSuccess(List<BannerBean.DataBean> bannerBeans) {
             //   com.orhanobut.logger.Logger.i(bannerBeans.size()+"");
                mView.onBanner(bannerBeans);
            }

            @Override
            public void onError() {

            }
        });

    }

    @Override
    public void onDestory() {
        if(mView !=null){
            mView = null;
        }
    }

    @Override
    public void requestDesigner(String version) {
               model.reqeustDesigner(version, new HomeContract.Model.DesignerListener() {
                   @Override
                   public void onSuccess(List<DesignerBean.DataBean.DisplayBean> displayBeans) {
                       mView.onDesigner(displayBeans);
                   }
                   @Override
                   public void onError() {

                   }
               });
    }

    @Override
    public void requestCatagory() {
        model.reqeustCatagory(new HomeContract.Model.CatatgoryListener() {
            @Override
            public void onSuccess(List<CatagoryBean.DataBean> displayBeans) {
                mView.onCatagory(displayBeans);
            }

            @Override
            public void onError() {

            }
        });

    }

    @Override
    public void requestDetail() {
        model.reqeustDetail(new HomeContract.Model.DetailListener() {
            @Override
            public void onSuccess(List<ProductBean.DataBean> detailBeans) {
                 mView.onDetail(detailBeans);
            }

            @Override
            public void onError() {

            }
        });
    }
}
