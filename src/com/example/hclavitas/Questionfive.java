package com.example.hclavitas;

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
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hclavitas.childfragment.FragmentSixFirstChild;

public class Questionfive extends Fragment {

	ImageView lay_one, lay_two, lay_three;

	View view;
	String facility;
	SharedPreferences share_pre;
	Editor editor;
    String difficultAns="",question="How courteous was our staff?";
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container, Bundle savedInstanceState) {
		getActivity().getWindow().setSoftInputMode(
	            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		view = inflater.inflate(R.layout.fifth_question, container, false);
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
	        View subview = inflater.inflate(R.layout.fifth_question, viewGroup);
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
		((ImageView)subview.findViewById(R.id.yelow)).setOnClickListener(onClick);
		((ImageView)subview.findViewById(R.id.red_smiley)).setOnClickListener(onClick);
		lay_one.setOnClickListener(onClick);
		lay_three.setOnClickListener(onClick);
		lay_two.setOnClickListener(onClick);
		getPrefrence();
		
		}


	private void getPrefrence() {
		try {
			int modes = Context.MODE_PRIVATE;

			SharedPreferences mySharedPreferences = getActivity().getSharedPreferences(Constants.PREFRENCE_NAME, modes);
			facility = mySharedPreferences.getString(Constants.FACILITY_NAME,"");
			Log.e("user_id", facility);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	OnClickListener onClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			FragmentSixFirstChild six=new FragmentSixFirstChild();
			switch (v.getId()) {
			case R.id.good:
				saveSharedPrefrence("Good");
				changeFragmentView(six);
				break;
			
			case R.id.average:
				saveSharedPrefrence("Average");
				changeFragmentView(six);
				break;
			
			case R.id.yelow:
				saveSharedPrefrence("Bad");
				changeFragmentView(six);
				break;
			case R.id.green:
				saveSharedPrefrence("Excellent");
				changeFragmentView(six);
				break;
			case R.id.red_smiley:
				saveSharedPrefrence("Very Bad");
				changeFragmentView(six);
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
		((ChangeQuestionColor)getActivity()).changeQuestion(6,"6. "+Constants.q_six);
		// getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
	}

	private void saveSharedPrefrence(String val) {
		int modes = Context.MODE_PRIVATE;
		share_pre =getActivity().getSharedPreferences(Constants.PREFRENCE_NAME, modes);
		editor = share_pre.edit();
		editor.putString(Constants.QUESTION_EIGHT, Constants.q_five);
		editor.putString(Constants.ANSWER_EIGHT, val);
		editor.putString(Constants.QUESTION_NINE,"");
		editor.putString(Constants.ANSWER_NINE, "");
		editor.putString(Constants.QUESTION_TEN,"");
		editor.putString(Constants.ANSWER_TEN, "");
	
		editor.commit();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
	    imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
	}
	
	
	
}
