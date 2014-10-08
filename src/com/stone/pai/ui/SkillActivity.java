package com.stone.pai.ui;

import java.util.List;

import com.stone.pai.ListLoader;
import com.stone.pai.SkillListAdapter;
import com.stone.pai.bean.Skill;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

public class SkillActivity extends ListActivity 
	implements LoaderManager.LoaderCallbacks<List<Skill>>{
	SkillListAdapter listAdapter; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		listAdapter = new SkillListAdapter(getBaseContext());
		setListAdapter(listAdapter);
		
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<List<Skill>> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		return new ListLoader<Skill>(getBaseContext(), Skill.class, "skill/list", "", "", true);
	}

	@Override
	public void onLoadFinished(Loader<List<Skill>> arg0, List<Skill> arg1) {
		listAdapter.clear();
		listAdapter.addAll(arg1);
	}

	@Override
	public void onLoaderReset(Loader<List<Skill>> arg0) {
		// TODO Auto-generated method stub
		
	}
}
