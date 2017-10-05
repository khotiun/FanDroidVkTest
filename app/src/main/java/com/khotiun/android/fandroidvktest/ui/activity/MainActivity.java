package com.khotiun.android.fandroidvktest.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.khotiun.android.fandroidvktest.CurrentUser;
import com.khotiun.android.fandroidvktest.MyApplication;
import com.khotiun.android.fandroidvktest.R;
import com.khotiun.android.fandroidvktest.consts.ApiConstans;
import com.khotiun.android.fandroidvktest.mvp.presenter.MainPresenter;
import com.khotiun.android.fandroidvktest.mvp.view.MainView;
import com.khotiun.android.fandroidvktest.ui.fragment.NewsFeedFragment;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class MainActivity extends BaseActivity implements MainView{

    //внедрение презентера
    @InjectPresenter
    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getApplicationComponent().inject(this);
        Log.d("QQQQQ" , "onCreate");
        mPresenter.checkAuth();
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //для авторизации пользователя
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                Log.d("QQQQQ" , "onResult");
            // Пользователь успешно авторизовался
                mPresenter.checkAuth();
            }
            @Override
            public void onError(VKError error) {
            // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
                Log.d("QQQQQ" , "onError");
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void startSignIn() {
        //код для авторизации
        VKSdk.login(this, ApiConstans.DEFAULT_LOGIN_SCOPE);
    }

    @Override
    public void signedId() {
        Toast.makeText(this, "Current user id: " + CurrentUser.getId(), Toast.LENGTH_LONG).show();
        setContent(new NewsFeedFragment());
    }
}
