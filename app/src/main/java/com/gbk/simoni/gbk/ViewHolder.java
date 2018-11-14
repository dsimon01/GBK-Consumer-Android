package com.gbk.simoni.gbk;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewHolder extends RecyclerView.ViewHolder {


    ImageView image;
    TextView name;
    TextView price;
    TextView desc;

    ImageView basketImg;
    TextView basketName;
    TextView basketPrice;
    TextView basketDesc;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image_view_recycler);
        name = itemView.findViewById(R.id.name_textView);
        price = itemView.findViewById(R.id.price_textV);
        desc = itemView.findViewById(R.id.desc_textV);
        basketImg = itemView.findViewById(R.id.basket_image_view_recycler);
        basketName = itemView.findViewById(R.id.itemNameBasketView);
        basketPrice = itemView.findViewById(R.id.itemPriceBasket);
        basketDesc = itemView.findViewById(R.id.itemDescriptionBasketView);
    }
}