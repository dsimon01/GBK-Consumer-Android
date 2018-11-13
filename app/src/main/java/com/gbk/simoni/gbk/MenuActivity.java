package com.gbk.simoni.gbk;


import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


// TODO - > Improve designs in item selection
// TODO - > Improve Bottom Nav bar - recycler view UI bug when bottom nav appears
// TODO - > Simply list the order items added to the basked activity
// TODO - > Test the Basket activity behaviour
// TODO - > Review code and add comments
// TODO - > TODO for the next day

public class MenuActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Items item2 = new Items();

    static List<Items> selectedItemsList = new ArrayList<>(); // list stays updated until cleared.

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

        List<Items> menuItem = new ArrayList<>();
        for (int i = 0; i < itemName.length; i++) {
            Items item = new Items();
            item.itemName = itemName[i];
            item.price = price[i];
            item.itemDescription = itemDesc[i];
            item.itemImage = image[i];
            menuItem.add(item);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(new RecyclerAdapter(menuItem));

        }

    private void getIntentFromBasket(){

        if (getIntent().hasExtra("item_name")
                && getIntent().hasExtra("item_description")
                && getIntent().hasExtra("item_price")
                && getIntent().hasExtra("counter_value")){

            String itemNameX = getIntent().getStringExtra("item_name");
            String itemDescription = getIntent().getStringExtra("item_description");
            String itemPrice = getIntent().getStringExtra("item_price");
            int counterValue = getIntent().getIntExtra("counter_value", 1);
            System.out.println("RECEIVED FROM ITEM SELECTION " + itemNameX + " " + itemDescription + " " + counterValue + " Times");

            addItemsToStaticList(counterValue, itemNameX, itemDescription, itemPrice);
            showBottomNavBar();

            System.out.println(selectedItemsList.size() + " The size of selected items list " + selectedItemsList);
        }

    }

    public void addItemsToStaticList(int counter, String name, String description, String price){

        for (int i = 0; i < counter; i++) {


            item2.itemName = name;
            item2.itemDescription = description;
            item2.price = price;

            selectedItemsList.add(item2);

        }
    }

    public void showBottomNavBar(){

        if (selectedItemsList.size() > 0) {
            BottomNavigationView bar = findViewById(R.id.bottomNav_id);
            bar.setVisibility(View.VISIBLE);
            showItemCount();
            showPrice();

            bar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),BasketActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public void showPrice(){


        TextView price = findViewById(R.id.priceBottomNavBar);
        price.setText(item2.price);
        System.out.println(item2.price + " HEREEEE");

    }

    public void showItemCount(){

        TextView itemCount = findViewById(R.id.itemCountBottomNavBar);
        itemCount.setText(Integer.toString(selectedItemsList.size()));

    }
}
