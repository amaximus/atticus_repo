package com.atticus.CallMap;

import android.app.Activity;
import android.app.Application;
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
	
	@Override
	public void onCreate() {

		tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
        assert(tm != null);
        
        GVariables.ownCountryISO = tm.getSimCountryIso().toUpperCase();
        
        cc = new Country_Code();
	}

	@Override
	public void onDestroy() {
           tm.listen(null, PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startid) {
		
		tm.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);

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
        	
        	String ownISO;
        	Integer cCountryCode = 0;	// call's country prefix
        	String  cCountry = null;	// call's country name
        	Integer tposition, tsec;
        	
        	Application cmapp = getApplication();
        	
        	// Log.v("PhoneStateListener"," state: " + Integer.toString(state));
        	
            switch (state) {
            
            case TelephonyManager.CALL_STATE_IDLE:
            		if ( GVariables.toast != null ) { GVariables.toast.cancel(); GVariables.toast = null; }
            		break;
            case TelephonyManager.CALL_STATE_RINGING:
            	
            	Context context = cmapp.getApplicationContext();
            	
            	SharedPreferences CallMapSettings = context.getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
                GVariables.app_enabled = CallMapSettings.getBoolean("app_enabled", GVariables.def_app_enabled);
                
            	if ( GVariables.app_enabled ) {
            		
                	GVariables.onlyInternational = CallMapSettings.getBoolean("only_international", GVariables.def_only_international);
                    ownISO = CallMapSettings.getString("own_ISO", "");
                    tposition = CallMapSettings.getInt("toast_position", GVariables.def_toast_position);
                	tsec = CallMapSettings.getInt("toast_sec", GVariables.def_toast_sec);
                	
            		cCountryCode = GVariables.country_prefix(incomingNumber);
            	            	
                    // Log.v("Ringing","incoming: " + Integer.toString(cCountryCode));

            		if ( cCountryCode != 0 ) {	// international incoming call
            			cCountry = cc.getcountry(cCountryCode);
            		} else { cCountry = cc.getcountry(ownISO); }
                
                    // Log.v("Ringing","onlyInternational: " + Boolean.toString(GVariables.onlyInternational));

            		if ( ! GVariables.onlyInternational || ( cCountry != cc.getcountry(ownISO))) {
            			if ( cCountry != null ) {
            				// Log.v("PhoneStateListener"," < " + Integer.toString(cCountryCode));

            				String txtMsg = cCountry;
            				String countryISO = cc.getISOmcc(cCountryCode);
            				// Log.v("PhoneStateListener"," < " + countryISO);

            				if ( countryISO != null && countryISO.length() != 0) {

            					String ccLocalTime = cc.getLocalTime(countryISO, ownISO);
                				Log.v("PhoneStateListener"," < " + ccLocalTime);

            					if ( ccLocalTime != "" ) { txtMsg = txtMsg + "\n" + ccLocalTime; }
            				}

            				GVariables.DisplayToast(getApplicationContext(), countryISO, cCountry,tposition, tsec);
            			}
            		}   // if
            	}  // if enabled
            	break;
            }  // switch

        }	// onCallStateChanged
    };  // PhoneStateListener
}