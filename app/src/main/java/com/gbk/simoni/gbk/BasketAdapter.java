package com.gbk.simoni.gbk;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Locale;

// Basket Adapter class for an Array list of Items displayed in a recycler view.
public class BasketAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Items> items;

    public BasketAdapter(List<Items> items) {
        this.items = items;
    }

    // Creates a view holder with the basket recycler layout from the resources folder.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.basket_recycler,
                viewGroup, false);
        return new ViewHolder(view);
    }

    /*
    When the constructor of the class is called an array list of Item type is required
    as parameter, this function assigns the name, description, price and image of an item
    in the recycler view, the function is called until there are no more items in the
    Array list passed as parameter.
    */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder ViewHolder, int i) {

        final Items basketItems = items.get(i);
        ViewHolder.basketName.setText(basketItems.itemName);
        ViewHolder.basketDesc.setText(basketItems.itemDescription);
        ViewHolder.basketPrice.setText(String.format(Locale.ENGLISH,
                "£%.2f", basketItems.price));
        ViewHolder.basketImg.setImageResource(basketItems.itemImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
