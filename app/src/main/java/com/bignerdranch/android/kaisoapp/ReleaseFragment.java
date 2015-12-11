package com.bignerdranch.android.kaisoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ursberger1 on 11/13/15.
 */
public class ReleaseFragment extends Fragment {

    private static final String ARG_RELEASE_ID = "release_id";
    private static final String TAG = "ReleaseFragmentActivity";
  //  private Release mRelease;

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

    public static ReleaseFragment newInstance(String releaseId) {
        Bundle args = new Bundle();
        args.putString(ARG_RELEASE_ID, releaseId);
        Log.d("f", " 20instance");
        ReleaseFragment fragment = new ReleaseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReleaseId =  getArguments().getString(ARG_RELEASE_ID);
        Log.d(TAG, "onCreate called1 "+ mReleaseId);
        Log.d("f", " 21fragoncreat");
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_release, container, false);

        listView  = (ListView) v.findViewById(R.id.list);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Release");
        query.getInBackground(mReleaseId, new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    mArtist = (TextView) v.findViewById(R.id.artist_text);
                    if(object.getString("mArtist") != null) {
                        mArtist.setText(object.getString("mArtist"));
                    }
                    mYear = (TextView) v.findViewById(R.id.year_text);
                    if(object.getString("mYear") != null) {
                        mYear.setText(object.getString("mYear"));
                    }
                    mTitle = (TextView) v.findViewById(R.id.release_title);
                    if(object.getString("mTitle") != null) {
                        mTitle.setText(object.getString("mTitle"));
                    }
                    //   mNumTracks = Integer.valueOf((object.getNumTracks()));

                    mTracks = (List<String>) object.get("mTracks");

          //          Log.d("f", " 23fragdisp");
//                    Log.d(TAG, "mTracks-tracklist size is " + mTracks.size());
                    if(getContext() != null && mTracks != null) {
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                                android.R.layout.simple_dropdown_item_1line, android.R.id.text1, mTracks);

                        listView.setAdapter(adapter);


                    mArranger = (TextView) v.findViewById(R.id.arranger_text);
                    if(object.getString("mArranger") != null) {
                        mArranger.setText(object.getString("mArranger"));
                    }
                //        mLabel = (TextView) v.findViewById(R.id.label_text);
                 //       mLabel.setText(object.getString("mLabel"));

                    mGenre = (TextView) v.findViewById(R.id.genre_text);
                    if(object.getString("mGenre") != null) {
                        mGenre.setText(object.getString("mGenre"));
                    }
                    }
                } else {
                    Log.d("Release", "Error: " + e.getMessage());
                }
            }
        });
        return v;
    }
}
        /*
        View v = inflater.inflate(R.layout.fragment_release, container, false);


        mArtist = (TextView) v.findViewById(R.id.artist_text);
        mArtist.setText(mRelease.getString("mArtist"));

        mYear = (TextView) v.findViewById(R.id.year_text);
        mYear.setText(mRelease.getString("mYear"));

        mTitle = (TextView) v.findViewById(R.id.release_title);
        mTitle.setText(mRelease.getString("mTitle"));

//        mNumTracks = Integer.valueOf((mRelease.getNumTracks()));
    //    for (int i = 0; i < mNumTracks; i++){
//            mTrack = (TextView) v.findViewById(R.id.track_text);
  //               mTrack.setText(mRelease.getTracks()[1]);
      //  }

        mTracks = mRelease.getTracks();
        listView = (ListView) v.findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, mTracks);
        listView.setAdapter(adapter);

        mArranger = (TextView) v.findViewById(R.id.arranger_text);
        mArranger.setText(mRelease.getString("mArranger"));

        mArranger = (TextView) v.findViewById(R.id.label_text);
        mArranger.setText(mRelease.getString("mArranger"));

        mGenre = (TextView) v.findViewById(R.id.genre_text);
        mGenre.setText(mRelease.getString("mGenre"));
*/


/*
public class ReleaseFragment extends Fragment {

    private static final String ARG_RELEASE_ID = "release_id";

    private Release mRelease;

    private TextView mArtist;
    private TextView mTitle;
    private TextView mYear;
    private Integer mNumTracks;
    private TextView mTrack;
    private TextView mArranger;
    private TextView mGenre;

    public static ReleaseFragment newInstance(UUID releaseId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_RELEASE_ID, releaseId);

        ReleaseFragment fragment = new ReleaseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID releaseId = (UUID) getArguments().getSerializable(ARG_RELEASE_ID);

        mRelease = ReleaseArchive.get(getActivity()).getRelease(releaseId);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_release, container, false);

        mArtist = (TextView) v.findViewById(R.id.artist_text);
        mArtist.setText(mRelease.getArtist());

        mYear = (TextView) v.findViewById(R.id.year_text);
        mYear.setText(mRelease.getYear());

        mTitle = (TextView) v.findViewById(R.id.release_title);
        mTitle.setText(mRelease.getTitle());

        mNumTracks = Integer.valueOf((mRelease.getNumTracks()));
    //    for (int i = 0; i < mNumTracks; i++){
            mTrack = (TextView) v.findViewById(R.id.track_text);
                 mTrack.setText(mRelease.getTracks()[1]);
      //  }

        mArranger = (TextView) v.findViewById(R.id.arranger_text);
        mArranger.setText(mRelease.getArranger());

        mGenre = (TextView) v.findViewById(R.id.genre_text);
        mGenre.setText(mRelease.getGenre());

        return v;
    }
}

 */