/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author szaiii
 */
public class PengaturanInstansi {
    private int idInstansi;
    private String namaInstansi;
    private String alamat;
    private String telpon;
    private String website;
    private String email;
    private String logoPath;

    public PengaturanInstansi() {
    }

    public PengaturanInstansi(int idInstansi, String namaInstansi, String alamat, String telpon, String website, String email, String logoPath) {
        this.idInstansi = idInstansi;
        this.namaInstansi = namaInstansi;
        this.alamat = alamat;
        this.telpon = telpon;
        this.website = website;
        this.email = email;
        this.logoPath = logoPath;
    }

    public int getIdInstansi() {
        return this.idInstansi;
    }

    public void setIdInstansi(int idInstansi) {
        this.idInstansi = idInstansi;
    }

    public String getNamaInstansi() {
        return this.namaInstansi;
    }

    public void setNamaInstansi(String namaInstansi) {
        this.namaInstansi = namaInstansi;
    }

    public String getAlamat() {
        return this.alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelpon() {
        return this.telpon;
    }

    public void setTelpon(String telpon) {
        this.telpon = telpon;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogoPath() {
        return this.logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
    
    
}
