package com.df.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.df.utils.IAppConstants;
import com.df.utils.PrefUtil;
import com.dfdemo.R;
import com.dreamfactory.api.FilesApi;
import com.dreamfactory.api.UserApi;
import com.dreamfactory.model.Login;
import com.dreamfactory.model.Session;

public class SplashActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		new StartLogin().execute();
	}

	private class StartLogin extends AsyncTask<Void, Void, Boolean>{
		@Override
		protected Boolean doInBackground(Void... params) {
			boolean validSession = false;
			String oldSessionKey = PrefUtil.getString(getApplicationContext(), IAppConstants.SESSION_ID, null);
			String userID = PrefUtil.getString(getApplicationContext(), IAppConstants.EMAIL, null);
			String userPass = PrefUtil.getString(getApplicationContext(), IAppConstants.PWD, null);
			String dspUrl = PrefUtil.getString(getApplicationContext(), IAppConstants.DSP_URL, null);

			if(oldSessionKey == null &&  userID == null && userPass == null && dspUrl == null){ 
				// show splash for 2 secs and go to login
				try { 
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}else{ // previously logged in, check if still valid
				try{
					UserApi userApi = new UserApi();
					userApi.addHeader("X-DreamFactory-Application-Name", IAppConstants.APP_NAME);
					Login login = new Login();
					login.setEmail(userID);
					login.setPassword(userPass);
					userApi.setBasePath(dspUrl + IAppConstants.DSP_URL_SUFIX);
					Session session = userApi.login(login);
					PrefUtil.putString(getApplicationContext(), IAppConstants.SESSION_ID, session.getSession_id());
					validSession = true;
				}catch(Exception e){
					PrefUtil.putString(getApplicationContext(), IAppConstants.SESSION_ID, "");
					PrefUtil.putString(getApplicationContext(), IAppConstants.EMAIL, "");
					PrefUtil.putString(getApplicationContext(), IAppConstants.PWD, "");
				}
			}
			return Boolean.valueOf(validSession);
		}
		@Override
		protected void onPostExecute(Boolean isVaildSession) {
			if(isVaildSession.booleanValue()){
				Intent in= new Intent(SplashActivity.this, ChooseDemoActivity.class);
				startActivity(in);		
			}
			else {
				Intent in=new Intent(SplashActivity.this, LoginActivity.class);
				startActivity(in);
			}		
			finish();
		}
	} 
}
