package database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Products {
	
	public DbManager		 	dbManager;
	public Statement			myState;
	public ResultSet			myResultSet;

	public String 				designation;
	public String 				description;
	
	public ResultSetMetaData	myResultSetMetaData;
	
	public String 				arrayContent[][];
	public String 				arrayHeader[];
	
	public List<String[]>		list;
	
	public Products(DbManager dbManager) {
		this.dbManager = dbManager;
		myState = this.dbManager.myState;
		updateList();
	}
	
	public void updateList() {
		try {
			
			myResultSet 				= myState.executeQuery("SELECT * FROM products");
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
	
	public Boolean removeItem(int itemId, int quantitie) {
		try {
			
			for (int i = 0; i != list.size(); i++)
			{
				String[] tmp = list.get(i);
				if (Integer.parseInt(tmp[0]) == itemId)
				{
					int originalQuantities = Integer.parseInt(tmp[5]);
					if (originalQuantities - quantitie >= 0)
					{
						myState.executeUpdate("UPDATE products SET quantities='" + (originalQuantities - quantitie) +"' WHERE id=" + tmp[0]);
						updateList();
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
	
	public List<String[]> getProductsList() { return list; }
}
