package com.khotiun.android.fandroidvktest.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.MvpFragment;

/**
 * Created by hotun on 02.10.2017.
 */

public abstract class BaseFragment extends MvpAppCompatFragment {

    @LayoutRes
    protected abstract int getMainContentLayout();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getMainContentLayout(), container, false);
    }

    //для отображения заголовка тулбара
    public String createToolbarTitle(Context context) {
        return context.getString(onCreateToolbarTitle());
    }

    //для запроса заголовка у дочерних элементов
    @StringRes
    public abstract int onCreateToolbarTitle();
}
