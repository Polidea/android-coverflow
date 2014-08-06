package pl.polidea.coverflow.testingactivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

import pl.polidea.coverflow.CoverFlow;

/**
 * Created by martawoldanska on 8/4/14.
 */
public class EmployeeAvatarTask extends AsyncTask<Void, Void, Void>
{

    LinkedList<Result> results;
    private CoverFlowTestingActivity activity;

    public EmployeeAvatarTask(final CoverFlowTestingActivity activity, LinkedList<Result> results)
    {
        this.activity = activity;
        this.results = results;
    }

    @Override
    protected void onPreExecute()
    {
        activity.getProgressDialog().show();
    }

    @Override
    protected Void doInBackground(Void... params)
    {
        String root = Environment.getExternalStorageDirectory()
                .getAbsolutePath();
        File filePath = new File(root + "/CoverFlow");

        for (int i = 0; i < results.size(); i++)
        {
            try
            {
                String fileID = convertUrlToFileID(results.get(i)
                        .getAvatarURL().toString());
                File file = new File(filePath, fileID);

                if (!file.exists())
                {
                    Bitmap img = BitmapFactory.decodeStream(results.get(i)
                            .getAvatarURL().openConnection().getInputStream());
                    file = saveBitmap(img, convertUrlToFileID(results.get(i)
                            .getAvatarURL().toString()));
                }

                results.get(i).setAvatarUri(Uri.fromFile(file));
            }
            catch (IOException e)
            {
                Log.e("EmployeeAvatarTask", "IOException in doInBackground");
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        activity.setResults(results);

        if (activity.getProgressDialog() != null)
            activity.getProgressDialog().dismiss();

        // note resources below are taken using getIdentifier to allow importing
        // this library as library.

        // not the prettiest way to do this, but this way the existence of employees' list is ensured
        final CoverFlow coverFlow1 = (CoverFlow) activity.findViewById(activity.getResources().getIdentifier("coverflow", "id",
                "pl.polidea.coverflow"));
        activity.setupCoverFlow(coverFlow1, false, results);
        final CoverFlow reflectingCoverFlow = (CoverFlow) activity.findViewById(activity.getResources().getIdentifier(
                "coverflowReflect", "id", "pl.polidea.coverflow"));
        activity.setupCoverFlow(reflectingCoverFlow, true, results);
    }

    private String convertUrlToFileID(String url)
    {
        String fileID = url.replaceAll("[\\/:?&=.]", "");
        return fileID;
    }

    private File saveBitmap(Bitmap bitmap, String fileID)
    {
        String root = Environment.getExternalStorageDirectory()
                .getAbsolutePath();
        File filePath = new File(root + "/CoverFlow");
        filePath.mkdirs();

        File file = new File(filePath, fileID);

        if (!file.exists())
        {
            try
            {
                FileOutputStream stream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                stream.flush();
                stream.close();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return file;
    }
}
