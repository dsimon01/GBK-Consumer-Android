package com.gbk.simoni.gbk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

public class OrderUpdatesActivity extends AppCompatActivity {

// Activity for order updates:

    RecyclerView basketRecyclerView;
    TextView time;
    Calendar now;
    ImageView clock;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_updates);

        time = findViewById(R.id.estimatedPrepTimeTextView);
        clock = findViewById(R.id.clock);

        progressBar = findViewById(R.id.linearProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        now = Calendar.getInstance();
        now.add(Calendar.MINUTE, 30);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        System.out.println("Estimated time is: " + timeFormat.format(now.getTime()));

        time.setText(timeFormat.format(now.getTime()));

        basketRecyclerView = findViewById(R.id.recyclerViewBasket);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        basketRecyclerView.setLayoutManager(linearLayoutManager);
        basketRecyclerView.setHasFixedSize(true);
        basketRecyclerView.setAdapter(new BasketAdapter(MenuActivity.selectedItemsList));

        Timer timer = new Timer();
        timer.schedule(new Ping(), 0, 10000);
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed(); commented this line in order to disable back press
        Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
    }

}
