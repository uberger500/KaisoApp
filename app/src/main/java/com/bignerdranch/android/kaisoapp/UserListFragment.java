package com.bignerdranch.android.kaisoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ursberger1 on 11/15/15.
 */
public class UserListFragment extends Fragment {

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
    /*
        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.fragment_user_list, menu);
        }
    */
    private void updateUI() {
        UserArchive userArchive = UserArchive.get(getActivity());
        List<User> users = userArchive.getUsers();

        mAdapter = new UserAdapter(users);
        mUserRecyclerView.setAdapter(mAdapter);
    }

    private class UserHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {


        private TextView mUserTitleTextView;
        private User mUser;

        public UserHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mUserTitleTextView = (TextView) itemView.findViewById(R.id.list_item_user_title_text_view);
        }

        public void bindUser(User user) {

            mUser = user;
            mUserTitleTextView.setText(mUser.getName());

        }

        @Override
        public void onClick(View v) {
            Intent intent = UserPagerActivity.newIntent(getActivity(), mUser.getId());
            startActivity(intent);
        }


    }

    private class UserAdapter extends RecyclerView.Adapter<UserHolder> {

        private List<User> mUsers;

        public UserAdapter(List<User> users) {
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
            User user = mUsers.get(position);
            holder.bindUser(user);
        }

        @Override
        public int getItemCount() {
            return mUsers.size();
        }
/*
            public void setReleases(List<Release> Releases) {
                mReleases = Releases;
            }
*/
    }

}
