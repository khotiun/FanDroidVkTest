package com.khotiun.android.fandroidvktest.di.module;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hotun on 05.10.2017.
 * отвечает за предоставления контекста
 */

//@Module - нужна для потки класса и формирования графа зависимостей. В этом классе определен набор методов провайд (постащиков)
@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application)  {
        mApplication = application;
    }

    @Singleton //обьект будет в одном экземпляре
    @Provides  //предаставляет нужный обьест для внедрения зависимостей, который можно за инджектить в нужном месте
    public Context provideContext() {
        return mApplication;
    }

    //для внедрения шрифта, теперь текст для счетчиков будет преобразован в иконки
    //Typeface нужен для того что бы TextView понимал какой тип шрифта ему использовать
    @Singleton
    @Provides
    Typeface provideGoogleTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "MaterialIcons-Regular.ttf");
    }
}
