package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbManager {
	
	public Connection 			myConnect;
	public Statement			myState;
	public ResultSet			myResultSet;
	
	public ResultSetMetaData	myResultSetMetaData;
	
	public String 				arrayContent[][];
	public String 				arrayHeader[];
	
	public List<String[]>		productList;
	public List<String[]>		cartList;
	public List<String[]>		usersList;
	
	public DbManager()		{}
	
	public void connectDb() {
		 try {
		        Class.forName("com.mysql.jdbc.Driver");
		        System.out.println("Driver O.K.");

		        String url = "jdbc:mysql://localhost/jservmarket";
		        String user = "root";
		        String passwd = "";

		        Connection myConnect = DriverManager.getConnection(url, user, passwd);
		        System.out.println("Connexion effective !");
		        System.out.println("--------------------------------------");
				
				myState = myConnect.createStatement();
				
				
		      } catch (Exception e) {
		        e.printStackTrace();
		      }          
	}
	
	public void updateList(String cmd, List<String[]> list) {
		try {
		
			myResultSet = myState.executeQuery("SELECT * FROM " + cmd);
			myResultSetMetaData = myResultSet.getMetaData();
			
			int nbrColumn				= myResultSetMetaData.getColumnCount();
			arrayHeader					= new String[nbrColumn];
			list						= new ArrayList<String[]>();
			
			while (myResultSet.next())
			{
				String[] content 		= new String[nbrColumn];
				for (int i = 0; i != nbrColumn; i++)
				{
					content[i] = myResultSet.getString(i + 1);
				}
				list.add(content);
			}
			showList(list);
			
		} catch (Exception e) {
	        e.printStackTrace();
	      }	
	}
	
	public void showList(List<String[]> list) {
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
	
	public List<String[]> getProductsList() { return productList; }
	public List<String[]> getCartList() 	{ return cartList; }
	public List<String[]> getUsersList() 	{ return usersList; }
	
}
