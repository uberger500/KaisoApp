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

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.xml.sax.helpers.ParserFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ursberger1 on 11/18/15.
 */
public class BrowseActivity extends AppCompatActivity {

    private static final String TAG = "BrowseActivity";

    private List<String> mArtistList = new ArrayList<>();

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

        mPageTitle = (TextView) findViewById(R.id.artists_browse_view);
        mPageTitle.setText(R.string.artists_browse_view_text);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Release");
        query.orderByAscending("mArtist");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> queryList, ParseException e) {
                if (e == null) {
                    mArtistList = createArtistList(queryList);
                    ArrayAdapter adapter = new ArrayAdapter<>(BrowseActivity.this,
                            android.R.layout.simple_list_item_1, android.R.id.text1, mArtistList);
                    mArtistListView = (ListView) findViewById(R.id.artistList);
                    mArtistListView.setAdapter(adapter);
                    mArtistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            String artistName = (String) mArtistListView.getItemAtPosition(position);
                            Intent intent = BrowseRelPagerActivity.newIntent(BrowseActivity.this, artistName);
                            startActivity(intent);
                        }
                    });

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }

    protected List<String> createArtistList(List<ParseObject> releases) {

        List<String> artistList = new ArrayList<>();

        for (ParseObject release : releases) {
            if (!artistList.contains(release.getString("mArtist"))) {
                artistList.add(release.getString("mArtist"));
            }
        }
        return artistList;
    }
}
