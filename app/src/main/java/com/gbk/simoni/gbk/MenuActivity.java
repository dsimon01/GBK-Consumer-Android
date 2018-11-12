package com.gbk.simoni.gbk;


import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

// TODO - > Add 2 or more items to the basket and reflect that to the sampleItem2 list
// TODO - > Calculate the total of an order about to go in the basket
// TODO - > Calculate the total count of items to add in the basket (print them to confirm correct behaviour)
// TODO - > Improve designs in item selection
// TODO - > Improve Bottom Nav bar - recycler view UI bug when bottom nav appears
// TODO - > Simply list the order items added to the basked activity
// TODO - > Test the Basket activity behaviour
// TODO - > Review code and add comments

public class MenuActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    CollapsingToolbarLayout collapsingToolbarLayout;

    static List<Items> sampleItem2 = new ArrayList<>();
    ArrayList<String> itemNameBasket = new ArrayList<>();
    ArrayList<String> itemDescBasket = new ArrayList<>();


    String[] itemName = {

            "GBK Cheese & Bacon",
            "Classic Beef",
            "The Mighty",
            "The Taxidriver",
            "Blue Cheese",
            "Kiwiburger",
            "GBK Applewood Cheese",
            "Avo Bacon",
            "Hot Diggity",
            "GBK American Cheese",
    };

    String[] price = {

            "£10.95",
            "£8.15",
            "£13.95",
            "£11.95",
            "£9.45",
            "£10.85",
            "£10.55",
            "£10.95",
            "£10.75",
            "£9.85",
    };

    String[] itemDesc = {

            "Crispy bacon, BBQ sauce, house mayo, dill pickle, salad.",
            "House mayo, mature Cheddar, relish, salad, chilli fried egg.",
            "Two 6oz patties, mature Cheddar, crispy bacon, garlic mayo, relish, dill pickle.",
            "American cheese, homemade onion ring, Cajun relish, chipotle mayo, dill pickle, salad.",
            "Onion jam, Cajun relish, house mayo, dill pickle, salad.",
            "6oz beef, mature Cheddar, beetroot, fried egg, grilled pineapple, house mayo, relish, salad",
            "Crispy bacon, BBQ sauce, house mayo, dill pickle, salad.",
            "Smashed avocado, crispy bacon, house mayo, relish.",
            "American and mature Cheddar cheese, chilli fried egg, basil mayo, habanero jam.",
            "Crispy bacon, BBQ sauce, house mayo, dill pickle, salad.",
    };

    public int[] image = {

            R.drawable.gbkhome,
            R.drawable.login,
            R.drawable.mighty,
            R.drawable.taxidriver,
            R.drawable.blue_cheese,
            R.drawable.kiwi,
            R.drawable.applewood,
            R.drawable.avobacon,
            R.drawable.diggity,
            R.drawable.american_cheese,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getIntentFromBasket();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setExpandedTitleColor(000);


        recyclerView = findViewById(R.id.recycler_view_id);
        List<Items> sampleItem = new ArrayList<>();

        for (int i = 0; i < itemName.length; i++) {

            Items item = new Items();
            item.itemName = itemName[i];
            item.price = price[i];
            item.itemDescription = itemDesc[i];
            item.itemImage = image[i];

            sampleItem.add(item);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(new RecyclerAdapter(sampleItem));

        }

    private void getIntentFromBasket(){

        if (getIntent().hasExtra("item_name") && getIntent().hasExtra("item_description")){
            Log.i("INTENTS FROM BASKET", "RECEIVED");
            String itemNameX = getIntent().getStringExtra("item_name");
            String itemDescription = getIntent().getStringExtra("item_description");
            itemNameBasket.add(itemNameX);
            itemDescBasket.add(itemDescription);
            Toast.makeText(this, "GOT " + itemNameX + " " + itemDescription, Toast.LENGTH_LONG).show();


            for (int i = 0; i < itemNameBasket.size(); i++) {

                Items item2 = new Items();
                item2.itemName = itemNameBasket.get(i);
                item2.itemDescription = itemDescBasket.get(i);
                //item2.itemImage = image[i];

                sampleItem2.add(item2);
            }

            if (sampleItem2.size() > 0){
                BottomNavigationView bar = findViewById(R.id.bottomNav_id);
                bar.setVisibility(View.VISIBLE);
                bar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(v.getContext(),BasketActivity.class);
                        v.getContext().startActivity(intent);


                    }
                });
            }
            System.out.println(sampleItem2.size() + " The size of sample2 " + sampleItem2);
            System.out.println(itemNameBasket.size() + " The size of itemNameBasket " + itemNameBasket);
            System.out.println(itemDescBasket.size() + " The size of itemDescBasket " + itemDescBasket);

        }

    }

}


/*
    Snackbar basketItemsSnack;


            View view = findViewById(R.id.menu_activity);
            basketItemsSnack = Snackbar.make(view, "ITEMS IN BASKET " + sampleItem2.size(), Snackbar.LENGTH_INDEFINITE);
            basketItemsSnack.setAction("View Basket", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    System.out.println("Clicked to view basket");

                }
            });
            View v = basketItemsSnack.getView();
            v.setBackgroundColor(getResources().getColor(R.color.snackBarBackground));
            TextView text = v.findViewById(android.support.design.R.id.snackbar_text);
            text.setTextColor(getResources().getColor(R.color.colorAccent));
            basketItemsSnack.show();
 */