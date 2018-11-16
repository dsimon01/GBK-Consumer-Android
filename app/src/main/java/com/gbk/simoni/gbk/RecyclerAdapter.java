package com.gbk.simoni.gbk;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


 // This class is called with an Items List parameter from the Menu Activity > setRecyclerView().

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Items> items;

    // Constructor
    public RecyclerAdapter(List<Items> items) {
        this.items = items;
    }

    @NonNull
    @Override
    // Uses the custom made "Card View" Layout.
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_recycler, viewGroup, false);
        return new ViewHolder(view);
    }

    // Creates a new Item, from the Items passed as parameters to the constructor.
    // Sets its attributes by finding the Resource ID in the ViewHolder Class.
    // Sets on Click listener to the view which redirects to the Item Selection Activity.
    // The data from the selected item is passed to the Selection activity with an intent.

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        final Items menuItem = items.get(i);
        viewHolder.name.setText(menuItem.itemName);
        viewHolder.price.setText(String.format(Locale.ENGLISH, "£%.2f", menuItem.price));
        viewHolder.desc.setText(menuItem.itemDescription);
        viewHolder.image.setImageResource(menuItem.itemImage);

         //System.out.println(menuItem);   = >  com.gbk.simoni.gbk.Items@ee5429c
        // System.out.println(menuItem.itemName);  = > Gourmet Very Spicy
        // System.out.println(menuItem.itemDescriptionList); Description1
        // System.out.println(menuItem.itemImageList); => 2131165283

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ItemClicked", "Item: " + i);
                Intent intent = new Intent(v.getContext(), ItemSelectionActivity.class);
                intent.putExtra("item_name", menuItem.itemName);
                intent.putExtra("item_price", menuItem.price);
                intent.putExtra("item_description", menuItem.itemDescription);
                intent.putExtra("item_image", menuItem.itemImage);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
