package com.bignerdranch.android.kaisoapp;

import java.util.UUID;

/**
 * Created by ursberger1 on 11/15/15.
 */
public class Discussion {
    private UUID mId;
    private String mTitle;


    public Discussion() {
        this(UUID.randomUUID());
    }
    public Discussion(UUID id) {
        mId = id;

    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
