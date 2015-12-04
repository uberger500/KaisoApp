package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by ursberger1 on 11/15/15.
 */
public class DiscussionListActivity extends SingleFragmentActivity {

    private static final String EXTRA_DISCUSSION_ID =
            "com.bignerdranch.android.kaisoapp.discussion_id";
/*
    public static Intent newIntent(Context packageContext, UUID userId) {
        Intent intent = new Intent(packageContext, DiscussionListActivity.class);
        intent.putExtra(EXTRA_DISCUSSION_ID, userId);
        return intent;
    }
*/
    @Override
    protected Fragment createFragment() {
        return new DiscussionListFragment();
    }
}
