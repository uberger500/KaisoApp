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
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ursberger1 on 11/15/15.
 */
//The pagerview for users
public class UserPagerActivity extends AppCompatActivity {

    private static final String TAG = "UserPagerActivity";
    private static final String EXTRA_USER_ID = "com.bignerdranch.android.kaisoapp.user_id";
    private ViewPager mViewPager;

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

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(final List<ParseUser> userList, ParseException e) {
                if (e == null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
                        @Override
                        public Fragment getItem(int position) {
                            ParseUser user = userList.get(position);
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
