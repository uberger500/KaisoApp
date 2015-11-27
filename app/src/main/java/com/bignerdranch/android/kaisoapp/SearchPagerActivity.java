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
 * Created by ursberger1 on 11/22/15.
 */
public class SearchPagerActivity extends AppCompatActivity {

    private static final String TAG = "SearchPagerActivity";

    private static final String EXTRA_RELEASE_ID = "com.bignerdranch.android.kaisoapp.release_id";
    private ViewPager mViewPager;
    private List<Release> mSearchList = new ArrayList<>();
    private List<Release> mReleases;
    private Release mSearchedArtist;
    private String mArtistName;

    public static Intent newIntent(Context packageContext, UUID releaseId) {
        Intent intent = new Intent(packageContext, SearchPagerActivity.class);
        intent.putExtra(EXTRA_RELEASE_ID, releaseId);
        Log.d(TAG, "in newIntent");
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_pager);



        UUID releaseId = (UUID) getIntent().getSerializableExtra(EXTRA_RELEASE_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_fragment_pager_view_pager);
        ReleaseArchive releaseArchive = ReleaseArchive.get(this);

        mReleases = releaseArchive.getReleases();
        mSearchedArtist = releaseArchive.getRelease(releaseId);
        mArtistName = mSearchedArtist.getArtist();
        Log.d(TAG, "creating release pager view, new Exception();");
        Log.d(TAG, "artistName is " + mArtistName);
        for (Release release : mReleases)  {
            if (release.getArtist().equals(mArtistName)) {
                mSearchList.add(release);
            }
        }
        Log.d(TAG, "mSearchListsize is " + mSearchList.size());

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Release release = mSearchList.get(position);
                return ReleaseFragment.newInstance(release.getId());
            }

            @Override
            public int getCount() {
                return mSearchList.size();
            }

        });

        for (int i = 0; i < mSearchList.size(); i++) {
            if (mSearchList.get(i).getId().equals(releaseId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
