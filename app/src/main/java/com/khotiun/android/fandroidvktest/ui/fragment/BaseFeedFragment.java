package com.khotiun.android.fandroidvktest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.khotiun.android.fandroidvktest.R;
import com.khotiun.android.fandroidvktest.common.BaseAdapter;
import com.khotiun.android.fandroidvktest.common.manager.MyLinearLayoutManager;
import com.khotiun.android.fandroidvktest.model.view.BaseViewModel;
import com.khotiun.android.fandroidvktest.mvp.presenter.BaseFeedPresenter;
import com.khotiun.android.fandroidvktest.mvp.view.BaseFeedView;

import java.util.List;

/**
 * Created by hotun on 11.10.2017.
 */

public abstract class BaseFeedFragment extends BaseFragment implements BaseFeedView {

    RecyclerView mRecyclerView;

    BaseAdapter mAdapter;

    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected ProgressBar mProgressBar;

    protected BaseFeedPresenter mBaseFeedPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpSwipeToRefreshLayout(view);
        setUpRecyclerView(view);
        setUpAdapter(mRecyclerView);

        //за содание пресентера отвечает класс наследник
        mBaseFeedPresenter = onCreateFeedPresenter();
        mBaseFeedPresenter.loadStart();
    }

    //для инициализации ресайкл вью и адаптера
    private void setUpRecyclerView(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_list);

        //кастомный лайоатменеджер, который проверяет нужно ли загружать новые элементы
        MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(myLinearLayoutManager);

        //слушает список и оповищает когда он скролица
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
              if (myLinearLayoutManager.isOnnextPagePosition()) {
                  mBaseFeedPresenter.loadNext(mAdapter.getRealItemCount());
              }
            }
        });

        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    //для инициализации адаптера и присваивании его RecycleView
    private void setUpAdapter(RecyclerView recyclerView) {
        mAdapter = new BaseAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    private void setUpSwipeToRefreshLayout (View rootView) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        //для того что бы обновлять данные при свайпе вниз
        mSwipeRefreshLayout.setOnRefreshListener(() -> onCreateFeedPresenter().loadRefresh());
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mProgressBar = getBaseActivity().getProgressBar();
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_feed;
    }

    @Override
    public int onCreateToolbarTitle() {
        return 0;
    }

    @Override
    public void showRefreshing() {

    }

    @Override
    public void hideRefreshing() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showListProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideListProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getBaseActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setItems(List<BaseViewModel> items) {
        mAdapter.setItems(items);
    }

    @Override
    public void addItems(List<BaseViewModel> items) {
        mAdapter.addItems(items);
    }

    //для получения переменной от класса наследника
    protected abstract BaseFeedPresenter onCreateFeedPresenter();
}
