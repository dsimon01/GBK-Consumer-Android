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
import java.util.Locale;

public class MenuActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private List<Items> menuItem = new ArrayList<>(); // List of Items used to populate the recycler
    static double totalPrice = 0.00;
    static List<Items> selectedItemsList = new ArrayList<>(); // list stays updated until cleared.

    // MENU ITEM DETAILS IN ARRAYS :

    private String[] itemName = {

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

    private Double[] price = {

            10.95,
            10.65,
            11.95,
            9.95,
            11.20,
            10.45,
            9.60,
            9.85,
            11.00,
            13.00,
    };

    private String[] itemDesc = {

            "Crispy bacon, BBQ sauce, house mayo, dill pickle, salad.",
            "House mayo, mature Cheddar, relish, salad, chilli fried egg.",
            "Two 6oz patties, mature Cheddar, crispy bacon, garlic mayo, relish, dill pickle.",
            "American cheese, homemade onion ring, Cajun relish, chipotle mayo.",
            "Onion jam, Cajun relish, house mayo, dill pickle, salad.",
            "6oz beef, mature Cheddar, beetroot, fried egg, grilled pineapple.",
            "Crispy bacon, BBQ sauce, house mayo, dill pickle, salad.",
            "Smashed avocado, crispy bacon, house mayo, relish.",
            "American and mature Cheddar cheese, chilli fried egg, basil mayo, habanero jam.",
            "Crispy bacon, BBQ sauce, house mayo, dill pickle, salad.",
    };

    private int[] image = {

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

        // call to a method tha defines the toolbar & collapsing toolbar of the activity
        setToolbarUI();

        // call to method that creates a menuItem list which is used to populate the recycler view
        // The list contains of Item objects, and their attributes are defined from the specified
        // arrays above -> Line 33 to 88
        createMenuItemsList();

        // call to a method that arranges the UI in the Menu Activity
        // Sets the context of this activity with LinearLayoutManager
        // Uses GridLayoutManger to apply a grid to the view
        // Calls the MenuRecyclerAdapter class with the menu Items as a parameter.
        setRecyclerView();

        // call to a method that takes the data passed from the Item Selection Activity,
        // and with an internal method call to "addItemsToSelectedItemsList" method converts
        //the incoming data into an Item object and adds it an Items list.
        getIntentFromItemSelectionActivity();

        // call to a method that checks if the Items list has at least one item.
        // if it does, then a bottom navigation bar is displayed to the UI which allows the user
        // to enter the Basket Activity if they tap on it.
        // Calls internally another 2 methods that display the total amount and total item count
        // within the navigation bar.
        showBottomNavBar();

    }

    private void setToolbarUI(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout;
        int color = 000;
        setSupportActionBar(toolbar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setExpandedTitleColor(color);
        recyclerView = findViewById(R.id.recycler_view_id);
    }

    private void createMenuItemsList() {
        for (int i = 0; i < itemName.length; i++) {
            Items item = new Items(itemName[i],itemDesc[i],price[i],image[i]);
            menuItem.add(item);
        }
    }

    private void setRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(new MenuRecyclerAdapter(menuItem));
    }

    private void getIntentFromItemSelectionActivity(){

        if (getIntent().hasExtra("item_name")
                && getIntent().hasExtra("item_description")
                && getIntent().hasExtra("item_image")
                && getIntent().hasExtra("item_price")){

            String itemName = getIntent().getStringExtra("item_name");
            String itemDescription = getIntent().getStringExtra("item_description");
            double itemPrice = getIntent().getDoubleExtra("item_price", 0.00);
            int itemImage = getIntent().getIntExtra("item_image", 2131165283);
            int counterValue = getIntent().getIntExtra("counter_value", 1);

            System.out.println("MENU ACTIVITY RECEIVED FROM ITEM SELECTION ACTIVITY: " + itemName
                    + " " + itemDescription + " " + counterValue + " Times");

            // call to method that takes the information from Item Selection Activity in its
            // parameters and creates a new Items object that is added to a new Items list.
            addItemsToSelectedItemsList(counterValue, itemName, itemDescription,
                    itemPrice, itemImage);

            System.out.println(selectedItemsList.size() +
                    " The size of selected items list " + selectedItemsList);
        }

    }

    private void addItemsToSelectedItemsList(int counter, String name, String description,
                                            double price, int image){

        Items item2;

        for (int i = 0; i < counter; i++) {
            item2 = new Items(name,description,price,image);
            totalPrice += item2.price;
            selectedItemsList.add(item2);
        }
    }

    private void showBottomNavBar(){

        if (selectedItemsList.size() > 0) {
            BottomNavigationView bar = findViewById(R.id.bottomNav_id);
            RecyclerView view = findViewById(R.id.recycler_view_id);
            view.getLayoutParams().height = 1150;
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

    private void showPrice(){
        TextView price = findViewById(R.id.priceBottomNavBar);
        price.setText(String.format(Locale.ENGLISH, "£%.2f", totalPrice));
        System.out.println(totalPrice + " THE CURRENT TOTAL PRICE");
    }

    private void showItemCount(){
        TextView itemCount = findViewById(R.id.itemCountBottomNavBar);
        if (selectedItemsList.size() > 1){
            itemCount.setText(String.format("%s Items",Integer.toString(selectedItemsList.size())));
        }
        if (selectedItemsList.size() <= 1){
            itemCount.setText(String.format("%s Item",Integer.toString(selectedItemsList.size())));
        }
    }
}
