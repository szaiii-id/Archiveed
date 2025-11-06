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
public class SuratKeluar {
    private int idSurat;
    private String noAgenda;
    private String tujuan;
    private String noSurat;
    private String isi;
    private String kode;
    private Date tglSurat;
    private String filePath;
    private String keterangan;
    private int idUser;

    public SuratKeluar() {
    }

    public SuratKeluar(int idSurat, String noAgenda, String tujuan, String noSurat, String isi, String kode, Date tglSurat, String filePath, String keterangan, int idUser) {
        this.idSurat = idSurat;
        this.noAgenda = noAgenda;
        this.tujuan = tujuan;
        this.noSurat = noSurat;
        this.isi = isi;
        this.kode = kode;
        this.tglSurat = tglSurat;
        this.filePath = filePath;
        this.keterangan = keterangan;
        this.idUser = idUser;
    }

    public int getIdSurat() {
        return this.idSurat;
    }

    public void setIdSurat(int idSurat) {
        this.idSurat = idSurat;
    }

    public String getNoAgenda() {
        return this.noAgenda;
    }

    public void setNoAgenda(String noAgenda) {
        this.noAgenda = noAgenda;
    }

    public String getTujuan() {
        return this.tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getNoSurat() {
        return this.noSurat;
    }

    public void setNoSurat(String noSurat) {
        this.noSurat = noSurat;
    }

    public String getIsi() {
        return this.isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getKode() {
        return this.kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public Date getTglSurat() {
        return this.tglSurat;
    }

    public void setTglSurat(Date tglSurat) {
        this.tglSurat = tglSurat;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getKeterangan() {
        return this.keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    
}
