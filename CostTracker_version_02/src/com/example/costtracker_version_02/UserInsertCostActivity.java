package com.example.costtracker_version_02;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserInsertCostActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_insert_cost);
		Button insertCostInfo = (Button) findViewById(R.id.insert_cost_info_btn);
		insertCostInfo.setOnClickListener(costDataEntry);
		
	}

	private OnClickListener costDataEntry = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(processCostData()){
				Toast.makeText(getApplicationContext(), "Cost Data inserted", Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(getApplicationContext(), "Cost Data insertion Failed", Toast.LENGTH_LONG).show();
			}
			
		}
	};
	
	private boolean processCostData(){
		boolean retCd = false;
					
		EditText dateData = (EditText)findViewById(R.id.editText1);
		EditText itemData = (EditText)findViewById(R.id.editText2);
		EditText amountData = (EditText)findViewById(R.id.editText3);
		
		EntryDataDAO dbEntry = new EntryDataDAO(getApplicationContext());
		EntryData costEntry = new EntryData();
		
		EntryData userSessionInfo = (EntryData) getIntent().getSerializableExtra("inserCostUserInfo");
		costEntry.setUserId(userSessionInfo.getUserId());
		costEntry.setDataTableName(userSessionInfo.getDataTableName());
		costEntry.setDate(dateData.getText().toString());
		costEntry.setAmount(amountData.getText().toString());
		costEntry.setItem(itemData.getText().toString());
		//costEntry.setUserId(userId);
		//String itemTypeDataString = itemTypeData.getText().toString();
		
		if(dbEntry.insertEntryData(costEntry,"user_cost_table")){			
			retCd = true;
		}
		return retCd;
		
	}
	
}
