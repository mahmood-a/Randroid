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
		
		loadDynamicFragment(new LotteriesFragment());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_lottery_machines, menu);
		return true;
	}

}
