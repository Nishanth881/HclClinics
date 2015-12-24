package com.example.hclavitas;

import java.util.Iterator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity{
	
	SharedPreferences share_pre;
	Editor editor;
	TextView facility_text;
	private AlertDialog alertDialog;
	private AlertDialog.Builder alt_bld;
	EditText email,password;
	String facilityNumber="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		facility_text=(TextView)findViewById(R.id.facility_text);
		email=(EditText)findViewById(R.id.email);
		password=(EditText)findViewById(R.id.pass);
		alt_bld = new AlertDialog.Builder(Login.this);
		facility_text.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				openAlertDialog();
			}
		});
		((Button)findViewById(R.id.submit)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				checkAuthentication();
			}
		});
		
	}
	
	
	
	
private void saveSharedPrefrence(String username,String pas, String manager_name,String number){
		
		
		share_pre = getSharedPreferences(Constants.PREFRENCE_NAME, MODE_PRIVATE);
		editor = share_pre.edit(); 
		editor.putString(Constants.FACILITY_NAME,facility_text.getText().toString().trim()); 
		editor.putString(Constants.FACILITY_USERNAME,username); 
		editor.putString(Constants.FACILITY_PASSWORD,pas); 
        editor.putString(Constants.FACILITY_NUMBER, facilityNumber);
        editor.putString(Constants.managerName, manager_name);
        editor.putString(Constants.FACILITY_NUMBER,number);
		editor.commit();
	}


	private void openAlertDialog() {

		final Constants c = new Constants();
		alt_bld.setItems(
				c.facilityList.toArray(new String[c.facilityList.size()]),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// selectedItem = item;
						facility_text.setText(c.facilityList.get(which));

						alertDialog.dismiss();
					}
				});
		alertDialog = alt_bld.create();

		alertDialog.show();
	}
	
	
	
	
	private void checkAuthentication(){
		String username=email.getText().toString().trim();
		String passw=password.getText().toString().trim();
		String facility=facility_text.getText().toString().trim();
		if(username.equalsIgnoreCase("") && passw.equalsIgnoreCase("")){
            Constants.showDialog(Login.this, "Please!", "Enter your Username and Password");
	}else{
		
			if(Constants.userName.contains(username) && Constants.pass.equalsIgnoreCase(passw)){
				int k=0;
				for(int i=0;i<Constants.userName.size();i++){
					if(Constants.userName.get(i).equalsIgnoreCase(username)){
						k=i;
					}
				}
				if(facility.equalsIgnoreCase("")){
		            Constants.showDialog(Login.this, "Please!", "Select Facility name.");
					
				}else{
					Log.e("",""+k);
					if(facility.equalsIgnoreCase(Constants.facilityList.get(k))){
						facilityNumber=Constants.phoneNumber.get(k);
					  String  manager_name=Constants.clusterManager.get(k);
				saveSharedPrefrence(username,passw,manager_name,facilityNumber);
				startActivity(new Intent(Login.this, MainActivity.class));
					}else{
						 Constants.showDialog(Login.this, "Sorry!!", "User does not belong to this facility.");
					}
			}}else{
	            Constants.showDialog(Login.this, "Sorry!", "Invalid Username and Password");

			}
			
		
		
		
	}
	
	}

}
