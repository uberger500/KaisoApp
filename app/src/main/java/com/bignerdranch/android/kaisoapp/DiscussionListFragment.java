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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by ursberger1 on 11/15/15.
 */
//The first screen of the discussion activity, a recyclerview that shows the list of
    //already created discussion topics, or a GO button when the list is empty
public class DiscussionListFragment extends Fragment {

    private static final String TAG = "DiscListActivity";

    private RecyclerView mDiscussionRecyclerView;
    private DiscussionAdapter mAdapter;

    LinearLayout mNoDiscussionLayout;
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

        mOkButton = (Button) view.findViewById(R.id.discussion_recycler_view_empty_button_text);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(getActivity(), R.string.submit_button_info, Toast.LENGTH_SHORT).show();
                createDiscussion();
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_discussion:
                createDiscussion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createDiscussion() {
        Intent i = new Intent(getActivity(), DiscussionCreateActivity.class);
        startActivity(i);

    }
    private void updateUI() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Discussion");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> discussionList, ParseException e) {
                if (e == null) {
                    mAdapter = new DiscussionAdapter(discussionList);
                    mDiscussionRecyclerView.setAdapter(mAdapter);

                    if(discussionList.size() != 0) {
                        mNoDiscussionLayout.setVisibility(View.GONE);
                    } else {
                        mNoDiscussionLayout.setVisibility(View.VISIBLE);
                    }
                } else {
                    Log.d("release", "Error: " + e.getMessage());
                }
            }
        });
    }

    private class DiscussionHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mDiscussionTitleTextView;
        private ParseObject mDiscussion;

        public DiscussionHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mDiscussionTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_discussion_title_text_view);
        }

        public void bindDiscussion(ParseObject discussion) {
            mDiscussion = discussion;
            mDiscussionTitleTextView.setText(mDiscussion.getString("mTitle"));
        }

        @Override
        public void onClick(View v) {
            Intent intent = DiscussionPagerActivity.newIntent(getActivity(), mDiscussion.getObjectId());
            startActivity(intent);
        }
    }

    private class DiscussionAdapter extends RecyclerView.Adapter<DiscussionHolder> {

        private List<ParseObject> mDiscussions;

        public DiscussionAdapter(List<ParseObject> discussions) {
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
            ParseObject discussion = mDiscussions.get(position);
            holder.bindDiscussion(discussion);
        }

        @Override
        public int getItemCount() {
            return mDiscussions.size();
        }
    }
}

