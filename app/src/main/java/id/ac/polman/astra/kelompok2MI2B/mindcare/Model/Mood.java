package id.ac.polman.astra.kelompok2MI2B.mindcare.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;


public class Mood {
    @SerializedName("id_mood")
    @Expose
    private int id_mood;

    @SerializedName("id_user")
    @Expose
    private Pengguna id_user;

    @SerializedName("tanggal")
    @Expose
    private Date tanggal;

    @SerializedName("nilai")
    @Expose
    private int nilai;

    @SerializedName("perasaan")
    @Expose
    private String perasaan;

    public Mood(int id_mood, Pengguna id_user, Date tanggal, int nilai, String perasaan) {
        this.id_mood = id_mood;
        this.id_user = id_user;
        this.tanggal = tanggal;
        this.nilai = nilai;
        this.perasaan = perasaan;
    }

    public int getId_mood() {
        return id_mood;
    }

    public void setId_mood(int id_mood) {
        this.id_mood = id_mood;
    }

    public Pengguna getId_user() {
        return id_user;
    }

    public void setId_user(Pengguna id_user) {
        this.id_user = id_user;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }

    public String getPerasaan() {
        return perasaan;
    }

    public void setPerasaan(String perasaan) {
        this.perasaan = perasaan;
    }
}
