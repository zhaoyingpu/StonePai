package com.stone.pai.ui;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

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
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Toast;
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
	 * Used to store the last screen title. For use in {@link #restoreActionBar()}
	 * .
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
		fragmentManager.beginTransaction().replace(R.id.container, fragment)
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
			LoaderManager.LoaderCallbacks<ListResult<Task>> {
		/**
		 * The fragment argument representing the section number for this fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";
		private static final String ARG_NAME = "name";
		private static final String ARG_FILTER = "filter";
		private static final String ARG_ORDERBY = "orderby";
		private static final int PAGE_COUNT = 5;

		private TaskListAdapter mTaskListAdapter;
		private boolean loading = false;

		@InjectView(R.id.id_swipe_Refresh)
		SwipeRefreshListLayout swipeRefreshLayout;

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber,
				TaskList taskList) {
			PlaceholderFragment fragment = new PlaceholderFragment();

			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			args.putString(ARG_NAME, taskList.getName());
			args.putString(ARG_FILTER, taskList.getFilter());
			args.putString(ARG_ORDERBY, taskList.getOrderby());
			args.putInt("start", 0);
			args.putInt("count", PAGE_COUNT);
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
			swipeRefreshLayout.setOnRefreshListener(this);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.fragment_main, container, false);
			ButterKnife.inject(this, view);
			
			return view;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);

			mTaskListAdapter = new TaskListAdapter(getActivity());
			setListAdapter(mTaskListAdapter);

			setListShown(false);

			getListView().setOnScrollListener(new EndlessScrollListener(1, 1) {
				@Override
				public boolean onLoadMore(int page, int totalItemsCount) {
					return customLoadMoreDataFromApi(page, totalItemsCount);
				}
			});
			
			getLoaderManager().initLoader(
				getArguments().getInt(ARG_SECTION_NUMBER),
				getArguments(), 
				this);
		}

		// Append more data into the adapter
		public boolean customLoadMoreDataFromApi(int page, int totalItemsCount) {
			// This method probably sends out a network request and appends new data
			// items to your adapter.
			// Use the offset value and add it as a parameter to your API request to
			// retrieve paginated data.
			// Deserialize API response and then construct new objects to append to
			// the adapter
			Bundle args = getArguments();
			Loader loader = getLoaderManager().getLoader(args.getInt(ARG_SECTION_NUMBER));
			if (mTaskListAdapter.hasMoreItem() && !loading) {
				args.putInt("start", PAGE_COUNT * (page - 1));
				args.putInt("count", PAGE_COUNT);
				getLoaderManager().restartLoader(args.getInt(ARG_SECTION_NUMBER), args, this);
				return true;
			}
			
			return false;
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
			Bundle args = getArguments();
			Loader loader = getLoaderManager().getLoader(
				args.getInt(ARG_SECTION_NUMBER));
			args.putInt("start", 0);
			args.putInt("count", PAGE_COUNT);
			getLoaderManager().restartLoader(args.getInt(ARG_SECTION_NUMBER), args,
				this);
		}

		@Override
		public Loader<ListResult<Task>> onCreateLoader(int id, Bundle args) {
			loading = true;
			return new ListLoader<Task>(getActivity(), Task.class, "task/list", args);
		}

		@Override
		public void onLoadFinished(Loader<ListResult<Task>> loader,
				ListResult<Task> tasks) {
			loading = false;
			swipeRefreshLayout.setRefreshing(false);

			if (tasks.getStatusCode() <= 0 || tasks.getStatusCode() >= 300) {
				Toast.makeText(getActivity(), tasks.getContent(), Toast.LENGTH_LONG)
						.show();
				return;
			}

			if (tasks.getStart() == 0)
				mTaskListAdapter.clear();

			mTaskListAdapter.addAll(tasks.getItems());
			mTaskListAdapter.setServerListSize(tasks.getTotal());
			if (isResumed()) {
				setListShown(true);
			} else {
				setListShownNoAnimation(true);
			}
		}

		@Override
		public void onLoaderReset(Loader<ListResult<Task>> arg0) {
		}

		public abstract class EndlessScrollListener implements OnScrollListener {
			// The minimum amount of items to have below your current scroll position
			// before loading more.
			private int visibleThreshold = 5;
			// The current offset index of data you have loaded
			private int currentPage = 0;
			// The total number of items in the dataset after the last load
			private int previousTotalItemCount = 0;
			// True if we are still waiting for the last set of data to load.
			private boolean loading = true;
			// Sets the starting page index
			private int startingPageIndex = 0;

			public EndlessScrollListener() {
			}

			public EndlessScrollListener(int visibleThreshold) {
				this.visibleThreshold = visibleThreshold;
			}

			public EndlessScrollListener(int visibleThreshold, int startPage) {
				this.visibleThreshold = visibleThreshold;
				this.startingPageIndex = startPage;
				this.currentPage = startPage;
				if (startPage > 0)
					loading = false;
			}

			// This happens many times a second during a scroll, so be wary of the
			// code you place here.
			// We are given a few useful parameters to help us work out if we need to
			// load some more data,
			// but first we check if we are waiting for the previous load to finish.
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// If the total item count is zero and the previous isn't, assume the
				// list is invalidated and should be reset back to initial state
				if (totalItemCount < previousTotalItemCount) {
					this.currentPage = this.startingPageIndex;
					this.previousTotalItemCount = totalItemCount;
					if (totalItemCount == 0) {
						this.loading = true;
					}
				}

				// If it¡¯s still loading, we check to see if the dataset count has
				// changed, if so we conclude it has finished loading and update the
				// current page
				// number and total item count.
				if (loading && (totalItemCount > previousTotalItemCount)) {
					loading = false;
					previousTotalItemCount = totalItemCount;
					currentPage++;
				}

				// If it isn¡¯t currently loading, we check to see if we have breached
				// the visibleThreshold and need to reload more data.
				// If we do need to reload some more data, we execute onLoadMore to
				// fetch the data.
				if (!loading
						&& (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
					loading = onLoadMore(currentPage + 1, totalItemCount);
				}
			}

			// Defines the process for actually loading more data based on page
			public abstract boolean onLoadMore(int page, int totalItemsCount);

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// Don't take any action on changed
			}
		}
	}
}
