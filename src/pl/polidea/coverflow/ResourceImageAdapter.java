package pl.polidea.coverflow;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * This class is an adapter that provides images from a fixed set of resource
 * ids. Bitmaps and ImageViews are kept as weak references so that they can be
 * cleared by garbage collection when not needed.
 * 
 */
class ResourceImageAdapter extends BaseAdapter {
    private static final String TAG = ResourceImageAdapter.class
            .getSimpleName();

    private static final int DEFAULT_LIST_SIZE = 20;
    private static final List<Integer> IMAGE_RESOURCE_IDS = new ArrayList<Integer>(
            DEFAULT_LIST_SIZE);
    private static final int[] DEFAULT_RESOURCE_LIST = { R.drawable.image01,
            R.drawable.image02, R.drawable.image03, R.drawable.image04,
            R.drawable.image05 };
    private final Context context;

    private final int width;
    private final int height;

    private final HashMap<Integer, WeakReference<Bitmap>> bitmapMap = new HashMap<Integer, WeakReference<Bitmap>>();
    private final HashMap<Integer, WeakReference<ImageView>> imageViewMap = new HashMap<Integer, WeakReference<ImageView>>();

    public ResourceImageAdapter(final Context context, final int width,
            final int height) {
        this.context = context;
        setResources(DEFAULT_RESOURCE_LIST);
        this.width = width;
        this.height = height;
    }

    public synchronized final void setResources(final int resourceIds[]) {
        IMAGE_RESOURCE_IDS.clear();
        for (final int resourceId : resourceIds) {
            IMAGE_RESOURCE_IDS.add(resourceId);
        }
        notifyDataSetChanged();
    }

    @Override
    public synchronized int getCount() {
        return IMAGE_RESOURCE_IDS.size();
    }

    @Override
    public synchronized Bitmap getItem(final int position) {
        final WeakReference<Bitmap> weakBitmapReference = bitmapMap
                .get(position);
        if (weakBitmapReference != null) {
            final Bitmap bitmap = weakBitmapReference.get();
            if (bitmap != null) {
                return bitmap;
            }
        }
        Log.v(TAG, "retrieving item " + position);
        final Bitmap bitmap = ((BitmapDrawable) context.getResources()
                .getDrawable(IMAGE_RESOURCE_IDS.get(position))).getBitmap();
        bitmapMap.put(position, new WeakReference<Bitmap>(bitmap));
        return bitmap;
    }

    @Override
    public synchronized long getItemId(final int position) {
        return position;
    }

    @Override
    public synchronized ImageView getView(final int position,
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
        imageView.setLayoutParams(new CoverFlow.LayoutParams(width, height));
        imageViewMap.put(position, new WeakReference<ImageView>(imageView));
        return imageView;
    }
}