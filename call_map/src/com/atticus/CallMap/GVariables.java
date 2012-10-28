package com.atticus.CallMap;

import android.content.Context;

//Global variables
public class GVariables {
	
	public static Boolean app_enabled;
	public static Boolean show_name;
	public static Boolean show_nameN;
	public static Boolean show_map;
	public static Boolean show_time;

	public static Integer toast_sec;
	public static Integer toast_position;
	public static Context appcontext;
	// public static Boolean canada;
	// public static String  ownCountryISO;		// own country ISO mcc code
	public static Boolean onlyInternational;
	
	// default values
	public static Boolean def_app_enabled = false;
	public static Boolean def_only_international = false;
	public static Integer def_toast_position = 35;
	public static Integer def_toast_sec = 8;
	public static Boolean def_show_name = true;
	public static Boolean def_show_nameN = true;
	public static Boolean def_show_map = true;
	public static Boolean def_show_time = true;
		
	public static Integer country_prefix(String s) {

		Boolean international = false;
		Integer prefix = -1;
		String s1 = "";
		
		if ( s != null && s.length() != 0 ) {
			if ( s.substring(0,1).equals("+") ) {
				international = true;
				// s.replaceFirst("^+", "");
				s1 = s.substring(1);
				s = s1;
			}
			if ( s.length() >1 && s.substring(0,2).equals("00") ) { 
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
				}	// switch
				}	// if
			} else { prefix = 0; }	// if international
		}

		return prefix;
	}
    
}