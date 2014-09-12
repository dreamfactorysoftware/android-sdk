package com.df.ui;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.df.adapters.FilesAdapter;
import com.df.utils.IAppConstants;
import com.dfdemo.R;
import com.dreamfactory.api.FilesApi;
import com.dreamfactory.client.ApiException;
import com.dreamfactory.model.FileResponse;
import com.dreamfactory.model.FolderResponse;

public class RemoteFileDemo extends BaseActivity{
	private ListView file_list_view;
	private ArrayList<FileResponse> files;
	private FilesAdapter adapter;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_file);
		progressDialog = new ProgressDialog(RemoteFileDemo.this);
		progressDialog.setMessage(getString((R.string.loading_message)));
		file_list_view = (ListView) findViewById(R.id.list_view);
		new GetFilesFromRemote().execute();

		file_list_view.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, final int position,
					long arg3) {
				Button dialogButtonDelete;//, dialogButtonRead;
				final Dialog dialog = new Dialog(RemoteFileDemo.this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.dialog);

				dialogButtonDelete = (Button) dialog.findViewById(R.id.delete);
				dialogButtonDelete.setText("Delete");
//				dialogButtonRead = (Button) dialog.findViewById(R.id.read);
//				dialogButtonRead.setText("To-do");
				dialog.show();
				dialogButtonDelete.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.cancel();
						FileResponse file = files.get(position);
						new DeleteFileTask().execute(file);
					}
				});

//				dialogButtonRead.setOnClickListener(new View.OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						dialog.cancel();
//						Toast.makeText(getApplicationContext(), "To-Do", Toast.LENGTH_SHORT).show();
//					}
//				});
			}
		});		
	}

	class GetFilesFromRemote extends AsyncTask<Void, FolderResponse, FolderResponse>{
		private String errorMsg;

		@Override
		protected void onPreExecute() {
			progressDialog.setMessage("Please Wait, Refreshing files from remote container. ");
			progressDialog.show();
		}

		@Override
		protected FolderResponse doInBackground(Void... params) {
			FilesApi fileApi = new FilesApi();
			fileApi.addHeader("X-DreamFactory-Application-Name", IAppConstants.APP_NAME);
			fileApi.addHeader("X-DreamFactory-Session-Token", session_id);
			fileApi.setBasePath(dsp_url);
			String containerName = IAppConstants.CONTAINER_NAME;
			String filePathOnServer = IAppConstants.FOLDER_NAME;
			try {
				FolderResponse resp = fileApi.getFolder(containerName, filePathOnServer, true, true, true, true, false);
				log(resp.toString());
				// parse file list
				return resp;
			} catch (ApiException e) {
				e.printStackTrace();
				errorMsg = e.getMessage();
			}
			return null;
		}
		@Override
		protected void onPostExecute(FolderResponse resp) {
			if(progressDialog != null && progressDialog.isShowing()){
				progressDialog.cancel();
			}
			if(resp != null){
				files = (ArrayList<FileResponse>) resp.getFile();
				adapter = new FilesAdapter(RemoteFileDemo.this, files);
				file_list_view.setAdapter(adapter);
				if(files.size() == 0){
					Toast.makeText(getApplicationContext(), "Empty Container.", Toast.LENGTH_LONG).show();
					finish();
				}
			}else{
				Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
			}
		}
	}
	
	class DeleteFileTask extends AsyncTask<Object, FileResponse, FileResponse>{
		private String errorMsg;

		@Override
		protected void onPreExecute() {
			progressDialog.setMessage("Please Wait, Deleting file from remote container. ");
			progressDialog.show();
		}

		@Override
		protected FileResponse doInBackground(Object... params) {
			FilesApi fileApi = new FilesApi();
			fileApi.addHeader("X-DreamFactory-Application-Name", IAppConstants.APP_NAME);
			fileApi.addHeader("X-DreamFactory-Session-Token", session_id);
			fileApi.setBasePath(dsp_url);
			FileResponse fileToDelete = (FileResponse) params[0];
			String containerName = IAppConstants.CONTAINER_NAME;
			String filePathOnServer = IAppConstants.FOLDER_NAME + "/";
			String fileName = fileToDelete.getName();
			try {
				fileName = URLEncoder.encode(fileName, "UTF-8"); // file name with space
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			try {
				FileResponse resp = fileApi.deleteFile(containerName, filePathOnServer + fileName);
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
			if(resp != null){
				Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
				new GetFilesFromRemote().execute();
			}else{
				Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
			}
		}
	}
}
