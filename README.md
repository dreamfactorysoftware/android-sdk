android-sdk
===========

DreamFactory Android java sdk 

Usage:  Pull records from a table named "todo"


    import com.dreamfactory.*;
    import java.util.List;
    class GetRecordsAsyncTask extends AsyncTask<Void, Void, List<Record>> {
    @Override
    protected List<Record> doInBackground(Void... params) {
        DbApi api = new DbApi();
        api.addHeader("X-DreamFactory-Application", "YOUR_KEY");
        try {
            return api.getRecords("todo");
        }
        catch (Exception e) {
            Log.d("GetRecordsAsyncTask", e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Record> records) {
        for(Record record : records) {
            Log.d("GetRecordsAsyncTask", record.toString());
        }
    }
    }

<p><b>Loading the Sample App</b></p>
Import the SampleApp directory into your Android Project.
Modify the targeted SDK version if need be in the Android Manifest.
