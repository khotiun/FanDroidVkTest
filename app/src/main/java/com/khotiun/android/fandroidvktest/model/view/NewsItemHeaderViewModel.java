package com.khotiun.android.fandroidvktest.model.view;

import android.view.View;

import com.khotiun.android.fandroidvktest.model.WallItem;
import com.khotiun.android.fandroidvktest.ui.holder.BaseViewHolder;
import com.khotiun.android.fandroidvktest.ui.holder.NewsItemHeaderHolder;

/**
 * Created by hotun on 09.10.2017.
 * для хедера
 */

public class NewsItemHeaderViewModel extends BaseViewModel {

    //переменная для идентификатора
    private int mId;

    //для фото и имени отправителя
    private String mProfilePhoto;
    private String mProfileName;
    //является ли запись репостом, если да то меняеться способ отображения хедера
    private boolean mIsRepost;
    //для автора репоста
    private String mRepostProfileName;

    public NewsItemHeaderViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();
        this.mProfilePhoto = wallItem.getSenderPhoto();
        this.mProfileName = wallItem.getSenderName();
        this.mIsRepost = wallItem.haveSharedRepost();

        if (mIsRepost) {
            this.mRepostProfileName = wallItem.getSharedRepost().getSenderName();
        }
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemHeader;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemHeaderHolder(view);
    }

    public int getId() {
        return mId;
    }

    public String getProfilePhoto() {
        return mProfilePhoto;
    }

    public String getProfileName() {
        return mProfileName;
    }

    public boolean isRepost() {
        return mIsRepost;
    }

    public String getRepostProfileName() {
        return mRepostProfileName;
    }

    @Override
    public boolean isItemDecorator() {
        return true;
    }
}
