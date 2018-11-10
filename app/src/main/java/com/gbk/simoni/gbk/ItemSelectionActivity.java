package com.gbk.simoni.gbk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemSelectionActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_selection_activity);

        getIntentFromRecyclerAdapter();
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

}