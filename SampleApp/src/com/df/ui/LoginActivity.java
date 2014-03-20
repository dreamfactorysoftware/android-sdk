package com.df.ui;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.df.utils.IAppConstants;
import com.df.utils.PrefUtil;
import com.dfdemo.R;
import com.dreamfactory.api.UserApi;
import com.dreamfactory.model.Login;
import com.dreamfactory.model.Session;

public class LoginActivity extends Activity {//implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener{
	private EditText editTextUserId, editTextUserPassword, editTextDspUrl;
	private Button btnSubmitLogin;
	private ProgressDialog progressDialog;
	private String userID,userPass, dspUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
			ActionBar actionbar = getActionBar();
			actionbar.setTitle("");
			actionbar.setIcon(R.drawable.df_logo_txt);
		}catch(Exception e){

		}
		setContentView(R.layout.login);
		progressDialog = new ProgressDialog(LoginActivity.this);
		progressDialog.setMessage(getText(R.string.loading_message));

		editTextDspUrl = (EditText)findViewById(R.id.edit_text_dsp);
		editTextUserId = (EditText) findViewById(R.id.edit_text_user_id);
		editTextUserPassword = (EditText) findViewById(R.id.edit_text_user_pass);
		
		editTextUserPassword.setTypeface(Typeface.DEFAULT);
		editTextUserPassword.setTransformationMethod(new PasswordTransformationMethod());

		btnSubmitLogin = (Button) findViewById(R.id.button_submit_login);
		userID = PrefUtil.getString(getApplicationContext(), IAppConstants.EMAIL, "");
		userPass = PrefUtil.getString(getApplicationContext(), IAppConstants.PWD, "");
		
		dspUrl = PrefUtil.getString(getApplicationContext(), IAppConstants.DSP_URL, "");
		
		editTextUserId.setText(userID);
		editTextUserPassword.setText(userPass);
		editTextDspUrl.setText(dspUrl);


		btnSubmitLogin.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				if (isValidEntries()){
					LoginTask loginTask = new LoginTask();
					loginTask.execute();
				}
			}
		});
	}

	public Boolean isValidEntries(){
		String User_ID = editTextUserId.getText().toString();
		String User_Pass = editTextUserPassword.getText().toString();
		dspUrl = editTextDspUrl.getText().toString();

		if(User_ID.length()==0){
			Toast.makeText(LoginActivity.this, getText(R.string.id_null), Toast.LENGTH_LONG).show();
			return false;
		}
		if(User_Pass.length()==0){
			Toast.makeText(LoginActivity.this,getText(R.string.pw_null),Toast.LENGTH_LONG).show();
			return false;
		}
		if(dspUrl.length()==0){
			Toast.makeText(LoginActivity.this,getText(R.string.dsp_null),Toast.LENGTH_LONG).show();
			return false;
		}
		else {	
			return true;
		}
	}

	class LoginTask extends AsyncTask<Void, Void, String>{

		@Override
		protected String doInBackground(Void... params) {

			UserApi userApi = new UserApi();
			userApi.addHeader("X-DreamFactory-Application-Name", IAppConstants.TABLE_NAME);
			userApi.setBasePath(dspUrl + IAppConstants.DSP_URL_SUFIX);
			Login login = new Login();
			login.setEmail(editTextUserId.getText().toString());
			login.setPassword(editTextUserPassword.getText().toString());
			try {
				Session session =	userApi.login(login);
				String session_id = session.getSession_id();
				
				PrefUtil.putString(getApplicationContext(), IAppConstants.DSP_URL, dspUrl);
				PrefUtil.putString(getApplicationContext(), IAppConstants.SESSION_ID, session_id);
				PrefUtil.putString(getApplicationContext(), IAppConstants.EMAIL, editTextUserId.getText().toString());
				PrefUtil.putString(getApplicationContext(), IAppConstants.PWD, editTextUserPassword.getText().toString());
			} catch (Exception e) {
				return e.getMessage();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			progressDialog.cancel();
			if (result !=null){
				String errorMsg = "";
				try {
					JSONObject jObj = new JSONObject(result);
					JSONArray jArray = jObj.getJSONArray("error");
					JSONObject obj = jArray.getJSONObject(0);
					errorMsg = obj.getString("message");
				} catch (JSONException e) {
					errorMsg = result;
				}
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
				alertDialog.setTitle("Message...").setMessage(errorMsg).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();					
					}
				});
				alertDialog.show();	
			}
			else {
				Intent in= new Intent(LoginActivity.this,MainActivity.class);
				startActivity(in);				
				finish();
			}
		}
		@Override
		protected void onPreExecute() {
			progressDialog.show();
		}
	} 
}
