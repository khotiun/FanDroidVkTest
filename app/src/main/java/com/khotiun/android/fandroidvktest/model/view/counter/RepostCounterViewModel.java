package com.khotiun.android.fandroidvktest.model.view.counter;

import com.khotiun.android.fandroidvktest.model.Reposts;

/**
 * Created by hotun on 10.10.2017.
 */

public class RepostCounterViewModel extends CounterViewModel{

    private Reposts mReposts;

    public RepostCounterViewModel(Reposts reposts) {
        super(reposts.getCount());

        this.mReposts = reposts;
        if (mReposts.getUserReposted() ==1 ) {
            setAccentColor();
        }
    }

    public Reposts getReposts() {
        return mReposts;
    }
}
