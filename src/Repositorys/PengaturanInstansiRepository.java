package Repositorys;

import Database.ConnectionDB;
import Models.PengaturanInstansi;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class PengaturanInstansiRepository {
    
    public void createPengaturanInstansi(PengaturanInstansi instansi) throws SQLException {
        String sql = "INSERT INTO pengaturan_instansi(id_instansi, nama_instansi, alamat, " +
                     "telpon, website, email, logo_path) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = ConnectionDB.getConnection();
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, instansi.getIdInstansi());
            stmt.setString(2, instansi.getNamaInstansi());
            stmt.setString(3, instansi.getAlamat());
            stmt.setString(4, instansi.getTelpon());
            stmt.setString(5, instansi.getWebsite());
            stmt.setString(6, instansi.getEmail());
            stmt.setString(7, instansi.getLogoPath());
            
            stmt.executeUpdate();
        } catch (SQLException except) {
            System.out.println("Failed Create Instansi: " + except.getMessage());
            throw except;
        }    
    }
    
    public void updatePengaturanInstansi(PengaturanInstansi instansi) throws SQLException {
        String sql = "UPDATE pengaturan_instansi SET nama_instansi = ?, alamat = ?, " +
                     "telpon = ?, website = ?, email = ?, logo_path = ? " +
                     "WHERE id_instansi = ?";
        
        Connection connection = ConnectionDB.getConnection();
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, instansi.getNamaInstansi());
            stmt.setString(2, instansi.getAlamat());
            stmt.setString(3, instansi.getTelpon());
            stmt.setString(4, instansi.getWebsite());
            stmt.setString(5, instansi.getEmail());
            stmt.setString(6, instansi.getLogoPath());
            stmt.setInt(7, instansi.getIdInstansi());
            
            stmt.executeUpdate();
        } catch (SQLException except) {
            System.out.println("Failed Update Instansi: " + except.getMessage());
            throw except;
        }    
    }
    
    public void deletePengaturanInstansi(int idInstansi) throws SQLException {
        String sql = "DELETE FROM pengaturan_instansi WHERE id_instansi = ?";
        Connection connection = ConnectionDB.getConnection();
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idInstansi);
            stmt.executeUpdate();
        } catch (SQLException except) {
            System.out.println("Failed Delete Instansi: " + except.getMessage());
            throw except;
        }    
    }
    
    public PengaturanInstansi getById(int idInstansi) throws SQLException {
        String sql = "SELECT * FROM pengaturan_instansi WHERE id_instansi = ?";
        
        PengaturanInstansi instansi = null;
        Connection connection = ConnectionDB.getConnection();
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idInstansi);
            
            try(ResultSet result = stmt.executeQuery()){
                if (result.next()) {
                    instansi = new PengaturanInstansi();
                    instansi.setIdInstansi(result.getInt("id_instansi"));
                    instansi.setNamaInstansi(result.getString("nama_instansi"));
                    instansi.setAlamat(result.getString("alamat"));
                    instansi.setTelpon(result.getString("telpon"));
                    instansi.setWebsite(result.getString("website"));
                    instansi.setEmail(result.getString("email"));
                    instansi.setLogoPath(result.getString("logo_path"));
                }
            }
        } catch (SQLException except) {
            System.out.println("Instansi Not Found: " + except.getMessage());
            throw except;
        }
        
        return instansi;
    }
    
    public List<PengaturanInstansi> getAll() throws SQLException {
        String sql = "SELECT * FROM pengaturan_instansi";
        List<PengaturanInstansi> listInstansi = new ArrayList<>();
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                PengaturanInstansi instansi = new PengaturanInstansi();
                instansi.setIdInstansi(result.getInt("id_instansi"));
                instansi.setNamaInstansi(result.getString("nama_instansi"));
                instansi.setAlamat(result.getString("alamat"));
                instansi.setTelpon(result.getString("telpon"));
                instansi.setWebsite(result.getString("website"));
                instansi.setEmail(result.getString("email"));
                instansi.setLogoPath(result.getString("logo_path"));
                listInstansi.add(instansi);
            }
        } catch (SQLException except) {
            System.out.println("List Instansi Not Found" + except.getMessage());
            throw except;
        }
        return listInstansi;
    }
    
    public List<PengaturanInstansi> search(String query) throws SQLException {
    
        String sql = "SELECT * FROM pengaturan_instansi WHERE " +
                     "id_instansi LIKE ? OR " +
                     "nama_instansi LIKE ? OR " +
                     "alamat LIKE ? OR " +
                     "telpon LIKE ? OR " +
                     "website LIKE ? OR " +
                     "email LIKE ? OR " +
                     "logo_path LIKE ?";

        List<PengaturanInstansi> listInstansi = new ArrayList<>();
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


            try (ResultSet result = stmt.executeQuery()) {
                while (result.next()) {
                    PengaturanInstansi instansi = new PengaturanInstansi();
                    instansi.setIdInstansi(result.getInt("id_instansi"));
                    instansi.setNamaInstansi(result.getString("nama_instansi"));
                    instansi.setAlamat(result.getString("alamat"));
                    instansi.setTelpon(result.getString("telpon"));
                    instansi.setWebsite(result.getString("website"));
                    instansi.setEmail(result.getString("email"));
                    instansi.setLogoPath(result.getString("logo_path"));
                    listInstansi.add(instansi);
                }
            }
        } catch (SQLException e) {
            System.out.println("Search failed (Instansi): " + e.getMessage());
            throw e;
        }

        return listInstansi;
    }
}