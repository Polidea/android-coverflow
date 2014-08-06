package pl.polidea.coverflow.testingactivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import pl.polidea.coverflow.CoverFlow;
import pl.polidea.coverflow.NetworkImageAdapter;
import pl.polidea.coverflow.R;
import pl.polidea.coverflow.ReflectingImageAdapter;
import pl.polidea.coverflow.ResourceImageAdapter;

/****
 * The Class CoverFlowTestingActivity.
 */
public class CoverFlowTestingActivity extends Activity {

    private TextView textView;

    private LinkedList<Result> results;
    private ProgressDialog progressDialog;
    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        textView = (TextView) findViewById(this.getResources()
                .getIdentifier("statusText", "id", "pl.polidea.coverflow"));

        if (!NetworkUtilities.isOnline(this))
        {
            Toast.makeText(this, "You must go online to use application", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog = new ProgressDialog(this)
        {
            @Override
            public void onBackPressed()
            {
                super.onBackPressed();
                CoverFlowTestingActivity.this.finish();
            }
        };
        progressDialog.setMessage("This might take a few seconds...");

        try
        {
            results = parseJSONResult(readStringFromInputStream(getAssets().open("faces.txt")));

            EmployeeAvatarTask avatarTask = new EmployeeAvatarTask(this, results);
            avatarTask.execute();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

//        // note resources below are taken using getIdentifier to allow importing
//        // this library as library.
//        final CoverFlow coverFlow1 = (CoverFlow) findViewById(this.getResources().getIdentifier("coverflow", "id",
//                "pl.polidea.coverflow"));
//        setupCoverFlow(coverFlow1, false, results);
//        final CoverFlow reflectingCoverFlow = (CoverFlow) findViewById(this.getResources().getIdentifier(
//                "coverflowReflect", "id", "pl.polidea.coverflow"));
//        setupCoverFlow(reflectingCoverFlow, true, results);
    }

    /**
     * Setup cover flow.
     * 
     * @param mCoverFlow
     *            the m cover flow
     * @param reflect
     *            the reflect
     */
    private void setupCoverFlow(final CoverFlow mCoverFlow, final boolean reflect) {
        BaseAdapter coverImageAdapter;
        if (reflect) {
            coverImageAdapter = new ReflectingImageAdapter(new ResourceImageAdapter(this));
        } else {
            coverImageAdapter = new ResourceImageAdapter(this);
        }
        mCoverFlow.setAdapter(coverImageAdapter);
        mCoverFlow.setSelection(2, true);
        setupListeners(mCoverFlow);
    }

    // this setup is used for Polidea avatars
    public void setupCoverFlow(final CoverFlow mCoverFlow, final boolean reflect, LinkedList<Result> results) {
        BaseAdapter coverImageAdapter;
        if (reflect) {
            coverImageAdapter = new ReflectingImageAdapter(new NetworkImageAdapter(this, results));
        } else {
            coverImageAdapter = new NetworkImageAdapter(this, results);
        }

        mCoverFlow.setAdapter(coverImageAdapter);
        mCoverFlow.setSelection(2, true);
        setupListeners(mCoverFlow);
    }

    /**
     * Sets the up listeners.
     * 
     * @param mCoverFlow
     *            the new up listeners
     */
    private void setupListeners(final CoverFlow mCoverFlow) {
        mCoverFlow.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView< ? > parent, final View view, final int position, final long id) {
                textView.setText("Item clicked! : " + id);
            }

        });
        mCoverFlow.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView< ? > parent, final View view, final int position, final long id) {
                textView.setText("Item selected! : " + id);
            }

            @Override
            public void onNothingSelected(final AdapterView< ? > parent) {
                textView.setText("Nothing clicked!");
            }
        });
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

    public LinkedList<Result> getResults()
    {
        return results;
    }

    public void setResults(LinkedList<Result> results)
    {
        this.results = results;
    }

    public ProgressDialog getProgressDialog()
    {
        return progressDialog;
    }
}
