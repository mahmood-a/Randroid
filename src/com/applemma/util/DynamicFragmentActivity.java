package com.applemma.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;

/**
 * An Activity that loads fragments dynamically, typically in
 * the on "OnCreate" callback
 * 
 * @author Mahmood
 * 
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DynamicFragmentActivity extends Activity
{
	protected void loadDynamicFragment(Fragment f)
	{
		if (getFragmentManager().findFragmentById(android.R.id.content) == null)
		{
			getFragmentManager().beginTransaction()
					.add(android.R.id.content, f).commit();
		}
	}

}
