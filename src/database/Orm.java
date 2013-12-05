package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Orm
{
	private String		mSelect;
	private String		mInsert;
	private String		mValues;
	private String		mColumn;
	private String		mWhere;
	private DbManager	db;

	public Orm(DbManager newDb)
	{
		db = newDb;
		try
		{
			db.connectDb();
		}
		catch (Exception e)
		{
			System.err.println("unable to connect db");
		}
	}

	public Orm insert(String s)
	{
		if (mInsert == null)
			mInsert = "INSERT INTO " + s;
		return (this);
	}
	
	public Orm column(String s)
	{
		if (mColumn == null)
			mColumn = "(" + s;
		else
			mColumn += ", " + s;
		return (this);
	}
	
	public Orm columnBack()
	{
		if (mColumn != null)
			mColumn += ")"; 
		return (this);
	}

	public Orm valuesNumber(String s)
	{
		if (mValues == null)
			mValues = " VALUES (" + s;
		else
			mValues += ", " + s;	
		return (this);
	}
	
	public Orm valuesString(String s)
	{
		if (mValues == null)
			mValues = " VALUES('" + s + "'";
		else
			mValues += ", '" + s + "'";	
		return (this);
	}
	
	public Orm valuesBack()
	{
		if (mValues != null)
			mValues += ")"; 
		return (this);
	}
	
	public Orm select(String s)
	{
		if (mSelect == null)
			mSelect = "SELECT " + s;
		else
			mSelect += ", " + s;
		return (this);
	}

	public Orm from(String s) throws MyOrmException
	{
		if (mSelect == null)
		{
			throw new MyOrmException("from : no SELECT");
		}
		else
		{
			mSelect += " FROM " + s;
		}
		return this;
	}

	public Orm where(String field, String op, String value) throws MyOrmException
	{
		if (mWhere == null)
		{
			mWhere = " WHERE " + field + op + "'" + value + "'";
		}
		else
		{
			throw new MyOrmException("Where is all ready set");
		}
		return this;
	}

	public Orm and(String field, String op, String value) throws MyOrmException
	{
		if (mWhere == null)
		{
			throw new MyOrmException("And : no Where");
		}
		else
		{
			mWhere += " AND " + field + op + "'" + value + "'";
		}
		return this;
	}

	public ResultSet query()
	{
		try
		{
			return (db.executeQuery(mSelect + mWhere));
		}
		catch (SQLException e)
		{
			
		}
		return (null);
	}

	public int update()
	{
		try
		{
			if (mColumn != null)
				return (db.executeUpdate(mInsert + mColumn + mValues));
			else
				return (db.executeUpdate(mInsert + mValues));
		}
		catch (SQLException e)
		{
			
		}
		return (0);
	}
}