package com.gbk.simoni.gbk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;

// Button to view Basket pressed from Menu Activity:
// This is a test for the new branch






// if this button is pressed then an order with the items fetched is created to parse server.

// give the option to remove a specific item.

public class BasketActivity extends AppCompatActivity {

    ArrayList<String> newList = MenuActivity.selectedItems;

    public void onRemoveItemsClick(View view){

        newList.clear();
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);

    }

    // IF : orderlist != null maybe to do.
    public void onPlaceOrderClick(View view){

        Log.i("Place order", "SUCCESS");

        ParseObject order = new ParseObject("Order");
        order.put("TableNumber", ParseUser.getCurrentUser().getUsername());
        order.put("OrderID", 19); // where 10 is random no
        order.put("Status", "new");
        order.put("Item", newList);

        order.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null){

                    Toast.makeText(BasketActivity.this, "Order is now COOKING", Toast.LENGTH_SHORT).show();

                }else {

                    e.printStackTrace();
                }

            }
        });


        order.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException ex) {
                if (ex == null) {
                    Log.i("Parse Result", "Successful!");
                } else {
                    Log.i("Parse Result", "Failed" + ex.toString());
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        //Intent intent = getIntent();
        //newList.clear();  to clear all items -- you gonna need it somewhere.


        ListView selectedItemsListView = findViewById(R.id.selectedItemsListView);



        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,newList);
        selectedItemsListView.setAdapter(adapter);


        System.out.println("Here is your intents " + newList);



       // Log.i("You are passing", intent.getStringExtra("items Selected"));

        /*
        ArrayList<String> itemsSelected = new ArrayList<>();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsSelected);
        */
    }
}
