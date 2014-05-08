package datamodels;


public class ShoppingItemModel  {
	private String itemName;
	private int quantity;
	public ShoppingItemModel(String name, int amountofitems){
		this.quantity = amountofitems;
		this.itemName = name;
	}
	
	public ShoppingItemModel(int quantity, String itemName) {
	
			this.quantity = quantity;
			this.itemName = itemName;
		}
		
		public int getQuantity() {
			return quantity;
		}
		
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
		public String getItemName() {
			return itemName;
		}
		
		public void setItemName(String itemName) {
			this.itemName = itemName;
		}
}
