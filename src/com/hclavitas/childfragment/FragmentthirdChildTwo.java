package com.hclavitas.childfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hclavitas.ChangeQuestionColor;
import com.example.hclavitas.Constants;
import com.example.hclavitas.QuestionFourth;
import com.example.hclavitas.R;

public class FragmentthirdChildTwo extends Fragment{
	TextView ans_one,ans_two,ans_three,ans_four,ans_five,ans_six,sad_text_text;
	String difficultAns="",val="";
	String question_five="How would you rate us on your waiting time?";
	SharedPreferences share_pre;
	Editor editor;
	View view;
	boolean isOne=false,isTwo=false,isThree=false;
	ImageView green_two;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		getActivity().getWindow().setSoftInputMode(
	            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		view=inflater.inflate(R.layout.fragment_third_child_two, container,false);
		initView(view);
		setRetainInstance(true);
		return view;
	}
	
	
	 @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        if(!difficultAns.equalsIgnoreCase("")){
				saveDifcultSharedPrefrence(val,difficultAns);
				
	        }
	        LayoutInflater inflater = LayoutInflater.from(getActivity());
	        populateViewForOrientation(inflater, (ViewGroup) getView());
	    }
	 
	    private void populateViewForOrientation(LayoutInflater inflater, ViewGroup viewGroup) {
	        viewGroup.removeAllViewsInLayout();
	        View subview = inflater.inflate(R.layout.fragment_third_child_two, viewGroup);
initView(subview);
getPrefrence();
	        // Find your buttons in subview, set up onclicks, set up callbacks to your parent fragment or activity here.
	        
	        // You can create ViewHolder or separate method for that.
	        // example of accessing views: TextView textViewExample = (TextView) view.findViewById(R.id.text_view_example);
	        // textViewExample.setText("example");
	    }
	
	

	    private void getPrefrence() {
			try {
				int modes = Context.MODE_PRIVATE;

				SharedPreferences mySharedPreferences =getActivity().getSharedPreferences(Constants.PREFRENCE_NAME, modes);
				difficultAns= mySharedPreferences.getString(Constants.ANSWER_SIX,"");
				Log.d("ans", difficultAns);
				 getText();
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
	    
	    
	    private void getText(){
	    	if(difficultAns.contains("Registration and Billing")){
	    		ans_six.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
	    		isThree=true;

	    	}
	    	if(difficultAns.contains("Waiting for Doctor/Lab test")){
	    		ans_five.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
	    		isTwo=true;
	    	}
	    	if(difficultAns.contains("Nurse interaction")){
	    			    		ans_one.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
	    		isOne=true;
	    	}
	    	    }

	
	
	
	
	private void initView(View subview) {
		// TODO Auto-generated method stub
		green_two = (ImageView) subview.findViewById(R.id.green_two);
		((ImageView) subview.findViewById(R.id.button_next))
				.setOnClickListener(onClick);
		((ImageView) subview.findViewById(R.id.button_back))
				.setOnClickListener(onClick);
		ans_one = (TextView) subview.findViewById(R.id.ans_one);

		ans_five = (TextView) subview.findViewById(R.id.ans_five);

		ans_six = (TextView) subview.findViewById(R.id.ans_six);
		sad_text_text = (TextView) subview.findViewById(R.id.sad_text_text);

		ans_one.setOnClickListener(onClick);

		ans_five.setOnClickListener(onClick);
		ans_six.setOnClickListener(onClick);
		val = getArguments().getString("value");
		sad_text_text.setText(val + " !");
		if (val.equalsIgnoreCase("Very Poor")) {
			green_two.setImageResource(R.drawable.very_poor);
		} else if (val.equalsIgnoreCase("Poor")) {
			green_two.setImageResource(R.drawable.poor);
		}
	}







	OnClickListener onClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			QuestionFourth frag;
			switch (v.getId()) {
						case R.id.button_next:
				if(!difficultAns.equalsIgnoreCase("")){
					Log.d("ans", difficultAns);
		saveDifcultSharedPrefrence(val,difficultAns);
				frag= new QuestionFourth();
				changeFragmentView(frag);
				}else{
					Constants.showDialog(getActivity(), "Please!", "Select an option first.");
				}
				break;
			case R.id.button_back:
				//setCheck(ans_one, ans_four, ans_three, ans_two,ans_five,ans_six);
               getActivity().getSupportFragmentManager().popBackStack();
				break;
			case R.id.ans_one:
				if(isOne){
					ans_one.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
					if(difficultAns.contains(ans_one.getText().toString().trim())){
						difficultAns=difficultAns.replace(ans_one.getText().toString().trim(), "");
						
					}
					isOne=false;
				}else{
					ans_one.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
					if(difficultAns.equalsIgnoreCase("")){
						difficultAns=ans_one.getText().toString().trim();
					}else{
						difficultAns=difficultAns+","+ans_one.getText().toString().trim();
					}
					isOne=true;
				}
				
				break;
			case R.id.ans_two:
				setCheck(ans_two, ans_four, ans_three, ans_one,ans_five,ans_six);
			     difficultAns=ans_two.getText().toString().trim();
			     
				break;
			case R.id.ans_three:
				difficultAns=ans_three.getText().toString().trim();
				setCheck(ans_three, ans_four, ans_two, ans_one,ans_five,ans_six);
				
				break;
			case R.id.ans_four:
				difficultAns=ans_four.getText().toString().trim();
				setCheck(ans_four, ans_two, ans_three, ans_one,ans_five,ans_six);
				break;
			case R.id.ans_five:
				if(isTwo){
					ans_five.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
					if(difficultAns.contains(ans_five.getText().toString().trim())){
						difficultAns=difficultAns.replace(ans_five.getText().toString().trim(), "");
						
					}
					isTwo=false;
				}else{
					ans_five.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
					if(difficultAns.equalsIgnoreCase("")){
						difficultAns=ans_five.getText().toString().trim();
					}else{
						difficultAns=difficultAns+","+ans_five.getText().toString().trim();
					}
					isTwo=true;
				}

				break;
			case R.id.ans_six:
				if(isThree){
					ans_six.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
					if(difficultAns.contains(ans_six.getText().toString().trim())){
						difficultAns=difficultAns.replace(ans_six.getText().toString().trim(), "");
						
					}
					isThree=false;
				}else{
					ans_six.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
					if(difficultAns.equalsIgnoreCase("")){
						difficultAns=ans_six.getText().toString().trim();
					}else{
						difficultAns=difficultAns+","+ans_six.getText().toString().trim();
					}
                  isThree=true;
				}

				break;
				

			default:
				break;
			}
		}
	};

	private void changeFragmentView(Fragment toFrament) {
		FragmentManager fragmentManager = getActivity()
				.getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left).addToBackStack(null);

		fragmentTransaction.add(R.id.mainframe, toFrament);

		fragmentTransaction.commit();
		((ChangeQuestionColor)getActivity()).changeQuestion(4,"4."+Constants.q_four);
		// getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
	}
	
	private void setCheck(TextView selected,TextView unselected_one,TextView unselected_two,TextView unselected_three,TextView unselected_four,TextView unselected_five){
		selected.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
		unselected_one.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
		unselected_two.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
		unselected_three.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
		unselected_four.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
		unselected_five.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
		
	}
	

	private void saveDifcultSharedPrefrence(String val,String ans) {
		int modes = Context.MODE_PRIVATE;
		share_pre =getActivity().getSharedPreferences(Constants.PREFRENCE_NAME, modes);
		editor = share_pre.edit();
		editor.putString(Constants.QUESTION_FIVE, Constants.q_three);
		editor.putString(Constants.QUESTION_SIX, Constants.sq_three);
		editor.putString(Constants.ANSWER_FIVE, val);
		editor.putString(Constants.ANSWER_SIX, ans);
		editor.putString(Constants.QUESTION_SEVEN,"");
		editor.putString(Constants.ANSWER_SEVEN, "");
		editor.putString(Constants.QUESTION_EIGHT,"");
		editor.putString(Constants.ANSWER_EIGHT, "");
		editor.putString(Constants.QUESTION_NINE,"");
		editor.putString(Constants.ANSWER_NINE, "");
		editor.putString(Constants.QUESTION_TEN,"");
		editor.putString(Constants.ANSWER_TEN, "");
		editor.commit();
	}
}
