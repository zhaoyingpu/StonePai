package com.stone.pai;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.util.TypeUtils;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.stone.pai.bean.ListResult;
import com.stone.pai.network.PaiAsyncHttpClient;

import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

public class ListLoader<D> extends Loader<ListResult<D>> {
	private final String url;
	private final boolean resultArray;
	private final Class<D> clazz;
	private Bundle args;
	private List<D> mItems;
	
	public ListLoader(Context context, Class<D> clazz, String url) {
		this(context, clazz, url, null, false);	
	}
	
	public ListLoader(Context context, Class<D> clazz, String url, Bundle args) {
		this(context, clazz, url, args, false);
	}
	
	public ListLoader(Context context, Class<D> clazz, String url, Bundle args, boolean rA) {
		super(context);
		this.clazz = clazz;
		this.url = url;
		this.setArgs(args);
		this.resultArray = rA;
	}
	
	@Override 
	protected void onStartLoading() {
		RequestParams params = new RequestParams();
		if (getArgs() != null)
			for(String key : getArgs().keySet()) 
			    params.put(key, getArgs().get(key));

		final ListResult<D> result = new ListResult<D>();

		PaiAsyncHttpClient.get(url, params, new TextHttpResponseHandler() {

			@Override
			public void onFailure(int statusCode, Header[] headers, String content,
					Throwable arg3) {
				result.setStatusCode(statusCode);
				result.setContent(content);
				deliverResult(result);
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, String content) {
				result.setStatusCode(statusCode);

				if (resultArray) {
					mItems = JSON.parseArray(content, clazz);

					result.setStart(0);
					result.setTotal(mItems.size());
				} else {
					JSONObject listBean = JSON.parseObject(content, Feature.AllowISO8601DateFormat);
					JSONArray items = listBean.getJSONArray("items");
					mItems = new ArrayList<D>(items.size());
					for(int i=0; i<items.size(); ++i)
						mItems.add(TypeUtils.castToJavaBean(items.get(i), clazz));
					
					result.setStart(listBean.getIntValue("start"));
					result.setTotal(listBean.getIntValue("total"));
				}
				result.setCount(mItems.size());;
				result.setItems(mItems);
				deliverResult(result);
			}
		}); 
    }

	public Bundle getArgs() {
		return args;
	}

	public void setArgs(Bundle args) {
		this.args = args;
	}
}
