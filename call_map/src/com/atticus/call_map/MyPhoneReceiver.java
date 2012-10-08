package com.atticus.call_map;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.util.Log;

public class MyPhoneReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
	if ( GVariables.app_enabled ) {
		
		Bundle extras = intent.getExtras();
		if (extras != null) {
			String outNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
      
			Integer ccCode = GVariables.country_prefix(outNumber);

			String ccCountry = GVariables.cc.getcountry(ccCode);
			if (ccCountry != null ) {
				// Log.w("DEBUG", " > " + ccCountry);

				GVariables.DisplayToast(context,ccCode,ccCountry);
				
			} // if
		} // if
    } // if
  }   // onReceive
  	
} 