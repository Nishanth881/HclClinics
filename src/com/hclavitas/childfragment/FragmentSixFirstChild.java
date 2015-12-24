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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.hclavitas.Constants;
import com.example.hclavitas.Dialog;
import com.example.hclavitas.R;

public class FragmentSixFirstChild extends Fragment implements OnClickListener{
	
	View childView;
	FragmentManager fragmentManager;
	ImageView lay_one, lay_two, lay_three, relativeLayout1,lay_four,lay,lay_five,ans_lay_two;
	SharedPreferences share_pre;
	Editor editor;
	String question="How would you rate your visit today?",negative_ans="";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getActivity().getWindow().setSoftInputMode(
	            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		childView=inflater.inflate(R.layout.infltae_one_frag, container,false);
	initView(childView);
	setRetainInstance(true);
	

		return childView;
	}
	 @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        LayoutInflater inflater = LayoutInflater.from(getActivity());
	        populateViewForOrientation(inflater, (ViewGroup) getView());
	    }
	 
	    private void populateViewForOrientation(LayoutInflater inflater, ViewGroup viewGroup) {
	        viewGroup.removeAllViewsInLayout();
	        View subview = inflater.inflate(R.layout.infltae_one_frag, viewGroup);
initView(subview);
	        // Find your buttons in subview, set up onclicks, set up callbacks to your parent fragment or activity here.
	        
	        // You can create ViewHolder or separate method for that.
	        // example of accessing views: TextView textViewExample = (TextView) view.findViewById(R.id.text_view_example);
	        // textViewExample.setText("example");
	    }

	private void initView(View subview) {
			// TODO Auto-generated method stub
		lay_one = (ImageView) subview.findViewById(R.id.green);
		lay_four = (ImageView) subview.findViewById(R.id.yelow);
		lay_two = (ImageView) subview.findViewById(R.id.good);
		lay_five = (ImageView) subview.findViewById(R.id.red_smiley);
		lay_three = (ImageView) subview.findViewById(R.id.average);
		lay_one.setOnClickListener(this);
		lay_three.setOnClickListener(this);
		lay_two.setOnClickListener(this);
		lay_five.setOnClickListener(this);
		lay_four.setOnClickListener(this);
		}
	@Override
	public void onClick(View v) {
		
		Dialog dialog=new Dialog();
          switch (v.getId()) {
          
          
          case R.id.red_smiley:
        	  
        	  changeQuestion("Very Poor");
  				break;
  			case R.id.yelow:
          	  changeQuestion("Poor");
  				break;
  			case R.id.green:
  				saveSharedPrefrence("Excellent");
  				dialog.show(getFragmentManager(), "d1");
  			break;
  			case R.id.good:
  				saveSharedPrefrence("Good");
  				dialog.show(getFragmentManager(), "d2");
  				break;
  			case R.id.average:
  				saveSharedPrefrence("Average");
  				dialog.show(getFragmentManager(), "d3");
  				  				break;
  			
		default:
			break;
		}		
	}
	private void saveSharedPrefrence(String val) {
		int modes = Context.MODE_PRIVATE;
		share_pre =getActivity().getSharedPreferences(Constants.PREFRENCE_NAME, modes);
		editor = share_pre.edit();
		editor.putString(Constants.QUESTION_NINE, Constants.q_six);
		editor.putString(Constants.ANSWER_NINE, val);
		editor.putString(Constants.QUESTION_TEN, Constants.sq_six);
		editor.putString(Constants.ANSWER_TEN, "");
	
		editor.commit();
	}

	
	private void changeQuestion(String string){
		
		  fragmentManager = getActivity().getSupportFragmentManager();
	      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
	      Bundle b=new Bundle();
	      b.putString("value", string);
	      
	      FragmentSixSecondChild pm_fragment = new FragmentSixSecondChild();
	      pm_fragment.setArguments(b);
	         fragmentTransaction.addToBackStack(null).add(R.id.mainframe, pm_fragment);
	      
	      fragmentTransaction.commit();
	}
	
	

}
