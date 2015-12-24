package com.example.hclavitas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.DialogInterface.OnKeyListener;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hclavitas.childfragment.FragmentOneFirstChild;
import com.hclavitas.database.Model_bean;
import com.hclavitas.database.MyDataBaseManager_;
import com.hclavitas.utils.ConnectionCheck;
import com.hclavitas.utils.WebConstants;

public class Dialog extends DialogFragment{
	ConnectionCheck _check;
	EditText name_box,number_box;
	View view;
	String userName="",userNumber="";
	 String result="";
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 getDialog().requestWindowFeature(STYLE_NO_TITLE);
		 getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

		view=inflater.inflate(R.layout.dialog, container,false);
		_check=new ConnectionCheck(getActivity());
		TextView tv=(TextView)view.findViewById(R.id.text_final);
		getDialog().setCanceledOnTouchOutside(false);
		String text="<font color=#000000>We are committed to keeping our patients at the centre of all that we do.<br/><br/>We would love to hear more from you! If you wish to give us any more feedback,you can write to </font><font color=#000000><b>ceo-avitas@hcl.com.</b></font>";
		tv.setText(Html.fromHtml(text));
		number_box=(EditText)view.findViewById(R.id.number_box);
		name_box=(EditText)view.findViewById(R.id.name_box);
		((Button)view.findViewById(R.id.send)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!name_box.getText().toString().trim().equalsIgnoreCase("") &&  !number_box.getText().toString().trim().equalsIgnoreCase("")){
					userName=name_box.getText().toString().trim();
					
					if(number_box.getText().toString().trim().length()!=10){
						Constants.showDialog(getActivity(), "Sorry !",  "Please enter 10 digit number.");
					}else{
						sendAndSaveData();
						dismiss();
					}
				}else{
					Constants.showDialog(getActivity(), "Sorry !", "Please enter your details.");
			
				}
                  
			}
		});
		((ImageView)view.findViewById(R.id.close)).setVisibility(View.GONE);
	((ImageView)view.findViewById(R.id.close)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			sendAndSaveData();
			}
		});
		return view;
	}

	
	 JSONArray createObject(){
		 JSONArray arr=new JSONArray();
			int modes = Context.MODE_PRIVATE;
	           Model_bean model=new Model_bean();
				SharedPreferences mySharedPreferences = getActivity().getSharedPreferences(Constants.PREFRENCE_NAME, modes);
		JSONObject object=new JSONObject();
		JSONArray array=new JSONArray();
		JSONObject objectInner=new JSONObject();
		/*JSONObject objectTwo=new JSONObject();
		JSONObject objectThree=new JSONObject();
		JSONObject objectFour=new JSONObject();
		JSONObject objectfive=new JSONObject();*/
		try {			
			object.put("userName",userName);
			object.put("userMobile",userNumber);
			object.put("clusterName",mySharedPreferences.getString(Constants.FACILITY_NAME, ""));
			object.put("clusterEmail",mySharedPreferences.getString(Constants.FACILITY_EMAIL, ""));
			object.put("q1",  mySharedPreferences.getString(Constants.ANSWER_ONE, ""));
			if(mySharedPreferences.getString(Constants.ANSWER_ONE, "").equalsIgnoreCase("Difficult") || mySharedPreferences.getString(Constants.ANSWER_ONE, "").equalsIgnoreCase("Very Difficult")){
			object.put("qa1", mySharedPreferences.getString(Constants.ANSWER_TWO, ""));
				}else{
					object.put("qa1", "");	
				}
			object.put("q2", mySharedPreferences.getString(Constants.ANSWER_THREE, ""));
			if(mySharedPreferences.getString(Constants.ANSWER_THREE, "").equalsIgnoreCase("No")){
			object.put("qa2", mySharedPreferences.getString(Constants.ANSWER_FOUR, ""));
			}else{
				object.put("qa2", "");	
			}
			object.put("q3", mySharedPreferences.getString(Constants.ANSWER_FIVE, ""));
			if(mySharedPreferences.getString(Constants.ANSWER_FIVE, "").equalsIgnoreCase("Not Good") || mySharedPreferences.getString(Constants.ANSWER_FIVE, "").equalsIgnoreCase("Good")){
			object.put("qa3", mySharedPreferences.getString(Constants.ANSWER_SIX, ""));
				}else{
					object.put("qa3", "");	
				}
			object.put("q4", mySharedPreferences.getString(Constants.ANSWER_SEVEN, ""));
			object.put("q5", mySharedPreferences.getString(Constants.ANSWER_EIGHT, ""));
			object.put("q6", mySharedPreferences.getString(Constants.ANSWER_NINE, ""));
			if(mySharedPreferences.getString(Constants.ANSWER_NINE, "").equalsIgnoreCase("Very Poor") || mySharedPreferences.getString(Constants.ANSWER_NINE, "").equalsIgnoreCase("Poor")){
			object.put("qa4", mySharedPreferences.getString(Constants.ANSWER_TEN, ""));
				}else{
					object.put("qa4", "");
				}
			
			object.put("clusterNumber", mySharedPreferences.getString(Constants.FACILITY_NUMBER, ""));
			//object.put("feedback",array);
	        object.put("managerName", mySharedPreferences.getString(Constants.managerName, ""));
			arr.put(0,object);
			//objectInner.put("data", arr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return arr;
		
		
		 
	}
	
	 
	/* class Async extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
		String result=	getResponce(createObject());
		Log.d("result", result);
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			
			name_box.setText("");
			number_box.setText("");
			userName="";
			userNumber="";
		//	Constants.showtoast(getActivity(), "Thanks for your Feedback.");
			 Toast.makeText(getActivity(), "Thanks for your Feedback.", Toast.LENGTH_LONG).show();
	            FragmentManager fm = getActivity().getSupportFragmentManager();
	            for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {    
	                fm.popBackStack();
	            }
	           FragmentOneFirstChild one =new FragmentOneFirstChild();
	           FragmentTransaction t=getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, one);
	           t.commit();
	           ((ChangeQuestionColor)getActivity()).changeQuestion(1,"1. "+Constants.q_one);
		
	           dismiss();
	           
	           Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/html");
			intent.putExtra(Intent.EXTRA_EMAIL, "rubi_it01@rediffmail.com.com");
			intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
			intent.putExtra(Intent.EXTRA_TEXT, result);

			startActivity(Intent.createChooser(intent, "Send Email"));
			super.onPostExecute(result);

		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		 
		 
		 
		 
		 
		 
		 
	 }*/
	
	
	
	///        httpost.setHeader("Accept-Charset", "utf-8");
		 /*StringEntity se = new StringEntity( "data:" + object.toString());  
	     se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
	     post.setEntity(se);
	   */

	 
	 @Override
	public void onResume() {
		// TODO Auto-generated method stub
/*		  getDialog().setOnKeyListener(new OnKeyListener()
		    {
		        @Override
		        public boolean onKey(android.content.DialogInterface dialog, int keyCode,android.view.KeyEvent event) {

		            if ((keyCode ==  android.view.KeyEvent.KEYCODE_BACK))
		                {
		                     //Hide your keyboard here!!!
		                     return true; // pretend we've processed it
		                }
		            else 
		                return false; // pass on to be processed as normal
		        }
		    });*/
		super.onResume();
	}
	 
	 private void sendAndSaveData(){
		 userNumber=number_box.getText().toString().trim();
			InputMethodManager	imm= (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		    imm.hideSoftInputFromWindow(number_box.getWindowToken(), 0);
			if(_check.checkConnection()){
			//new Async().execute();
		
			Thread tn=new Thread(new  Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					 result=	SendRequestToServer.getResponce(createObject());
					Log.d("result", result);
		
				}
			});
			tn.start();
			
			name_box.setText("");
			number_box.setText("");
			userName="";
			userNumber="";
			Constants.showtoast(getActivity());
			// Toast.makeText(getActivity(), "Thanks for your Feedback.", Toast.LENGTH_LONG).show();
	            FragmentManager fm = getActivity().getSupportFragmentManager();
	            for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {    
	                fm.popBackStack();
	            }
	           
	           FragmentOneFirstChild one =new FragmentOneFirstChild();
	           FragmentTransaction t=getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, one);
	           t.commit();
	           ((ChangeQuestionColor)getActivity()).changeQuestion(1,"1. "+Constants.q_one);
		
	           dismiss();
	           
			}else{
		SaveIntoDb.setValuesInDatabase(userName,userNumber,getActivity());
		Constants.showtoast(getActivity());
		dismiss();
			}
	 }

}
