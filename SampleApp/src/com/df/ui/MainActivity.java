package com.df.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.df.adapters.ToDoAdapter;
import com.df.utils.IAppConstants;
import com.df.utils.PrefUtil;
import com.dfdemo.R;
import com.dreamfactory.api.DbApi;
import com.dreamfactory.model.Record;
import com.dreamfactory.model.Records;

public class MainActivity extends Activity {
	private Button buttonAdd,buttonLogout;
	private EditText editTextAddTask;
	private ListView list_view;
	private ProgressDialog progressDialog;
	private ToDoAdapter adapter = null;
	private String dsp_url;
	private String session_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
			ActionBar actionbar = getActionBar();
			actionbar.setTitle("");
			actionbar.setIcon(R.drawable.df_logo_txt);
		}catch(Exception e){
		}

		dsp_url = PrefUtil.getString(getApplicationContext(), IAppConstants.DSP_URL);
		dsp_url += IAppConstants.DSP_URL_SUFIX;
		session_id = PrefUtil.getString(getApplicationContext(), IAppConstants.SESSION_ID);

		setContentView(R.layout.activity_main);
		progressDialog = new ProgressDialog(MainActivity.this);
		progressDialog.setMessage(getString((R.string.loading_message)));
		list_view = (ListView) findViewById(R.id.list_view_strik_text);
		editTextAddTask = (EditText) findViewById(R.id.editText_add_task);
		buttonAdd = (Button) findViewById(R.id.btnButton);
		buttonLogout = (Button)findViewById(R.id.btnLogout);
		buttonAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String task = editTextAddTask.getText().toString();
				if(task.length()==0){
					Toast.makeText(MainActivity.this, getText(R.string.task_blank), Toast.LENGTH_SHORT).show();
				}
				else {
					CreateRecordTask addToDoItem = new CreateRecordTask();
					addToDoItem.execute(task);
				}
			}
		});
		buttonLogout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				logout();
			}
		});
		GetRecordsTask listItem = new GetRecordsTask();
		listItem.execute();
	}


	private void logout(){
		PrefUtil.putString(getApplicationContext(), IAppConstants.SESSION_ID, "");
		PrefUtil.putString(getApplicationContext(), IAppConstants.EMAIL, "");
		PrefUtil.putString(getApplicationContext(), IAppConstants.PWD, "");
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("EXIT", true);
		startActivity(intent);
		finish();
	}

	class GetRecordsTask extends AsyncTask<Void, Records, Records>{
		private String errorMsg;

		@Override
		protected void onPreExecute() {
			progressDialog.show();
		}

		@Override
		protected Records doInBackground(Void... params) {
			DbApi dbApi = new DbApi();
			dbApi.addHeader("X-DreamFactory-Application-Name", IAppConstants.APP_NAME);
			dbApi.addHeader("X-DreamFactory-Session-Token", session_id);
			dbApi.setBasePath(dsp_url);
			try {
				Records records = dbApi.getRecords(IAppConstants.TABLE_NAME, null, null, null, null, null, null, null, true, null, null);
				log(records.toString());
				return records;
			} catch (Exception e) {
				e.printStackTrace();
				errorMsg = e.getMessage();
			}
			return null;
		}
		@Override
		protected void onPostExecute(Records records) {
			if(progressDialog != null && progressDialog.isShowing()){
				progressDialog.cancel();
			}
			if(records != null){ // success
				adapter = new ToDoAdapter(MainActivity.this, records.getRecord());
				list_view.setAdapter(adapter);
			}else{ // some error show dialog
				Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
				logout();
			}
		}
	}

	class CreateRecordTask extends AsyncTask<String, Void, Record>{
		private String errorMsg;

		@Override
		protected void onPreExecute() {
			progressDialog.show();
		}

		@Override
		protected Record doInBackground(String... params) {
			String todoItem = params[0];
			Record record = new Record();
			record.setName(todoItem);

			DbApi dbApi = new DbApi();
			dbApi.setBasePath(dsp_url);
			dbApi.addHeader("X-DreamFactory-Application-Name", IAppConstants.APP_NAME);
			dbApi.addHeader("X-DreamFactory-Session-Token", session_id);
			try {
				/* You cant pass an id in this method just yet*/
				Record resultRecord = dbApi.createRecord(IAppConstants.TABLE_NAME, "", null, record, null, null);
				resultRecord.setName(todoItem);
				log(resultRecord.toString());
				return resultRecord;
			} catch (Exception e) {
				e.printStackTrace();
				errorMsg = e.getMessage();
			}
			return null;
		}
		@Override
		protected void onPostExecute(Record record) {	
			progressDialog.cancel();
			if(record != null){
				adapter.addTask(record);
				adapter.notifyDataSetChanged();
				editTextAddTask.setText("");
			}else {				
				Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
			}
		}
	}

	private void log(String message){
		System.out.println("log: " + message);
	}
}
