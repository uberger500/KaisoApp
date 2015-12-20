package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ursberger1 on 11/23/15.
 */
//The screen to create a discussion topic, this is activated by the go button when the list is
    //empty or by the button in the menubar
public class DiscussionCreateActivity extends AppCompatActivity {

    private static final String TAG = "DiscCreateActivity";
    private static final String EXTRA_NEW_DISCUSSION = "com.bignerdranch.android.kaisoapp.new_discussion";

    private Discussion mDiscussion;
    private EditText mDiscussionTitle;
    private EditText mDiscussionPoint;
    private Button mSubmitbtn;

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
            StringBuilder validationErrorMessage = new StringBuilder();
            if (discussionTitle.length() == 0) {
                validationError = true;
                validationErrorMessage.append(getString(R.string.error_blank_title));
                validationErrorMessage.append(" ");

            }
            if (discussionPoint.length() == 0) {
                if (validationError) {
                    validationErrorMessage.append(getString(R.string.error_join));
                    validationErrorMessage.append(" ");

                }
                validationError = true;
                validationErrorMessage.append(getString(R.string.error_blank_discussion_point));
                validationErrorMessage.append(" ");

            }

            if (validationError) {
                Toast.makeText(DiscussionCreateActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                        .show();
                return;
            }
        mDiscussion = new Discussion();
        mDiscussion.setTitle(discussionTitle);
        mDiscussionPoints.add(discussionPoint);
        mDiscussion.setPoints(mDiscussionPoints);
        mDiscussion.saveInBackground();
        finish();
    }
}



