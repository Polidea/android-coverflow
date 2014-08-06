package pl.polidea.coverflow.testingactivity;

import android.net.Uri;

import java.net.URL;

/**
 * Created by martawoldanska on 8/4/14.
 */
public class Result
{

    private URL avatarURL;
    private String userId;
    private Uri avatarUri;

    public Result()
    {
        this.avatarURL = null;
        this.userId = "";
        this.avatarUri = null;
    }

    public Result(URL avatarURL, String userId, Uri avatarUri)
    {
        this.avatarURL = avatarURL;
        this.userId = userId;
        this.avatarUri = avatarUri;
    }

    public URL getAvatarURL()
    {
        return this.avatarURL;
    }

    public void setAvatarURL(URL avatarURL)
    {
        this.avatarURL = avatarURL;
    }

    public String getUserId()
    {
        return this.userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public Uri getAvatarUri()
    {
        return avatarUri;
    }

    public void setAvatarUri(Uri avatarUri)
    {
        this.avatarUri = avatarUri;
    }
}
