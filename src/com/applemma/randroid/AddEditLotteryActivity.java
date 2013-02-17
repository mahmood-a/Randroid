package com.applemma.randroid;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.applemma.util.support.DynamicFragmentActivity;

public class AddEditLotteryActivity extends DynamicFragmentActivity implements
		TicketDialogFragment.EditTicketDialogListener
{
	private static final String DIALOG_FRAGMENT_TAG = "dialog_fragment";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		loadDynamicFragment(new AddEditLotteryFragment());
	}

	public void showTicketDialog()
	{
		String dglTitle = getString(R.string.add_ticket_dlg_title);
		TicketDialogFragment dlg = TicketDialogFragment.newInstance(dglTitle);

		FragmentManager fm = getSupportFragmentManager();
		dlg.show(fm, DIALOG_FRAGMENT_TAG);
	}

	@Override
	public void onFinishEditTicket(String inputText)
	{
		// TODO Auto-generated method stub

	}
}
