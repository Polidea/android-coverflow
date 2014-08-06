package pl.polidea.coverflow.testingactivity;

import android.net.Uri;

import java.net.URI;
import java.net.URL;

/**
 * Created by martawoldanska on 8/4/14.
 */
public class Result
{

    private String avatarURL;
    private String userId;

    public Result()
    {
        this.avatarURL = null;
        this.userId = "";
    }

    public Result(String avatarURL, String userId)
    {
        this.avatarURL = avatarURL;
        this.userId = userId;
    }

    public String getAvatarURL()
    {
        return this.avatarURL;
    }

}
