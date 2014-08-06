package pl.polidea.coverflow.testingactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import pl.polidea.coverflow.AbstractCoverFlowImageAdapter;
import pl.polidea.coverflow.CoverFlow;
import pl.polidea.coverflow.NetworkImageAdapter;
import pl.polidea.coverflow.R;
import pl.polidea.coverflow.ReflectingImageAdapter;

/****
 * The Class CoverFlowTestingActivity.
 */
public class CoverFlowTestingActivity extends Activity {

    private TextView textView;
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
        final CoverFlow coverFlow1 = (CoverFlow) findViewById(this.getResources().getIdentifier("coverflow", "id",
                "pl.polidea.coverflow"));
        setupCoverFlow(coverFlow1, false);
        final CoverFlow reflectingCoverFlow = (CoverFlow) findViewById(this.getResources().getIdentifier(
                "coverflowReflect", "id", "pl.polidea.coverflow"));
        setupCoverFlow(reflectingCoverFlow, true);
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

        AbstractCoverFlowImageAdapter linkedAdapter;

        // select here which adapter to use
        linkedAdapter = new ResourceImageAdapter(this);
//        linkedAdapter = new NetworkImageAdapter(this);

        if (reflect) {
            coverImageAdapter = new ReflectingImageAdapter(linkedAdapter);
        } else {
            coverImageAdapter = linkedAdapter;
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
}
