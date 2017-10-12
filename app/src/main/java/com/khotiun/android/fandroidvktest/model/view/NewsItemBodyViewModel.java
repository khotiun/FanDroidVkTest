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

    //для отображения аттачментов
    private String mAttachmentString;
    private boolean mIsRepost;

    public NewsItemBodyViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();
        this.mIsRepost = wallItem.haveSharedRepost();

        if (mIsRepost) {
            this.mText = wallItem.getSharedRepost().getText();
            this.mAttachmentString = wallItem.getSharedRepost().getAttachmentsString();
        } else {
            this.mText = wallItem.getText();
            this.mAttachmentString = wallItem.getAttachmentsString();
        }
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

    public String getAttachmentString() {
        return mAttachmentString;
    }

    @Override
    public boolean isItemDecorator() {
        return true;
    }
}
