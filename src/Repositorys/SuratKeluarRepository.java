/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorys;

import Database.ConnectionDB;
import Models.SuratKeluar; // <- Diubah ke Model SuratKeluar
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
public class SuratKeluarRepository {
    
    public void createSuratKeluar(SuratKeluar surat) throws SQLException {
        String sql = "INSERT INTO surat_keluar(id_surat, no_agenda, tujuan, no_surat, isi, " +
                     "kode, tgl_surat, file_path, keterangan, id_user) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 

        Connection connection = ConnectionDB.getConnection();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, surat.getIdSurat()); 
            stmt.setString(2, surat.getNoAgenda());
            stmt.setString(3, surat.getTujuan());
            stmt.setString(4, surat.getNoSurat());
            stmt.setString(5, surat.getIsi());
            stmt.setString(6, surat.getKode());
            stmt.setDate(7, surat.getTglSurat());
            stmt.setString(8, surat.getFilePath());
            stmt.setString(9, surat.getKeterangan());
            stmt.setInt(10, surat.getIdUser()); 

            stmt.executeUpdate();

            System.out.println("Success Create Surat Keluar");
        } catch (SQLException except) {
            System.out.println("Failed Create Surat Keluar: " + except.getMessage());
            throw except;
        }    
    }
    
    public void updateSuratKeluar(SuratKeluar surat) throws SQLException {
        
        String sql = "UPDATE surat_keluar SET no_agenda = ?, tujuan = ?, no_surat = ?, " +
                     "isi = ?, kode = ?, tgl_surat = ?, file_path = ?, " +
                     "keterangan = ?, id_user = ? " +
                     "WHERE id_surat = ?";
        
        Connection connection = ConnectionDB.getConnection();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, surat.getNoAgenda());
            stmt.setString(2, surat.getTujuan());
            stmt.setString(3, surat.getNoSurat());
            stmt.setString(4, surat.getIsi());
            stmt.setString(5, surat.getKode());
            stmt.setDate(6, surat.getTglSurat());
            stmt.setString(7, surat.getFilePath());
            stmt.setString(8, surat.getKeterangan());
            stmt.setInt(9, surat.getIdUser());
            stmt.setInt(10, surat.getIdSurat()); 
            
            stmt.executeUpdate();
            
            System.out.println("Success Update Surat Keluar");
        } catch (SQLException except) {
            System.out.println("Failed Update Surat Keluar: " + except.getMessage());
        }   
    }
    
    public void deleteSuratKeluar(int idSurat) throws SQLException {
        String sql = "DELETE FROM surat_keluar WHERE id_surat = ?";
        Connection connection = ConnectionDB.getConnection();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idSurat);
            
            stmt.executeUpdate();
            
            System.out.println("Success Delete Surat Keluar");
        } catch (SQLException except) {
            System.out.println("Failed Delete Surat Keluar: " + except.getMessage());
        }   
    }
    
    public SuratKeluar getById(int idSurat) throws SQLException{
        String sql = "SELECT * FROM surat_keluar WHERE id_surat = ?";
        
        SuratKeluar surat = null;
        Connection connection = ConnectionDB.getConnection();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idSurat);
            
            try(ResultSet result = stmt.executeQuery()){
    
                if (result.next()) {
                    surat = new SuratKeluar();
                    
                    surat.setIdSurat(result.getInt("id_surat"));
                    surat.setNoAgenda(result.getString("no_agenda"));
                    surat.setTujuan(result.getString("tujuan"));
                    surat.setNoSurat(result.getString("no_surat"));
                    surat.setIsi(result.getString("isi"));
                    surat.setKode(result.getString("kode"));
                    surat.setTglSurat(result.getDate("tgl_surat"));
                    surat.setFilePath(result.getString("file_path"));
                    surat.setKeterangan(result.getString("keterangan"));
                    surat.setIdUser(result.getInt("id_user"));
                }
            }
        } catch (SQLException except) {
            System.out.println("Surat Keluar Not Found");
        }
        
        return surat;
    }
    
    public List<SuratKeluar> getAll() throws SQLException{

        String sql = "SELECT * FROM surat_keluar";
        
        List<SuratKeluar> ListSuratKeluar = new ArrayList<>();
        
        try {
            
            Connection conn = ConnectionDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                SuratKeluar surat = new SuratKeluar();              
            
                surat.setIdSurat(result.getInt("id_surat"));
                surat.setNoAgenda(result.getString("no_agenda"));
                surat.setTujuan(result.getString("tujuan"));
                surat.setNoSurat(result.getString("no_surat"));
                surat.setIsi(result.getString("isi"));
                surat.setKode(result.getString("kode"));
                surat.setTglSurat(result.getDate("tgl_surat"));
                surat.setFilePath(result.getString("file_path"));
                surat.setKeterangan(result.getString("keterangan"));
                surat.setIdUser(result.getInt("id_user"));

                ListSuratKeluar.add(surat);
            }
            
        } catch (SQLException except) {
            System.out.println("List Surat Keluar Not Found" + except.getMessage());
        }
        return ListSuratKeluar;
    }
    
    public List<SuratKeluar> search(String query) throws SQLException {
    
        String sql = "SELECT * FROM surat_keluar WHERE " +
                     "id_surat LIKE ? OR " +
                     "no_agenda LIKE ? OR " +
                     "tujuan LIKE ? OR " +
                     "no_surat LIKE ? OR " +
                     "isi LIKE ? OR " +
                     "kode LIKE ? OR " +
                     "file_path LIKE ? OR " +
                     "keterangan LIKE ?";

        List<SuratKeluar> listSurat = new ArrayList<>();
        String searchQuery = "%" + query + "%"; 

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, searchQuery);
            stmt.setString(2, searchQuery);
            stmt.setString(3, searchQuery);
            stmt.setString(4, searchQuery);
            stmt.setString(5, searchQuery);
            stmt.setString(6, searchQuery);
            stmt.setString(7, searchQuery);
            stmt.setString(8, searchQuery);

            try (ResultSet result = stmt.executeQuery()) {
                while (result.next()) {
                    SuratKeluar surat = new SuratKeluar();
                    surat.setIdSurat(result.getInt("id_surat"));
                    surat.setNoAgenda(result.getString("no_agenda"));
                    surat.setTujuan(result.getString("tujuan"));
                    surat.setNoSurat(result.getString("no_surat"));
                    surat.setIsi(result.getString("isi"));
                    surat.setKode(result.getString("kode"));
                    surat.setTglSurat(result.getDate("tgl_surat"));
                    surat.setFilePath(result.getString("file_path"));
                    surat.setKeterangan(result.getString("keterangan"));
                    surat.setIdUser(result.getInt("id_user"));
                    listSurat.add(surat);
                }
            }
        } catch (SQLException e) {
            System.out.println("Search failed (SuratKeluar): " + e.getMessage());
            throw e;
        }

        return listSurat;
    }
}