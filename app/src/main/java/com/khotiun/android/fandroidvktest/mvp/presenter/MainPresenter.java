package com.khotiun.android.fandroidvktest.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.khotiun.android.fandroidvktest.CurrentUser;
import com.khotiun.android.fandroidvktest.mvp.view.MainView;

/**
 * Created by hotun on 01.10.2017.
 */
//для привязки ViewState
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    //проверяется условие если пользователь не авторизован
    public void checkAuth() {
        if (!CurrentUser.isAuthorized()) {
            getViewState().startSignIn();
         } else {
            getViewState().signedId();
        }
    }
}
