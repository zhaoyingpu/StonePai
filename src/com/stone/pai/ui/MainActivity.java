package com.stone.pai.ui;

import java.util.List;

import com.stone.pai.ListLoader;
import com.stone.pai.R;
import com.stone.pai.TaskList;
import com.stone.pai.TaskListAdapter;
import com.stone.pai.bean.*;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.app.FragmentManager;
import android.content.Loader;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;

public class MainActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position, TaskList taskList) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		Fragment fragment = PlaceholderFragment.newInstance(position + 1, taskList);
		fragmentManager
				.beginTransaction()
				.replace(R.id.container, fragment)
				.commit();
	}

	public void onSectionAttached(int number, String title) {
		mTitle = title;
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends ListFragment implements 
		SwipeRefreshLayout.OnRefreshListener,
		LoaderManager.LoaderCallbacks<List<Task>> {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";
		private static final String ARG_NAME = "name";
		private static final String ARG_FILTER = "filter";
		private static final String ARG_ORDERBY = "orderby";
		private TaskListAdapter mTaskListAdapter;
		
		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber, TaskList taskList) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			args.putString(ARG_NAME, taskList.getName());
			args.putString(ARG_FILTER, taskList.getFilter());
			args.putString(ARG_ORDERBY, taskList.getOrderby());
			fragment.setArguments(args);
			
			return fragment;
		}

		public PlaceholderFragment() {
			
		}

		@Override
	    public void onViewCreated(View view, Bundle savedInstanceState) {
	        super.onViewCreated(view, savedInstanceState);
	        // remove the dividers from the ListView of the ListFragment
	        getListView().setDivider(new ColorDrawable(Color.rgb(170, 170, 170)));
	        getListView().setDividerHeight(1);
	        //setOnRefreshListener(this);
	    }
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
		    View view = inflater.inflate(R.layout.fragment_main, container, false);
		    return view;
		}
		
		@Override 
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			
	        mTaskListAdapter = new TaskListAdapter(getActivity());
			setListAdapter(mTaskListAdapter);
			
			setListShown(false);

			getLoaderManager().initLoader(
					getArguments().getInt(ARG_SECTION_NUMBER), 
					getArguments(), 
					this);
		}
		
		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(
					getArguments().getInt(ARG_SECTION_NUMBER),
					getArguments().getString(ARG_NAME));
		}

		@Override
	    public void onListItemClick(ListView l, View v, int position, long id) {
	    }
		
		@Override
		public void onRefresh() {
			getLoaderManager().restartLoader(					
					getArguments().getInt(ARG_SECTION_NUMBER), 
					getArguments(), 
					this);
		}

		@Override
		public Loader<List<Task>> onCreateLoader(int id, Bundle args) {
			return new ListLoader<Task>(getActivity(), 
					Task.class, 
					"task/list", 
					args.getString(ARG_FILTER), 
					args.getString(ARG_ORDERBY), 
					false);
		}

		@Override
		public void onLoadFinished(Loader<List<Task>> loader, List<Task> tasks) {
			mTaskListAdapter.clear();
			mTaskListAdapter.addAll(tasks);
			
	        if (isResumed()) {
	            setListShown(true);
	        } else {
	            setListShownNoAnimation(true);
	        }
	        
	        //setRefreshing(false);
		}

		@Override
		public void onLoaderReset(Loader<List<Task>> arg0) {
		}
	}

}
