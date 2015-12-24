package com.hclavitas.childfragment;

import com.example.hclavitas.Constants;
import com.example.hclavitas.Dialog;
import com.example.hclavitas.QuestionFourth;
import com.example.hclavitas.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentSixSecondChild extends Fragment{
	
	View view;
	ImageView green_two;
	EditText ans_free_text;
	TextView ans_one,ans_two,ans_three,ans_four,ans_five,ans_six;
	String difficultAns="",val="",otherAns="";
	  boolean isOne=false,isTwo=false,isThree=false,isFour=false,isFive=false,isSix=false;
	SharedPreferences share_pre;
	Editor editor;
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		getActivity().getWindow().setSoftInputMode(
	            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		view=inflater.inflate(R.layout.fragment_six_child_two,container,false);
	    initView(view);
	    setRetainInstance(true);
		return view;
	}
	
	 @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        if(!difficultAns.equalsIgnoreCase("")){
	        	saveSharedPrefrence(difficultAns);
				
	        }
	        LayoutInflater inflater = LayoutInflater.from(getActivity());
	        populateViewForOrientation(inflater, (ViewGroup) getView());
	    }
	 
	    private void populateViewForOrientation(LayoutInflater inflater, ViewGroup viewGroup) {
	        viewGroup.removeAllViewsInLayout();
	        View subview = inflater.inflate(R.layout.fragment_six_child_two, viewGroup);
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
				difficultAns= mySharedPreferences.getString(Constants.ANSWER_TEN,"");
				Log.d("ans", difficultAns);
				 getText();
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

	
	
	    private void getText(){
	    	if(difficultAns.contains("Doctor/Nurse")){
	    		ans_one.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
	    		isOne=true;
	    	}
	    	if(difficultAns.contains("Lab test")){
	    		ans_two.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
	    		isTwo=true;
	    	}
	    	if(difficultAns.contains("Front desk staff")){
	    		ans_three.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
	    		isThree=true;
	    	}
	    	if(difficultAns.contains("Others")){
	    		ans_four.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
	    		isFour=true;
	    	}
	    	if(difficultAns.contains("Infrastructure")){
	    		ans_five.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
	    		isFive=true;
	    	}
	    	if(difficultAns.contains("Service")){
	    		ans_six.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
	    		isSix=true;
	    	}
	    }
	
	
	
	
	
	
	
	
	
	private void initView(View subview) {
			// TODO Auto-generated method stub
		ans_free_text=(EditText)subview.findViewById(R.id.ans_free_text);
		ans_free_text.setVisibility(View.GONE);
		ans_free_text.setHint("Please mention here.");
		((ImageView) subview.findViewById(R.id.button_next))	.setOnClickListener(onClick);
		((ImageView) subview.findViewById(R.id.button_back)).setOnClickListener(onClick);
		ans_one=(TextView)subview.findViewById(R.id.ans_one);
		ans_five=(TextView)subview.findViewById(R.id.ans_five);
		ans_four=(TextView)subview.findViewById(R.id.ans_four);
		ans_three=(TextView)subview.findViewById(R.id.ans_three);
		ans_six=(TextView)subview.findViewById(R.id.ans_six);
		ans_two=(TextView)subview.findViewById(R.id.ans_two);
		ans_five.setOnClickListener(onClick);
		ans_one.setOnClickListener(onClick);
		ans_two.setOnClickListener(onClick);
		ans_three.setOnClickListener(onClick);
		ans_four.setOnClickListener(onClick);
		ans_six.setOnClickListener(onClick);
		green_two=(ImageView)subview.findViewById(R.id.green_two);
		val=getArguments().getString("value");
		((TextView)subview.findViewById(R.id.sad_text_text)).setText(difficultAns);;
		  if(val.equalsIgnoreCase("Very Poor")){
		   		green_two.setImageResource(R.drawable.very_poor);
		   	}else if(val.equalsIgnoreCase("Poor")){
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
				if(difficultAns.equalsIgnoreCase("")){
			
				Constants.showDialog(getActivity(), "Please!", "Select an option first.");
				}else{
					Log.d("ans", difficultAns);
					saveSharedPrefrence(difficultAns);
				Dialog dialog=new Dialog();
				dialog.show(getFragmentManager(), "d3");
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
					ans_free_text.setVisibility(View.GONE);
					if(difficultAns.contains(ans_four.getText().toString().trim()) ){
						difficultAns=difficultAns.replace(ans_four.getText().toString().trim(), "");
						
					}else if(difficultAns.contains(otherAns) ){
						difficultAns=difficultAns.replace(otherAns, "");
						
					}
					isFour=false;
				}else{
					ans_four.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
					ans_free_text.setVisibility(View.VISIBLE);
					if(difficultAns.equalsIgnoreCase("")){
						if(ans_free_text.getText().toString().trim().equalsIgnoreCase("")){
							difficultAns=ans_four.getText().toString().trim();
						}else{
						difficultAns=ans_free_text.getText().toString().trim();
						otherAns=ans_free_text.getText().toString().trim();
						}
					}else{
						if(ans_free_text.getText().toString().trim().equalsIgnoreCase("")){
							difficultAns=difficultAns+","+ans_four.getText().toString().trim();
						}else{
						difficultAns=difficultAns+","+ans_free_text.getText().toString().trim();
						otherAns=ans_free_text.getText().toString().trim();
						}
					
					}
					isFour=true;
				}
				/*difficultAns=ans_four.getText().toString().trim();
				setCheck(ans_four, ans_two, ans_one,ans_five,ans_six);*/
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
			case R.id.ans_six:
				if(isSix){
					ans_six.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
					if(difficultAns.contains(ans_six.getText().toString().trim())){
						difficultAns=difficultAns.replace(ans_six.getText().toString().trim(), "");
						
					}
					isSix=false;
				}else{
					ans_six.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
					if(difficultAns.equalsIgnoreCase("")){
						difficultAns=ans_six.getText().toString().trim();
					}else{
						difficultAns=difficultAns+","+ans_six.getText().toString().trim();
					}
					isSix=true;
				}
				break;
				

			default:
				break;
			}
		}
	};
	
	
	private void setCheck(TextView selected,TextView unselected_one,TextView unselected_two,TextView unselected_four,TextView unselected_five){
		selected.setCompoundDrawablesWithIntrinsicBounds(R.drawable.checkbox, 0, 0, 0);
		unselected_one.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
		unselected_two.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
		unselected_four.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
		unselected_five.setCompoundDrawablesWithIntrinsicBounds(R.drawable.unchecked, 0, 0, 0);
		
	}
	private void saveSharedPrefrence(String value) {
		int modes = Context.MODE_PRIVATE;
		share_pre =getActivity().getSharedPreferences(Constants.PREFRENCE_NAME, modes);
		editor = share_pre.edit();
		editor.putString(Constants.QUESTION_NINE, Constants.q_six);
		editor.putString(Constants.ANSWER_NINE, val);
		editor.putString(Constants.QUESTION_TEN, Constants.sq_six);
		editor.putString(Constants.ANSWER_TEN, value);
	
		editor.commit();
	}


	
}
