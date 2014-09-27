package com.example.costtracker_version_02;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class UserScreenActivity extends Activity {
	static String USER_SESSION_EMAIL;
	static String USER_SESSION_PASS;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if not in database don't allow login
        //For time being assume that everything works.
        checkDatabaseCredentials(this);
        setContentView(R.layout.activity_user_screen);
        //Deligate different button click to different activity
        Button costBtn = (Button)findViewById(R.id.costbutton);
        costBtn.setOnClickListener(costActivity);
        Button incomeBtn = (Button)findViewById(R.id.incomebutton);
        incomeBtn.setOnClickListener(incomeActivity);
        Button debtBtn = (Button)findViewById(R.id.debtbutton);
        debtBtn.setOnClickListener(debtActivity);
        Button loanBtn = (Button)findViewById(R.id.loanbutton);
        loanBtn.setOnClickListener(loanActivity);
    }
	
	private OnClickListener costActivity = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent iCost = new Intent("com.example.costtracker_version_02.UserCostActivityScreen");
			iCost.putExtra("email",USER_SESSION_EMAIL);
			iCost.putExtra("pass", USER_SESSION_PASS);
	    	startActivity(iCost);
		}
	};
	
private OnClickListener incomeActivity = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent iIncome = new Intent("com.example.costtracker_version_02.UserIncomeActivityScreen");
			iIncome.putExtra("email",USER_SESSION_EMAIL);
			iIncome.putExtra("pass", USER_SESSION_PASS);
	    	startActivity(iIncome);
			
		}
	};
	
private OnClickListener debtActivity = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent iDebt = new Intent("com.example.costtracker_version_02.UserDebtActivityScreen");
			iDebt.putExtra("email",USER_SESSION_EMAIL);
			iDebt.putExtra("pass", USER_SESSION_PASS);
	    	startActivity(iDebt);
			
		}
	};
	
private OnClickListener loanActivity = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent iLoan = new Intent("com.example.costtracker_version_02.UserLoanActivityScreen");
			iLoan.putExtra("email",USER_SESSION_EMAIL);
			iLoan.putExtra("pass", USER_SESSION_PASS);
	    	startActivity(iLoan);
			
		}
	};
	
	private void checkDatabaseCredentials(Context ctx)
	{
		//Check the user details already exists or not;
		//If not, ask the user to create one.
		//For time being assume the user gives permission
		DBAdapter dba = new DBAdapter(ctx);
        String email =  getIntent().getStringExtra("email");
	    String pass = getIntent().getStringExtra("pass"); 	
	    //Just for future reference if we need the user name and pass
	    //later on in any activity
	    USER_SESSION_EMAIL=email;
	    USER_SESSION_PASS=pass;
	    dba.open();
	    //dba.insertContact(email, pass);
	    if(dba.verifyContact(email, pass))
	     {
	    	 dba.close();
	    	 Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG).show();
	     }
	}
	
}
