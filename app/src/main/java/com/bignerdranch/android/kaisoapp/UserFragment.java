package com.bignerdranch.android.kaisoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.telephony.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;
import java.util.UUID;

/**
 * Created by ursberger1 on 11/15/15.
 */

public class UserFragment extends Fragment {

    private static final String ARG_USER_ID = "user_id";

    private ParseObject mUser;

    private TextView mName;
    private TextView mEmail;
    private TextView mPhone;
    private Button mSendMessage;

    private String userName;

    public static UserFragment newInstance(String userName) {
        Bundle args = new Bundle();
        args.putString(ARG_USER_ID, userName);

        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userName =  getArguments().getString(ARG_USER_ID);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_user, container, false);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("mName", userName);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> userList, ParseException e) {
                if (e == null) {
                    mUser = userList.get(0);
                    mName = (TextView) v.findViewById(R.id.user_name);
                    mName.setText(mUser.getString("mName"));

                    mEmail = (TextView) v.findViewById(R.id.user_email);
                    mEmail.setText(mUser.getString("mEmail"));

                    mPhone = (TextView) v.findViewById(R.id.user_phone);
                    mPhone.setText(mUser.getString("mPhone"));
                    Log.d("user", "Retrieved " + mUser.getString("mName"));

                    mSendMessage = (Button) v.findViewById(R.id.send_message_button);
                    mSendMessage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String phoneNo = mUser.getString("mPhone");
                            Log.d("phone", "phone " + phoneNo);
                            //   String sms = locationText.getText().toString();
/*
                            try {
                                //set up a smsManager to send message
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(phoneNo, null, null, null, null);
                                Toast.makeText(getContext(), "SMS Sent!",
                                        Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(getContext(),
                                        "SMS failed, please try again later!",
                                        Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                            */
                                  Intent i = new Intent(Intent.ACTION_SEND);
                                i.setType("text/plain");
                              startActivity(i);
                        }
                    });
                    // object will be your game score
                } else {
                    Log.d("user", "Error: " + e.getMessage());
                }
            }
        });
        return v;
    }

}
