package com.example.costtracker_version_02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class WelcomeScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void onClick(View view){
    	Intent iSecond = new Intent("com.example.costtracker_version_02.UserScreenActivity");
    	String email = ((EditText)findViewById(R.id.editText1)).getText().toString();
    	String pass = ((EditText)findViewById(R.id.editText2)).getText().toString();
    	iSecond.putExtra("email",email);
    	iSecond.putExtra("pass", pass);
    	startActivity(iSecond);
    }
}
