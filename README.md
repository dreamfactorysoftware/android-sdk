android-sdk
===========

DreamFactory Android java sdk 

Usage:

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


