package com.dperky2910.instagramclone;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText edtUsernameSign, edtUsernameLog, edtPasswordSign, edtPasswordLog;
    private Button btnSign, btnLog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup_login_activity);

        edtUsernameSign = findViewById(R.id.edtUserName);
        edtUsernameLog = findViewById(R.id.edtUsernameLog);
        edtPasswordSign = findViewById(R.id.edtPassword);
        edtPasswordLog = findViewById(R.id.edtPasswordLog);

        btnSign = findViewById(R.id.btnSign);
        btnLog = findViewById(R.id.btnLog);

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUsernameSign.getText().toString());
                appUser.setPassword(edtPasswordSign.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(SignUpLoginActivity.this, appUser.get("username") + " is signed up successfully.",
                                    FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                        } else {
                            FancyToast.makeText(SignUpLoginActivity.this, "error: " + e.getMessage(),
                                    FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                        }
                    }
                });

            }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(edtUsernameLog.getText().toString(), edtPasswordLog.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null) {
                            FancyToast.makeText(SignUpLoginActivity.this, "Log in for " + user.get("username") + " successful.",
                                    FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                        } else {
                            FancyToast.makeText(SignUpLoginActivity.this, "error: " + e.getMessage(),
                                    FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                        }
                    }
                });

            }
        });

    }
}
