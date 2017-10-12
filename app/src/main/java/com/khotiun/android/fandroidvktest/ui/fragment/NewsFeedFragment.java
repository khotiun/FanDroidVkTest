package com.khotiun.android.fandroidvktest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.khotiun.android.fandroidvktest.MyApplication;
import com.khotiun.android.fandroidvktest.R;
import com.khotiun.android.fandroidvktest.common.utils.VkListHelper;
import com.khotiun.android.fandroidvktest.model.view.BaseViewModel;
import com.khotiun.android.fandroidvktest.model.view.NewsItemBodyViewModel;
import com.khotiun.android.fandroidvktest.model.view.NewsItemFooterViewModel;
import com.khotiun.android.fandroidvktest.model.view.NewsItemHeaderViewModel;
import com.khotiun.android.fandroidvktest.rest.api.WallApi;
import com.khotiun.android.fandroidvktest.rest.model.request.WallGetRequestModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hotun on 07.10.2017.
 */

public class NewsFeedFragment extends BaseFeedFragment {

    private static final String TAG = "NewsFeedFragment";

    @Inject
    WallApi mWallApi;



    public NewsFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(TAG, "onActivityCreated");
        //flatMap - принимает данные излучамые одним Observable, возвращает данные излучаемые другим Observable
        //в данном случае принимает WallGetResponse, возвращает WallItem
        mWallApi.get(new WallGetRequestModel(-86529522).toMap())
                .flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
                .flatMap(wallItem -> {
                    List<BaseViewModel> baseItems = new ArrayList<>();
                    baseItems.add(new NewsItemHeaderViewModel(wallItem));
                    baseItems.add(new NewsItemBodyViewModel(wallItem));
                    baseItems.add(new NewsItemFooterViewModel(wallItem));
                    return Observable.fromIterable(baseItems);
                })
                .toList()//преобразовывает все элементы излучаемые rx цепочкой в Observable - который излучает единственный элемент список из этих элементов
                //передаем BaseViewModel, а возвращаем <List<BaseViewModel>>, для того что бы подавать элементы в адаптер парционно на каждую загрузку новый список
                .subscribeOn(Schedulers.io())//поток в котором будет выполняться процесс Observable (в нашем случае все преобразования данных)
                .observeOn(AndroidSchedulers.mainThread())//поток в котором будут выполняться все последующие операции над излученными данными переданными в этот метод
                .subscribe(objects -> mAdapter.addItems(objects));



    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }
}
