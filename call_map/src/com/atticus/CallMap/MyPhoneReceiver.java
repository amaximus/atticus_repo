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
	  
	String ownISO;
	Integer tposition, tsec;
	  	
    SharedPreferences CallMapSettings = context.getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
    GVariables.app_enabled = CallMapSettings.getBoolean("app_enabled", GVariables.def_app_enabled);
	GVariables.onlyInternational = CallMapSettings.getBoolean("only_international", GVariables.def_only_international);
	tposition = CallMapSettings.getInt("toast_position", GVariables.def_toast_position);
	tsec = CallMapSettings.getInt("toast_sec", GVariables.def_toast_sec);

    ownISO = CallMapSettings.getString("own_ISO", "");
	
	if ( GVariables.app_enabled) {
		
		Bundle extras = intent.getExtras();
		if (extras != null) {
			
			// CallMap cmap = new CallMap();
			
			String outNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
      
			if ( outNumber != null && outNumber.length() != 0 ) {
				Integer ccCode = GVariables.country_prefix(outNumber);
				
				GVariables.cc = new Country_Code();

				String ccCountry = GVariables.cc.getcountry(ccCode);
				
				if ( ccCountry != null && ccCountry.length() != 0 ) {
			
					if ( ! GVariables.onlyInternational || ( ccCountry != GVariables.cc.getcountry(ownISO))) {
						if (ccCountry != null ) {
							// Log.w("DEBUG", " > " + ccCountry);
							GVariables.DisplayToast(context,ccCode,ccCountry,tposition,tsec);
						}
					} // if
				}
			} // if
		} // if
    } // if
  }   // onReceive
  	
} 