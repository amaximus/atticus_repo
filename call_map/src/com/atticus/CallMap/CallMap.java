package com.atticus.CallMap;

import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.util.Log;

public class CallMap extends Activity {
	
	static private Context appcontext;
	private Integer myPrefix = 0;
	Boolean prefsOnCreate = false;
	Boolean prefsShown = false;
		    
	@Override 
    public void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
	       setContentView(R.layout.activity_main);
	       
	       prefsOnCreate = true;
           appcontext = this;
           
           TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
           assert(tm != null);
           String myPhoneNumber = tm.getLine1Number();
     	   if ( myPhoneNumber != null && myPhoneNumber.length() != 0 ) {
    		   myPrefix = GVariables.country_prefix(myPhoneNumber);
               //Log.d(getClass().getSimpleName(),"myNumber: " + myPhoneNumber + " (" + Integer.toString(myPrefix) + ")");
     	   } 
           //GVariables.ownCountryISO = tm.getSimCountryIso().toUpperCase();
     	   
           GVariables.appcontext = getApplicationContext();
           SharedPreferences CallMapSettings = getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
           
     	   if ( myPrefix != 0 ) {
     		    SharedPreferences.Editor editor = CallMapSettings.edit();
     		    editor.putInt("my_prefix", myPrefix);
     		    editor.commit();
     	   }

           GVariables.app_enabled = CallMapSettings.getBoolean("app_enabled", GVariables.def_app_enabled);
           GVariables.toast_sec = CallMapSettings.getInt("toast_sec", GVariables.def_toast_sec);
           GVariables.toast_position = CallMapSettings.getInt("toast_position", GVariables.def_toast_position);
           GVariables.onlyInternational = CallMapSettings.getBoolean("only_international", GVariables.def_only_international);
           GVariables.show_name = CallMapSettings.getBoolean("show_name", GVariables.def_show_name);
           GVariables.show_nameN = CallMapSettings.getBoolean("show_nameN", GVariables.def_show_nameN);
           GVariables.show_map = CallMapSettings.getBoolean("show_map", GVariables.def_show_map);
           GVariables.show_time = CallMapSettings.getBoolean("show_time", GVariables.def_show_time);
           
   		   // Log.d("CallMap","onCreate: " + Boolean.toString(GVariables.app_enabled));
	       // appcontext = getApplicationContext();
  		   final Intent service = new Intent(appcontext, CallMapService.class);

	       ToggleButton tb1 = (ToggleButton) findViewById(R.id.toggleButton1);
  	       tb1.setChecked(GVariables.app_enabled);
   	       tb1.setOnClickListener(new OnClickListener() {
  	    	   @Override
   	    	   public void onClick(View v) {
  	    		   final Intent service = new Intent(appcontext, CallMapService.class);
   	    		   if ( GVariables.app_enabled ) {
   	    			   GVariables.app_enabled = false;
   	    			   GVariables.appcontext.stopService(service);
   	    		   } else { 
   	    			   GVariables.app_enabled = true;
   	    			   GVariables.appcontext.startService(service);
   	    		   }
   	    		   
   	    		   SharedPreferences settings = getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
   	    		   SharedPreferences.Editor editor = settings.edit();
   	    		   editor.putBoolean("app_enabled", GVariables.app_enabled);
   	    		   editor.putInt("my_prefix", myPrefix);
   			    
   	    		   // Commit the edits!
   	    		   editor.commit();
   	    		   
   	    		   // Log.d("CallMap",Boolean.toString(GVariables.app_enabled));
   	    	   }
   	       });
   	       
   	       if ( GVariables.app_enabled ) { GVariables.appcontext.startService(service); }
   	       
