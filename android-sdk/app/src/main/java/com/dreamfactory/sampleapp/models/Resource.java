package com.dreamfactory.sampleapp.models;

import android.os.Parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nirmel on 6/3/2016.
 */
public class Resource<T> implements Serializable {

    protected List<T> resource = new ArrayList<>();

    public List<T> getResource() {
        return resource;
    }

    public void setResource(List<T> resource) {
        this.resource = resource;
    }

    public void addResource(T value) {
        resource.add(value);
    }

    public Resource() {
    }

    public static class Parcelable<T extends BaseRecord> extends Resource<T> implements android.os.Parcelable {

        public Parcelable() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(resource.size());

            if(resource.size() > 0) {
                dest.writeValue(resource.get(0).getClass());
            }

            for(T record : resource){
                dest.writeParcelable((android.os.Parcelable) record, flags);
            }
        }

        public static final Parcelable.Creator<Parcelable> CREATOR = new Parcelable.Creator<Parcelable>() {
            public Parcelable createFromParcel(Parcel in) {
                return new Parcelable(in);
            }

            @Override
            public Parcelable[] newArray(int size) {
                return new Parcelable[size];
            }
        };

        private Parcelable(Parcel in) {
            int size = in.readInt();

            resource = new ArrayList<>();

            if(size > 0) {
                Class c = (Class) in.readValue(null);

                for(int i = 0; i < size; i++){
                    T value = in.readParcelable(c.getClassLoader());

                    resource.add(value);
                }
            }
        }
    }
}
