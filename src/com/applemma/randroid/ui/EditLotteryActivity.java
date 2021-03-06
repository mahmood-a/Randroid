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
import android.support.v4.app.FragmentManager;

public class EditLotteryActivity extends LotteryActivity implements
		EditDeleteDialog.OnActionSelectedListener,
		EditDeleteDialog.IDialogShower
{
	private static final String EDIT_DEL_DIALOG_TAG = "edit_del_dlg";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		long lotteryId = getIntent().getLongExtra(EXTRA_LOTTERY_ID, -1);

		loadDynamicFragment(EditLotteryFragment.newInstance(lotteryId));
	}

	@Override
	public void onEditSelected(long itemId)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onDelSelected(long itemId)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void showDialog(String title, long itemID)
	{
		EditDeleteDialog dlg = EditDeleteDialog.newInstance(title, itemID);
		FragmentManager fm = getSupportFragmentManager();
		dlg.show(fm, EDIT_DEL_DIALOG_TAG);
	}
}
