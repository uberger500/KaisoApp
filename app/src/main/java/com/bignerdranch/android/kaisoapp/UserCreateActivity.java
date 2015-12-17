package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by ursberger1 on 11/16/15.
 */
public class UserCreateActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mEmail;
    private EditText mPassword;
    private Button mSubmitbtn;

    private User mUser = new User();

    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, UserCreateActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_user);

        mName = (EditText) findViewById(R.id.editText_name);
        mEmail = (EditText) findViewById(R.id.editText_email);
        mPassword = (EditText) findViewById(R.id.editText_password);
        mSubmitbtn = (Button) findViewById(R.id.button_submit);
        mSubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    public void addUser() {

        String name = mName.getText().toString().trim().toLowerCase();
        String email = mEmail.getText().toString().trim().toLowerCase();
        String password = mPassword.getText().toString().trim();

        //this validation code is based on a website I can't remember...
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
        if (name.length() == 0) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_name));
        }
        if (email.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_email));
        }

        if (password.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_password));
        }

        validationErrorMessage.append(getString(R.string.error_end));

        if (validationError) {
            Toast.makeText(UserCreateActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        //parseUser is used to create a regular User
        ParseUser parseUser = new ParseUser();
        parseUser.setUsername(name);
        parseUser.setPassword(password);
        parseUser.setEmail(email);

        //Since I was not able to access the user information for display, I am creating a separate
        //user object for later display in a list of users
        mUser.setName(name);
        mUser.setEmail(email);

        parseUser.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(UserCreateActivity.this, "User is signed up", Toast.LENGTH_LONG)
                            .show();
                    mUser.saveInBackground();
                    finish();
                } else {
                    Log.d("parseUser", "Error: " + e.getMessage());
                    Toast.makeText(UserCreateActivity.this, e.getMessage(), Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }
}
