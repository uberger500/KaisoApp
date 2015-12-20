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
 * Created by ursberger1 on 11/18/15.
 */
//The second screen for the browse activity shows a pagerview for all the releases for the
    //chosen artist
public class BrowseRelPagerActivity extends AppCompatActivity {

    private static final String TAG = "BrowseReleaseActivity";

    private static final String EXTRA_ARTIST_NAME = "com.bignerdranch.android.kaisoapp.artist_name";
    private ViewPager mViewPager;

    public static Intent newIntent(Context packageContext, String artistName) {
        Intent intent = new Intent(packageContext, BrowseRelPagerActivity.class);
        intent.putExtra(EXTRA_ARTIST_NAME, artistName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_pager);

        final String artistName = getIntent().getStringExtra(EXTRA_ARTIST_NAME);

        mViewPager = (ViewPager) findViewById(R.id.activity_fragment_pager_view_pager);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Release");
        query.whereEqualTo("mArtist", artistName);
        query.orderByAscending("mArtist");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> queryList, ParseException e) {
                if (e == null) {
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
                        if (releaseList.get(i).getString("mArtist").equals(artistName)) {
                            mViewPager.setCurrentItem(i);
                            break;
                        }
                    }
                } else {
                    Log.d("browse", "Error: " + e.getMessage());
                }
            }
        });
    }
}


