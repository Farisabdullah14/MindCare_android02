package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.w3c.dom.Text;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Jurnal;
import id.ac.polman.astra.kelompok2MI2B.mindcare.R;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.JurnalRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.viewmodel.JurnalDetailViewModel;

public class DetailJurnalFragment extends Fragment {
    private static final String ARG_JURNAL_ID = "jurnal_id";
    private static final String TAG = "DetailJurnalFragment";

    private Jurnal mJurnal;
    private TextView mJurnalKonten;
    private TextView mJurnalName;
    private TextView mJurnalTanggal;

    private JurnalDetailViewModel mJurnalDetailViewModel;
    private int mJurnalId;

    private JurnalRepository mJurnalRepository;


    public JurnalDetailViewModel getJurnalDetailViewModel() {
        if (mJurnalDetailViewModel == null) {
            mJurnalDetailViewModel = new ViewModelProvider(this).get(JurnalDetailViewModel.class);
            System.out.println("data di get kosong");
        }
        return mJurnalDetailViewModel;
    }

    public static DetailJurnalFragment newInstance(int jurnalId) {
        Bundle args = new Bundle();
        args.putInt(ARG_JURNAL_ID, jurnalId);
        DetailJurnalFragment fragment = new DetailJurnalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "DetailJurnalFragment.onCreate() called");
        mJurnalId = getArguments().getInt(ARG_JURNAL_ID);
        mJurnalDetailViewModel = getJurnalDetailViewModel();
        mJurnalRepository = JurnalRepository.get();

    }

    private void updateUI() {
        Log.i(TAG, "DetailJurnalFragment.updateUI() called");
        if (mJurnal != null) {

            Log.d(TAG, "Data tidak kosong");
            mJurnalName.setText(mJurnal.getNama_jurnal());
            mJurnalKonten.setText(mJurnal.getKonten());
            mJurnalTanggal.setText(String.valueOf(mJurnal.getTanggal_jurnal()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_jurnal, container, false);
        mJurnalName = v.findViewById(R.id.jurnal_judul);
        mJurnalKonten = v.findViewById(R.id.jurnal_konten);
        mJurnalTanggal = v.findViewById(R.id.jurnal_tanggal);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "DetailJurnalFragment.onViewCreated() called");

        mJurnalDetailViewModel.getJurnalLiveData().observe(getViewLifecycleOwner(), new Observer<Jurnal>() {
            @Override
            public void onChanged(Jurnal jurnal) {
                mJurnal = jurnal;
                updateUI();
            }
        });
        mJurnalDetailViewModel.loadJurnal(mJurnalId);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "DetailJurnalFragment.onStop() called");
        // mJurnalDetailViewModel.saveJurnal(mJurnal);
    }
}





