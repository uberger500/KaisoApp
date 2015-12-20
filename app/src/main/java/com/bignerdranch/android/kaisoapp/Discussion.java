package com.bignerdranch.android.kaisoapp;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by ursberger1 on 11/15/15.
 */

//The discussion object that is transformed to a ParseObject for the database
@ParseClassName("Discussion")
public class Discussion extends ParseObject{

    public Discussion() {}

    public List<String> getPoints() {
        return (List<String>) get("mDiscussionPoints");
    }

    public void setPoints(List<String> points) {
        put("mDiscussionPoints", points);
    }

    public String getTitle() {
        return getString("mTitle");
    }

    public void setTitle(String title) {
        put("mTitle", title);
    }

}
