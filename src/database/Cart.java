package database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Cart {
	
	public DbManager		 	dbManager;
	public Statement			myState;
	public ResultSet			myResultSet;
	
	public ResultSetMetaData	myResultSetMetaData;
	
	public String 				arrayContent[][];
	public String 				arrayHeader[];
	
	public List<String[]>		list;
	
	public Cart(DbManager dbmanager) {
		this.dbManager = dbmanager;
		myState = this.dbManager.myState;
		updateList();
	}
	
	public void updateList() {
		try {
			
			myResultSet 				= myState.executeQuery("SELECT * FROM cart");
			myResultSetMetaData			= myResultSet.getMetaData();
			
			int nbrColumn				= myResultSetMetaData.getColumnCount();
			list						= new ArrayList<String[]>();
			arrayHeader					= new String[nbrColumn];
			
			while (myResultSet.next())
			{
				String[] content 		= new String[nbrColumn];
				for (int i = 0; i != nbrColumn; i++)
				{
					content[i] = myResultSet.getString(i + 1);
				}
				list.add(content);		
			}
			
		} catch (Exception e) {
	        e.printStackTrace();
	      }	
	}
	
	public void showList() {
		for (int i = 0; i != list.size(); i++)
		{
			String[] tmp = list.get(i);
			for (int j = 0; j != tmp.length; j++)
			{
				System.out.print(tmp[j] + " | ");
			}
			System.out.println("");
		}	
		System.out.println("--------------------------------------");
	}
	
	public void addItem() {
		
	}
	
	public void deleteAllItem() {
		try {
			myState.executeUpdate("DELETE FROM cart");
			updateList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String[]> getCartList() { return list; }

}
