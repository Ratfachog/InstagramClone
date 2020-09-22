package com.dperky2910.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        if (ParseUser.getCurrentUser() != null) {
        ParseUser.getCurrentUser().logOut(); }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnLogin:
                ParseUser.logInInBackground(edtLogEmail.getText().toString(),
                        edtLogEmail.getText().toString(),
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {

                                if (user != null && e == null) {
                                    FancyToast.makeText(LoginActivity.this, "Welcome back.",
                                            Toast.LENGTH_SHORT, FancyToast.SUCCESS, false);
                                } else if (e != null) {
                                    FancyToast.makeText(LoginActivity.this, e.getMessage(),
                                            Toast.LENGTH_SHORT, FancyToast.ERROR, false);
                                }
                            }
                        });
                break;

            case R.id.btnOrSignUp:
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
                break;
        }
    }
}