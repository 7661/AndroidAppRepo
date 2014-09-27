package com.example.costtracker_version_02;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UserCostActivityScreen extends Activity {
	
	private static Context ctx;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ctx = this;
		setContentView(R.layout.activity_user_cost_screen);
		Button insertButton = (Button) findViewById(R.id.insert_cost_button);
		insertButton.setOnClickListener(insertCostAct);
		Button viewButton = (Button) findViewById(R.id.view_cost_button);
		viewButton.setOnClickListener(viewCostAct);
	}

	private OnClickListener insertCostAct= new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent insertCost = new Intent("com.example.costtracker_version_02.UserInsertCostActivity");
			//insertCost.putExtra("email",USER_SESSION_EMAIL);
			//insertCost.putExtra("pass", USER_SESSION_PASS);
	    	EntryData userInfo = new EntryData();
	    	userInfo.setUserId("tapas");
	    	userInfo.setDataTableName("user_cost_table");
			insertCost.putExtra("inserCostUserInfo", userInfo);
			startActivity(insertCost);
			
		}
	};
	
	private OnClickListener viewCostAct= new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			EntryDataDAO dbread = new EntryDataDAO(getApplicationContext());
			EntryDataBulk costEntryBulkRec = dbread.readAllEntryData("tapas", "user_cost_table");
			//List<EntryData> dataString = new ArrayList<EntryData>();
			//costEntryRec.setItem("Shirt");
			//costEntryRec.setAmount("15");
			//dataString.add(costEntryRec);
			//Intent viewCost = new Intent("com.example.costtracker_version_02.UserViewCostActivity");
			//Intent viewCost = new Intent("com.example.costtracker_version_02.UserListViewCostActivity");
			Intent tableViewCost = new Intent("com.example.costtracker_version_02.UserTableViewCostActivity");
			tableViewCost.putExtra("viewCost", costEntryBulkRec);
			startActivity(tableViewCost);
		}
	};
	
	
}
