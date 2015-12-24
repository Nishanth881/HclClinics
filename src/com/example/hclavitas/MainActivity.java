package com.example.hclavitas;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.hclavitas.childfragment.FragThreeChildOne;
import com.hclavitas.childfragment.FragmentOneFirstChild;
import com.hclavitas.childfragment.FragmentSixFirstChild;
import com.hclavitas.childfragment.FragmentSixSecondChild;
import com.hclavitas.childfragment.FragmentTwoFirstChild;
import com.hclavitas.childfragment.FragmentthirdChildTwo;
import com.hclavitas.childfragment.fragmentSecondChildOne;
import com.hclavitas.childfragment.fragmentSecondTwoChild;
import com.hclavitas.database.Model_bean;
import com.hclavitas.utils.ConnectionCheck;


public class MainActivity extends FragmentActivity implements ChangeQuestionColor{
	FragmentManager fragmentManager;
	Fragment fragment;
	String facility;
	ImageView q_one,q_two,q_three,q_four,q_five,q_six;
	TextView question;
	ConnectionCheck _check;
	String from="",result="";
	int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    getWindow().setSoftInputMode(
	            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.fragment_main);
        Log.d("change", "oncreate");
        getPrefrence();
        _check=new ConnectionCheck(MainActivity.this);
        q_one=(ImageView)findViewById(R.id.q_one);
        q_two=(ImageView)findViewById(R.id.q_two);
        q_three=(ImageView)findViewById(R.id.q_three);
        q_four=(ImageView)findViewById(R.id.q_four);
        q_five=(ImageView)findViewById(R.id.q_five);
        q_six=(ImageView)findViewById(R.id.q_six);
        question=(TextView)findViewById(R.id.question);
        ((TextView)findViewById(R.id.facility_name)).setText(facility);
        ((ImageView)findViewById(R.id.logout_button)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				from= "logout";
				sendTask();
			}
		});
        
        ((ImageView)findViewById(R.id.resume_button)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				from= "resume";
				sendTask();
			
			}
		});
        
	      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

	      FragmentOneFirstChild pm_fragment = new FragmentOneFirstChild();
	         fragmentTransaction.add(R.id.mainframe, pm_fragment);
	      
	      fragmentTransaction.commit();
    }
    
    
    private void getPrefrence() {
		try {
			int modes = Context.MODE_PRIVATE;

			SharedPreferences mySharedPreferences =getSharedPreferences(Constants.PREFRENCE_NAME, modes);
			facility = mySharedPreferences.getString(Constants.FACILITY_NAME,"");
			Log.e("user_id", facility);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
  
    
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	 int backCount = getSupportFragmentManager().getBackStackEntryCount();
    	 Fragment fm=getSupportFragmentManager().findFragmentById(R.id.mainframe);
    	 if(backCount>0){
    		 if(fm instanceof fragmentSecondChildOne) 
    		{
    		changeQuestion(1,"1. "+Constants.q_one);	
    		super.onBackPressed();
    		}else if(fm instanceof fragmentSecondTwoChild) 
    		{
    		changeQuestion(1,"2. "+Constants.q_two);
    		super.onBackPressed();
    		}else if(fm instanceof FragThreeChildOne) 
    		{
    		changeQuestion(2,"2. "+Constants.q_two);	
    		super.onBackPressed();
    		}else if(fm instanceof FragmentthirdChildTwo) 
    		{
    		changeQuestion(3,"3. "+Constants.q_three);	
    		super.onBackPressed();
    		}else if(fm instanceof QuestionFourth) 
    		{
    		changeQuestion(3,"3. "+Constants.q_three);	
    		super.onBackPressed();
    		}else if(fm instanceof Questionfive) 
    		{
    		changeQuestion(4,"4. "+Constants.q_four);	
    		super.onBackPressed();
    		}else if(fm instanceof FragmentSixFirstChild) 
    		{
    		changeQuestion(5,"5. "+Constants.q_five);	
    		super.onBackPressed();
    		}else if(fm instanceof FragmentSixSecondChild) 
    		{
    		changeQuestion(5,"6. "+Constants.q_six);	
    		super.onBackPressed();
    		}else if(fm instanceof FragmentTwoFirstChild){
    			changeQuestion(1,"1. "+Constants.q_one);
    			super.onBackPressed();	
    		}
    	 }
    	
    }


	@SuppressLint("ResourceAsColor") @Override
	public void changeQuestion(int val,String question_text) {
		// TODO Auto-generated method stub
		i=val;
		if(val==1){
			q_one.setImageResource(R.drawable.radio_button_off);
			q_two.setImageResource(R.drawable.radio_button_on);
			q_three.setImageResource(R.drawable.radio_button_on);
			q_four.setImageResource(R.drawable.radio_button_on);
			q_five.setImageResource(R.drawable.radio_button_on);
			q_six.setImageResource(R.drawable.radio_button_on);
		}else if(val==2){
			q_two.setImageResource(R.drawable.radio_button_off);
			q_one.setImageResource(R.drawable.radio_button_on);
			q_three.setImageResource(R.drawable.radio_button_on);
			q_four.setImageResource(R.drawable.radio_button_on);
			q_five.setImageResource(R.drawable.radio_button_on);
			q_six.setImageResource(R.drawable.radio_button_on);
		}else if(val==3){
			q_three.setImageResource(R.drawable.radio_button_off);
			q_two.setImageResource(R.drawable.radio_button_on);
			q_one.setImageResource(R.drawable.radio_button_on);
			q_four.setImageResource(R.drawable.radio_button_on);
			q_five.setImageResource(R.drawable.radio_button_on);
			q_six.setImageResource(R.drawable.radio_button_on);
		}else if(val==4){
			q_four.setImageResource(R.drawable.radio_button_off);
			q_two.setImageResource(R.drawable.radio_button_on);
			q_three.setImageResource(R.drawable.radio_button_on);
			q_one.setImageResource(R.drawable.radio_button_on);
			q_five.setImageResource(R.drawable.radio_button_on);
			q_six.setImageResource(R.drawable.radio_button_on);
		}else if(val==5){
			q_five.setImageResource(R.drawable.radio_button_off);
			q_two.setImageResource(R.drawable.radio_button_on);
			q_three.setImageResource(R.drawable.radio_button_on);
			q_four.setImageResource(R.drawable.radio_button_on);
			q_one.setImageResource(R.drawable.radio_button_on);
			q_six.setImageResource(R.drawable.radio_button_on);
		}else if(val==6){
			q_six.setImageResource(R.drawable.radio_button_off);
			q_two.setImageResource(R.drawable.radio_button_on);
			q_three.setImageResource(R.drawable.radio_button_on);
			q_four.setImageResource(R.drawable.radio_button_on);
			q_five.setImageResource(R.drawable.radio_button_on);
			q_one.setImageResource(R.drawable.radio_button_on);
		}
		question.setText(question_text);

		
	}
	/*public static boolean isXLargeScreen(Context context) {
	    return (context.getResources().getConfiguration().screenLayout  & Configuration.SCREENLAYOUT_SIZE_MASK)
	    >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
	} */
	
	
	/*@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		if(getResources().getConfiguration().orientation==newConfig.ORIENTATION_LANDSCAPE){
			Log.d("change", "confi_land");
		}else if(getResources().getConfiguration().orientation==newConfig.ORIENTATION_PORTRAIT){
			Log.d("change", "confi_port");
		}
		super.onConfigurationChanged(newConfig);
	}*/
     
	
	
	private void sendTask(){
		

	

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
		}else{
			SaveIntoDb.setValuesInDatabase("","",MainActivity.this);
		
				}

		if(from.equalsIgnoreCase("resume")){
			 FragmentOneFirstChild one =new FragmentOneFirstChild();
	           FragmentTransaction t=getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, one);
	           t.commit();
	           changeQuestion(1,"1. "+Constants.q_one);
		}else if(from.equalsIgnoreCase("logout")){
			finish();
		}
			
	
		
	}
	
	
	 JSONArray createObject(){
		 JSONArray arr=new JSONArray();
			int modes = Context.MODE_PRIVATE;
	           Model_bean model=new Model_bean();
				SharedPreferences mySharedPreferences =getSharedPreferences(Constants.PREFRENCE_NAME, modes);
		JSONObject object=new JSONObject();
		JSONArray array=new JSONArray();
		JSONObject objectInner=new JSONObject();
		/*JSONObject objectTwo=new JSONObject();
		JSONObject objectThree=new JSONObject();
		JSONObject objectFour=new JSONObject();
		JSONObject objectfive=new JSONObject();*/
		try {			
			object.put("userName","");
			object.put("userMobile","");
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
	
}
