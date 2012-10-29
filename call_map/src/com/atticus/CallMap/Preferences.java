package com.atticus.CallMap;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

public class Preferences extends Activity {
	    
	Integer toast_sec = 0;
	Integer toast_position = 0;
	Boolean onlyInternational = false;
	Boolean show_name, show_nameN, show_time, show_map = true; 
		
	@Override 
    public void onCreate(Bundle savedInstanceState) {
		
		   super.onCreate(savedInstanceState);
	       setContentView(R.layout.activity_prefs);
	       
	       SharedPreferences CallMapSettings = getApplicationContext().getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
		   GVariables.toast_sec = CallMapSettings.getInt("toast_sec", GVariables.def_toast_sec);
		   GVariables.toast_position = CallMapSettings.getInt("toast_position", GVariables.def_toast_position);
		   GVariables.onlyInternational = CallMapSettings.getBoolean("only_international", GVariables.def_only_international);
		   GVariables.show_name = CallMapSettings.getBoolean("show_name", GVariables.def_show_name);
           GVariables.show_nameN = CallMapSettings.getBoolean("show_nameN", GVariables.def_show_nameN);
           GVariables.show_map = CallMapSettings.getBoolean("show_map", GVariables.def_show_map);
           GVariables.show_time = CallMapSettings.getBoolean("show_time", GVariables.def_show_time);
           toast_sec = GVariables.toast_sec;
           toast_position = GVariables.toast_position;
           onlyInternational = GVariables.onlyInternational;
           show_name = GVariables.show_name;
           show_nameN = GVariables.show_nameN;
           show_map = GVariables.show_map;
           show_time = GVariables.show_time;

		   final EditText et1 = (EditText) findViewById(R.id.edtimeout);
           et1.setText(Integer.toString(toast_sec));
   	       et1.setOnFocusChangeListener(new OnFocusChangeListener(){
   	    	   @Override
   	    	   public void onFocusChange(View v,boolean hasFocus){
   	    		   	   String s = et1.getText().toString();
   	    		   	   if ( s != null && s.length() != 0 ) { 
   	    		   		   if ( s.length() < 4 ) { toast_sec = GVariables.toast_sec = Integer.parseInt(s); }
   	    		   		   else { 
   	    		   			   Toast.makeText(getApplicationContext(),"Number too large; reset to previous value",Toast.LENGTH_SHORT).show();
    	    		   		   et1.setText(Integer.toString(toast_sec));
   	    		   		   }
   	    		   	   } else {
   	    		   		   // GVariables.toast_sec = def_toast_sec;
   	    		   		   Toast.makeText(getApplicationContext(),"Empty field; reset to previous value",Toast.LENGTH_SHORT).show();
   	    		   		   et1.setText(Integer.toString(GVariables.toast_sec));
   	    		   	   }
   	    		   	   //Log.v("CallMapSetting_position","|" + Integer.toString(GVariables.toast_sec) + "|");

   	    	   }  // onFocusCange
   	       });
   	       
           final EditText et2 = (EditText) findViewById(R.id.edposition);
           et2.setText(Integer.toString(toast_position));
   	       et2.setOnFocusChangeListener(new OnFocusChangeListener(){
  	    	   @Override
   	    	   public void onFocusChange(View v,boolean hasFocus){
   	    		   	   String s = et2.getText().toString();
   	    		   	   if ( s != null && s.length() != 0 ) { 
   	    		   		   if ( s.length() < 4 ) { toast_position = GVariables.toast_position = Integer.parseInt(s); }
   	    		   		   else {
   	    		   			   Toast.makeText(getApplicationContext(),"Number too large; reset to previous value",Toast.LENGTH_SHORT).show();
   	    		   			   et1.setText(Integer.toString(toast_position));
   	    		   		   }
   	    		   	   } else { 
   	    		   		   //GVariables.toast_position = def_toast_position;
   	    		   		   Toast.makeText(getApplicationContext(),"Empty field reset to previous value",Toast.LENGTH_SHORT).show();
   	    		   		   et2.setText(Integer.toString(toast_position));
   	    		   	   }
   	    		   // Log.v("CallMapSetting_position",Integer.toString(GVariables.toast_position));

   	    	   }  // onFocus
   	       });
   	       
	       final CheckBox cb1 = (CheckBox) findViewById(R.id.checkBox1);
  	       cb1.setChecked(onlyInternational);
	       cb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
 			   @Override
	    	   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	    		   	  if (buttonView.isChecked()) { onlyInternational = GVariables.onlyInternational = true; }
	    		   	  else { onlyInternational = GVariables.onlyInternational = false; }
	   	    		  //Log.v("CheckBox",Boolean.toString(GVariables.onlyInternational));
	    	   }
	       });
	       
  	       ToggleButton tb1 = (ToggleButton) findViewById(R.id.tnameEN);
  	       tb1.setChecked(show_name);
   	       tb1.setOnClickListener(new OnClickListener() {
  	    	   @Override
   	    	   public void onClick(View v) {
   	    		   if ( show_name ) { show_name = GVariables.show_name = false; }
   	    		   else { show_name = GVariables.show_name = true; }
   	    	   }
   	       });
   	       
 	       ToggleButton tb2 = (ToggleButton) findViewById(R.id.tnameNA);
  	       tb2.setChecked(show_nameN);
   	       tb2.setOnClickListener(new OnClickListener() {
  	    	   @Override
   	    	   public void onClick(View v) {
   	    		   if ( show_nameN ) { show_nameN = GVariables.show_nameN = false; }
   	    		   else { show_nameN = GVariables.show_nameN = true; }
   	    	   }
   	       });
   	       
 	       ToggleButton tb3 = (ToggleButton) findViewById(R.id.tmap);
  	       tb3.setChecked(show_map);
   	       tb3.setOnClickListener(new OnClickListener() {
  	    	   @Override
   	    	   public void onClick(View v) {
   	    		   if ( show_map ) { show_map = GVariables.show_map = false; }
   	    		   else { show_map = GVariables.show_map = true; }
   	    	   }
   	       });
   	       
 	       ToggleButton tb4 = (ToggleButton) findViewById(R.id.ttime);
  	       tb4.setChecked(show_time);
   	       tb4.setOnClickListener(new OnClickListener() {
  	    	   @Override
   	    	   public void onClick(View v) {
   	    		   if ( show_time ) { show_time = GVariables.show_time = false; }
   	    		   else { show_time = GVariables.show_time = true; }
   	    	   }
   	       });   
	}  // onCreate
    
	@Override
	protected void onStop(){
		super.onStop();
		
		SharedPreferences settings = getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
	    SharedPreferences.Editor editor = settings.edit();
	    editor.putBoolean("app_enabled", GVariables.app_enabled);
	    editor.putInt("toast_sec", toast_sec);
	    editor.putInt("toast_position", toast_position);
	    editor.putBoolean("only_international", onlyInternational);
	    editor.putBoolean("show_name", show_name);
	    editor.putBoolean("show_nameN", show_nameN);
	    editor.putBoolean("show_map", show_map);
	    editor.putBoolean("show_time", show_time);
	    editor.commit();
	    
	    // restart the service
	    // appcontext = getApplicationContext();
	    if ( GVariables.appcontext != null ) {
	    	final Intent service = new Intent(GVariables.appcontext, CallMapService.class);
	    	GVariables.appcontext.stopService(service);
	    	GVariables.appcontext.startService(service);
	    }
	}
	
    @Override
    public void onBackPressed() {
    
        super.onBackPressed();
        
		SharedPreferences settings = getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
	    SharedPreferences.Editor editor = settings.edit();
	    editor.putBoolean("app_enabled", GVariables.app_enabled);
	    editor.putInt("toast_sec", toast_sec);
	    editor.putInt("toast_position", toast_position);
	    editor.putBoolean("only_international", onlyInternational);
	    editor.putBoolean("show_name", show_name);
	    editor.putBoolean("show_nameN", show_nameN);
	    editor.putBoolean("show_map", show_map);
	    editor.putBoolean("show_time", show_time);
	    editor.commit();    
	    
	    // restart the service
	    // appcontext = getApplicationContext();
	    if ( GVariables.appcontext != null ) {
	    	final Intent service = new Intent(GVariables.appcontext, CallMapService.class);
	    	GVariables.appcontext.stopService(service);
	    	GVariables.appcontext.startService(service);
	    }
	}  
}

