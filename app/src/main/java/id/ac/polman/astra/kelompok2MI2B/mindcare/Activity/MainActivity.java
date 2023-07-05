package id.ac.polman.astra.kelompok2MI2B.mindcare.Activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

/*import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.HomeFragment;*/
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.DashboardFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.DetailJurnalFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.JurnalListFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.LoginFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.PsikologListFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment.RegisterFragment;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Psikolog;
import id.ac.polman.astra.kelompok2MI2B.mindcare.R;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.JurnalRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.PenggunaRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.PsikologRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.RawRepository;

public class MainActivity extends AppCompatActivity implements JurnalListFragment.Callbacks, PsikologListFragment.Callbacks {
    private static final String TAG = "MainActivity";

    private RawRepository mRawRepository;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kosong);

        JurnalRepository.initialize(this);
        PsikologRepository.initialize(this);

        initComponents();
    }

    private void initComponents() {
        pref = getSharedPreferences("pengguna_pref", MODE_PRIVATE);
        mRawRepository = RawRepository.get();

        if (pref.contains("NIM") && pref.contains("password")) {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.activity_main_fragment_container);
            fragment = new DashboardFragment().newInstance();

            if (fragment == null) {
                fm.beginTransaction().add(R.id.activity_main_fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            } else {
                fm.beginTransaction()
                        .replace(R.id.activity_main_fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        } else {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.activity_main_fragment_container);
            fragment = new LoginFragment().newInstance();
            fm.beginTransaction().replace(R.id.activity_main_fragment_container, fragment).commit();
        }
    }

    @Override
    public void onJurnalSelected(int jurnalId) {
        Log.i(TAG, "id_section = " + jurnalId);
        Fragment fragment = DetailJurnalFragment.newInstance(jurnalId);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onPsikologSelected(int psikologId) {
        Log.i(TAG,"id_psikolog = " + psikologId);
        Fragment fragment = PsikologListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_home, fragment)
                .addToBackStack(null)
                .commit();
    }
}
