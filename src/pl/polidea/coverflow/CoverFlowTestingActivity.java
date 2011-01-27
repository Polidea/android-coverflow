package pl.polidea.coverflow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class CoverFlowTestingActivity extends Activity {

    private CoverFlow mCoverFlow;
    protected Toast toast;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        mCoverFlow = (CoverFlow) findViewById(R.id.coverflow);
        final WindowManager window = (WindowManager) this
                .getSystemService(WINDOW_SERVICE);
        final int width = window.getDefaultDisplay().getWidth() / 2;
        final int height = window.getDefaultDisplay().getHeight() / 2;

        final SpinnerAdapter coverImageAdapter = new ResourceImageAdapter(this,
                width, height);
        mCoverFlow.setAdapter(coverImageAdapter);
        mCoverFlow.setSpacing(-15);
        mCoverFlow.setSelection(2, true);
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

    protected void clearToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

}
