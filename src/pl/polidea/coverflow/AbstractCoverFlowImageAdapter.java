package pl.polidea.coverflow;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * This class is an adapter that provides base, abstract class for images
 * adapter.
 * 
 */
public abstract class AbstractCoverFlowImageAdapter extends BaseAdapter {

    /** The Constant TAG. */
    private static final String TAG = AbstractCoverFlowImageAdapter.class
            .getSimpleName();

    /** The context. */
    private final Context context;

    /** The width. */
    private float width = 0;

    /** The height. */
    private float height = 0;

    /** The bitmap map. */
    private final Map<Integer, WeakReference<Bitmap>> bitmapMap = new HashMap<Integer, WeakReference<Bitmap>>();

    /** The image view map. */
    private final Map<Integer, WeakReference<ImageView>> imageViewMap = new HashMap<Integer, WeakReference<ImageView>>();

    /**
     * Craetes image adapter.
     * 
     * @param context
     *            context of the view
     */
    public AbstractCoverFlowImageAdapter(final Context context) {
        super();
        this.context = context;
    }

    /**
     * Set width for all pictures.
     * 
     * @param width
     *            picture height
     */
    public synchronized void setWidth(final float width) {
        this.width = width;
    }

    /**
     * Set height for all pictures.
     * 
     * @param height
     *            picture height
     */
    public synchronized void setHeight(final float height) {
        this.height = height;
    }

    @Override
    public final synchronized Bitmap getItem(final int position) {
        final WeakReference<Bitmap> weakBitmapReference = bitmapMap
                .get(position);
        if (weakBitmapReference != null) {
            final Bitmap bitmap = weakBitmapReference.get();
            if (bitmap != null) {
                return bitmap;
            }
        }
        Log.v(TAG, "retrieving item " + position);
        final Bitmap bitmap = createBitmap(position);
        bitmapMap.put(position, new WeakReference<Bitmap>(bitmap));
        return bitmap;
    }

    /**
     * Creates new bitmap for the position specified.
     * 
     * @param position
     *            position
     * @return Bitmap created
     */
    protected abstract Bitmap createBitmap(int position);

    /*
     * (non-Javadoc)
     * 
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public final synchronized long getItemId(final int position) {
        return position;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.widget.Adapter#getView(int, android.view.View,
     * android.view.ViewGroup)
     */
    @Override
    public final synchronized ImageView getView(final int position,
            final View convertView, final ViewGroup parent) {
        final WeakReference<ImageView> weakImageViewReference = imageViewMap
                .get(position);
        if (weakImageViewReference != null) {
            final ImageView imageView = weakImageViewReference.get();
            if (imageView != null) {
                return imageView;
            }
        }
        Log.v(TAG, "getting item view " + position);
        final ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(getItem(position));
        imageView.setLayoutParams(new CoverFlow.LayoutParams((int) width,
                (int) height));
        imageViewMap.put(position, new WeakReference<ImageView>(imageView));
        return imageView;
    }

    /**
     * Retrieves context.
     * 
     * @return context
     */
    public Context getContext() {
        return context;
    }
}
