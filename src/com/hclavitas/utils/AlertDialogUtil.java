package com.hclavitas.utils;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.hclavitas.R;

public class AlertDialogUtil {
	public static void showAlertDialog(final Activity context, String message,
			String title, final Intent redirectActivity) {
		if(context!=null){
		final Dialog AlertDialog = new Dialog(new ContextThemeWrapper(context,
				android.R.style.Theme_Translucent));
		AlertDialog.setTitle(title);
		AlertDialog.setCancelable(true);
		AlertDialog.setContentView(R.layout.payment_dialog);
		Button ok = (Button) AlertDialog.findViewById(R.id.btncancel);

		TextView message_tv = (TextView) AlertDialog
				.findViewById(R.id.welcomemessage);
		message_tv.setText(Html.fromHtml(message));
		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.cancel();
			
				if(redirectActivity!=null){
					context.finish();
				redirectActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				context.startActivity(redirectActivity);
				}
			}
		});
		
		AlertDialog.show();
		}
	}
	
	public static ProgressDialog create_progress(Context context) {
		ProgressDialog dialog = null;
		if(context!=null){
		 dialog = new ProgressDialog(context);
		dialog.setCancelable(false);
		dialog.setMessage("Please Wait...");
		dialog.show();
	}
		return dialog;
	}
}