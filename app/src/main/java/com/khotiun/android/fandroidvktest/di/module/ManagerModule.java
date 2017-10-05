package com.khotiun.android.fandroidvktest.di.module;

import com.khotiun.android.fandroidvktest.common.manager.MyFragmentManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hotun on 05.10.2017.
 */

@Module
public class ManagerModule {

    @Singleton
    @Provides
    MyFragmentManager provideMyFragmentManager() {
        return new MyFragmentManager();
    }
}
