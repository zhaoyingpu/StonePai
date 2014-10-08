package com.stone.pai.ui;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Sub-class of {@link android.support.v4.widget.SwipeRefreshLayout} for use in this
 * {@link android.support.v4.app.ListFragment}. The reason that this is needed is because
 * {@link android.support.v4.widget.SwipeRefreshLayout} only supports a single child, which it
 * expects to be the one which triggers refreshes. In our case the layout's child is the content
 * view returned from
 * {@link android.support.v4.app.ListFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)}
 * which is a {@link android.view.ViewGroup}.
 *
 * <p>To enable 'swipe-to-refresh' support via the {@link android.widget.ListView} we need to
 * override the default behavior and properly signal when a gesture is possible. This is done by
 * overriding {@link #canChildScrollUp()}.
 */
public class SwipeRefreshListLayout extends SwipeRefreshLayout {
    /**
     * As mentioned above, we need to override this method to properly signal when a
     * 'swipe-to-refresh' is possible.
     *
     * @return true if the {@link android.widget.ListView} is visible and can scroll up.
     */
	public SwipeRefreshListLayout(Context context) {
		super(context);
	}
	
    public SwipeRefreshListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
	public boolean canChildScrollUp() {
		ListView listView =  (ListView)findViewById(android.R.id.list);

        if (listView.getVisibility() == View.VISIBLE) {
            return canListViewScrollUp(listView);
        } else {
            return false;
        }
	}
    /**
     * Utility method to check whether a {@link ListView} can scroll up from it's current position.
     * Handles platform version differences, providing backwards compatible functionality where
     * needed.
     */
    private static boolean canListViewScrollUp(ListView listView) {
        if (android.os.Build.VERSION.SDK_INT >= 14) {
            // For ICS and above we can call canScrollVertically() to determine this
            return ViewCompat.canScrollVertically(listView, -1);
        } else {
            // Pre-ICS we need to manually check the first visible item and the child view's top
            // value
            return listView.getChildCount() > 0 &&
                    (listView.getFirstVisiblePosition() > 0
                            || listView.getChildAt(0).getTop() < listView.getPaddingTop());
        }
    }
}
