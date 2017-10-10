package com.khotiun.android.fandroidvktest.model.attachment;

import com.vk.sdk.api.model.Identifiable;

/**
 * Created by hotun on 10.10.2017.
 */

public interface Attachment extends Identifiable {

    //поля для каждого типа Attachment
    String getType();
}
