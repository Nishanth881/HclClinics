package com.example.hclavitas;

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

import android.util.Log;

import com.hclavitas.utils.WebConstants;

public class SendRequestToServer {
	
	
	
	SendRequestToServer(){
		
	}
	
	
	public static String getResponce(JSONArray object) {
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


}
