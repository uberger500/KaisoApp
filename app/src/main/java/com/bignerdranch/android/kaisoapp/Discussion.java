package com.bignerdranch.android.kaisoapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by ursberger1 on 11/15/15.
 */
public class Discussion {
    private UUID mId;
    private String mTitle;
    private List<String> mDiscussionPoints;

    public Discussion(UUID id, String title, ArrayList<String> discussionPoints) {
        mId = id;
        mTitle = title;
        mDiscussionPoints = discussionPoints;
    }
//   private Date mDate;
  //  private String mDiscussionPoint;


    public Discussion() {
        this(UUID.randomUUID());
    }
    public Discussion(UUID id) {
        mId = id;
  //      mDate = new Date();
    }

  //  public void setDate(Date date) {
      //  mDate = date;
    //}

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

    public List<String> getDiscussionPoints() {
        return mDiscussionPoints;
    }

    public void setDiscussionPoints(List<String> discussionPoints) {
        mDiscussionPoints = discussionPoints;
    }
}
