package com.gbk.simoni.gbk;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
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

}