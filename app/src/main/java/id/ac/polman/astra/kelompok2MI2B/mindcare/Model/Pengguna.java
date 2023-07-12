package id.ac.polman.astra.kelompok2MI2B.mindcare.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pengguna {
    @PrimaryKey
    @NonNull
    private int mid_user;
    private String mNIM;
    private String mNama;
    private String mUsername;
    private String mPassword;

    private String mAlamat;

    private String mjenis_kelamin;

    private String status;


    public Pengguna() {

    }


    public Pengguna(int id_user, String NIM, String nama, String username, String password, String alamat, String jenisKelamin, String status) {
        mid_user = id_user;
        mNIM = NIM;
        mNama = nama;
        mUsername = username;
        mPassword = password;
        mAlamat = alamat;
        mjenis_kelamin = jenisKelamin;
        this.status = status;
    }

    public int getMid_user() {
        return mid_user;
    }

    public void setMid_user(int mid_user) {
        this.mid_user = mid_user;
    }

    public String getMjenis_kelamin() {
        return mjenis_kelamin;
    }

    public void setMjenis_kelamin(String mjenis_kelamin) {
        this.mjenis_kelamin = mjenis_kelamin;
    }


    public String getNIM() {
        return mNIM;
    }

    public void setNIM(String NIM) {
        mNIM = NIM;
    }

    public String getNama() {
        return mNama;
    }

    public void setNama(String nama) {
        mNama = nama;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getAlamat() {
        return mAlamat;
    }

    public void setAlamat(String alamat) {
        mAlamat = alamat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
