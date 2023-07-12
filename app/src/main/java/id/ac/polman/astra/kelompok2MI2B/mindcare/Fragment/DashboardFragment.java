package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import id.ac.polman.astra.kelompok2MI2B.mindcare.R;

import id.ac.polman.astra.kelompok2MI2B.mindcare.databinding.ActivityMainBinding;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class DashboardFragment extends Fragment {
    private static final String TAG = "DashboardFragment";
    ActivityMainBinding binding;

    private FloatingActionButton fabButton;
    private MoodFragment mMoodFragment;
    private BottomSheetDialog bottomSheetDialog;
    private AlertDialog alertDialog;

    public static DashboardFragment newInstance(){
        return new DashboardFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.jurnal:
                    replaceFragment(new JurnalListFragment());
                    break;
                case R.id.Statik:
                    replaceFragment(new StatistikFragment());
                    break;
                case R.id.akun:
                    replaceFragment(new ProfilFragment());
                    break;
            }
            return true;
        });

        fabButton = view.findViewById(R.id.button_emote);
        fabButton.setOnClickListener(v -> showIconAlertDialog());

        BottomAppBar bottomAppBar = binding.bottomAppBar;
        ((AppCompatActivity) getActivity()).setSupportActionBar(bottomAppBar);
    }

    public void showIconSheetDialog() {
        bottomSheetDialog = new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(R.layout.sheet_icon);

        ImageView icon1 = bottomSheetDialog.findViewById(R.id.icon1);
        ImageView icon2 = bottomSheetDialog.findViewById(R.id.icon2);
        ImageView icon3 = bottomSheetDialog.findViewById(R.id.icon3);
        ImageView icon4 = bottomSheetDialog.findViewById(R.id.icon4);
        ImageView icon5 = bottomSheetDialog.findViewById(R.id.icon5);

        icon1.setOnClickListener(v -> {
            Fragment moodFragment = new MoodFragment();
            replaceFragment(moodFragment);
            bottomSheetDialog.dismiss();
        });

        icon2.setOnClickListener(v -> {
            // Logika yang akan dijalankan saat icon2 diklik
            Fragment moodFragment = new MoodFragment();
            replaceFragment(moodFragment);
            bottomSheetDialog.dismiss();
        });

        icon3.setOnClickListener(v -> {
            // Logika yang akan dijalankan saat icon3 diklik
            Fragment moodFragment = new MoodFragment();
            replaceFragment(moodFragment);
            bottomSheetDialog.dismiss();
        });

        icon4.setOnClickListener(v -> {
            // Logika yang akan dijalankan saat icon4 diklik
            Fragment moodFragment = new MoodFragment();
            replaceFragment(moodFragment);
            bottomSheetDialog.dismiss();
        });

        icon5.setOnClickListener(v -> {
            Fragment moodFragment = new MoodFragment();
            replaceFragment(moodFragment);
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.show();
    }


    public void showIconAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogView = getLayoutInflater().inflate(R.layout.sheet_icon, null);
        builder.setView(dialogView);
        alertDialog = builder.create();
        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM; // Ubah posisi dialog sesuai keinginan (misalnya, Gravity.TOP untuk posisi atas)

        params.x = 0; // Mengatur posisi x (horizontal)
        params.y = 200; // Mengatur posisi y (vertical)
        window.setAttributes(params);



        ImageView icon1 = dialogView.findViewById(R.id.icon1);
        ImageView icon2 = dialogView.findViewById(R.id.icon2);
        ImageView icon3 = dialogView.findViewById(R.id.icon3);
        ImageView icon4 = dialogView.findViewById(R.id.icon4);
        ImageView icon5 = dialogView.findViewById(R.id.icon5);

        icon1.setOnClickListener(v -> {


            Fragment moodFragment = new MoodFragment();
            replaceFragment(moodFragment);
            alertDialog.dismiss();
        });

        icon2.setOnClickListener(v -> {

            // Logika yang akan dijalankan saat icon2 diklik
            Fragment moodFragment = new MoodFragment();
            replaceFragment(moodFragment);
            alertDialog.dismiss();
        });

        icon3.setOnClickListener(v -> {
            // Logika yang akan dijalankan saat icon3 diklik
            Fragment moodFragment = new MoodFragment();
            replaceFragment(moodFragment);
            alertDialog.dismiss();
        });

        icon4.setOnClickListener(v -> {
            // Logika yang akan dijalankan saat icon4 diklik
            Fragment moodFragment = new MoodFragment();
            replaceFragment(moodFragment);
            alertDialog.dismiss();
        });

        icon5.setOnClickListener(v -> {
            Fragment moodFragment = new MoodFragment();
            replaceFragment(moodFragment);
            alertDialog.dismiss();
        });

        alertDialog.show();
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
