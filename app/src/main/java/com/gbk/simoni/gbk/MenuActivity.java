package com.gbk.simoni.gbk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    static final ArrayList<String> selectedItems = new ArrayList<>();

    public void onViewBasketClick(View view) {
        Intent intent = new Intent(getApplicationContext(), BasketActivity.class);
        //intent.putExtra("items Selected", selectedItems);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final ListView menuListView = findViewById(R.id.menuListView);
        final ArrayList<String> items = new ArrayList<>();
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);



        // new code logs items pressed on the menu screen : TODO - add items selected in basket - Make basket visible - {clean basket after 5 minutes} (Animate the button visibility)



        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Button basket = findViewById(R.id.viewBasketButton);
                basket.setVisibility(view.VISIBLE);

                selectedItems.add(items.get(position));
                Log.i("Item Tapped: ", items.get(position));

            }
        });


        // new code ^^^

        ParseQuery<ParseObject> query = ParseQuery.getQuery("MenuItems");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null){
                    if (objects.size() > 0){

                        for (ParseObject object : objects){

                            items.add(object.getString("Category"));
                            items.add(object.getString("ItemName"));
                            items.add(object.getString("ItemDescription"));
                            items.add(object.getString("Price"));


                   //         Log.i("findInBHRND: ", object.getString("Category"));

                        }

                        menuListView.setAdapter(arrayAdapter);
                    }

                }else {
                    e.printStackTrace();
                }

            }
        });

        }

    }
