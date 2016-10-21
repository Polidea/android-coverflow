package pl.polidea.coverflow.testingactivity;

public class Country
{
    private int id;
    private String name;
    String flag_128;

    public Result toResult()
    {
        return new Result(
                "https://raw.githubusercontent.com/cristiroma/countries/master/data/flags/" + flag_128,
                String.valueOf(id)
        );
    }
}
