package com.applemma.randroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;

public class TicketDialogFragment extends DialogFragment implements
		DialogInterface.OnClickListener
{
	private static final String KEY_TITLE = "title";
	private View mForm;

	public interface EditTicketDialogListener
	{
		void onFinishEditTicket(String inputText);
	}

	public static TicketDialogFragment newInstance(String dialogTitle)
	{
		TicketDialogFragment f = new TicketDialogFragment();
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
