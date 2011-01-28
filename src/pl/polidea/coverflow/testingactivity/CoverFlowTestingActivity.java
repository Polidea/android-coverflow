package pl.polidea.coverflow.testingactivity;

import pl.polidea.coverflow.CoverAdapterView;
import pl.polidea.coverflow.CoverFlow;
import pl.polidea.coverflow.R;
import pl.polidea.coverflow.ReflectingImageAdapter;
import pl.polidea.coverflow.ResourceImageAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

/**
 * The Class CoverFlowTestingActivity.
 */
public class CoverFlowTestingActivity extends Activity {

    /** The toast. */
    private Toast toast;

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        final WindowManager window = (WindowManager) this
                .getSystemService(WINDOW_SERVICE);
        final int width = window.getDefaultDisplay().getWidth() / 3;
        final int height = window.getDefaultDisplay().getHeight() / 3;
        // note resources below are taken using getIdentifier to allow importing
        // this library as library.
        final CoverFlow mCoverFlow = (CoverFlow) findViewById(this
                .getResources().getIdentifier("coverflow", "id",
                        "pl.polidea.coverflow"));
        setupCoverFlow(mCoverFlow, width, height, false);
        final CoverFlow reflectingCoverFlow = (CoverFlow) findViewById(this
                .getResources().getIdentifier("coverflowReflect", "id",
                        "pl.polidea.coverflow"));
        setupCoverFlow(reflectingCoverFlow, width, height, true);
    }

    /**
     * Setup cover flow.
     * 
     * @param mCoverFlow
     *            the m cover flow
     * @param width
     *            the width
     * @param height
     *            the height
     * @param reflect
     *            the reflect
     */
    private void setupCoverFlow(final CoverFlow mCoverFlow, final int width,
            final int height, final boolean reflect) {
        SpinnerAdapter coverImageAdapter = null;
        if (reflect) {
            coverImageAdapter = new ReflectingImageAdapter(this,
                    new ResourceImageAdapter(this));
        } else {
            coverImageAdapter = new ResourceImageAdapter(this);
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
        mCoverFlow
                .setOnItemClickListener(new CoverAdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(final CoverAdapterView< ? > parent,
                            final View view, final int position, final long id) {
                        final CharSequence text = "Item clicked! : " + id;
                        final int duration = Toast.LENGTH_SHORT;
                        clearToast();
                        toast = Toast.makeText(CoverFlowTestingActivity.this,
                                text, duration);
                        toast.show();
                    }

                });
        mCoverFlow
                .setOnItemSelectedListener(new CoverAdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            final CoverAdapterView< ? > parent,
                            final View view, final int position, final long id) {
                        final CharSequence text = "Item selected! : " + id;
                        final int duration = Toast.LENGTH_SHORT;
                        clearToast();
                        toast = Toast.makeText(CoverFlowTestingActivity.this,
                                text, duration);
                        toast.show();
                    }

                    @Override
                    public void onNothingSelected(
                            final CoverAdapterView< ? > parent) {
                        final CharSequence text = "Nothing clicked!";
                        final int duration = Toast.LENGTH_SHORT;
                        clearToast();
                        toast = Toast.makeText(CoverFlowTestingActivity.this,
                                text, duration);
                        toast.show();
                    }
                });
    }

    /**
     * Clear toast.
     */
    protected void clearToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

}
