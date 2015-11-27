package com.bignerdranch.android.kaisoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ursberger1 on 11/15/15.
 */
public class DiscussionFragment extends Fragment {

    private static final String ARG_DISCUSSION_ID = "discussion_id";

    private Discussion mDiscussion;
    private List<Discussion> mDiscussions;
    private String mDiscussionTitle;
    private ArrayList<String> mDiscussionThread = new ArrayList<>();
    private TextView mTitle;
    private TextView mDiscussionEditText;
    private Button mSubmit;
    ListView listView;


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

        mDiscussionTitle = mDiscussion.getTitle();
        mDiscussions = DiscussionArchive.get(getActivity()).getDiscussions();

        for (Discussion discussion : mDiscussions) {
            if (discussion.getTitle().equals(mDiscussionTitle)) {
                mDiscussionThread.add(discussion.getDiscussionPoint());
            }
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_discussion, container, false);

        listView = (ListView) v.findViewById(R.id.listViewDiscussion);

        mTitle = (TextView) v.findViewById(R.id.discussion_title1);
        mTitle.setText(mDiscussionTitle);
        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mDiscussion.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

     /*   if(mDiscussionThread != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_list_item_1, android.R.id.text1, mDiscussionThread);
            listView.setAdapter(adapter);

        }*/
        mDiscussionEditText = (EditText) v.findViewById(R.id.discussion_point1);
        mDiscussionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mDiscussion.setDiscussionPoint(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mSubmit = (Button) v.findViewById(R.id.submit_disc_btn);
        mSubmit.setText(R.string.submit_disc_btn);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.submit_button_info, Toast.LENGTH_SHORT).show();
                DiscussionArchive.get(getActivity()).addDiscussion(mDiscussion);

                getActivity().finish();
            }
        });
        return v;
    }
}
