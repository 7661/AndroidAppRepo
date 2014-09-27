package com.example.costtracker_version_02;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class UserListViewCostActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_user_view_list_cost);
	    //Retrieve data
	    EntryData viewData;
	    viewData = (EntryData) getIntent().getSerializableExtra("viewCost");
	    String[] viewDataStr = new String[] {viewData.getItem(),viewData.getAmount()};
	    ListView listView = (ListView) findViewById(R.id.listview01);
	    //gridview.setAdapter(new ViewCostAdapter(this,viewData));

	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, viewDataStr);
	    List<EntryData> putList = new ArrayList<EntryData>();
	    EntryData viewData2 = new EntryData();
	    viewData2.setItem("secodn");
	    viewData2.setAmount("17");
	    
	    putList.add(viewData);
	    putList.add(viewData2);
	    ArrayAdapter<EntryData>listAdapter = new ArrayAdapter<EntryData>(this, android.R.layout.simple_list_item_1, putList);
	    ListAdapter printList = new ArrayAdapter<EntryData>(this, android.R.layout.simple_list_item_1, putList);
	    listView.setAdapter(printList);
	    //gridview.setAdapter(adapter);
	    
	    listView.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            Toast.makeText(UserListViewCostActivity.this, "" + position, Toast.LENGTH_SHORT).show();
	        }
	    });
	}
}
