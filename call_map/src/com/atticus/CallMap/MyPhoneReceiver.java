package com.atticus.CallMap;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.util.Log;

public class MyPhoneReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
	  	
	if ( GVariables.app_enabled == null ) {
        SharedPreferences CallMapSettings = context.getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
        GVariables.app_enabled = CallMapSettings.getBoolean("app_enabled", GVariables.def_app_enabled);
	}
	
	if ( GVariables.app_enabled) {
		
		Bundle extras = intent.getExtras();
		if (extras != null) {
			
			CallMap cmap = new CallMap();
			
			String outNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
      
			Integer ccCode = GVariables.country_prefix(outNumber);

			String ccCountry = GVariables.cc.getcountry(ccCode);
			
			if ( ! GVariables.onlyInternational || ( ccCountry != GVariables.cc.getcountry(GVariables.ownCountryISO))) {
				if (ccCountry != null ) {
					// Log.w("DEBUG", " > " + ccCountry);
						
					GVariables.DisplayToast(context,ccCode,ccCountry);
				}
			} // if
		} // if
    } // if
  }   // onReceive
  	
} 