package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;

/**
 * Created by ursberger1 on 11/13/15.
 */
public class ReleaseListActivity extends SingleFragmentActivity {

    private static final String EXTRA_RELEASE_ID =
            "com.bignerdranch.android.kaisoapp.release_id";

   /* public static Intent newIntent(Context packageContext, UUID releaseId) {
        Intent intent = new Intent(packageContext, ReleaseListActivity.class);
        intent.putExtra(EXTRA_RELEASE_ID, releaseId);
        return intent;
    }
*/
    @Override
    protected Fragment createFragment() {
        Log.d("f", " 2");
        return new ReleaseListFragment();
    }
}
