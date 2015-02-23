package com.df.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dfdemo.R;
import com.dreamfactory.model.FileResponse;

public class FilesAdapter extends BaseAdapter{
	Context context;
	private ArrayList<FileResponse> files;
	private LayoutInflater inflater;
	
	public FilesAdapter(Context context, ArrayList<FileResponse> files) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.files = files;
		inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return files == null? 0 : files.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return files == null? null : files.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return files.indexOf(getItem(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null){
			convertView = inflater.inflate(R.layout.file_list_item, null);
			holder = new ViewHolder();
			
			holder.fileName = (TextView) convertView.findViewById(R.id.file_name);
			holder.filePath = (TextView) convertView.findViewById(R.id.file_path);
			convertView.setTag(holder);		
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		FileResponse file = (FileResponse) getItem(position);
		holder.fileName.setText(file.getName());
		holder.filePath.setText(file.getPath());
		return convertView;
	}
	
	private class ViewHolder {
        TextView fileName;
        TextView filePath;
    }
}


