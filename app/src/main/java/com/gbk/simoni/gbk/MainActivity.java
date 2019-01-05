package com.gbk.simoni.gbk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //once the main activity is ready redirect to Log in screen.
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);


        // code that keeps user logged in to the app, avoiding the login screen
        // redirecting to Home activity.
/*

        if (ParseUser.getCurrentUser() != null){
            Intent alreadyLoggedIn = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(alreadyLoggedIn);
        }

*/

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}
