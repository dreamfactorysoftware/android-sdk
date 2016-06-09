package dfapi;


import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;

import com.dreamfactory.sampleapp.models.FileRequest;
import com.fasterxml.jackson.databind.JavaType;

public class ApiInvoker {
    private static ApiInvoker INSTANCE = new ApiInvoker();
    private Map<String, String> defaultHeaderMap = new HashMap<>();

    private HttpClient client = null;

    private boolean ignoreSSLCertificates = false;

    private ClientConnectionManager ignoreSSLConnectionManager;

    private ApiInvoker() {
        initConnectionManager();
    }

    public static ApiInvoker getInstance() {
        return INSTANCE;
    }

    public void ignoreSSLCertificates(boolean ignoreSSLCertificates) {
        this.ignoreSSLCertificates = ignoreSSLCertificates;
    }

    public void addDefaultHeader(String key, String value) {
        defaultHeaderMap.put(key, value);
    }

    public String escapeString(String str) {
        return str;
    }

    /**
     * Convert a JSON object to a Java type. Can map to custom POJOs
     * @param json json string to deserialize
     * @param containerType if the object is in a list container
     * @param cls class to deserialize json into
     * @return deserialize object
     * @throws ApiException
     */
    public static Object deserialize(String json, String containerType, Class cls) throws ApiException {
        try{
            if("List".equals(containerType)) {
                JavaType typeInfo = JsonUtil.getJsonMapper().getTypeFactory().constructCollectionType(List.class, cls);
                return JsonUtil.getJsonMapper().readValue(json, typeInfo);
            }
            else if(String.class.equals(cls)) {
                if(json != null && json.startsWith("\"") && json.endsWith("\"") && json.length() > 1)
                    return json.substring(1, json.length() - 2);
                else
                    return json;
            }
            else {
                return JsonUtil.getJsonMapper().readValue(json, cls);
            }
        }
        catch (IOException e) {
            throw new ApiException(500, e.getMessage());
        }
    }

    public static String serialize(Object obj) throws ApiException {
        try {
            if (obj != null)
                return JsonUtil.getJsonMapper().writeValueAsString(obj);
            else
                return null;
        }
        catch (Exception e) {
            throw new ApiException(500, e.getMessage());
        }
    }

    public String invokeAPI(String host, String path, String method, Map<String, String> queryParams, String body, Map<String, String> headerParams, String contentType) throws ApiException {
        return invokeAPIInternal(host, path, method, queryParams, body, headerParams, contentType, null);
    }

    public String invokeAPI(String host, String path, String method, Map<String, String> queryParams, Object body, Map<String, String> headerParams, String contentType) throws ApiException {
        return invokeAPI(host, path, method, queryParams, body, headerParams, contentType, null);
    }

    public String invokeAPI(String host, String path, String method, Map<String, String> queryParams, String body, Map<String, String> headerParams, String contentType, FileRequest fileRequest) throws ApiException {
        return invokeAPIInternal(host, path, method, queryParams, body, headerParams, contentType, fileRequest);
    }

    public String invokeAPI(String host, String path, String method, Map<String, String> queryParams, Object body, Map<String, String> headerParams, String contentType, FileRequest fileRequest) throws ApiException {
        return invokeAPIInternal(host, path, method, queryParams, serialize(body), headerParams, contentType, fileRequest);
    }

