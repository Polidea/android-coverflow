package pl.polidea.coverflow;

/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Adapter;

/**
 * An AdapterView is a view whose children are determined by an Adapter.
 * 
 * <p>
 * See ListView, GridView, Spinner and Gallery for commonly used subclasses of
 * AdapterView.
 * 
 * @param <T>
 *            the generic type
 */
public abstract class AbstractCoverAdapterView<T extends Adapter> extends
        ViewGroup {

    public int getmFirstPosition() {
        return mFirstPosition;
    }

    public void setmFirstPosition(final int mFirstPosition) {
        this.mFirstPosition = mFirstPosition;
    }

    public int getmSpecificTop() {
        return mSpecificTop;
    }

    public void setmSpecificTop(final int mSpecificTop) {
        this.mSpecificTop = mSpecificTop;
    }

    public int getmSyncPosition() {
        return mSyncPosition;
    }

    public void setmSyncPosition(final int mSyncPosition) {
        this.mSyncPosition = mSyncPosition;
    }

    public long getmSyncRowId() {
        return mSyncRowId;
    }

    public void setmSyncRowId(final long mSyncRowId) {
        this.mSyncRowId = mSyncRowId;
    }

    public long getmSyncHeight() {
        return mSyncHeight;
    }

    public void setmSyncHeight(final long mSyncHeight) {
        this.mSyncHeight = mSyncHeight;
    }

    public boolean ismNeedSync() {
        return mNeedSync;
    }

    public void setmNeedSync(final boolean mNeedSync) {
        this.mNeedSync = mNeedSync;
    }

    public int getmSyncMode() {
        return mSyncMode;
    }

    public void setmSyncMode(final int mSyncMode) {
        this.mSyncMode = mSyncMode;
    }

    public int getmLayoutHeight() {
        return mLayoutHeight;
    }

    public void setmLayoutHeight(final int mLayoutHeight) {
        this.mLayoutHeight = mLayoutHeight;
    }

    public boolean ismInLayout() {
        return mInLayout;
    }

    public void setmInLayout(final boolean mInLayout) {
        this.mInLayout = mInLayout;
    }

    public OnItemSelectedListener getmOnItemSelectedListener() {
        return mOnItemSelectedListener;
    }

    public void setmOnItemSelectedListener(
            final OnItemSelectedListener mOnItemSelectedListener) {
        this.mOnItemSelectedListener = mOnItemSelectedListener;
    }

    public OnItemClickListener getmOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setmOnItemClickListener(
            final OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public OnItemLongClickListener getmOnItemLongClickListener() {
        return mOnItemLongClickListener;
    }

    public void setmOnItemLongClickListener(
            final OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public boolean ismDataChanged() {
        return mDataChanged;
    }

    public void setmDataChanged(final boolean mDataChanged) {
        this.mDataChanged = mDataChanged;
    }

    public int getmNextSelectedPosition() {
        return mNextSelectedPosition;
    }

    public void setmNextSelectedPosition(final int mNextSelectedPosition) {
        this.mNextSelectedPosition = mNextSelectedPosition;
    }

    public long getmNextSelectedRowId() {
        return mNextSelectedRowId;
    }

    public void setmNextSelectedRowId(final long mNextSelectedRowId) {
        this.mNextSelectedRowId = mNextSelectedRowId;
    }

    public int getmSelectedPosition() {
        return mSelectedPosition;
    }

    public void setmSelectedPosition(final int mSelectedPosition) {
        this.mSelectedPosition = mSelectedPosition;
    }

    public long getmSelectedRowId() {
        return mSelectedRowId;
    }

    public void setmSelectedRowId(final long mSelectedRowId) {
        this.mSelectedRowId = mSelectedRowId;
    }

    public View getmEmptyView() {
        return mEmptyView;
    }

    public void setmEmptyView(final View mEmptyView) {
        this.mEmptyView = mEmptyView;
    }

    public int getmItemCount() {
        return mItemCount;
    }

    public void setmItemCount(final int mItemCount) {
        this.mItemCount = mItemCount;
    }

    public int getmOldItemCount() {
        return mOldItemCount;
    }

    public void setmOldItemCount(final int mOldItemCount) {
        this.mOldItemCount = mOldItemCount;
    }

    public int getmOldSelectedPosition() {
        return mOldSelectedPosition;
    }

    public void setmOldSelectedPosition(final int mOldSelectedPosition) {
        this.mOldSelectedPosition = mOldSelectedPosition;
    }

    public long getmOldSelectedRowId() {
        return mOldSelectedRowId;
    }

    public void setmOldSelectedRowId(final long mOldSelectedRowId) {
        this.mOldSelectedRowId = mOldSelectedRowId;
    }

    public boolean ismDesiredFocusableState() {
        return mDesiredFocusableState;
    }

    public void setmDesiredFocusableState(final boolean mDesiredFocusableState) {
        this.mDesiredFocusableState = mDesiredFocusableState;
    }

    public boolean ismDesiredFocusableInTouchModeState() {
        return mDesiredFocusableInTouchModeState;
    }

    public void setmDesiredFocusableInTouchModeState(
            final boolean mDesiredFocusableInTouchModeState) {
        this.mDesiredFocusableInTouchModeState = mDesiredFocusableInTouchModeState;
    }

    public SelectionNotifier getmSelectionNotifier() {
        return mSelectionNotifier;
    }

    public void setmSelectionNotifier(final SelectionNotifier mSelectionNotifier) {
        this.mSelectionNotifier = mSelectionNotifier;
    }

    public boolean ismBlockLayoutRequests() {
        return mBlockLayoutRequests;
    }

    public void setmBlockLayoutRequests(final boolean mBlockLayoutRequests) {
        this.mBlockLayoutRequests = mBlockLayoutRequests;
    }

    /**
     * The item view type returned by {@link Adapter#getItemViewType(int)} when
     * the adapter does not want the item's view recycled.
     */
    public static final int ITEM_VIEW_TYPE_IGNORE = -1;

    /**
     * The item view type returned by {@link Adapter#getItemViewType(int)} when
     * the item is a header or footer.
     */
    public static final int ITEM_VIEW_TYPE_HEADER_OR_FOOTER = -2;

    /** The position of the first child displayed. */
    @ViewDebug.ExportedProperty
    private int mFirstPosition = 0;

    /**
     * The offset in pixels from the top of the AdapterView to the top of the
     * view to select during the next layout.
     */
    private int mSpecificTop;

    /** Position from which to start looking for mSyncRowId. */
    private int mSyncPosition;

    /** Row id to look for when data has changed. */
    private long mSyncRowId = INVALID_ROW_ID;

    /** Height of the view when mSyncPosition and mSyncRowId where set. */
    private long mSyncHeight;

    /** True if we need to sync to mSyncRowId. */
    private boolean mNeedSync = false;

    /**
     * Indicates whether to sync based on the selection or position. Possible
     * values are {@link #SYNC_SELECTED_POSITION} or
     * {@link #SYNC_FIRST_POSITION}.
     */
    private int mSyncMode;

    /** Our height after the last layout. */
    private int mLayoutHeight;

    /** Sync based on the selected child. */
    static final int SYNC_SELECTED_POSITION = 0;

    /** Sync based on the first child displayed. */
    static final int SYNC_FIRST_POSITION = 1;

    /** Maximum amount of time to spend in {@link #findSyncPosition()}. */
    static final int SYNC_MAX_DURATION_MILLIS = 100;

    /**
     * Indicates that this view is currently being laid out.
     */
    private boolean mInLayout = false;

    /**
     * The listener that receives notifications when an item is selected.
     */
    private OnItemSelectedListener mOnItemSelectedListener;

    /**
     * The listener that receives notifications when an item is clicked.
     */
    private OnItemClickListener mOnItemClickListener;

    /**
     * The listener that receives notifications when an item is long clicked.
     */
    private OnItemLongClickListener mOnItemLongClickListener;

    /** True if the data has changed since the last layout. */
    private boolean mDataChanged;

    /**
     * The position within the adapter's data set of the item to select during
     * the next layout.
     */
    @ViewDebug.ExportedProperty
    private int mNextSelectedPosition = INVALID_POSITION;

    /**
     * The item id of the item to select during the next layout.
     */
    private long mNextSelectedRowId = INVALID_ROW_ID;

    /**
     * The position within the adapter's data set of the currently selected
     * item.
     */
    @ViewDebug.ExportedProperty
    private int mSelectedPosition = INVALID_POSITION;

    /**
     * The item id of the currently selected item.
     */
    private long mSelectedRowId = INVALID_ROW_ID;

    /**
     * View to show if there are no items to show.
     */
    private View mEmptyView;

    /**
     * The number of items in the current adapter.
     */
    @ViewDebug.ExportedProperty
    private int mItemCount;

    /**
     * The number of items in the adapter before a data changed event occured.
     */
    private int mOldItemCount;

    /**
     * Represents an invalid position. All valid positions are in the range 0 to
     * 1 less than the number of items in the current adapter.
     */
    public static final int INVALID_POSITION = -1;

    /** Represents an empty or invalid row id. */
    public static final long INVALID_ROW_ID = Long.MIN_VALUE;

    /** The last selected position we used when notifying. */
    private int mOldSelectedPosition = INVALID_POSITION;

    /** The id of the last selected position we used when notifying. */
    private long mOldSelectedRowId = INVALID_ROW_ID;

    /**
     * Indicates what focusable state is requested when calling setFocusable().
     * In addition to this, this view has other criteria for actually
     * determining the focusable state (such as whether its empty or the text
     * filter is shown).
     * 
     * @see #setFocusable(boolean)
     * @see #checkFocus()
     */
    private boolean mDesiredFocusableState;

    /** The m desired focusable in touch mode state. */
    private boolean mDesiredFocusableInTouchModeState;

    /** The m selection notifier. */
    private SelectionNotifier mSelectionNotifier;
    /**
     * When set to true, calls to requestLayout() will not propagate up the
     * parent hierarchy. This is used to layout the children during a layout
     * pass.
     */
    private boolean mBlockLayoutRequests = false;

    /**
     * Instantiates a new cover adapter view.
     * 
     * @param context
     *            the context
     */
    public AbstractCoverAdapterView(final Context context) {
        super(context);
    }

    /**
     * Instantiates a new cover adapter view.
     * 
     * @param context
     *            the context
     * @param attrs
     *            the attrs
     */
    public AbstractCoverAdapterView(final Context context,
            final AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new cover adapter view.
     * 
     * @param context
     *            the context
     * @param attrs
     *            the attrs
     * @param defStyle
     *            the def style
     */
    public AbstractCoverAdapterView(final Context context,
            final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Interface definition for a callback to be invoked when an item in this
     * AdapterView has been clicked.
     * 
     * @see OnItemClickEvent
     */
    public interface OnItemClickListener {

        /**
         * Callback method to be invoked when an item in this AdapterView has
         * been clicked.
         * <p>
         * Implementers can call getItemAtPosition(position) if they need to
         * access the data associated with the selected item.
         * 
         * @param parent
         *            The AdapterView where the click happened.
         * @param view
         *            The view within the AdapterView that was clicked (this
         *            will be a view provided by the adapter)
         * @param position
         *            The position of the view in the adapter.
         * @param id
         *            The row id of the item that was clicked.
         */
        void onItemClick(AbstractCoverAdapterView< ? > parent, View view,
                int position, long id);

    }

    /**
     * Register a callback to be invoked when an item in this AdapterView has
     * been clicked.
     * 
     * @param listener
     *            The callback that will be invoked.
     */
    public void setOnItemClickListener(final OnItemClickListener listener) {
        setmOnItemClickListener(listener);
    }

    /**
     * Gets the on item click listener.
     * 
     * @return The callback to be invoked with an item in this AdapterView has
     *         been clicked, or null id no callback has been set.
     */
    public final OnItemClickListener getOnItemClickListener() {
        return getmOnItemClickListener();
    }

    /**
     * Call the OnItemClickListener, if it is defined.
     * 
     * @param view
     *            The view within the AdapterView that was clicked.
     * @param position
     *            The position of the view in the adapter.
     * @param id
     *            The row id of the item that was clicked.
     * @return True if there was an assigned OnItemClickListener that was
     *         called, false otherwise is returned.
     */
    public boolean performItemClick(final View view, final int position,
            final long id) {
        if (getmOnItemClickListener() != null) {
            playSoundEffect(SoundEffectConstants.CLICK);
            getmOnItemClickListener().onItemClick(this, view, position, id);
            return true;
        }

        return false;
    }

    /**
     * Interface definition for a callback to be invoked when an item in this
     * view has been clicked and held.
     * 
     * @see OnItemLongClickEvent
     */
    public interface OnItemLongClickListener {
        /**
         * Callback method to be invoked when an item in this view has been
         * clicked and held.
         * 
         * Implementers can call getItemAtPosition(position) if they need to
         * access the data associated with the selected item.
         * 
         * @param coverFlow
         *            The AbsListView where the click happened
         * @param view
         *            The view within the AbsListView that was clicked
         * @param position
         *            The position of the view in the list
         * @param id
         *            The row id of the item that was clicked
         * 
         * @return true if the callback consumed the long click, false otherwise
         */
        boolean onItemLongClick(CoverFlow coverFlow, View view, int position,
                long id);
    }

    /**
     * Register a callback to be invoked when an item in this AdapterView has
     * been clicked and held.
     * 
     * @param listener
     *            The callback that will run
     */
    public void setOnItemLongClickListener(
            final OnItemLongClickListener listener) {
        if (!isLongClickable()) {
            setLongClickable(true);
        }
        setmOnItemLongClickListener(listener);
    }

    /**
     * Gets the on item long click listener.
     * 
     * @return The callback to be invoked with an item in this AdapterView has
     *         been clicked and held, or null id no callback as been set.
     */
    public final OnItemLongClickListener getOnItemLongClickListener() {
        return getmOnItemLongClickListener();
    }

    /**
     * Interface definition for a callback to be invoked when an item in this
     * view has been selected.
     * 
     * @see OnItemSelectedEvent
     */
    public interface OnItemSelectedListener {
        /**
         * Callback method to be invoked when an item in this view has been
         * selected.
         * 
         * Impelmenters can call getItemAtPosition(position) if they need to
         * access the data associated with the selected item.
         * 
         * @param parent
         *            The AdapterView where the selection happened
         * @param view
         *            The view within the AdapterView that was clicked
         * @param position
         *            The position of the view in the adapter
         * @param id
         *            The row id of the item that is selected
         */
        void onItemSelected(AbstractCoverAdapterView< ? > parent, View view,
                int position, long id);

        /**
         * Callback method to be invoked when the selection disappears from this
         * view. The selection can disappear for instance when touch is
         * activated or when the adapter becomes empty.
         * 
         * @param parent
         *            The AdapterView that now contains no selected item.
         */
        void onNothingSelected(AbstractCoverAdapterView< ? > parent);
    }

    /**
     * Register a callback to be invoked when an item in this AdapterView has
     * been selected.
     * 
     * @param listener
     *            The callback that will run
     */
    public void setOnItemSelectedListener(final OnItemSelectedListener listener) {
        setmOnItemSelectedListener(listener);
    }

    /**
     * Gets the on item selected listener.
     * 
     * @return the on item selected listener
     */
    public final OnItemSelectedListener getOnItemSelectedListener() {
        return getmOnItemSelectedListener();
    }

    /**
     * Extra menu information provided to the.
     * 
     * android.view.View.OnCreateContextMenuListener#onCreateContextMenu(
     * ContextMenu, View, ContextMenuInfo) callback when a context menu is
     * brought up for this AdapterView.
     */
    public static class AdapterContextMenuInfo implements
            ContextMenu.ContextMenuInfo {

        /**
         * Instantiates a new adapter context menu info.
         * 
         * @param targetView
         *            the target view
         * @param position
         *            the position
         * @param id
         *            the id
         */
        public AdapterContextMenuInfo(final View targetView,
                final int position, final long id) {
            this.setTargetView(targetView);
            this.setPosition(position);
            this.setId(id);
        }

        public final void setTargetView(final View targetView) {
            this.targetView = targetView;
        }

        public final View getTargetView() {
            return targetView;
        }

        public final void setPosition(final int position) {
            this.position = position;
        }

        public int getPosition() {
            return position;
        }

        public final void setId(final long id) {
            this.id = id;
        }

        public long getId() {
            return id;
        }

        /**
         * The child view for which the context menu is being displayed. This
         * will be one of the children of this AdapterView.
         */
        private View targetView;

        /**
         * The position in the adapter for which the context menu is being
         * displayed.
         */
        private int position;

        /**
         * The row id of the item for which the context menu is being displayed.
         */
        private long id;
    }

    /**
     * Returns the adapter currently associated with this widget.
     * 
     * @return The adapter used to provide this view's content.
     */
    public abstract T getAdapter();

    /**
     * Sets the adapter that provides the data and the views to represent the
     * data in this widget.
     * 
     * @param adapter
     *            The adapter to use to create this view's content.
     */
    public abstract void setAdapter(T adapter);

    /**
     * This method is not supported and throws an UnsupportedOperationException
     * when called.
     * 
     * @param child
     *            Ignored.
     */
    @Override
    public void addView(final View child) {
        throw new UnsupportedOperationException(
                "addView(View) is not supported in AdapterView");
    }

    /**
     * This method is not supported and throws an UnsupportedOperationException
     * when called.
     * 
     * @param child
     *            Ignored.
     * @param index
     *            Ignored.
     */
    @Override
    public void addView(final View child, final int index) {
        throw new UnsupportedOperationException(
                "addView(View, int) is not supported in AdapterView");
    }

    /**
     * This method is not supported and throws an UnsupportedOperationException
     * when called.
     * 
     * @param child
     *            Ignored.
     * @param params
     *            Ignored.
     */
    @Override
    public void addView(final View child, final LayoutParams params) {
        throw new UnsupportedOperationException("addView(View, LayoutParams) "
                + "is not supported in AdapterView");
    }

    /**
     * This method is not supported and throws an UnsupportedOperationException
     * when called.
     * 
     * @param child
     *            Ignored.
     * @param index
     *            Ignored.
     * @param params
     *            Ignored.
     */
    @Override
    public void addView(final View child, final int index,
            final LayoutParams params) {
        throw new UnsupportedOperationException(
                "addView(View, int, LayoutParams) "
                        + "is not supported in AdapterView");
    }

    /**
     * This method is not supported and throws an UnsupportedOperationException
     * when called.
     * 
     * @param child
     *            Ignored.
     */
    @Override
    public void removeView(final View child) {
        throw new UnsupportedOperationException(
                "removeView(View) is not supported in AdapterView");
    }

    /**
     * This method is not supported and throws an UnsupportedOperationException
     * when called.
     * 
     * @param index
     *            Ignored.
     */
    @Override
    public void removeViewAt(final int index) {
        throw new UnsupportedOperationException(
                "removeViewAt(int) is not supported in AdapterView");
    }

    /**
     * This method is not supported and throws an UnsupportedOperationException
     * when called.
     * 
     */
    @Override
    public void removeAllViews() {
        throw new UnsupportedOperationException(
                "removeAllViews() is not supported in AdapterView");
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.ViewGroup#onLayout(boolean, int, int, int, int)
     */
    @Override
    protected void onLayout(final boolean changed, final int left,
            final int top, final int right, final int bottom) {
        setmLayoutHeight(getHeight());
    }

    /**
     * Return the position of the currently selected item within the adapter's
     * data set.
     * 
     * @return int Position (starting at 0), or {@link #INVALID_POSITION} if
     *         there is nothing selected.
     */
    // @ViewDebug.CapturedViewProperty
    public int getSelectedItemPosition() {
        return getmNextSelectedPosition();
    }

    /**
     * Gets the selected item id.
     * 
     * @return The id corresponding to the currently selected item, or
     *         {@link #INVALID_ROW_ID} if nothing is selected.
     */
    // @ViewDebug.CapturedViewProperty
    public long getSelectedItemId() {
        return getmNextSelectedRowId();
    }

    /**
     * Gets the selected view.
     * 
     * @return The view corresponding to the currently selected item, or null if
     *         nothing is selected
     */
    public abstract View getSelectedView();

    /**
     * Gets the selected item.
     * 
     * @return The data corresponding to the currently selected item, or null if
     *         there is nothing selected.
     */
    public Object getSelectedItem() {
        final T adapter = getAdapter();
        final int selection = getSelectedItemPosition();
        if (adapter != null && adapter.getCount() > 0 && selection >= 0) {
            return adapter.getItem(selection);
        } else {
            return null;
        }
    }

    /**
     * Gets the count.
     * 
     * @return The number of items owned by the Adapter associated with this
     *         AdapterView. (This is the number of data items, which may be
     *         larger than the number of visible view.)
     */
    // @ViewDebug.CapturedViewProperty
    public int getCount() {
        return getmItemCount();
    }

    /**
     * Get the position within the adapter's data set for the view, where view
     * is a an adapter item or a descendant of an adapter item.
     * 
     * @param view
     *            an adapter item, or a descendant of an adapter item. This must
     *            be visible in this AdapterView at the time of the call.
     * @return the position within the adapter's data set of the view, or
     *         {@link #INVALID_POSITION} if the view does not correspond to a
     *         list item (or it is not currently visible).
     */
    public int getPositionForView(final View view) {
        View listItem = view;
        try {
            View v = (View) listItem.getParent();
            while (!v.equals(this)) {
                listItem = v;
                v = (View) listItem.getParent();
            }
        } catch (final ClassCastException e) {
            // We made it up to the window without find this list view
            return INVALID_POSITION;
        }

        // Search the children for the list item
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i).equals(listItem)) {
                return getmFirstPosition() + i;
            }
        }

        // Child not found!
        return INVALID_POSITION;
    }

    /**
     * Returns the position within the adapter's data set for the first item
     * displayed on screen.
     * 
     * @return The position within the adapter's data set
     */
    public int getFirstVisiblePosition() {
        return getmFirstPosition();
    }

    /**
     * Returns the position within the adapter's data set for the last item
     * displayed on screen.
     * 
     * @return The position within the adapter's data set
     */
    public int getLastVisiblePosition() {
        return getmFirstPosition() + getChildCount() - 1;
    }

    /**
     * Sets the currently selected item.
     * 
     * @param position
     *            Index (starting at 0) of the data item to be selected.
     */
    public abstract void setSelection(int position);

    /**
     * Sets the view to show if the adapter is empty.
     * 
     * @param emptyView
     *            the new empty view
     */
    public void setEmptyView(final View emptyView) {
        setmEmptyView(emptyView);

        final T adapter = getAdapter();
        final boolean empty = adapter == null || adapter.isEmpty();
        updateEmptyStatus(empty);
    }

    /**
     * When the current adapter is empty, the AdapterView can display a special
     * view call the empty view. The empty view is used to provide feedback to
     * the user that no data is available in this AdapterView.
     * 
     * @return The view to show if the adapter is empty.
     */
    public View getEmptyView() {
        return getmEmptyView();
    }

    /**
     * Indicates whether this view is in filter mode. Filter mode can for
     * instance be enabled by a user when typing on the keyboard.
     * 
     * @return True if the view is in filter mode, false otherwise.
     */
    boolean isInFilterMode() { // NOPMD by potiuk on 1/28/11 2:43 AM
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.View#setFocusable(boolean)
     */
    @Override
    public void setFocusable(final boolean focusable) {
        final T adapter = getAdapter();
        final boolean empty = adapter == null || adapter.getCount() == 0;

        mDesiredFocusableState = focusable;
        if (!focusable) {
            mDesiredFocusableInTouchModeState = false;
        }

        super.setFocusable(focusable && (!empty || isInFilterMode()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.View#setFocusableInTouchMode(boolean)
     */
    @Override
    public void setFocusableInTouchMode(final boolean focusable) {
        final T adapter = getAdapter();
        final boolean empty = adapter == null || adapter.getCount() == 0;

        mDesiredFocusableInTouchModeState = focusable;
        if (focusable) {
            mDesiredFocusableState = true;
        }

        super.setFocusableInTouchMode(focusable && (!empty || isInFilterMode()));
    }

    /**
     * Check focus.
     */
    void checkFocus() {
        final T adapter = getAdapter();
        final boolean empty = adapter == null || adapter.getCount() == 0;
        final boolean focusable = !empty || isInFilterMode();
        // The order in which we set focusable in touch mode/focusable may
        // matter
        // for the client, see View.setFocusableInTouchMode() comments for more
        // details
        super.setFocusableInTouchMode(focusable
                && mDesiredFocusableInTouchModeState);
        super.setFocusable(focusable && mDesiredFocusableState);
        if (getmEmptyView() != null) {
            updateEmptyStatus(adapter == null || adapter.isEmpty());
        }
    }

    /**
     * Update the status of the list based on the empty parameter. If empty is
     * true and we have an empty view, display it. In all the other cases, make
     * sure that the listview is VISIBLE and that the empty view is GONE (if
     * it's not null).
     * 
     * @param empty
     *            the empty
     */
    private void updateEmptyStatus(final boolean emptyParam) {
        boolean empty = emptyParam;
        if (isInFilterMode()) {
            empty = false;
        }

        if (empty) {
            if (getmEmptyView() == null) {
                // If the caller just removed our empty view, make sure the list
                // view is visible
                setVisibility(View.VISIBLE);
            } else {
                getmEmptyView().setVisibility(View.VISIBLE);
                setVisibility(View.GONE);
            }

            // We are now GONE, so pending layouts will not be dispatched.
            // Force one here to make sure that the state of the list matches
            // the state of the adapter.
            if (ismDataChanged()) {
                this.onLayout(false, getLeft(), getTop(), getRight(),
                        getBottom());
            }
        } else {
            if (getmEmptyView() != null) {
                getmEmptyView().setVisibility(View.GONE);
            }
            setVisibility(View.VISIBLE);
        }
    }

    /**
     * Gets the data associated with the specified position in the list.
     * 
     * @param position
     *            Which data to get
     * @return The data associated with the specified position in the list
     */
    public Object getItemAtPosition(final int position) {
        final T adapter = getAdapter();
        return adapter == null || position < 0 ? null : adapter
                .getItem(position);
    }

    /**
     * Gets the item id at position.
     * 
     * @param position
     *            the position
     * @return the item id at position
     */
    public long getItemIdAtPosition(final int position) {
        final T adapter = getAdapter();
        return adapter == null || position < 0 ? INVALID_ROW_ID : adapter
                .getItemId(position);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.view.View#setOnClickListener(android.view.View.OnClickListener)
     */
    @Override
    public void setOnClickListener(final OnClickListener l) {
        throw new IllegalStateException(
                "Don't call setOnClickListener for an AdapterView. "
                        + "You probably want setOnItemClickListener instead");
    }

    /**
     * Override to prevent freezing of any views created by the adapter.
     * 
     * @param container
     *            the container
     */
    @Override
    protected void dispatchSaveInstanceState(
            final SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    /**
     * Override to prevent thawing of any views created by the adapter.
     * 
     * @param container
     *            the container
     */
    @Override
    protected void dispatchRestoreInstanceState(
            final SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }

    /**
     * An asynchronous update interface for receiving notifications about
     * AdapterDataSet information as the AdapterDataSet is constructed.
     */
    class AdapterDataSetObserver extends DataSetObserver {

        /** The m instance state. */
        private Parcelable mInstanceState = null;

        /*
         * (non-Javadoc)
         * 
         * @see android.database.DataSetObserver#onChanged()
         */
        @Override
        public void onChanged() {
            setmDataChanged(true);
            setmOldItemCount(getmItemCount());
            setmItemCount(getAdapter().getCount());

            // Detect the case where a cursor that was previously invalidated
            // has
            // been repopulated with new data.
            if (AbstractCoverAdapterView.this.getAdapter().hasStableIds()
                    && mInstanceState != null && getmOldItemCount() == 0
                    && getmItemCount() > 0) {
                AbstractCoverAdapterView.this
                        .onRestoreInstanceState(mInstanceState);
                mInstanceState = null;
            } else {
                rememberSyncState();
            }
            checkFocus();
            requestLayout();
        }

        /*
         * (non-Javadoc)
         * 
         * @see android.database.DataSetObserver#onInvalidated()
         */
        @Override
        public void onInvalidated() {
            setmDataChanged(true);

            if (AbstractCoverAdapterView.this.getAdapter().hasStableIds()) {
                // Remember the current state for the case where our hosting
                // activity is being
                // stopped and later restarted
                mInstanceState = AbstractCoverAdapterView.this
                        .onSaveInstanceState();
            }

            // Data is invalid so we should reset our state
            setmOldItemCount(getmItemCount());
            setmItemCount(0);
            setmSelectedPosition(INVALID_POSITION);
            setmSelectedRowId(INVALID_ROW_ID);
            setmNextSelectedPosition(INVALID_POSITION);
            setmNextSelectedRowId(INVALID_ROW_ID);
            setmNeedSync(false);
            checkSelectionChanged();

            checkFocus();
            requestLayout();
        }

        /**
         * This method is called when information about an AdapterDataSet which
         * was previously requested using an asynchronous interface becomes
         * available.
         */
        public void clearSavedState() {
            mInstanceState = null;
        }
    }

    /**
     * The Class SelectionNotifier.
     */
    private class SelectionNotifier extends Handler implements Runnable {

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            if (ismDataChanged()) {
                // Data has changed between when this SelectionNotifier
                // was posted and now. We need to wait until the AdapterView
                // has been synched to the new data.
                post(this);
            } else {
                fireOnSelected();
            }
        }
    }

    /**
     * Selection changed.
     */
    void selectionChanged() {
        if (getmOnItemSelectedListener() != null) {
            if (ismInLayout() || ismBlockLayoutRequests()) {
                // If we are in a layout traversal, defer notification
                // by posting. This ensures that the view tree is
                // in a consistent state and is able to accomodate
                // new layout or invalidate requests.
                if (mSelectionNotifier == null) {
                    mSelectionNotifier = new SelectionNotifier();
                }
                mSelectionNotifier.post(mSelectionNotifier);
            } else {
                fireOnSelected();
            }
        }
    }

    /**
     * Fire on selected.
     */
    private void fireOnSelected() {
        if (getmOnItemSelectedListener() == null) {
            return;
        }

        final int selection = this.getSelectedItemPosition();
        if (selection >= 0) {
            final View v = getSelectedView();
            getmOnItemSelectedListener().onItemSelected(this, v, selection,
                    getAdapter().getItemId(selection));
        } else {
            getmOnItemSelectedListener().onNothingSelected(this);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.ViewGroup#canAnimate()
     */
    @Override
    protected boolean canAnimate() {
        return super.canAnimate() && getmItemCount() > 0;
    }

    /**
     * Handle data changed.
     */
    void handleDataChanged() {
        final int count = getmItemCount();
        boolean found = false;

        if (count > 0) {

            int newPos;

            // Find the row we are supposed to sync to
            if (ismNeedSync()) {
                // Update this first, since setNextSelectedPositionInt inspects
                // it
                setmNeedSync(false);

                // See if we can find a position in the new data with the same
                // id as the old selection
                newPos = findSyncPosition();
                if (newPos >= 0) {
                    // Verify that new selection is selectable
                    final int selectablePos = lookForSelectablePosition(newPos,
                            true);
                    if (selectablePos == newPos) {
                        // Same row id is selected
                        setNextSelectedPositionInt(newPos);
                        found = true;
                    }
                }
            }
            if (!found) {
                // Try to use the same position if we can't find matching data
                newPos = getSelectedItemPosition();

                // Pin position to the available range
                if (newPos >= count) {
                    newPos = count - 1;
                }
                if (newPos < 0) {
                    newPos = 0;
                }

                // Make sure we select something selectable -- first look down
                int selectablePos = lookForSelectablePosition(newPos, true);
                if (selectablePos < 0) {
                    // Looking down didn't work -- try looking up
                    selectablePos = lookForSelectablePosition(newPos, false);
                }
                if (selectablePos >= 0) {
                    setNextSelectedPositionInt(selectablePos);
                    checkSelectionChanged();
                    found = true;
                }
            }
        }
        if (!found) {
            // Nothing is selected
            setmSelectedPosition(INVALID_POSITION);
            setmSelectedRowId(INVALID_ROW_ID);
            setmNextSelectedPosition(INVALID_POSITION);
            setmNextSelectedRowId(INVALID_ROW_ID);
            setmNeedSync(false);
            checkSelectionChanged();
        }
    }

    /**
     * Check selection changed.
     */
    void checkSelectionChanged() {
        if (getmSelectedPosition() != getmOldSelectedPosition()
                || getmSelectedRowId() != getmOldSelectedRowId()) {
            selectionChanged();
            setmOldSelectedPosition(getmSelectedPosition());
            setmOldSelectedRowId(getmSelectedRowId());
        }
    }

    /**
     * Searches the adapter for a position matching mSyncRowId. The search
     * starts at mSyncPosition and then alternates between moving up and moving
     * down until 1) we find the right position, or 2) we run out of time, or 3)
     * we have looked at every position
     * 
     * @return Position of the row that matches mSyncRowId, or
     *         {@link #INVALID_POSITION} if it can't be found
     */
    int findSyncPosition() {
        final int count = getmItemCount();

        if (count == 0) {
            return INVALID_POSITION;
        }

        final long idToMatch = getmSyncRowId();
        int seed = getmSyncPosition();

        // If there isn't a selection don't hunt for it
        if (idToMatch == INVALID_ROW_ID) {
            return INVALID_POSITION;
        }

        // Pin seed to reasonable values
        seed = Math.max(0, seed);
        seed = Math.min(count - 1, seed);

        final long endTime = SystemClock.uptimeMillis()
                + SYNC_MAX_DURATION_MILLIS;

        long rowId;

        // first position scanned so far
        int first = seed;

        // last position scanned so far
        int last = seed;

        // True if we should move down on the next iteration
        boolean next = false;

        // True when we have looked at the first item in the data
        boolean hitFirst;

        // True when we have looked at the last item in the data
        boolean hitLast;

        // Get the item ID locally (instead of getItemIdAtPosition), so
        // we need the adapter
        final T adapter = getAdapter();
        if (adapter == null) {
            return INVALID_POSITION;
        }

        while (SystemClock.uptimeMillis() <= endTime) {
            rowId = adapter.getItemId(seed);
            if (rowId == idToMatch) {
                // Found it!
                return seed;
            }

            hitLast = last == count - 1;
            hitFirst = first == 0;

            if (hitLast && hitFirst) {
                // Looked at everything
                break;
            }

            if (hitFirst || next && !hitLast) {
                // Either we hit the top, or we are trying to move down
                last++;
                seed = last;
                // Try going up next time
                next = false;
            } else if (hitLast || !next && !hitFirst) {
                // Either we hit the bottom, or we are trying to move up
                first--;
                seed = first;
                // Try going down next time
                next = true;
            }

        }

        return INVALID_POSITION;
    }

    /**
     * Find a position that can be selected (i.e., is not a separator).
     * 
     * @param position
     *            The starting position to look at.
     * @param lookDown
     *            Whether to look down for other positions.
     * @return The next selectable position starting at position and then
     *         searching either up or down. Returns {@link #INVALID_POSITION} if
     *         nothing can be found.
     */
    int lookForSelectablePosition(final int position, final boolean lookDown) {
        return position;
    }

    /**
     * Utility to keep mSelectedPosition and mSelectedRowId in sync.
     * 
     * @param position
     *            Our current position
     */
    void setSelectedPositionInt(final int position) {
        setmSelectedPosition(position);
        setmSelectedRowId(getItemIdAtPosition(position));
    }

    /**
     * Utility to keep mNextSelectedPosition and mNextSelectedRowId in sync.
     * 
     * @param position
     *            Intended value for mSelectedPosition the next time we go
     *            through layout
     */
    void setNextSelectedPositionInt(final int position) {
        setmNextSelectedPosition(position);
        setmNextSelectedRowId(getItemIdAtPosition(position));
        // If we are trying to sync to the selection, update that too
        if (ismNeedSync() && getmSyncMode() == SYNC_SELECTED_POSITION
                && position >= 0) {
            setmSyncPosition(position);
            setmSyncRowId(getmNextSelectedRowId());
        }
    }

    /**
     * Remember enough information to restore the screen state when the data has
     * changed.
     * 
     */
    void rememberSyncState() {
        if (getChildCount() > 0) {
            setmNeedSync(true);
            setmSyncHeight(getmLayoutHeight());
            if (getmSelectedPosition() >= 0) {
                // Sync the selection state
                final View v = getChildAt(getmSelectedPosition()
                        - getmFirstPosition());
                setmSyncRowId(getmNextSelectedRowId());
                setmSyncPosition(getmNextSelectedPosition());
                if (v != null) {
                    setmSpecificTop(v.getTop());
                }
                setmSyncMode(SYNC_SELECTED_POSITION);
            } else {
                // Sync the based on the offset of the first view
                final View v = getChildAt(0);
                final T adapter = getAdapter();
                if (getmFirstPosition() >= 0
                        && getmFirstPosition() < adapter.getCount()) {
                    setmSyncRowId(adapter.getItemId(getmFirstPosition()));
                } else {
                    setmSyncRowId(NO_ID);
                }
                setmSyncPosition(getmFirstPosition());
                if (v != null) {
                    setmSpecificTop(v.getTop());
                }
                setmSyncMode(SYNC_FIRST_POSITION);
            }
        }
    }
}