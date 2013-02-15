package com.applemma.util.support;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * An Activity that is loads fragments dynamically, typically in
 * the "OnCreate" callback. This class is used with support versions
 * of FragmentActivity and Fragment
 * 
 * @author Mahmood
 * 
 */

public class DynamicFragmentActivity extends FragmentActivity
{
	protected void loadDynamicFragment(Fragment f)
	{
		if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null)
		{
			getSupportFragmentManager().beginTransaction()
					.add(android.R.id.content, f).commit();
		}
	}

}