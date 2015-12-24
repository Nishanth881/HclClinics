package com.example.hclavitas;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class Constants {
	
	private static AlertDialog dialog;
//	ArrayList<String> QUESTION_LIST=new ArrayList<String>();
	public static String PREFRENCE_NAME="Hcl Avitas";
	public static String FACILITY_NAME="facility_name";
	public static String FACILITY_NUMBER="facility_number";
	public static String FACILITY_EMAIL="facility_email";
	public static String FACILITY_USERNAME="facility_username";
	public static String FACILITY_PASSWORD="facility_pass";
	public static String QUESTION_ONE="question_one";
	public static String QUESTION_TWO="question_two";
	public static String QUESTION_THREE="question_three";
	public static String QUESTION_FOUR="question_four";
	public static String QUESTION_FIVE="question_five";
	public static String QUESTION_SIX="question_six";
	public static String QUESTION_SEVEN="question_seven";
	public static String QUESTION_EIGHT="question_eight";
	public static String QUESTION_NINE="question_nine";
	public static String QUESTION_TEN="question_ten";
	public static String ANSWER_ONE="answer_one";
	public static String ANSWER_TWO="answer_two";
	public static String ANSWER_THREE="answer_three";
	public static String ANSWER_FOUR="answer_four";
	public static String ANSWER_FIVE="answer_five";
	public static String ANSWER_SIX="answer_six";
	public static String ANSWER_SEVEN="answer_seven";
	public static String ANSWER_EIGHT="answer_eight";
	public static String ANSWER_NINE="answer_nine";
	public static String ANSWER_TEN="answer_ten";
	public static String managerName="manager_name";
	public static  ArrayList<String> facilityList=new ArrayList<String>();
	public static  ArrayList<String> clusterManager=new ArrayList<String>();
	public  ArrayList<String> email=new ArrayList<String>();
	public static ArrayList<String> phoneNumber=new ArrayList<String>();
	public static ArrayList<String> userName=new ArrayList<String>();
	public static ArrayList<String> password=new ArrayList<String>();
	public static String pass="Avitas@123";
    public static String q_one="How was your appointment booking experience?";
    public static String sq_one="Why was it difficult to book an appointment?";
    public static String q_two="Did you find the specialties and services you were looking for?";
    public static String sq_two="What specialty/service were you looking for?";
    public static String q_three="How would you rate us on your waiting time?";
    public static String sq_three="Area of improvement";
    public static String q_four="How did you find the clinic's Hygiene and Sanitation?";
    public static String q_five="How courteous was our staff?";
    public static String q_six="How would you rate your visit today?";
    public static String sq_six="Please let us know where we can improve.";
	public static String FIX_CODE="hclapp";
	
	
    public Constants() {
	/*	QUESTION_LIST.add("How easy was it to get an appoinment?");
		QUESTION_LIST.add("Did you find the specialities and services you were looking for?");
		QUESTION_LIST.add("How would you rate us on your waiting time?");
		QUESTION_LIST.add("How would you rate your visit today?");*/
		facilityList = new ArrayList<String>();
		clusterManager = new ArrayList<String>();
		email = new ArrayList<String>();
		phoneNumber = new ArrayList<String>();
		userName = new ArrayList<String>();
		password = new ArrayList<String>();
		userName.clear();
		password.clear();
		facilityList.clear();
		clusterManager.clear();
		email.clear();
		phoneNumber.clear();
		facilityList.add("AMC Dwarka");
		facilityList.add("AMC Gurgaon");
		facilityList.add("FHC Gurgaon Sec-14");
		facilityList.add("AMC Nirman vihar");
		facilityList.add("AMC Noida");
		facilityList.add("FHC Noida Sec-63");
		facilityList.add("FHC SEZ Noida");
		facilityList.add("AMC Pitampura");
		facilityList.add("FHC Shalimar Bagh");
		facilityList.add("FHC Rohini Sec 7");

		clusterManager.add("Prerna");
		clusterManager.add("Dr Rimy khurana");
		clusterManager.add("Dr Rimy khurana");
		clusterManager.add("Sandeep Kumar");
		clusterManager.add("Shazab Hussain");
		clusterManager.add("Anu Gupta");
		clusterManager.add("Anu Gupta");
		clusterManager.add("Dr Anubha Dhawan");
		clusterManager.add("Dr Anubha Dhawan");
		clusterManager.add("Dr Anubha Dhawan");

		email.add("prerna.panwar@hcl.com");
		email.add("Rimy.Khurana@hcl.com");
		email.add("Rimy.Khurana@hcl.com");
		email.add("Sandeep.dogra@hcl.com");
		email.add("shahzad.hussain@hcl.com");
		email.add("Anu.gupta@hcl.com");
		email.add("Anu.gupta@hcl.com");
		email.add("Anubha.dhawan@hcl.com");
		email.add("Anubha.dhawan@hcl.com");
		email.add("Anubha.dhawan@hcl.com");

		phoneNumber.add("9654598877");
		phoneNumber.add("9650737733");
		phoneNumber.add("9650737733");
		phoneNumber.add("9560477885");
		phoneNumber.add("9717352600");
		phoneNumber.add("9971166810");
		phoneNumber.add("9971166810");
		phoneNumber.add("9871114112");
		phoneNumber.add("9871114112");
		phoneNumber.add("9871114112");

		userName.add("hcl.dwarka");
		userName.add("hcl.gurgaon");
		userName.add("hcl.gsec14");
		userName.add("hcl.nirmanvihaar");
		userName.add("hcl.noida");
		userName.add("hcl.nsec63");
		userName.add("hcl.snoida");
		userName.add("hcl.pitampura");
		userName.add("hcl.sbagh");
		userName.add("hcl.rsec7");
		
		
	}
    
    public static AlertDialog showDialog(Context  _mContext,String title,String message){
    	
    dialog=	new AlertDialog.Builder(_mContext)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
                // continue with delete
            }
         })
        .setIcon(android.R.drawable.ic_dialog_alert).show();
    
    return dialog;
    }
    
    
    @SuppressWarnings("static-access")
	public static void showtoast(Context  _mContext){
    	
 LayoutInflater inflater=((FragmentActivity) _mContext).getLayoutInflater();
 
 View customToastroot =inflater.inflate(R.layout.custom_toast, null);
 
 Toast customtoast=new Toast(_mContext);
 
 customtoast.setView(customToastroot);
 customtoast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL,0, 0);
 customtoast.setDuration(Toast.LENGTH_LONG);
 customtoast.show();
    }
}
