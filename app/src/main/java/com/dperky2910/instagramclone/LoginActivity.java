package com.dperky2910.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin, btnOrSign;
    private EditText edtLogEmail, edtLogPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Instasham - Log in");

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnOrSign = findViewById(R.id.btnOrSignUp);
        btnOrSign.setOnClickListener(this);

        edtLogEmail = findViewById(R.id.edtEmailLog);
        edtLogPassword = findViewById(R.id.edtPasswordLog);
        edtLogPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER &&
                        event.getAction() == KeyEvent.ACTION_DOWN) {

                    onClick(btnLogin);
                }
                return false;
            }
        });

        if (ParseUser.getCurrentUser() != null) {
        ParseUser.getCurrentUser().logOut(); }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnLogin:

                if (edtLogEmail.getText().toString().equals("") || edtLogPassword.getText().toString().equals("")) {
                    FancyToast.makeText(LoginActivity.this, "please fill in all required fields",
                            Toast.LENGTH_SHORT, FancyToast.INFO, false).show();
                } else {
                    ParseUser.logInInBackground(edtLogEmail.getText().toString(),
                            edtLogPassword.getText().toString(),
                            new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {

                                    if (user != null && e == null) {
                                        FancyToast.makeText(LoginActivity.this, "Welcome back, " + ParseUser.getCurrentUser().getUsername() + "!",
                                                Toast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                                        transitionToSocialMediaActivity();
                                    } else if (e != null) {
                                        FancyToast.makeText(LoginActivity.this, e.getMessage(),
                                                Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                                    }
                                }
                            });
                }
                break;

            case R.id.btnOrSignUp:
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
                break;
        }
    }

    public void layoutTapped(View view) {

        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void transitionToSocialMediaActivity() {

        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);
    }
}