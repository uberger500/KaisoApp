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

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by ursberger1 on 11/15/15.
 */

public class UserListFragment extends Fragment {
    private static final String TAG = "UserListFrag";

    private RecyclerView mUserRecyclerView;
    private UserAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.d(TAG, "in onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        mUserRecyclerView = (RecyclerView) view.findViewById(R.id.item_recycler_view);
        mUserRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.d(TAG, "in onCreateView1");

        updateUI();
        Log.d(TAG, "in onCreateView2");

        return view;
    }
    /*
        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.fragment_user_list, menu);
        }
    */


    private void updateUI() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> userList, ParseException e) {
                if (e == null) {
                    Log.d(TAG, "in Callback");
                    Log.d(TAG, "Retrieved " + userList.size() + " users");
                  //  releases = releaseBackConvert(releaseList);
                    mAdapter = new UserAdapter(userList);
                    mUserRecyclerView.setAdapter(mAdapter);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

    }

    private class UserHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mUserTitleTextView;
        private ParseObject mUser;

        public UserHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mUserTitleTextView = (TextView) itemView.findViewById(R.id.list_item_user_title_text_view);
        }

        public void bindUser(ParseObject user) {

            mUser = user;
            mUserTitleTextView.setText(mUser.getString("mName"));

        }

        @Override
        public void onClick(View v) {
            Intent intent = UserPagerActivity.newIntent(getActivity(), mUser.getString("mName"));
            startActivity(intent);
        }


    }

    private class UserAdapter extends RecyclerView.Adapter<UserHolder> {

        private List<ParseObject> mUsers;

        public UserAdapter(List<ParseObject> users) {
            mUsers = users;
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_user, parent, false);
            return new UserHolder(view);
        }

        @Override
        public void onBindViewHolder(UserHolder holder, int position) {
            ParseObject user = mUsers.get(position);
            holder.bindUser(user);
        }

        @Override
        public int getItemCount() {
            return mUsers.size();
        }
  }
}
