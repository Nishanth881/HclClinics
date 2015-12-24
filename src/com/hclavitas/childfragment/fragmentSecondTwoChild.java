package com.hclavitas.childfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hclavitas.ChangeQuestionColor;
import com.example.hclavitas.Constants;
import com.example.hclavitas.R;

public class fragmentSecondTwoChild extends Fragment{
	
	EditText sad_question;
    String ans_two=""; 
	View view;
	TextView sad_text_text;
	SharedPreferences share_pre;
	Editor editor;
    String saveVal="",question_four="What speciality/service you were looking for?",question_three="Did you find the specialist and services you were looking for?";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getActivity().getWindow().setSoftInputMode(
	            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		view=inflater.inflate(R.layout.second_fraf_child_two, container,false);
		initView(view);
		setRetainInstance(true);
		

		return view;
	}
	 @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	       InputMethodManager imm= (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		    imm.hideSoftInputFromWindow(sad_question.getWindowToken(), 0);
	        String difficultAns=sad_question.getText().toString().trim();
			if(!difficultAns.equalsIgnoreCase("")){
				saveDifcultSharedPrefrence(ans_two,difficultAns);
			}
	        LayoutInflater inflater = LayoutInflater.from(getActivity());
	        populateViewForOrientation(inflater, (ViewGroup) getView());
	    }
	 
	    private void populateViewForOrientation(LayoutInflater inflater, ViewGroup viewGroup) {
	        viewGroup.removeAllViewsInLayout();
	        View subview = inflater.inflate(R.layout.second_fraf_child_two, viewGroup);
            initView(subview);
            getPrefrence();
	    }
	    private void getPrefrence() {
			try {
				int modes = Context.MODE_PRIVATE;

				SharedPreferences mySharedPreferences =getActivity().getSharedPreferences(Constants.PREFRENCE_NAME, modes);
				sad_question.setText( mySharedPreferences.getString(Constants.ANSWER_FOUR,""));
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

	
	private void initView(View subview) {
			// TODO Auto-generated method stub
		sad_question=(EditText)subview.findViewById(R.id.sad_question);
		sad_question.setHint(Constants.sq_two);
		((ImageView) subview.findViewById(R.id.button_next)).setOnClickListener(onClick);
		((ImageView) subview.findViewById(R.id.button_back)).setOnClickListener(onClick);
	//	sad_text_text=(TextView)view.findViewById(R.id.sad_text_text);

		ans_two=getArguments().getString("ans_second");
	      // sad_text_text.setText(ans_two+" !");
		}
	OnClickListener onClick = new OnClickListener() {
		FragThreeChildOne fm;
        InputMethodManager imm ;
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
				case R.id.button_next:
				imm= (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				    imm.hideSoftInputFromWindow(sad_question.getWindowToken(), 0);
				String difficultAns=sad_question.getText().toString().trim();
				if(!difficultAns.equalsIgnoreCase("")){
					saveDifcultSharedPrefrence(ans_two,difficultAns);
				 fm=new FragThreeChildOne();
				 changeFragmentView(fm);
				 sad_question.setText("");
				}else{
					Constants.showDialog(getActivity(), "Please!", "Please give the answer first.");
				
				}
				break;
			case R.id.button_back:
				sad_question.setText("");
				getActivity().getSupportFragmentManager().popBackStack();
				  imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				    imm.hideSoftInputFromWindow(sad_question.getWindowToken(), 0);
			
				break;
			default:
				break;
			}
		}
	};

	private void changeFragmentView(Fragment toFrament){
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left).addToBackStack(null);

		fragmentTransaction.add(R.id.mainframe, toFrament);

		fragmentTransaction.commit();
		((ChangeQuestionColor)getActivity()).changeQuestion(3,"3. "+Constants.q_three);
		//getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
	}

	private void saveDifcultSharedPrefrence(String val,String ans) {
		int modes = Context.MODE_PRIVATE;
		share_pre =getActivity().getSharedPreferences(Constants.PREFRENCE_NAME, modes);
		editor = share_pre.edit();
		editor.putString(Constants.QUESTION_THREE, Constants.q_two);
		editor.putString(Constants.QUESTION_FOUR, Constants.sq_two);
		editor.putString(Constants.ANSWER_THREE, val);
		editor.putString(Constants.ANSWER_FOUR, ans);
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
