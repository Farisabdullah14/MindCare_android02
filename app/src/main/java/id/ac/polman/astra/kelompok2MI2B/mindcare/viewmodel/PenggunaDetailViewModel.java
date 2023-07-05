package id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Pengguna;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Service.response.PenggunaResponse;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.PenggunaRepository;


public class PenggunaDetailViewModel extends ViewModel {
    private static final String TAG = "PenggunaDetailViewModel";

    private LiveData<Pengguna> mPenggunaLiveData;
    private PenggunaRepository mPenggunaRepository;
    private MutableLiveData<Integer> mIdMutableLiveData;

    public PenggunaDetailViewModel(){
        mPenggunaRepository = PenggunaRepository.get();
        mIdMutableLiveData = new MutableLiveData<Integer>();
        mPenggunaLiveData = Transformations.switchMap(mIdMutableLiveData,
                penggunaId -> mPenggunaRepository.getPengguna(penggunaId));
    }

    public void loadPengguna(int penggunaId){
        Log.i(TAG, "loadPengguna() called");
        mIdMutableLiveData.setValue(penggunaId);
    }

    public LiveData<Pengguna> getPenggunaLiveData(){
        Log.i(TAG, "getPenggunaLiveData() called");
        return mPenggunaLiveData;
    }

    public void savePengguna(Pengguna pengguna){
        mPenggunaRepository.updatePengguna(pengguna);
    }

    public void deleteUser(int penggunaId){
        mPenggunaRepository.deletePengguna(penggunaId);
    }

    public LiveData<PenggunaResponse> login(String username, String password) {
        return mPenggunaRepository.login(username, password);
    }
}
