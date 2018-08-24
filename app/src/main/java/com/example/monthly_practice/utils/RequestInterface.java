package com.example.monthly_practice.utils;

import com.example.monthly_practice.bean.BannerBean;
import com.example.monthly_practice.bean.CatagoryBean;
import com.example.monthly_practice.bean.DesignerBean;
import com.example.monthly_practice.bean.ProductBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface RequestInterface {

    @POST("quarter/getAd")
    Observable<BannerBean> getBanner(
     );
    @POST("product/getCatagory")
    Observable<CatagoryBean> getProduct(
    );

    @POST("product/getProducts")
    Observable<ProductBean> getDetail(
            @Query("pscid") String pscid
    );
    @POST("api.php/api/Lists/designer")
    Observable<DesignerBean>getDesigner(
      @Query("version") String version
    );





}
