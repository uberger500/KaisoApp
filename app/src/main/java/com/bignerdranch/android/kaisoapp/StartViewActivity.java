package com.bignerdranch.android.kaisoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by ursberger1 on 11/13/15.
 */
public class StartViewActivity extends AppCompatActivity {
    private static final String EXTRA_NEW_RELEASES = "new_releases";

    private Button mSearchButton;
    private Button mBrowseButton;
    private Button mNewReleasesButton;
    private Button mDiscussionButton;
    private Button mContactButton;
    private Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_view);

        Release mRelease1 = new Release();
        String[] mDum1Tracks = new String[] {"track1","track2","track3"};
        Release mDummyRelease1 = new Release(mRelease1.getId(),"title1","artist1","year1","arranger1",
                "3",mDum1Tracks,"link1","genre1");
        ReleaseArchive.get(StartViewActivity.this).addRelease(mDummyRelease1);
        Release mRelease2 = new Release();
        String[] mDum2Tracks = new String[] {"track1","track2","track3"};
        Release mDummyRelease2 = new Release(mRelease2.getId(),"title2","artist2","year2","arranger2",
                "3",mDum2Tracks,"link2","genre2");
        ReleaseArchive.get(StartViewActivity.this).addRelease(mDummyRelease2);
        Release mRelease3 = new Release();
        String[] mDum3Tracks = new String[] {"track1","track2","track3"};
        Release mDummyRelease3 = new Release(mRelease3.getId(),"title3","artist1","year1","arranger1",
                "3",mDum3Tracks,"link3","genre1");
        ReleaseArchive.get(StartViewActivity.this).addRelease(mDummyRelease3);


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
                Toast.makeText(StartViewActivity.this,R.string.button_test, Toast.LENGTH_SHORT).show();
            }
        });

        mNewReleasesButton = (Button) findViewById(R.id.button_new_releases);
        mNewReleasesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

         //       Release release = new Release();
           //     ReleaseArchive.get(this).addRelease(release);
             //   Intent intent = CrimePagerActivity.newIntent(getActivity(), release.getId());

             //   Intent i = ReleaseListActivity.newIntent(getActivity()); //, buttonPressed);
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


                Release release = new Release();
                ReleaseArchive.get(StartViewActivity.this).addRelease(release);
                Intent intent = SubmitActivity.newIntent(StartViewActivity.this, release.getId());
                startActivity(intent);

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
                createUser();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createUser() {
        User user = new User();
        UserArchive.get(this).addUser(user);
        Intent intent = UserCreateActivity.newIntent(this, user.getId());
        startActivity(intent);
    }


/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    */
}
