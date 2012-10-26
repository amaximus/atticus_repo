package com.atticus.CallMap;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.app.Activity;

public class About extends Activity {
	    
	@Override 
    public void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
	       setContentView(R.layout.activity_about);
	       
	       TextView tv = (TextView) findViewById(R.id.textView3);
	       tv.setText(Html.fromHtml(getString(R.string.contact)));
	       tv.setMovementMethod(LinkMovementMethod.getInstance());

	}  // onCreate
    
/*	@Override
	protected void onStop(){
		super.onStop();
	    
	    //Toast.makeText(GVariables.appcontext,GVariables.ownCountryISO,Toast.LENGTH_SHORT).show();
	}
	
    @Override
    public void onBackPressed() {
    
        super.onBackPressed();
        
	} */
    
}

