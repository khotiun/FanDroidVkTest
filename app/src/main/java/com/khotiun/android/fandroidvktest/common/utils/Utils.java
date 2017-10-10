package com.khotiun.android.fandroidvktest.common.utils;

import com.khotiun.android.fandroidvktest.model.attachment.ApiAttachment;
import com.vk.sdk.api.model.VKAttachments;

import java.util.List;

/**
 * Created by hotun on 10.10.2017.
 */

public class Utils {

    //будет ковертировать список из Attachment в строку шрифтов иконок
    public static String convertAttachmentsToFrontIcons(List<ApiAttachment> attachments) {
        String attachmentsString = "";

        for (ApiAttachment attachment : attachments) {
            switch (attachment.getType()) {
                case VKAttachments.TYPE_PHOTO:
                    attachmentsString += new String(new char[]{0xE251}) + " ";
                    break;
                case VKAttachments.TYPE_AUDIO:
                    attachmentsString += new String(new char[]{0xE310}) + " ";
                    break;
                case VKAttachments.TYPE_VIDEO:
                    attachmentsString += new String(new char[]{0xE02C}) + " ";
                    break;
                case VKAttachments.TYPE_LINK:
                    attachmentsString += new String(new char[]{0xE250}) + " ";
                    break;
                case VKAttachments.TYPE_DOC:
                    attachmentsString += new String(new char[]{0xE24D}) + " ";
                    break;
            }
        }
        return attachmentsString;
    }
}
