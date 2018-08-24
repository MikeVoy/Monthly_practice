package com.example.monthly_practice.utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxUtils {
    private static final String BASE_URL = "https://www.zhaoapi.cn/";
    private static RxUtils instance = null;
    private void RxUtils(){}

    public static RxUtils getInstance() {
        if(instance == null){
            synchronized (RxUtils.class){
                instance = new RxUtils();
            }
        }
        return instance;
    }
    private static  OkHttpClient.Builder okbuilder = new OkHttpClient.Builder();
 /*   public static void setInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okbuilder.addInterceptor(httpLoggingInterceptor);
    }*/
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
           .client(okbuilder.build())
           .baseUrl(BASE_URL)
           .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
           .addConverterFactory(GsonConverterFactory.create());
    //接受class
    public static <S> S setService(Class<S>service){
        Retrofit build = retrofitBuilder.build();
        S s = build.create(service);
        return s;
    }


}
