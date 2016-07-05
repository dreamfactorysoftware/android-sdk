package com.dreamfactory.sampleapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.models.FileRecord;

import java.util.List;

public class ProfileImageChooserAdapter extends BaseAdapter {
    private Activity context;
    private List<FileRecord> imageList;

    public ProfileImageChooserAdapter(Activity context, List<FileRecord> imageList){
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if(rowView == null){
            // reuse views
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.rowlayout, null);
            FileHolder viewHolder = new FileHolder();
            viewHolder.text = (TextView) rowView.findViewById(R.id.row_text_label);
            rowView.setTag(viewHolder);
        }

        rowView.setClickable(false);
        // fill data
        FileHolder holder = (FileHolder) rowView.getTag();
        String fileName = imageList.get(position).getName();
        holder.text.setText(fileName);
        holder.fileName = fileName;
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileHolder fileHolder = (FileHolder) v.getTag();
                Intent intent = new Intent();
                intent.putExtra("imageUrl", fileHolder.fileName);
                context.setResult(Activity.RESULT_OK, intent);
                context.finish();
                context = null;
            }
        });
        return rowView;
    }

    private class FileHolder{
        public String fileName;
        public TextView text;
    }
}
