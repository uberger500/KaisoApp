package com.bignerdranch.android.kaisoapp;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ursberger1 on 11/15/15.
 */
public class Discussion {
    private UUID mId;
    private String mTitle;
    private Date mDate;


    public Discussion() {
        this(UUID.randomUUID());
    }
    public Discussion(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
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
