package pl.polidea.coverflow.testingactivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pl.polidea.coverflow.AbstractCoverFlowImageAdapter;
import pl.polidea.coverflow.CoverFlow;
import pl.polidea.coverflow.NetworkImageAdapter;
import pl.polidea.coverflow.R;
import pl.polidea.coverflow.ReflectingImageAdapter;
import pl.polidea.coverflow.ResourceImageAdapter;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/****
 * The Class CoverFlowTestingActivity.
 */
public class CoverFlowTestingActivity extends Activity {

    private static final String TAG = CoverFlowTestingActivity.class.getSimpleName();
    private TextView textView;
    private AbstractCoverFlowImageAdapter linkedAdapter;
    Button button;
    private CoverFlow coverFlow;

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

        // note resources below are taken using getIdentifier to allow importing
        // this library as library.
        coverFlow = (CoverFlow) findViewById(this.getResources().getIdentifier("coverflow", "id",
                "pl.polidea.coverflow"));
        setupCoverFlow(coverFlow, true);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fetchFlags();
            }
        });

    }

    private void fetchFlags()
    {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://pastebin.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        FlagsInterface service = restAdapter.create(FlagsInterface.class);
        service.fetch("36bxhPpE", new Callback<FlagsInterface.Wrapper>()
        {
            @Override
            public void success(FlagsInterface.Wrapper wrapper, Response response)
            {
                if (linkedAdapter instanceof NetworkImageAdapter) {
                    ((NetworkImageAdapter) linkedAdapter).setCountries(wrapper.countries);
                    coverFlow.setAdapter(linkedAdapter);
                }
            }

            @Override
            public void failure(RetrofitError error)
            {
                Toast.makeText(getBaseContext(), "Error: " + error.getResponse().getReason(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Setup cover flow.
     * 
     * @param mCoverFlow
     *            the m cover flow
     * @param showFaces
     *            adapter uses people's faces instead of Chuck Norris
     */
    private void setupCoverFlow(final CoverFlow mCoverFlow, final boolean showFaces) {
        BaseAdapter coverImageAdapter;

        // select here which adapter to use

        if (showFaces)
            linkedAdapter = new NetworkImageAdapter(this);
        else
            linkedAdapter = new ResourceImageAdapter(this);

        coverImageAdapter = linkedAdapter;
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
}
