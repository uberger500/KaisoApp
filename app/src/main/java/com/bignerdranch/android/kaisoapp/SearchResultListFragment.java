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
import java.util.UUID;

/**
 * Created by ursberger1 on 11/18/15.
 */

public class SearchResultListFragment extends Fragment {

    private static final String EXTRA_ARTIST_SEARCH =
            "com.bignerdranch.android.kaisoapp.artist_search";

    private static final String TAG = "SearchResulListFrag";

    private RecyclerView mReleaseRecyclerView;
    private ReleaseAdapter mAdapter;
    private String mArtistSearch;

    private List<ParseObject> mArtistList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "create1");

        mArtistSearch = getActivity().getIntent().getStringExtra(EXTRA_ARTIST_SEARCH);

        Log.d(TAG, "mArtistSearch is " + mArtistSearch);
        setHasOptionsMenu(true);
        Log.d(TAG, "create");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        mReleaseRecyclerView = (RecyclerView) view.findViewById(R.id.item_recycler_view);
        mReleaseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }


    private void updateUI() {
        Log.d(TAG, "updateUI");
        Log.d(TAG, "mArtistSearch2 is " + mArtistSearch);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Release");
        query.whereEqualTo("mArtist", mArtistSearch);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> releaseList, ParseException e) {
                if (e == null) {
                    if(releaseList.size() == 0) {
                        Toast.makeText(getActivity(), R.string.search_nothing_found, Toast.LENGTH_SHORT).show();

                    }
                    if (mAdapter == null) {
                        Log.d(TAG, "Retrieved " + releaseList.size() + " releases");
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


        private TextView mReleaseTitleTextView;
        private ParseObject mRelease;

        public ReleaseHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mReleaseTitleTextView = (TextView) itemView.findViewById(R.id.list_item_release_title_text_view);
        }

        public void bindRelease(ParseObject release) {

            mRelease = release;
            Log.d(TAG, "title is " + mRelease.getString("mTitle"));
            mReleaseTitleTextView.setText(mRelease.getString("mTitle"));

        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "clicktitle is " + mRelease.getString("mTitle"));
            Log.d(TAG, "objectId is " + mRelease.getObjectId());

            Intent intent = SearchPagerActivity.newIntent(getActivity(), mRelease.getString("mArtist"));
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
}
