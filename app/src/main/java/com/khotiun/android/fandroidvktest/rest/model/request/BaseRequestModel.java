package com.khotiun.android.fandroidvktest.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.khotiun.android.fandroidvktest.CurrentUser;
import com.khotiun.android.fandroidvktest.consts.ApiConstans;
import com.vk.sdk.api.VKApiConst;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hotun on 08.10.2017.
 */

public abstract class BaseRequestModel {

    //@SerializedName - для того что бы ретрофит понимал название полей
    @SerializedName(VKApiConst.VERSION)
    Double version = ApiConstans.DEFAULT_VERSION;

    @SerializedName(VKApiConst.ACCESS_TOKEN)
    String accessToken = CurrentUser.getAccessToken();

    public Double getVersion() {
        return version;
    }

    public String getAccessToken() {
        return accessToken;
    }

    //для преобразования полей класса в массив ключ значение
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();

        map.put(VKApiConst.VERSION, String.valueOf(getVersion()));
        if (accessToken != null) {
            map.put(VKApiConst.ACCESS_TOKEN, getAccessToken());
        }

        onMapCreate(map);

        return map;
    }

    //для того чтобы передавать map в дочернии классы
    public abstract void onMapCreate(Map<String, String> map);
}
