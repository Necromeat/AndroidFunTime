/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package commands;

import java.util.ArrayList;
import java.util.List;
import models.ShoppingListItemModel;
import models.ShoppingListModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrew
 */
public class ControllerTest {
    
    public ControllerTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getShoppingList method, of class Controller.
     */
    @Test
    public void testGetShoppingList() {
        System.out.println("getShoppingList");
        ArrayList<ShoppingListModel> test = new ArrayList();
        String Name = "TestModel";
        Controller instance = new Controller();
        instance.loadAllLists();
        test = (ArrayList)instance.getShoppingList(Name);
         int expResult = 1;
         int result = test.size();
         System.out.println(test.get(0).getShoppingList().get(0).getItemName());
        assertEquals(expResult, result);
    }

    /**
     * Test of addItemToList method, of class Controller.
     */
    @Test
    public void testAddItemToList() {
        System.out.println("addItemToList");
        String ShoppingListName = "";
        ShoppingListItemModel item = null;
        Controller instance = null;
        instance.addItemToList(ShoppingListName, item);
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeItemFromList method, of class Controller.
     */
    @Test
    public void testRemoveItemFromList() {
        System.out.println("removeItemFromList");
        String ShoppingListName = "";
        ShoppingListItemModel item = null;
        Controller instance = null;
        instance.removeItemFromList(ShoppingListName, item);
        fail("The test case is a prototype.");
    }
    
}
