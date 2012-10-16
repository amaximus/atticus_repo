package com.atticus.CallMap;

import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
//import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class CallMap extends Activity {
	
	//// private Integer cCountryCode = 0;	// call's country prefix
	//// private static String cCountry ="";		// call's country name
	
	//private Integer def_toast_position = 75;
	//private Integer def_toast_sec = 8;
	//private Boolean def_app_enabled = false;
	//private Boolean def_only_international = false;

	private Integer WHITE = 0xFFFFFFFF;
	private Integer GREY  = 0xFF736F6E;
	
	static private Context appcontext;
		    
	@Override 
    public void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
	       setContentView(R.layout.activity_main);
	       
           GVariables.canada = false;
           appcontext = this;
           
           // Log.v(getClass().getSimpleName(),"own ISOmcc: " + ownCountryISO);
           
           GVariables.appcontext = getApplicationContext();
           SharedPreferences CallMapSettings = getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
           GVariables.app_enabled = CallMapSettings.getBoolean("app_enabled", GVariables.def_app_enabled);
           GVariables.toast_sec = CallMapSettings.getInt("toast_sec", GVariables.def_toast_sec);
           GVariables.toast_position = CallMapSettings.getInt("toast_position", GVariables.def_toast_position);
           GVariables.onlyInternational = CallMapSettings.getBoolean("only_international", GVariables.def_only_international);
                      
           final EditText et1 = (EditText) findViewById(R.id.edtimeout);
           et1.setText(Integer.toString(GVariables.toast_sec));
           et1.setFocusableInTouchMode(GVariables.app_enabled);
		   et1.setFocusable(GVariables.app_enabled);
   	       et1.setOnFocusChangeListener(new OnFocusChangeListener(){
   	    	   @Override
   	    	   public void onFocusChange(View v,boolean hasFocus){
   	    		   	   String s = et1.getText().toString();
   	    		   	   if ( s != null && s.length() != 0 ) { GVariables.toast_sec = Integer.parseInt(s); }
   	    		   	   else {
   	    		   		   // GVariables.toast_sec = def_toast_sec;
   	    		   		   Toast.makeText(GVariables.appcontext,"Empty field reset to previous value",Toast.LENGTH_SHORT).show();
   	    		   		   et1.setText(Integer.toString(GVariables.toast_sec));
   	    		   	   }
   	    		   	   //Log.v("CallMapSetting_position","|" + Integer.toString(GVariables.toast_sec) + "|");

   	    	   }  // onFocusCange
   	       });
   	       
           final EditText et2 = (EditText) findViewById(R.id.edposition);
           et2.setText(Integer.toString(GVariables.toast_position));
           et2.setFocusableInTouchMode(GVariables.app_enabled);
		   et2.setFocusable(GVariables.app_enabled);
   	       et2.setOnFocusChangeListener(new OnFocusChangeListener(){
   	    	   @Override
   	    	   public void onFocusChange(View v,boolean hasFocus){
   	    		   	   String s = et2.getText().toString();
   	    		   	   if ( s != null && s.length() != 0 ) { GVariables.toast_position = Integer.parseInt(s); }
   	    		   	   else { 
   	    		   		   //GVariables.toast_position = def_toast_position;
   	    		   		   Toast.makeText(GVariables.appcontext,"Empty field reset to previous value",Toast.LENGTH_SHORT).show();
   	    		   		   et2.setText(Integer.toString(GVariables.toast_position));
   	    		   	   }
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
	   	    		  //Log.v("CheckBox",Boolean.toString(GVariables.onlyInternational));

	    	   }
	       });

	       appcontext = getApplicationContext();
	       final Intent service = new Intent(appcontext, CallMapService.class);

  	       ToggleButton tb = (ToggleButton) findViewById(R.id.toggleButton1);
  	       tb.setChecked(GVariables.app_enabled);
   	       tb.setOnClickListener(new OnClickListener() {
   	    	   @Override
   	    	   public void onClick(View v) {
   	    		   if ( GVariables.app_enabled ) {
   	    			   GVariables.app_enabled = false;
   	    			   
   	    			   appcontext.stopService(service);

   	    	           // tm.listen(null, PhoneStateListener.LISTEN_CALL_STATE);
   	    			   
   	    			   //Log.v(getClass().getSimpleName(),"OFF");
   	    			   
   	    			   tv3.setTextColor(GREY);
   	    			   tv4.setTextColor(GREY);
   	    			   tv5.setTextColor(GREY);
   	    			   tv6.setTextColor(GREY);
   	    			   tv7.setTextColor(GREY);
   	    			   tv8.setTextColor(GREY);
   	    		   } else {
   	    			   GVariables.app_enabled = true;
   	    			   
   	    			   appcontext.startService(service);

   	    	           // tm.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
   	    	           
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
	    editor.putString("own_ISO", GVariables.ownCountryISO);


	    // Commit the edits!
	    editor.commit();
	}
	
    public Boolean getOptEnabled() {
    	SharedPreferences CallMapSettings = getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
        return CallMapSettings.getBoolean("app_enabled", GVariables.def_app_enabled);
    	
    }
}

