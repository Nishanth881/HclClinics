package com.hclavitas.childfragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hclavitas.ChangeQuestionColor;
import com.example.hclavitas.Constants;
import com.example.hclavitas.R;
import com.hclavitas.database.FetachDataByDataBase;
import com.hclavitas.database.Model_bean;
import com.hclavitas.database.MyDataBaseManager_;
import com.hclavitas.utils.ConnectionCheck;
import com.hclavitas.utils.WebConstants;

public class FragmentOneFirstChild extends Fragment implements OnClickListener{
	
	View childView;
	FragmentManager fragmentManager;
	ImageView lay_one, lay_two, lay_three, relativeLayout1,lay_four,lay,lay_five,ans_lay_two;
	 String difficultAns="";
	SharedPreferences share_pre;
	Editor editor;ConnectionCheck _check;
	ArrayList<Model_bean> md;
	TextView sad_text_text;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getActivity().getWindow().setSoftInputMode(
	            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		childView=inflater.inflate(R.layout.infltae_one_frag, container,false);
		initView(childView);
	    setRetainInstance(true);
		_check=new ConnectionCheck(getActivity());
	
		FetachDataByDataBase fd=new FetachDataByDataBase();
		 md=new ArrayList<Model_bean>();
		md.addAll(fd.FEtchdatafrom_db(getActivity()));
		
		if(md.size()>0){
			if(_check.checkConnection()){
		    Async();
		}}
		
		return childView;
	}
	
	
	private void initView(View subview){
		
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
	

	private void Async() {
		// TODO Auto-generated method stub
		Thread thread=new  Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String result=	getResponce(createObject());
				getActivity().runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						MyDataBaseManager_ sb=new MyDataBaseManager_(getActivity());
						sb.deleteTable();	
					}
				});
			
				Log.d("result", result);
			}
		});;
		thread.start();
	}
	
	


	@Override
	public void onClick(View v) {
		
		fragmentSecondChildOne three=new fragmentSecondChildOne();
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
		
		  fragmentManager = getActivity().getSupportFragmentManager();
	      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
	      
	      
	      FragmentTwoFirstChild pm_fragment = new FragmentTwoFirstChild();
	      Bundle b=new Bundle();
	      b.putString("answer_one", difficultAns2);
	      pm_fragment.setArguments(b);
	         fragmentTransaction.addToBackStack(null).add(R.id.mainframe, pm_fragment);
	      
	      fragmentTransaction.commit();
	}
	
	private void changeFragmentView(Fragment toFrament){
	 fragmentManager = getActivity().getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left).addToBackStack(null);

		fragmentTransaction.add(R.id.mainframe, toFrament);

		fragmentTransaction.commit();
		((ChangeQuestionColor)getActivity()).changeQuestion(2,"2. "+Constants.q_two);
		//getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
	}
	
	
	
	
	private void saveSharedPrefrence(String val) {
		int modes = Context.MODE_PRIVATE;
		share_pre =getActivity().getSharedPreferences(Constants.PREFRENCE_NAME, modes);
		editor = share_pre.edit();
		editor.putString(Constants.QUESTION_ONE, Constants.q_one);
		editor.putString(Constants.ANSWER_ONE, val);
		editor.putString(Constants.QUESTION_TWO, Constants.sq_one);
		editor.putString(Constants.ANSWER_TWO, "");
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
	
	
	
	
	
	 
	 private String getResponce(JSONArray object) {
			String result="";
			try {
				
				Log.e("array", object.toString());
				HttpClient client = new DefaultHttpClient();  
				HttpPost post = new HttpPost(WebConstants.SUBMIT_URL);   
				post.setHeader("Content-type", "application/json");
		  	     	 try {
		  	     		  // StringEntity se = new StringEntity( "data" + object.toString());  
		                   // se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		                  //  post.setEntity(se);
		  	     		 
		  	     		 
		                    List<NameValuePair> pairs = new ArrayList<NameValuePair>();  

					pairs.add(new BasicNameValuePair("data", object.toString()));

					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs,"UTF-8");
					post.setEntity(entity); 
		  	     		 Log.d("send", object.toString());
		   	   HttpResponse response = client.execute(post);

		          Log.e("responce", response.toString());
		          if( response != null )  { // 9. receive response as inputStream
		            InputStream  inputStream = response.getEntity().getContent();
		              
		              // 10. convert inputstream to string
		              if(inputStream != null)
		                  result = convertInputStreamToString(inputStream);
		              else
		                  result = "Did not work!";}
		   	    // clear text box
		     } catch (ClientProtocolException e) {
		         // TODO Auto-generated catch block
		    	 e.printStackTrace();
		     } catch (IOException e) {
		         // TODO Auto-generated catch block
		    	 e.printStackTrace();
		     }

			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return result;
			
		
	}
		
		
		 private static String convertInputStreamToString(InputStream inputStream) throws IOException{
		        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
		        String line = "";
		        String result = "";
		        while((line = bufferedReader.readLine()) != null)
		            result += line;
		 
		        inputStream.close();
		        return result;
		 
		    }   
		 JSONArray createObject(){
			 JSONArray arr=new JSONArray();
			 
				JSONArray array=new JSONArray();
				JSONObject objectInner=new JSONObject();
				try {
					
				for(int i=0;i<md.size();i++){
					
					Model_bean model=md.get(i);
					JSONObject object=new JSONObject();
					object.put("q1", model.getAns_one());
					if(model.getAns_one().equalsIgnoreCase("Difficult") || model.getAns_one().equalsIgnoreCase("Very Difficult")){
					object.put("qa1", model.getAns_two());
						}else{
							object.put("qa1", "");	
						}
					object.put("q2", model.getAns_three());
					if(model.getAns_three().equalsIgnoreCase("No")){
					object.put("qa2",model.getAns_four());
					}else{
						object.put("qa2", "");	
					}
					object.put("q3", model.getAns_five());
					if( model.getAns_five().equalsIgnoreCase("Not Good") ||  model.getAns_five().equalsIgnoreCase("Good")){
					object.put("qa3",model.getAns_six());
						}else{
							object.put("qa3", "");	
						}
					object.put("q4",model.getAns_seven());
					object.put("q5", model.getAns_eight());
					object.put("q6", model.getAns_nine());
					if( model.getAns_nine().equalsIgnoreCase("Very Poor") ||  model.getAns_nine().equalsIgnoreCase("Poor")){
					object.put("qa4",  model.getAns_ten());
						}else{
							object.put("qa4", "");
						}
					
	
				
				object.put("userName",model.getUser_name());
				object.put("userMobile",model.getPhone_nu());
				object.put("clusterName",model.getFacility_type());
				object.put("clusterNumber",model.getClusterMobileNumber());
				object.put("clusterEmail","");
		        object.put("managerName", model.getManagerName());
				arr.put(i,object);
			   
			
				}
				objectInner.put("data", arr);
				} catch (Exception e) {
					// TODO: handle exception
				}
			return arr;
			
			
			 
		}
	
	

}
