package com.df.ui;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.df.adapters.FilesAdapter;
import com.df.utils.IAppConstants;
import com.dfdemo.R;
import com.dreamfactory.api.FilesApi;
import com.dreamfactory.client.ApiException;
import com.dreamfactory.model.FileRequest;
import com.dreamfactory.model.FileResponse;

public class UploadFileDemo extends BaseActivity {
	private ListView file_list_view;
	private ArrayList<FileResponse> files;
	private FilesAdapter adapter;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_file);
		progressDialog = new ProgressDialog(UploadFileDemo.this);
		progressDialog.setMessage(getString((R.string.loading_message)));
		file_list_view = (ListView) findViewById(R.id.list_view);
		initFileList();
		adapter = new FilesAdapter(this, files);
		file_list_view.setAdapter(adapter);
		file_list_view.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				FileResponse file = files.get(position);
				new UploadFileTask().execute(file);
			}
		});
	}

	public void initFileList() {
		java.io.File root = new java.io.File(Environment.getExternalStorageDirectory().getAbsolutePath());
		files = new ArrayList<FileResponse>();
		for (java.io.File f : root.listFiles()) {
			if (f.isFile()){
				FileResponse file = new FileResponse();
				file.setName(f.getName());
				file.setPath(f.getAbsolutePath());
				files.add(file);
			}
		}
	}

	class UploadFileTask extends AsyncTask<Object, FileResponse, FileResponse>{
		private String errorMsg;

		@Override
		protected void onPreExecute() {
			progressDialog.show();
		}

		@Override
		protected FileResponse doInBackground(Object... params) {
//			FilesApiDeprecated fileApi = new FilesApiDeprecated();
			FilesApi fileApi = new FilesApi();
			fileApi.addHeader("X-DreamFactory-Application-Name", IAppConstants.APP_NAME);
			fileApi.addHeader("X-DreamFactory-Session-Token", session_id);
			fileApi.setBasePath(dsp_url);
			FileResponse fileToUpload = (FileResponse)params[0];
			// where to upload on server
			String containerName = IAppConstants.CONTAINER_NAME;
			String filePathOnServer = IAppConstants.FOLDER_NAME + "/";

			FileRequest request = new FileRequest();
			request.setName(fileToUpload.getName());  // this will be stored file name on server
			request.setPath(fileToUpload.getPath());
			try {
				FileResponse resp = fileApi.createFile(containerName, filePathOnServer, false, request);
				log(resp.toString());
				return resp;
			} catch (ApiException e) {
				e.printStackTrace();
				errorMsg = e.getMessage();
			}
			return null;
		}
		@Override
		protected void onPostExecute(FileResponse resp) {
			if(progressDialog != null && progressDialog.isShowing()){
				progressDialog.cancel();
			}
			if(resp != null){ // success
				Toast.makeText(getApplicationContext(), "Uploaded successfully.", Toast.LENGTH_LONG).show(); 
			}else{ // some error show dialog
				Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
			}
		}
	}
}
