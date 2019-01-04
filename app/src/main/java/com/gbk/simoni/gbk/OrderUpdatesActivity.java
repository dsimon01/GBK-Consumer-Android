package com.gbk.simoni.gbk;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class OrderUpdatesActivity extends AppCompatActivity {

// Activity for order updates:

    RecyclerView basketRecyclerView;
    TextView time;
    Calendar now;
    ImageView clock;
    ProgressBar progressBar;
    Boolean orderReady;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_updates);


        final Handler handler = new Handler();
        final int delay = 15000; //milliseconds

        time = findViewById(R.id.estimatedPrepTimeTextView);
        clock = findViewById(R.id.clock);
        progressBar = findViewById(R.id.linearProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        now = Calendar.getInstance();
        now.add(Calendar.MINUTE, 30);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        System.out.println("Estimated time is: " + timeFormat.format(now.getTime()));
        orderReady = false;
        time.setText(timeFormat.format(now.getTime()));

        handler.postDelayed(new Runnable(){
            public void run(){
                System.out.println(" -->> scheduled ping for order updates");

                ParseQuery<ParseObject> orderStatusUpdate = ParseQuery.getQuery("Order");
                orderStatusUpdate.whereEqualTo("OrderID", BasketActivity.orderNumber);

                orderStatusUpdate.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null && objects != null) {
                            for (ParseObject object : objects) {

                                if (object.getString("Status").equals("Ready")) {
                                    System.out.println("ORDER IS READY");
                                    // call method to update text views and return to home screen after 5 minutes - collect from till number
                                        updateOrder();
                                }else {
                                    System.out.println("ORDER NOT READY");
                                }
                            }
                        } else {
                            Log.i("ERROR", "ERROR");
                            e.printStackTrace();
                        }
                    }
                });

                handler.postDelayed(this, delay);
            }
        }, delay);

        basketRecyclerView = findViewById(R.id.recyclerViewBasket);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        basketRecyclerView.setLayoutManager(linearLayoutManager);
        basketRecyclerView.setHasFixedSize(true);
        basketRecyclerView.setAdapter(new BasketAdapter(MenuActivity.selectedItemsList));

        System.out.println("ORDER NUMBER RETURNED " + BasketActivity.orderNumber);
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed(); commented this line in order to disable back press
        Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
    }

    public void updateOrder(){
        progressBar.setVisibility(View.GONE);
        clock.setVisibility(View.GONE);
        time.setText("Hooray your order is ready");
    }
}