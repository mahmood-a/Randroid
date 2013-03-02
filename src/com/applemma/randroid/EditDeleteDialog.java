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

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

public class EditDeleteDialog extends DialogFragment
{
	private static final String KEY_TITLE = "title";
	private View mForm;

	public interface OnActionSelectedListener
	{
		public void onEditSelected();

		public void onDelSelected();
	}

	public static EditDeleteDialog getInstance(String dialogTitle)
	{
		EditDeleteDialog f = new EditDeleteDialog();
		Bundle args = new Bundle();

		args.putString(KEY_TITLE, dialogTitle);
		f.setArguments(args);

		return f;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		mForm = getActivity().getLayoutInflater().inflate(
				R.layout.edit_del_dialog, null);

		TextView edit = (TextView) mForm.findViewById(R.id.dlg_edit);
		edit.setOnClickListener(new EditOnClickListener());

		TextView del = (TextView) mForm.findViewById(R.id.dlg_del);
		del.setOnClickListener(new DelOnClickListener());

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		AlertDialog dialog = builder
				.setTitle(getArguments().getString(KEY_TITLE)).setView(mForm)
				.setIcon(android.R.drawable.ic_dialog_alert).create();

		return dialog;
	}

	private class EditOnClickListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v)
		{
			OnActionSelectedListener activity = (OnActionSelectedListener) getActivity();
			activity.onEditSelected();
		}

	}

	private class DelOnClickListener implements View.OnClickListener
	{

		@Override
		public void onClick(View v)
		{
			OnActionSelectedListener activity = (OnActionSelectedListener) getActivity();
			activity.onDelSelected();
		}

	}
}
