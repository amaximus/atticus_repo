package com.atticus.call_map;

import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
// import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class CallMap extends Activity {
	
	private Integer cCountryCode = 0;	// call's country prefix
	private static String cCountry ="";		// call's country name
	
	private Integer def_toast_position = 75;
	private Integer def_toast_sec = 8;
	private Boolean def_app_enabled = false;
	private Boolean def_only_international = false;

	private Integer WHITE = 0xFFFFFFFF;
	private Integer GREY  = 0xFF736F6E;
		    
	@Override 
    public void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
	       setContentView(R.layout.activity_main);
	       
	       //register with the telephony mgr to make sure we can read call state
           final TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
           assert(tm != null); 
           
           GVariables.ownCountryISO = tm.getSimCountryIso().toUpperCase();
           GVariables.canada = false;
           
           // Log.v(getClass().getSimpleName(),"own ISOmcc: " + ownCountryISO);
      
           GVariables.cc = new Country_Code();
           GVariables.cc.put(0,GVariables.cc.getcountry(GVariables.ownCountryISO));	// non-international calls
           
           GVariables.appcontext = getApplicationContext();
           SharedPreferences CallMapSettings = getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
           GVariables.app_enabled = CallMapSettings.getBoolean("app_enabled", def_app_enabled);
           GVariables.toast_sec = CallMapSettings.getInt("toast_sec", def_toast_sec);
           GVariables.toast_position = CallMapSettings.getInt("toast_position", def_toast_position);
           GVariables.onlyInternational = CallMapSettings.getBoolean("only_international", def_only_international);

           // Needed to work after reboot 
           if ( GVariables.app_enabled ) {
        	   tm.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
           }
           
           final EditText et1 = (EditText) findViewById(R.id.edtimeout);
           et1.setText(Integer.toString(GVariables.toast_sec));
           et1.setFocusableInTouchMode(GVariables.app_enabled);
		   et1.setFocusable(GVariables.app_enabled);
   	       et1.setOnFocusChangeListener(new OnFocusChangeListener(){
   	    	   @Override
   	    	   public void onFocusChange(View v,boolean hasFocus){
   	    			   GVariables.toast_sec = Integer.parseInt(et1.getText().toString());
   	    	   }  // onFocusCange
   	       });
   	       
           final EditText et2 = (EditText) findViewById(R.id.edposition);
           et2.setText(Integer.toString(GVariables.toast_position));
           et2.setFocusableInTouchMode(GVariables.app_enabled);
		   et2.setFocusable(GVariables.app_enabled);
   	       et2.setOnFocusChangeListener(new OnFocusChangeListener(){
   	    	   @Override
   	    	   public void onFocusChange(View v,boolean hasFocus){
   	    			   GVariables.toast_position = Integer.parseInt(et2.getText().toString());   	    			   
   	    		   // Log.v("CallMapSetting_position",Integer.toString(GVariables.toast_position));

   	    	   }  // onFocus
   	       });
   	       
           final TextView tv3 = (TextView) findViewById(R.id.textView3);
           final TextView tv4 = (TextView) findViewById(R.id.textView4);
           final TextView tv5 = (TextView) findViewById(R.id.textView5);
           final TextView tv6 = (TextView) findViewById(R.id.textView6);
           final TextView tv7 = (TextView) findViewById(R.id.textView7);
           final TextView tv8 = (TextView) findViewById(R.id.textView8);
           
	       final CheckBox cb1 = (CheckBox) findViewById(R.id.checkBox1);
	       cb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
 			   @Override
	    	   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	    		   	  if (buttonView.isChecked()) { GVariables.onlyInternational = true; }
	    		   	  else { GVariables.onlyInternational = false; }
	   	    		  Log.v("CheckBox",Boolean.toString(GVariables.onlyInternational));

	    	   }
	       });

  	       ToggleButton tb = (ToggleButton) findViewById(R.id.toggleButton1);
  	       tb.setChecked(GVariables.app_enabled);
   	       tb.setOnClickListener(new OnClickListener() {
   	    	   @Override
   	    	   public void onClick(View v) {
   	    		   if ( GVariables.app_enabled ) {
   	    			   GVariables.app_enabled = false;
   	    			   
   	    	           tm.listen(null, PhoneStateListener.LISTEN_CALL_STATE);
   	    			   
   	    			   //Log.v(getClass().getSimpleName(),"OFF");
   	    			   
   	    			   tv3.setTextColor(GREY);
   	    			   tv4.setTextColor(GREY);
   	    			   tv5.setTextColor(GREY);
   	    			   tv6.setTextColor(GREY);
   	    			   tv7.setTextColor(GREY);
   	    			   tv8.setTextColor(GREY);
   	    		   } else {
   	    			   GVariables.app_enabled = true;

   	    	           tm.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
   	    	           
   	    			   //Log.v(getClass().getSimpleName(),"ON");
   	    	           
   	    			   tv3.setTextColor(WHITE);
   	    			   tv4.setTextColor(WHITE);
   	    			   tv5.setTextColor(WHITE);
   	    			   tv6.setTextColor(WHITE);
   	    			   tv7.setTextColor(WHITE);
   	    			   tv8.setTextColor(WHITE);
   	    		   }
   	    		   et1.setFocusableInTouchMode(GVariables.app_enabled);
   	    		   et1.setFocusable(GVariables.app_enabled);
   	    		   et2.setFocusableInTouchMode(GVariables.app_enabled);
   	    		   et2.setFocusable(GVariables.app_enabled);
   	    		   cb1.setEnabled(GVariables.app_enabled);

   	    	   }
   	       });	       
	}  // onCreate
	
	@Override
	protected void onStop(){
		super.onStop();
		
		SharedPreferences settings = getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
	    SharedPreferences.Editor editor = settings.edit();
	    editor.putBoolean("app_enabled", GVariables.app_enabled);
	    editor.putInt("toast_sec", GVariables.toast_sec);
	    editor.putInt("toast_position", GVariables.toast_position);
	    editor.putBoolean("only_international", GVariables.onlyInternational);

	    // Commit the edits!
	    editor.commit();
	}
  
    private final PhoneStateListener phoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
        	
        	Log.v("PhoneStateListener"," state: " + Integer.toString(state));
        	
            switch (state) {
            
            case TelephonyManager.CALL_STATE_IDLE:
            		if ( GVariables.toast != null ) { GVariables.toast.cancel(); GVariables.toast = null; }
            		break;
            case TelephonyManager.CALL_STATE_RINGING:
            	
            	if ( GVariables.app_enabled ) {
            		cCountry = null;
            		cCountryCode = GVariables.country_prefix(incomingNumber);
            	            	
            		if ( cCountryCode != 0 ) {	// international incoming call
            			cCountry = GVariables.cc.getcountry(cCountryCode);
            		} else { cCountry = GVariables.cc.getcountry(GVariables.ownCountryISO); }
                
                    // Log.v("Ringing","onlyInternational: " + Boolean.toString(GVariables.onlyInternational));

            		if ( ! GVariables.onlyInternational || ( cCountry != GVariables.cc.getcountry(GVariables.ownCountryISO))) {
            			if ( cCountry != null ) {
            				//Log.v("PhoneStateListener"," < " + cCountry);
            				GVariables.DisplayToast(getApplicationContext(), cCountryCode, cCountry);
            			}
            		}   // if
            	}  // if enabled
            	break;
            }  // switch

        }	// onCallStateChanged
    };  // PhoneStateListener
}

