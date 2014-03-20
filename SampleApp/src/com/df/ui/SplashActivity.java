package com.df.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.df.utils.IAppConstants;
import com.df.utils.PrefUtil;
import com.dfdemo.R;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
			ActionBar actionbar = getActionBar();
			actionbar.setTitle("");
			actionbar.setIcon(R.drawable.df_logo_txt);
		}catch(Exception e){

		}
		setContentView(R.layout.activity_splash);
		new StartLogin().execute();
	}

	private class StartLogin extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			long waitTime=1000*1;
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			if(isValidSession()){
				Intent in= new Intent(SplashActivity.this, MainActivity.class);
				startActivity(in);		
			}
			else {
				Intent in=new Intent(SplashActivity.this, LoginActivity.class);
				startActivity(in);
			}		
			finish();
		}
	} 

	private boolean isValidSession(){
		return (PrefUtil.getString(getApplicationContext(), IAppConstants.SESSION_ID, "").length() !=0 );
	}
}