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
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.SpinnerAdapter;

// TODO: Auto-generated Javadoc
/**
 * An abstract base class for spinner widgets. SDK users will probably not need
 * to use this class.
 * 
 */
public abstract class CoverAbsSpinner extends CoverAdapterView<SpinnerAdapter> {

    /** The m adapter. */
    private SpinnerAdapter mAdapter;

    /** The m height measure spec. */
    private int mHeightMeasureSpec;

    /** The m width measure spec. */
    private int mWidthMeasureSpec;

    /** The m block layout requests. */
    private boolean mBlockLayoutRequests;

    /**
     * Checks if is m block layout requests.
     * 
     * @return true, if is m block layout requests
     */
    @Override
    public boolean ismBlockLayoutRequests() {
        return mBlockLayoutRequests;
    }

    /**
     * Sets the m block layout requests.
     * 
     * @param mBlockLayoutRequests
     *            the new m block layout requests
     */
    @Override
    public void setmBlockLayoutRequests(final boolean mBlockLayoutRequests) {
        this.mBlockLayoutRequests = mBlockLayoutRequests;
    }

    /**
     * Gets the m selection left padding.
     * 
     * @return the m selection left padding
     */
    public int getmSelectionLeftPadding() {
        return mSelectionLeftPadding;
    }

    /**
     * Sets the m selection left padding.
     * 
     * @param mSelectionLeftPadding
     *            the new m selection left padding
     */
    public void setmSelectionLeftPadding(final int mSelectionLeftPadding) {
        this.mSelectionLeftPadding = mSelectionLeftPadding;
    }

    /**
     * Gets the m selection top padding.
     * 
     * @return the m selection top padding
     */
    public int getmSelectionTopPadding() {
        return mSelectionTopPadding;
    }

    /**
     * Sets the m selection top padding.
     * 
     * @param mSelectionTopPadding
     *            the new m selection top padding
     */
    public void setmSelectionTopPadding(final int mSelectionTopPadding) {
        this.mSelectionTopPadding = mSelectionTopPadding;
    }

    /**
     * Gets the m selection right padding.
     * 
     * @return the m selection right padding
     */
    public int getmSelectionRightPadding() {
        return mSelectionRightPadding;
    }

    /**
     * Sets the m selection right padding.
     * 
     * @param mSelectionRightPadding
     *            the new m selection right padding
     */
    public void setmSelectionRightPadding(final int mSelectionRightPadding) {
        this.mSelectionRightPadding = mSelectionRightPadding;
    }

    /**
     * Gets the m selection bottom padding.
     * 
     * @return the m selection bottom padding
     */
    public int getmSelectionBottomPadding() {
        return mSelectionBottomPadding;
    }

    /**
     * Sets the m selection bottom padding.
     * 
     * @param mSelectionBottomPadding
     *            the new m selection bottom padding
     */
    public void setmSelectionBottomPadding(final int mSelectionBottomPadding) {
        this.mSelectionBottomPadding = mSelectionBottomPadding;
    }

    /**
     * Gets the m spinner padding.
     * 
     * @return the m spinner padding
     */
    public Rect getmSpinnerPadding() {
        return mSpinnerPadding;
    }

    /**
     * Sets the m spinner padding.
     * 
     * @param mSpinnerPadding
     *            the new m spinner padding
     */
    public void setmSpinnerPadding(final Rect mSpinnerPadding) {
        this.mSpinnerPadding = mSpinnerPadding;
    }

    /**
     * Gets the m selected view.
     * 
     * @return the m selected view
     */
    public View getmSelectedView() {
        return mSelectedView;
    }

    /**
     * Sets the m selected view.
     * 
     * @param mSelectedView
     *            the new m selected view
     */
    public void setmSelectedView(final View mSelectedView) {
        this.mSelectedView = mSelectedView;
    }

    /**
     * Gets the m interpolator.
     * 
     * @return the m interpolator
     */
    public Interpolator getmInterpolator() {
        return mInterpolator;
    }

    /**
     * Sets the m interpolator.
     * 
     * @param mInterpolator
     *            the new m interpolator
     */
    public void setmInterpolator(final Interpolator mInterpolator) {
        this.mInterpolator = mInterpolator;
    }

