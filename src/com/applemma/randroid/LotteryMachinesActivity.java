package com.applemma.randroid;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class LotteryMachinesActivity extends FragmentActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null)
		{
			getSupportFragmentManager().beginTransaction()
					.add(android.R.id.content, new LotteryMachinesFragment())
					.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_lottery_machines, menu);
		return true;
	}

}
