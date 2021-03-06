package com.khotiun.android.fandroidvktest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hotun on 09.10.2017.
 */

public class Profile implements Owner {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("photo_50")
    @Expose
    private String photo50;

    @SerializedName("photo_100")
    @Expose
    private String photo100;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("sex")
    @Expose
    private int sex;

    @SerializedName("screen_name")
    @Expose
    private String screenName;


    @SerializedName("online")
    @Expose
    private int online;

    @SerializedName("hidden")
    @Expose
    private int hidden;

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto50() {
        return photo50;
    }

    public void setPhoto50(String photo50) {
        this.photo50 = photo50;
    }

    public String getPhoto100() {
        return photo100;
    }

    public void setPhoto100(String photo100) {
        this.photo100 = photo100;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getHidden() {
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }

    @Override
    public String getFullName() {
        return null;
    }

    @Override
    public String getPhoto() {
        return null;
    }

    @Override
    public int getId() {
        return 0;
    }
}
