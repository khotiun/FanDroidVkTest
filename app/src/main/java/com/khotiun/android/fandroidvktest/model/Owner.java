package com.khotiun.android.fandroidvktest.model;

import com.vk.sdk.api.model.Identifiable;

/**
 * Created by hotun on 09.10.2017.
 * для получения полного имени и фото
 */

public interface Owner extends Identifiable {

    String getFullName();
    String getPhoto();
}
