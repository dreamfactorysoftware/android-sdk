package com.df.ui;

import android.app.ProgressDialog;
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
import com.dfdemo.R;
import com.dreamfactory.api.DbApi;
import com.dreamfactory.model.RecordRequest;
import com.dreamfactory.model.RecordResponse;
import com.dreamfactory.model.RecordsResponse;

public class ToDoDemoActivity extends BaseActivity {
	private Button buttonAdd,buttonLogout;
	private EditText editTextAddTask;
	private ListView list_view;
	private ProgressDialog progressDialog;
	private ToDoAdapter adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		progressDialog = new ProgressDialog(ToDoDemoActivity.this);
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
					Toast.makeText(ToDoDemoActivity.this, getText(R.string.task_blank), Toast.LENGTH_SHORT).show();
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


	class GetRecordsTask extends AsyncTask<Void, RecordsResponse, RecordsResponse>{
		private String errorMsg;

		@Override
		protected void onPreExecute() {
			progressDialog.show();
		}

		@Override
		protected RecordsResponse doInBackground(Void... params) {
			DbApi dbApi = new DbApi();
			dbApi.addHeader("X-DreamFactory-Application-Name", IAppConstants.APP_NAME);
			dbApi.addHeader("X-DreamFactory-Session-Token", session_id);
			dbApi.setBasePath(dsp_url);
			try {
				RecordsResponse records = dbApi.getRecords(IAppConstants.TABLE_NAME,null,null,-1,-1,null,null,false,false,null,null,true,null);
				log(records.toString());
				return records;
			} catch (Exception e) {
				e.printStackTrace();
				errorMsg = e.getMessage();
			}
			return null;
		}
		@Override
		protected void onPostExecute(RecordsResponse records) {
			if(progressDialog != null && progressDialog.isShowing()){
				progressDialog.cancel();
			}
			if(records != null){ // success
				adapter = new ToDoAdapter(ToDoDemoActivity.this, records.getRecord());
				list_view.setAdapter(adapter);
			}else{ // some error show dialog
				Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
				logout();
			}
		}
	}

	class CreateRecordTask extends AsyncTask<String, Void, RecordResponse>{
		private String errorMsg;

		@Override
		protected void onPreExecute() {
			progressDialog.show();
		}

		@Override
		protected RecordResponse doInBackground(String... params) {
			String todoItem = params[0];
			RecordRequest record = new RecordRequest();
			record.setName(todoItem);

			DbApi dbApi = new DbApi();
			dbApi.setBasePath(dsp_url);
			dbApi.addHeader("X-DreamFactory-Application-Name", IAppConstants.APP_NAME);
			dbApi.addHeader("X-DreamFactory-Session-Token", session_id);
			try {
				/* You cant pass an id in this method just yet hmmm*/ 
				RecordResponse resultRecord = dbApi.createRecord(IAppConstants.TABLE_NAME, "123", record, null, null, null,null);
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
		protected void onPostExecute(RecordResponse record) {	
			progressDialog.cancel();
			if(record != null){
				adapter.addTask(record);
				adapter.notifyDataSetChanged();
				editTextAddTask.setText("");
			}else {				
				Toast.makeText(ToDoDemoActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
			}
		}
	}
}

