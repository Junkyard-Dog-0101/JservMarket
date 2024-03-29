package database;

import java.sql.ResultSet;
import java.sql.SQLException;

/* n'oubliez pas de faire appelle � clear() avant chaque cr�ation de requ�te */

public class Orm
{
	private String				mSelect;
	private String				mInsert;
	private String				mValues;
	private String				mColumn;
	private String				mWhere;
	private String				mUpdate;
	private String				mSet;
	private String				mDelete;
	private static DbConnect	db;

	public Orm()
	{
		db = DbConnect.getInstance();
	}

	public Orm insert(String s)
	{
		if (mInsert == null)
			mInsert = "INSERT INTO " + s;
		return (this);
	}
	
	public Orm delete(String s)
	{
		if (mDelete == null)
			mDelete = "DELETE FROM " + s;
		return (this);
	}
	
	public Orm update(String s)
	{
		if (mUpdate == null)
			mUpdate = "UPDATE " + s;
		return (this);
	}
	
	public Orm set(String s)
	{
		if (mSet == null)
			mSet = " SET " + s;
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
	
	public Orm valuesStringMD5(String s)
	{
		if (mValues == null)
			mValues = " VALUES( MD5('" + s + "')";
		else
			mValues += ", MD5('" + s + "')";	
		return (this);
	}
	
	public Orm valuesInt(String s)
	{
		if (mValues == null)
			mValues = " VALUES(" + s;
		else
			mValues += ", " + s;	
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
	
	public Orm andMD5(String field, String op, String value) throws MyOrmException
	{
		if (mWhere == null)
		{
			throw new MyOrmException("And : no Where");
		}
		else
		{
			mWhere += " AND " + field + op + " MD5('" + value + "')";
		}
		return this;
	}

	public ResultSet query()
	{
		System.out.println(mSelect + mWhere);
		try
		{
			if (mWhere != null)
				return (db.executeQuery(mSelect + mWhere));
			else
				return (db.executeQuery(mSelect));
		}
		catch (SQLException e)
		{
			
		}
		return (null);
	}

	public int exeUpdate()
	{
		try
		{
			System.out.println(mUpdate + mSet + mWhere);
			System.out.println(mInsert + mColumn + mValues);
			if (mInsert != null && mValues != null)
			{
				if (mColumn != null)
					return (db.executeUpdate(mInsert + mColumn + mValues));
				else
					return (db.executeUpdate(mInsert + mValues));
			}
			else if (mDelete != null)
			{
				if (mWhere == null)
					return (db.executeUpdate(mDelete));
				else
					return (db.executeUpdate(mDelete + mWhere));
			}
			else
			{
				if (mWhere == null)
					return (db.executeUpdate(mUpdate + mSet));
				else
					return (db.executeUpdate(mUpdate + mSet + mWhere));
			}
		}
		catch (SQLException e)
		{
			
		}
		return (0);
	}

	public void clear()
	{
		mSelect = null;
		mInsert = null;
		mValues = null;
		mColumn = null;
		mWhere = null;
		mUpdate = null;
		mSet = null;
		mDelete = null;
	}
}
