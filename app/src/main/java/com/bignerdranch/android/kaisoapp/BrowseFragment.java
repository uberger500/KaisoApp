package com.bignerdranch.android.kaisoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ursberger1 on 11/18/15.
 */

public class BrowseFragment extends Fragment {

    private static final String ARG_RELEASE_ID = "release_id";
    private static final String TAG = "BrowseFragment";

    private List<ParseObject> mReleases;

    ListView listView ;

    private TextView mArtist;
    private TextView mTitle;
    private TextView mYear;
    private String mNumTracks;
    private TextView mTrack;
    private List<String> mTracks = new ArrayList<>();
    private TextView mArranger;
    private TextView mLabel;
    private TextView mGenre;
    public String mReleaseId;

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
        Log.d(TAG, "Release idBrowse is " + mReleaseId);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_release, container, false);

        listView  = (ListView) v.findViewById(R.id.list);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Release");
        query.whereEqualTo("objectId", mReleaseId);
       // query.getInBackground(mReleaseId, new GetCallback<ParseObject>() {
        query.findInBackground(new FindCallback<ParseObject>() {
            //public void done(ParseObject object, ParseException e) {
            public void done(List<ParseObject> userList, ParseException e) {
                if (e == null) {
                    ParseObject object = userList.get(0);
                    mArtist = (TextView) v.findViewById(R.id.artist_text);
                    mArtist.setText(object.getString("mArtist"));

                    mYear = (TextView) v.findViewById(R.id.year_text);
                    mYear.setText(object.getString("mYear"));

                    mTitle = (TextView) v.findViewById(R.id.release_title);
                    mTitle.setText(object.getString("mTitle"));

                 //   mNumTracks = Integer.valueOf((object.getNumTracks()));

                    mTracks = (List<String>) object.get("mTracks");
                    Log.d(TAG, "mtracks size " + mTracks.size());
                    Log.d(TAG, "context " + getContext());
                    Log.d("browse", "Retrieved " + object.getString("mTitle"));


               //     ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                 //           android.R.layout.simple_list_item_1, android.R.id.text1, mTracks);
                  //  listView.setAdapter(adapter);

                    mArranger = (TextView) v.findViewById(R.id.arranger_text);
                    mArranger.setText(object.getString("mArranger"));

                    mLabel = (TextView) v.findViewById(R.id.label_text);
                    mLabel.setText(object.getString("mLabel"));

                    mGenre = (TextView) v.findViewById(R.id.genre_text);
                    mGenre.setText(object.getString("mGenre"));

                } else {
                    Log.d("browse", "Error: " + e.getMessage());
                }
            }
        });

        return v;
    }
}
