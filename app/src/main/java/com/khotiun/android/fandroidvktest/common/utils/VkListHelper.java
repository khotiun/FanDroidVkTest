package com.khotiun.android.fandroidvktest.common.utils;

import com.khotiun.android.fandroidvktest.model.Owner;
import com.khotiun.android.fandroidvktest.model.WallItem;
import com.khotiun.android.fandroidvktest.rest.model.response.ItemWithSendersResponse;

import java.util.List;

/**
 * Created by hotun on 09.10.2017.
 */

public class VkListHelper {
    //для заполнения полей Sender.name, Sender.photo для отправителя записи,
    //а так же для отправителя репоста, если запись являеться репостом
    public static List<WallItem> getWallList(ItemWithSendersResponse<WallItem> response) {
        List<WallItem> wallItems = response.items;

        for (WallItem wallItem : wallItems) {
            Owner sender = response.getSender(wallItem.getFromId());
            wallItem.setSenderName(sender.getFullName());
            wallItem.setSenderPhoto(sender.getPhoto());

            if (wallItem.haveSharedRepost()) {
                Owner repostSender = response.getSender(wallItem.getSharedRepost().getFromId());
                wallItem.getSharedRepost().setSenderName(repostSender.getFullName());
                wallItem.getSharedRepost().setSenderPhoto(repostSender.getPhoto());
            }
        }
        return wallItems;
    }
}
