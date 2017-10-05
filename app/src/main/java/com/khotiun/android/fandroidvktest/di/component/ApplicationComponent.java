package com.khotiun.android.fandroidvktest.di.component;

import com.khotiun.android.fandroidvktest.di.module.ApplicationModule;
import com.khotiun.android.fandroidvktest.di.module.ManagerModule;
import com.khotiun.android.fandroidvktest.ui.activity.BaseActivity;
import com.khotiun.android.fandroidvktest.ui.activity.MainActivity;


import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hotun on 05.10.2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ManagerModule.class}) //связывает модули частямии, которые запрашивают зависимости, в данном случае один компонент отвечает за два модуля
public interface ApplicationComponent {

    //методы где будет использоваться внедрение
    void inject(BaseActivity activity);
    void inject(MainActivity activity);

}
