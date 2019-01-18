package com.gbk.simoni.gbk;


// Object Items and its attributes.

public class Items {

    String itemName;
    String itemDescription;
    Double price;
    int itemImage;

    public Items(String itemName, String desc, double price, int itemImage){

        this.itemName = itemName;
        this.itemDescription = desc;
        this.price = price;
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public Double getPrice() {
        return price;
    }

    public int getItemImage() {
        return itemImage;
    }

}
