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
    
}

