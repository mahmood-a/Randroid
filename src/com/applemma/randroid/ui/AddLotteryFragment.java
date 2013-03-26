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

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.applemma.randroid.R;

public class AddLotteryFragment extends LotteryFragment implements
		View.OnClickListener
{

	@Override
	public void onClickOkBtn()
	{
		 String title = getTitleEdit().getText().toString();
		 String description = getDescriptionEdit().getText().toString();
		
		 new InsertLotteryTask().execute(title, description);
	}

	private class InsertLotteryTask extends AsyncTask<String, Void, Void>
	{
		boolean lotteryAdded = false;

		@Override
		protected Void doInBackground(String... args)
		{
			long rowId = getDb().insertLottery(args[0], args[1]);

			if (rowId >= 0)
			{
				lotteryAdded = true;
				getDb().insertLotteryTickets(rowId, getTicketsList());
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result)
		{
			Intent i = new Intent(getActivity(), LotteriesActivity.class);
			startActivity(i);

			if (lotteryAdded)
			{
				Toast.makeText(getActivity(), R.string.lottery_added_toast,
						Toast.LENGTH_SHORT).show();
			}
		}
	}

}
