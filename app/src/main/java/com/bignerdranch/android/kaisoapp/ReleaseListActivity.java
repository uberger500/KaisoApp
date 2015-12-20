package com.bignerdranch.android.kaisoapp;

import android.support.v4.app.Fragment;

/**
 * Created by ursberger1 on 11/13/15.
 */
//The initial new release activity that creates a ReleaseListFragment
public class ReleaseListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ReleaseListFragment();
    }
}
