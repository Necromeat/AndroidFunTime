package DBFunctions;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import Interfaces.ControllerInterface;
import Models.MsgRecipient;
import Models.ShoppingList;

public class Controller<E> implements ControllerInterface<E>{
	DummyData dd = new DummyData();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createDB(Context context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getShoppingList() {
		// TODO Auto-generated method stub
		return dd.getShoppingList();
	}

	@Override
	public void storeList(List list) {
		dd.storList(list);
	}

	@Override
	public MsgRecipient getRecipient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeBarcodes(E[] barcodes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storePriceOfItemByID(E e, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeMsgRecipients(E[] person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeListAsTextFile(E[] list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getListFromFileToMakeDB() {
		// TODO Auto-generated method stub
		return null;
	}

		
}
