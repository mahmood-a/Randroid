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

package com.applemma.randroid.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.applemma.randroid.data.Lottery;

import java.util.ArrayList;

public class EditLotteryFragment extends LotteryFragment implements
		View.OnClickListener
{
	private static String KEY_LOTTERY_ID = "lottery_id";
	
	private String mTitleInit;
	private boolean mTitleChanged = false;
	
	private String mDescInit;
	private boolean mDescChanged = false;
	
	private ArrayList<String> mTicketsInit;
	private boolean mTicketsChanged = false;

	public static EditLotteryFragment newInstance(long lotteryId)
	{
		EditLotteryFragment f = new EditLotteryFragment();
		Bundle args = new Bundle();

		args.putLong(KEY_LOTTERY_ID, lotteryId);
		f.setArguments(args);

		return f;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		loadLottery();
	}

	private void loadLottery()
	{
		long lotteryId = getArguments().getLong(KEY_LOTTERY_ID);
		new LoadLotteryTask().execute(lotteryId);
	}

	@Override
	public void onClickOkBtn()
	{
		String titleCurrent = getTitleEdit().getText().toString();
		if(!mTitleInit.equals(titleCurrent))
		{
			mTitleChanged = true;
		}
		
		String descCurrent = getDescriptionEdit().getText().toString();
		if(!mDescInit.equals(descCurrent))
		{
			mDescChanged = true;
		}
		
		ArrayList<String> ticketsCurrent = getTicketsList();
		if(ticketsCurrent.size() != mTicketsInit.size())
		{
			mTicketsChanged = true;
		}
		else if(true)
		{
			// TODO, one of the tickets have different value
		}
	}

	private class LoadLotteryTask extends AsyncTask<Long, Void, Lottery>
	{

		@Override
		protected Lottery doInBackground(Long... params)
		{
			long lotteryId = params[0];
			Lottery lottery = new Lottery(lotteryId, getActivity());
			mTicketsInit = lottery.getLotteryTickets();
			return lottery;
		}

		@Override
		protected void onPostExecute(Lottery loadedLottery)
		{
			mTitleInit = loadedLottery.getTitle();
			getTitleEdit().setText(mTitleInit);
			
			mDescInit = loadedLottery.getDescription();
			getDescriptionEdit().setText(mDescInit);
			
			if (mTicketsInit != null)
			{
				setTicketsList(mTicketsInit);
			}
			super.onPostExecute(loadedLottery);
		}

	}

}
