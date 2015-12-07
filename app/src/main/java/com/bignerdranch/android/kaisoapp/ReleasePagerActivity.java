package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
import java.util.UUID;

/**
 * Created by ursberger1 on 11/13/15.
 */

public class ReleasePagerActivity extends AppCompatActivity {

    private static final String TAG = "ReleasePagerActivity";

    private static final String EXTRA_RELEASE_ID = "com.bignerdranch.android.kaisoapp.release_id";
    private ViewPager mViewPager;
    private List<Release> mReleases;

    public static Intent newIntent(Context packageContext, String releaseId) {
        Intent intent = new Intent(packageContext, ReleasePagerActivity.class);
        intent.putExtra(EXTRA_RELEASE_ID, releaseId);
        Log.d(TAG, "in newIntent");
        Log.d("f", " 14pagerintent");
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_pager);

        Log.d(TAG, "creating release pager view, new Exception();");

        Log.d("f", " 15oncreate");
        final String releaseId = getIntent().getStringExtra(EXTRA_RELEASE_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_fragment_pager_view_pager);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Release");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> queryList, ParseException e) {
                if (e == null) {
                    Log.d("f", " 16query");
                    Log.d(TAG, "Retrieved 2 " + queryList.size() + " release");
                    final List<ParseObject> releaseList = queryList;
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
                        @Override
                        public Fragment getItem(int position) {
                            ParseObject release = releaseList.get(position);
                            Log.d("f", " 17getitem");
                            Log.d(TAG, "getItem called1");
                            return ReleaseFragment.newInstance(release.getObjectId());
                        }

                        @Override
                        public int getCount() {
                            Log.d("f", " 18getcount");
                            return releaseList.size();
                        }

                    });

                    for (int i = 0; i < releaseList.size(); i++) {
                        Log.d("f", " 19aoutsideloop");

                        if (releaseList.get(i).getObjectId().equals(releaseId)) {
                            Log.d("f", " 19inloop");
                            Log.d(TAG, "in if releaseId is " + releaseId);
                            mViewPager.setCurrentItem(i);
                            break;
                        }
                    }
                } else {
                    Log.d("browse", "Error: " + e.getMessage());
                }
            }
        });
/*
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Release release = mReleases.get(position);
                return ReleaseFragment.newInstance(release.getObjectId());
            }

            @Override
            public int getCount() {
                return mReleases.size();
            }

        });

        for (int i = 0; i < mReleases.size(); i++) {
            if (mReleases.get(i).getId().equals(releaseId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }*/
    }
}
