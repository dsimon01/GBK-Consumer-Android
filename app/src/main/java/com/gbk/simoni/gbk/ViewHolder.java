package com.gbk.simoni.gbk;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {


    ImageView image;
    TextView name;
    TextView desc;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image_view_recycler);
        name = itemView.findViewById(R.id.name_textView);
        desc = itemView.findViewById(R.id.desc_textV);

    }




}
