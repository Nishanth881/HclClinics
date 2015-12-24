package com.hclavitas.utils;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionCheck {
	ConnectivityManager cm;
	NetworkInfo activeNetwork;
	boolean isConnected;
	Context _context;
	public ConnectionCheck(Context context)
	{
		this._context=context;
	}
public boolean checkConnection()
{
	
	if(_context!=null){
	cm =(ConnectivityManager)_context.getSystemService(Context.CONNECTIVITY_SERVICE);
	activeNetwork = cm.getActiveNetworkInfo();
	if (null ==activeNetwork)
	    return false;
    isConnected = activeNetwork.isConnectedOrConnecting();
	return isConnected;
	}else{
		return false;
	}
	
}
}
