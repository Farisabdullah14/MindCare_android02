package id.ac.polman.astra.kelompok2MI2B.mindcare.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RawService {

    @GET("/api/raw/getdata")
    Call<List<Object[]>> getData(@Query("query")String query);

    @GET("/api/raw/getdatasingle")
    Call<List<Object[]>> getDataSingle(@Query("query")String query);

    @GET("/api/raw/execute")
    Call<Object[]> execute(@Query("query")String query);
}
