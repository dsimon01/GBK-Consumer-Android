package com.gbk.simoni.gbk;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {

    // In order to use a recycler view a ViewHolder class has to be implemented.
    // In this project there are 3 recycler views so I am using the same view holder for both cases.

    // MENU ACTIVITY:
    ImageView image;
    TextView name;
    TextView price;
    TextView desc;

    // BASKET ACTIVITY -- > Reused in OrderUpdatesActivity
    ImageView basketImg;
    TextView basketName;
    TextView basketPrice;
    TextView basketDesc;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        //MENU ACTIVITY:
        image = itemView.findViewById(R.id.image_view_recycler);
        name = itemView.findViewById(R.id.name_textView);
        price = itemView.findViewById(R.id.price_textV);
        desc = itemView.findViewById(R.id.desc_textV);
        //BASKET:
        basketImg = itemView.findViewById(R.id.basket_image_view_recycler);
        basketName = itemView.findViewById(R.id.itemNameBasketView);
        basketPrice = itemView.findViewById(R.id.itemPriceBasket);
        basketDesc = itemView.findViewById(R.id.itemDescriptionBasketView);
    }
}