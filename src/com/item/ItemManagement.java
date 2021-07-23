package com.item;

import java.util.ArrayList;
import java.util.List;

public class ItemManagement {
    List<Item> itemList=new ArrayList<>();
    public static void main(String[] args) {
        ItemManagement itm=new ItemManagement();
        itm.itemInput();
        itm.itemOutput();
    }

    public void itemInput(){
        Item i1=new Item();
        i1.setItemName("Biscuit");
        i1.setItemPrice(100);
        i1.setItemQuantity(5);
        i1.setItemType(new Raw());

        Item i2=new Item();
        i2.setItemName("Maggi");
        i2.setItemPrice(50);
        i2.setItemQuantity(25);
        i2.setItemType(new Manufactor());

        itemList.add(i1);
        itemList.add(i2);
    }

    public void itemOutput(){
        for(Item i:itemList){
            System.out.println(i);
        }
    }
}
