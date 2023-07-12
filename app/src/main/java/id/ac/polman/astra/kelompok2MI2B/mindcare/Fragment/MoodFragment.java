package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Mood;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Model.Pengguna;
import id.ac.polman.astra.kelompok2MI2B.mindcare.R;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.MoodRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.PenggunaRepository;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.RawRepository;

import android.widget.ImageView;
import android.view.View;

public class MoodFragment extends Fragment {
    private static final String TAG = "MoodFragment";


    private EditText mPerasaanField;
    private ImageView mMoodView1;
    private ImageView mMoodView2;
    private ImageView mMoodView3;
    private ImageView mMoodView4;
    private ImageView mMoodView5;
    private Button btnSimpan;
    private MoodRepository mMoodRepository;
    private SharedPreferences pref;
    private RawRepository mRawRepository;
    private int nilaiMood;

    private Pengguna mPengguna;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String dateorder;

    private HomeFragment mHomeFragment;


    // Deklarasikan ImageView dan simpan referensinya



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = requireContext().getSharedPreferences("pengguna_pref", Context.MODE_PRIVATE);

        mRawRepository = RawRepository.get();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mood, container, false);
        String nama = pref.getString("id_user", "");
        double idUser = Double.parseDouble(nama);
        int idPengguna = (int) idUser;
        System.out.println(idPengguna);
        // Inisialisasi UI

        mPerasaanField = view.findViewById(R.id.editTextPerasaan);
        mMoodView1 = view.findViewById(R.id.icon1);
        mMoodView2 = view.findViewById(R.id.icon2);
        mMoodView3 = view.findViewById(R.id.icon3);
        mMoodView4 = view.findViewById(R.id.icon4);
        mMoodView5 = view.findViewById(R.id.icon5);
        btnSimpan = view.findViewById(R.id.btn_simpanmood);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateorder = dateFormat.format(calendar.getTime());

        Drawable originalIcon1 = mMoodView1.getDrawable();

        mMoodView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMoodView1.setImageResource(R.drawable.emoji5);

                // Kembalikan gambar pada icon2 ke gambar asli
                mMoodView4.setImageDrawable(originalIcon1);
                mMoodView5.setImageDrawable(originalIcon1);
                mMoodView3.setImageDrawable(originalIcon1);
                mMoodView2.setImageDrawable(originalIcon1);

                Toast.makeText(getContext(), "Mood Anda Sangat Buruk, Semoga Harimu Lebih Baik!", Toast.LENGTH_SHORT).show();
                nilaiMood =1;


            }
        });

        mMoodView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMoodView2.setImageResource(R.drawable.emoji4);

                mMoodView1.setImageDrawable(originalIcon1);
                mMoodView4.setImageDrawable(originalIcon1);
                mMoodView5.setImageDrawable(originalIcon1);
                mMoodView3.setImageDrawable(originalIcon1);

                Toast.makeText(getContext(), "Mood Anda Buruk, Semoga Semuanya berjalan baik!", Toast.LENGTH_SHORT).show();
                nilaiMood =2;
            }
        });

        mMoodView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMoodView3.setImageResource(R.drawable.emoji3);

                mMoodView1.setImageDrawable(originalIcon1);
                mMoodView2.setImageDrawable(originalIcon1);
                mMoodView4.setImageDrawable(originalIcon1);
                mMoodView5.setImageDrawable(originalIcon1);

                Toast.makeText(getContext(), "Mood Anda Sangat Normal, Semangat !", Toast.LENGTH_SHORT).show();
                nilaiMood =3;
            }
        });

        mMoodView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMoodView4.setImageResource(R.drawable.emoji2);

                mMoodView1.setImageDrawable(originalIcon1);
                mMoodView2.setImageDrawable(originalIcon1);
                mMoodView3.setImageDrawable(originalIcon1);
                mMoodView5.setImageDrawable(originalIcon1);

                Toast.makeText(getContext(), "Mood Anda Baik, Kamu yang terbaik!", Toast.LENGTH_SHORT).show();
                nilaiMood =4;
            }
        });

        mMoodView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMoodView5.setImageResource(R.drawable.emoji1);

                mMoodView1.setImageDrawable(originalIcon1);
                mMoodView2.setImageDrawable(originalIcon1);
                mMoodView4.setImageDrawable(originalIcon1);
                mMoodView3.setImageDrawable(originalIcon1);

                Toast.makeText(getContext(), "Mood Anda Sangat Baik, Tetap Jaga ya!", Toast.LENGTH_SHORT).show();
                nilaiMood =5;
            }
        });

        // Inisialisasi Repository
        mMoodRepository = MoodRepository.get();

        // Mengatur aksi klik pada tombol simpan
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat objek pengguna baru
                Mood mood = new Mood();
                mood.setPerasaan(mPerasaanField.getText().toString());
                mood.setId_user(idPengguna);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    System.out.println(dateorder);
                    mood.setTanggal(dateorder);

                }

                mood.setNilai(nilaiMood);

                // Melakukan permintaan registrasi
                mMoodRepository.addMood(mood);

                HomeFragment fragmentHome = new HomeFragment();
                replaceFragment(fragmentHome);
                System.out.println("Mood telah masuk di FRAGMENT " + mPerasaanField.getText().toString());
            }
        });


        return view;

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }





}
