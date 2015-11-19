package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by ursberger1 on 11/18/15.
 */
public class SearchResultListActivity extends SingleFragmentActivity {

    private static final String EXTRA_ARTIST_SEARCH =
            "com.bignerdranch.android.kaisoapp.artist_search";

    public static Intent newIntent(Context packageContext, String ArtistSearch) {
        Intent intent = new Intent(packageContext, SearchResultListActivity.class);
        intent.putExtra(EXTRA_ARTIST_SEARCH, ArtistSearch);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new SearchResultListFragment();
    }
}
