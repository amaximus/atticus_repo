package com.atticus.CallMap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {

	CallMap cmap = new CallMap();
	
	if ( cmap.getOptEnabled(context) ) {
		// start the service
		Intent service = new Intent(context, CallMapService.class);
	    context.startService(service);
	}
    
	// if ( enabled ) {
    // } // if
  }   // onReceive
  	
} 