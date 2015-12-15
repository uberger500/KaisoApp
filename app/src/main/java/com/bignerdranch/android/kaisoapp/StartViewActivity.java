package com.bignerdranch.android.kaisoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.cognito.CognitoSyncManager;
import com.amazonaws.mobileconnectors.cognito.Dataset;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*;
*/

/**
 * Created by ursberger1 on 11/13/15.
 */
public class StartViewActivity extends AppCompatActivity {

    private static final String EXTRA_NEW_RELEASES = "new_releases";
    private static final String TAG = "StartViewActivity";
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
                Log.d(TAG, "in onClick");
                startActivity(i);
            }
        });

        mSubmitButton = (Button) findViewById(R.id.button_submit);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "in onClick");
                Intent i = new Intent(StartViewActivity.this, SubmitActivity.class);
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
            case R.id.menu_item_initialize:
                Intent intent2 = InitializeActivity.newIntent(this);
                startActivity(intent2);
                return true;
            case R.id.menu_item_format:
                Intent intent3 = FormatReleasesActivity.newIntent(this);
                startActivity(intent3);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


    /*
    // Initialize the Amazon Cognito credentials provider
    CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
            getApplicationContext(),
            "us-east-1:4ef84c86-7004-4422-823e-29d4ea617323", // Identity Pool ID
            Regions.US_EAST_1 // Region
    );

    AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);

    // Initialize the Cognito Sync client
    CognitoSyncManager syncClient = new CognitoSyncManager(
            getApplicationContext(),
            Regions.US_EAST_1, // Region
            credentialsProvider);

    DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);

    // Create a record in a dataset and synchronize with the server
    Dataset dataset = syncClient.openOrCreateDataset("KaisoArchive");
    dataset.put("myKey", "myValue");
    dataset.synchronize(new DefaultSyncCallback() {
        @Override
        public void onSuccess(Dataset dataset, List<> newRecords) {
            //Your handler code here
        }
    });
*/
  /*   ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();



        Release mRelease1 = new Release();
        ArrayList<String> mDum1Tracks = new ArrayList<String>(Arrays.asList("track1","track2","track3"));
        Release mDummyRelease1 = new Release(mRelease1.getId(),"title1","artist1","year1","arranger1",
                "3",mDum1Tracks,"link1","genre1");
        ReleaseArchive.get(StartViewActivity.this).addRelease(mDummyRelease1);
        mRelease1.put("mArtist", "artist1");
        mRelease1.saveInBackground();



        ParseQuery<ParseObject> query = ParseQuery.getQuery("Release");
        query.whereEqualTo("mArtist", "artist1");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d(TAG, "retrieve");
                    Log.d(TAG, "Retrieved " + scoreList.size() + " scores");
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        Release mRelease2 = new Release();
        ArrayList<String> mDum2Tracks = new ArrayList<String>(Arrays.asList("track1", "track2", "track3", "track4", "track5"));
        Release mDummyRelease2 = new Release(mRelease2.getId(),"title2","artist2","year2","arranger2",
                "5",mDum2Tracks,"link2","genre2");
        ReleaseArchive.get(StartViewActivity.this).addRelease(mDummyRelease2);

        mRelease2.saveInBackground();

        Release mRelease3 = new Release();
        ArrayList<String> mDum3Tracks = new ArrayList<String>(Arrays.asList("track1", "track2", "track3", "track4"));
        Release mDummyRelease3 = new Release(mRelease3.getId(),"title2","artist1","year2","arranger1",
                "4",mDum3Tracks,"link3","genre1");
        ReleaseArchive.get(StartViewActivity.this).addRelease(mDummyRelease3);
        ArrayList<String> mDum4Tracks = new ArrayList<String>(Arrays.asList("track1","track2","track3"));
        Release mDummyRelease4 = new Release(mRelease1.getId(),"title3","artist1","year2","arranger1",
                "3",mDum4Tracks,"link4","genre1");
        ReleaseArchive.get(StartViewActivity.this).addRelease(mDummyRelease4);
        ArrayList<String> mDum5Tracks = new ArrayList<String>(Arrays.asList("track1","track2"));
        Release mDummyRelease5 = new Release(mRelease1.getId(),"title5","artist3","year1","arranger1",
                "2",mDum5Tracks,"link5","genre1");
        ReleaseArchive.get(StartViewActivity.this).addRelease(mDummyRelease5);
*/