package com.anton111111.api;

import android.content.Context;


import com.anton111111.Log;
import com.anton111111.utils.NetworkUtil;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract API class
 *
 * @author Anton Potekhin (anton.potekhin@gmail.com)
 */
public abstract class APIAbstract {

    public static final String REQUEST_METHOD_POST = "POST";
    public static final String REQUEST_METHOD_GET = "GET";

    protected String userAgent;

    protected Context context;

    public APIAbstract(Context context) {
        this(context, null);
    }

    public APIAbstract(Context context, String userAgent) {
        this.context = context;
        this.userAgent = userAgent;
    }

    /**
     * Override this method to set url
     *
     * @return
     */
    protected abstract String getURL();

    /**
     * Override this method to set request method
     *
     * @return
     */
    protected abstract String getRequestMethod();


    protected String executeRequest(HashMap<String, String> params) throws APIException, InterruptedIOException {
        return executeRequest(params, null, null);
    }

    protected String executeRequest(HashMap<String, String> params, String url) throws APIException, InterruptedIOException {
        return executeRequest(params, url, null);
    }

    protected String executeRequest(HashMap<String, String> params, String url, String requestMethod) throws APIException, InterruptedIOException {

        HttpURLConnection httpURLConnection;

        if (!NetworkUtil.isConnected(context)) {
            throw new APIException("Don't execute request because no connection.");
        }


        if (url == null || url.isEmpty()) {
            url = getURL();
        }
        if (requestMethod == null || requestMethod.isEmpty()) {
            requestMethod = getRequestMethod();
        }

        try {
            String paramsString = createParamsString(params);

            if (requestMethod == REQUEST_METHOD_GET) {
                if (!url.contains("?")) {
                    url += "?";
                }
                url += "&" + paramsString;
            }

            httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            if (userAgent != null && !userAgent.isEmpty()) {
                httpURLConnection.setRequestProperty("User-Agent", userAgent);
            }

            if (requestMethod == REQUEST_METHOD_POST) {
                httpURLConnection.setRequestMethod("POST");
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(paramsString);

                writer.flush();
                writer.close();
                os.close();
            }

            if (requestMethod == REQUEST_METHOD_POST) {
                Log.d(String.format("Request %s URL: %s with params: %s", requestMethod, url, paramsString));
            } else {
                Log.d(String.format("Request %s URL: %s", requestMethod, url));
            }

            int code = httpURLConnection.getResponseCode();
            if (code == 200) {
                return readInputStreamToString(httpURLConnection);
            }
            throw new APIException("Wrong response code " + code + " for request " + url +
                    ".\n Response header: " +
                    httpURLConnection.getHeaderFields().toString());

        } catch (IOException e) {
            if (e instanceof InterruptedIOException) {
                throw (InterruptedIOException) e;
            }
            throw new APIException(e.getMessage(), e);
        }
    }


    protected String createParamsString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }


    protected String readInputStreamToString(HttpURLConnection connection) {
        String result;
        StringBuffer sb = new StringBuffer();
        InputStream is = null;

        try {
            is = new BufferedInputStream(connection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            result = sb.toString();
        } catch (Exception e) {
            Log.e("Error reading InputStream");
            result = null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    Log.e(e.getMessage());
                }
            }
        }
        return result;
    }


    public class APIException extends Exception {
        public APIException(String message) {
            super(message);
        }

        public APIException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
