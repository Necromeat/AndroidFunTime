/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import interfaces.ShoppingList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrew
 */
public class ShoppingListModel implements ShoppingList{
    ArrayList<ShoppingListItemModel> items = new ArrayList();
    private String listName;
    public ShoppingListModel(String shoppingListName) {
        this.listName = shoppingListName;
    }
    
    @Override
    public void addItemToList(ShoppingListItemModel item) {
        if(!item.getItemName().isEmpty() && !item.getAmountOfItem().isEmpty()){
            items.add(item);
        }
    
    }

    @Override
    public List<ShoppingListItemModel> getShoppingList() {
        return items;
    }

    @Override
    public void removeItemFromList(ShoppingListItemModel item) {
        items.remove(item);
    }

    @Override
    public String getShoppingListName() {
        return listName;
    }
    
}
