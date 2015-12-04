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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ursberger1 on 11/23/15.
 */
public class DiscussionCreateActivity extends AppCompatActivity {

    private static final String TAG = "DiscCreateActivity";
    private static final String EXTRA_NEW_DISCUSSION = "com.bignerdranch.android.kaisoapp.new_discussion";

    private Discussion mDiscussion;
    private EditText mDiscussionTitle;
    private EditText mDiscussionPoint;
    private Button mSubmitbtn;
  //  private List<Discussion> mDiscussions = DiscussionArchive.get(this).getDiscussions();
    private List<String> mDiscussionPoints = new ArrayList<>();

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, DiscussionCreateActivity.class);
        return i;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_discussion);

        mDiscussionTitle = (EditText) findViewById(R.id.discussion_title1);
        mDiscussionPoint = (EditText) findViewById(R.id.discussion_point1);

        mSubmitbtn = (Button) findViewById(R.id.submit_disc_btn);
        mSubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDiscussion();
            }
        });

    }
    private void createDiscussion() {
            String discussionTitle = mDiscussionTitle.getText().toString().trim();
            String discussionPoint = mDiscussionPoint.getText().toString().trim();

            boolean validationError = false;
            StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
            if (discussionTitle.length() == 0) {
                validationError = true;
                validationErrorMessage.append(getString(R.string.error_blank_title));
            }
            if (discussionPoint.length() == 0) {
                if (validationError) {
                    validationErrorMessage.append(getString(R.string.error_join));
                }
                validationError = true;
                validationErrorMessage.append(getString(R.string.error_blank_discussion_point));
            }

            validationErrorMessage.append(getString(R.string.error_end));

            if (validationError) {
                Toast.makeText(DiscussionCreateActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                        .show();
                return;
            }

            // Set up and start a progress dialog

            // Set up a new Discussion
        mDiscussion = new Discussion();
        mDiscussion.setTitle(discussionTitle);
        mDiscussionPoints.add(discussionPoint);
        mDiscussion.setPoints(mDiscussionPoints);
      //  mDiscussions.add(mDiscussion);

        mDiscussion.saveInBackground();
        finish();
    }
}

      /*  mDiscussionTitle.setText(mDiscussion.getTitle());
        mDiscussionTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mDiscussion.setTitle(s.toString());
                if (s.toString() != null && s.toString().length() > 0) {
                    flag1 = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
*/
/*
        mDiscussionPoint.setText(mDiscussion.getDiscussionPoint());
        mDiscussionPoint.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mDiscussion.setDiscussionPoint(s.toString());
                if (s.toString() != null && s.toString().length() > 0) {
                    flag2 = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
*/
 //            ParseUser user = new ParseUser();
//          user.setUsername(username);
//        user.setPassword(password);
// Call the Parse signup method
//      user.signUpInBackground(new SignUpCallback() {
//        @Override
//      public void done(ParseException e) {
// Handle the response
//    }
//   });
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


