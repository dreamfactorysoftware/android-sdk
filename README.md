android-sdk
===========

DreamFactory Android java sdk 

Usage:  Pull records from a table 


    import com.dreamfactory.*;
    import java.util.List;
   class GetRecordsTask extends AsyncTask<Void, RecordsResponse, RecordsResponse>{ 

		@Override
		protected RecordsResponse doInBackground(Void... params) {
			DbApi dbApi = new DbApi();
			dbApi.addHeader("X-DreamFactory-Application-Name", "YOUR_KEY");
			dbApi.addHeader("X-DreamFactory-Session-Token", "YOUR_SESSION_ID");
			dbApi.setBasePath("YOUR_DSP_URL");
			try {
				RecordsResponse records = dbApi.getRecords(IAppConstants.TABLE_NAME,null,null,-1,-1,null,null,false,false,null,null,true,null);
				return records;
			} catch (Exception e) {
				e.printStackTrace();
				errorMsg = e.getMessage();
			}
			return null;
		}
		@Override
		protected void onPostExecute(RecordsResponse records) {
			
			  Log.d("GetRecordsAsyncTask", records.toString());
				
		}
	}

<p><b>Loading the Sample App</b></p>
Import the SampleApp directory into your Android Project.
Modify the targeted SDK version if need be in the Android Manifest.
