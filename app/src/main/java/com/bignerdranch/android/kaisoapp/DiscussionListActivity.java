package com.bignerdranch.android.kaisoapp;

import android.support.v4.app.Fragment;

/**
 * Created by ursberger1 on 11/15/15.
 */
//The first activity for the discussions function, return a DiscussionListFragment
public class DiscussionListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new DiscussionListFragment();
    }
}
