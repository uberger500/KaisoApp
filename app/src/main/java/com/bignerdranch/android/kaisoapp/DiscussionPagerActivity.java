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
import java.util.UUID;

/**
 * Created by ursberger1 on 11/15/15.
 */
public class DiscussionPagerActivity extends AppCompatActivity {
    private static final String TAG = "DiscPagerActivity";

    private static final String EXTRA_DISCUSSION_ID = "com.bignerdranch.android.kaisoapp.discussion_id";
    private ViewPager mViewPager;
    private List<Discussion> mDiscussions;

    public static Intent newIntent(Context packageContext, String discussionId) {
        Intent intent = new Intent(packageContext, DiscussionPagerActivity.class);
        intent.putExtra(EXTRA_DISCUSSION_ID, discussionId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_pager);

        final String discussionId = getIntent().getStringExtra(EXTRA_DISCUSSION_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_fragment_pager_view_pager);

        // mDiscussions = DiscussionArchive.get(this).getDiscussions();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Discussion");
        //  query.whereEqualTo("mArtist", artistName);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> queryList, ParseException e) {
                if (e == null) {
                    Log.d(TAG, "Retrieved " + queryList.size() + " release");
                    final List<ParseObject> discussionList = queryList;
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
                        @Override
                        public Fragment getItem(int position) {
                            ParseObject discussion = discussionList.get(position);
                            Log.d(TAG, "getItem called");
                            return DiscussionFragment.newInstance(discussion.getObjectId());
                        }

                        @Override
                        public int getCount() {
                            return discussionList.size();
                        }

                    });

                    for (int i = 0; i < discussionList.size(); i++) {
                        if (discussionList.get(i).getObjectId().equals(discussionId)) {
                            Log.d(TAG, "in if releaseId is " + discussionId);
                            mViewPager.setCurrentItem(i);
                            break;
                        }
                    }
                } else {
                    Log.d("Discussion", "Error: " + e.getMessage());
                }
            }
        });
    }
}
 /*

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Discussion discussion = mDiscussions.get(position);
                return DiscussionFragment.newInstance(discussion.getObjectId());
            }

            @Override
            public int getCount() {
                return mDiscussions.size();
            }

        });

        for (int i = 0; i < mDiscussions.size(); i++) {
            if (mDiscussions.get(i).getObjectId().equals(discussionId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
*/
/*
  <LinearLayout
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/start_discussion_view">

        <EditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:textAppearance="?android:attr/textAppearanceLarge"
            android:hint="@string/discussion_title"
            android:layout_gravity="left"
            android:id="@+id/discussion_title1"
            android:padding="16dp"/>

        <EditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:id="@+id/discussion_point1"
            android:hint="@string/discussion_point1"
            android:padding="16dp"/>

    </LinearLayout>
 */
