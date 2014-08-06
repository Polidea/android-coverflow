package pl.polidea.coverflow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import pl.polidea.coverflow.testingactivity.Result;

/**
 * This class is an adapter that provides images from a fixed set of resource
 * ids. Bitmaps and ImageViews are kept as weak references so that they can be
 * cleared by garbage collection when not needed.
 * 
 */
public class NetworkImageAdapter extends AbstractCoverFlowImageAdapter {

    /** The Constant TAG. */
    private static final String TAG = NetworkImageAdapter.class.getSimpleName();

    /** The Constant DEFAULT_LIST_SIZE. */
    private static final int DEFAULT_LIST_SIZE = 20;

    /** The Constant IMAGE_RESOURCE_IDS. */
    private static final List<Integer> IMAGE_RESOURCE_IDS = new ArrayList<Integer>(DEFAULT_LIST_SIZE);

    /** The Constant DEFAULT_RESOURCE_LIST. */
    private static final int[] DEFAULT_RESOURCE_LIST = { R.drawable.image01, R.drawable.image02, R.drawable.image03,
            R.drawable.image04, R.drawable.image05 };

    /** The bitmap map. */
    private final Map<Integer, WeakReference<Bitmap>> bitmapMap = new HashMap<Integer, WeakReference<Bitmap>>();

    private final Context context;


    private LinkedList<Result> results;
    private static final List<Uri> IMAGE_RESOURCE_URIS = new ArrayList<Uri>();

    /**
     * Creates the adapter with default set of resource images.
     *
     * @param context
     *            context
     */
    public NetworkImageAdapter(final Context context) {
        super();
        this.context = context;
        setResources(DEFAULT_RESOURCE_LIST);
    }

    public NetworkImageAdapter(final Context context, LinkedList<Result> results)
    {
        super();
        this.context = context;
        this.results = results;
        setResources();
    }

    private final synchronized void setResources()
    {
        IMAGE_RESOURCE_URIS.clear();

        for (Result r : results)
        {
            IMAGE_RESOURCE_URIS.add(r.getAvatarUri());
        }

        notifyDataSetChanged();
    }

    /**
     * Replaces resources with those specified.
     * 
     * @param resourceIds
     *            array of ids of resources.
     */
    public final synchronized void setResources(final int[] resourceIds) {
        IMAGE_RESOURCE_IDS.clear();
        for (final int resourceId : resourceIds) {
            IMAGE_RESOURCE_IDS.add(resourceId);
        }
        notifyDataSetChanged();
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public synchronized int getCount()
    {
        // return this size if you use standard images
        //return IMAGE_RESOURCE_IDS.size();
        return IMAGE_RESOURCE_URIS.size();
    }

    /*
     * (non-Javadoc)
     * 
     * @see pl.polidea.coverflow.AbstractCoverFlowImageAdapter#createBitmap(int)
     */
    @Override
    protected Bitmap createBitmap(final int position) {
        Log.v(TAG, "creating item " + position);

        // use this code to use standard images
//        final Bitmap bitmap = ((BitmapDrawable) context.getResources().getDrawable(IMAGE_RESOURCE_IDS.get(position)))
//                .getBitmap();

        final Bitmap bitmap;
        try
        {
            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(results.get(position).getAvatarUri()));
        }
        catch (FileNotFoundException e)
        {
            Log.e("ResourceImageAdapter", "FileNotFoundException at createBitmap");
            return null;
        }

        bitmapMap.put(position, new WeakReference<Bitmap>(bitmap));
        return bitmap;
    }
}