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
 * Created by ursberger1 on 11/15/15.
 */
public class DiscussionListFragment extends Fragment {

    private RecyclerView mDiscussionRecyclerView;
    private DiscussionAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        mDiscussionRecyclerView = (RecyclerView) view.findViewById(R.id.item_recycler_view);
        mDiscussionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }
/*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_discussion_list, menu);
    }
*/
    private void updateUI() {
        DiscussionArchive discussionArchive = DiscussionArchive.get(getActivity());
        List<Discussion> discussions = discussionArchive.getDiscussions();

        mAdapter = new DiscussionAdapter(discussions);
        mDiscussionRecyclerView.setAdapter(mAdapter);
    }

    private class DiscussionHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {


        private TextView mDiscussionTitleTextView;
        private Discussion mDiscussion;

        public DiscussionHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mDiscussionTitleTextView = (TextView) itemView.findViewById(R.id.list_item_discussion_title_text_view);
        }

        public void bindDiscussion(Discussion discussion) {

            mDiscussion = discussion;
            mDiscussionTitleTextView.setText(mDiscussion.getTitle());

        }

        @Override
        public void onClick(View v) {
            Intent intent = DiscussionPagerActivity.newIntent(getActivity(), mDiscussion.getId());
            startActivity(intent);
        }
    }

    private class DiscussionAdapter extends RecyclerView.Adapter<DiscussionHolder> {

        private List<Discussion> mDiscussions;

        public DiscussionAdapter(List<Discussion> discussions) {
            mDiscussions = discussions;
        }

        @Override
        public DiscussionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_discussion, parent, false);
            return new DiscussionHolder(view);
        }

        @Override
        public void onBindViewHolder(DiscussionHolder holder, int position) {
            Discussion discussion = mDiscussions.get(position);
            holder.bindDiscussion(discussion);
        }

        @Override
        public int getItemCount() {
            return mDiscussions.size();
        }
/*
            public void setReleases(List<Release> Releases) {
                mReleases = Releases;
            }
*/
    }


}
