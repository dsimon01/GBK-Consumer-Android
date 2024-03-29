package com.gbk.simoni.gbk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.parse.ParseAnalytics;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //once the main activity is ready - directs to Log in screen.
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}
