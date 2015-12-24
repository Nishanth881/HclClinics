package com.example.hclavitas;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.hclavitas.childfragment.FragmentOneFirstChild;
import com.hclavitas.database.Model_bean;
import com.hclavitas.database.MyDataBaseManager_;

public class SaveIntoDb {
	
	
	 public static void setValuesInDatabase(String userName, String userNumber,Context _mContext) {
		try {
			int modes = Context.MODE_PRIVATE;
           Model_bean model=new Model_bean();
			SharedPreferences mySharedPreferences =((FragmentActivity)_mContext).getSharedPreferences(Constants.PREFRENCE_NAME, modes);
			model.setFacility_type(mySharedPreferences.getString(Constants.FACILITY_NAME,""));
			model.setClusterMobileNumber(mySharedPreferences.getString(Constants.FACILITY_NUMBER,""));
            model.setUser_name(userName);
            model.setPhone_nu(userNumber);
            model.setQues_one(mySharedPreferences.getString(Constants.QUESTION_ONE, ""));
            model.setQuest_two(mySharedPreferences.getString(Constants.QUESTION_TWO, ""));
            model.setQust_three(mySharedPreferences.getString(Constants.QUESTION_THREE, ""));
            model.setQust_four(mySharedPreferences.getString(Constants.QUESTION_FOUR, ""));
            model.setQustn_five(mySharedPreferences.getString(Constants.QUESTION_FIVE, ""));
            model.setQues_six(mySharedPreferences.getString(Constants.QUESTION_SIX, ""));
            model.setQues_seven(mySharedPreferences.getString(Constants.QUESTION_SEVEN, ""));
            model.setQues_eight(mySharedPreferences.getString(Constants.QUESTION_EIGHT, ""));
            model.setQues_nine(mySharedPreferences.getString(Constants.QUESTION_NINE, ""));
            model.setQues_ten(mySharedPreferences.getString(Constants.QUESTION_TEN, ""));
            model.setAns_five(mySharedPreferences.getString(Constants.ANSWER_FIVE, ""));
            model.setAns_four(mySharedPreferences.getString(Constants.ANSWER_FOUR, ""));
            model.setAns_one(mySharedPreferences.getString(Constants.ANSWER_ONE, ""));
            model.setAns_seven(mySharedPreferences.getString(Constants.ANSWER_SEVEN, ""));
            model.setAns_six(mySharedPreferences.getString(Constants.ANSWER_SIX, ""));
            model.setAns_three(mySharedPreferences.getString(Constants.ANSWER_THREE, ""));
            model.setAns_two(mySharedPreferences.getString(Constants.ANSWER_TWO, ""));
            model.setAns_eight(mySharedPreferences.getString(Constants.ANSWER_EIGHT, ""));
            model.setAns_nine(mySharedPreferences.getString(Constants.ANSWER_NINE, ""));
            model.setAns_ten(mySharedPreferences.getString(Constants.ANSWER_TEN, ""));
            model.setManagerName(mySharedPreferences.getString(Constants.managerName,""));
          
            MyDataBaseManager_ manager=new MyDataBaseManager_(_mContext);
            manager.Insert_Info(model);
            
         // Constants.showtoast(getActivity(), "Thanks for your Feedback.");
            //Toast.makeText(_mContext, "Thanks for your Feedback.", Toast.LENGTH_LONG).show();
            FragmentManager fm = ((FragmentActivity)_mContext).getSupportFragmentManager();
            for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {    
                fm.popBackStack();
            }
           FragmentOneFirstChild one =new FragmentOneFirstChild();
           FragmentTransaction t=((FragmentActivity)_mContext).getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, one);
           t.commit();
           ((ChangeQuestionColor)((FragmentActivity)_mContext)).changeQuestion(1,"1. "+Constants.q_one);
           
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	

}
