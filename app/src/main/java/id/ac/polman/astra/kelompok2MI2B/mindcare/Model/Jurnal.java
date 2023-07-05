package id.ac.polman.astra.kelompok2MI2B.mindcare.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Jurnal {

    @SerializedName("id_jurnal")
    @Expose
    private int id_jurnal;

    @SerializedName("id_user")
    @Expose
    private Pengguna id_user;

    @SerializedName("nama_jurnal")
    @Expose
    private String nama_jurnal;

    @SerializedName("tanggal_jurnal")
    @Expose
    private Date tanggal_jurnal;


    @SerializedName("konten")
    @Expose
    private String konten;

    @SerializedName("status")
    @Expose
    private String status;

    public Jurnal(int id_jurnal, Pengguna id_user, String nama_jurnal, Date tanggal_jurnal, String konten, String status) {
        this.id_jurnal = id_jurnal;
        this.id_user = id_user;
        this.nama_jurnal = nama_jurnal;
        this.tanggal_jurnal = tanggal_jurnal;
        this.konten = konten;
        this.status = status;
    }

    public Jurnal() {
    }

    public int getId_jurnal() {
        return id_jurnal;
    }

    public void setId_jurnal(int id_jurnal) {
        this.id_jurnal = id_jurnal;
    }

    public Pengguna getId_user() {
        return id_user;
    }

    public void setId_user(Pengguna id_user) {
        this.id_user = id_user;
    }

    public String getNama_jurnal() {
        return nama_jurnal;
    }

    public void setNama_jurnal(String nama_jurnal) {
        this.nama_jurnal = nama_jurnal;
    }

    public Date getTanggal_jurnal() {
        return tanggal_jurnal;
    }

    public void setTanggal_jurnal(Date tanggal_jurnal) {
        this.tanggal_jurnal = tanggal_jurnal;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
