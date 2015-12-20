package com.bignerdranch.android.kaisoapp;

import android.support.v4.app.Fragment;

/**
 * Created by ursberger1 on 11/15/15.
 */
//The first activity to create a user list
public class UserListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new UserListFragment();
    }
}
