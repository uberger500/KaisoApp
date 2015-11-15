package com.bignerdranch.android.kaisoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ursberger1 on 11/13/15.
 */
public class ReleaseListFragment extends Fragment {
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
        View view = inflater.inflate(R.layout.fragment_release_list, container, false);

        mReleaseRecyclerView = (RecyclerView) view.findViewById(R.id.release_recycler_view);
        mReleaseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        ReleaseArchive releaseArchive = ReleaseArchive.get(getActivity());
        List<Release> releases = releaseArchive.getReleases();

        mAdapter = new ReleaseAdapter(releases);
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
            Intent intent = ReleasePagerActivity.newIntent(getActivity(), mRelease.getId());
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
/*
            public void setReleases(List<Release> Releases) {
                mReleases = Releases;
            }
*/
        }



}

