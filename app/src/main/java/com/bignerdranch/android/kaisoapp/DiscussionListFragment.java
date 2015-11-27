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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ursberger1 on 11/15/15.
 */
public class DiscussionListFragment extends Fragment {

    private RecyclerView mDiscussionRecyclerView;
    private DiscussionAdapter mAdapter;
    private Discussion mDiscussion;

    LinearLayout mNoDiscussionLayout;
 //   private TextView mNoDiscussion;
    private Button mOkButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discussion_list, container, false);

        mNoDiscussionLayout = (LinearLayout) view.findViewById(R.id.discussion_recycler_view_empty);
   //     mNoDiscussion = (TextView) view.findViewById(R.id.discussion_recycler_view_empty_text);
        mOkButton = (Button) view.findViewById(R.id.discussion_recycler_view_empty_button_text);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.submit_button_info, Toast.LENGTH_SHORT).show();
             //   Intent i = new Intent(DiscussionListFragment.this, DiscussionCreateActivity.class);
             //   startActivity(i);
                Intent intent = DiscussionCreateActivity.newIntent(getActivity());
                startActivity(intent);
            }
        });
        mDiscussionRecyclerView = (RecyclerView) view.findViewById(R.id.item_recycler_view);
        mDiscussionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_discussion_list, menu);
    }

    private void updateUI() {
        DiscussionArchive discussionArchive = DiscussionArchive.get(getActivity());
        List<Discussion> discussions = discussionArchive.getDiscussions();

        mAdapter = new DiscussionAdapter(discussions);
        mDiscussionRecyclerView.setAdapter(mAdapter);


  /*      if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setCrimes(crimes);
            mAdapter.notifyDataSetChanged();
        }

        updateSubtitle();
*/
        if(discussions.size() != 0) {
            mNoDiscussionLayout.setVisibility(View.GONE);
        } else {
            mNoDiscussionLayout.setVisibility(View.VISIBLE);
        }
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
