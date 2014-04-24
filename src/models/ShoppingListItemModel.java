/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import interfaces.ShoppingListItem;

/**
 *
 * @author Andrew
 */
public class ShoppingListItemModel implements ShoppingListItem {

    private String itemName;
    private int amount;

    public ShoppingListItemModel(String itemName, int amount) {
        this.itemName = itemName;
        this.amount = amount;
    }
    
    
    
    
    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public String getAmountOfItem() {
        return ""+amount;
    }
    
}
