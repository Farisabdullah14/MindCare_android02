package id.ac.polman.astra.kelompok2MI2B.mindcare.Service;

import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Jurnal;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Mood;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MoodService {


    @GET("/api/mood/mood")
    Call<Mood> getMoodbyId(@Query("id") int id);

    @GET("/api/mood/moods")
    Call<List<Mood>> getMoods();

    @POST("/api/mood/mood")
    Call<Mood> saveMood(@Body Mood mood);





}
