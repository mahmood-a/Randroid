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

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Mahmood
 * 
 */
public class Lottery
{
	private long mId;
	private String mTitle;
	private String mDescription;
	private Date mCreationTime = null;
	private Cursor mCursor;
	private Context mCtxt;
	private DatabaseHelper mDb;

	public Lottery(long id, Context ctxt)
	{
		mCtxt = ctxt;
		mId = id;
		mDb = DatabaseHelper.getInstance(ctxt);
		mCursor = mDb.selectLottery(mId);
		mCursor.moveToFirst();
		mTitle = mCursor.getString(DatabaseHelper.LOTTERY_TITLE_INDEX);
		mDescription = mCursor
				.getString(DatabaseHelper.LOTTERY_DESCRIPTION_INDEX);
	}

	public void addTicket(String ticketTitle)
	{
		Ticket.newTicket(mId, ticketTitle, mCtxt);
	}

	public ArrayList<String> getLotteryTickets()
	{
		Cursor ticketsCur = mDb.selectLotteryTickets(mId);
		ArrayList<String> ticketsList = new ArrayList<String>();
		while (ticketsCur.moveToNext())
		{
			String ticket = ticketsCur
					.getString(DatabaseHelper.TICKET_TITLE_INDEX);
			ticketsList.add(ticket);
		}
		
		return ticketsList;
	}

	public String getTitle()
	{
		return mTitle;
	}

	public void setTitle(String title)
	{
		// TODO
	}

	public String getDescription()
	{
		return mDescription;
	}

	public void setDescription(String description)
	{
		// TODO
	}

	public Date getCreationTime()
	{
		if (mCreationTime == null)
		{
			// TODO, convert time string to Date
		}

		return mCreationTime;
	}

	public void setCreationTime(Date creationTime)
	{
		// TODO
	}

	public long getId()
	{
		return mId;
	}

	public Cursor getCursor()
	{
		return mCursor;
	}

}
