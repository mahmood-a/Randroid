package com.applemma.util.support;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * A fragment that is executed dynamically from the activity, typically in
 * "OnCreate" callback of an activity. This class is used with support versions
 * of FragmentActivity and Fragment
 * 
 * @author Mahmood
 * 
 */

public class DynamicFragmentActivity extends FragmentActivity
{
	protected void executeDynamicFragment(Fragment f)
	{
		if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null)
		{
			getSupportFragmentManager().beginTransaction()
					.add(android.R.id.content, f).commit();
		}
	}

}