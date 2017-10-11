package com.khotiun.android.fandroidvktest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.khotiun.android.fandroidvktest.R;
import com.khotiun.android.fandroidvktest.common.BaseAdapter;

/**
 * Created by hotun on 11.10.2017.
 */

public class BaseFeedFragment extends BaseFragment {

    RecyclerView mRecyclerView;

    BaseAdapter mAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpRecyclerView(view);
        setUpAdapter(mRecyclerView);
    }

    //для инициализации ресайкл вью и адаптера
    private void setUpRecyclerView(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    //для инициализации адаптера и присваивании его RecycleView
    private void setUpAdapter(RecyclerView recyclerView) {
        mAdapter = new BaseAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_feed;
    }

    @Override
    public int onCreateToolbarTitle() {
        return 0;
    }
}
