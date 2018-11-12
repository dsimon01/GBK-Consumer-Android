package com.gbk.simoni.gbk;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemSelectionActivity extends AppCompatActivity {

    int defaultValue = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_selection_activity);


        getIntentFromRecyclerAdapter();

        final Button button = findViewById(R.id.addToBasketButton);
        FloatingActionButton incrementCount = findViewById(R.id.addFloatingActionButton);
        final TextView itemsToAdd = findViewById(R.id.numberOfItems);
        incrementCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultValue = defaultValue + 1;
                itemsToAdd.setText(Integer.toString(defaultValue));
                button.setText("ADD " + defaultValue + " ITEMS TO BASKET");
                System.out.println(defaultValue);
            }
        });

        final FloatingActionButton decrementCount = findViewById(R.id.removeFloatingActionButton);
        decrementCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (defaultValue > 1){
                    defaultValue = defaultValue - 1;
                    itemsToAdd.setText(Integer.toString(defaultValue));
                    button.setText("ADD " + defaultValue + " ITEMS TO BASKET");
                }

                if (defaultValue == 1){
                    button.setText("ADD ITEM TO BASKET");
                }
            }
        });
    }

    private void getIntentFromRecyclerAdapter(){

        //check if there are any intents to avoid crashing.
        if (getIntent().hasExtra("item_name") && getIntent().hasExtra("item_description") && getIntent().hasExtra("item_image")){
            Log.i("INTENTS FROM RA", "RECEIVED");

            String itemName = getIntent().getStringExtra("item_name");
            String itemDescription = getIntent().getStringExtra("item_description");
            int itemImage = getIntent().getIntExtra("item_image", 2131165283);

            setActivityContent(itemName,itemDescription,itemImage);

        }
    }

    private void setActivityContent(String itemName, String itemDescription, int image){

        TextView name = findViewById(R.id.itemNameTextView);
        TextView desc = findViewById(R.id.itemDescriptionTextView);
        ImageView images = findViewById(R.id.selectedItemImageView);

        name.setText(itemName);
        desc.setText(itemDescription);
        images.setImageResource(image);

    }

    public void addToBasket(View view){

        String itemName = getIntent().getStringExtra("item_name");
        String itemDescription = getIntent().getStringExtra("item_description");
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("item_name", itemName);
        intent.putExtra("item_description", itemDescription);
        startActivity(intent);

    }


}