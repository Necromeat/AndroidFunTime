/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DummyData;

import interfaces.ShoppingListItem;
import java.util.ArrayList;
import java.util.List;
import models.ShoppingListItemModel;
import models.ShoppingListModel;

/**
 *
 * @author Andrew
 */
public class BuildList {
    static ArrayList<ShoppingListItemModel> list = new ArrayList();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        list.add(new ShoppingListItemModel("a",1));
        list.add(new ShoppingListItemModel("b",2));
        list.add(new ShoppingListItemModel("c",3));
        list.add(new ShoppingListItemModel("d",4));
        list.add(new ShoppingListItemModel("e",5));
        list.add(new ShoppingListItemModel("f",6));
        list.add(new ShoppingListItemModel("g",7));
        list.add(new ShoppingListItemModel("h",8));
    }
    
    
    public static List<ShoppingListItemModel> getList(){
        return list;
    }
    
    
}
