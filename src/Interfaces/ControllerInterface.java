package Interfaces;

import java.util.List;

import Models.MsgRecipient;
import Models.ShoppingList;
import android.content.Context;

public interface ControllerInterface<E> extends Runnable{

	public void createDB(Context context);
	public List<ShoppingList> getShoppingList();
	public void storeList(List list);
	public MsgRecipient getRecipient();
	public void storeBarcodes(E[] barcodes);
	public void storePriceOfItemByID(E e, int id);
	public void storeMsgRecipients(E[] person);
	public void storeListAsTextFile(E[] list);
	public List<E> getListFromFileToMakeDB();
	
	
}
