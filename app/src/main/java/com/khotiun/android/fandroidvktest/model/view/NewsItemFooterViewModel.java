package com.khotiun.android.fandroidvktest.model.view;

import android.view.View;

import com.khotiun.android.fandroidvktest.model.WallItem;
import com.khotiun.android.fandroidvktest.model.view.counter.CommentCounterViewModel;
import com.khotiun.android.fandroidvktest.model.view.counter.LikeCounterViewModel;
import com.khotiun.android.fandroidvktest.model.view.counter.RepostCounterViewModel;
import com.khotiun.android.fandroidvktest.ui.holder.BaseViewHolder;
import com.khotiun.android.fandroidvktest.ui.holder.NewsItemFooterHolder;

/**
 * Created by hotun on 11.10.2017.
 */

public class NewsItemFooterViewModel extends BaseViewModel {

    //переменные для идентификаторов
    private int mId;
    private int ownerId;
    //переменная для даты
    private long mDateLong;

    //переменные для счетчиков
    private LikeCounterViewModel mLikes;
    private CommentCounterViewModel mComments;
    private RepostCounterViewModel mReposts;

    public NewsItemFooterViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();
        this.ownerId = wallItem.getOwnerId();
        this.mDateLong = wallItem.getDate();
        this.mLikes = new LikeCounterViewModel(wallItem.getLikes());
        this.mComments = new CommentCounterViewModel(wallItem.getComments());
        this.mReposts = new RepostCounterViewModel(wallItem.getReposts());
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemFooter;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemFooterHolder(view);
    }

    public long getDateLong() {
        return mDateLong;
    }

    public void setDateLong(long dateLong) {
        mDateLong = dateLong;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public LikeCounterViewModel getLikes() {
        return mLikes;
    }

    public void setLikes(LikeCounterViewModel likes) {
        mLikes = likes;
    }

    public CommentCounterViewModel getComments() {
        return mComments;
    }

    public void setComments(CommentCounterViewModel comments) {
        mComments = comments;
    }

    public RepostCounterViewModel getReposts() {
        return mReposts;
    }

    public void setReposts(RepostCounterViewModel reposts) {
        mReposts = reposts;
    }
}
