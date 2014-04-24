/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import java.util.List;
import models.ShoppingListItemModel;
import models.ShoppingListModel;

/**
 *
 * @author Andrew
 */
public interface ControllerInterface {
    public List<ShoppingListModel> getShoppingList(String Name);
    public void addItemToList(String ShoppingListName,ShoppingListItemModel item);
    public void removeItemFromList(String ShoppingListName, ShoppingListItemModel item);
    public void loadList(String listName);
    public void loadAllLists();
    public void saveAllListToDB();
    public void SaveSpecificListToDB(String ShoppingListName);
    
}