    /**
     * Gets the m recycler.
     * 
     * @return the m recycler
     */
    public RecycleBin getmRecycler() {
        return mRecycler;
    }

    /**
     * Sets the m recycler.
     * 
     * @param mRecycler
     *            the new m recycler
     */
    public void setmRecycler(final RecycleBin mRecycler) {
        this.mRecycler = mRecycler;
    }

    /**
     * Gets the m data set observer.
     * 
     * @return the m data set observer
     */
    public DataSetObserver getmDataSetObserver() {
        return mDataSetObserver;
    }

    /**
     * Sets the m data set observer.
     * 
     * @param mDataSetObserver
     *            the new m data set observer
     */
    public void setmDataSetObserver(final DataSetObserver mDataSetObserver) {
        this.mDataSetObserver = mDataSetObserver;
    }

    /**
     * Gets the m touch frame.
     * 
     * @return the m touch frame
     */
    public Rect getmTouchFrame() {
        return mTouchFrame;
    }

    /**
     * Sets the m touch frame.
     * 
     * @param mTouchFrame
     *            the new m touch frame
     */
    public void setmTouchFrame(final Rect mTouchFrame) {
        this.mTouchFrame = mTouchFrame;
    }

    /** The m selection left padding. */
    private int mSelectionLeftPadding = 0;

    /** The m selection top padding. */
    private int mSelectionTopPadding = 0;

    /** The m selection right padding. */
    private int mSelectionRightPadding = 0;

    /** The m selection bottom padding. */
    private int mSelectionBottomPadding = 0;

    /** The m spinner padding. */
    private Rect mSpinnerPadding = new Rect();

    /** The m selected view. */
    private View mSelectedView = null;

    /** The m interpolator. */
    private Interpolator mInterpolator;

    /** The m recycler. */
    private RecycleBin mRecycler = new RecycleBin();

    /** The m data set observer. */
    private DataSetObserver mDataSetObserver;

    /** Temporary frame to hold a child View's frame rectangle. */
    private Rect mTouchFrame;

    /**
     * Instantiates a new cover abs spinner.
     * 
     * @param context
     *            the context
     */
    public CoverAbsSpinner(final Context context) {
        super(context);
        initAbsSpinner();
    }

