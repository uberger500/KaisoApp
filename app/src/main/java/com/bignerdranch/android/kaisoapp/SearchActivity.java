package com.bignerdranch.android.kaisoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;

/**
 * Created by ursberger1 on 11/15/15.
 */
//This class shows the search screen
public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";

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
        mYear = (EditText) findViewById(R.id.year_edit_text);
        mTitle = (EditText) findViewById(R.id.title_edit_text);
        mTrack = (EditText) findViewById(R.id.track_edit_text);
        mArranger = (EditText) findViewById(R.id.arranger_edit_text);
        mGenre = (EditText) findViewById(R.id.genre_edit_text);

        mSearchSubmitButton = (Button) findViewById(R.id.search_submit_button);
        mSearchSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchActivity.this, R.string.search_submit_button_info, Toast.LENGTH_SHORT).show();
                search();
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        ParseObject.unpinAllInBackground();
    }

    public void search() {
        String searchArtist = mArtist.getText().toString().trim();
        String searchYear = mYear.getText().toString().trim();
        String searchTitle = mTitle.getText().toString().trim();
        String searchTrack = mTrack.getText().toString().trim();
        String searchArranger = mArranger.getText().toString().trim();
        String searchGenre = mGenre.getText().toString().trim();

        //test for input
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder();
        if (searchArtist.length() == 0 &&
                searchYear.length() == 0 &&
                searchTitle.length() == 0 &&
                searchTrack.length() == 0 &&
                searchArranger.length() == 0 &&
                searchGenre.length() == 0) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_input));
        }

        if (validationError) {
            Toast.makeText(SearchActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        Intent intent = SearchResultListActivity.newIntent(SearchActivity.this, searchArtist,
                searchYear, searchTitle, searchTrack,
                 searchArranger, searchGenre );
        startActivity(intent);
    }
}

