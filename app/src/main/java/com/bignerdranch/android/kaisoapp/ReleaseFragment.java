package com.bignerdranch.android.kaisoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by ursberger1 on 11/13/15.
 */
public class ReleaseFragment extends Fragment {

    private static final String ARG_RELEASE_ID = "release_id";

    private Release mRelease;

    private TextView mArtist;
    private TextView mTitle;
    private TextView mYear;
    private TextView mTrack;
    private TextView mArranger;
    private TextView mGenre;


//    private EditText mTitleField;

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

        mTitle = (TextView) v.findViewById(R.id.release_title);

        mTrack = (TextView) v.findViewById(R.id.track_text);

        mArranger = (TextView) v.findViewById(R.id.arranger_text);

        mGenre = (TextView) v.findViewById(R.id.genre_text);

        return v;
    }
}

