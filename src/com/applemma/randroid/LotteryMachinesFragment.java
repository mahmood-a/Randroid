package com.applemma.randroid;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class LotteryMachinesFragment extends ListFragment
{
	private DatabaseHelper db;

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		setRetainInstance(true);

		db = new DatabaseHelper(getActivity().getApplicationContext());
		new LoadMachinesCursorTask().execute();
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();

		((CursorAdapter) getListAdapter()).getCursor().close();
		db.close();
	}

	private Cursor selectMachinesQuery()
	{
		String sql = "SELECT _id, title, description"
				+ " FROM machines ORDER BY title";
		return db.getReadableDatabase().rawQuery(sql, null);
	}

	private class LoadMachinesCursorTask extends AsyncTask<Void, Void, Void>
	{
		private Cursor machinesCursor;

		@Override
		protected Void doInBackground(Void... params)
		{
			machinesCursor = selectMachinesQuery();
			machinesCursor.getCount();

			return null;
		}

		@Override
		protected void onPostExecute(Void result)
		{
			MachinesListAdapter adapter;

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			{
				adapter = new MachinesListAdapter(machinesCursor);
			}
			else
			{
				adapter = new MachinesListAdapter(machinesCursor, 0);
			}

			setListAdapter(adapter);
		}
	}

	private class MachinesListAdapter extends SimpleCursorAdapter
	{
		@SuppressWarnings("deprecation")
		MachinesListAdapter(Cursor cursor)
		{
			super(LotteryMachinesFragment.this.getActivity(),
					R.layout.machines_list_row, cursor, new String[] {
							DatabaseHelper.MACHINES_TITLE,
							DatabaseHelper.MACHINES_DESCRIPTION }, new int[] {
							R.id.lottery_title, R.id.lottery_description });

		}

		MachinesListAdapter(Cursor cursor, int flags)
		{
			super(LotteryMachinesFragment.this.getActivity(),
					R.layout.machines_list_row, cursor, new String[] {
							DatabaseHelper.MACHINES_TITLE,
							DatabaseHelper.MACHINES_DESCRIPTION }, new int[] {
							R.id.lottery_title, R.id.lottery_description },
					flags);

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View row = super.getView(position, convertView, parent);

			ImageButton lotteryIcon = (ImageButton) row
					.findViewById(R.id.lottery_icon);
			lotteryIcon.setImageResource(R.drawable.blue_ball);

			return row;
		}

	}

}
