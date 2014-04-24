/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package commands;

import interfaces.ControllerInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import models.ShoppingListItemModel;
import models.ShoppingListModel;

/**
 *
 * @author Andrew
 */
public class Controller implements ControllerInterface{
    private ArrayList<ShoppingListModel> list = new ArrayList();
    private ShoppingListModel ListModel = new ShoppingListModel("TestModel");
    private HashMap<String, ShoppingListModel> test = new HashMap();
 
    @Override
    public List<ShoppingListModel> getShoppingList(String Name) {
        ArrayList<ShoppingListModel> temp = new ArrayList();
      
            temp.add(test.get(Name));
        
        return temp;
    }

    @Override
    public void addItemToList(String ShoppingListName, ShoppingListItemModel item) {
        test.get(ShoppingListName).addItemToList(item);
    }

    @Override
    public void removeItemFromList(String ShoppingListName, ShoppingListItemModel item) {
        test.get(ShoppingListName).removeItemFromList(item);
    }

    @Override
    public void loadList(String listName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadAllLists() {
       ListModel.addItemToList(new ShoppingListItemModel("a",1));
       ListModel.addItemToList(new ShoppingListItemModel("b",2));
       ListModel.addItemToList(new ShoppingListItemModel("c",3));
       ListModel.addItemToList(new ShoppingListItemModel("d",4));
       ListModel.addItemToList(new ShoppingListItemModel("e",5));
       ListModel.addItemToList(new ShoppingListItemModel("f",6));
       ListModel.addItemToList(new ShoppingListItemModel("g",7));
       ListModel.addItemToList(new ShoppingListItemModel("h",8));
       test.put(ListModel.getShoppingListName(), ListModel);
    }

    @Override
    public void saveAllListToDB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SaveSpecificListToDB(String ShoppingListName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
