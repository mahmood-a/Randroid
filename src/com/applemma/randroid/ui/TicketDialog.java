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

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;

import com.applemma.randroid.R;

public class TicketDialog extends DialogFragment implements
		DialogInterface.OnClickListener
{
	private static final String KEY_TITLE = "title";
	private View mForm;

	public interface EditTicketDialogListener
	{
		void onFinishEditTicket(String inputText);
	}

	public static TicketDialog newInstance(String dialogTitle)
	{
		TicketDialog f = new TicketDialog();
		Bundle args = new Bundle();
		
		args.putString(KEY_TITLE, dialogTitle);
		f.setArguments(args);

		return f;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		mForm = getActivity().getLayoutInflater().inflate(
				R.layout.ticket_dialog, null);

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		AlertDialog dialog = builder
				.setTitle(getArguments().getString(KEY_TITLE))
				.setView(mForm)
				.setPositiveButton(android.R.string.ok, this)
				.setNegativeButton(android.R.string.cancel, null)
				.create();

		return dialog;
	}

	@Override
	public void onClick(DialogInterface dialog, int which)
	{
		EditText editText = (EditText) mForm
				.findViewById(R.id.ticket_title_edit);
		String inputText = editText.getText().toString();

		EditTicketDialogListener activity = (EditTicketDialogListener) getActivity();
		activity.onFinishEditTicket(inputText);
	}
}
