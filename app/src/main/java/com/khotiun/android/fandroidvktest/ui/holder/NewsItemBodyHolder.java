package com.khotiun.android.fandroidvktest.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.khotiun.android.fandroidvktest.R;
import com.khotiun.android.fandroidvktest.model.view.NewsItemBodyViewModel;

/**
 * Created by hotun on 08.10.2017.
 */

public class NewsItemBodyHolder extends BaseViewHolder<NewsItemBodyViewModel>{

    public TextView mText;

    public NewsItemBodyHolder(View itemView) {
        super(itemView);

        mText = (TextView) itemView.findViewById(R.id.tv_text);
    }

    @Override
    public void bindViewHolder(NewsItemBodyViewModel newsItemBodyViewModel) {
        mText.setText(newsItemBodyViewModel.getText());
    }

    @Override
    public void unbindViewHolder() {
        mText.setText(null);
    }
}
