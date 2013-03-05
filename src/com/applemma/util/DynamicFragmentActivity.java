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

package com.applemma.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;

/**
 * An Activity that loads fragments dynamically, typically in
 * the on "OnCreate" callback
 * 
 * @author Mahmood
 * 
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DynamicFragmentActivity extends Activity
{
	private int fragmentId = -1;
	
	/**
	 * This method assumes the fragId = android.R.id.content
	 * @param f	The fragment instance that is to be loaded dynamically
	 */
	protected void loadDynamicFragment(Fragment f)
	{
		loadDynamicFragment(f, android.R.id.content);
	}

	protected void loadDynamicFragment(Fragment f, int fragId)
	{
		fragmentId = fragId;
		if (getFragmentManager().findFragmentById(fragmentId) == null)
		{
			getFragmentManager().beginTransaction().add(fragmentId, f)
					.commit();
		}
	}
	
	protected int getFragmentId()
	{
		return fragmentId;
	}
}
