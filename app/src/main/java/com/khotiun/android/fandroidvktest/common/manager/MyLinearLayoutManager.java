package com.khotiun.android.fandroidvktest.common.manager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * Created by hotun on 12.10.2017.
 * класс имеет доступ к вью списка и будет проверять находится ли список в той позиции в которой нам нужно подгружать следующие элементы
 */

public class MyLinearLayoutManager extends LinearLayoutManager {
    public MyLinearLayoutManager(Context context) {
        super(context);
    }

    public MyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public MyLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //для того что бы подгружать новые списки нужно знать количество элементов в адаптере,
    // так как мы делим каждый элемент на 3 количество будет отличатся от реального
    public boolean isOnnextPagePosition() {
        int visibleItemCount = getChildCount();
        int totalItemCount = getItemCount();
        int pastVisibleItems = findFirstVisibleItemPosition();

        return (visibleItemCount + pastVisibleItems) >= totalItemCount / 2;
    }
}
