package com.gbk.simoni.gbk;


import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
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

public class MenuActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;

    static List<Items> sampleItem2 = new ArrayList<>();
    ArrayList<String> itemNameBasket = new ArrayList<>();
    ArrayList<String> itemDescBasket = new ArrayList<>();


    String[] itemName = {

            "Gourmet Spicy",
            "Gourmet Blue",
            "Gourmet Very Spicy",
            "Gourmet Green",
            "Gourmet Spicy",
            "Gourmet Blue",
            "Gourmet Very Spicy",
            "Gourmet Green",
            "Gourmet Spicy",
            "Gourmet Blue",
            "Gourmet Very Spicy",
            "Gourmet Green",
    };

    String[] itemDesc = {

            "Description1 kshfkdhslsdlgkj sdgklsd gjl ksgjslk dgjs dljg sd",
            "Description2 kshfkdh sl sdlgk jsdgklsd gjlksgjslkdg sdljgsd",
            "Description3 kshfkdhsl sdl gkjsd gkl sdgjlk sgjslkdg j dljgsd",
            "Descriptio4 kshf kdhsls dlgkjsd gk lsdg jlksgjslkd gjsdl jgsd",
            "Description1 kshfkdhslsdlgkj sdgklsd gjl ksgjslk dgjs dljg sd",
            "Description2 kshfkdh sl sdlgk jsdgklsd gjlksgjslkdg sdljgsd",
            "Description3 kshfkdhsl sdl gkjsd gkl sdgjlk sgjslkdg j dljgsd",
            "Descriptio4 kshf kdhsls dlgkjsd gk lsdg jlksgjslkd gjsdl jgsd",
            "Description1 kshfkdhslsdlgkj sdgklsd gjl ksgjslk dgjs dljg sd",
            "Description2 kshfkdh sl sdlgk jsdgklsd gjlksgjslkdg sdljgsd",
            "Description3 kshfkdhsl sdl gkjsd gkl sdgjlk sgjslkdg j dljgsd",
            "Descriptio4 kshf kdhsls dlgkjsd gk lsdg jlksgjslkd gjsdl jgsd",

    };

    public int[] image = {

            R.drawable.gbkhome,
            R.drawable.login,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
            R.drawable.gbkhome,
            R.drawable.login,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
            R.drawable.gbkhome,
            R.drawable.login,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getIntentFromBasket();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view_id);
        List<Items> sampleItem = new ArrayList<>();

        for (int i = 0; i < itemName.length; i++) {

            Items item = new Items();
            item.itemName = itemName[i];
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