   	       //new Handler().postDelayed(new Runnable() { public void run() { openOptionsMenu(); } }, 3000);
   	       
	}  // onCreate
	
	@Override
	public void onResume() {
		
		final Intent service = new Intent(appcontext, CallMapService.class);

	    ToggleButton tb1 = (ToggleButton) findViewById(R.id.toggleButton1);
  	    tb1.setChecked(GVariables.app_enabled);
   	    tb1.setOnClickListener(new OnClickListener() {
   	    	@Override
   	    	public void onClick(View v) {
   	    		final Intent service = new Intent(appcontext, CallMapService.class);
   	    		if ( GVariables.app_enabled ) {
   	    			GVariables.app_enabled = false;
   	    			GVariables.appcontext.stopService(service);
   	    		} else { 
   	    			GVariables.app_enabled = true;
   	    			GVariables.appcontext.startService(service);
   	    		}
   	    		   
   	    		SharedPreferences settings = getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
   	    		SharedPreferences.Editor editor = settings.edit();
   	    		editor.putBoolean("app_enabled", GVariables.app_enabled);
   	    		editor.putInt("my_prefix", myPrefix);

   	    		// Commit the edits!
   	    		editor.commit();
   	    		   
   	    		//Log.d("CallMap",Boolean.toString(GVariables.app_enabled));
   	    	 }
   	    });
   	       
   	    if ( GVariables.app_enabled ) { GVariables.appcontext.startService(service); }
		//Log.d("CallMap","onResume: " + Boolean.toString(GVariables.app_enabled));
   	    
   	    new Handler().postDelayed(new Runnable() { 
   	    	public void run() { 
   	    		if ( prefsOnCreate ) {
   	    			openOptionsMenu();
   	   	   			prefsShown = true;
   	    			prefsOnCreate = false;
   	   			}
   	   		} 
   	   	}, 2000);

		super.onResume();

	}
	
	@Override
	public void onRestart() {
		
		final Intent service = new Intent(appcontext, CallMapService.class);

	    ToggleButton tb1 = (ToggleButton) findViewById(R.id.toggleButton1);
  	    tb1.setChecked(GVariables.app_enabled);
   	    tb1.setOnClickListener(new OnClickListener() {
   	    	@Override
   	    	public void onClick(View v) {
   	    		final Intent service = new Intent(appcontext, CallMapService.class);
   	    		if ( GVariables.app_enabled ) {
   	    			GVariables.app_enabled = false;
   	    			GVariables.appcontext.stopService(service);
   	    		} else { 
   	    			GVariables.app_enabled = true;
   	    			GVariables.appcontext.startService(service);
   	    		}
   	    		   
   	    		SharedPreferences settings = getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
   	    		SharedPreferences.Editor editor = settings.edit();
   	    		editor.putBoolean("app_enabled", GVariables.app_enabled);
   	    		editor.putInt("my_prefix", myPrefix);
   			    
   	    		// Commit the edits!
   	    		editor.commit();
   	    		   
   	    		//Log.d("CallMap",Boolean.toString(GVariables.app_enabled));
   	    	 }
   	    });
   	       
   	    if ( GVariables.app_enabled ) { GVariables.appcontext.startService(service); }
		//Log.d("CallMap","onRestart: " + Boolean.toString(GVariables.app_enabled));
		
		super.onRestart();

	}

	@Override
	public void onBackPressed() {
	        super.onBackPressed();
	        
	   		//Log.d("CallMap","back: " + Boolean.toString(prefsOnCreate) + "|" + Boolean.toString(prefsShown) );
	        
			if ( prefsOnCreate && ! prefsShown ) { prefsOnCreate = false; }
	}
	
	/* @Override
	protected void onStop(){
		super.onStop();
		
		SharedPreferences settings = getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
	    SharedPreferences.Editor editor = settings.edit();
	    editor.putBoolean("app_enabled", GVariables.app_enabled);
	    
	    // Commit the edits!
	    editor.commit();
	} */
	
	// Initiating Menu XML file
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.menu, menu);
        return true;
    }
    
    /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        { 
        case R.id.menu_preferences:
        	if ( GVariables.app_enabled ) { startActivity(new Intent(this, Preferences.class)); }
        	else { Toast.makeText(GVariables.appcontext,"Cannot set preferences while functionality is disabled.",
        			Toast.LENGTH_LONG).show();
        	}
            return true;
            
        case R.id.menu_help:
        	startActivity(new Intent(this, Help.class));
            return true;
            
        case R.id.menu_about:
        	startActivity(new Intent(this, About.class));
            return true;
            
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
    public Boolean getOptEnabled(Context context) {
    	SharedPreferences CallMapSettings = context.getSharedPreferences("CallMapSetting",Activity.MODE_PRIVATE);
        return CallMapSettings.getBoolean("app_enabled", GVariables.def_app_enabled);
    	
    }
    
}

