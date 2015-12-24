package com.hclavitas.childfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.hclavitas.ChangeQuestionColor;
import com.example.hclavitas.Constants;
import com.example.hclavitas.QuestionFourth;
import com.example.hclavitas.R;

public class FragThreeChildOne extends Fragment implements OnClickListener{
	View view;
	String difficultAns="",question_five="How would you rate us on your waiting time?";
	ImageView lay_one, lay_two, lay_three,lay_forth,lay_fifth;
	SharedPreferences share_pre;
	Editor editor;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		getActivity().getWindow().setSoftInputMode(
	            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		view=inflater.inflate(R.layout.frag_third_child_one, container,false);
		initView(view);
		setRetainInstance(true);
		
		
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
	        View subview = inflater.inflate(R.layout.frag_third_child_one, viewGroup);
initView(subview);
	        // Find your buttons in subview, set up onclicks, set up callbacks to your parent fragment or activity here.
	        
	        // You can create ViewHolder or separate method for that.
	        // example of accessing views: TextView textViewExample = (TextView) view.findViewById(R.id.text_view_example);
	        // textViewExample.setText("example");
	    }
	
	

	private void initView(View subview) {
			// TODO Auto-generated method stub
		lay_three = (ImageView) subview.findViewById(R.id.average);
		lay_one = (ImageView) subview.findViewById(R.id.green);
		lay_two = (ImageView) subview.findViewById(R.id.good);
		lay_forth = (ImageView) subview.findViewById(R.id.yelow);
		lay_fifth = (ImageView) subview.findViewById(R.id.red_smiley);

		lay_one.setOnClickListener(this);
		lay_three.setOnClickListener(this);
		lay_two.setOnClickListener(this);
		lay_fifth.setOnClickListener(this);
		lay_forth.setOnClickListener(this);
		}

	@Override
	public void onClick(View v) {
		
		QuestionFourth three=new QuestionFourth();
          switch (v.getId()) {
          
          
          case R.id.red_smiley:
        	  difficultAns="Very Poor";
        	  changeQuestion(difficultAns);
  				break;
  			case R.id.yelow:
  				difficultAns="Poor";
          	  changeQuestion(difficultAns);
  				break;
  			case R.id.green:
  				saveSharedPrefrence("Excellent");
  				changeFragmentView(three);
  			break;
  			case R.id.good:
  				saveSharedPrefrence("Good");
  				changeFragmentView(three);
  				break;
  			case R.id.average:
  				saveSharedPrefrence("Average");
  				changeFragmentView(three);
  				break;
  			
		default:
			break;
		}		
	}
	
	
	private void changeQuestion(String difficultAns2){
		
		FragmentManager  fragmentManager = getActivity().getSupportFragmentManager();
	      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
	      Bundle b=new Bundle();
	      b.putString("value", difficultAns2);
	      
	      FragmentthirdChildTwo pm_fragment = new FragmentthirdChildTwo();
	      pm_fragment.setArguments(b);
	         fragmentTransaction.addToBackStack(null).add(R.id.mainframe, pm_fragment);
	      
	      fragmentTransaction.commit();
	}
	
	private void changeFragmentView(Fragment toFrament){
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left).addToBackStack(null);

		fragmentTransaction.add(R.id.mainframe, toFrament);

		fragmentTransaction.commit();
		((ChangeQuestionColor)getActivity()).changeQuestion(4,"4."+Constants.q_four);
		//getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
	}

	private void saveSharedPrefrence(String val) {
		int modes = Context.MODE_PRIVATE;
		share_pre =getActivity().getSharedPreferences(Constants.PREFRENCE_NAME, modes);
		editor = share_pre.edit();
		editor.putString(Constants.QUESTION_FIVE, Constants.q_three);
		editor.putString(Constants.ANSWER_FIVE, val);
		editor.putString(Constants.QUESTION_SIX, Constants.sq_three);
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
