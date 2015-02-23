package com.df.ui;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

import com.df.models.DemoLoginModel;
import com.df.utils.IAppConstants;
import com.df.utils.PrefUtil;
import com.dfdemo.R;
import com.dreamfactory.api.UserApi;
import com.dreamfactory.client.ApiException;
import com.dreamfactory.client.ApiInvoker;
import com.dreamfactory.model.Login;
import com.dreamfactory.model.Session;

public class LoginActivity extends BaseActivity {//implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener{
	private EditText editTextUserId, editTextUserPassword, editTextDspUrl;
	private Button btnSubmitLogin;
	private ProgressDialog progressDialog;
	private String userID,userPass, dspUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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

		//		dspUrl = "http://dsp-x.cloud.dreamfactory.com";
		

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
			try {
				String session_id = loginServiceDemoApproach_1();
				//String session_id = loginServiceDemoApproach_2();
				//String session_id = loginServiceDemoApproach_3();
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
		protected void onPostExecute(String errorMessage) {
			progressDialog.cancel();
			if (errorMessage !=null){
				String errorMsg = "";
				try {
					JSONObject jObj = new JSONObject(errorMessage);
					JSONArray jArray = jObj.getJSONArray("error");
					JSONObject obj = jArray.getJSONObject(0);
					errorMsg = obj.getString("message");
				} catch (JSONException e) {
					errorMsg = errorMessage;
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
				Intent in= new Intent(LoginActivity.this,ChooseDemoActivity.class);
				startActivity(in);				
				finish();
			}
		}
		@Override
		protected void onPreExecute() {
			progressDialog.show();
		}
	} 

	/**
	 * This is a df user Api example. It explains how to use user api by using sdk model classes
	 * 
	 */
	private String loginServiceDemoApproach_1() throws ApiException{
		UserApi userApi = new UserApi();
		userApi.addHeader("X-DreamFactory-Application-Name", IAppConstants.APP_NAME);
		userApi.setBasePath(dspUrl + IAppConstants.DSP_URL_SUFIX);
		Login login = new Login();
		login.setEmail(editTextUserId.getText().toString());
		login.setPassword(editTextUserPassword.getText().toString());
		Session session =	userApi.login(login);
		return session.getSession_id();
	}

	/**
	 * This is another way to user use df sdk to connect to backend server by passing json formated string
	 * This is generic api inoker where you can pass all required header, query parms and body as json formated string
	 * 
	 * This approach is more useful when we you are creating additional services apart from Dreamfactry default services.
	 * apiInvoikerreturns string response and from here you are free to play with data
	 *
	 * For custom service you can also create your owd model class (ex Login) using JacksonAnnotation and directly pass to APIinvoker as described in function loginServiceDemoApproach_3
	 */
	private String loginServiceDemoApproach_2() throws Exception{
		ApiInvoker invoker  = new ApiInvoker();
		invoker.addDefaultHeader("X-DreamFactory-Application-Name", IAppConstants.APP_NAME);
		// create path and map variables
		String serviceName = "user";
		String endPoint = "session";
		String path = new StringBuilder("/").append(serviceName).append("/").append(endPoint).append("/").toString();
		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();
		String contentType = "application/json";

		JSONObject obj = new JSONObject();
		obj.put("email", userID);
		obj.put("password", userPass);
		String requestStringFromJsonObject = obj.toString();
		String response = invoker.invokeAPI(dsp_url, path, "POST", queryParams, requestStringFromJsonObject, headerParams, contentType);
		JSONObject object = new JSONObject(response);
		return object.getString("session_id");
	}

	/**
	 * This is sample function to demonstrate how to call custom service by passing model object 
	 */ 

	private String loginServiceDemoApproach_3() throws Exception{
		ApiInvoker invoker  = new ApiInvoker();
		invoker.addDefaultHeader("X-DreamFactory-Application-Name", IAppConstants.APP_NAME);
		// create path and map variables
		String serviceName = "user";
		String endPoint = "session";
		String path = new StringBuilder("/").append(serviceName).append("/").append(endPoint).append("/").toString();
		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();
		String contentType = "application/json";

		DemoLoginModel login = new DemoLoginModel();
		login.setEmail(editTextUserId.getText().toString());
		login.setPassword(editTextUserPassword.getText().toString());
		
		String response = invoker.invokeAPI(dsp_url, path, "POST", queryParams, login, headerParams, contentType);
		
		// here you can also convert this response to model class
		
		Session session = (Session) ApiInvoker.deserialize(response, "", Session.class);

		JSONObject object = new JSONObject(response);
		return object.getString("session_id");
	}


}
