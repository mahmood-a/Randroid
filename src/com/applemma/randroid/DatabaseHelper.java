package com.applemma.randroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper
{

	// Database schema
	private static final String DATABASE_NAME = "randroid.db";
	private static final int SCHEMA = 1;

	// Database tables and their columns
	static final String TABLE_LOTTERIES = "lotteries";
	static final String LOTTERIES_TITLE = "title";
	static final String LOTTERIES_DESCRIPTION = "description";
	static final String LOTTERIES_CREATION_TIME = "creation_time";

	static final String TABLE_TICKETS = "tickets";
	static final String TICKETS_TITLE = "title";
	static final String TICKETS_CREATION_TIME = "creation_time";
	static final String TICKETS_LOTTERY_ID = "lottery_id";

	static final String TABLE_DRAWS = "draw";
	static final String DRAWS_TIMESTAMP = "timestamp";
	static final String DRAWS_RESULT = "result";
	static final String DRAWS_LOTTERY_ID = "lottery_id";

	// Singleton members
	private static DatabaseHelper singleton = null;
	private Context ctxt = null;

	public synchronized static DatabaseHelper getInstance(Context ctxt)
	{
		if (singleton == null)
		{
			singleton = new DatabaseHelper(ctxt.getApplicationContext());
		}

		return singleton;
	}

	private DatabaseHelper(Context ctxt)
	{
		super(ctxt, DATABASE_NAME, null, SCHEMA);
		this.ctxt = ctxt;
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

			supplyDummyData(db);

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
		long rowId = sqlDb.insert(TABLE_LOTTERIES, LOTTERIES_TITLE, cv);

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
		for(String tckt: tickets)
		{
			cv.put(TICKETS_TITLE, tckt);
			cv.put(TICKETS_CREATION_TIME, getTimestamp());
			cv.put(TICKETS_LOTTERY_ID, lotteryId);
			
			sqlDb.insert(TABLE_TICKETS, TICKETS_TITLE, cv);
		}
	}

	public Cursor selectLotteriesQuery()
	{
		String sql = "SELECT _id, title, description"
				+ " FROM lotteries ORDER BY title";
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
