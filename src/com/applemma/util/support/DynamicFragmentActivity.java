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

package com.applemma.util.support;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * An Activity that loads fragments dynamically, typically in
 * the "OnCreate" callback. This class is used with support versions
 * of FragmentActivity and Fragment
 * 
 * @author Mahmood
 * 
 */

public class DynamicFragmentActivity extends FragmentActivity
{
	protected void loadDynamicFragment(Fragment f)
	{
		if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null)
		{
			getSupportFragmentManager().beginTransaction()
					.add(android.R.id.content, f).commit();
		}
	}

}