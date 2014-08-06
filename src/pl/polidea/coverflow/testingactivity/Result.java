package pl.polidea.coverflow.testingactivity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by martawoldanska on 8/4/14.
 */
public class Result
{
    @SerializedName("gravatar")
    private String avatarURL;

    @SerializedName("id")
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
