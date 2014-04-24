package datamodels;

public class ShoppingItemModel {
	private int quantity;
	private String itemName;
	
		public ShoppingItemModel(int quantity, String itemName) {
			super();
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
