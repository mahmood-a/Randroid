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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;

import com.applemma.util.support.DynamicFragmentActivity;

public class LotteriesActivity extends DynamicFragmentActivity implements
		EditDeleteDialog.OnActionSelectedListener,
		LotteriesFragment.IDialogShower
{
	private static final String EDIT_DEL_DIALOG_TAG = "edit_del_tag";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		loadDynamicFragment(new LotteriesFragment());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_lottery_machines, menu);
		return true;
	}

	@Override
	public void onEditSelected(long lotteryID)
	{
		Intent i = new Intent(this, AddLotteryActivity.class);
		i.putExtra(AddLotteryActivity.EXTRA_LOTTERY_ID, lotteryID);
		
		startActivity(i);
	}

	@Override
	public void onDelSelected(long lotteryID)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void showEditDelDialog(long lotteryID)
	{
		String dlgTitle = getString(R.string.edit_del_dlg_title);
		EditDeleteDialog dlg = EditDeleteDialog
				.getInstance(dlgTitle, lotteryID);

		FragmentManager fm = getSupportFragmentManager();
		dlg.show(fm, EDIT_DEL_DIALOG_TAG);
	}

}
