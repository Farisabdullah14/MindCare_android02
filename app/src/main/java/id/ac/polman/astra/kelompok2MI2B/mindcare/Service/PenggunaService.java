package id.ac.polman.astra.kelompok2MI2B.mindcare.Service;



import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Pengguna;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Service.response.PenggunaResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface PenggunaService {
    @GET("pengguna")
    Call<Pengguna> getPenggunaById(@Query("id_pengguna") int id);

    @GET("users")
    Call<List<Pengguna>> getPenggunas();

    @POST("user")
    Call<Pengguna> addPengguna(@Body Pengguna pengguna);

    @POST("savepengguna")
    Call<Pengguna> savePengguna(@Body Pengguna pengguna);

    @PUT("user")
    Call<Pengguna> updatePengguna(@Body Pengguna pengguna);

    @DELETE("user")
    Call<Pengguna> deletePenggunaById(@Query("id_pengguna") int id);

    @FormUrlEncoded
    @POST("login")
    Call<PenggunaResponse> login(@Field("username") String username, @Field("password") String password);

}
