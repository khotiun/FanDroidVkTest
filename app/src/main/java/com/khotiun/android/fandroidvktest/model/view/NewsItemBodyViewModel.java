package com.khotiun.android.fandroidvktest.model.view;

import android.view.View;

import com.khotiun.android.fandroidvktest.model.WallItem;
import com.khotiun.android.fandroidvktest.ui.holder.BaseViewHolder;
import com.khotiun.android.fandroidvktest.ui.holder.NewsItemBodyHolder;

/**
 * Created by hotun on 08.10.2017.
 */

public class NewsItemBodyViewModel extends BaseViewModel {

    private int mId;
    private String mText;

    public NewsItemBodyViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();
        this.mText = wallItem.getText();
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemBody;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemBodyHolder(view);
    }

    public int getId() {
        return mId;
    }

    public String getText() {
        return mText;
    }
}
