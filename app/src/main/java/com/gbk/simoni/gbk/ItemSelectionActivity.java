package com.gbk.simoni.gbk;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ItemSelectionActivity extends AppCompatActivity {

    int counterValue = 1; // holds the value of items to add

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_selection_activity);
        // calling function implemented below which receives and forms the content of the intent.
        getIntentFromRecyclerAdapter();
        final Button button = findViewById(R.id.addToBasketButton);
        FloatingActionButton incrementCount = findViewById(R.id.addFloatingActionButton);
        final TextView itemsToAdd = findViewById(R.id.numberOfItems);
        // OnClickListener on when the user is incrementing the items to add in their basket.
        incrementCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterValue = counterValue + 1;
                itemsToAdd.setText(Integer.toString(counterValue));
                button.setText("ADD " + counterValue + " ITEMS TO BASKET");
                System.out.println(counterValue);
            }
        });
        //OnClickListener on when the user is decrementing the items for their basket
        //the function below updates the text shown in the button to add items to basket.
        final FloatingActionButton decrementCount = findViewById(R.id.removeFloatingActionButton);
        decrementCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counterValue > 1){
                    counterValue = counterValue - 1;
                    itemsToAdd.setText(Integer.toString(counterValue));
                    button.setText("ADD " + counterValue + " ITEMS TO BASKET");
                }
                if (counterValue == 1){
                    button.setText("ADD ITEM TO BASKET");
                }
                System.out.println(counterValue);
            }
        });
    }

    private void getIntentFromRecyclerAdapter(){

        //check if there are any intents to avoid crashing.
        if (getIntent().hasExtra("item_name")
                && getIntent().hasExtra("item_description")
                && getIntent().hasExtra("item_image")
                && getIntent().hasExtra("item_price")){

            String itemName = getIntent().getStringExtra("item_name");
            String itemDescription = getIntent().getStringExtra("item_description");
            double itemPrice = getIntent().getDoubleExtra("item_price", 0.00);
            int itemImage = getIntent().getIntExtra("item_image", 2131165283);

            setActivityContent(itemName,itemDescription,itemPrice,itemImage);
        }
    }

    private void setActivityContent(String itemName, String itemDescription,double itemPrice, int image){

        TextView name = findViewById(R.id.itemNameTextView);
        TextView desc = findViewById(R.id.itemDescriptionTextView);
      //  TextView price = findViewById(R.id.itemPriceTextView);
        ImageView images = findViewById(R.id.selectedItemImageView);
        name.setText(itemName);
        desc.setText(itemDescription);
      //  price.setText(Double.toString(itemPrice));
        images.setImageResource(image);
    }

    // REMOVE PUT EXTRA FROM HERE
    public void addToBasket(View view){
        String itemName = getIntent().getStringExtra("item_name");
        String itemDescription = getIntent().getStringExtra("item_description");
        int itemImage = getIntent().getIntExtra("item_image", 0);
        double itemPrice = getIntent().getDoubleExtra("item_price", 0.00);
        int counterVal = getIntent().getIntExtra("counter_value", counterValue);
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("item_name", itemName);
        intent.putExtra("item_description", itemDescription);
        intent.putExtra("item_image", itemImage);
        intent.putExtra("item_price", itemPrice);
        intent.putExtra("counter_value", counterVal);
        System.out.println("YOU ARE SENDING " + counterValue + " ITEMS TO THE BASKET ACTIVITY");
        startActivity(intent);


    }


}