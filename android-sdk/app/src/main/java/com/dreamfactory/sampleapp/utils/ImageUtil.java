package com.dreamfactory.sampleapp.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import com.dreamfactory.sampleapp.activities.CreateContactActivity;

/**
 * Created by Murtic on 25/06/16.
 */
public class ImageUtil {

    public static String getImagePath(Uri uri, ContentResolver contentResolver) {
        String profileImagePath = null;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                String wholeID = DocumentsContract.getDocumentId(uri);
                String[] column = {MediaStore.Images.Media.DATA};

                // Split at colon, use second item in the array
                String id = wholeID.split(":")[1];

                // where id is equal to
                String sel = MediaStore.Images.Media._ID + "=?";

                Cursor cursor = contentResolver.query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        column, sel, new String[]{id}, null);

                int columnIndex = cursor.getColumnIndex(column[0]);

                if (cursor.moveToFirst()) {
                    profileImagePath = cursor.getString(columnIndex);
                }
                cursor.close();
            } catch (Exception e) {
                Log.e(CreateContactActivity.class.getSimpleName(), "could not decode image: " + e.toString());
            }
        } else {
            profileImagePath = uri.getPath();
        }

        return profileImagePath;
    }
}
