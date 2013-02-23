package com.applemma.randroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

public class LotteriesFragment extends ListFragment implements
		View.OnClickListener, AdapterView.OnItemLongClickListener
{
	DatabaseHelper db;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		setRetainInstance(true);

		db = DatabaseHelper.getInstance(getActivity());
		new LoadLotteriesCursorTask().execute();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View layout = inflater.inflate(R.layout.lotteries_fragment, container,
				false);

		layout.findViewById(R.id.add_lottery_btn).setOnClickListener(this);
		getListView().setOnItemLongClickListener(this);

		return layout;
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onClick(View v)
	{
		Intent i = new Intent(getActivity(), AddEditLotteryActivity.class);

		// extras could be put here, later

		startActivity(i);

	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();

		((CursorAdapter) getListAdapter()).getCursor().close();
		db.close();
	}

	private class LoadLotteriesCursorTask extends AsyncTask<Void, Void, Void>
	{
		private Cursor lotteriesCursor;

		@Override
		protected Void doInBackground(Void... params)
		{
			lotteriesCursor = db.selectLotteriesQuery();
			lotteriesCursor.getCount();

			return null;
		}

		@SuppressWarnings("deprecation")
		@Override
		protected void onPostExecute(Void result)
		{
			SimpleCursorAdapter adapter;

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			{
				adapter = new SimpleCursorAdapter(
						LotteriesFragment.this.getActivity(),
						R.layout.lottery_list_row, lotteriesCursor,
						new String[] { DatabaseHelper.LOTTERIES_TITLE,
								DatabaseHelper.LOTTERIES_DESCRIPTION },
						new int[] { R.id.lottery_title,
								R.id.lottery_description });
			}
			else
			{
				adapter = new SimpleCursorAdapter(
						LotteriesFragment.this.getActivity(),
						R.layout.lottery_list_row, lotteriesCursor,
						new String[] { DatabaseHelper.LOTTERIES_TITLE,
								DatabaseHelper.LOTTERIES_DESCRIPTION },
						new int[] { R.id.lottery_title,
								R.id.lottery_description }, 0);
			}

			setListAdapter(adapter);
		}
	}

}
