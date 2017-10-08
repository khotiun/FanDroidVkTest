package com.khotiun.android.fandroidvktest.rest.api;

import com.khotiun.android.fandroidvktest.rest.model.response.WallGetResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by hotun on 05.10.2017.
 */

public interface WallApi {

    @GET(ApiMethods.WALL_GET)
    Call<WallGetResponse> get(@QueryMap Map<String, String> map);
}
