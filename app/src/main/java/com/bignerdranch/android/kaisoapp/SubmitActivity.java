package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ursberger1 on 11/15/15.
 */

public class SubmitActivity extends AppCompatActivity {

    private static final String EXTRA_NEW_RELEASE = "com.bignerdranch.android.kaisoapp.new_release";

    private Release mRelease = new Release();
    private String mTempTrack;
    private List<String> mTracks = new ArrayList<>();

    ListView listView;

    private EditText mArtist;
    private EditText mYear;
    private EditText mTitle;
    private EditText mNumTracks;
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
        //  mRelease.setTracks(mTracks);
        //  ReleaseArchive.get(this).addRelease(mRelease);
        //   mTracks = mRelease.getTracks();

        setContentView(R.layout.activity_submit);

        listView = (ListView) findViewById(R.id.list_tracks);


        mArtist = (EditText) findViewById(R.id.artist_edit_text);
        mYear = (EditText) findViewById(R.id.year_edit_text);
        mTitle = (EditText) findViewById(R.id.title_edit_text);
        mNumTracks = (EditText) findViewById(R.id.num_tracks_edit_text);

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
                 Toast.makeText(SubmitActivity.this, R.string.submit_button_info, Toast.LENGTH_SHORT).show();
                addTrack();

                adapter.notifyDataSetChanged();
            }
        });
        mTracksSave = (Button) findViewById(R.id.track_save_button);
        mTracksSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Toast.makeText(SubmitActivity.this, R.string.submit_button_info, Toast.LENGTH_SHORT).show();
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
                // Toast.makeText(SubmitActivity.this, R.string.submit_button_info, Toast.LENGTH_SHORT).show();
                addRelease();
            }
        });
    }


    public void addTrack() {
        String track = mTrack.getText().toString().trim();
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
        if (track.length() == 0) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_track));
        }
        validationErrorMessage.append(getString(R.string.error_end));
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
      //  String numTracks = mNumTracks.getText().toString().trim();
        String label = mLabel.getText().toString().trim();
        String arranger = mArranger.getText().toString().trim();
        String genre = mGenre.getText().toString().trim();


        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
        if (artist.length() == 0) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_artist));
        }
        if (year.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_year));
        }
        if (title.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_title));
        }/*
        if (numTracks.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_num_tracks));
        }*/
/*
        if (arranger.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_arranger));
        }
        if (label.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_label));
        }*/
        if (genre.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_genre));
        }

        //   else {
      //      phoneNumber = Integer.parseInt(number);


        validationErrorMessage.append(getString(R.string.error_end));

        if (validationError) {
            Toast.makeText(SubmitActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        mRelease.setArtist(artist);
        mRelease.setYear(year);
        mRelease.setTitle(title);
      //  mRelease.setNumTracks(numTracks);
      //  mRelease.setTracks(mTracks);
        mRelease.setArranger(arranger);
        mRelease.setLabel(label);
        mRelease.setGenre(genre);

     //   ReleaseArchive.get(this).addRelease(mRelease);

        mRelease.saveInBackground();
/*
        if (mUsers.size() == 0 ) {
            UserArchive.get(UserCreateActivity.this).addUser(mUser);
        } else {
            boolean flag = false;
            for (User user : mUsers) {
                if (user.getName().equals(mUser.getName())) {
                    Toast.makeText(UserCreateActivity.this, R.string.duplicate_user, Toast.LENGTH_SHORT).show();
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                UserArchive.get(UserCreateActivity.this).addUser(mUser);
            }
        }
*/


        finish();
    }
}
/*
    private void updateUI() {
            CrimeLab crimeLab = CrimeLab.get(getActivity());
            List<Crime> crimes = crimeLab.getCrimes();

            if (mAdapter == null) {
                mAdapter = new CrimeAdapter(crimes);
                mCrimeRecyclerView.setAdapter(mAdapter);
            } else {
                mAdapter.setCrimes(crimes);
                mAdapter.notifyDataSetChanged();
            }

            updateSubtitle();

            if(crimes.size() != 0) {
                mNoCrimeLayout.setVisibility(View.GONE);
            } else {
                mNoCrimeLayout.setVisibility(View.VISIBLE);
            }
    }


        mTracksSave = (Button) findViewById(R.id.track_save_button);
        mTracksSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(SubmitActivity.this, R.string.submit_button_info, Toast.LENGTH_SHORT).show();
                addTrack();
                adapter.notifyDataSetChanged();
                mPlusButton.setEnabled(false);
            }
        });




        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(SubmitActivity.this, R.string.submit_button_info, Toast.LENGTH_SHORT).show();
                addRelease();
            }
        });

    }*/
/*
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
                mTempTrack = s.toString();
      //          mRelease.addTrack(s.toString());
                mTracks.add(mTempTrack);
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
*/

/*
 <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/link_edit_text"
        tools:text="link_edit_text"
        android:editable="true"
        android:hint="Link"/>
 */