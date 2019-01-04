package com.gbk.simoni.gbk;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {

    EditText username;
    EditText password;

    @Override
    public void onBackPressed() {
        // super.onBackPressed(); commented this line in order to disable back press
        Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        password.setOnKeyListener(this);
        ConstraintLayout background  = findViewById(R.id.backgroundLayout);
        background.setOnClickListener(this);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (keyCode == event.KEYCODE_ENTER && event.getAction() == event.ACTION_DOWN) {
            onLoginClick(v);
        }
        return false;
    }

    public void onClick(View view){
        if (view.getId() == R.id.backgroundLayout || view.getId() == R.id.loginImageView){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    //When the user selects the login addToBasketButton:
    public void onLoginClick(View view) {

        //error handling for the incorrect input.
        if (username.getText().toString().matches("") || password.getText().toString().matches("")) {
            Toast.makeText(this, "Username/Password required", Toast.LENGTH_SHORT).show();
        } else {
            //validate the user input with the credentials stored in the server and attempt to login.
            //upon successful login, redirects the user to the Home activity
            ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        //if login fails show error in Toast.
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

