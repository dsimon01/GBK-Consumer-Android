package com.gbk.simoni.gbk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    //When the view Menu button is pressed by the user,they will be redirected to the Menu Activity.
    public void onViewMenuClick(View view){
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed(); commented this line in order to disable back press
        Toast.makeText(getApplicationContext(), "Back press disabled!",
                Toast.LENGTH_SHORT).show();
    }
}
