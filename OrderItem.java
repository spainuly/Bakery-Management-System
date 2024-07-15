package demo;

import demo.Product;

public class OrderItem {
	 private demo.Product product;
	    private int quantity;

	    public OrderItem(demo.Product selectedProduct, int quantity) {
	        this.product = selectedProduct;
	        this.quantity = quantity;
	    }

	    public demo.Product getProduct() {
	        return product;
	    }

	    public int getQuantity() {
	        return quantity;
	    }
}