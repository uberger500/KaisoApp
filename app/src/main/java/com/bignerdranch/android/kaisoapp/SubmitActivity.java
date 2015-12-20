package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
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
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ursberger1 on 11/15/15.
 */
//The submit a new release activity
public class SubmitActivity extends AppCompatActivity {

    private Release mRelease = new Release();
    private List<String> mTracks = new ArrayList<>();
    private List<String> mComments = new ArrayList<>();
    private List<String> mUsers = new ArrayList<>();
    private List<String> mDates = new ArrayList<>();

    ListView listView;

    private EditText mArtist;
    private EditText mYear;
    private EditText mTitle;
    private EditText mTrack;
    private EditText mArranger;
    private EditText mGenre;
    private EditText mLabel;
    private Button mPlusButton;
    private Button mTracksSave;
    private Button mSubmitButton;

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, SubmitActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_submit);

        listView = (ListView) findViewById(R.id.list_tracks);

        mArtist = (EditText) findViewById(R.id.artist_edit_text);
        mYear = (EditText) findViewById(R.id.year_edit_text);
        mTitle = (EditText) findViewById(R.id.title_edit_text);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, mTracks) {

            //this method to change the color of simple_list_item_1 comes from stack overflow:
            //http://stackoverflow.com/questions/5563698/how-to-change-text-color-of-simple-list-item
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text1);
                    text.setTextColor(Color.BLACK);
                    return view;
                }
        };
        listView.setAdapter(adapter);

        mPlusButton = (Button) findViewById(R.id.plus_button);
        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTrack();
                adapter.notifyDataSetChanged();
            }
        });

        mTracksSave = (Button) findViewById(R.id.track_save_button);
        mTracksSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTrack();
                adapter.notifyDataSetChanged();
                mPlusButton.setEnabled(false);
            }
        });

        mTrack = (EditText) findViewById(R.id.track_edit_text);
        mArranger = (EditText) findViewById(R.id.arranger_edit_text);
        mLabel = (EditText) findViewById(R.id.label_edit_text);
        mGenre = (EditText) findViewById(R.id.genre_edit_text);

        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Toast.makeText(SubmitActivity.this, R.string.submit_button_info, Toast.LENGTH_SHORT).show();
                addRelease();
            }
        });
    }

    public void addTrack() {
        String track = mTrack.getText().toString().trim();
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder();
        if ((track.length() == 0) &&
                mTracks.size() == 0){
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_track));
        }
        if (validationError) {
            Toast.makeText(SubmitActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        mTracks.add(track);
        mTrack.setText("");

    }

    public void addRelease() {

        String artist = mArtist.getText().toString().trim();
        String year = mYear.getText().toString().trim();
        String title = mTitle.getText().toString().trim();
        String label = mLabel.getText().toString().trim();
        String arranger = mArranger.getText().toString().trim();
        String genre = mGenre.getText().toString().trim();

        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder();
        if (artist.length() == 0) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_artist));
            validationErrorMessage.append(" ");

        }
        if (year.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
                validationErrorMessage.append(" ");

            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_year));
            validationErrorMessage.append(" ");

        }
        if (title.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
                validationErrorMessage.append(" ");

            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_title));
            validationErrorMessage.append(" ");

        }


        //I decided to not check for arranger or label input, since that is not always not
       /* if (arranger.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
                validationErrorMessage.append(" ");

            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_arranger));
            validationErrorMessage.append(" ");

        }
        if (label.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
                validationErrorMessage.append(" ");

            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_label));
            validationErrorMessage.append(" ");

        }*/
        if (genre.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
                validationErrorMessage.append(" ");

            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_genre));
            validationErrorMessage.append(" ");

        }

        if (validationError) {
            Toast.makeText(SubmitActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        mRelease.setArtist(artist);
        mRelease.setYear(Integer.parseInt(year));
        mRelease.setTitle(title);
        mRelease.setTracks(mTracks);
        mRelease.setArranger(arranger);
        mRelease.setLabel(label);
        mRelease.setGenre(genre);
        //these three fields are for later when I figure out how to design a custom arrayadapter
        //for three text fields
        mRelease.put("mComments", mComments);
        mRelease.put("mUsers", mUsers);
        mRelease.put("mDates", mDates);

        mRelease.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(SubmitActivity.this, "Release info has been added", Toast.LENGTH_LONG)
                            .show();
                    finish();
                } else {
                    Log.d("ReleaseSubmission", "Error: " + e.getMessage());
                    Toast.makeText(SubmitActivity.this, e.getMessage(), Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        finish();
    }
}
