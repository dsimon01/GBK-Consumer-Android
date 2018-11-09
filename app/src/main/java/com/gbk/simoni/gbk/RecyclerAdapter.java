package com.gbk.simoni.gbk;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Items> items;

    public RecyclerAdapter(List<Items> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_items, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Items sampleItem = items.get(i);
        viewHolder.name.setText(sampleItem.itemName);
        viewHolder.desc.setText(sampleItem.itemDescription);
        viewHolder.image.setImageResource(sampleItem.userImage);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
