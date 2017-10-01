package com.khotiun.android.fandroidvktest;

import android.app.Application;

import com.vk.sdk.VKSdk;

/**
 * Created by hotun on 01.10.2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Необходимо инициализировать SDK при запуске приложения следующим методом, лучше всего это делать в методе onCreate Вашего Application
        VKSdk.initialize(this);
    }
}
