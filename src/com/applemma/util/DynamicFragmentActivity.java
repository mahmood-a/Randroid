package com.applemma.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;

/**
 * A fragment that is executed dynamically from the activity, typically in
 * "OnCreate" callback of an activity
 * 
 * @author Mahmood
 * 
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DynamicFragmentActivity extends Activity
{
	protected void executeDynamicFragment(Fragment f)
	{
		if (getFragmentManager().findFragmentById(android.R.id.content) == null)
		{
			getFragmentManager().beginTransaction()
					.add(android.R.id.content, f).commit();
		}
	}

}
