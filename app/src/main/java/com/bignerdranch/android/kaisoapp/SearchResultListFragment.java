package com.bignerdranch.android.kaisoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ursberger1 on 11/18/15.
 */

public class SearchResultListFragment extends Fragment {

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

    private static final String TAG = "SearchResulListFrag";

    private RecyclerView mReleaseRecyclerView;
    private ReleaseAdapter mAdapter;
    private String mArtistSearch;
    private String mYearSearch;
    private String mAlbumTitleSearch;
    private String mTrackSearch;
    private String mArrangerSearch;
    private String mGenreSearch;

    private List<ParseObject> mArtistList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mArtistSearch = getActivity().getIntent().getStringExtra(EXTRA_ARTIST_SEARCH);
        mYearSearch = getActivity().getIntent().getStringExtra(EXTRA_YEAR_SEARCH);
        mAlbumTitleSearch = getActivity().getIntent().getStringExtra(EXTRA_TITLE_SEARCH);
      //  mTrackSearch = getActivity().getIntent().getStringExtra(EXTRA_TRACK_SEARCH);
        mArrangerSearch = getActivity().getIntent().getStringExtra(EXTRA_ARRANGER_SEARCH);
        mGenreSearch = getActivity().getIntent().getStringExtra(EXTRA_GENRE_SEARCH);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        ParseObject.unpinAllInBackground();
        mReleaseRecyclerView = (RecyclerView) view.findViewById(R.id.item_recycler_view);
        mReleaseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }


    private void updateUI() {
        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Release");

        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Release");
        if (mArtistSearch.length() != 0) {
            query1.whereEqualTo("mArtist", mArtistSearch);
            query2.whereEqualTo("mArtistLowercase", mArtistSearch.toLowerCase());
        }
        List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();
        queries.add(query1);
        queries.add(query2);
        ParseQuery<ParseObject> query = ParseQuery.or(queries);

        if (mYearSearch.length() != 0) {
            query.whereEqualTo("mYear", mYearSearch);
        }
        if (mAlbumTitleSearch.length() != 0) {
            query.whereContains("mTitle", mAlbumTitleSearch);
        }
    //    if (mTrackSearch.length() != 0) {
    //        query.whereContains("mTrack", mTrackSearch);
    //    }
        if (mArrangerSearch.length() != 0) {
            query.whereEqualTo("mArranger", mArrangerSearch);
        }
        if (mGenreSearch.length() != 0) {
            query.whereEqualTo("mGenre", mGenreSearch);
        }
     //   query.orderByAscending("mArtist");
        query.orderByAscending("mArtist");

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> releaseList, ParseException e) {
                if (e == null) {
                    if (releaseList.size() == 0) {
                        Toast.makeText(getActivity(), R.string.search_nothing_found, Toast.LENGTH_SHORT).show();
                    }
                    ParseObject.pinAllInBackground(releaseList);

                    if (mAdapter == null) {
                        mAdapter = new ReleaseAdapter(releaseList);
                        mReleaseRecyclerView.setAdapter(mAdapter);
                    } else {
                        mAdapter.setReleases(releaseList);
                        mAdapter.notifyDataSetChanged();
                    }

                } else {
                    Log.d("Search", "Error: " + e.getMessage());
                }
            }
        });
    }

    private class ReleaseHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mReleaseArtistTextView;
        private TextView mReleaseTitleTextView;
        private TextView mReleaseConnectorTextView;

        private ParseObject mRelease;

        public ReleaseHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mReleaseArtistTextView = (TextView) itemView.findViewById(R.id.list_item_release_artist_text_view);
            mReleaseConnectorTextView = (TextView) itemView.findViewById(R.id.list_item_release_connector_text_view);
            mReleaseTitleTextView = (TextView) itemView.findViewById(R.id.list_item_release_title_text_view);
        }

        public void bindRelease(ParseObject release) {

            mRelease = release;
            mReleaseArtistTextView.setText(mRelease.getString("mArtist"));
            mReleaseConnectorTextView.setText(" - ");
            mReleaseTitleTextView.setText(mRelease.getString("mTitle"));

        }

        @Override
        public void onClick(View v) {
          //  Intent intent = ReleasePagerActivity.newIntent(getActivity(), mRelease.getObjectId());
            Intent intent = SearchPagerActivity.newIntent(getActivity(), mRelease.getObjectId(), mRelease.getString("mArtist"));
            startActivity(intent);
        }
    }
    private class ReleaseAdapter extends RecyclerView.Adapter<ReleaseHolder> {

        private List<ParseObject> mReleases;

        public ReleaseAdapter(List<ParseObject> releases) {
            mReleases = releases;
        }

        @Override
        public ReleaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_release, parent, false);
            return new ReleaseHolder(view);
        }

        @Override
        public void onBindViewHolder(ReleaseHolder holder, int position) {
            ParseObject release = mReleases.get(position);
            holder.bindRelease(release);
        }

        @Override
        public int getItemCount() {
            return mReleases.size();
        }

        public  void setReleases(List<ParseObject> releases) {
            mReleases = releases;
        }
    }
    /*
    private class ReleaseHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mReleaseArtistTextView;
        private TextView mReleaseConnectorTextView;
        private TextView mReleaseTitleTextView;
        private ParseObject mRelease;

        public ReleaseHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mReleaseTitleTextView = (TextView) itemView.findViewById(R.id.list_item_release_title_text_view);            mReleaseTitleTextView = (TextView) itemView.findViewById(R.id.list_item_release_title_text_view);
       //     mReleaseTitleTextView = (TextView) itemView.findViewById(R.id.list_item_release_title_text_view);

        }

        public void bindRelease(ParseObject release) {

            mRelease = release;
            mReleaseTitleTextView.setText(mRelease.getString("mTitle"));
        //    mReleaseConnectorTextView.setText(" - ");
          //  mReleaseTitleTextView.setText(mRelease.getString("mTitle"));

        }

        @Override
        public void onClick(View v) {

            Intent intent = SearchPagerActivity.newIntent(getActivity(), mRelease.getObjectId(), mRelease.getString("mArtist"));
            startActivity(intent);
        }
    }


    private class ReleaseAdapter extends RecyclerView.Adapter<ReleaseHolder> {

        private List<ParseObject> mReleases;

        public ReleaseAdapter(List<ParseObject> releases) {
            mReleases = releases;
        }

        @Override
        public ReleaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_release, parent, false);
            return new ReleaseHolder(view);
        }

        @Override
        public void onBindViewHolder(ReleaseHolder holder, int position) {
            ParseObject release = mReleases.get(position);
            holder.bindRelease(release);
        }

        @Override
        public int getItemCount() {
            return mReleases.size();
        }

        public  void setReleases(List<ParseObject> releases) {
            mReleases = releases;
        }
    }*/
}
