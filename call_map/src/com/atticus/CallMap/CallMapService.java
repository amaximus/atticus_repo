package com.atticus.CallMap;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class CallMapService extends Service {
	
	TelephonyManager tm;
	Country_Code cc;
	String ownISO;
	Integer tposition, tsec;
	Boolean svcState;
	
	@Override
	public void onCreate() {

		tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
        assert(tm != null);
        
        GVariables.ownCountryISO = tm.getSimCountryIso().toUpperCase();
	    SharedPreferences CallMapSettings = getApplicationContext().getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
	    svcState = CallMapSettings.getBoolean("app_enabled", GVariables.def_app_enabled);
        
        cc = new Country_Code();
        if ( GVariables.ownCountryISO.equals("US") || GVariables.ownCountryISO.equals("CA") ) {
     	   // get my own number
     	   String myPhoneNumber = tm.getLine1Number();
     	   // NANP
     	   Country_Code cc = new Country_Code();
     	   GVariables.ownCountryISO =   cc.getISOmcc(1000 + Integer.parseInt(myPhoneNumber.substring(1,4)));
     	   if ( GVariables.ownCountryISO == null ) { GVariables.ownCountryISO = ""; }
     	   // cCountry = cc.getcountry(cCountryCode, GVariables.show_name, GVariables.show_nameN);
        }
	}

	@Override
	public void onDestroy() {
		
			svcState = false;
			Log.d("PhoneStateListener2","state: " + Boolean.toString(svcState));

           tm.listen(null, PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startid) {
		
	    SharedPreferences CallMapSettings = getApplicationContext().getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
	    GVariables.app_enabled = CallMapSettings.getBoolean("app_enabled", GVariables.def_app_enabled);
	    
	    GVariables.onlyInternational = CallMapSettings.getBoolean("only_international", GVariables.def_only_international);
	    ownISO = CallMapSettings.getString("own_ISO", "");
	    tposition = CallMapSettings.getInt("toast_position", GVariables.def_toast_position);
	    tsec = CallMapSettings.getInt("toast_sec", GVariables.def_toast_sec);
	    GVariables.show_name = CallMapSettings.getBoolean("show_name", GVariables.def_show_name);
	    GVariables.show_nameN = CallMapSettings.getBoolean("show_nameN", GVariables.def_show_nameN);
	    GVariables.show_map = CallMapSettings.getBoolean("show_map", GVariables.def_show_map);
	    GVariables.show_time = CallMapSettings.getBoolean("show_time", GVariables.def_show_time);
	        
		tm.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
		svcState = true;
		Log.d("PhoneStateListener3","state: " + Boolean.toString(svcState));
		
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
        	
        	Integer cCountryCode = 0;	// call's country prefix
        	String  cCountry = null;	// call's country name
        	
            SharedPreferences CallMapSettings = getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
            GVariables.app_enabled = CallMapSettings.getBoolean("app_enabled", GVariables.def_app_enabled);
        	        	
        	Log.d("PhoneStateListener1","state: " + Integer.toString(state) + " + " + Boolean.toString(GVariables.app_enabled));

            switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
            		if ( GVariables.toast != null ) { 
            			GVariables.toast.cancel(); GVariables.toast = null;
            		}
            		break;
            case TelephonyManager.CALL_STATE_RINGING:
            	
                // Log.v("CallMapService","ownISO: " + ownISO);
                
            	if ( svcState ) {
                	
            		cCountryCode = GVariables.country_prefix(incomingNumber);
            	            	
                    // Log.v("Ringing","incoming: " + Integer.toString(cCountryCode));

            		if ( cCountryCode != 0 ) {	// international incoming call
            			cCountry = cc.getcountry(cCountryCode, GVariables.show_name, GVariables.show_nameN);
            		} else { cCountry = cc.getcountry(ownISO, GVariables.show_name, GVariables.show_nameN); }
                
                    //Log.d("Ringing","ownISO: " + ownISO);

            		if ( ! GVariables.onlyInternational || ( cCountry != cc.getcountry(ownISO, GVariables.show_name, GVariables.show_nameN))) {
            			if ( cCountry != null ) {
            				//Log.d("PhoneStateListener1"," < " + Integer.toString(cCountryCode));

            				String txtMsg = cCountry;
            				String countryISO = cc.getISOmcc(cCountryCode);
            				//Log.d("PhoneStateListener2"," < " + countryISO);

            				if ( countryISO != null && countryISO.length() != 0) {

            					if ( GVariables.show_time ) {
            						String ccLocalTime = cc.getLocalTime(countryISO, ownISO);
            						//Log.d("PhoneStateListener3"," < " + ccLocalTime);

            						if ( ! ccLocalTime.equals("") ) { txtMsg = txtMsg + "|" + ccLocalTime; }
            					}
            				}

            				//Log.d("PhoneStateListener4"," < " + txtMsg);

            				GVariables.DisplayToast(getApplicationContext(), countryISO, txtMsg,tposition, tsec, GVariables.show_map);
            			}
            		}   // if
            	}  // if enabled
            	break;
            }  // switch

        }	// onCallStateChanged
    };  // PhoneStateListener
    
    public Boolean getSvcState() {
    	return svcState;
    }
}