package com.gbk.simoni.gbk;

import android.content.Intent;
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
import java.util.Random;

public class OrderUpdatesActivity extends AppCompatActivity {

// Activity for order updates:

    private TextView time, prep, hurray, collect;
    private ImageView clock, food;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_updates);

        // Defines the Activity's views by locating them in the Resources file.
        defineViews();

        // Arranges the items selected into a recycler view by calling the Adapter constructor.
        displayItemsInRecyclerView();

        // Estimates preparation time based on the order size.
        estimatedPreparationTime();

        /*
         GET request using a Handler to database that runs every 15'' looking for order updates.
         Calls internally a method to update the Activity View if the order is Ready.
         Deletes the order from the Database and redirects the user to Home activity.
        */
        getOrderUpdates();
    }

    private void defineViews(){
        time = findViewById(R.id.estimatedPrepTimeTextView);
        clock = findViewById(R.id.clock);
        prep = findViewById(R.id.textViewPrep);
        hurray = findViewById(R.id.hurrayTextView);
        collect = findViewById(R.id.collectTextView);
        food = findViewById(R.id.foodImageView);
        progressBar = findViewById(R.id.linearProgressBar);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void displayItemsInRecyclerView(){
        RecyclerView basketRecyclerView = findViewById(R.id.recyclerViewBasket);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        basketRecyclerView.setLayoutManager(linearLayoutManager);
        basketRecyclerView.setHasFixedSize(true);
        basketRecyclerView.setAdapter(new OrderUpdatesAdapter(MenuActivity.populateBasketRecycler));
    }

    private void estimatedPreparationTime(){
         Calendar now = Calendar.getInstance();

        if (MenuActivity.selectedItemsList.size() <= 2){
            now.add(Calendar.MINUTE, 10);
        }else if (MenuActivity.selectedItemsList.size() > 2 &&
                MenuActivity.selectedItemsList.size() <= 5){
            now.add(Calendar.MINUTE, 20);
        }else {
            now.add(Calendar.MINUTE, 30);
        }

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        time.setText(timeFormat.format(now.getTime()));

        System.out.println("Estimated time is: " + timeFormat.format(now.getTime()));
    }

    private void getOrderUpdates(){

        final Handler handler = new Handler();
        final int delay = 15000; //milliseconds

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

                                if (object.getString("Status").equals("ready")) {
                                    System.out.println("ORDER IS READY");
                                    // call method to update text views
                                    updateOrder();
                                    // delete from DB once ready
                                    object.deleteInBackground();
                                    handler.removeCallbacksAndMessages(null);
                                }else {
                                    System.out.println("The status of the order is: "
                                            + object.getString("Status"));
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
    }

    private void updateOrder(){

        int collectionPoint = new Random().nextInt(10) + 1; // [0,10] + 1 => [1, 10]

        progressBar.setVisibility(View.GONE);
        clock.setVisibility(View.GONE);
        time.setVisibility(View.GONE);
        prep.setVisibility(View.GONE);
        hurray.setVisibility(View.VISIBLE);
        food.setVisibility(View.VISIBLE);
        collect.setText("Please collect from counter " + Integer.toString(collectionPoint)
                + ", your order number is #" + BasketActivity.orderNumber);
        collect.setVisibility(View.VISIBLE);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("Redirect to home screen schedule");
                MenuActivity.selectedItemsList.clear();
                MenuActivity.populateBasketRecycler.clear();
                MenuActivity.totalPrice = 0.00;
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        }, 10000);
    }

    // Method that blocks back press.
    @Override
    public void onBackPressed() {
        // super.onBackPressed(); commented this line in order to disable back press
    }
}