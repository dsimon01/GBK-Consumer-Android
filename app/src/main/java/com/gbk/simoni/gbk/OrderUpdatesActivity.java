package com.gbk.simoni.gbk;

import android.os.Handler;
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
import java.util.TimerTask;


public class OrderUpdatesActivity extends AppCompatActivity {

// Activity for order updates:

    RecyclerView basketRecyclerView;
  //  Handler handler;
  //  Runnable runnable;
   // Timer timer;
    TextView time;
    Calendar now;
    ImageView clock;
    ProgressBar progressBar;
   // int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_updates);

        time = findViewById(R.id.estimatedPrepTimeTextView);
        clock = findViewById(R.id.clock);

        progressBar = findViewById(R.id.linearProgressBar);
        progressBar.setVisibility(View.VISIBLE);
     //   progressBar.setProgress(0);
     //   progressBar.setMax(100);

    //    handler = new Handler();
      //  runnable = new Runnable() {
        //    @Override
          //  public void run() {

            //    if (++i <= 100){

              //      progressBar.setProgress(i);

                //}else {

                  //  timer.cancel();
                //}

          //  }
        //};

       // timer = new Timer();
        //timer.schedule(new TimerTask() {
          //  @Override
           // public void run() {
             //   handler.post(runnable);
           // }
       // }, 8000, 1000);

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
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed(); commented this line in order to disable back press
        Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
    }

}
