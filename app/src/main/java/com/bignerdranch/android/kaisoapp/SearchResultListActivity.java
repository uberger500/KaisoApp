package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;

/**
 * Created by ursberger1 on 11/18/15.
 */

public class SearchResultListActivity extends SingleFragmentActivity {

    private static final String EXTRA_ARTIST_SEARCH =
            "com.bignerdranch.android.kaisoapp.artist_search";
    private static final String EXTRA_YEAR_SEARCH =
            "com.bignerdranch.android.kaisoapp.year_search";
    private static final String EXTRA_TITLE_SEARCH =
            "com.bignerdranch.android.kaisoapp.title_search";
    private static final String EXTRA_TRACK_SEARCH =
            "com.bignerdranch.android.kaisoapp.track_search";
    private static final String EXTRA_ARRANGER_SEARCH =
            "com.bignerdranch.android.kaisoapp.arranger_search";
    private static final String EXTRA_GENRE_SEARCH =
            "com.bignerdranch.android.kaisoapp.genre_search";

    public static Intent newIntent(Context packageContext, String searchArtist, String
                                   searchYear, String searchTitle, String searchTrack,
                                   String searchArranger, String searchGenre) {
        Intent intent = new Intent(packageContext, SearchResultListActivity.class);
        intent.putExtra(EXTRA_ARTIST_SEARCH, searchArtist);
        intent.putExtra(EXTRA_YEAR_SEARCH, searchYear);
        intent.putExtra(EXTRA_TITLE_SEARCH, searchTitle);
        intent.putExtra(EXTRA_TRACK_SEARCH, searchTrack);
        intent.putExtra(EXTRA_ARRANGER_SEARCH, searchArranger);
        intent.putExtra(EXTRA_GENRE_SEARCH, searchGenre);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new SearchResultListFragment();
    }
}
