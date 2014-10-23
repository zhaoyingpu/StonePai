package com.stone.pai.ui;

import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.stone.pai.ListLoader;
import com.stone.pai.R;
import com.stone.pai.TaskList;
import com.stone.pai.TaskListAdapter;
import com.stone.pai.bean.ListResult;
import com.stone.pai.bean.Task;
import com.stone.pai.voice.VolumeEnvelopeView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends ListFragment implements
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
	@InjectView(R.id.etMessage)
	EditText editMessage; 
	@InjectView(R.id.ivSend)
	ImageView ivSend;
	@InjectView(R.id.ivVoice)
	ImageView ivVoice;
	@InjectView(R.id.bSend)
	View mSendButton;
	@InjectView(R.id.bNew)
	View mNewButton;
	@InjectView(R.id.volume_envelope)
	VolumeEnvelopeView mEnvelopeView;
	@InjectView(R.id.voiceHeader)
	View mVoiceHeaderView;
	@InjectView(R.id.voiceRecTimeLeft)
	TextView mVoiceRecTimeLeftView;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static MainFragment newInstance(int sectionNumber,
			TaskList taskList) {
		MainFragment fragment = new MainFragment();

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

	public MainFragment() {

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
		
		mSendButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (editMessage.getText().toString().length() > 0) {
					//sendMessage(friend.getName());
				}
			}
		});
		mSendButton.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (editMessage.getText().toString().length() > 0) {
					//sendMessage(friend.getName());
				}
				else {
					//VoiceController.startRecording(MainActivity.this, friend.getName());
				}

				return true;
			}
		});

		mSendButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (true) {//VoiceController.isRecording()) {
						if (editMessage.getText().toString().length() == 0) {

							int width = mSendButton.getWidth();

							// if user let go of send button out of send button + width (height) bounds, don't send the recording
							Rect rect = new Rect(mSendButton.getLeft() - width, mSendButton.getTop() - width, mSendButton.getRight(), mSendButton
									.getBottom() + width);

							boolean send = true;
							if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {

								send = false;

								//Utils.makeToast(MainActivity.this, getString(R.string.recording_cancelled));

							}

							final boolean finalSend = send;

							//SurespotLog.d(TAG, "voice record up");

							// truncates without the delay for some reason
							mSendButton.post(new Runnable() {

								@Override
								public void run() {
									//VoiceController.stopRecording(MainActivity.this, finalSend);

								}
							});
						}
					}
				}

				return false;
			}
		});
		
		editMessage.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (editMessage.getText().length() > 0) {
					ivVoice.setVisibility(View.GONE);
					ivSend.setVisibility(View.VISIBLE);
				}
				else {
					ivVoice.setVisibility(View.VISIBLE);
					ivSend.setVisibility(View.GONE);
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
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
		mTaskListAdapter.getItem(position).setSubject(String.format("%d", position));
		mTaskListAdapter.notifyDataSetChanged();
	}

	@Override
	public void onRefresh() {
		Bundle args = getArguments();
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