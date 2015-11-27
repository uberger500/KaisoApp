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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ursberger1 on 11/18/15.
 */
public class SearchResultListFragment extends Fragment {

    private static final String EXTRA_ARTIST_SEARCH =
            "com.bignerdranch.android.kaisoapp.artist_search";

    private static final String TAG = "SearchResultActivity";

    private RecyclerView mReleaseRecyclerView;
    private ReleaseAdapter mAdapter;
    private String mArtistSearch;

    private List<Release> mArtistList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mArtistSearch = getActivity().getIntent().getStringExtra(EXTRA_ARTIST_SEARCH);

        setHasOptionsMenu(true);
        Log.d(TAG, "creating search List view");
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
        ReleaseArchive releaseArchive = ReleaseArchive.get(getActivity());
        List<Release> releases = releaseArchive.getReleases();

        for (Release release : releases)  {
            if (release.getArtist().equals(mArtistSearch)) {
                mArtistList.add(release);
            }
        }

        mAdapter = new ReleaseAdapter(mArtistList);
        mReleaseRecyclerView.setAdapter(mAdapter);
    }


    private class ReleaseHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {


        private TextView mReleaseTitleTextView;
        private Release mRelease;

        public ReleaseHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mReleaseTitleTextView = (TextView) itemView.findViewById(R.id.list_item_release_title_text_view);
        }

        public void bindRelease(Release release) {

            mRelease = release;
            mReleaseTitleTextView.setText(mRelease.getTitle());

        }

        @Override
        public void onClick(View v) {
            Intent intent = SearchPagerActivity.newIntent(getActivity(), mRelease.getId());
            startActivity(intent);
        }
    }


    private class ReleaseAdapter extends RecyclerView.Adapter<ReleaseHolder> {

        private List<Release> mReleases;

        public ReleaseAdapter(List<Release> releases) {
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
            Release release = mReleases.get(position);
            holder.bindRelease(release);
        }

        @Override
        public int getItemCount() {
            return mReleases.size();
        }
    }
}
