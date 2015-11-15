package com.bignerdranch.android.kaisoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by ursberger1 on 11/15/15.
 */
public class DiscussionFragment extends Fragment {

    private static final String ARG_DISCUSSION_ID = "discussion_id";

    private Discussion mDiscussion;

    private TextView mTitle;


    public static DiscussionFragment newInstance(UUID discussionId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DISCUSSION_ID, discussionId);

        DiscussionFragment fragment = new DiscussionFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID discussionId = (UUID) getArguments().getSerializable(ARG_DISCUSSION_ID);

        mDiscussion = DiscussionArchive.get(getActivity()).getDiscussion(discussionId);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_discussion, container, false);



        mTitle = (TextView) v.findViewById(R.id.discussion_title);
        mTitle.setText(mDiscussion.getTitle());


        return v;
    }
}
