package pl.polidea.coverflow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import pl.polidea.coverflow.testingactivity.EmployeeAvatarTask;
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

    private static final List<Uri> IMAGE_RESOURCE_URIS = new ArrayList<Uri>();

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
        IMAGE_RESOURCE_URIS.clear();

        for (Result r : results)
        {
            IMAGE_RESOURCE_URIS.add(r.getAvatarUri());
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
        return IMAGE_RESOURCE_URIS.size();
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

    private LinkedList<Result> parseJSONResult(String result)
    {
        LinkedList<Result> results = new LinkedList<Result>();

        try
        {
            JSONObject outerItem = new JSONObject(result);
            JSONArray jArray = outerItem.getJSONArray("people");

            for (int i = 0; i < jArray.length(); i++)
            {
                JSONObject employee = jArray.getJSONObject(i);
                String id = employee.getString("id");

                URL url = new URL(employee.getString("gravatar"));

                Result employeeResult = new Result(url, id, null);
                results.add(employeeResult);
            }
        }
        catch (MalformedURLException e)
        {
            Log.e("CoverFlowTestingActivity", "MalformedUrlException in parseJSONResult");
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            Log.e("CoverFlowTestingActivity", "JSONException in parseJSONResult");
            e.printStackTrace();
        }

        return results;
    }
}