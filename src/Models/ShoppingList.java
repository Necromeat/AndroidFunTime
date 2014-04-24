package Models;

public class ShoppingList {
	private int stock;
	private String itemName;
	public ShoppingList(int stock, String itemName) {
		super();
		this.stock = stock;
		this.itemName = itemName;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	
}
