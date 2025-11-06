/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorys;

import Database.ConnectionDB;
import Models.SuratMasuk; 
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author szaiii
 */
public class SuratMasukRepository {
    
    public void createSuratMasuk(SuratMasuk surat) throws SQLException {
        // 'id_surat' tidak di-insert karena auto_increment
        String sql = "INSERT INTO surat_masuk(id_surat, no_agenda, asal_surat, no_surat, isi, " +
                     "kode, indeks, tgl_surat, tgl_diterima, file_path, keterangan, id_user) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = ConnectionDB.getConnection();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, surat.getIdSurat());
            stmt.setString(2, surat.getNoAgenda());
            stmt.setString(3, surat.getAsalSurat());
            stmt.setString(4, surat.getNoSurat());
            stmt.setString(5, surat.getIsi());
            stmt.setString(6, surat.getKode());
            stmt.setString(7, surat.getIndeks());
            stmt.setDate(8, surat.getTglSurat());
            stmt.setDate(9, surat.getTglDiterima());
            stmt.setString(10, surat.getFilePath());
            stmt.setString(11, surat.getKeterangan());
            stmt.setInt(12, surat.getIdUser());
            
            stmt.executeUpdate();
            
            System.out.println("Success Create Surat Masuk");
        } catch (SQLException except) {
            System.out.println("Failed Create Surat Masuk: " + except.getMessage());
            throw except;
        }   
    }
    
    public void updateSuratMasuk(SuratMasuk surat) throws SQLException {
        
        String sql = "UPDATE surat_masuk SET no_agenda = ?, asal_surat = ?, no_surat = ?, " +
                     "isi = ?, kode = ?, indeks = ?, tgl_surat = ?, tgl_diterima = ?, " +
                     "file_path = ?, keterangan = ?, id_user = ? " +
                     "WHERE id_surat = ?";
        
        Connection connection = ConnectionDB.getConnection();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, surat.getNoAgenda());
            stmt.setString(2, surat.getAsalSurat());
            stmt.setString(3, surat.getNoSurat());
            stmt.setString(4, surat.getIsi());
            stmt.setString(5, surat.getKode());
            stmt.setString(6, surat.getIndeks());
            stmt.setDate(7, surat.getTglSurat());
            stmt.setDate(8, surat.getTglDiterima());
            stmt.setString(9, surat.getFilePath());
            stmt.setString(10, surat.getKeterangan());
            stmt.setInt(11, surat.getIdUser());
            stmt.setInt(12, surat.getIdSurat()); 
            
            stmt.executeUpdate();
            
            System.out.println("Success Update Surat Masuk");
        } catch (SQLException except) {
            System.out.println("Failed Update Surat Masuk: " + except.getMessage());
            throw except;
        }   
    }
    
    public void deleteSuratMasuk(int idSurat) throws SQLException {
        String sql = "DELETE FROM surat_masuk WHERE id_surat = ?";
        Connection connection = ConnectionDB.getConnection();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idSurat);
            
            stmt.executeUpdate();
            
            System.out.println("Success Delete Surat Masuk");
        } catch (SQLException except) {
            System.out.println("Failed Delete Surat Masuk: " + except.getMessage());
            throw except;
        }   
    }
    
    public SuratMasuk getById(int idSurat) throws SQLException{
        String sql = "SELECT * FROM surat_masuk WHERE id_surat = ?";
        
        SuratMasuk surat = null;
        Connection connection = ConnectionDB.getConnection();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idSurat);
            
            try(ResultSet result = stmt.executeQuery();){
    
                if (result.next()) {
                    surat = new SuratMasuk();
                    
                    surat.setIdSurat(result.getInt("id_surat"));
                    surat.setNoAgenda(result.getString("no_agenda"));
                    surat.setAsalSurat(result.getString("asal_surat"));
                    surat.setNoSurat(result.getString("no_surat"));
                    surat.setIsi(result.getString("isi"));
                    surat.setKode(result.getString("kode"));
                    surat.setIndeks(result.getString("indeks"));
                    surat.setTglSurat(result.getDate("tgl_surat"));
                    surat.setTglDiterima(result.getDate("tgl_diterima"));
                    surat.setFilePath(result.getString("file_path"));
                    surat.setKeterangan(result.getString("keterangan"));
                    surat.setIdUser(result.getInt("id_user"));
                }
            }
        } catch (SQLException except) {
            System.out.println("Surat Masuk Not Found");
            throw except;
        }
        
        return surat;
    }
    
    public List<SuratMasuk> getAll() throws SQLException{

        String sql = "SELECT * FROM surat_masuk";
        
        List<SuratMasuk> ListSuratMasuk = new ArrayList<>();
        
        try {
            
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                SuratMasuk surat = new SuratMasuk();              
            
                surat.setIdSurat(result.getInt("id_surat"));
                surat.setNoAgenda(result.getString("no_agenda"));
                surat.setAsalSurat(result.getString("asal_surat"));
                surat.setNoSurat(result.getString("no_surat"));
                surat.setIsi(result.getString("isi"));
                surat.setKode(result.getString("kode"));
                surat.setIndeks(result.getString("indeks"));
                surat.setTglSurat(result.getDate("tgl_surat"));
                surat.setTglDiterima(result.getDate("tgl_diterima"));
                surat.setFilePath(result.getString("file_path"));
                surat.setKeterangan(result.getString("keterangan"));
                surat.setIdUser(result.getInt("id_user"));

                ListSuratMasuk.add(surat);
            }
            
        } catch (SQLException except) {
            System.out.println("List Surat Masuk Not Found" + except.getMessage());
            throw except;
        }
        return ListSuratMasuk;
    }
    

    public List<SuratMasuk> search(String query) throws SQLException {

        String sql = "SELECT * FROM surat_masuk WHERE " +
                     "no_agenda LIKE ? OR " +
                     "asal_surat LIKE ? OR " +
                     "no_surat LIKE ? OR " +
                     "isi LIKE ? OR " +
                     "keterangan LIKE ?";

        List<SuratMasuk> listSurat = new ArrayList<>();

        String searchQuery = "%" + query + "%"; 

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, searchQuery);
            stmt.setString(2, searchQuery);
            stmt.setString(3, searchQuery);
            stmt.setString(4, searchQuery);
            stmt.setString(5, searchQuery);

            try (ResultSet result = stmt.executeQuery()) {
                while (result.next()) {
                    SuratMasuk surat = new SuratMasuk();
                    surat.setIdSurat(result.getInt("id_surat"));
                    surat.setNoAgenda(result.getString("no_agenda"));
                    surat.setAsalSurat(result.getString("asal_surat"));
                    surat.setNoSurat(result.getString("no_surat"));
                    surat.setIsi(result.getString("isi"));
                    surat.setKode(result.getString("kode"));
                    surat.setIndeks(result.getString("indeks"));
                    surat.setTglSurat(result.getDate("tgl_surat"));
                    surat.setTglDiterima(result.getDate("tgl_diterima"));
                    surat.setFilePath(result.getString("file_path"));
                    surat.setKeterangan(result.getString("keterangan"));
                    surat.setIdUser(result.getInt("id_user"));
                    listSurat.add(surat);
                }
            }
        } catch (SQLException e) {
            System.out.println("Search failed: " + e.getMessage());
            throw e;
        }

        return listSurat;
    }
}