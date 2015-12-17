package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by ursberger1 on 11/22/15.
 */

public class SearchPagerActivity extends AppCompatActivity {

    private static final String TAG = "SearchPagerActivity";

    private static final String EXTRA_RELEASE_ID = "com.bignerdranch.android.kaisoapp.release_id";
    private static final String EXTRA_ARTIST_NAME = "com.bignerdranch.android.kaisoapp.artist_name";

    private ViewPager mViewPager;

    private List<ParseObject> releaseList;

    public static Intent newIntent(Context packageContext, String objectId, String artistName) {
        Intent intent = new Intent(packageContext, SearchPagerActivity.class);
        intent.putExtra(EXTRA_RELEASE_ID, objectId);
        intent.putExtra(EXTRA_ARTIST_NAME, artistName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_pager);

        final String objectId = getIntent().getStringExtra(EXTRA_RELEASE_ID);
        final String artistName = getIntent().getStringExtra(EXTRA_ARTIST_NAME);

        mViewPager = (ViewPager) findViewById(R.id.activity_fragment_pager_view_pager);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Release");
        query.fromLocalDatastore();
        query.orderByAscending("mArtist");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> queryList, ParseException e) {
                if (e == null) {
                    Log.d(TAG, "Retrieved " + queryList.size() + " release");
                    releaseList = queryList;
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
                        @Override
                        public Fragment getItem(int position) {
                            ParseObject release = releaseList.get(position);
                            return BrowseFragment.newInstance(release.getObjectId());
                        }

                        @Override
                        public int getCount() {
                            return releaseList.size();
                        }

                    });

                    for (int i = 0; i < releaseList.size(); i++) {

                        if (releaseList.get(i).getObjectId().equals(objectId)) {
                            mViewPager.setCurrentItem(i);
                            break;
                        }
                    }
                } else {
                    Log.d("browse", "Error: " + e.getMessage());
                }
            }

            /*
            public void done(List<ParseObject> scoreList,
                             ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size());
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
            */
        });


    }

}

     /*   ParseQuery<ParseObject> query = ParseQuery.getQuery("Release");
        query.whereEqualTo("mArtist", artistName);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> queryList, ParseException e) {
                if (e == null) {
                    Log.d(TAG, "Retrieved " + queryList.size() + " release");
                    final List<ParseObject> releaseList = queryList;
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
                        @Override
                        public Fragment getItem(int position) {
                            ParseObject release = releaseList.get(position);
                            return BrowseFragment.newInstance(release.getObjectId());
                        }

                        @Override
                        public int getCount() {
                            return releaseList.size();
                        }

                    });

                    for (int i = 0; i < releaseList.size(); i++) {

                        if (releaseList.get(i).getObjectId().equals(objectId)) {
                            mViewPager.setCurrentItem(i);
                            break;
                        }
                    }
                } else {
                    Log.d("browse", "Error: " + e.getMessage());
                }
            }
        });
*/




// ReleaseArchive releaseArchive = ReleaseArchive.get(this);

//  mReleases = releaseArchive.getReleases();
//   mSearchedArtist = releaseArchive.getRelease(releaseId);
// mArtistName = mSearchedArtist.getArtist();
  /*      Log.d(TAG, "creating release pager view, new Exception();");
        Log.d(TAG, "artistName is " + mArtistName);
        for (ParseObject release : mReleases)  {
            if (release.getString("mArtist").equals(mArtistName)) {
                mSearchList.add(release);
            }
        }
        Log.d(TAG, "mSearchListsize is " + mSearchList.size());

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                ParseObject release = mSearchList.get(position);
                return ReleaseFragment.newInstance(release.getObjectId());
            }

            @Override
            public int getCount() {
                return mSearchList.size();
            }

        });

        for (int i = 0; i < mSearchList.size(); i++) {
            if (mSearchList.get(i).getObjectId().equals(releaseId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
        */