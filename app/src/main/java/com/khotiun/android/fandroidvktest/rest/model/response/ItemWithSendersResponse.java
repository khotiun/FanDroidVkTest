package com.khotiun.android.fandroidvktest.rest.model.response;

import com.khotiun.android.fandroidvktest.model.Group;
import com.khotiun.android.fandroidvktest.model.Owner;
import com.khotiun.android.fandroidvktest.model.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hotun on 09.10.2017.
 * нужен для того что бы парсить не только item, но и profile, group
 */

public class ItemWithSendersResponse<T> extends BaseItemResponse<T> {

    private List<Profile> mProfiles = new ArrayList<>();
    private List<Group> groups = new ArrayList<>();

    private List<Profile> getProfiles() {
        return mProfiles;
    }

    private List<Group> getGroups() {
        return groups;
    }

    //для получения списка отправителей
    private List<Owner> getAllSenders() {
        List<Owner> all = new ArrayList<>();
        all.addAll(getProfiles());
        all.addAll(getGroups());
        return all;
    }

    //метод получения конкретного отправителя
    public Owner getSender (int id) {
        for (Owner owner : getAllSenders()) {
            if (owner.getId() == Math.abs(id)) {
                return owner;
            }
        }
        return null;
    }
}
