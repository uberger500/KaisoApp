package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ursberger1 on 11/18/15.
 */
public class BrowseActivity extends AppCompatActivity {

    private static final String TAG = "BrowseActivity";
    private ReleaseArchive mReleaseArchive = ReleaseArchive.get(this);
    private Release mRelease;

    private ArrayList<String> mArtistList = new ArrayList<>();
  //  private ArrayList<String> mArtistList = new ArrayList<String>(Arrays.asList("track1", "track2", "track3"));

    private TextView mPageTitle;
    private ListView mArtistListView;

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, BrowseActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_browse);
        Log.d(TAG, "creating browse activity view");
        mPageTitle = (TextView) findViewById(R.id.artists_browse_view);
        mPageTitle.setText(R.string.artists_browse_view_text);

        createArtistList(mReleaseArchive, mArtistList);

        mArtistListView = (ListView) findViewById(R.id.artistList);
        ArrayAdapter adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, mArtistList);
        mArtistListView.setAdapter(adapter);
        mArtistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                int itemPosition = position;
                String itemValue = (String) mArtistListView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();
                Intent intent = BrowseRelPagerActivity.newIntent(BrowseActivity.this, itemValue);
                Log.d(TAG, "creating browse activity view clicked");
                startActivity(intent);
            }

        });


    }

    protected void createArtistList(ReleaseArchive releaseArchive, ArrayList<String> artistList) {

        List<Release> mReleases = releaseArchive.getReleases();
        Release mRelease = mReleases.get(0);
        artistList.add(mRelease.getArtist());

        for (Release release : mReleases)  {
            if (!artistList.contains(release.getArtist())) {
                artistList.add(release.getArtist());
            }
        }
    }

}
