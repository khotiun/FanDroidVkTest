package com.khotiun.android.fandroidvktest.model.view.counter;

import com.khotiun.android.fandroidvktest.model.Likes;

/**
 * Created by hotun on 10.10.2017.
 * для подсчета лайков
 */

public class LikeCounterViewModel extends CounterViewModel {

    private Likes mLikes;


    public LikeCounterViewModel(Likes likes) {
        super(likes.getCount());

        this.mLikes = likes;

        if (mLikes.getUserLikes() == 1) {
            setAccentColor();
        }
    }

    public Likes getLikes() {
        return mLikes;
    }
}
