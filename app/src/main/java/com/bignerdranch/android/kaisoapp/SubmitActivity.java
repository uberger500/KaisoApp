package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by ursberger1 on 11/15/15.
 */
public class SubmitActivity extends AppCompatActivity {

    private static final String EXTRA_NEW_RELEASE = "com.bignerdranch.android.kaisoapp.new_release";

    private Release mRelease;
    private ArrayList<String> mTracks;

    private EditText mArtist;
    private EditText mYear;
    private EditText mTitle;
    private EditText mNumTracks;
    private EditText mTrack;
    private EditText mArranger;
    private EditText mGenre;
    private EditText mLink;
    private Button mSubmitButton;

/*
    public static Intent newIntent(Context packageContext, UUID releaseId) {
        Intent i = new Intent(packageContext, SubmitActivity.class);
        i.putExtra(EXTRA_NEW_RELEASE, releaseId);
        return i;
    }
*/
    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, SubmitActivity.class);
    //    i.putExtra(EXTRA_NEW_RELEASE, releaseId);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   //     UUID releaseId = (UUID) getIntent().getSerializableExtra(EXTRA_NEW_RELEASE);
   //     mRelease = ReleaseArchive.get(this).getRelease(releaseId);
        final Release mRelease = new Release();

        mTracks = new ArrayList<>();
        mRelease.setTracks(mTracks);
        ReleaseArchive.get(this).addRelease(mRelease);
     //   mTracks = mRelease.getTracks();

        setContentView(R.layout.activity_submit);

        mArtist = (EditText) findViewById(R.id.artist_edit_text);
        mArtist.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mRelease.setArtist(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mYear = (EditText) findViewById(R.id.year_edit_text);
        mYear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mRelease.setYear(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mTitle = (EditText) findViewById(R.id.title_edit_text);
        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mRelease.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mNumTracks = (EditText) findViewById(R.id.num_tracks_edit_text);
        mNumTracks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mRelease.setNumTracks(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mTrack = (EditText) findViewById(R.id.track_edit_text);
        mTrack.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTracks.add(s.toString());
      //          mRelease.addTrack(s.toString());
                mRelease.setTracks(mTracks);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mArranger = (EditText) findViewById(R.id.arranger_edit_text);
        mArranger.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mRelease.setArranger(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mGenre = (EditText) findViewById(R.id.genre_edit_text);
        mGenre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mRelease.setGenre(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mLink = (EditText) findViewById(R.id.link_edit_text);
        mLink.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mRelease.setLink(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SubmitActivity.this, R.string.submit_button_info, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
