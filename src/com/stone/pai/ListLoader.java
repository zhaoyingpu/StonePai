package com.stone.pai;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.util.TypeUtils;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.stone.pai.bean.Skill;
import com.stone.pai.network.PaiAsyncHttpClient;

import android.content.Context;
import android.content.Loader;

public class ListLoader<D> extends Loader<List<D>> {
	private List<D> mItems;
	private final String url;
	private final String filter;
	private final String orderby;
	private final boolean resultArray;
	private final Class<D> clazz;
	
	public ListLoader(Context context, Class<D> clazz, String url) {
		this(context, clazz, url, "", "", false);	
	}
	
	public ListLoader(Context context, Class<D> clazz, String url, String f, String o) {
		this(context, clazz, url, f, o, false);
	}
	
	public ListLoader(Context context, Class<D> clazz, String url, String f, String o, boolean rA) {
		super(context);
		this.clazz = clazz;
		this.url = url;
		this.filter = f;
		this.orderby = o;
		this.resultArray = rA;
	}
	
	@Override protected void onStartLoading() {
		RequestParams params = new RequestParams();
		params.put("filter", filter);
		params.put("orderby", orderby);
		
		PaiAsyncHttpClient.get(url, params, new TextHttpResponseHandler() {

			@Override
			public void onFailure(int statusCode, Header[] headers, String content,
					Throwable arg3) {
			}

			@SuppressWarnings("null")
			@Override
			public void onSuccess(int statusCode, Header[] headers, String content) {
				try {
					if (resultArray) {
						mItems = JSON.parseArray(content, clazz);
					} else {
						JSONObject listBean = JSON.parseObject(content, Feature.AllowISO8601DateFormat);
						JSONArray items = listBean.getJSONArray("items");
						mItems = new ArrayList<D>(items.size());
						for(int i=0; i<items.size(); ++i)
							mItems.set(i, TypeUtils.castToJavaBean(items.get(i), clazz));
					}

					deliverResult(mItems);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}); 
    }
}
