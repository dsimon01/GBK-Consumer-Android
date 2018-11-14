package com.gbk.simoni.gbk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasketActivity extends AppCompatActivity {

    //try passing parse the object selectedItemsList rather than 4 lists

    List<Items> orderItems = new ArrayList<>();
    ArrayList<String> itemNamesList = new ArrayList<>();
    ArrayList<String> itemDescriptionList = new ArrayList<>();
    ArrayList<Double> itemPriceList = new ArrayList<>();
    ArrayList<Integer> itemImageList = new ArrayList<>();
    Items item = new Items();

    Gson gson = new Gson();
    String json = gson.toJson(MenuActivity.selectedItemsList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        System.out.println("JSON SER " + json + " " + MenuActivity.selectedItemsList.size());

        JSONArray jsonarray = null;

        try {

            jsonarray = new JSONArray(json);

        } catch (JSONException e) {

            e.printStackTrace();
        }

        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = null;
            try {

                jsonobject = jsonarray.getJSONObject(i);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            double price = 0;
            String name = "";
            String description = "";
            int image = 0;

            try {

                price = jsonobject.getDouble("price");
                name = jsonobject.getString("itemName");
                description = jsonobject.getString("itemDescription");
                image = jsonobject.getInt("itemImage");

            } catch (JSONException e) {
                e.printStackTrace();
            }



            item.itemName = name;
            itemNamesList.add(item.itemName);
            item.price = price;
            itemPriceList.add(item.price);
            item.itemDescription = description;
            itemDescriptionList.add(item.itemDescription);
            item.itemImage = image;
            itemImageList.add(item.itemImage);
            orderItems.add(item); // populate list with these items

        }
            ListView selectedItemsListView = findViewById(R.id.selectedItemsListView);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itemNamesList); // The list we get from menu activity goes here.);
            selectedItemsListView.setAdapter(adapter);

        System.out.println(orderItems);
        for (int i = 0; i < itemNamesList.size(); i++) {
            System.out.println(itemNamesList.get(i) + " ITEM NAME");
        }
    }


    public void onPlaceOrderClick(View view){
        System.out.println("PLACED ORDER");
        final int orderNumber = new Random().nextInt(9000) + 1000; // [0,8999] + 1000 => [1000, 9999]
        ParseObject order = new ParseObject("Order");
        order.put("TableNumber", ParseUser.getCurrentUser().getUsername());
        order.put("OrderID", orderNumber);
        order.put("Status", "new");
        order.put("Item", itemNamesList);
        order.put("Description", itemDescriptionList);
        order.put("Image", itemImageList);
        order.put("Price", itemPriceList);
        order.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Toast.makeText(BasketActivity.this, "Order is now COOKING", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), OrderUpdatesActivity.class);
                    startActivity(intent);
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

}


/*




         System.out.println("Here is your intents " + newList);
        // Log.i("You are passing", intent.getStringExtra("items Selected"));


        ArrayList<String> itemsSelected = new ArrayList<>();
         ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsSelected);




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
         final int orderNumber = new Random().nextInt(9000) + 1000; // [0,8999] + 1000 => [1000, 9999]
         ParseObject order = new ParseObject("Order");
        order.put("TableNumber", ParseUser.getCurrentUser().getUsername());
        order.put("OrderID", orderNumber);
        order.put("Status", "new");
        order.put("Item", newList);
         order.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                 if (e == null){
                     Toast.makeText(BasketActivity.this, "Order is now COOKING", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), OrderUpdatesActivity.class);
                    startActivity(intent);
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




=======================================================================================================




on create () {




        }

    }
}

*/

// Basket Activity
// MenuActivity.selectedItemsList.clear();
//MenuActivity.totalPrice = 0.00;