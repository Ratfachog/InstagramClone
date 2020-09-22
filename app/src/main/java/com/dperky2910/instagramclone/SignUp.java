package com.dperky2910.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSign, btnOrLogin;
    private EditText edtUser, edtEmail, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Instasham - Sign Up");

        btnSign = findViewById(R.id.btnOrSignUp);
        btnSign.setOnClickListener(this);
        btnOrLogin = findViewById(R.id.btnLogin);
        btnOrLogin.setOnClickListener(this);

        edtUser = findViewById(R.id.edtUsernameLog);
        edtEmail = findViewById(R.id.edtEmailLog);
        edtPassword = findViewById(R.id.edtPasswordLog);
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER &&
                        event.getAction() == KeyEvent.ACTION_DOWN) {

                    onClick(btnSign);
                }
                return false;
            }
        });

        if (ParseUser.getCurrentUser() != null) {
            transitionToSocialMediaActivity();
        }

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnOrSignUp:

                if (edtEmail.getText().toString().equals("") || edtUser.getText().toString().equals("") || edtPassword.getText().toString().equals("")) {
                    FancyToast.makeText(SignUp.this, "please fill in all required fields",
                            Toast.LENGTH_SHORT, FancyToast.INFO, false).show();
                } else {

                final ParseUser appUser = new ParseUser();
                appUser.setEmail(edtEmail.getText().toString());
                appUser.setUsername(edtUser.getText().toString());
                appUser.setPassword(edtPassword.getText().toString());


                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(SignUp.this, "Welcome, " + appUser.getUsername() + "!",
                                    Toast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                            transitionToSocialMediaActivity();
                        } else {
                            FancyToast.makeText(SignUp.this, "error: " + e.getMessage(),
                                    Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                        }
                    }
                });
        }
                break;

            case R.id.btnLogin:
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
                break;
        }

    }

    public void routeLayoutTapped(View view) {

        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void transitionToSocialMediaActivity() {

        Intent intent = new Intent(SignUp.this, SocialMediaActivity.class);
        startActivity(intent);
    }
}