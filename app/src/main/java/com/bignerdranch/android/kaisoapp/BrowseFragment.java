package com.bignerdranch.android.kaisoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ursberger1 on 11/18/15.
 */
public class BrowseFragment extends Fragment {

    private static final String ARG_RELEASE_ID = "release_id";

    private Release mRelease;
    private List<Release> mReleases;

    ListView listView ;

    private TextView mArtist;
    private TextView mTitle;
    private TextView mYear;
    private Integer mNumTracks;
    private TextView mTrack;
    private List<String> mTracks;
    private TextView mArranger;
    private TextView mGenre;

    public static BrowseFragment newInstance(UUID releaseId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_RELEASE_ID, releaseId);

        BrowseFragment fragment = new BrowseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID releaseId = (UUID) getArguments().getSerializable(ARG_RELEASE_ID);

        mRelease = ReleaseArchive.get(getActivity()).getRelease(releaseId);

      //  final String artistName = mRelease1.getArtist();
      //  mReleases = ReleaseArchive.get(getActivity()).getReleases();
      //  final List<Release> mSelectionList = createReleaseSublist(mReleases, artistName);
      //  mRelease = mSelectionList.get(getActivity()).getRelease(0);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_release, container, false);

        listView = (ListView) v.findViewById(R.id.list);

        mArtist = (TextView) v.findViewById(R.id.artist_text);
        mArtist.setText(mRelease.getArtist());

        mYear = (TextView) v.findViewById(R.id.year_text);
        mYear.setText(mRelease.getYear());

        mTitle = (TextView) v.findViewById(R.id.release_title);
        mTitle.setText(mRelease.getTitle());

        mNumTracks = Integer.valueOf((mRelease.getNumTracks()));

        mTracks = mRelease.getTracks();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, mTracks);
        listView.setAdapter(adapter);

        mArranger = (TextView) v.findViewById(R.id.arranger_text);
        mArranger.setText(mRelease.getArranger());

        mGenre = (TextView) v.findViewById(R.id.genre_text);
        mGenre.setText(mRelease.getGenre());

        return v;
    }
}
