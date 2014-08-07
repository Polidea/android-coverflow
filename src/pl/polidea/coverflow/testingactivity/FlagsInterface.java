package pl.polidea.coverflow.testingactivity;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface FlagsInterface
{
    public static class Wrapper {
        public List<Country> countries;
    }

    @GET("/raw.php")
    void fetch(@Query("i") String id, Callback<Wrapper> cb);
}
