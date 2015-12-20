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
//The recyclerview screen to show users
public class UserListFragment extends Fragment {
    private static final String TAG = "UserListFrag";

    private RecyclerView mUserRecyclerView;
    private UserAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        mUserRecyclerView = (RecyclerView) view.findViewById(R.id.item_recycler_view);
        mUserRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> userList, ParseException e) {
                if (e == null) {
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

        public void bindUser(ParseUser user) {

            mUser = user;
            mUserTitleTextView.setText(mUser.getString("username"));
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "userid is " + mUser.getObjectId());
            Intent intent = UserPagerActivity.newIntent(getActivity(), mUser.getObjectId());
            startActivity(intent);
        }
    }

    private class UserAdapter extends RecyclerView.Adapter<UserHolder> {

        private List<ParseUser> mUsers;

        public UserAdapter(List<ParseUser> users) {
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
            ParseUser user = mUsers.get(position);
            holder.bindUser(user);
        }

        @Override
        public int getItemCount() {
            return mUsers.size();
        }
  }
}
