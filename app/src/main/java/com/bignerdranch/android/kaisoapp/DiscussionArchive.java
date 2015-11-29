package com.bignerdranch.android.kaisoapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ursberger1 on 11/15/15.
 */
public class DiscussionArchive {

    private static DiscussionArchive sDiscussionsArchive;

    private List<Discussion> mDiscussions;

    public static DiscussionArchive get(Context context) {
        if (sDiscussionsArchive == null) {
            sDiscussionsArchive = new DiscussionArchive(context);
        }
        return sDiscussionsArchive;
    }

    private DiscussionArchive(Context context) {
        mDiscussions = new ArrayList<>();
  /*      for (int i = 0; i < 5; i++) {
            Discussion discussion = new Discussion();
            discussion.setTitle("Discussion #" + i);
            mDiscussions.add(discussion);
        }*/
    }

    public List<Discussion> getDiscussions() {
        return mDiscussions;
    }

    public Discussion getDiscussion(UUID id) {
        for (Discussion discussion : mDiscussions) {
            if (discussion.getId().equals(id)) {
                return discussion;
            }
        }
        return null;
    }

    public void addDiscussion(Discussion d) {
        mDiscussions.add(d);
    }
/*
    public List<String> selectDiscussionThread(String discussionTitle) {

        List<String> discussionThread = new ArrayList<>();
        for (Discussion discussion : mDiscussions) {
            if (discussion.getTitle().equals(discussionTitle)) {
                discussionThread.add(discussion.getDiscussionPoint());
            }
        }
        return discussionThread;
    }

    public List<String> selectDiscussionTitles() {

        List<String> discussionTitles = new ArrayList<>();

        for (Discussion discussion : mDiscussions) {
            if (discussion.getTitle().contains(discussion.getTitle())) {
                discussionTitles.add(discussion.getTitle());
            }
        }
        return discussionTitles;
    }
    */
}
