package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import id.ac.polman.astra.kelompok2MI2B.mindcare.Activity.LoginActivity;
import id.ac.polman.astra.kelompok2MI2B.mindcare.Activity.MainActivity;
import id.ac.polman.astra.kelompok2MI2B.mindcare.R;

public class LoginFragment extends Fragment {

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    private Button mButtonRegister;
    private Button mButtonLogin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*return super.onCreateView(inflater, container, savedInstanceState);*/
        View view = inflater.inflate(R.layout.fragment_welcome,container,false);


        mButtonLogin = (Button) view.findViewById(R.id.loginButton);



        Bundle bundle = getArguments();


        FragmentManager fm = getActivity().getSupportFragmentManager();


        //buat masuk ke fragment register
//        mButtonRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = fm.findFragmentById(R.id.activity_main_fragment_container);
//                fragment = new PageRegisterFragment().newInstance();
//                fm.beginTransaction().replace(R.id.activity_main_fragment_container,fragment).addToBackStack(null).commit();
//            }
//        });


        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(requireContext(), LoginActivity.class);
                startActivity(intent);

                //  finish();


//                Fragment fragment = fm.findFragmentById(R.id.activity_main_fragment_container);
//                fragment = new PenggunaFragmentLogin().newInstance();
//
//                //untuk mengganti ke main fragment lagi
//                fm.beginTransaction().replace(R.id.activity_main_fragment_container,fragment).addToBackStack(null).commit();
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = fm.findFragmentById(R.id.activity_main_fragment_container);
                fragment = new RegisterFragment().newInstance();

                //untuk mengganti ke main fragment lagi
                fm.beginTransaction().replace(R.id.activity_main_fragment_container,fragment).addToBackStack(null).commit();
            }
        });

        return view;
    }


}
