package com.gbk.simoni.gbk;

import android.content.DialogInterface;
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
import android.widget.Toast;
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
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class BasketActivity extends AppCompatActivity {

    Toolbar toolbar;
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    static List<Items> orderItems;
    ArrayList<String> itemNamesList , itemDescriptionList;
    ArrayList<Double> itemPriceList;
    ArrayList<Integer> itemImageList;
    ImageView bin;
    TextView totalPrice;
    Items item = new Items();
    RecyclerView basketRecyclerView;
    Gson gson = new Gson();
    String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basket_activity);

        orderItems = new ArrayList<>();
        itemNamesList = new ArrayList<>();
        itemPriceList = new ArrayList<>();
        itemImageList = new ArrayList<>();
        itemDescriptionList = new ArrayList<>();
        basketRecyclerView = findViewById(R.id.recyclerViewBasket);
        bin = findViewById(R.id.binImage);
        totalPrice = findViewById(R.id.totalPrice);
        json = gson.toJson(MenuActivity.selectedItemsList);
        totalPrice.setText((String.format(Locale.ENGLISH, "£%.2f", MenuActivity.totalPrice)));

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
            orderItems.add(item);
        }

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        basketRecyclerView.setLayoutManager(linearLayoutManager);
        basketRecyclerView.setHasFixedSize(true);
        basketRecyclerView.setAdapter(new BasketAdapter(MenuActivity.selectedItemsList));

        bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("BIN CLICKED:");
                setDialog();
            }
        });
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

    public void setDialog() {

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