    /**
     * Send the REST request to the server
     * @param host path to the instance
     * @param path service name/endpoint
     * @param method HTTP verb
     * @param queryParams any query params to send with request, each pair forms a query
     * @param body request to send to server, every verb except GET can take a body
     * @param headerParams Typically used to store session token and API key
     * @param contentType format of request body
     * @param fileRequest
     * @return
     * @throws ApiException
     */
    private String invokeAPIInternal(String host, String path, String method, Map<String, String> queryParams, String body, Map<String, String> headerParams, String contentType, FileRequest fileRequest) throws ApiException {
        HttpClient client = getClient(host);

        // build query params into a string to append on the back of the request
        StringBuilder b = new StringBuilder();
        if(queryParams != null) {
            for (String key : queryParams.keySet()) {
                String value = queryParams.get(key);
                if (value != null) {
                    if (b.toString().length() == 0)
                        b.append("?");
                    else
                        b.append("&");
                    b.append(escapeString(key)).append("=").append(escapeString(value));
                }
            }
        }

        // build the full path, form is: <instance url>/api/v2/<service name>/<end point>?[a params]
        String url = host + path + b.toString();

        HashMap<String, String> headers = new HashMap<>();

        for(String key : headerParams.keySet()) {
            headers.put(key, headerParams.get(key));
        }

        for(String key : defaultHeaderMap.keySet()) {
            if(!headerParams.containsKey(key)) {
                headers.put(key, defaultHeaderMap.get(key));
            }
        }
        // only accept JSON responses
        headers.put("Accept", "application/json");

        // build and send request
        HttpResponse response = null;
        try{
            if("GET".equals(method)) {
                HttpGet get = new HttpGet(url);

                for(String key : headers.keySet()) {
                    get.setHeader(key, headers.get(key));
                }
                response = client.execute(get);
            }
            else if ("POST".equals(method)) {
                HttpPost post = new HttpPost(url);
                post.setHeader("Content-Type", contentType);

                if (fileRequest != null){
                    File file = new File(fileRequest.getPath());
                    FileEntity reqEntity = new FileEntity(file, contentType);
                    post.setEntity(reqEntity);
                }
                else if (body != null) {
                    post.setEntity(new StringEntity(body, "UTF-8"));
                }

                for(String key : headers.keySet()) {
                    post.setHeader(key, headers.get(key));
                }
                response = client.execute(post);
            }
            else if ("PUT".equals(method)) {
                HttpPut put = new HttpPut(url);

                if(body != null) {
                    put.setHeader("Content-Type", contentType);
                    put.setEntity(new StringEntity(body, "UTF-8"));
                }
                for(String key : headers.keySet()) {
                    put.setHeader(key, headers.get(key));
                }
                response = client.execute(put);
            }
            else if ("DELETE".equals(method)) {
                HttpDeleteWithBody delete = new HttpDeleteWithBody(url);

                if(body != null){
                    delete.setHeader("Content-Type", contentType);
                    delete.setEntity(new StringEntity(body, "UTF-8"));
                }
                for(String key : headers.keySet()) {
                    delete.setHeader(key, headers.get(key));
                }
                response = client.execute(delete);
            }
            else if ("PATCH".equals(method)) {
                HttpPatch patch = new HttpPatch(url);

                if (body != null) {
                    patch.setHeader("Content-Type", contentType);
                    patch.setEntity(new StringEntity(body, "UTF-8"));
                }
                for(String key : headers.keySet()) {
                    patch.setHeader(key, headers.get(key));
                }
                response = client.execute(patch);
            }

            int code = response.getStatusLine().getStatusCode();
            String responseString = null;
            if(code == 204)
                responseString = "";
            else if(code >= 200 && code < 300) {
                if(response.getEntity() != null) {
                    HttpEntity resEntity = response.getEntity();
                    responseString = EntityUtils.toString(resEntity);
                }
            }
            else {
                if(response.getEntity() != null) {
                    HttpEntity resEntity = response.getEntity();
                    responseString = EntityUtils.toString(resEntity);
                }
                else
                    responseString = "no data";
                throw new ApiException(code, responseString);
            }
            return responseString;
        }
        catch(IOException e) {
            throw new ApiException(500, e.getMessage());
        }
    }

    private HttpClient getClient(String host) {
        if (client == null) {
            if (ignoreSSLCertificates && ignoreSSLConnectionManager != null) {
                // Trust self signed certificates
                client = new DefaultHttpClient(ignoreSSLConnectionManager, new BasicHttpParams());
            } else {
                client = new DefaultHttpClient();
            }
        }
        return client;
    }

    private void initConnectionManager() {
        try {
            final SSLContext sslContext = SSLContext.getInstance("SSL");

            // set up a TrustManager that trusts everything
            TrustManager[] trustManagers = new TrustManager[] {
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }};

            sslContext.init(null, trustManagers, new SecureRandom());

            SSLSocketFactory sf = new SSLSocketFactory((KeyStore)null) {
                private javax.net.ssl.SSLSocketFactory sslFactory = sslContext.getSocketFactory();

                public Socket createSocket(Socket socket, String host, int port, boolean autoClose)
                        throws IOException {
                    return sslFactory.createSocket(socket, host, port, autoClose);
                }

                public Socket createSocket() throws IOException {
                    return sslFactory.createSocket();
                }
            };

            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            Scheme httpsScheme = new Scheme("https", sf, 443);
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(httpsScheme);
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

            ignoreSSLConnectionManager = new SingleClientConnManager(new BasicHttpParams(), schemeRegistry);
        } catch (NoSuchAlgorithmException e) {
            // This will only be thrown if SSL isn't available for some reason.
        } catch (KeyManagementException e) {
            // This might be thrown when passing a key into init(), but no key is being passed.
        } catch (GeneralSecurityException e) {
            // This catches anything else that might go wrong.
            // If anything goes wrong we default to the standard connection manager.
        }
    }
}

