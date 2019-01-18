package com.gbk.simoni.gbk;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class BasketActivity extends AppCompatActivity {


    private ProgressDialog dialog;
    static int orderNumber;
    private ArrayList<String> itemNamesList, itemDescriptionList;
    private ArrayList<Double> itemPriceList;
    private ArrayList<Integer> itemImageList;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basket_activity);

        // Call to method which finds text views and assigns them a value to display.
        orderSummary();

        // Call to method that arranges the display of a toolbar
        // Calls internally a dialog function when the bin icon within the toolbar is clicked.
        setToolbar();

        // Call to method that arranges the user's items so far in a recycler view.
        setRecycler();

        // The following method obtains an array list of objects and retrieves each object's
        // attributes.
        retrieveObjectData();

    }

    private void orderSummary() {

        TextView itemNumberSummary = findViewById(R.id.itemNumberSummary);
        TextView totalPrice = findViewById(R.id.totalPrice);
        totalPrice.setText((String.format(Locale.ENGLISH, "£%.2f", MenuActivity.totalPrice)));
        itemNumberSummary.setText(Integer.toString(MenuActivity.selectedItemsList.size()));

    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        ImageView bin = findViewById(R.id.binImage);
        setSupportActionBar(toolbar);
        bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBinDialog();
            }
        });

    }

    private void setRecycler() {

        RecyclerView basketRecyclerView = findViewById(R.id.recyclerViewBasket);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        basketRecyclerView.setLayoutManager(linearLayoutManager);
        basketRecyclerView.setHasFixedSize(true);
        // calls the constructor of BasketAdapter with the user item selection list:
        basketRecyclerView.setAdapter(new BasketAdapter(MenuActivity.selectedItemsList));
    }


    /*
    The method below deals with the deserialization of an object in the users list of items.
    The reason for that is so the attributes of the object are retrieved separately and added
    to the database, without deserialization only the objects memory location can be referenced.
     */
    private void retrieveObjectData() {
        /*
        Calls a function to create an arrayList for each of the objects attributes
        The lists then will be used later on to populate the database.
        */
        createArrayLists();

        /*
        converts gson to json string, example output :
        {"itemDescription":"House mayo, mature Cheddar, relish, salad, chilli fried egg.",
        "itemImage":2131165302,"itemName":"Classic Beef","price":10.65}
        */
        String json = gson.toJson(MenuActivity.selectedItemsList);

        // Declaring an array of type JSONArray and using the json created above as a parameter.
        JSONArray jsonarray = null;

        try {

            jsonarray = new JSONArray(json);

        } catch (JSONException e) {

            e.printStackTrace();
        }

        /*
        For every element in the array created above, gets the Object which is separated from
        other objects in Json with the use of { } (see example output above).
        */

        if (jsonarray != null) {
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = null;
                try {

                    jsonobject = jsonarray.getJSONObject(i);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Declaring default values to avoid Null exceptions.

                double price = 0;
                String name = "";
                String description = "";
                int image = 0;

                try {

                    /*
                    Based on the objects attributes retrieves them and assigns each attribute
                    to a variable defined above.
                    */
                    price = jsonobject.getDouble("price");
                    name = jsonobject.getString("itemName");
                    description = jsonobject.getString("itemDescription");
                    image = jsonobject.getInt("itemImage");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Creates new item object with the attributes retrieved from the Json object
                Items item = new Items(name, description, price, image);
                itemNamesList.add(item.getItemName());
                itemPriceList.add(item.getPrice());
                itemDescriptionList.add(item.getItemDescription());
                itemImageList.add(item.getItemImage());

            }
        }
    }

    private void createArrayLists() {

        itemNamesList = new ArrayList<>();
        itemPriceList = new ArrayList<>();
        itemImageList = new ArrayList<>();
        itemDescriptionList = new ArrayList<>();

    }

    /*
    The below function is executed when the user has decided to place their order.
    Shows a dialog for 2 seconds for confirmation of the action using a Handler.
    Generates a random Order Number.
    Populates the database with the elements of each array list with a PUT request.
    Directs the user to a new activity.
    */

    public void onPlaceOrderClick(View view) {

        dialog = new ProgressDialog(BasketActivity.this);
        dialog.setTitle("Processing your order");
        dialog.setMessage("Please wait...");
        dialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                orderNumber = new Random().nextInt(9000) + 1000;
                // [0,8999] + 1000 => [1000, 9999]
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
                        if (e == null) {
                            Intent intent = new Intent(getApplicationContext(),
                                    OrderUpdatesActivity.class);
                            startActivity(intent);
                        } else {
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

                dialog.cancel();
            }
        }, 2000);
    }

    /*
    Method that generates a dialog when the user taps on the bin icon in the toolbar.
    Shows an option to cancel or delete.
    if delete is selected, clears the array list, resets the value of the total price
    and for the changes to update redirects the user to the menu activity.
     */
    private void setBinDialog() {

        AlertDialog alertDialog;
        AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(this);
        builder.setMessage("Remove all items in basket?");
        builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                System.out.println("clicked on remove!");
                MenuActivity.totalPrice = 0.00;
                MenuActivity.selectedItemsList.clear();
                Intent intent = new Intent(BasketActivity.this, MenuActivity.class);
                startActivity(intent);

            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                System.out.println("clicked on cancel");

            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }
}
