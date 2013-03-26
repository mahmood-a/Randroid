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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.applemma.randroid.R;
import com.applemma.randroid.data.DatabaseHelper;

import java.util.ArrayList;

/**
 * @author Mahmood
 * 
 */
public abstract class LotteryFragment extends Fragment implements
		View.OnClickListener
{

	private DatabaseHelper mDb;
	private EditText mTitleEdit;
	private EditText mDescriptionEdit;
	private Button mOkBtn;
	private Button mAddTicketBtn;
	private ArrayList<String> mTicketsList;
	private ListView mTicketsListView;
	private ArrayAdapter<String> mListAdapter;

	// Setters & Getters
	protected final DatabaseHelper getDb()
	{
		return mDb;
	}

	protected final EditText getTitleEdit()
	{
		return mTitleEdit;
	}

	protected final EditText getDescriptionEdit()
	{
		return mDescriptionEdit;
	}

	protected final Button getOkBtn()
	{
		return mOkBtn;
	}

	protected final Button getAddTicketBtn()
	{
		return mAddTicketBtn;
	}

	protected final ArrayList<String> getTicketsList()
	{
		return mTicketsList;
	}

	protected final void setTicketsList(ArrayList<String> list)
	{
		mTicketsList.clear();
		mTicketsList.addAll(list);
		if (mListAdapter != null)
		{
			mListAdapter.notifyDataSetChanged();
		}
	}

	protected final ListView getTicketsListView()
	{
		return mTicketsListView;
	}

	protected final ArrayAdapter<String> getListAdapter()
	{
		return mListAdapter;
	}
	// End of Setters & Getters

	public interface IDialogShower
	{
		void showAddTicketDialog();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		setRetainInstance(true);

		mDb = DatabaseHelper.getInstance(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View layout = inflater.inflate(R.layout.add_edit_lottery, container,
				false);

		mTitleEdit = (EditText) layout.findViewById(R.id.edit_title);
		mDescriptionEdit = (EditText) layout
				.findViewById(R.id.edit_description);

		mTicketsList = new ArrayList<String>();
		mTicketsListView = (ListView) layout.findViewById(R.id.tickets_list);
		mListAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, mTicketsList);
		mTicketsListView.setAdapter(mListAdapter);

		mOkBtn = (Button) layout.findViewById(R.id.lottery_ok_btn);
		mOkBtn.setOnClickListener(this);

		mAddTicketBtn = (Button) layout.findViewById(R.id.add_ticket_btn);
		mAddTicketBtn.setOnClickListener(this);

		return layout;
	}

	@Override
	public void onClick(View v)
	{
		if (v.getId() == mOkBtn.getId())
		{
			onClickOkBtn();
		}
		else if (v.getId() == mAddTicketBtn.getId())
		{
			onClickAddTicketBtn();
		}
	}

	public abstract void onClickOkBtn();

	public void onClickAddTicketBtn()
	{
		IDialogShower activity = (IDialogShower) getActivity();
		activity.showAddTicketDialog();
	}

	public void addTicketToList(String ticket)
	{
		mTicketsList.add(ticket);
		mListAdapter.notifyDataSetChanged();
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}
}
