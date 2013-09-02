package com.atticus.CallMap;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
//import android.util.Log;

public class CallMapService extends Service {
	
	TelephonyManager tm;
	// String ownISO;
	Integer tposition, tsec;
	Boolean svcState;
	Boolean international;
	Integer myPrefix = 0;
	static Country_Code cc = null;
	
	@Override
	public void onCreate() {

		tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
        assert(tm != null);
  	   	/* String myPhoneNumber = tm.getLine1Number();
  	   	if ( myPhoneNumber != null && myPhoneNumber.length() != 0 ) {
 		   myPrefix = GVariables.country_prefix(myPhoneNumber);
  	   	} */
        
	    SharedPreferences CallMapSettings = getApplicationContext().getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
	    svcState = CallMapSettings.getBoolean("app_enabled", GVariables.def_app_enabled);
	    international = CallMapSettings.getBoolean("onlyInternational", GVariables.def_only_international);
	    myPrefix = CallMapSettings.getInt("my_prefix", 0);
	}

	@Override
	public void onDestroy() {
		
			svcState = false;
			//Log.d("PhoneStateListener2","state: " + Boolean.toString(svcState));
			
			if ( tm != null ) { tm.listen(null, PhoneStateListener.LISTEN_CALL_STATE); }
			
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startid) {
		
	    SharedPreferences CallMapSettings = getApplicationContext().getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
	    GVariables.app_enabled = CallMapSettings.getBoolean("app_enabled", GVariables.def_app_enabled);
	    
	    GVariables.onlyInternational = CallMapSettings.getBoolean("only_international", GVariables.def_only_international);
	    //ownISO = CallMapSettings.getString("own_ISO", "");
	    tposition = CallMapSettings.getInt("toast_position", GVariables.def_toast_position);
	    tsec = CallMapSettings.getInt("toast_sec", GVariables.def_toast_sec);
	    GVariables.show_name = CallMapSettings.getBoolean("show_name", GVariables.def_show_name);
	    GVariables.show_nameN = CallMapSettings.getBoolean("show_nameN", GVariables.def_show_nameN);
	    GVariables.show_map = CallMapSettings.getBoolean("show_map", GVariables.def_show_map);
	    GVariables.show_time = CallMapSettings.getBoolean("show_time", GVariables.def_show_time);
	        
		tm.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
		svcState = true;
		//Log.d("PhoneStateListener3","state: " + Boolean.toString(svcState));
		
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private final PhoneStateListener phoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
        	
        	Integer cCountryPrefix = 0;	// call's country prefix
        	//String  cCountry = null;	// call's country name
        	
            SharedPreferences CallMapSettings = getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
            GVariables.app_enabled = CallMapSettings.getBoolean("app_enabled", GVariables.def_app_enabled);
            international = CallMapSettings.getBoolean("only_international", GVariables.def_only_international);
        	        	
        	//Log.d("PhoneStateListener1","state: " + Integer.toString(state) + " + " + Boolean.toString(GVariables.app_enabled));

            switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
            		
            		if ( cc != null ) { cc.cancelToast(); }
            		//else { Log.d("PhoneStateListener6","idle null cc"); }
            		break;
            case TelephonyManager.CALL_STATE_RINGING:
            	
                //Log.d("PhoneStateListener4","svcState: " + Boolean.toString(svcState));
                
            	if ( svcState ) {
                	
            		cCountryPrefix = GVariables.country_prefix(incomingNumber);
    				//Log.d("Ringing","incoming call from: " + incomingNumber + " (" + Integer.toString(cCountryPrefix) + ")");

            		if ( cCountryPrefix != 0 ) { // no code found or
            			cc = new Country_Code(cCountryPrefix, myPrefix);
            			
            			if ( cc.hasData () ) {
            				// Log.v("Ringing","incoming: " + Integer.toString(cCountryCode));

            		/* if ( cCountryCode != 0 ) {	// international incoming call
            			cCountry = cc.getcountry(cCountryCode, GVariables.show_name, GVariables.show_nameN);
            		} else { cCountry = cc.getcountry(ownISO, GVariables.show_name, GVariables.show_nameN); } */
                
                    // Log.d("Ringing","ownISO: " + ownISO);

            				if ( ! international || ( myPrefix != cCountryPrefix )) {
            					cc.DisplayToast(getApplicationContext(),tposition, tsec, GVariables.show_name, GVariables.show_nameN,GVariables.show_map, GVariables.show_time);
            				}   // if
            			} // if hasData
            		}  // if cCountryPrefix
            	}	// if svcState
            	break;
            }  // switch

        }	// onCallStateChanged
    };  // PhoneStateListener
    
    public Boolean getSvcState() {
    	return svcState;
    }
}