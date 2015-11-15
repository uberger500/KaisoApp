package com.bignerdranch.android.kaisoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ursberger1 on 11/15/15.
 */
public class SearchActivity extends AppCompatActivity {

    private Release mRelease;

    private EditText mArtist;
    private EditText mYear;
    private EditText mTitle;

    private EditText mTrack;
    private EditText mArranger;
    private EditText mGenre;

    private Button mSearchSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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


        mTrack = (EditText) findViewById(R.id.track_edit_text);
        mTrack.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //          mRelease.setTracks(s.toString());
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



        mSearchSubmitButton = (Button) findViewById(R.id.search_submit_button);
        mSearchSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchActivity.this, R.string.search_submit_button_info, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
