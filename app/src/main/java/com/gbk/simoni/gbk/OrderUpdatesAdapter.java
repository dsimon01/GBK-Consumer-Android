package com.gbk.simoni.gbk;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import java.util.Locale;


public class OrderUpdatesAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Items> items;

    public OrderUpdatesAdapter(List<Items> items) {

        this.items = items;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.basket_recycler, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder ViewHolder, int i) {

        final Items basketItems = items.get(i);
        ViewHolder.basketName.setText(basketItems.itemName);
        ViewHolder.basketDesc.setText(basketItems.itemDescription);
        ViewHolder.basketPrice.setText(String.format(Locale.ENGLISH, "£%.2f", basketItems.price));
        ViewHolder.basketImg.setImageResource(basketItems.itemImage);
    }

    @Override
    public int getItemCount() {

        return items.size();

    }
}
