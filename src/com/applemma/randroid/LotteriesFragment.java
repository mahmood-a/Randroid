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

public class LotteriesFragment extends ListFragment
{
	private DatabaseHelper db;

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		setRetainInstance(true);

		db = new DatabaseHelper(getActivity().getApplicationContext());
		new LoadLotteriesCursorTask().execute();
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();

		((CursorAdapter) getListAdapter()).getCursor().close();
		db.close();
	}

	private Cursor selectLotteriesQuery()
	{
		String sql = "SELECT _id, title, description"
				+ " FROM lotteries ORDER BY title";
		return db.getReadableDatabase().rawQuery(sql, null);
	}

	private class LoadLotteriesCursorTask extends AsyncTask<Void, Void, Void>
	{
		private Cursor lotteriesCursor;

		@Override
		protected Void doInBackground(Void... params)
		{
			lotteriesCursor = selectLotteriesQuery();
			lotteriesCursor.getCount();

			return null;
		}

		@Override
		protected void onPostExecute(Void result)
		{
			LotteryListAdapter adapter;

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			{
				adapter = new LotteryListAdapter(lotteriesCursor);
			}
			else
			{
				adapter = new LotteryListAdapter(lotteriesCursor, 0);
			}

			setListAdapter(adapter);
		}
	}

	private class LotteryListAdapter extends SimpleCursorAdapter
	{
		@SuppressWarnings("deprecation")
		LotteryListAdapter(Cursor cursor)
		{
			super(LotteriesFragment.this.getActivity(),
					R.layout.lottery_list_row, cursor, new String[] {
							DatabaseHelper.LOTTERIES_TITLE,
							DatabaseHelper.LOTTERIES_DESCRIPTION }, new int[] {
							R.id.lottery_title, R.id.lottery_description });

		}

		LotteryListAdapter(Cursor cursor, int flags)
		{
			super(LotteriesFragment.this.getActivity(),
					R.layout.lottery_list_row, cursor, new String[] {
							DatabaseHelper.LOTTERIES_TITLE,
							DatabaseHelper.LOTTERIES_DESCRIPTION }, new int[] {
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
