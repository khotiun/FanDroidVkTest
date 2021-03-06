package com.khotiun.android.fandroidvktest.mvp.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.khotiun.android.fandroidvktest.model.view.BaseViewModel;
import com.khotiun.android.fandroidvktest.mvp.view.BaseFeedView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hotun on 12.10.2017.
 */

public abstract class BaseFeedPresenter<V extends BaseFeedView> extends MvpPresenter<V> {

    public static final int START_PAGE_SIZE = 15;
    public static final int NEXT_PAGE_SIZE = 5;

    private boolean mIsInLoading;

    //будет инициировать загрузку данных с помощью onCreateDataObserveble
    //взависимости от статуса загрузки будут вызываться соответствующие методы onLoadStart, onLoadFinish
    public void loadData(ProgressType progressType, int offset, int count) {
        if (mIsInLoading) {
            return;
        }
        mIsInLoading = true;
        onCreateLoadDataObservable(count, offset)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    onLoadingStart(progressType);
                })
                .doFinally(() -> {
                    onLoadingFinish(progressType);
                })
                .subscribe(repositories -> {
                    onLoadingSuccess(progressType, repositories);
                }, error -> {
                    error.printStackTrace();
                    onLoadingFailed(error);
                });
    }

    //для создания Observable, который излучает данные из сети
    public abstract Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset);

    public enum ProgressType {
        Refreshing, ListProgress, Paging
    }

    //в зависимости от типа загрузки будет показывать различные прогресс бары
    public void showProgress(ProgressType progressType) {
        switch (progressType) {
            case Refreshing:
                getViewState().showRefreshing();
                break;
            case ListProgress:
                getViewState().showListProgress();
                break;
        }
    }

    public void hideProgress(ProgressType progressType) {
        switch (progressType) {
            case Refreshing:
                getViewState().hideRefreshing();
                break;
            case ListProgress:
                getViewState().hideListProgress();
                break;
        }
    }

    //методы будут вызывать loadData с различными параметрами зависищями от типа загрузки
    //вызывается при первой загрузки
    public void loadStart() {
        loadData(ProgressType.ListProgress, 0, START_PAGE_SIZE);
    }

    //вызывается при прокрутки новых элементов при прокрутки
    public void loadNext(int offset) {
        loadData(ProgressType.Paging, offset, NEXT_PAGE_SIZE);
    }

    //при обновлении списка
    public void loadRefresh() {
        loadData(ProgressType.Refreshing, 0, START_PAGE_SIZE);
    }

    public void onLoadingStart(ProgressType progressType) {
        showProgress(progressType);
    }

    public void onLoadingFinish(ProgressType progressType) {
        mIsInLoading = false;
        hideProgress(progressType);
    }

    public void onLoadingFailed(Throwable throwable) {
        getViewState().showError(throwable.getMessage());
    }

    public void onLoadingSuccess(ProgressType progressType, List<BaseViewModel> items) {
        if (progressType == ProgressType.Paging) {
            getViewState().addItems(items);
        } else {
            getViewState().setItems(items);
        }
    }


}
