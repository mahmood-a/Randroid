package com.applemma.randroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper
{
	private static final String DATABASE_NAME = "randroid.db";
	private static final int SCHEMA = 1;

	// Database tables and their columns

	static final String TABLE_MACHINES = "machines";
	static final String MACHINES_TITLE = "title";
	static final String MACHINES_DESCRIPTION = "description";
	static final String MACHINES_CREATION_TIME = "creation_time";

	static final String TABLE_TICKETS = "tickets";
	static final String TICKETS_TITLE = "title";
	static final String TICKETS_CREATION_TIME = "creation_time";
	static final String TICKETS_MACHINE_ID = "machine_id";

	static final String TABLE_DRAWS = "draw";
	static final String DRAWS_TIMESTAMP = "timestamp";
	static final String DRAWS_RESULT = "result";
	static final String DRAWS_MACHINE_ID = "machine_id";

	public DatabaseHelper(Context context)
	{
		super(context, DATABASE_NAME, null, SCHEMA);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		try
		{
			db.beginTransaction();
			
			// Create (lottery) machines table
			String createMachinesTableSql =
					"CREATE TABLE " + TABLE_MACHINES + " (" +
					"_id INTEGER PRIMARY KEY AUTOINCREMENT," +
					MACHINES_TITLE + " TEXT," +
					MACHINES_DESCRIPTION + " TEXT," +
					MACHINES_CREATION_TIME + " TEXT);";
			db.execSQL(createMachinesTableSql);
			
			// Create tickets table
			String createTicketsTableSql =
					"CREATE TABLE " + TABLE_TICKETS + " (" +
					"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
					TICKETS_MACHINE_ID + " INTEGER, " +
					TICKETS_TITLE + " TEXT," +
					TICKETS_CREATION_TIME + " TEXT, " +
					"FOREIGN KEY" + "(" + TICKETS_MACHINE_ID + ") " + 
					"REFERENCES " + TABLE_MACHINES + "(" + "_id" + ")" + ");";
			db.execSQL(createTicketsTableSql);
			
			// Create draws table
			String createDrawsTableSql =
					"CREATE TABLE " + TABLE_DRAWS + " (" +
					"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
					DRAWS_MACHINE_ID + " INTEGER, " +		
					DRAWS_TIMESTAMP + " TEXT, " +
					DRAWS_RESULT + " TEXT, " +
					"FOREIGN KEY" + "(" + DRAWS_MACHINE_ID + ") " + 
					"REFERENCES " + TABLE_MACHINES + "(" + "_id" + ")" + ");";
			db.execSQL(createDrawsTableSql);
			
			supplyDummyData(db);
			
			db.setTransactionSuccessful();
		}
		finally
		{
			db.endTransaction();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
	{
		// TODO Auto-generated method stub

	}
	
	private void supplyDummyData(SQLiteDatabase db)
	{
		ContentValues cv = new ContentValues();
		
		cv.put(MACHINES_TITLE, "Restaurants");
		cv.put(MACHINES_DESCRIPTION, "Choose a random restaurant for me!");
		Date now = new Date();
		cv.put(MACHINES_CREATION_TIME, now.toString());
		db.insert(TABLE_MACHINES, MACHINES_TITLE, cv);
		
		cv.put(MACHINES_TITLE, "Movies");
		cv.put(MACHINES_DESCRIPTION, "Choose a movie from my list of movies awaited to be watched!");
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DATE, -1);
		Date yesterday = cal.getTime();
		cv.put(MACHINES_CREATION_TIME, yesterday.toString());
		db.insert(TABLE_MACHINES, MACHINES_TITLE, cv);
		
		cv.put(MACHINES_TITLE, "Winner");
		cv.put(MACHINES_DESCRIPTION, "Choose the winner of the lottery!");
		cal.setTime(now);
		cal.add(Calendar.DATE, -1);
		Date otherDay = cal.getTime();
		cv.put(MACHINES_CREATION_TIME, otherDay.toString());
		db.insert(TABLE_MACHINES, MACHINES_TITLE, cv);
	}

}
