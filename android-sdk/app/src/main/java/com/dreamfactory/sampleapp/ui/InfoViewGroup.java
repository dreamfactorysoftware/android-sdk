package com.dreamfactory.sampleapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.models.ContactInfoRecord;

@RemoteViews.RemoteView
public class InfoViewGroup extends RelativeLayout {

    public InfoViewGroup(Context context, ContactInfoRecord record) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);

        inflater.inflate(R.layout.contact_info_layout, this, true);
        TextView type = (TextView) findViewById(R.id.type_label);
        type.setText(record.getInfoType());

        if (record.getPhone().isEmpty()) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.info_phone_layout);
            layout.setVisibility(GONE);
        } else {
            TextView phone_label = (TextView) findViewById(R.id.phone_label);
            phone_label.setText(record.getPhone());
        }

        if (record.getEmail().isEmpty()) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.info_email_layout);
            layout.setVisibility(GONE);
        } else {
            TextView email_label = (TextView) findViewById(R.id.email_label);
            email_label.setText(record.getEmail());
        }

        if (record.getAddress().isEmpty() || record.getCity().isEmpty()) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.info_address_layout);
            layout.setVisibility(GONE);
        } else {
            TextView address_label = (TextView) findViewById(R.id.address_label);
            address_label.setText(record.getAddress() + " " + record.getCity());
        }
    }

    void removeFromParent (){
        ((ViewGroup) this.getParent()).removeView(this);
    }
    public InfoViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InfoViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}