    /**
     * Instantiates a new cover abs spinner.
     * 
     * @param context
     *            the context
     * @param attrs
     *            the attrs
     */
    public CoverAbsSpinner(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Instantiates a new cover abs spinner.
     * 
     * @param context
     *            the context
     * @param attrs
     *            the attrs
     * @param defStyle
     *            the def style
     */
    public CoverAbsSpinner(final Context context, final AttributeSet attrs,
            final int defStyle) {
        super(context, attrs, defStyle);
        initAbsSpinner();
    }

    /**
     * Common code for different constructor flavors.
     */
    private void initAbsSpinner() {
        setFocusable(true);
        setWillNotDraw(false);
    }

    /**
     * The Adapter is used to provide the data which backs this Spinner. It also
     * provides methods to transform spinner items based on their position
     * relative to the selected item.
     * 
     * @param adapter
     *            The SpinnerAdapter to use for this Spinner
     */
    @Override
    public void setAdapter(final SpinnerAdapter adapter) {
        if (null != getmAdapter()) {
            getmAdapter().unregisterDataSetObserver(mDataSetObserver);
            resetList();
        }

        setmAdapter(adapter);

        setmOldSelectedPosition(INVALID_POSITION);
        setmOldSelectedRowId(INVALID_ROW_ID);

        if (getmAdapter() != null) {
            setmOldItemCount(getmItemCount());
            setmItemCount(getmAdapter().getCount());
            checkFocus();

            mDataSetObserver = new AdapterDataSetObserver();
            getmAdapter().registerDataSetObserver(mDataSetObserver);

            final int position = getmItemCount() > 0 ? 0 : INVALID_POSITION;

            setSelectedPositionInt(position);
            setNextSelectedPositionInt(position);

            if (getmItemCount() == 0) {
                // Nothing selected
                checkSelectionChanged();
            }

        } else {
            checkFocus();
            resetList();
            // Nothing selected
            checkSelectionChanged();
        }

        requestLayout();
    }

    /**
     * Clear out all children from the list.
     */
    void resetList() {
        setmDataChanged(false);
        setmNeedSync(false);

        removeAllViewsInLayout();
        setmOldSelectedPosition(INVALID_POSITION);
        setmOldSelectedRowId(INVALID_ROW_ID);

        setSelectedPositionInt(INVALID_POSITION);
        setNextSelectedPositionInt(INVALID_POSITION);
        invalidate();
    }

    /**
     * On measure.
     * 
     * @param widthMeasureSpec
     *            the width measure spec
     * @param heightMeasureSpec
     *            the height measure spec
     * @see android.view.View#measure(int, int)
     * 
     *      Figure out the dimensions of this Spinner. The width comes from the
     *      widthMeasureSpec as Spinnners can't have their width set to
     *      UNSPECIFIED. The height is based on the height of the selected item
     *      plus padding.
     */
    @Override
    protected void onMeasure(final int widthMeasureSpec,
            final int heightMeasureSpec) {
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize;
        int heightSize;

        mSpinnerPadding.left = getPaddingLeft() > mSelectionLeftPadding ? getPaddingLeft()
                : mSelectionLeftPadding;
        mSpinnerPadding.top = getPaddingTop() > mSelectionTopPadding ? getPaddingTop()
                : mSelectionTopPadding;
        mSpinnerPadding.right = getPaddingRight() > mSelectionRightPadding ? getPaddingRight()
                : mSelectionRightPadding;
        mSpinnerPadding.bottom = getPaddingBottom() > mSelectionBottomPadding ? getPaddingBottom()
                : mSelectionBottomPadding;

        if (ismDataChanged()) {
            handleDataChanged();
        }

        int preferredHeight = 0;
        int preferredWidth = 0;
        boolean needsMeasuring = true;

        final int selectedPosition = getSelectedItemPosition();
        if (selectedPosition >= 0 && getmAdapter() != null) {
            // Try looking in the recycler. (Maybe we were measured once
            // already)
            View view = mRecycler.get(selectedPosition);
            if (view == null) {
                // Make a new one
                view = getmAdapter().getView(selectedPosition, null, this);
            }

            if (view != null) {
                // Put in recycler for re-measuring and/or layout
                mRecycler.put(selectedPosition, view);
            }

            if (view != null) {
                if (view.getLayoutParams() == null) {
                    setmBlockLayoutRequests(true);
                    view.setLayoutParams(generateDefaultLayoutParams());
                    setmBlockLayoutRequests(false);
                }
                measureChild(view, widthMeasureSpec, heightMeasureSpec);

                preferredHeight = getChildHeight(view) + mSpinnerPadding.top
                        + mSpinnerPadding.bottom;
                preferredWidth = getChildWidth(view) + mSpinnerPadding.left
                        + mSpinnerPadding.right;

                needsMeasuring = false;
            }
        }

        if (needsMeasuring) {
            // No views -- just use padding
            preferredHeight = mSpinnerPadding.top + mSpinnerPadding.bottom;
            if (widthMode == MeasureSpec.UNSPECIFIED) {
                preferredWidth = mSpinnerPadding.left + mSpinnerPadding.right;
            }
        }

        preferredHeight = Math
                .max(preferredHeight, getSuggestedMinimumHeight());
        preferredWidth = Math.max(preferredWidth, getSuggestedMinimumWidth());

        heightSize = resolveSize(preferredHeight, heightMeasureSpec);
        widthSize = resolveSize(preferredWidth, widthMeasureSpec);

        setMeasuredDimension(widthSize, heightSize);
        setmHeightMeasureSpec(heightMeasureSpec);
        setmWidthMeasureSpec(widthMeasureSpec);
    }

    /**
     * Gets the child height.
     * 
     * @param child
     *            the child
     * @return the child height
     */
    int getChildHeight(final View child) {
        return child.getMeasuredHeight();
    }

    /**
     * Gets the child width.
     * 
     * @param child
     *            the child
     * @return the child width
     */
    int getChildWidth(final View child) {
        return child.getMeasuredWidth();
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.ViewGroup#generateDefaultLayoutParams()
     */
    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * Recycle all views.
     */
    void recycleAllViews() {
        final int childCount = getChildCount();
        final CoverAbsSpinner.RecycleBin recycleBin = mRecycler;

        // All views go in recycler
        for (int i = 0; i < childCount; i++) {
            final View v = getChildAt(i);
            final int index = getmFirstPosition() + i;
            recycleBin.put(index, v);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see pl.polidea.coverflow.CoverAdapterView#handleDataChanged()
     */
    @Override
    void handleDataChanged() {
        // FIXME -- this is called from both measure and layout.
        // This is harmless right now, but we don't want to do redundant work if
        // this gets more complicated
        super.handleDataChanged();
    }

    /**
     * Jump directly to a specific item in the adapter data.
     * 
     * @param position
     *            the position
     * @param animate
     *            the animate
     */
    public void setSelection(final int position, final boolean animate) {
        // Animate only if requested position is already on screen somewhere
        final boolean shouldAnimate = animate
                && getmFirstPosition() <= position
                && position <= getmFirstPosition() + getChildCount() - 1;
        setSelectionInt(position, shouldAnimate);
    }

    /*
     * (non-Javadoc)
     * 
     * @see pl.polidea.coverflow.CoverAdapterView#setSelection(int)
     */
    @Override
    public void setSelection(final int position) {
        setNextSelectedPositionInt(position);
        requestLayout();
        invalidate();
    }

    /**
     * Makes the item at the supplied position selected.
     * 
     * @param position
     *            Position to select
     * @param animate
     *            Should the transition be animated
     * 
     */
    void setSelectionInt(final int position, final boolean animate) {
        if (position != getmOldSelectedPosition()) {
            setmBlockLayoutRequests(true);
            final int delta = position - getmSelectedPosition();
            setNextSelectedPositionInt(position);
            layout(delta, animate);
            setmBlockLayoutRequests(false);
        }
    }

    /**
     * Layout.
     * 
     * @param delta
     *            the delta
     * @param animate
     *            the animate
     */
    abstract void layout(int delta, boolean animate);

    /*
     * (non-Javadoc)
     * 
     * @see pl.polidea.coverflow.CoverAdapterView#getSelectedView()
     */
    @Override
    public View getSelectedView() {
        if (getmItemCount() > 0 && getmSelectedPosition() >= 0) {
            return getChildAt(getmSelectedPosition() - getmFirstPosition());
        } else {
            return null;
        }
    }

    /**
     * Override to prevent spamming ourselves with layout requests as we place
     * views.
     * 
     * @see android.view.View#requestLayout()
     */
    @Override
    public void requestLayout() {
        if (!ismBlockLayoutRequests()) {
            super.requestLayout();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see pl.polidea.coverflow.CoverAdapterView#getAdapter()
     */
    @Override
    public SpinnerAdapter getAdapter() {
        return getmAdapter();
    }

    /*
     * (non-Javadoc)
     * 
     * @see pl.polidea.coverflow.CoverAdapterView#getCount()
     */
    @Override
    public int getCount() {
        return getmItemCount();
    }

    /**
     * Maps a point to a position in the list.
     * 
     * @param x
     *            X in local coordinate
     * @param y
     *            Y in local coordinate
     * @return The position of the item which contains the specified point, or
     *         {@link #INVALID_POSITION} if the point does not intersect an
     *         item.
     */
    public int pointToPosition(final int x, final int y) {
        Rect frame = mTouchFrame;
        if (frame == null) {
            mTouchFrame = new Rect();
            frame = mTouchFrame;
        }

        final int count = getChildCount();
        for (int i = count - 1; i >= 0; i--) {
            final View child = getChildAt(i);
            if (child.getVisibility() == View.VISIBLE) {
                child.getHitRect(frame);
                if (frame.contains(x, y)) {
                    return getmFirstPosition() + i;
                }
            }
        }
        return INVALID_POSITION;
    }

    /**
     * The Class SavedState.
     */
    static class SavedState extends BaseSavedState {

        /** The selected id. */
        private long selectedId;

        /** The position. */
        private int position;

        /**
         * Constructor called from AbsSpinner#onSaveInstanceState().
         * 
         * @param superState
         *            the super state
         */
        SavedState(final Parcelable superState) {
            super(superState);
        }

        /**
         * Constructor called from {@link #CREATOR}.
         * 
         * @param in
         *            the in
         */
        private SavedState(final Parcel in) {
            super(in);
            selectedId = in.readLong();
            position = in.readInt();
        }

        @Override
        public void writeToParcel(final Parcel out, final int flags) {
            super.writeToParcel(out, flags);
            out.writeLong(selectedId);
            out.writeInt(position);
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "AbsSpinner.SavedState{"
                    + Integer.toHexString(System.identityHashCode(this))
                    + " selectedId=" + selectedId + " position=" + position
                    + "}";
        }

        /** The Constant CREATOR. */
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(final Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(final int size) {
                return new SavedState[size];
            }
        };
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.View#onSaveInstanceState()
     */
    @Override
    public Parcelable onSaveInstanceState() {
        final Parcelable superState = super.onSaveInstanceState();
        final SavedState ss = new SavedState(superState);
        ss.selectedId = getSelectedItemId();
        if (ss.selectedId >= 0) {
            ss.position = getSelectedItemPosition();
        } else {
            ss.position = INVALID_POSITION;
        }
        return ss;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.View#onRestoreInstanceState(android.os.Parcelable)
     */
    @Override
    public void onRestoreInstanceState(final Parcelable state) {
        final SavedState ss = (SavedState) state;

        super.onRestoreInstanceState(ss.getSuperState());

        if (ss.selectedId >= 0) {
            setmDataChanged(true);
            setmNeedSync(true);
            setmSyncRowId(ss.selectedId);
            setmSyncPosition(ss.position);
            setmSyncMode(SYNC_SELECTED_POSITION);
            requestLayout();
        }
    }

    /**
     * Sets the m adapter.
     * 
     * @param mAdapter
     *            the new m adapter
     */
    public void setmAdapter(final SpinnerAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    /**
     * Gets the m adapter.
     * 
     * @return the m adapter
     */
    public SpinnerAdapter getmAdapter() {
        return mAdapter;
    }

    /**
     * Sets the m height measure spec.
     * 
     * @param mHeightMeasureSpec
     *            the new m height measure spec
     */
    public void setmHeightMeasureSpec(final int mHeightMeasureSpec) {
        this.mHeightMeasureSpec = mHeightMeasureSpec;
    }

    /**
     * Gets the m height measure spec.
     * 
     * @return the m height measure spec
     */
    public int getmHeightMeasureSpec() {
        return mHeightMeasureSpec;
    }

    /**
     * Sets the m width measure spec.
     * 
     * @param mWidthMeasureSpec
     *            the new m width measure spec
     */
    public void setmWidthMeasureSpec(final int mWidthMeasureSpec) {
        this.mWidthMeasureSpec = mWidthMeasureSpec;
    }

    /**
     * Gets the m width measure spec.
     * 
     * @return the m width measure spec
     */
    public int getmWidthMeasureSpec() {
        return mWidthMeasureSpec;
    }

    /**
     * The Class RecycleBin.
     */
    class RecycleBin {

        /** The m scrap heap. */
        private final SparseArray<View> mScrapHeap = new SparseArray<View>();

        /**
         * Put.
         * 
         * @param position
         *            the position
         * @param v
         *            the v
         */
        public void put(final int position, final View v) {
            mScrapHeap.put(position, v);
        }

        /**
         * Gets the.
         * 
         * @param position
         *            the position
         * @return the view
         */
        View get(final int position) {
            // System.out.print("Looking for " + position);
            final View result = mScrapHeap.get(position);
            if (result != null) {
                // System.out.println(" HIT");
                mScrapHeap.delete(position);
            } else {
                // System.out.println(" MISS");
            }
            return result;
        }

        /**
         * Peek.
         * 
         * @param position
         *            the position
         * @return the view
         */
        View peek(final int position) {
            // System.out.print("Looking for " + position);
            return mScrapHeap.get(position);
        }

        /**
         * Clear.
         */
        void clear() {
            final SparseArray<View> scrapHeap = mScrapHeap;
            final int count = scrapHeap.size();
            for (int i = 0; i < count; i++) {
                final View view = scrapHeap.valueAt(i);
                if (view != null) {
                    removeDetachedView(view, true);
                }
            }
            scrapHeap.clear();
        }
    }
}