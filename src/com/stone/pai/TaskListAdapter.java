package com.stone.pai;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.stone.pai.bean.Task;
import com.stone.pai.network.PaiAsyncHttpClient;
import com.stone.pai.utilities.DateFormat;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TaskListAdapter extends ArrayAdapter<Task> {
	private static final int TYPE_LOADING = 0;
	private static final int TYPE_ITEM = 1;
	private static final int TYPE_ITEM_NOACTION = 2;
	private static final int TYPE_MAX_COUNT = TYPE_ITEM_NOACTION + 1;

	// the serverListSize is the total number of items on the server side,
	// which should be returned from the web request results
	protected int serverListSize = -1;

	private static final int[] colors = new int[] {
			Color.argb(255, 89, 162, 190), Color.argb(255, 103, 191, 116),
			Color.argb(255, 241, 99, 100), Color.argb(255, 245, 133, 89),
			Color.argb(255, 228, 198, 46), Color.argb(255, 173, 98, 167),
			Color.argb(255, 32, 147, 205), };

	private final LayoutInflater mInflater;
	private final DisplayImageOptions options;

	public TaskListAdapter(Context context) {
		super(context, R.layout.fragment_task_list_item);
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.avatar_placeholder_gray)
				.showImageForEmptyUri(R.drawable.avatar_placeholder)
				.showImageOnFail(R.drawable.avatar_placeholder_gray)
				.delayBeforeLoading(200).cacheInMemory(true) // default
				.cacheOnDisk(true) // default
				.build();
	}

	public void setServerListSize(int serverListSize) {
		this.serverListSize = serverListSize;
	}

	public boolean hasMoreItem() {
		return serverListSize > 0 && getTaskCount() < serverListSize;
	}

	public int getTaskCount() {
		return super.getCount();
	}

	@Override
	public int getCount() {
		return getTaskCount() + 1;
	}

	@Override
	public Task getItem(int position) {
		return (getItemViewType(position) != TYPE_LOADING) ? super
				.getItem(position) : null;
	}

	@Override
	public long getItemId(int position) {
		return (getItemViewType(position) == TYPE_LOADING) ? position : -1;
	}

	@Override
	public int getItemViewType(int position) {
		if (position >= getTaskCount())
			return TYPE_LOADING;
		else if (super.getItem(position).getAllowed_actions().size() > 0)
			return TYPE_ITEM;
		else
			return TYPE_ITEM_NOACTION;
	}

	@Override
	public int getViewTypeCount() {
		return TYPE_MAX_COUNT;
	}

	@Override
	public boolean isEnabled(int position) {

		return getItemViewType(position) != TYPE_LOADING;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		int type = getItemViewType(position);
		if (type == TYPE_LOADING) {
			// display the last row
			return getFooterView(position, convertView, parent);
		}

		if (convertView == null) {
			switch (type) {
			case TYPE_ITEM:
				convertView = mInflater.inflate(R.layout.fragment_task_list_item,
					parent, false);
				break;
			case TYPE_ITEM_NOACTION:
				convertView = mInflater.inflate(
					R.layout.fragment_task_list_item_noaction, parent, false);
				break;
			}
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Task task = getItem(position);

		holder.name.setText(task.getOwner().getContact().getName());
		holder.jobTitle.setText(task.getOwner().getTitle());
		holder.createdTime.setText(DateFormat.toString(task.getCreated_at()));
		holder.content.setText(task.getContent());
		holder.status.setText(task.getStatus_detail_str());
		holder.kpi2.setText(task.getKpi2().toString());

		if (task.getOwner() != null
				&& task.getOwner().getProfile_image_url() != null) {
			ImageLoader.getInstance().displayImage(
				PaiAsyncHttpClient.getAbsoluteUrl(task.getOwner()
						.getProfile_image_url()), holder.avatar, options);
			holder.avatar.setBackgroundColor(Color.TRANSPARENT);
		} else {
			holder.avatar.setImageResource(R.drawable.ic_generic_man);
			holder.avatar.setBackgroundColor(colors[task.getOwner().getId()
					% colors.length]);
		}

		holder.attachment
				.setVisibility(task.getAttachments().isEmpty() ? View.INVISIBLE
						: View.VISIBLE);
		return convertView;
	}

	public View getFooterView(int position, View convertView, ViewGroup parent) {
		if (position >= serverListSize && serverListSize > 0) {
			// the ListView has reached the last row
			TextView tvLastRow = new TextView(getContext());
			tvLastRow.setHint("没有更多任务.");
			tvLastRow.setGravity(Gravity.CENTER);
			return tvLastRow;
		}

		View row = convertView;
		if (row == null) {
			row = mInflater.inflate(R.layout.fragment_progress, parent, false);
		}

		return row;
	}

	static class ViewHolder {
		@InjectView(R.id.task_owner_name)
		TextView name;
		@InjectView(R.id.task_owner_title)
		TextView jobTitle;
		@InjectView(R.id.task_created_time)
		TextView createdTime;
		@InjectView(R.id.task_content)
		TextView content;
		@InjectView(R.id.task_status)
		TextView status;
		@InjectView(R.id.task_kpi2)
		TextView kpi2;
		@InjectView(R.id.task_owner_avatar)
		ImageView avatar;
		@InjectView(R.id.task_attachment)
		ImageView attachment;

		@Optional
		@InjectView(R.id.task_action1)
		Button button1;
		@Optional
		@InjectView(R.id.task_action2)
		Button button2;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}
}