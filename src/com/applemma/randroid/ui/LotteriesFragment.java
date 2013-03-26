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
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.applemma.randroid.R;
import com.applemma.randroid.data.DatabaseHelper;
import com.applemma.util.support.SimplerCursorAdapter;

public class LotteriesFragment extends ListFragment implements
		View.OnClickListener, AdapterView.OnItemLongClickListener
{
	private DatabaseHelper mDb;

	public interface IDialogShower
	{
		public void showEditDelDialog(long lotteryID);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		setRetainInstance(true);

		getListView().setLongClickable(true);
		getListView().setOnItemLongClickListener(this);

		mDb = DatabaseHelper.getInstance(getActivity());

		new LoadLotteriesTask().execute();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View layout = inflater.inflate(R.layout.lotteries_fragment, container,
				false);

		layout.findViewById(R.id.add_lottery_btn).setOnClickListener(this);

		return layout;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id)
	{
		IDialogShower activity = (IDialogShower) getActivity();
		activity.showEditDelDialog(id);

		return true;
	}

	@Override
	public void onClick(View v)
	{
		Intent i = new Intent(getActivity(), AddLotteryActivity.class);

		// extras could be put here, later

		startActivity(i);

	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();

		((CursorAdapter) getListAdapter()).getCursor().close();
		mDb.close();
	}

	private class LoadLotteriesTask extends AsyncTask<Void, Void, Void>
	{
		private Cursor lotteriesCursor;

		@Override
		protected Void doInBackground(Void... params)
		{
			lotteriesCursor = mDb.selectLotteries();
			lotteriesCursor.getCount();

			return null;
		}

		@Override
		protected void onPostExecute(Void result)
		{
			SimpleCursorAdapter adapter;
			adapter = SimplerCursorAdapter.newInstance(
					LotteriesFragment.this.getActivity(),
					R.layout.lottery_list_row, lotteriesCursor, new String[] {
							DatabaseHelper.LOTTERIES_TITLE,
							DatabaseHelper.LOTTERIES_DESCRIPTION }, new int[] {
							R.id.lottery_title, R.id.lottery_description });

			setListAdapter(adapter);
		}
	}

}
