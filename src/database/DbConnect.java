package database;

import database.Cart;
import database.DbManager;
import database.Products;
import database.Users;

public class DbConnect
{
	public DbManager dbManager;
	public Products products;
	public Users users;
	public Cart cart;
	
	public DbConnect() {
		callDbManager();
	}
	
	public void callDbManager() {
		dbManager = new DbManager();
		dbManager.connectDb();
		products = new Products(dbManager);
		products.showList();
		users = new Users(dbManager);
		users.showList();
		cart = new Cart(dbManager);
		cart.showList();
	}
	
	public void addToCart(int idItem, int quantities) {
		
		if (products.removeItem(idItem, quantities) == false)
		{
			System.out.println("Item not found");
			return;
		}
		
	}
	
	public void pay() {
		cart.deleteAllItem();
	}
}
