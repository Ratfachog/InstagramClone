package com.dperky2910.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        if (ParseUser.getCurrentUser() != null) {

            ParseUser.getCurrentUser().logOut();
        }

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnOrSignUp:

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
                        } else {
                            FancyToast.makeText(SignUp.this, "error: " + e.getMessage(),
                                    Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                        }
                    }
                });
                break;

            case R.id.btnLogin:
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
                break;
        }

    }
}