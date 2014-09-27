package com.example.costtracker_version_02;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.AbsoluteLayout;

public class EntryDataDAO {
	
	private DBAdapter dba;
	private SQLiteDatabase db;
	
	public EntryDataDAO(Context ctx){
		this.dba = new DBAdapter(ctx);
		this.db= dba.open();
	}  

	public boolean insertEntryData(EntryData data, String tableName){
		boolean insertSuccess = true;
		
		if(!checkTableExist(tableName)){
			createTable(tableName);
		}
		ContentValues insertValue = new ContentValues();
		insertValue.put("user_id", data.getUserId());
		insertValue.put("item", data.getItem());
		insertValue.put("amount", data.getAmount());
		insertValue.put("date", data.getDate());
		db.insert(tableName, null, insertValue);
		
		return insertSuccess;
	}
	
	public  EntryDataBulk readAllEntryData(String userName, String tableName){
		EntryData dataRead = new EntryData();
		dataRead.setUserId(userName);
		//dataRead.setDataTableName(tableName);
		//dataRead.setDate("20Aug");
		//dataRead.setItem("Shirt");
		//dataRead.setAmount("15");
		//EntryData dataRead2 = new EntryData();
		//dataRead2.setUserId(userName);
		//dataRead2.setDataTableName(tableName);
		//dataRead2.setDate("15Aug");
		//dataRead2.setItem("Pant");
		//dataRead2.setAmount("25");
		List<EntryData> entryDataColl=new ArrayList<EntryData>();
		EntryDataBulk readAllData = null;
		
		//if(!checkTableExist(tableName)){
			createTable(tableName);
		//}
		String DATABASE_TABLE = tableName;
		String selectionArg1 = "'"+userName+"'";
		String selectionArg2 = "'"+""+"'";
		String[] selectionArgs = {selectionArg1};
		//Cursor costCursor = db.query(DATABASE_TABLE, null, "user_id=? AND password=?", selectionArgs, null,null,null);
		//Cursor costCursor = db.query(DATABASE_TABLE, null, "user_id = tapas", selectionArgs, null,null,null);
		String sql1 = "SELECT * FROM " +tableName+ " WHERE user_id = "+ selectionArg1; 
		Cursor costCursor = db.rawQuery(sql1, null);
		if(costCursor!=null){
			costCursor.moveToFirst();
			for(int i=0; i<costCursor.getCount();i++){
				EntryData entryData = new EntryData();
				int userIdIdx = costCursor.getColumnIndex("user_id");
				int dateIdx = costCursor.getColumnIndex("date");
				int itemIdx = costCursor.getColumnIndex("item");
				int amountIdx = costCursor.getColumnIndex("amount");
				entryData.setUserId(costCursor.getString(userIdIdx));
				entryData.setDate(costCursor.getString(dateIdx));
				entryData.setItem(costCursor.getString(itemIdx));
				entryData.setAmount(costCursor.getString(amountIdx));
				costCursor.moveToNext();
				entryDataColl.add(entryData);
				entryData = null;
			}
			readAllData = new EntryDataBulk(entryDataColl);
		}
		
		return readAllData;
	}
	
	public boolean updateEntryData(EntryData data, String tableName){
		boolean updateSuccess = false;
		
		return updateSuccess;
	}
	
	public boolean deleteEntryData(EntryData data, String tableName){
		boolean deleteSuccess = false;
		
		return deleteSuccess;
	}
	
	private boolean checkTableExist(String tableName){
		
		boolean retCd = false;
		
		String sql1 = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+tableName+"'"; 
		Cursor checkTable = db.rawQuery(sql1, null);
		int rowCount = 0;
		if(checkTable!= null){
			checkTable.moveToFirst();
			rowCount = checkTable.getCount();
			if(rowCount>0){
				retCd = true;
			}
		}
		return retCd;
	}
	
	private void createTable(String tableName){
		
		String DATABASE_new_CREATE = "create table " + tableName + "(id integer primary key autoincrement,"
				+"user_id text not null, date text not null, item text not null, amount text not null);";
		try{
			db.execSQL(DATABASE_new_CREATE);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
