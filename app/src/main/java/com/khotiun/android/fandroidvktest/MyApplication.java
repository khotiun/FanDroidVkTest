package com.khotiun.android.fandroidvktest;

import android.app.Application;

import com.khotiun.android.fandroidvktest.di.component.ApplicationComponent;
import com.khotiun.android.fandroidvktest.di.component.DaggerApplicationComponent;
import com.khotiun.android.fandroidvktest.di.module.ApplicationModule;
import com.vk.sdk.VKSdk;

/**
 * Created by hotun on 01.10.2017.
 */

public class MyApplication extends Application {

    private static ApplicationComponent sApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initComponent();
        // Необходимо инициализировать SDK при запуске приложения следующим методом, лучше всего это делать в методе onCreate Вашего Application
        VKSdk.initialize(this);
    }

    //метод для инициализации компонента
    private void initComponent() {
        //DaggerApplicationComponent - этот класс генерируется в процессе компиляции проекта. Кодогенерация.
        sApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    //метод для получения компонента
    public static ApplicationComponent getApplicationComponent() {
        return sApplicationComponent;
    }
}
