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

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.v4.widget.SimpleCursorAdapter;

/**
 * This class is the same as SimpleCursorAdapter with the sole difference of
 * having a static factory method "newInstance" that does backwards
 * compatibility checks before creating the adapter
 * 
 * @author Mahmood
 * 
 */
public class SimplerCursorAdapter extends SimpleCursorAdapter
{

	@SuppressWarnings("deprecation")
	private SimplerCursorAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to)
	{
		super(context, layout, c, from, to);
	}

	private SimplerCursorAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to, int flags)
	{
		super(context, layout, c, from, to, flags);
	}

	public static SimplerCursorAdapter newInstance(Context context, int layout,
			Cursor c, String[] from, int[] to)
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
		{
			return new SimplerCursorAdapter(context, layout, c, from, to);
		}
		else
		{
			return new SimplerCursorAdapter(context, layout, c, from, to, 0);
		}
	}

}
