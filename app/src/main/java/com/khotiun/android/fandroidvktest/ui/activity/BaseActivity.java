package com.khotiun.android.fandroidvktest.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.khotiun.android.fandroidvktest.MyApplication;
import com.khotiun.android.fandroidvktest.R;
import com.khotiun.android.fandroidvktest.common.manager.MyFragmentManager;
import com.khotiun.android.fandroidvktest.ui.fragment.BaseFragment;

import javax.inject.Inject;

/**
 * Created by hotun on 02.10.2017.
 * MvpAppCompatActivity - выполняет роль вью в модели MVP
 */

public abstract class BaseActivity extends MvpAppCompatActivity {

    @Inject //Dagger будет брать инициализацию из manadger model
    MyFragmentManager mMyFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getApplicationComponent().inject(this);

        setContentView(R.layout.activity_base);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FrameLayout parent = (FrameLayout) findViewById(R.id.main_wrapper);
        getLayoutInflater().inflate(getMainContentLayout(), parent);
    }

    //@LayoutRes - предпологает что метод будет возвращать id layout
    @LayoutRes
    protected abstract int getMainContentLayout();

    //будем вызывать при смене фрагмента, будет менять заголовок тулбара и видимость кнопки FAB
    public void fragmentOnScreen(BaseFragment fragment) {
        setToolbarTitle(fragment.createToolbarTitle(this));
    }

    //для установки заголовка тулбара
    public void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    //метод для установки корневого элемента
    public void setContent(BaseFragment fragment) {
        mMyFragmentManager.setFragment(this, fragment, R.id.main_wrapper);
    }

    //метод для установки дополнительных фрагментов
    public void addContent(BaseFragment fragment) {
        mMyFragmentManager.addFragment(this, fragment, R.id.main_wrapper);
    }

    //для удаления текущего фрагмента
    public boolean removeCurrentFragment() {
        return mMyFragmentManager.removeCurrentFragment(this);
    }

    //для удаления фрагмента
    public boolean removeFragment(BaseFragment fragment) {
        return mMyFragmentManager.removeFragment(this, fragment);
    }

    @Override
    public void onBackPressed() {
        removeCurrentFragment();
    }
}
