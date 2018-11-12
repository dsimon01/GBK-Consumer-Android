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


public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Items> items;

    public RecyclerAdapter(List<Items> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_recycler, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        final Items sampleItem = items.get(i);
        viewHolder.name.setText(sampleItem.itemName);
        viewHolder.price.setText(Double.toString(sampleItem.price));
        viewHolder.desc.setText(sampleItem.itemDescription);
        viewHolder.image.setImageResource(sampleItem.itemImage);

         //System.out.println(sampleItem);   = > I/System.out: com.gbk.simoni.gbk.Items@ee5429c
        // System.out.println(sampleItem.itemName);  = > Gourmet Very Spicy
        // System.out.println(sampleItem.itemDescription); Description1
        // System.out.println(sampleItem.itemImage); => 2131165283

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ItemClicked", "Item: " + i);
                Intent intent = new Intent(v.getContext(), ItemSelectionActivity.class);
                intent.putExtra("item_name", sampleItem.itemName);
                intent.putExtra("item_price", sampleItem.price);
                intent.putExtra("item_description", sampleItem.itemDescription);
                intent.putExtra("item_image", sampleItem.itemImage);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
