package com.khotiun.android.fandroidvktest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.khotiun.android.fandroidvktest.MyApplication;
import com.khotiun.android.fandroidvktest.R;
import com.khotiun.android.fandroidvktest.common.utils.VkListHelper;
import com.khotiun.android.fandroidvktest.model.WallItem;
import com.khotiun.android.fandroidvktest.model.view.BaseViewModel;
import com.khotiun.android.fandroidvktest.model.view.NewsItemBodyViewModel;
import com.khotiun.android.fandroidvktest.model.view.NewsItemFooterViewModel;
import com.khotiun.android.fandroidvktest.model.view.NewsItemHeaderViewModel;
import com.khotiun.android.fandroidvktest.rest.api.WallApi;
import com.khotiun.android.fandroidvktest.rest.model.request.WallGetRequestModel;
import com.khotiun.android.fandroidvktest.rest.model.response.WallGetResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
        mWallApi.get(new WallGetRequestModel(-86529522).toMap()).flatMap(new Function<WallGetResponse, ObservableSource<WallItem>>() {
            @Override
            public ObservableSource<WallItem> apply(@NonNull WallGetResponse wallGetResponse) throws Exception {
                return io.reactivex.Observable.fromIterable(VkListHelper.getWallList(wallGetResponse.response));
            }
        })
                //в данном случае принимает WallItem, возвращает BaseViewModel
                .flatMap(new Function<WallItem, ObservableSource<BaseViewModel>>() {
                    @Override
                    public ObservableSource<BaseViewModel> apply(@NonNull WallItem wallItem) throws Exception {
                        List<BaseViewModel> baseItems = new ArrayList<>();
                        baseItems.add(new NewsItemHeaderViewModel(wallItem));
                        baseItems.add(new NewsItemBodyViewModel(wallItem));
                        baseItems.add(new NewsItemFooterViewModel(wallItem));

                        return io.reactivex.Observable.fromIterable(baseItems);
                    }
                })
                .toList()//преобразовывает все элементы излучаемые rx цепочкой в Observable - который излучает единственный элемент список из этих элементов
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<BaseViewModel>>() {
                    @Override
                    public void accept(List<BaseViewModel> objects) throws Exception {
                        mAdapter.addItems(objects);
                    }
                });



    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }
}
