package com.khotiun.android.fandroidvktest.model.view.counter;

import com.khotiun.android.fandroidvktest.R;

/**
 * Created by hotun on 10.10.2017.
 * будет содержать цвета и значения для заполнения вью счетчика
 */

public class CounterViewModel {

    protected int mCount;
    protected int mIconColor = R.color.colorIconDis;
    protected int mTextColor = R.color.colorIconDis;

    public CounterViewModel(int count) {
        this.mCount = count;
        if (mCount > 0) {
            setDefaultColor();
        } else {
            setDisabledColor();
        }
    }

    private void setDisabledColor() {
        mIconColor = R.color.colorIconDis;
        mTextColor = R.color.colorIconDis;
    }

    private void setDefaultColor() {
        mIconColor = R.color.colorIcon;
        mTextColor = R.color.colorIcon;
    }

    protected void setAccentColor() {
        mIconColor = R.color.colorAccent;
        mTextColor = R.color.colorAccent;
    }

    public int getCount() {
        return mCount;
    }

    public int getIconColor() {
        return mIconColor;
    }

    public int getTextColor() {
        return mTextColor;
    }
}
