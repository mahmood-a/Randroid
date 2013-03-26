/*
 * Copyright (C) 2013 Mahmood Abdulla
 * 
 * This file is part of Randroid.
 * 
 * Randroid is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * Randroid is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * Randroid. If not, see <http://www.gnu.org/licenses/>.
 */

package com.applemma.randroid.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.applemma.randroid.BuildConfig;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper
{

	// Database schema
	private static final String DATABASE_NAME = "randroid.db";
	private static final int SCHEMA = 1;

	// Database tables and their columns
	public static final String TABLE_LOTTERIES = "lotteries";
	public static final String LOTTERIES_TITLE = "title";
	public static final String LOTTERIES_DESCRIPTION = "description";
	public static final String LOTTERIES_CREATION_TIME = "creation_time";
	public static final int LOTTERY_ID_INDEX = 0;
	public static final int LOTTERY_TITLE_INDEX = 1;
	public static final int LOTTERY_DESCRIPTION_INDEX = 2;
	public static final int LOTTERY_CREATION_INDEX = 4;

	public static final String TABLE_TICKETS = "tickets";
	public static final String TICKETS_TITLE = "title";
	public static final String TICKETS_CREATION_TIME = "creation_time";
	public static final String TICKETS_LOTTERY_ID = "lottery_id";
	public static final int TICKET_ID_INDEX = 0;
	public static final int TICKET_LOTTERY_ID_INDEX = 1;
	public static final int TICKET_TITLE_INDEX = 2;
	public static final int TICKET_CREATION_INDEX = 3;

	public static final String TABLE_DRAWS = "draw";
	public static final String DRAWS_TIMESTAMP = "timestamp";
	public static final String DRAWS_RESULT = "result";
	public static final String DRAWS_LOTTERY_ID = "lottery_id";

	// Singleton members
	private static DatabaseHelper mSingleton = null;
	private Context mCtxt = null;

	public synchronized static DatabaseHelper getInstance(Context ctxt)
	{
		if (mSingleton == null)
		{
			mSingleton = new DatabaseHelper(ctxt.getApplicationContext());
		}

		return mSingleton;
	}

	private DatabaseHelper(Context ctxt)
	{
		super(ctxt, DATABASE_NAME, null, SCHEMA);
		mCtxt = ctxt;
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		try
		{
			db.beginTransaction();

			// Create lotteries table
			String createLotteriesTableSql = "CREATE TABLE " + TABLE_LOTTERIES
					+ " (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ LOTTERIES_TITLE + " TEXT," + LOTTERIES_DESCRIPTION
					+ " TEXT," + LOTTERIES_CREATION_TIME + " TEXT);";
			db.execSQL(createLotteriesTableSql);

			// Create tickets table
			String createTicketsTableSql = "CREATE TABLE " + TABLE_TICKETS
					+ " (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ TICKETS_LOTTERY_ID + " INTEGER, " + TICKETS_TITLE
					+ " TEXT," + TICKETS_CREATION_TIME + " TEXT, "
					+ "FOREIGN KEY" + "(" + TICKETS_LOTTERY_ID + ") "
					+ "REFERENCES " + TABLE_LOTTERIES + "(" + "_id" + ")"
					+ ");";
			db.execSQL(createTicketsTableSql);

			// Create draws table
			String createDrawsTableSql = "CREATE TABLE " + TABLE_DRAWS + " ("
					+ "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ DRAWS_LOTTERY_ID + " INTEGER, " + DRAWS_TIMESTAMP
					+ " TEXT, " + DRAWS_RESULT + " TEXT, " + "FOREIGN KEY"
					+ "(" + DRAWS_LOTTERY_ID + ") " + "REFERENCES "
					+ TABLE_LOTTERIES + "(" + "_id" + ")" + ");";
			db.execSQL(createDrawsTableSql);

			if (BuildConfig.DEBUG)
			{
				supplyDummyData(db);
			}

			db.setTransactionSuccessful();
		}
		finally
		{
			db.endTransaction();
		}

	}

	public long insertLottery(String title, String description)
	{
		ContentValues cv = new ContentValues();

		cv.put(DatabaseHelper.LOTTERIES_TITLE, title);
		cv.put(DatabaseHelper.LOTTERIES_DESCRIPTION, description);
		cv.put(DatabaseHelper.LOTTERIES_CREATION_TIME, getTimestamp());

		SQLiteDatabase sqlDb = getWritableDatabase();
		long rowId = -1; // the value -1 means no row was added

		try
		{
			sqlDb.beginTransaction();

			rowId = sqlDb.insert(TABLE_LOTTERIES, LOTTERIES_TITLE, cv);

			sqlDb.setTransactionSuccessful();
		}
		catch (Exception e)
		{
			Log.e(getClass().getSimpleName(),
					"Exception inserting a new lottery.", e);
		}
		finally
		{
			sqlDb.endTransaction();
		}

		return rowId;
	}

	private String getTimestamp()
	{
		String timeStamp = new Date().toString();
		return timeStamp;
	}

	public void insertLotteryTickets(long lotteryId, ArrayList<String> tickets)
	{
		SQLiteDatabase sqlDb = getWritableDatabase();
		ContentValues cv = new ContentValues();

		try
		{
			sqlDb.beginTransaction();

			for (String tckt : tickets)
			{
				cv.put(TICKETS_TITLE, tckt);
				cv.put(TICKETS_CREATION_TIME, getTimestamp());
				cv.put(TICKETS_LOTTERY_ID, lotteryId);

				sqlDb.insert(TABLE_TICKETS, TICKETS_TITLE, cv);
			}

			sqlDb.setTransactionSuccessful();
		}
		catch (Exception e)
		{
			Log.e(getClass().getSimpleName(),
					"Exception inserting tickets for a lottery.", e);
		}
		finally
		{
			sqlDb.endTransaction();
		}

	}
	
	/**
	 * 
	 * @param lotteryId The ID of the lottery for which a new ticket is to be created
	 * @param title the title/value of the ticket
	 * @return The ID of the newly inserted ticket
	 */
	public long insertTicket(long lotteryId, String title)
	{
		SQLiteDatabase sqlDb = getWritableDatabase();
		ContentValues cv = new ContentValues();
		long ticketId = -1;
		
		try
		{
			sqlDb.beginTransaction();

			cv.put(TICKETS_TITLE, title);
			cv.put(TICKETS_CREATION_TIME, getTimestamp());
			cv.put(TICKETS_LOTTERY_ID, lotteryId);
			
			ticketId = sqlDb.insert(TABLE_TICKETS, TICKETS_TITLE, cv);

			sqlDb.setTransactionSuccessful();
		}
		catch (Exception e)
		{
			Log.e(getClass().getSimpleName(),
					"Exception inserting a single ticket for a lottery.", e);
		}
		finally
		{
			sqlDb.endTransaction();
		}
		
		return ticketId;
	}

	public Cursor selectLotteries()
	{
		String sql = "SELECT _id, title, description"
				+ " FROM lotteries ORDER BY title";
		return getReadableDatabase().rawQuery(sql, null);
	}

	public Cursor selectLottery(long lotteryId)
	{
		String sql = String.format("SELECT * FROM lotteries WHERE _id = %d",
				lotteryId);
		return getReadableDatabase().rawQuery(sql, null);
	}

	public Cursor selectLotteryTickets(long lotteryId)
	{
		String sql = String.format("SELECT * FROM %s WHERE %s = %d",
				TABLE_TICKETS, TICKETS_LOTTERY_ID, lotteryId);

		return getReadableDatabase().rawQuery(sql, null);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
	{
		// TODO Auto-generated method stub

	}

	private void supplyDummyData(SQLiteDatabase db)
	{
		ContentValues cv = new ContentValues();

		cv.put(LOTTERIES_TITLE, "Restaurants");
		cv.put(LOTTERIES_DESCRIPTION, "Choose a random restaurant for me!");
		Date now = new Date();
		cv.put(LOTTERIES_CREATION_TIME, now.toString());
		db.insert(TABLE_LOTTERIES, LOTTERIES_TITLE, cv);

		cv.put(LOTTERIES_TITLE, "Movies");
		cv.put(LOTTERIES_DESCRIPTION,
				"Choose a movie from my list of movies awaited to be watched!");
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DATE, -1);
		Date yesterday = cal.getTime();
		cv.put(LOTTERIES_CREATION_TIME, yesterday.toString());
		db.insert(TABLE_LOTTERIES, LOTTERIES_TITLE, cv);

		cv.put(LOTTERIES_TITLE, "Winner");
		cv.put(LOTTERIES_DESCRIPTION, "Choose the winner of the lottery!");
		cal.setTime(now);
		cal.add(Calendar.DATE, -1);
		Date otherDay = cal.getTime();
		cv.put(LOTTERIES_CREATION_TIME, otherDay.toString());
		db.insert(TABLE_LOTTERIES, LOTTERIES_TITLE, cv);
	}
}
