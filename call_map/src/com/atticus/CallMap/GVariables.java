package com.atticus.CallMap;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//Global variables
public class GVariables {
	
	public static Country_Code cc;
	public static Toast toast;
	
	public static Boolean app_enabled;
	public static Integer toast_sec;
	public static Integer toast_position;
	public static Context appcontext;
	public static Boolean canada;
	public static String  ownCountryISO;		// own country ISO mcc code
	public static Boolean onlyInternational;
	// public static String cCountry = "";
	public static Boolean def_app_enabled = false;
	public static Boolean def_only_international = false;
	public static Integer def_toast_position = 35;
	public static Integer def_toast_sec = 8;
	
	public static Integer country_prefix(String s) {

		Boolean international = false;
		Integer prefix = 0;
		String s1 = "";
						
		if ( s.substring(0,1).equals("+") ) {
				international = true;
				// s.replaceFirst("^+", "");
				s1 = s.substring(1);
				s = s1;
		}
		if ( s.substring(0,2).equals("00") ) { 
			international = true;
			//s.replaceFirst("^00", "");
			s1 = s.substring(2);
			s = s1;
		}

		if ( international ) {			
			if (s.substring(0,1).equals("1") ) {	// North America Numbering Plan
				prefix = 1000 + Integer.parseInt(s.substring(1,4));
			} else if ( s.substring(0,1).equals("7") ) {	// Russia, Kazahstan
				prefix=7;
			} else {
				switch ( Integer.parseInt(s.substring(0,2)) ) {
				case 21:	// three-digit country codes
				case 22:
				case 23:
				case 24:
				case 25:
				case 26:
				case 29:
				case 35:
				case 37:
				case 38:
				case 42:
				case 50:
				case 59:
				case 67:
				case 68:
				case 69:
				case 80:
				case 85:
				case 87:
				case 88:
				case 96:
				case 97:
				case 99:
					prefix = Integer.parseInt(s.substring(0,3));
					break;
				default:	// two-digit country codes
					prefix = Integer.parseInt(s.substring(0,2));
					break;
				}
			}
		}
		
		return prefix;
	}
    
    /*
	 * countryc = international call code
	 * countryn = country name to display
	 */
    public static void DisplayToast(final Context context, String countryISO, String countryn, Integer tposition,final Integer tsec) {
    	
    	Integer ToastDelay = 2500;	// delay for toast show
    	
    	LayoutInflater inflater = LayoutInflater.from(context);
    	View layout = inflater.inflate(R.layout.toast_layout, null);
    	
    	ImageView image = (ImageView) layout.findViewById(R.id.image);
    	/* Integer resourceID = context.getResources().getIdentifier("drawable/imageHU.png", null,"com.atticus.call_map");
    	
    	if ( resourceID != 0 ) { image.setImageResource(resourceID); } */
    	
    	image.setImageResource(context.getResources().getIdentifier("image" + countryISO, "drawable",context.getPackageName()));

    	Log.v("Ringing","DisplayToast: " + context.getPackageName());
    	//Log.v("Ringing","DisplayToast: " + Integer.toString(resourceID));
    	
    	TextView text = (TextView) layout.findViewById(R.id.text);
    	// if ( cCountry != "" ) { countryn = cCountry; }
    	    	
    	if ( countryn != null && countryn.length() != 0 ) {
    			text.setText(countryn);
    	}

    	toast = new Toast(context);
    	toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, tposition);
    	toast.setView(layout);
    	    	
    	final Handler handler = new Handler();	// wait 2000ms
    	handler.postDelayed(new Runnable() {    		
    	  @Override
    	  public void run() {
    		 new CountDownTimer(tsec*1000, 1000) {
          	    	public void onTick(long millisUntilFinished) { 
          	    		// Toast.makeText(context, Boolean.toString(callState),Toast.LENGTH_SHORT).show();
          	    		if ( toast != null ) { toast.show(); } }
          	    	public void onFinish() { /* toast.show(); */  }
          	 }.start();
    	  }   // run
    	}, ToastDelay);  // Handler
    }
}