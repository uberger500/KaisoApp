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

    public static Intent newIntent(Context packageContext, UUID releaseId) {
        Intent intent = new Intent(packageContext, ReleasePagerActivity.class);
        intent.putExtra(EXTRA_RELEASE_ID, releaseId);
        Log.d(TAG, "in newIntnet");
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_pager);

        Log.d(TAG, "creating release pager view, new Exception();" );

        UUID releaseId = (UUID) getIntent().getSerializableExtra(EXTRA_RELEASE_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_fragment_pager_view_pager);

        mReleases = ReleaseArchive.get(this).getReleases();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Release release = mReleases.get(position);
                return ReleaseFragment.newInstance(release.getId());
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
        }
    }
}
