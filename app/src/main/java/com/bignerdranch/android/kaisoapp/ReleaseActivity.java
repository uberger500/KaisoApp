package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;


import java.util.UUID;
/*
public class ReleaseActivity extends SingleFragmentActivity {

    private static final String EXTRA_RELEASE_ID =
            "com.bignerdranch.android.kaisoapp.release_id";

    public static Intent newIntent(Context packageContext, UUID releaseId) {
        Intent intent = new Intent(packageContext, ReleaseActivity.class);
        intent.putExtra(EXTRA_RELEASE_ID, releaseId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID releaseId = (UUID) getIntent().getSerializableExtra(EXTRA_RELEASE_ID);
        return ReleaseFragment.newInstance(releaseId);
    }
}
*/