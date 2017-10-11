package com.khotiun.android.fandroidvktest.rest;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hotun on 05.10.2017.
 * для инициализации ретрофита, и сервисов api запросов
 */

public class RestClient {

    //константа для хранения url запросов к api
    private static final String VK_BASE_URL = "https://api.vk.com/method/";

    private Retrofit mRetrofit;

    public RestClient() {
        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//для получения observable вместо call
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(VK_BASE_URL)
                .build();
    }

    //переменная для инициализации rest api сервисов
    public <S> S createService(Class<S> serviceClass) {
        return mRetrofit.create(serviceClass);
    }
}
