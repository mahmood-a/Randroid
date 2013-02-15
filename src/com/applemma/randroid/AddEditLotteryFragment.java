package com.applemma.randroid;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Date;

public class AddEditLotteryFragment extends Fragment implements
		View.OnClickListener
{
	DatabaseHelper db;

	private EditText titleEdit;
	private EditText descriptionEdit;
	private Button okBtn;
	private Button addTicketBtn;
	private ListView ticketsList;

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		setRetainInstance(true);

		db = DatabaseHelper.getInstance(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View layout = inflater.inflate(R.layout.add_edit_lottery, container,
				false);

		titleEdit = (EditText) layout.findViewById(R.id.edit_title);
		descriptionEdit = (EditText) layout.findViewById(R.id.edit_description);

		okBtn = (Button) layout.findViewById(R.id.lottery_ok_btn);
		okBtn.setOnClickListener(this);
		
		ticketsList = (ListView)layout.findViewById(R.id.tickets_list);

		addTicketBtn = (Button) layout.findViewById(R.id.add_ticket_btn);
		addTicketBtn.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
			}
		});

		return layout;
	}

	@Override
	public void onClick(View v)
	{
		ContentValues cv = new ContentValues();

		String title = titleEdit.getText().toString();
		cv.put(DatabaseHelper.LOTTERIES_TITLE, title);

		String desciption = descriptionEdit.getText().toString();
		cv.put(DatabaseHelper.LOTTERIES_DESCRIPTION, desciption);

		String timeStamp = new Date().toString();
		cv.put(DatabaseHelper.LOTTERIES_CREATION_TIME, timeStamp);

		new InsertLotteryTask().execute(cv);
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}

	private class InsertLotteryTask extends
			AsyncTask<ContentValues, Void, Void>
	{
		Boolean rowAdded = false;

		@Override
		protected Void doInBackground(ContentValues... cv)
		{
			long rowId = db.getWritableDatabase().insert(
					DatabaseHelper.TABLE_LOTTERIES,
					DatabaseHelper.LOTTERIES_TITLE, cv[0]);

			if (rowId < 0)
			{
				Log.e("Row not added",
						"InsertLotteryTask in AddEditLotteryFragment "
								+ "could not add a new lottery to the database.");
			}
			else
			{
				rowAdded = true;
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result)
		{
			Intent i = new Intent(getActivity(), LotteriesActivity.class);
			startActivity(i);

			if (rowAdded)
			{
				Toast.makeText(getActivity(), R.string.lottery_added_toast,
						Toast.LENGTH_SHORT).show();
			}
		}
	}

}
