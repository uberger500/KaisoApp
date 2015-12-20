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
//The third screen in the search process shows a pagerview for the chosen release
public class SearchPagerActivity extends AppCompatActivity {

    private static final String TAG = "SearchPagerActivity";

    private static final String EXTRA_RELEASE_ID = "com.bignerdranch.android.kaisoapp.release_id";

    private ViewPager mViewPager;

    private List<ParseObject> releaseList;


    public static Intent newIntent(Context packageContext, String objectId) {
        Intent intent = new Intent(packageContext, SearchPagerActivity.class);
        intent.putExtra(EXTRA_RELEASE_ID, objectId);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_pager);

        final String objectId = getIntent().getStringExtra(EXTRA_RELEASE_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_fragment_pager_view_pager);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Release");
        //the local store is queried
        query.fromLocalDatastore();
        query.orderByAscending("mArtist");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> queryList, ParseException e) {
                if (e == null) {
                    releaseList = queryList;
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
                        @Override
                        public Fragment getItem(int position) {
                            ParseObject release = releaseList.get(position);
                            //the standard BrowseFragment is used for the pager view
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

