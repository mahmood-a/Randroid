package com.applemma.randroid;

import android.os.Bundle;

import com.applemma.util.support.DynamicFragmentActivity;

public class EditLotteryActivity extends DynamicFragmentActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		loadDynamicFragment(new EditLotteryFragment());
	}

}
