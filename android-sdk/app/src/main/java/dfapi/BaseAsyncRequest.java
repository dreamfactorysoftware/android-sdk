package dfapi;

import android.os.AsyncTask;
import android.util.Log;

import com.dreamfactory.sampleapp.utils.AppConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 *      Primarily to take care of boiler plate for API calls
 *      When execute is called:
 *          1) doInBackground starts, runs in background
 *              2) doSetup called --> do setup here
 *              3) request built and sent
 *              4) process data called on response
 *          5) doInBackground finishes
 *          6) onPostExecute starts, runs in caller's thread
 *              7) onCompletion called
 *          8) onPostExecute finishes
 *      Any errors or thrown exceptions during doInBackground are written to the log
 *      then passed to onError
 */
public class BaseAsyncRequest extends AsyncTask<Void, Void, Boolean> {
    // service name, endpoint, verb and appName are needed for every call
    protected String serviceName; // eg files, db etc
    protected String endPoint; // eg table name
    protected String verb; // rest verb
    protected String applicationApiKey; // automatically added to the header

    // sessionToken is needed for every call except for login
    protected String sessionToken; // automatically added to the header

    // objects not already created (to save memory):
    protected Map<String, String> queryParams = null;
    protected JSONObject requestBody = null; // JSON object for request body

    // optional params:
    // format of request string, default to JSON
    protected String contentType = "application/json";

    // convert request body to string before sending it to API call
    // set the request string if you want to skip creating the JSON object
    protected String requestString = "";

    // tag to use for error messages and logging
    protected String callerName = "BaseAsyncRequest";

    // header params other than API key and session token
    protected Map<String, String> headerParams = new HashMap<>();

    // instance url, for example http://localhost:8080/api/v2
    protected String baseInstanceUrl = AppConstants.INSTANCE_URL;

    // set to true to print the request and response strings to verbose
    protected boolean use_logging = false;

    // used when sending a file up to the server
    // the ApiInvoker opens the file path from the FileRequest
    protected FileRequest fileRequest = null;

    /**
     * Formats and sends the REST request
     * @param params not used
     * @return Boolean whether or not the call succeeded.
     */
    @Override
    protected Boolean doInBackground(Void... params) {
        try{
            // call the setup function
            doSetup();

            // build the path to append onto the the instance url
            String path = "/" + serviceName + "/" + endPoint;

            // add sessionToken and API key into the header params
            if(applicationApiKey == null || applicationApiKey.isEmpty()){
                Log.w(callerName, "API key not provided");
            }
            else {
                headerParams.put("X-DreamFactory-Api-Key", applicationApiKey);
            }

            if(sessionToken == null || sessionToken.isEmpty()){
                Log.w(callerName, "session token not provided, only OK if logging in");
            }
            headerParams.put("X-DreamFactory-Session-Token", sessionToken);

            // build the request body
            if(requestBody != null){
                if(requestString != null && !requestString.isEmpty()){
                    Log.w(callerName, "supplied both a request body and request string " +
                            "to BaseAsync task, overwriting request string with request body");
                }
                requestString = requestBody.toString();
            }
            if(use_logging) {
                Log.v(callerName, "request string is: " + requestString);
            }

            // send the REST call
            String response = ApiInvoker.getInstance().invokeAPI(baseInstanceUrl,
                    path,
                    verb,
                    queryParams,
                    requestString,
                    headerParams,
                    contentType,
                    fileRequest);

            if(use_logging){
                Log.v(callerName, "response string is: " + response);
            }

            // send the response off to be processed
            processResponse(response);
            // made it to the end without an error being thrown, success == true
            return true;
        }
        catch (ApiException e){
            Log.e(callerName, "going to on_failure() for an ApiException");
            onError(e);
        }
        catch (JSONException e){
            Log.e(callerName, "going to onError() for a JSONException: " + e.toString());
            onError(e);
        }
        catch (Exception e){
            Log.e(callerName, "going to on_failure() for an unexpected exception type");
            onError(e);
        }
        return false;
    }

    /**
     * Finish the API call, runs on the thread that invoked the request
     * @param result did call complete successfully
     */
    @Override
    protected void onPostExecute(Boolean result) {
        if(use_logging){
            Log.v(callerName, "finished call");
        }
        onCompletion(result);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        onCompletion(false);
    }

    /**
     * Set up the variables for the API call, runs asynchronously.
     * @throws ApiException
     * @throws JSONException
     */
    protected void doSetup() throws ApiException, JSONException { }

    /**
     * Work with the result from the API call, runs asynchronously.
     * @param response JSON response from request
     * @throws ApiException
     * @throws JSONException
     */
    protected void processResponse(String response) throws ApiException, JSONException { }

    /**
     * Called when the request completes, regardless of the result. Runs on the thread that invoked the request
     * @param success did the call complete successfully
     */
    protected void onCompletion(boolean success){ }

    /**
     * Called when an error is thrown by any part of the request process. This includes doSetup and
     * processResponse
     * @param e exception thrown when trying to make the API call
     */
    protected void onError(Exception e) {Log.e(callerName + "::baserequest::onError", e.toString());}
}
