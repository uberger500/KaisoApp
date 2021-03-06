package com.bignerdranch.android.kaisoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ursberger1 on 11/15/15.
 */
//The fragment for the discussion pagerview
public class DiscussionFragment extends Fragment {

    private static final String TAG = "DiscFragment";

    private static final String ARG_DISCUSSION_ID = "discussion_id";

    private ParseObject mDiscussion;
    private TextView mTitle;
    private TextView mDiscussionPoint;
    private Button mSubmit;
    ListView listView;
    public String mDiscussionId;
    private List<String> mPoints = new ArrayList<>();

    public static DiscussionFragment newInstance(String discussionId) {
        Bundle args = new Bundle();
        args.putString(ARG_DISCUSSION_ID, discussionId);
        DiscussionFragment fragment = new DiscussionFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDiscussionId =  getArguments().getString(ARG_DISCUSSION_ID);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_discussion, container, false);

        listView = (ListView) v.findViewById(R.id.list_view_discussion);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Discussion");
        query.getInBackground(mDiscussionId, new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    mTitle = (TextView) v.findViewById(R.id.discussion_title2);
                    mTitle.setText(object.getString("mTitle"));

                    mDiscussion = object;
                    mPoints = (List<String>) object.get("mDiscussionPoints");

                    if(getContext() != null) {
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                                android.R.layout.simple_list_item_1, android.R.id.text1, mPoints);
                        listView.setAdapter(adapter);

                        mDiscussionPoint = (EditText) v.findViewById(R.id.discussion_point2);
                        mSubmit = (Button) v.findViewById(R.id.submit_disc_btn);
                        mSubmit.setText(R.string.submit_disc_btn);
                        mSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addPoint();
                            }

                        });

                    }
                } else {
                    Log.d(TAG, "Error: " + e.getMessage());
                }
            }
        });

        return v;
    }

    private void addPoint() {
        String discussionPoint = mDiscussionPoint.getText().toString().trim();

        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder();
        if (discussionPoint.length() == 0) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_discussion_point));
        }
        if (validationError) {
            Toast.makeText(getActivity(), validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }
        // Set up a new Discussion
        mPoints.add(discussionPoint);
        mDiscussion.put("mDiscussionPoints", mPoints);
        mDiscussion.saveInBackground();

        getActivity().finish();
    }
}
