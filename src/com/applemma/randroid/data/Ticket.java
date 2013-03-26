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

import android.content.Context;
import android.database.Cursor;

import java.util.Date;

/**
 * @author Mahmood
 * 
 */
public class Ticket
{
	private long mTicketId;
	private String mTitle;
	private Date mCreationTime;
	private long mLotteryId;
	private Cursor mCursor;
	private Context mCtxt;
	private DatabaseHelper mDb;

	private Ticket(long ticketId, String title, long lotteryId, Context ctxt)
	{
		mTicketId = ticketId;
		mTitle = title;
		mLotteryId = lotteryId;
		mCtxt = ctxt;
		mDb = DatabaseHelper.getInstance(mCtxt);
	}

	public static Ticket newTicket(long lotteryId, String title, Context ctxt)
	{
		DatabaseHelper db = DatabaseHelper.getInstance(ctxt);
		long ticketId = db.insertTicket(lotteryId, title);
		return new Ticket(ticketId, title, lotteryId, ctxt);
	}
	
	public String getTitle()
	{
		return mTitle;
	}

	public void setTitle(String title)
	{
		mTitle = title;
	}

	public long getTicketId()
	{
		return mTicketId;
	}

	public Date getCreationTime()
	{
		return mCreationTime;
	}

	public long getLotteryId()
	{
		return mLotteryId;
	}
}
