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
 * Created by ursberger1 on 11/18/15.
 */

public class BrowseFragment extends Fragment {

    private static final String ARG_RELEASE_ID = "release_id";
    private static final String TAG = "BrowseFragment";

    ListView listViewTracks ;
    ListView listViewComments ;

    private TextView mArtist;
    private TextView mTitle;
    private TextView mYear;
    private List<String> mTracks = new ArrayList<>();
    private TextView mArranger;
    private TextView mLabel;
    private TextView mGenre;
    public String mReleaseId;

    private EditText mAddCommentEdit;
    private Button mSubmitComment;
    private List<String> mComments = new ArrayList<>();
    private ParseObject mObject;

    public static BrowseFragment newInstance(String releaseId) {
        Bundle args = new Bundle();
        args.putString(ARG_RELEASE_ID, releaseId);

        BrowseFragment fragment = new BrowseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReleaseId =  getArguments().getString(ARG_RELEASE_ID);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_release, container, false);

        listViewTracks  = (ListView) v.findViewById(R.id.list);
        listViewComments  = (ListView) v.findViewById(R.id.list_comment);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Release");
        query.getInBackground(mReleaseId, new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    mObject = object;
                    mArtist = (TextView) v.findViewById(R.id.artist_text);
                    if (object.getString("mArtist") != null) {
                        mArtist.setText(object.getString("mArtist"));
                    }
                    mYear = (TextView) v.findViewById(R.id.year_text);
                    if (object.getString("mYear") != null) {
                        mYear.setText(object.getString("mYear"));
                    }
                    mTitle = (TextView) v.findViewById(R.id.release_title);
                    if (object.getString("mTitle") != null) {
                        mTitle.setText(object.getString("mTitle"));
                    }
                    mGenre = (TextView) v.findViewById(R.id.genre_text);
                    if (object.getString("mGenre") != null) {
                        Log.d(TAG, "onCreateView 6");
                        mGenre.setText(object.getString("mGenre"));
                    }
                    mArranger = (TextView) v.findViewById(R.id.arranger_text);
                    if (object.getString("mArranger") != null) {
                        mArranger.setText(object.getString("mArranger"));
                    }
                    mLabel = (TextView) v.findViewById(R.id.label_text);
                    if (object.getString("mLabel") != null) {
                        mLabel.setText(object.getString("mLabel"));
                    }

                    mTracks = (List<String>) object.get("mTracks");
                    if (getContext() != null && mTracks != null) {
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                                android.R.layout.simple_list_item_1, android.R.id.text1, mTracks);
                        listViewTracks.setAdapter(adapter);
                           }
                    }

                    mComments = (List<String>) object.get("mComments");
                    if (getContext() != null && mComments != null) {
                        final ArrayAdapter<String> adapterComments = new ArrayAdapter<>(getContext(),
                                android.R.layout.simple_list_item_1, android.R.id.text1, mComments);
                        listViewComments.setAdapter(adapterComments);

                    mAddCommentEdit = (EditText) v.findViewById(R.id.add_comment_edit_text);

                    mSubmitComment = (Button) v.findViewById(R.id.button_add_comment);
                    mSubmitComment.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            addComment();
                            adapterComments.notifyDataSetChanged();
                        }
                    });

                } else {
                    Log.d("browse", "Error: " );
                }
            }
        });

        return v;
    }

    public void addComment() {
        String comment = mAddCommentEdit.getText().toString();

        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
        if (comment.length() == 0 ) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_input));
        }

        validationErrorMessage.append(getString(R.string.error_end));

        if (validationError) {
            Toast.makeText(getContext(), validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        mComments.add(comment);
        mObject.put("mComments", mComments);

        mObject.saveInBackground();
        mAddCommentEdit.setText("");
    }
 }

