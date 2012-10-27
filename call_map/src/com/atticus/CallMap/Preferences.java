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
	    
	@Override 
    public void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
	       setContentView(R.layout.activity_prefs);
	       
	       final EditText et1 = (EditText) findViewById(R.id.edtimeout);
           et1.setText(Integer.toString(GVariables.toast_sec));
   	       et1.setOnFocusChangeListener(new OnFocusChangeListener(){
   	    	   @Override
   	    	   public void onFocusChange(View v,boolean hasFocus){
   	    		   	   String s = et1.getText().toString();
   	    		   	   if ( s != null && s.length() != 0 ) { 
   	    		   		   if ( s.length() < 4 ) { GVariables.toast_sec = Integer.parseInt(s); }
   	    		   		   else { 
   	    		   			   Toast.makeText(GVariables.appcontext,"Number too large; reset to previous value",Toast.LENGTH_SHORT).show();
    	    		   		   et1.setText(Integer.toString(GVariables.toast_sec));
   	    		   		   }
   	    		   	   } else {
   	    		   		   // GVariables.toast_sec = def_toast_sec;
   	    		   		   Toast.makeText(GVariables.appcontext,"Empty field; reset to previous value",Toast.LENGTH_SHORT).show();
   	    		   		   et1.setText(Integer.toString(GVariables.toast_sec));
   	    		   	   }
   	    		   	   //Log.v("CallMapSetting_position","|" + Integer.toString(GVariables.toast_sec) + "|");

   	    	   }  // onFocusCange
   	       });
   	       
           final EditText et2 = (EditText) findViewById(R.id.edposition);
           et2.setText(Integer.toString(GVariables.toast_position));
   	       et2.setOnFocusChangeListener(new OnFocusChangeListener(){
  	    	   @Override
   	    	   public void onFocusChange(View v,boolean hasFocus){
   	    		   	   String s = et2.getText().toString();
   	    		   	   if ( s != null && s.length() != 0 ) { 
   	    		   		   if ( s.length() < 4 ) { GVariables.toast_position = Integer.parseInt(s); }
   	    		   		   else {
   	    		   			   Toast.makeText(GVariables.appcontext,"Number too large; reset to previous value",Toast.LENGTH_SHORT).show();
   	    		   			   et1.setText(Integer.toString(GVariables.toast_sec));
   	    		   		   }
   	    		   	   } else { 
   	    		   		   //GVariables.toast_position = def_toast_position;
   	    		   		   Toast.makeText(GVariables.appcontext,"Empty field reset to previous value",Toast.LENGTH_SHORT).show();
   	    		   		   et2.setText(Integer.toString(GVariables.toast_position));
   	    		   	   }
   	    		   // Log.v("CallMapSetting_position",Integer.toString(GVariables.toast_position));

   	    	   }  // onFocus
   	       });
   	       
	       final CheckBox cb1 = (CheckBox) findViewById(R.id.checkBox1);
  	       cb1.setChecked(GVariables.onlyInternational);
	       cb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
 			   @Override
	    	   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	    		   	  if (buttonView.isChecked()) { GVariables.onlyInternational = true; }
	    		   	  else { GVariables.onlyInternational = false; }
	   	    		  //Log.v("CheckBox",Boolean.toString(GVariables.onlyInternational));

	    	   }
	       });
	       
  	       ToggleButton tb1 = (ToggleButton) findViewById(R.id.tnameEN);
  	       tb1.setChecked(GVariables.show_name);
   	       tb1.setOnClickListener(new OnClickListener() {
  	    	   @Override
   	    	   public void onClick(View v) {
   	    		   if ( GVariables.show_name ) { GVariables.show_name = false; }
   	    		   else { GVariables.show_name = true; }
   	    	   }
   	       });
   	       
 	       ToggleButton tb2 = (ToggleButton) findViewById(R.id.tnameNA);
  	       tb2.setChecked(GVariables.show_nameN);
   	       tb2.setOnClickListener(new OnClickListener() {
  	    	   @Override
   	    	   public void onClick(View v) {
   	    		   if ( GVariables.show_nameN ) { GVariables.show_nameN = false; }
   	    		   else { GVariables.show_nameN = true; }
   	    	   }
   	       });
   	       
 	       ToggleButton tb3 = (ToggleButton) findViewById(R.id.tmap);
  	       tb3.setChecked(GVariables.show_map);
   	       tb3.setOnClickListener(new OnClickListener() {
  	    	   @Override
   	    	   public void onClick(View v) {
   	    		   if ( GVariables.show_map ) { GVariables.show_map = false; }
   	    		   else { GVariables.show_map = true; }
   	    	   }
   	       });
   	       
 	       ToggleButton tb4 = (ToggleButton) findViewById(R.id.ttime);
  	       tb4.setChecked(GVariables.show_time);
   	       tb4.setOnClickListener(new OnClickListener() {
  	    	   @Override
   	    	   public void onClick(View v) {
   	    		   if ( GVariables.show_time ) { GVariables.show_time = false; }
   	    		   else { GVariables.show_time = true; }
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
	    //editor.putString("own_ISO", GVariables.ownCountryISO);
	    editor.putBoolean("show_name", GVariables.show_name);
	    editor.putBoolean("show_nameN", GVariables.show_nameN);
	    editor.putBoolean("show_map", GVariables.show_map);
	    editor.putBoolean("show_time", GVariables.show_time);
	    
	    // Commit the edits!
	    editor.commit();
	    
	    // restart the service
	    // appcontext = getApplicationContext();
	    final Intent service = new Intent(GVariables.appcontext, CallMapService.class);
		GVariables.appcontext.stopService(service);
		GVariables.appcontext.startService(service);
	}
	
    @Override
    public void onBackPressed() {
    
        super.onBackPressed();
        
		SharedPreferences settings = getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
	    SharedPreferences.Editor editor = settings.edit();
	    editor.putBoolean("app_enabled", GVariables.app_enabled);
	    editor.putInt("toast_sec", GVariables.toast_sec);
	    editor.putInt("toast_position", GVariables.toast_position);
	    editor.putBoolean("only_international", GVariables.onlyInternational);
	    // editor.putString("own_ISO", GVariables.ownCountryISO);
	    editor.putBoolean("show_name", GVariables.show_name);
	    editor.putBoolean("show_nameN", GVariables.show_nameN);
	    editor.putBoolean("show_map", GVariables.show_map);
	    editor.putBoolean("show_time", GVariables.show_time);
	    
	    // Commit the edits!
	    editor.commit();    
	    
	    // restart the service
	    // appcontext = getApplicationContext();
	    final Intent service = new Intent(GVariables.appcontext, CallMapService.class);
		GVariables.appcontext.stopService(service);
		GVariables.appcontext.startService(service);
	} 
    
}

