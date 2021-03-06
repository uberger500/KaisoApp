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
 * Created by ursberger1 on 11/13/15.
 */
//The second screen of the new release activity displaying a pagerview for the releases
public class ReleasePagerActivity extends AppCompatActivity {

    private static final String TAG = "ReleasePagerActivity";

    private static final String EXTRA_RELEASE_ID = "com.bignerdranch.android.kaisoapp.release_id";
    private ViewPager mViewPager;
    private List<ParseObject> releaseList;

    public static Intent newIntent(Context packageContext, String releaseId) {
        Intent intent = new Intent(packageContext, ReleasePagerActivity.class);
        intent.putExtra(EXTRA_RELEASE_ID, releaseId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_pager);

        final String releaseId = getIntent().getStringExtra(EXTRA_RELEASE_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_fragment_pager_view_pager);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Release");
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> queryList, ParseException e) {
                if (e == null) {
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
                        if (releaseList.get(i).getObjectId().equals(releaseId)) {
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

