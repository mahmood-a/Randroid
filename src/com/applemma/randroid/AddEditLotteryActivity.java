package com.applemma.randroid;

import android.os.Bundle;

import com.applemma.util.support.DynamicFragmentActivity;

public class AddEditLotteryActivity extends DynamicFragmentActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		executeDynamicFragment(new AddEditLotteryFragment());
	}
}
