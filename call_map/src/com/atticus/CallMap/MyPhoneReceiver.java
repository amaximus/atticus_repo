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
	  
	Integer tposition, tsec;
	Boolean international;
	Integer myPrefix = 0;
	Country_Code phrcc;
	
    SharedPreferences CallMapSettings = context.getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
    GVariables.app_enabled = CallMapSettings.getBoolean("app_enabled", GVariables.def_app_enabled);
	international = CallMapSettings.getBoolean("only_international", GVariables.def_only_international);
	tposition = CallMapSettings.getInt("toast_position", GVariables.def_toast_position);
	tsec = CallMapSettings.getInt("toast_sec", GVariables.def_toast_sec);
	myPrefix = CallMapSettings.getInt("my_prefix", 0);
	GVariables.show_name = CallMapSettings.getBoolean("show_name", GVariables.def_show_name);
    GVariables.show_nameN = CallMapSettings.getBoolean("show_nameN", GVariables.def_show_nameN);
    GVariables.show_map = CallMapSettings.getBoolean("show_map", GVariables.def_show_map);
    GVariables.show_time = CallMapSettings.getBoolean("show_time", GVariables.def_show_time);

	/* TelephonyManager tm;
	tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
    assert(tm != null);
	String myPhoneNumber = tm.getLine1Number();
	if ( myPhoneNumber != null && myPhoneNumber.length() != 0 ) {
		   myPrefix = GVariables.country_prefix(myPhoneNumber);
	} */
    
	//Log.d("Outgoing", " > " + Boolean.toString(GVariables.onlyInternational));

	if ( GVariables.app_enabled) {
		
		Bundle extras = intent.getExtras();
		if (extras != null) {
			
			// CallMap cmap = new CallMap();
			String outNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
      
			//Log.d("Outgoing", " > " + outNumber);

			if ( outNumber != null && outNumber.length() != 0 ) {
				Integer ccCode = GVariables.country_prefix(outNumber);
           		
        		if ( ccCode != 0 ) { // no code found or
        			phrcc = new Country_Code(ccCode, myPrefix);
        			
        			//Log.d("MyPhoneReceiver", Boolean.toString(international) + "|" + Integer.toString(ccCode) + "|" + Integer.toString(myPrefix));

        			if ( phrcc.hasData () ) {
           				if ( ! international || ( myPrefix != ccCode )) {
        					phrcc.DisplayToast(context,tposition, tsec, GVariables.show_name, GVariables.show_nameN,GVariables.show_map, GVariables.show_time);
        				}   // if
        			} // if hasData
        		}  // if cCountryPrefix
			} // if
		} // if
    } // if
  }   // onReceive
  	
} 