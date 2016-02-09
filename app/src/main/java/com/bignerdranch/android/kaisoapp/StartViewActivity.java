package com.bignerdranch.android.kaisoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


/**
 * Created by ursberger1 on 11/13/15.
 */

//This class provides the splash screen plus the sign-up in the menubar
public class StartViewActivity extends AppCompatActivity {

    private static final String EXTRA_NEW_RELEASES = "new_releases";
    private static final String TAG = "StartViewActivity";
    private Button mSearchButton;
    private Button mBrowseButton;
    private Button mNewReleasesButton;
    private Button mDiscussionButton;
    private Button mContactButton;
    private Button mSubmitButton;
    private Button mYoutubeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start_view);

        mSearchButton = (Button) findViewById(R.id.button_search);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartViewActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });

        mBrowseButton = (Button) findViewById(R.id.button_browse);
        mBrowseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = BrowseActivity.newIntent(StartViewActivity.this);
                startActivity(intent);
            }
        });

        mNewReleasesButton = (Button) findViewById(R.id.button_new_releases);
        mNewReleasesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartViewActivity.this, ReleaseListActivity.class);
                startActivity(i);
            }
        });

        mDiscussionButton = (Button) findViewById(R.id.button_discussions);
        mDiscussionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartViewActivity.this, DiscussionListActivity.class);
                startActivity(i);
            }
        });

        mContactButton = (Button) findViewById(R.id.button_contact);
        mContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartViewActivity.this, UserListActivity.class);
                startActivity(i);
            }
        });

        mSubmitButton = (Button) findViewById(R.id.button_submit);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartViewActivity.this, SubmitActivity.class);
                startActivity(i);
            }
        });

        mYoutubeButton = (Button) findViewById(R.id.button_youtube);
        mYoutubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartViewActivity.this, VideoListActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fragment_release_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_user:
                Intent intent = UserCreateActivity.newIntent(this);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

