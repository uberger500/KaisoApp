package com.bignerdranch.android.kaisoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by ursberger1 on 11/13/15.
 */

public class ReleaseListFragment extends Fragment {
    private static final String TAG = "ReleaseListActivity";
    private RecyclerView mReleaseRecyclerView;
    private ReleaseAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Release");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> releaseList, ParseException e) {
                Log.d("f", " 5");
                if (e == null) {
                 //   if (mAdapter == null) {
                        Log.d(TAG, "Retrieved 1 " + releaseList.size() + " releases");
                        mAdapter = new ReleaseAdapter(releaseList);
                        mReleaseRecyclerView.setAdapter(mAdapter);
                //    } else {
                //        mAdapter.setReleases(releaseList);
                //        mAdapter.notifyDataSetChanged();
                //    }

                } else {
                    Log.d("release", "Error: " + e.getMessage());
                }
            }
        });
    }
/*
    @Override
    public void onResume() {
        super.onResume();
        Log.d("f", " 6");
        updateUI();

    }
*/
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
            Intent intent = ReleasePagerActivity.newIntent(getActivity(), mRelease.getObjectId());
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

