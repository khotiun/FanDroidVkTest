package com.khotiun.android.fandroidvktest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.khotiun.android.fandroidvktest.CurrentUser;
import com.khotiun.android.fandroidvktest.MyApplication;
import com.khotiun.android.fandroidvktest.R;
import com.khotiun.android.fandroidvktest.rest.api.WallApi;
import com.khotiun.android.fandroidvktest.rest.model.request.WallGetRequestModel;
import com.khotiun.android.fandroidvktest.rest.model.response.WallGetResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hotun on 07.10.2017.
 */

public class NewsFeedFragment extends BaseFragment {

    @Inject
    WallApi mWallApi;

    public NewsFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("NewsFeedFragment", "onCreate");
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("NewsFeedFragment", "onActivityCreated" + mWallApi.toString());
        mWallApi.get(new WallGetRequestModel(-86529522).toMap()).enqueue(new Callback<WallGetResponse>() {
            @Override
            public void onResponse(Call<WallGetResponse> call, Response<WallGetResponse> response) {
                Log.d("NewsFeedFragment", "onResponse");
                Toast.makeText(getActivity(), "Likes: " + response.body().response.getItems().get(0).getLikes().getCount(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<WallGetResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_feed;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }
}
