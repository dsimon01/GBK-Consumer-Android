package com.gbk.simoni.gbk;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import java.util.Locale;

// Basket Adapter class for an Array list of Items displayed in a recycler view.
public class BasketAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Items> items;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder ViewHolder, int i) {

        // When the constructor of the class is called an array list of Item type is required
        // as parameter, this function assigns the name, description, price and image of an item
        // in the recycler view, the function is called until there are no more items in the
        // Array list passed as parameter.
        final Items basketItems = items.get(i);
        ViewHolder.basketName.setText(basketItems.itemName);
        ViewHolder.basketDesc.setText(basketItems.itemDescription);
        ViewHolder.basketPrice.setText(String.format(Locale.ENGLISH,
                "£%.2f", basketItems.price));
        ViewHolder.basketImg.setImageResource(basketItems.itemImage);

        // On item click listener for an item clicked within the recycler view
        // Generates a dialog to delete the item clicked on
        // Removes the item from the arrayList and updates the total price displayed
        // if the user decided to do so otherwise cancels the dialog.
        ViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Remove this item from your basket?");
                builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        System.out.println("clicked on remove! " + which);
                        MenuActivity.totalPrice = MenuActivity.totalPrice -
                                MenuActivity.selectedItemsList.get(which + 1).price;
                        MenuActivity.selectedItemsList.remove(which + 1);
                        Intent intent = new Intent(v.getContext(), MenuActivity.class);
                        v.getContext().startActivity(intent);
                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        System.out.println("clicked on cancel");

                    }
                });

                alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {

        return items.size();

    }
}
