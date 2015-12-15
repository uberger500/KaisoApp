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
public class FormatReleasesActivity extends AppCompatActivity {

    private List<String> mArtistList = new ArrayList<>();
    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, FormatReleasesActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Release");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    mArtistList = createArtistList(list);
                    for (String artist : mArtistList) {
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Release");
                        query.whereEqualTo("mArtist", artist);
                        query.addAscendingOrder("mTitle");
                        query.findInBackground(new FindCallback<ParseObject>() {
                            public void done(List<ParseObject> artistReleaselist, ParseException e) {
                                if (e == null) {
                                   for (ParseObject release : artistReleaselist) {

                                       List<String> mTracks = new ArrayList<>();
                                       mTracks.add(release.getString("mTitle"));
                                       Log.d("inloop", " looping");
                                   }

                                } else {
                                    Log.d("error", e.getMessage());
                                }
                            }
                        });
                    }

                } else {
                    Log.d("error", e.getMessage());
                }
            }
        });

    }
    protected List<String> createArtistList(List<ParseObject> releases) {

        List<String> artistList = new ArrayList<>();

        for (ParseObject release : releases) {
            if (!artistList.contains(release.getString("mArtist"))) {
                artistList.add(release.getString("mArtist"));
            }
        }
        return artistList;
    }
}
*/