package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ursberger1 on 11/15/15.
 */

public class UserPagerActivity extends AppCompatActivity {

    private static final String TAG = "UserPagerActivity";
    private static final String EXTRA_USER_ID = "com.bignerdranch.android.kaisoapp.user_id";
    private ViewPager mViewPager;
    private final List<ParseObject> mUsers = new ArrayList<>();

    public static Intent newIntent(Context packageContext, String userId) {
        Intent intent = new Intent(packageContext, UserPagerActivity.class);
        intent.putExtra(EXTRA_USER_ID, userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_pager);

        final String userId =  getIntent().getStringExtra(EXTRA_USER_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_fragment_pager_view_pager);

      //  mUsers = UserArchive.get(this).getUsers();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> userList, ParseException e) {
                if (e == null) {
                 //   mUsers = userList;
                    Log.d(TAG, "Retrieved " + userList.size() + " users");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
                        @Override
                        public Fragment getItem(int position) {
                            ParseObject user = userList.get(position);
                            Log.d(TAG, "releaseId called " + user.getObjectId() + user.getString("mTitle"));
                            return UserFragment.newInstance(user.getObjectId());
                        }

                        @Override
                        public int getCount() {
                            return userList.size();
                        }

                    });

                    for (int i = 0; i < userList.size(); i++) {
                        if (userList.get(i).getObjectId().equals(userId)) {
                            mViewPager.setCurrentItem(i);
                            break;
                        }
                    }
                } else {
                    Log.d("Userpager", " Error: " + e.getMessage());
                }
            }
        });

    }
}
