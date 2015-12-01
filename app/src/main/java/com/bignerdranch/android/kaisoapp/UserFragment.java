package com.bignerdranch.android.kaisoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by ursberger1 on 11/15/15.
 */
public class UserFragment extends Fragment {

    private static final String ARG_USER_ID = "user_id";

    private User mUser;

    private TextView mName;
    private TextView mEmail;
    private TextView mPhone;
    private Button mSendMessage;

    public static UserFragment newInstance(UUID userId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_ID, userId);

        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID userId = (UUID) getArguments().getSerializable(ARG_USER_ID);

        mUser = UserArchive.get(getActivity()).getUser(userId);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        mName = (TextView) v.findViewById(R.id.user_name);
        mName.setText(mUser.getName());

        mEmail = (TextView) v.findViewById(R.id.user_email);
        mEmail.setText(mUser.getEmail());

        mPhone = (TextView) v.findViewById(R.id.user_phone);
        mPhone.setText(mUser.getPhone().toString());

        mSendMessage = (Button) v.findViewById(R.id.send_message_button);
        mSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                startActivity(i);
            }
        });

        return v;
    }

}
