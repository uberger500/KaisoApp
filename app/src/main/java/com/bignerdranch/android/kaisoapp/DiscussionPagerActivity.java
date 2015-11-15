package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by ursberger1 on 11/15/15.
 */
public class DiscussionPagerActivity extends AppCompatActivity {
 
    private static final String EXTRA_DISCUSSION_ID = "com.bignerdranch.android.kaisoapp.discussion_id";
    private ViewPager mViewPager;
    private List<Discussion> mDiscussions;

    public static Intent newIntent(Context packageContext, UUID discussionId) {
        Intent intent = new Intent(packageContext, DiscussionPagerActivity.class);
        intent.putExtra(EXTRA_DISCUSSION_ID, discussionId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_pager);

        UUID discussionId = (UUID) getIntent().getSerializableExtra(EXTRA_DISCUSSION_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_fragment_pager_view_pager);

        mDiscussions = DiscussionArchive.get(this).getDiscussions();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Discussion discussion = mDiscussions.get(position);
                return DiscussionFragment.newInstance(discussion.getId());
            }

            @Override
            public int getCount() {
                return mDiscussions.size();
            }

        });

        for (int i = 0; i < mDiscussions.size(); i++) {
            if (mDiscussions.get(i).getId().equals(discussionId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
