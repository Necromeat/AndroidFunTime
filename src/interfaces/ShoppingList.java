/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import java.util.List;
import models.ShoppingListItemModel;

/**
 *
 * @author Andrew
 */
public interface ShoppingList {
    public String getShoppingListName();
    public void addItemToList(ShoppingListItemModel item);
    public List<ShoppingListItemModel> getShoppingList();
    public void removeItemFromList(ShoppingListItemModel item);
}
