package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by urs on 12/14/15.
 */
/*
public class InitializeActivity extends AppCompatActivity {

    private int mNumberReleases;
    private int currentItem;
    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, InitializeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Release");
        query.countInBackground(new CountCallback() {
            public void done(int count, ParseException e) {
                if (e == null) {
                    Log.d("counted", count + " releases");
                    mNumberReleases = count;
                    currentItem = 0;
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Release");
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> releaseList, ParseException e) {
                            if (e == null) {
                                for (ParseObject release : releaseList) {
                                    Log.d("counting", " currentitem is " + currentItem);
                                    final List<String> mComments = new ArrayList<>();
                                    release.put("mComments", mComments);
                                    release.saveInBackground();
                                    currentItem = currentItem + 1;
                                }
                                ParseQuery<ParseObject> queryDiscussion = ParseQuery.getQuery("Discussion");
                                queryDiscussion.findInBackground(new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> discussionList, ParseException e) {
                                        if (e == null) {
                                            for (ParseObject discussion : discussionList) {
                                                final List<String> mDiscussionPoints = new ArrayList<>();
                                                discussion.put("mTitle", "");
                                                discussion.put("mDiscussionPoints", mDiscussionPoints);
                                                discussion.remove("mDiscussionTitle");
                                                discussion.deleteInBackground();
                                            }
                                            finish();
                                        } else {
                                            Log.d("score", "Error: " + e.getMessage());
                                        }
                                    }

                                });
                            } else {
                                Log.d("score", "Error: " + e.getMessage());
                            }
                        }
                    });


                    //   }

                } else {
                    Log.d("error", e.getMessage());
                }
            }
        });

    }
}
*/