/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 * @author szaiii
 */
public class Disposisi {
    private int idDisposisi;
    private int idSurat;
    private String tujuan;
    private String isiDisposisi;
    private String sifat;
    private Date batasWaktu;
    private String catatan;
    private int idUser;

    public Disposisi() {
    }

    public Disposisi(int idDisposisi, int idSurat, String tujuan, String isiDisposisi, String sifat, Date batasWaktu, String catatan, int idUser) {
        this.idDisposisi = idDisposisi;
        this.idSurat = idSurat;
        this.tujuan = tujuan;
        this.isiDisposisi = isiDisposisi;
        this.sifat = sifat;
        this.batasWaktu = batasWaktu;
        this.catatan = catatan;
        this.idUser = idUser;
    }

    public int getIdDisposisi() {
        return this.idDisposisi;
    }

    public void setIdDisposisi(int idDisposisi) {
        this.idDisposisi = idDisposisi;
    }

    public int getIdSurat() {
        return this.idSurat;
    }

    public void setIdSurat(int idSurat) {
        this.idSurat = idSurat;
    }

    public String getTujuan() {
        return this.tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getIsiDisposisi() {
        return this.isiDisposisi;
    }

    public void setIsiDisposisi(String isiDisposisi) {
        this.isiDisposisi = isiDisposisi;
    }

    public String getSifat() {
        return this.sifat;
    }

    public void setSifat(String sifat) {
        this.sifat = sifat;
    }

    public Date getBatasWaktu() {
        return this.batasWaktu;
    }

    public void setBatasWaktu(Date batasWaktu) {
        this.batasWaktu = batasWaktu;
    }

    public String getCatatan() {
        return this.catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    
}
