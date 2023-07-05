package id.ac.polman.astra.kelompok2MI2B.mindcare.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.R;
import id.ac.polman.astra.kelompok2MI2B.mindcare.helper.QueryHelper;
import id.ac.polman.astra.kelompok2MI2B.mindcare.helper.ValidationHelper;
import id.ac.polman.astra.kelompok2MI2B.mindcare.repository.RawRepository;

public class LoginActivity extends AppCompatActivity {


    private static final String TAG = "RawRepository";
    private RawRepository mRawRepository;
    private QueryHelper QH;


    private EditText mUsername;
    private EditText mPassword;
    private Button mLoginButton;

    private TextView mSignupButton;

    private SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RawRepository.initialize(getApplicationContext());
        setContentView(R.layout.login);
        initComponents();
    }

    private void initComponents() {
        pref = getSharedPreferences("pengguna_pref",MODE_PRIVATE);
        mRawRepository = RawRepository.get();

        if(pref.contains("username") && pref.contains("password")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else{
            mLoginButton = (Button) findViewById(R.id.btn_login);
            mSignupButton = (TextView) findViewById(R.id.btn_daftar);
            mUsername = (EditText) findViewById(R.id.login_username);
            mPassword = (EditText) findViewById(R.id.login_password);
            /*mNoIdentitasLayout = (TextInputLayout) findViewById(R.id.no_identitas_Layout);
            mPasswordLayout = (TextInputLayout) findViewById(R.id.passwordLayout);*/

            mLoginButton.setOnClickListener(v -> {
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                /*Log.d(TAG, nim);
                Log.d(TAG, password);*/

                if (validate(v)) {
                    Log.d(TAG, "A");
                    /*loading = ProgressDialog.show(this, "Login", "Please wait...", true, false);*/
                    /*mRawRepository.getData(QH.login(no_identitas,password)).observe(this, new Observer<List<Object[]>>() {*/
                    mRawRepository.getData(QH.login(username,password)).observe(this, new Observer<List<Object[]>>() {
                        @Override
                        public void onChanged(List<Object[]> objects) {
                            int nomsg = Integer.parseInt(objects.get(0)[0].toString());
                            if(nomsg  == 200){
                                Log.d(TAG, "tes");
                                //ambildata
                                List<Object[]> resultdata = new ArrayList<>();
                                for(int i = 1 ;i < objects.size();i++){
                                    Object[] data = new Object[]{};
                                    data = (Object[]) objects.get(i);
                                    resultdata.add(data);
                                }

                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("id_user", resultdata.get(0)[0].toString());
                                editor.putString("NIM", resultdata.get(0)[1].toString());
                                editor.putString("nama", resultdata.get(0)[2].toString());
                                editor.putString("jenis_kelamin", resultdata.get(0)[3].toString());
                                editor.putString("alamat", resultdata.get(0)[4].toString());
                                editor.putString("password", resultdata.get(0)[5].toString());
                                editor.putString("status", resultdata.get(0)[6].toString());
                                editor.apply();

                                String nama = pref.getString("id_user", "");
                                Log.d(TAG, nama);

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                                Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                            }
                            /*loading.dismiss();*/
                        }
                    });
                }
            });

            mSignupButton.setOnClickListener(v -> {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            });
        }
    }

    public boolean validate(View v) {
        boolean usernameValidation = ValidationHelper.requiredTextEditValidation(mUsername);
        boolean passwordValidation = ValidationHelper.requiredTextEditValidation(mPassword);

        return usernameValidation && passwordValidation;
    }





}
