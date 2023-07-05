package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import id.ac.polman.astra.kelompok2MI2B.mindcare.R;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.RawRepository;


public class HomeFragment extends Fragment {
    private RawRepository mRawRepository;
    private SharedPreferences pref;
    private TextView mNama;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = requireContext().getSharedPreferences("pengguna_pref", Context.MODE_PRIVATE);
        mRawRepository = RawRepository.get();




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mNama  = (TextView) view.findViewById(R.id.nama_user);
        String nama = pref.getString("nama", "");

        mNama.setText(nama);

        RelativeLayout relativeLayout = view.findViewById(R.id.button_psikolog);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat instance dari AlamatPsikologFragment
                PsikologListFragment psikologListFragment = new PsikologListFragment();

                // Memperoleh instance FragmentManager
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                // Membuat transaksi fragment
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Menggantikan fragment_home dengan AlamatPsikologFragment
                fragmentTransaction.replace(R.id.frame_layout, psikologListFragment);

                // Menambahkan transaksi ke back stack agar dapat kembali ke fragment_home jika diperlukan
                fragmentTransaction.addToBackStack(null);

                // Melakukan commit transaksi
                fragmentTransaction.commit();
            }
        });

        return view;
    }
    public static HomeFragment newInstance(){
        return new HomeFragment();
    }
}