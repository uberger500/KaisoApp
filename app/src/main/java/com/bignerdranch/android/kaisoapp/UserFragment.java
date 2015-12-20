package com.bignerdranch.android.kaisoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by ursberger1 on 11/15/15.
 */
//The fragment for the user pagerview
public class UserFragment extends Fragment {

    private static final String ARG_USER_ID = "user_id";

    private ParseUser mUser;

    private TextView mName;
    private TextView mEmailAddress;
    private EditText mEmailSubject;
    private EditText mEmailBody;
    private Button mSendMessage;

    private String userId;

    public static UserFragment newInstance(String userId) {
        Bundle args = new Bundle();
        args.putString(ARG_USER_ID, userId);

        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId =  getArguments().getString(ARG_USER_ID);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_user, container, false);

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.getInBackground(userId, new GetCallback<ParseUser>() {
            public void done(ParseUser mUser, ParseException e) {
             if (e == null) {
                    mName = (TextView) v.findViewById(R.id.user_name);
                    mName.setText(mUser.getUsername());

                    mEmailAddress = (TextView) v.findViewById(R.id.user_email);
                    mEmailAddress.setText(mUser.getEmail());

                    mEmailSubject = (EditText) v.findViewById(R.id.user_email_subject);
                    mEmailBody = (EditText) v.findViewById(R.id.user_email_body);

                    mSendMessage = (Button) v.findViewById(R.id.send_message_button);
                    mSendMessage.setOnClickListener(new View.OnClickListener() {
                        //this code to send an email is based on code provided in:
                        //http://www.helloandroid.com/tutorials/how-send-email-your-application
                        @Override
                        public void onClick(View v) {
                            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                                    new String[]{mEmailAddress.getText().toString()});
                            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                                    mEmailSubject.getText().toString());
                            emailIntent.setType("plain/text");
                            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                                    mEmailBody.getText());
                            getContext().startActivity(Intent.createChooser(emailIntent,
                                    "Send mail..."));
                            getActivity().finish();
                        }
                    });
                } else {
                    Log.d("user", "Error: " + e.getMessage());
                }
            }
        });
        return v;
    }
}
