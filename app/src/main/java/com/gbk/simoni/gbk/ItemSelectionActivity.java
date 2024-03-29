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


    private int counterValue = 1;
    private Button addToBasketButton;
    private FloatingActionButton incrementCount, decrementCount;
    private TextView itemsToAdd;
    private String itemName, itemDescription;
    private double itemPrice;
    private int itemImage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_selection_activity);
        addToBasketButton = findViewById(R.id.addToBasketButton);
        incrementCount = findViewById(R.id.addFloatingActionButton);
        decrementCount = findViewById(R.id.removeFloatingActionButton);
        itemsToAdd = findViewById(R.id.numberOfItems);

        /*
        Method call to retrieve data passed from the MenuRecyclerAdapter and then calls internally
        another method which populates the activity resources with the data retrieved.
        */
        getIntentFromRecyclerAdapter();

        // OnClickListener on when the user is incrementing the items to add in their basket.
        incrementListener();

        //OnClickListener on when the user is decrementing the items for their basket
        decrementListener();
    }

    private void getIntentFromRecyclerAdapter() {
        //check if there are any intents to avoid crashing.
        if (getIntent().hasExtra("item_name")
                && getIntent().hasExtra("item_description")
                && getIntent().hasExtra("item_image")
                && getIntent().hasExtra("item_price")) {
            itemName = getIntent().getStringExtra("item_name");
            itemDescription = getIntent().getStringExtra("item_description");
            itemPrice = getIntent().getDoubleExtra("item_price", 0.00);
            itemImage = getIntent().getIntExtra("item_image", 2131165283);
            // Calls method to set content based on the above variable values
            setActivityContent(itemName, itemDescription, itemImage);
        }
    }

    private void incrementListener(){
        incrementCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterValue = counterValue + 1;
                itemsToAdd.setText(Integer.toString(counterValue));
                addToBasketButton.setText("ADD " + counterValue + " ITEMS TO BASKET");
                System.out.println(counterValue);
            }
        });
    }

    private void decrementListener(){
            decrementCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (counterValue > 1) {
                        counterValue = counterValue - 1;
                        itemsToAdd.setText(Integer.toString(counterValue));
                        addToBasketButton.setText("ADD " + counterValue + " ITEMS TO BASKET");
                    }
                    if (counterValue == 1) {
                        addToBasketButton.setText("ADD ITEM TO BASKET");
                    }
                    System.out.println(counterValue);
                }
            });
        }


    //Method that expects values to set the content of the activity.
    private void setActivityContent(String itemName, String itemDescription, int image) {

        TextView name = findViewById(R.id.itemNameTextView);
        TextView desc = findViewById(R.id.itemDescriptionTextView);
        ImageView images = findViewById(R.id.selectedItemImageView);
        name.setText(itemName);
        desc.setText(itemDescription);
        images.setImageResource(image);
    }

    /*
    When the user is clicking on to add their items to the basket, the following code
    is executed. All item data is passed to the Menu activity where it is converted into an Item
    object and added to the user's selected items.
    */
    public void addToBasket(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("item_name", itemName);
        intent.putExtra("item_description", itemDescription);
        intent.putExtra("item_image", itemImage);
        intent.putExtra("item_price", itemPrice);
        intent.putExtra("counter_value", counterValue);
        startActivity(intent);
    }


}