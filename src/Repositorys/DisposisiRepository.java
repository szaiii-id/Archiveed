package Repositorys;

import Database.ConnectionDB;
import Models.Disposisi;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;
import java.util.ArrayList;

public class DisposisiRepository {
    
    public void createDisposisi(Disposisi disposisi) throws SQLException {
        String sql = "INSERT INTO disposisi(id_disposisi, id_surat, tujuan, isi_disposisi, " +
                     "sifat, batas_waktu, catatan, id_user) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = ConnectionDB.getConnection();
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, disposisi.getIdDisposisi());
            stmt.setInt(2, disposisi.getIdSurat());
            stmt.setString(3, disposisi.getTujuan());
            stmt.setString(4, disposisi.getIsiDisposisi());
            stmt.setString(5, disposisi.getSifat());
            stmt.setDate(6, disposisi.getBatasWaktu());
            stmt.setString(7, disposisi.getCatatan());
            stmt.setInt(8, disposisi.getIdUser());
            
            stmt.executeUpdate();
        } catch (SQLException except) {
            System.out.println("Failed Create Disposisi: " + except.getMessage());
            throw except;
        }    
    }
    
    public void updateDisposisi(Disposisi disposisi) throws SQLException {
        String sql = "UPDATE disposisi SET id_surat = ?, tujuan = ?, isi_disposisi = ?, " +
                     "sifat = ?, batas_waktu = ?, catatan = ?, id_user = ? " +
                     "WHERE id_disposisi = ?";
        
        Connection connection = ConnectionDB.getConnection();
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, disposisi.getIdSurat());
            stmt.setString(2, disposisi.getTujuan());
            stmt.setString(3, disposisi.getIsiDisposisi());
            stmt.setString(4, disposisi.getSifat());
            stmt.setDate(5, disposisi.getBatasWaktu());
            stmt.setString(6, disposisi.getCatatan());
            stmt.setInt(7, disposisi.getIdUser());
            stmt.setInt(8, disposisi.getIdDisposisi());
            
            stmt.executeUpdate();
        } catch (SQLException except) {
            System.out.println("Failed Update Disposisi: " + except.getMessage());
            throw except;
        }    
    }
    
    public void deleteDisposisi(int idDisposisi) throws SQLException {
        String sql = "DELETE FROM disposisi WHERE id_disposisi = ?";
        Connection connection = ConnectionDB.getConnection();
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idDisposisi);
            stmt.executeUpdate();
        } catch (SQLException except) {
            System.out.println("Failed Delete Disposisi: " + except.getMessage());
            throw except;
        }    
    }
    
    public Disposisi getById(int idDisposisi) throws SQLException {
        String sql = "SELECT * FROM disposisi WHERE id_disposisi = ?";
        
        Disposisi disposisi = null;
        Connection connection = ConnectionDB.getConnection();
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idDisposisi);
            
            try(ResultSet result = stmt.executeQuery()){
                if (result.next()) {
                    disposisi = new Disposisi();
                    disposisi.setIdDisposisi(result.getInt("id_disposisi"));
                    disposisi.setIdSurat(result.getInt("id_surat"));
                    disposisi.setTujuan(result.getString("tujuan"));
                    disposisi.setIsiDisposisi(result.getString("isi_disposisi"));
                    disposisi.setSifat(result.getString("sifat"));
                    disposisi.setBatasWaktu(result.getDate("batas_waktu"));
                    disposisi.setCatatan(result.getString("catatan"));
                    disposisi.setIdUser(result.getInt("id_user"));
                }
            }
        } catch (SQLException except) {
            System.out.println("Disposisi Not Found: " + except.getMessage());
            throw except;
        }
        
        return disposisi;
    }
    
    public List<Disposisi> getAll() throws SQLException {
        String sql = "SELECT * FROM disposisi";
        List<Disposisi> listDisposisi = new ArrayList<>();
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                Disposisi disposisi = new Disposisi();
                disposisi.setIdDisposisi(result.getInt("id_disposisi"));
                disposisi.setIdSurat(result.getInt("id_surat"));
                disposisi.setTujuan(result.getString("tujuan"));
                disposisi.setIsiDisposisi(result.getString("isi_disposisi"));
                disposisi.setSifat(result.getString("sifat"));
                disposisi.setBatasWaktu(result.getDate("batas_waktu"));
                disposisi.setCatatan(result.getString("catatan"));
                disposisi.setIdUser(result.getInt("id_user"));
                listDisposisi.add(disposisi);
            }
        } catch (SQLException except) {
            System.out.println("List Disposisi Not Found" + except.getMessage());
            throw except;
        }
        return listDisposisi;
    }
    
    public List<Disposisi> search(String query) throws SQLException {
        String sql = "SELECT * FROM disposisi WHERE " +
                     "id_disposisi LIKE ? OR " +
                     "tujuan LIKE ? OR " +
                     "isi_disposisi LIKE ? OR " +
                     "sifat LIKE ? OR " +
                     "catatan LIKE ?";

        List<Disposisi> listDisposisi = new ArrayList<>();
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
                    Disposisi disposisi = new Disposisi();
                    disposisi.setIdDisposisi(result.getInt("id_disposisi"));
                    disposisi.setIdSurat(result.getInt("id_surat"));
                    disposisi.setTujuan(result.getString("tujuan"));
                    disposisi.setIsiDisposisi(result.getString("isi_disposisi"));
                    disposisi.setSifat(result.getString("sifat"));
                    disposisi.setBatasWaktu(result.getDate("batas_waktu"));
                    disposisi.setCatatan(result.getString("catatan"));
                    disposisi.setIdUser(result.getInt("id_user"));
                    listDisposisi.add(disposisi);
                }
            }
        } catch (SQLException e) {
            System.out.println("Search failed (Disposisi): " + e.getMessage());
            throw e;
        }

        return listDisposisi;
    }
}