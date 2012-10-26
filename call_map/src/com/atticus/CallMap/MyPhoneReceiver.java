package com.atticus.CallMap;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.util.Log;
import android.util.Log;

public class MyPhoneReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
	  
	String ownISO;
	Integer tposition, tsec;
	Boolean map;
	  	
    SharedPreferences CallMapSettings = context.getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
    GVariables.app_enabled = CallMapSettings.getBoolean("app_enabled", GVariables.def_app_enabled);
	GVariables.onlyInternational = CallMapSettings.getBoolean("only_international", GVariables.def_only_international);
	tposition = CallMapSettings.getInt("toast_position", GVariables.def_toast_position);
	tsec = CallMapSettings.getInt("toast_sec", GVariables.def_toast_sec);
	map = CallMapSettings.getBoolean("show_map", GVariables.def_show_map);

	ownISO = CallMapSettings.getString("own_ISO", "");
    
	//Log.d("Outgoing", " > " + Boolean.toString(GVariables.onlyInternational));

	if ( GVariables.app_enabled) {
		
		Bundle extras = intent.getExtras();
		if (extras != null) {
			
			// CallMap cmap = new CallMap();
			String outNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
      
			if ( outNumber != null && outNumber.length() != 0 ) {
				Integer ccCode = GVariables.country_prefix(outNumber);
				
				GVariables.cc = new Country_Code();

				String ccCountryISO = GVariables.cc.getISOmcc(ccCode);
				String ccCountry = GVariables.cc.getcountry(ccCode, GVariables.show_name,GVariables.show_nameN);
				
				Log.d("Outgoing", " > " + ccCountryISO + "-" + ownISO);
				
				if ( ccCountry != null && ccCountry.length() != 0 ) {
			
					if ( ! GVariables.onlyInternational || ( ! ccCountryISO.equals(ownISO))) {
						
							String txtMsg = ccCountry;

							if ( GVariables.show_time ) {
							
								String ccLocalTime = GVariables.cc.getLocalTime(ccCountryISO, ownISO);							
								if ( ! ccLocalTime.equals("") ) { txtMsg = txtMsg + "|" + ccLocalTime; }
							}
							// Log.w("Outgoing", " > DisplayToast");

							GVariables.DisplayToast(context,ccCountryISO,txtMsg,tposition,tsec,map);
					} // if
				}
			} // if
		} // if
    } // if
  }   // onReceive
  	
} 