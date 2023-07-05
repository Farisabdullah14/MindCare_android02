package id.ac.polman.astra.kelompok2MI2B.mindcare.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;



import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Api.ApiUtils;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Pengguna;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Service.PenggunaService;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Service.response.PenggunaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PenggunaRepository {
    private static final String TAG = "PenggunaRepository";
    private static PenggunaRepository INSTANCE;
    private PenggunaService mPenggunaService;

    private PenggunaRepository(Context context){
        mPenggunaService = ApiUtils.getPenggunaService();
    }

    public static void initialize(Context context){
        if(INSTANCE == null){
            INSTANCE = new PenggunaRepository(context);
        }
    }

    public static PenggunaRepository get(){
        return INSTANCE;
    }

    public MutableLiveData<List<Pengguna>> getPenggunas(){
        MutableLiveData<List<Pengguna>> penggunas = new MutableLiveData<>();

        Call<List<Pengguna>> call = mPenggunaService.getPenggunas();
        call.enqueue(new Callback<List<Pengguna>>() {
            @Override
            public void onResponse(Call<List<Pengguna>> call,
                                   Response<List<Pengguna>> response) {
                if (response.isSuccessful()){
                    penggunas.setValue(response.body());
                    Log.d(TAG, "getPenggunas.onResponse() called");
                }
            }

            @Override
            public void onFailure(Call<List<Pengguna>> call, Throwable t) {
                Log.e("Error API call : ", t.getMessage());

            }
        });
        return penggunas;
    }

    public MutableLiveData<Pengguna> getPengguna(int penggunaId){
        MutableLiveData<Pengguna> pengguna = new MutableLiveData<>();

        Call<Pengguna> call = mPenggunaService.getPenggunaById(penggunaId);
        call.enqueue(new Callback<Pengguna>() {
            @Override
            public void onResponse(Call<Pengguna> call,
                                   Response<Pengguna> response) {
                if(response.isSuccessful()){
                    pengguna.setValue(response.body());
                    Log.d(TAG, "getPenggunaById.onResponse() called" + response.body());
                }
            }

            @Override
            public void onFailure(Call<Pengguna> call, Throwable t) {
                Log.e("Error API call : ", t.getMessage());
            }
        });
        return pengguna;
    }

    public void updatePengguna(Pengguna pengguna){
        Log.i(TAG, "updatePengguna() called");
        Call<Pengguna> call = mPenggunaService.updatePengguna(pengguna);
        call.enqueue(new Callback<Pengguna>() {
            @Override
            public void onResponse(Call<Pengguna> call, Response<Pengguna> response) {
                if (response.isSuccessful()){
                    Log.i(TAG, "Pengguna updated " + pengguna.getNama());
                }
            }

            @Override
            public void onFailure(Call<Pengguna> call, Throwable t) {

            }
        });
    }

    public void savepengguna(Pengguna pengguna){
        Log.i(TAG, "addPengguna() called");
        Call<Pengguna> call = mPenggunaService.addPengguna(pengguna);
        call.enqueue(new Callback<Pengguna>() {
            @Override
            public void onResponse(Call<Pengguna> call, Response<Pengguna> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "Pengguna added " + pengguna.getNama());
                }
            }

            @Override
            public void onFailure(Call<Pengguna> call, Throwable t) {

            }
        });
    }

    public void deletePengguna(int penggunaId){
        Log.i(TAG, "deleteUser() called");
        Call<Pengguna> call = mPenggunaService.deletePenggunaById(penggunaId);
        call.enqueue(new Callback<Pengguna>() {
            @Override
            public void onResponse(Call<Pengguna> call, Response<Pengguna> response) {
                if (response.isSuccessful()){
                    Log.i(TAG, "Pengguna deleted with id: " + penggunaId);
                }
            }

            @Override
            public void onFailure(Call<Pengguna> call, Throwable t) {
                Log.e("Error API call : ", t.getMessage());
            }
        });
    }

    public MutableLiveData<PenggunaResponse> login(String username, String password){
        MutableLiveData<PenggunaResponse> cekLogin = new MutableLiveData<>();

        Call<PenggunaResponse> call = mPenggunaService.login(username, password);
        call.enqueue(new Callback<PenggunaResponse>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<PenggunaResponse> call, Response<PenggunaResponse> response) {
                if(response.isSuccessful()){
                    cekLogin.setValue(response.body());
                    Log.d("CekPengguna", "cekPengguna.onResponse called ");
                }
            }

            @Override
            public void onFailure(Call<PenggunaResponse> call, Throwable t) {
                Log.e("Error API call : ", t.getMessage() );
            }
        });
        return cekLogin;
    }
}
