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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ursberger1 on 11/18/15.
 */
public class BrowseRelPagerActivity extends AppCompatActivity {

    private static final String TAG = "BrowseReleaseActivity";

    private static final String EXTRA_ARTIST_NAME = "com.bignerdranch.android.kaisoapp.artist_name";
    private ViewPager mViewPager;
    private List<Release> mReleases;
    private List<Release> mSelectionList;

    public static Intent newIntent(Context packageContext, String artistName) {
        Intent intent = new Intent(packageContext, BrowseRelPagerActivity.class);
        Log.d(TAG, "in newIntentArtist");
        intent.putExtra(EXTRA_ARTIST_NAME, artistName);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_pager);
        Log.d(TAG, "onCreate called");

        String artistName = (String) getIntent().getSerializableExtra(EXTRA_ARTIST_NAME);

        mViewPager = (ViewPager) findViewById(R.id.activity_fragment_pager_view_pager);

        mReleases = ReleaseArchive.get(this).getReleases();

        mSelectionList = createReleaseSublist(mReleases, artistName);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Release release = mSelectionList.get(position);
                Log.d(TAG, "getItem called");
                return BrowseFragment.newInstance(release.getId());
            }

            @Override
            public int getCount() {
                return mSelectionList.size();
            }

        });

        for (int i = 0; i < mSelectionList.size(); i++) {
            if (mSelectionList.get(i).getArtist().equals(artistName)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }
    protected List<Release> createReleaseSublist(List<Release> mReleases, String artistName) {

        List<Release> mSubList = new ArrayList<>();
        for(Release release : mReleases) {
            if (release.getArtist().equals(artistName)) {
                mSubList.add(release);
            }
        }
        return mSubList;
    }
}
