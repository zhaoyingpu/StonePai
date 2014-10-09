package com.stone.pai.ui;

import java.util.List;

import com.stone.pai.ListLoader;
import com.stone.pai.SkillListAdapter;
import com.stone.pai.bean.ListResult;
import com.stone.pai.bean.Skill;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

public class SkillActivity extends ListActivity 
	implements LoaderManager.LoaderCallbacks<ListResult<Skill>>{
	SkillListAdapter listAdapter; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		listAdapter = new SkillListAdapter(getBaseContext());
		setListAdapter(listAdapter);
		
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<ListResult<Skill>> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		return new ListLoader<Skill>(getBaseContext(), Skill.class, "skill/list", null, true);
	}

	@Override
	public void onLoadFinished(Loader<ListResult<Skill>> arg0, ListResult<Skill> arg1) {
		listAdapter.clear();
		listAdapter.addAll(arg1.getItems());
	}

	@Override
	public void onLoaderReset(Loader<ListResult<Skill>> arg0) {
		// TODO Auto-generated method stub
		
	}
}
