package com.stone.pai.network;

import com.loopj.android.http.*;

public class PaiAsyncHttpClient extends AsyncHttpClient {
	  private static final String BASE_URL = "http://case.stoneit.cn:8080/api/1.0/";

	  private static AsyncHttpClient client = new AsyncHttpClient();

	  public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
	      client.get(getAbsoluteUrl(url), params, responseHandler);
	  }

	  public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
	      client.post(getAbsoluteUrl(url), params, responseHandler);
	  }

	  public static void setAccessToken(String token)
	  {
		  if (token == null)
			  client.removeHeader("Authorization");
		  else
			  client.addHeader("Authorization", "Bearer "+token);
	  }
	  
	  public static String getAbsoluteUrl(String relativeUrl) {
	      return BASE_URL + relativeUrl;
	  }
}
