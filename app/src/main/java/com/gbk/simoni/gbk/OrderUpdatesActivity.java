package com.gbk.simoni.gbk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderUpdatesActivity extends AppCompatActivity {

// Activity for order updates:

    RecyclerView basketRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_updates);

        basketRecyclerView = findViewById(R.id.recyclerViewBasket);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        basketRecyclerView.setLayoutManager(linearLayoutManager);
        basketRecyclerView.setHasFixedSize(true);
        basketRecyclerView.setAdapter(new BasketAdapter(MenuActivity.selectedItemsList));

    }
}
