package com.applemma.randroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

public class TicketDialogFragment extends DialogFragment implements
		DialogInterface.OnClickListener
{
	private View mForm;
	private String mDialogTitle;

	public interface EditTicketDialogListener
	{
		void onFinishEditTicket(String inputText);
	}

	public TicketDialogFragment(String dialogTitle)
	{
		mDialogTitle = dialogTitle;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		mForm = getActivity().getLayoutInflater().inflate(
				R.layout.ticket_dialog, null);

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		AlertDialog dialog = 
				builder.setTitle(mDialogTitle)
					   .setView(mForm)
					   .setPositiveButton(android.R.string.ok, this)
					   .setNegativeButton(android.R.string.cancel, null)
					   .create();
		
		return dialog;
	}

	@Override
	public void onClick(DialogInterface dialog, int which)
	{
		// TODO
	}

}
