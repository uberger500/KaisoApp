package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.parse.ParseObject;

import java.util.UUID;

/**
 * Created by ursberger1 on 11/13/15.
 */
public class ReleaseListActivity extends SingleFragmentActivity {

    @Override
    protected void onPostResume() {
        super.onPostResume();
      //  ParseObject.unpinAllInBackground("releaseList");
    }
    @Override
    protected Fragment createFragment() {
        return new ReleaseListFragment();
    }
}
