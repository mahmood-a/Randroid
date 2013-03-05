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

package com.applemma.randroid;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.applemma.util.support.DynamicFragmentActivity;

public class AddEditLotteryActivity extends DynamicFragmentActivity implements
		TicketDialog.EditTicketDialogListener,
		AddEditLotteryFragment.IDialogShower
{
	public static final String EXTRA_LOTTERY_ID = "lottery_id";

	private static final String DIALOG_FRAGMENT_TAG = "dialog_fragment";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		loadDynamicFragment(new AddEditLotteryFragment());
	}

	@Override
	public void showAddTicketDialog()
	{
		String dglTitle = getString(R.string.add_ticket_dlg_title);
		TicketDialog dlg = TicketDialog.newInstance(dglTitle);

		FragmentManager fm = getSupportFragmentManager();
		dlg.show(fm, DIALOG_FRAGMENT_TAG);
	}

	@Override
	public void onFinishEditTicket(String inputText)
	{
		FragmentManager fm = getSupportFragmentManager();
		AddEditLotteryFragment frag = (AddEditLotteryFragment) fm
				.findFragmentById(getFragmentId());

		frag.addTicketToList(inputText);
	}
}
