package com.khotiun.android.fandroidvktest.rest.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hotun on 05.10.2017.
 */

public class Full<T> {

    @SerializedName("response")
    @Expose
    public T response;
}
