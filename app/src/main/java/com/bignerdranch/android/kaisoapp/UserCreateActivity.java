package com.bignerdranch.android.kaisoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ursberger1 on 11/16/15.
 */
public class UserCreateActivity extends AppCompatActivity {

    private static final String TAG = "UserCreateActivity";
    private static final String EXTRA_NEW_USER = "com.bignerdranch.android.kaisoapp.new_user";

    private EditText mName;
    private EditText mEmail;
    private EditText mPhone;
    private Button mSubmitbtn;
    private List<User>  mUsers = UserArchive.get(this).getUsers();
    private User mUser = new User();


  /*  public static Intent newIntent(Context packageContext, UUID userId) {
        Intent i = new Intent(packageContext, UserCreateActivity.class);
        i.putExtra(EXTRA_NEW_USER, userId);
        return i;
    }
*/
  public static Intent newIntent(Context packageContext) {
      Intent i = new Intent(packageContext, UserCreateActivity.class);
   //   i.putExtra(EXTRA_NEW_USER, userId);
      return i;
  }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    UUID userId = (UUID) getIntent().getSerializableExtra(EXTRA_NEW_USER);
     //   mUser = UserArchive.get(this).getUser(userId);
        setContentView(R.layout.activity_new_user);


        mName = (EditText) findViewById(R.id.editText_name);
     /*   mName.setText(mUser.getName());
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
        */
        mEmail = (EditText) findViewById(R.id.editText_email);
   /*     mEmail.setText(mUser.getEmail());
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
        mPhone = (EditText) findViewById(R.id.editText_phone);
    /*    mPhone.setText(mUser.getPhone());
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
*/
        mSubmitbtn = (Button) findViewById(R.id.button_submit);
        mSubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addUser();
         /*       if (mUsers.size() == 0 ) {
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
                */
            }
        });

    }

    public void addUser() {
        String name = mName.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String number = mPhone.getText().toString().trim();
        Integer phoneNumber = 0;

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

        if (number.isEmpty() == true) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_phone_number));
        } else {
            phoneNumber = Integer.parseInt(number);
        }

        validationErrorMessage.append(getString(R.string.error_end));

        if (validationError) {
            Toast.makeText(UserCreateActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        mUser.setName(name);
        mUser.setEmail(email);
        mUser.setPhone(phoneNumber);

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
}
