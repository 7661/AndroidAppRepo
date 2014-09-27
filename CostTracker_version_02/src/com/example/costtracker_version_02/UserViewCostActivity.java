package com.example.costtracker_version_02;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

public class UserViewCostActivity extends Activity {
	
	
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_user_view_cost);
	    //Retrieve data
	    EntryData viewData;
	    viewData = (EntryData) getIntent().getSerializableExtra("viewCost");
	    String[] viewDataStr = new String[] {viewData.getItem(),viewData.getAmount()};
	    GridView gridview = (GridView) findViewById(R.id.gridview);
	    //gridview.setAdapter(new ViewCostAdapter(this,viewData));

	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, viewDataStr);
	    List<EntryData> putList = new ArrayList<EntryData>();
	    List<String[]> stringList = new ArrayList<String[]>();
	    stringList.add(viewDataStr);
	    EntryData viewData2 = new EntryData();
	    viewData2.setItem("secodn");
	    viewData2.setAmount("17");
	    
	    putList.add(viewData);
	    putList.add(viewData2);
	    ArrayAdapter<EntryData>listAdapter = new ArrayAdapter<EntryData>(this, android.R.layout.simple_list_item_1, putList);
	    //ListAdapter printList = new ArrayAdapter<EntryData>(this, android.R.layout.simple_list_item_1, putList);
	    ListAdapter printList = new ArrayAdapter<String[]>(this, android.R.layout.simple_list_item_1, stringList);

	    //ArrayAdapter<String>stringAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, putList);
	    gridview.setAdapter(printList);
	    //gridview.setAdapter(adapter);
	    
	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            Toast.makeText(UserViewCostActivity.this, "" + position, Toast.LENGTH_SHORT).show();
	        }
	    });
	}

}
