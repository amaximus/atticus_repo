package com.atticus.CallMap;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
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
	public static String cCountry = "";
	public static Boolean def_app_enabled = false;
	
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
				Integer NANP = Integer.parseInt(s.substring(1,4));
				switch ( NANP ) { // check for Canada
					case 403:	// Alberta
					case 587:
					case 780:
					case 825:
					case 236:	// British Columbia
					case 250:
					case 604:
					case 672:
					case 778:
					case 204:	// Manitoba
					case 431:
					case 506:	// New Brunswick
					case 226:	// Ontario
					case 249:
					case 289:
					case 343:
					case 365:
					case 416:
					case 437:
					case 519:
					case 613:
					case 647:
					case 705:
					case 807:
					case 905:
					case 418:	// Quebec
					case 438:
					case 450:
					case 514:
					case 579:
					case 581:
					case 819:
					case 873:
					case 306:	// Saskatchewan
					case 639:
					case 709:	// Newfoundland and Labrador
					case 867:	// Yukon, Nunavut, NW Territories
					case 902:	// Nova Scotia, Prince Edward Island
						//canada = true;
						break;
				}
				prefix = 1000 + NANP;
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
    public static void DisplayToast(final Context context, Integer countryc, String countryn) {
    	
    	Integer ToastDelay = 2500;	// delay for toast show
    	
    	LayoutInflater inflater = LayoutInflater.from(context);
    	View layout = inflater.inflate(R.layout.toast_layout, null);
    	
    	ImageView image = (ImageView) layout.findViewById(R.id.image);
		//image.setMaxHeight(40);
		// image.setMaxWidth(40);
    	image.setImageResource(getImageId(context, countryc));

    	TextView text = (TextView) layout.findViewById(R.id.text);
    	if ( cCountry != "" ) { countryn = cCountry; }
    	text.setText(countryn);

    	toast = new Toast(context);
    	toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, toast_position);
    	toast.setView(layout);
    	
    	// Toast.makeText(context, Boolean.toString(callState),Toast.LENGTH_SHORT).show();
    	
    	final Handler handler = new Handler();	// wait 2000ms
    	handler.postDelayed(new Runnable() {    		
    	  @Override
    	  public void run() {
    		 new CountDownTimer(toast_sec*1000, 1000) {
          	    	public void onTick(long millisUntilFinished) { 
          	    		// Toast.makeText(context, Boolean.toString(callState),Toast.LENGTH_SHORT).show();
          	    		if ( toast != null ) { toast.show(); } }
          	    	public void onFinish() { /* toast.show(); */  }
          	 }.start();
    	  }   // run
    	}, ToastDelay);  // Handler
    }
    
    public static Integer getImageId(Context context, Integer imageNr) {
        Integer res = context.getResources().getIdentifier("drawable/image" + Integer.toString(imageNr), "drawable",context.getPackageName());
        // Log.v("getImageID",Integer.toString(imageNr) + "=> resourceID: " + Integer.toString(res));

        if ( res == 0) {				// no valid resource found
        		if ( imageNr > 1000 ) {	// North American Country Plan
        			if ( GVariables.canada ) {
        				res = context.getResources().getIdentifier("drawable/canada","drawable",context.getPackageName());
        				GVariables.cCountry = "Canada";
        		        // Log.v("CallMap country","Canada");
        			} else {
        				res = context.getResources().getIdentifier("drawable/usa","drawable",context.getPackageName());
        				GVariables.cCountry = "United States of America";
        			}
        		}
        }
        return res;
    }
}