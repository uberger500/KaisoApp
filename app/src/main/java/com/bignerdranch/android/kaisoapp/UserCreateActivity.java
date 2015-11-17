package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by ursberger1 on 11/16/15.
 */
public class UserCreateActivity extends AppCompatActivity {

    private static final String EXTRA_NEW_USER = "com.bignerdranch.android.kaisoapp.new_user";

    private User mUser;
    private EditText mName;
    private EditText mEmail;
    private EditText mPhone;
    private Button mSubmitbtn;


    public static Intent newIntent(Context packageContext, UUID userId) {
        Intent i = new Intent(packageContext, UserCreateActivity.class);
        i.putExtra(EXTRA_NEW_USER, userId);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID userId = (UUID) getIntent().getSerializableExtra(EXTRA_NEW_USER);
        mUser = UserArchive.get(this).getUser(userId);
        setContentView(R.layout.activity_new_user);

        mName = (EditText) findViewById(R.id.editText_name);
        mName.setText(mUser.getName());
        mName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mUser.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mEmail = (EditText) findViewById(R.id.editText_email);
        mEmail.setText(mUser.getEmail());
        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mUser.setEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mPhone = (EditText) findViewById(R.id.editText_phone);
        mPhone.setText(mUser.getPhone());
        mPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mUser.setPhone(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mSubmitbtn = (Button) findViewById(R.id.button_submit);
        mSubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
/*
        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);

        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }*/
    }
}
