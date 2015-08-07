package com.dreamfactory.sampleapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class ParcelableContactInfoRecords implements Parcelable {

    private List<ParcelableContactInfoRecord> recordList;

    public ParcelableContactInfoRecords(ContactInfoRecords records){
        recordList = new ArrayList<>();

        for(ContactInfoRecord record : records.record){
            recordList.add(new ParcelableContactInfoRecord(record));
        }
    }

    public ContactInfoRecords buildContactInfoRecords(){
        ContactInfoRecords contactInfoRecords = new ContactInfoRecords();
        contactInfoRecords.record = new ArrayList<>();
        for(ParcelableContactInfoRecord record : recordList){
            contactInfoRecords.record.add(record.buildContactInfoRecord());
        }
        return contactInfoRecords;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // add the number of objects to read on the other side
        dest.writeInt(recordList.size());

        for(ParcelableContactInfoRecord record : recordList){
            dest.writeParcelable(record, flags);
        }
    }

    private ParcelableContactInfoRecords(Parcel in){
        int size = in.readInt();

        recordList = new ArrayList<>();
        for(int i = 0; i < size; i++){
            ParcelableContactInfoRecord parcelableContactInfoRecord =
                    in.readParcelable(ParcelableContactInfoRecord.class.getClassLoader());

            recordList.add(parcelableContactInfoRecord);
        }
    }

    public static final Parcelable.Creator<ParcelableContactInfoRecords> CREATOR = new Parcelable.Creator<ParcelableContactInfoRecords>() {
        public ParcelableContactInfoRecords createFromParcel(Parcel in) {
            return new ParcelableContactInfoRecords(in);
        }

        @Override
        public ParcelableContactInfoRecords[] newArray(int size) {
            return new ParcelableContactInfoRecords[size];
        }
    };
}
