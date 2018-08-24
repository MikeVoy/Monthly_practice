package com.example.monthly_practice.contract;

import com.example.monthly_practice.bean.BannerBean;
import com.example.monthly_practice.bean.CatagoryBean;
import com.example.monthly_practice.bean.DesignerBean;
import com.example.monthly_practice.bean.ProductBean;

import java.util.List;

public interface HomeContract {
    interface Model {

        interface BannerListener{
            void onSuccess(List<BannerBean.DataBean> bannerBeans);
            void onError();
        }
        void reqeustBanner(BannerListener bannerListener);

        interface DesignerListener{
            void onSuccess(List<DesignerBean.DataBean.DisplayBean> displayBeans);
            void onError();
        }
        void reqeustDesigner(String version, DesignerListener designerListener);

        interface CatatgoryListener{
            void onSuccess(List<CatagoryBean.DataBean> displayBeans);
            void onError();
        }
        void reqeustCatagory( CatatgoryListener catatgoryListener);

        interface DetailListener{
            void onSuccess(List<ProductBean.DataBean> detailBeans);
            void onError();
        }
        void reqeustDetail( DetailListener detailListener);

    }
    interface View {

        void onBanner(List<BannerBean.DataBean> bannerBeans);
        void onError();
        void onDesigner(List<DesignerBean.DataBean.DisplayBean> displayBeans);
        void onDetail(List<ProductBean.DataBean> products);
        void onCatagory(List<CatagoryBean.DataBean> dataBeans);
    }

    interface Presenter {
        void requestBanner();
        void onDestory();
        void requestDesigner(String version);
        void requestCatagory();

        void requestDetail();
    }
}
