package com.khotiun.android.fandroidvktest.rest.model.response;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hotun on 05.10.2017.
 */

public class BaseItemResponse<T> {

    public Integer count;
    public List<T> items = new ArrayList<>();

    public Integer getCount() {
        return count;
    }

    public List<T> getItems() {
        return items;
    }
}
