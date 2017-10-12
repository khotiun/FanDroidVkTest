package com.khotiun.android.fandroidvktest.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.khotiun.android.fandroidvktest.MyApplication;
import com.khotiun.android.fandroidvktest.common.utils.VkListHelper;
import com.khotiun.android.fandroidvktest.model.view.BaseViewModel;
import com.khotiun.android.fandroidvktest.model.view.NewsItemBodyViewModel;
import com.khotiun.android.fandroidvktest.model.view.NewsItemFooterViewModel;
import com.khotiun.android.fandroidvktest.model.view.NewsItemHeaderViewModel;
import com.khotiun.android.fandroidvktest.mvp.view.BaseFeedView;
import com.khotiun.android.fandroidvktest.rest.api.WallApi;
import com.khotiun.android.fandroidvktest.rest.model.request.WallGetRequestModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by hotun on 12.10.2017.
 * реализует onCreateLoadDataObservable
 */
@InjectViewState
public class NewsFeedPresenter extends BaseFeedPresenter<BaseFeedView> {

    @Inject
    WallApi mWallApi;

    public NewsFeedPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mWallApi.get(new WallGetRequestModel(-86529522, count, offset).toMap())
                .flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
                .flatMap(wallItem -> {
                    List<BaseViewModel> baseItems = new ArrayList<>();
                    baseItems.add(new NewsItemHeaderViewModel(wallItem));
                    baseItems.add(new NewsItemBodyViewModel(wallItem));
                    baseItems.add(new NewsItemFooterViewModel(wallItem));
                    return Observable.fromIterable(baseItems);
                });
    }
}
