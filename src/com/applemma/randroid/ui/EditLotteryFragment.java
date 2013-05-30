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

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.applemma.randroid.R;
import com.applemma.randroid.data.DatabaseHelper;
import com.applemma.randroid.data.Lottery;

public class EditLotteryFragment extends Fragment
{
	private static String KEY_LOTTERY_ID = "lottery_id";

	private DatabaseHelper mDb;
	private EditText mEditTitle;
	private EditText mEditDescription;
	private ListView mTicketsListView;
	private Button mAddTicketBtn;

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

		setRetainInstance(true);

		mDb = DatabaseHelper.getInstance(getActivity());
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View layout = inflater.inflate(R.layout.edit_lottery, container, false);
		mEditTitle = (EditText) layout.findViewById(R.id.edit_title);
		mEditDescription = (EditText) layout
				.findViewById(R.id.edit_description);

		mTicketsListView = (ListView) layout.findViewById(R.id.tickets_list);
		mTicketsListView.setLongClickable(true);
		mTicketsListView
				.setOnItemLongClickListener(new OnItemLongClickListener()
				{

					@Override
					public boolean onItemLongClick(AdapterView<?> adapter,
							View view, int pos, long id)
					{
						EditDeleteDialog.IDialogShower activity = 
								(EditDeleteDialog.IDialogShower) getActivity();
						
						String title = getString(R.string.edit_del_dlg_title);
						activity.showDialog(title, id);
						
						return true;
					}
				});

		mAddTicketBtn = (Button) layout.findViewById(R.id.add_lottery_btn);

		// TODO, Custom CursorAdapter for a synced list

		return layout;
	}

	private class LoadLotteryTask extends AsyncTask<Long, Void, Lottery>
	{

		@Override
		protected Lottery doInBackground(Long... params)
		{
			return null;
		}

		@Override
		protected void onPostExecute(Lottery loadedLottery)
		{

		}

	}

	private class TicketsCursorAdapter extends CursorAdapter
	{
		private Context mCtxt;
		private Cursor mCur;
		private LayoutInflater mInflater;
		private final String TAG = getClass().getSimpleName();

		public TicketsCursorAdapter(Context context, Cursor c, int flags)
		{
			super(context, c, flags);
			mCtxt = context;
			mCur = c;
			mInflater = LayoutInflater.from(mCtxt);
		}

		@Override
		public void bindView(View view, Context context, Cursor c)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public View newView(Context arg0, Cursor arg1, ViewGroup arg2)
		{
			// TODO Auto-generated method stub
			return null;
		}

	}

}
