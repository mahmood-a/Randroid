package com.applemma.randroid;

import android.os.Bundle;
import android.view.Menu;

import com.applemma.util.support.DynamicFragmentActivity;

public class LotteriesActivity extends DynamicFragmentActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		executeDynamicFragment(new LotteriesFragment());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_lottery_machines, menu);
		return true;
	}

}
