package pl.polidea.coverflow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
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

    /** The bitmap map. */
    private final Map<Integer, WeakReference<Bitmap>> bitmapMap = new HashMap<Integer, WeakReference<Bitmap>>();

    private final Context context;

    private static final LinkedList<Result> RESULTS = new LinkedList<Result>();

    public NetworkImageAdapter(final Context context)
    {
        super();

        this.context = context;
        try {
            setResources(parseJSONResult(readStringFromInputStream(context.getAssets().open("faces.txt"))));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public final synchronized void setResources(List<Result> results)
    {
        RESULTS.clear();

        for (Result r : results)
        {
            RESULTS.add(r);
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
        return RESULTS.size();
    }

    /*
     * (non-Javadoc)
     * 
     * @see pl.polidea.coverflow.AbstractCoverFlowImageAdapter#createBitmap(int)
     */
    @Override
    protected Bitmap createBitmap(final int position) {
        return ((BitmapDrawable) context.getResources().getDrawable(R.drawable.image01)).getBitmap();
    }

    private String readStringFromInputStream(InputStream inputStream) throws IOException
    {
        StringBuilder builder = new StringBuilder();

        int c;

        while((c = inputStream.read()) != -1)
        {
            builder.append((char)c);
        }

        return builder.toString();
    }

    private class Wrapper {
        public LinkedList<Result> people = new LinkedList<Result>();
    }

    private LinkedList<Result> parseJSONResult(String json)
    {
        Gson gson = new Gson();

        Wrapper wrapper = gson.fromJson(json, Wrapper.class);

        return wrapper.people;
    }

    @Override
    public synchronized ImageView getView(int position, View convertView, ViewGroup parent)
    {
        ImageView view = super.getView(position, convertView, parent);

        Picasso.with(context).load(RESULTS.get(position).getAvatarURL()).into(view);

        return view;
    }
}