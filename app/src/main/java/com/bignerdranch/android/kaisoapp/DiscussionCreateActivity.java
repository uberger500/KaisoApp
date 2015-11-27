package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ursberger1 on 11/23/15.
 */
public class DiscussionCreateActivity extends AppCompatActivity {

    private static final String TAG = "DActivityCreateActivity";
    private static final String EXTRA_NEW_DISCUSSION = "com.bignerdranch.android.kaisoapp.new_discussion";

    private Discussion mDiscussion;
    private EditText mDiscussionTitle;
    private EditText mDiscussionPoint;
    private Button mSubmitbtn;
    private List<Discussion> mDiscussions = DiscussionArchive.get(this).getDiscussions();
    private Boolean flag1 = false;
    private Boolean flag2 = false;


    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, DiscussionCreateActivity.class);
        Log.d(TAG, "increateintent");
        return i;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //    UUID userId = (UUID) getIntent().getSerializableExtra(EXTRA_NEW_USER);
        //   mUser = UserArchive.get(this).getUser(userId);
        Log.d(TAG, "in discreate");
        setContentView(R.layout.activity_new_discussion);

        mDiscussion = new Discussion();
   //     DiscussionArchive discussionArchive =  DiscussionArchive.get(DiscussionCreateActivity.this);

        mDiscussionTitle = (EditText) findViewById(R.id.discussion_title1);
        mDiscussionTitle.setText(mDiscussion.getTitle());
        mDiscussionTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mDiscussion.setTitle(s.toString());
                if(s.toString() != null && s.toString().length() > 0) {
                    flag1 = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mDiscussionPoint = (EditText) findViewById(R.id.discussion_point1);

                                                    mDiscussionPoint.setText(mDiscussion.getDiscussionPoint());
        mDiscussionPoint.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mDiscussion.setDiscussionPoint(s.toString());
                if(s.toString() != null && s.toString().length() > 0) {
                    flag2 = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        mSubmitbtn = (Button) findViewById(R.id.submit_disc_btn);
        mSubmitbtn.setText(R.string.submit_disc_btn);
        if(flag1 && flag2) {
            mSubmitbtn.setEnabled(true);
        } else {
            mSubmitbtn.setEnabled(false);
        }
        mSubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(this, R.string.submit_button_info, Toast.LENGTH_SHORT).show();

                mDiscussions.add(mDiscussion);
                finish();
            }
        });

        /*
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
*/
/*
        mSubmitbtn = (Button) findViewById(R.id.button_submit);
        mSubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


                finish();
            }
        });
*/
    }
}
