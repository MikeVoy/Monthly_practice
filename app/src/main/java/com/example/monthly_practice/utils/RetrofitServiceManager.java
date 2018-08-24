package com.example.monthly_practice.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceManager {

    private static final String BASE_URL = "https://www.zhaoapi.cn/";
    private static final  int DEFAULT_TIME_OUT = 5;
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private static Retrofit mRetrofit;

    private static RetrofitServiceManager instance= null;
    //工具类采用单例
    public synchronized static RetrofitServiceManager getInstance(){

        if(instance == null){
           instance = new  RetrofitServiceManager();
        }
        return instance;
    }
    private RetrofitServiceManager(){

        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }



}
