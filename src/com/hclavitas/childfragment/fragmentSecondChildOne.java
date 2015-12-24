package com.hclavitas.childfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.hclavitas.ChangeQuestionColor;
import com.example.hclavitas.Constants;
import com.example.hclavitas.R;

public class fragmentSecondChildOne extends Fragment{
	View view;
	LinearLayout lay_one, lay_two, lay_three,relativeLayout1;
	SharedPreferences share_pre;
	Editor editor;
	String question_three="Did you find the specialist and services you were looking for?",negartive_ans="";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getActivity().getWindow().setSoftInputMode(
	            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		view=inflater.inflate(R.layout.frag_second_child_one, container ,false);
		setRetainInstance(true);
		lay_three = (LinearLayout) view.findViewById(R.id.difficult_lay);
		lay_one = (LinearLayout) view.findViewById(R.id.effortless_lay);
		
		lay_one.setOnClickListener(onClick);

		lay_three.setOnClickListener(onClick);
		return view;
	}
	 @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        LayoutInflater inflater = LayoutInflater.from(getActivity());
	        populateViewForOrientation(inflater, (ViewGroup) getView());
	    }
	 
	    private void populateViewForOrientation(LayoutInflater inflater, ViewGroup viewGroup) {
	        viewGroup.removeAllViewsInLayout();
	        View subview = inflater.inflate(R.layout.frag_second_child_one, viewGroup);
	        lay_three = (LinearLayout) subview.findViewById(R.id.difficult_lay);
			lay_one = (LinearLayout) subview.findViewById(R.id.effortless_lay);
			
			lay_one.setOnClickListener(onClick);

			lay_three.setOnClickListener(onClick);
	        // Find your buttons in subview, set up onclicks, set up callbacks to your parent fragment or activity here.
	        
	        // You can create ViewHolder or separate method for that.
	        // example of accessing views: TextView textViewExample = (TextView) view.findViewById(R.id.text_view_example);
	        // textViewExample.setText("example");
	    }
	
	OnClickListener onClick=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.difficult_lay:
				
				negartive_ans="No";
				changeQuestion(negartive_ans);
				break;
			case R.id.effortless_lay:
				saveSharedPrefrence("Yes");
				FragThreeChildOne fm=new FragThreeChildOne();
		         changeFragmentView(fm);
		        
			default:
				break;
			}
			
		}
	};
	
	
	class A extends AsyncTask<Void, String,Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			changeQuestion(negartive_ans);
			super.onPostExecute(result);
		}
		
		
		
		
		
	}
	private void changeQuestion(String negartive_ans2){
		
		FragmentManager  fragmentManager = getActivity().getSupportFragmentManager();
	      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
	      
	      
	      fragmentSecondTwoChild pm_fragment = new fragmentSecondTwoChild();
	      Bundle b=new Bundle();
	      b.putString("ans_second",negartive_ans2);
	      pm_fragment.setArguments(b);
	         fragmentTransaction.addToBackStack(null).add(R.id.mainframe, pm_fragment);
	      
	      fragmentTransaction.commit();
	}
	private void changeFragmentView(Fragment toFrament){
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left).addToBackStack(null);

		fragmentTransaction.add(R.id.mainframe, toFrament);

		fragmentTransaction.commit();
		((ChangeQuestionColor)getActivity()).changeQuestion(3,"3. "+Constants.q_three);
		//getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
	}

	private void saveSharedPrefrence(String val) {
		int modes = Context.MODE_PRIVATE;
		share_pre =getActivity().getSharedPreferences(Constants.PREFRENCE_NAME, modes);
		editor = share_pre.edit();
		editor.putString(Constants.QUESTION_THREE, Constants.q_two);
		editor.putString(Constants.ANSWER_THREE, val);
		editor.putString(Constants.QUESTION_FOUR, Constants.sq_two);
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
