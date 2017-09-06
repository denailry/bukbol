package com.example.user.bukbol.data;

/**
 * Created by User on 06/09/2017.
 */

public class TempatFutsal {

    int gambar;
    String nama;
    String alamat;
    String jam;
    String harga;

    public TempatFutsal(String nama, String alamat, String jam, String harga, int gambar) {
        this.nama = nama;
        this.alamat = alamat;
        this.jam = jam;
        this.harga = harga;
        this.gambar = gambar;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
