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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hclavitas.ChangeQuestionColor;
import com.example.hclavitas.Constants;
import com.example.hclavitas.R;

public class FragmentTwoFirstChild extends Fragment{
	
	View childView;
	TextView ans_one,ans_two,ans_three,ans_four,sad_text_text,ans_five;
   String difficultAns="";
	SharedPreferences share_pre;
	  String val="";
	  boolean isOne=false,isTwo=false,isThree=false,isFour=false,isFive=false;
	Editor editor;
	ImageView green_two;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getActivity().getWindow().setSoftInputMode(
	            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		childView=inflater.inflate(R.layout.inflte_frag_two_first, container,false);
	initView(childView);
	setRetainInstance(true);
		return childView;
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
	        View subview = inflater.inflate(R.layout.inflte_frag_two_first, viewGroup);
initView(subview);
getPrefrence();
	       
	    }
	
	
	
	
	    private void getPrefrence() {
			try {
				int modes = Context.MODE_PRIVATE;

				SharedPreferences mySharedPreferences =getActivity().getSharedPreferences(Constants.PREFRENCE_NAME, modes);
				difficultAns= mySharedPreferences.getString(Constants.ANSWER_TWO,"");
				Log.d("ans", difficultAns);
				 getText();
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

	   
	    
	    
	    private void getText(){
	    	if(difficultAns.contains("Phone line was busy")){
	    		ans_one.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
	    		isOne=true;
	    	}
	    	if(difficultAns.contains("Phone went unanswered")){
	    		ans_two.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
	    		isTwo=true;
	    	}
	    	if(difficultAns.contains("Slow website")){
	    		ans_three.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
	    		isThree=true;
	    	}
	    	if(difficultAns.contains("Poor response on queries")){
	    		ans_four.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
	    		isFour=true;
	    	}
	    	if(difficultAns.contains("Doctor unavailable")){
	    		ans_five.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
	    		isFive=true;
	    	}
	    }
	
	
	
	
	
	
	
	
	private void initView(View subview) {
			// TODO Auto-generated method stub
		ans_one=(TextView)subview.findViewById(R.id.ans_one);
		ans_two=(TextView)subview.findViewById(R.id.ans_two);
		ans_three=(TextView)subview.findViewById(R.id.ans_three);
		ans_four=(TextView)subview.findViewById(R.id.ans_four);
		ans_five=(TextView)subview.findViewById(R.id.ans_five);
		sad_text_text=(TextView)subview.findViewById(R.id.sad_text_text);
		green_two=(ImageView)subview.findViewById(R.id.green_two);
		((ImageView) subview.findViewById(R.id.button_next)).setOnClickListener(onClick);
		((ImageView) subview.findViewById(R.id.button_back)).setOnClickListener(onClick);
		ans_four.setOnClickListener(onClick);
		ans_one.setOnClickListener(onClick);
		ans_three.setOnClickListener(onClick);
		ans_two.setOnClickListener(onClick);
		ans_five.setOnClickListener(onClick);
       val=getArguments().getString("answer_one");
       sad_text_text.setText(val+" !");
       if(val.equalsIgnoreCase("Very Poor")){
   		green_two.setImageResource(R.drawable.very_poor);
   	}else if(val.equalsIgnoreCase("Poor")){
   		green_two.setImageResource(R.drawable.poor);
   	}
		}














	OnClickListener onClick = new OnClickListener() {
		fragmentSecondChildOne f2 ;
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.button_next:
				if(!difficultAns.equalsIgnoreCase("")){
				saveDifcultSharedPrefrence(val,difficultAns);
				 f2 = new fragmentSecondChildOne();
				changeFragmentView(f2);
				}else{
					Toast.makeText(getActivity(), "Please select an option first.", Toast.LENGTH_LONG).show();
				}
				break;
			case R.id.button_back:
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
				if(isTwo){
					ans_two.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
					if(difficultAns.contains(ans_two.getText().toString().trim())){
						difficultAns=difficultAns.replace(ans_two.getText().toString().trim(), "");
						
					}
					isTwo=false;
				}else{
					ans_two.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
					if(difficultAns.equalsIgnoreCase("")){
						difficultAns=ans_two.getText().toString().trim();
					}else{
						difficultAns=difficultAns+","+ans_two.getText().toString().trim();
					}
					isTwo=true;
				}
				
			     
				break;
			case R.id.ans_three:
				if(isThree){
					ans_three.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
					if(difficultAns.contains(ans_three.getText().toString().trim())){
						difficultAns=difficultAns.replace(ans_three.getText().toString().trim(), "");
						
					}
					isThree=false;
				}else{
					ans_three.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
					if(difficultAns.equalsIgnoreCase("")){
						difficultAns=ans_three.getText().toString().trim();
					}else{
						difficultAns=difficultAns+","+ans_three.getText().toString().trim();
					}
					isThree=true;
				}
				
				
				
				
				break;
			case R.id.ans_four:
				if(isFour){
					ans_four.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
					if(difficultAns.contains(ans_four.getText().toString().trim())){
						difficultAns=difficultAns.replace(ans_four.getText().toString().trim(), "");
						
					}
					isFour=false;
				}else{
					ans_four.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
					if(difficultAns.equalsIgnoreCase("")){
						difficultAns=ans_four.getText().toString().trim();
					}else{
						difficultAns=difficultAns+","+ans_four.getText().toString().trim();
					}
					isFour=true;
				}
				
			
				
				break;
			case R.id.ans_five:
				if(isFive){
					ans_five.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
					if(difficultAns.contains(ans_five.getText().toString().trim())){
						difficultAns=difficultAns.replace(ans_five.getText().toString().trim(), "");
						
					}
					isFive=false;
				}else{
					ans_five.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
					if(difficultAns.equalsIgnoreCase("")){
						difficultAns=ans_five.getText().toString().trim();
					}else{
						difficultAns=difficultAns+","+ans_five.getText().toString().trim();
					}
					isFive=true;
				}
				
			
				
				break;
				
				
			default:
				break;
			}
		}
	};
	private void setCheck(TextView selected,TextView unselected_one,TextView unselected_two,TextView unselected_three){
		selected.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
		unselected_one.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
		unselected_two.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
		unselected_three.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
	}
	
	private void changeFragmentView(Fragment toFrament) {
		FragmentManager fragmentManager = getActivity()
				.getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left).addToBackStack(null);

		fragmentTransaction.add(R.id.mainframe, toFrament);

		fragmentTransaction.commit();
		((ChangeQuestionColor)getActivity()).changeQuestion(2,"2. "+Constants.q_two);
		// getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
	}

	private void saveDifcultSharedPrefrence(String val,String ans) {
		int modes = Context.MODE_PRIVATE;
		share_pre =getActivity().getSharedPreferences(Constants.PREFRENCE_NAME, modes);
		editor = share_pre.edit();
		editor.putString(Constants.QUESTION_ONE, Constants.q_one);
		editor.putString(Constants.QUESTION_TWO, Constants.sq_one);
		editor.putString(Constants.ANSWER_ONE, val);
		editor.putString(Constants.ANSWER_TWO, ans);
		editor.putString(Constants.QUESTION_THREE,"");
		editor.putString(Constants.ANSWER_THREE, "");
		editor.putString(Constants.QUESTION_FOUR,"");
		editor.putString(Constants.ANSWER_FOUR, "");
		editor.putString(Constants.QUESTION_FIVE,"");
		editor.putString(Constants.ANSWER_FIVE, "");
		editor.putString(Constants.QUESTION_SIX,"");
		editor.putString(Constants.ANSWER_SIX, "");
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
