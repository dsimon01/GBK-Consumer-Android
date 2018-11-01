package com.gbk.simoni.gbk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderUpdatesActivity extends AppCompatActivity {

    ArrayList<String> orderItems = MenuActivity.selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_updates);


        ListView orderItemsList = findViewById(R.id.orderItemsPlacedListView);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,orderItems);
        orderItemsList.setAdapter(adapter);



    }
}
