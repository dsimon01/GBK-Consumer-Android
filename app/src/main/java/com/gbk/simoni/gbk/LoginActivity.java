package com.gbk.simoni.gbk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    //When the user selects the login addToBasketButton:
    public void onLoginClick(View view){

        //locate their input for username and password and assign them to corresponding vars.
        final EditText username = findViewById(R.id.usernameEditText);
        EditText password = findViewById(R.id.passwordEditText);

        //error handling for the incorrect input.
        if (username.getText().toString().matches("") || password.getText().toString().matches("")){
            Toast.makeText(this, "Username/Password required", Toast.LENGTH_SHORT).show();
        }else {
            //validate the user input with the credentials stored in the server and attempt to login.
            //upon successful login, redirects the user to the Home activity
            ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null){
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else {
                        //if login fails show error in Toast.
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
