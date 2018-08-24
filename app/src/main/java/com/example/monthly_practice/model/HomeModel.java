package com.example.monthly_practice.model;
import android.util.Log;

import com.example.monthly_practice.bean.BannerBean;
import com.example.monthly_practice.bean.CatagoryBean;
import com.example.monthly_practice.bean.DesignerBean;
import com.example.monthly_practice.bean.ProductBean;
import com.example.monthly_practice.contract.HomeContract;
import com.example.monthly_practice.utils.ReUtils;
import com.example.monthly_practice.utils.RequestInterface;
import com.example.monthly_practice.utils.RxUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeModel implements HomeContract.Model {
    private static final String TAG = HomeModel.class.getSimpleName();

    @Override
    public void reqeustBanner(final BannerListener bannerListener) {
        RequestInterface requestInterface = RxUtils.setService(RequestInterface.class);
        final Observable<BannerBean> banner = requestInterface.getBanner();

        banner.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BannerBean>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(BannerBean bannerBean) {
                        //Logger.d(bannerBean.getCode());
                       bannerListener.onSuccess(bannerBean.getData());
                    }
                });
    }

    @Override
    public void reqeustDesigner(String version, final DesignerListener designerListener) {
        RequestInterface requestInterface = ReUtils.setService(RequestInterface.class);
        final Observable<DesignerBean> designer = requestInterface.getDesigner(version);

        designer.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DesignerBean>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(DesignerBean designerBean) {
                        designerListener.onSuccess(designerBean.getData().getDisplay());
                    }
                });

    }

    @Override
    public void reqeustCatagory(final CatatgoryListener catatgoryListener) {
        RequestInterface requestInterface = RxUtils.setService(RequestInterface.class);
        Observable<CatagoryBean> product = requestInterface.getProduct();
        product.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CatagoryBean>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CatagoryBean catagoryBean) {
                       catatgoryListener.onSuccess(catagoryBean.getData());
                    }
                });
    }

    @Override
    public void reqeustDetail(final DetailListener detailListener) {
        RequestInterface requestInterface = RxUtils.setService(RequestInterface.class);
        Observable<ProductBean> detail = requestInterface.getDetail("1");

        detail.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProductBean>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(ProductBean productBean) {
                        Log.i(TAG, "onNext: "+productBean.getCode()+"==========");
                        detailListener.onSuccess(productBean.getData());
                    }
                });

    }


}
