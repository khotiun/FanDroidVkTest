package com.khotiun.android.fandroidvktest.model.view.counter;

import com.khotiun.android.fandroidvktest.model.Comments;

/**
 * Created by hotun on 10.10.2017.
 */

public class CommentCounterViewModel extends CounterViewModel {

    private Comments mComments;

    public CommentCounterViewModel(Comments comments) {
        super(comments.getCount());

        this.mComments = comments;
    }

    public Comments getComments() {
        return mComments;
    }
}
