package Services.Impl;

import Models.PengaturanInstansi;
import Repositorys.PengaturanInstansiRepository;
import Services.PengaturanInstansiService; 
import java.sql.SQLException;
import java.util.List;


public class PengaturanInstansiServiceImpl implements PengaturanInstansiService {
    
    private PengaturanInstansiRepository instansiRepo;
    
    public PengaturanInstansiServiceImpl() {
        this.instansiRepo = new PengaturanInstansiRepository();
    }

    @Override
    public void createInstansi(PengaturanInstansi instansi) throws Exception {
        
        if (instansi.getIdInstansi() <= 0) {
            throw new Exception("Institute ID must be a positive number.");
        }
        if (instansi.getNamaInstansi() == null || instansi.getNamaInstansi().trim().isEmpty()) {
            throw new Exception("Institute Name cannot be empty!");    
        }
        if (instansi.getAlamat() == null || instansi.getAlamat().trim().isEmpty()) {
            throw new Exception("Address cannot be empty!");    
        }
        if (instansi.getTelpon() == null || instansi.getTelpon().trim().isEmpty()) {
            throw new Exception("Phone Number cannot be empty!");    
        }
        if (instansi.getEmail() == null || instansi.getEmail().trim().isEmpty()) {
            throw new Exception("Email cannot be empty!");    
        }

        try {
            PengaturanInstansi existing = instansiRepo.getById(instansi.getIdInstansi());
            if (existing != null) {
                throw new Exception("Institute ID " + instansi.getIdInstansi() + " is already in use.");
            }
            
            instansiRepo.createPengaturanInstansi(instansi);
            
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                 throw new Exception("Institute ID " + instansi.getIdInstansi() + " already exists (Database Error).");
            }
            throw new Exception("Failed to save to Database: " + e.getMessage());
        }
    }

    @Override
    public void updateInstansi(PengaturanInstansi instansiDariForm) throws Exception {

        if (instansiDariForm.getIdInstansi() <= 0) {
            throw new Exception("Invalid Institute ID for update!");
        }

        PengaturanInstansi dataOld = instansiRepo.getById(instansiDariForm.getIdInstansi());
        if (dataOld == null) {
            throw new Exception("Institute with ID " + instansiDariForm.getIdInstansi() + " not found. Cannot update.");
        }

        if (instansiDariForm.getNamaInstansi() != null && !instansiDariForm.getNamaInstansi().trim().isEmpty()) {
            dataOld.setNamaInstansi(instansiDariForm.getNamaInstansi());    
        }
        if (instansiDariForm.getAlamat() != null && !instansiDariForm.getAlamat().trim().isEmpty()) {
            dataOld.setAlamat(instansiDariForm.getAlamat());    
        }
        if (instansiDariForm.getTelpon() != null && !instansiDariForm.getTelpon().trim().isEmpty()) {
            dataOld.setTelpon(instansiDariForm.getTelpon());    
        }
        if (instansiDariForm.getEmail() != null && !instansiDariForm.getEmail().trim().isEmpty()) {
            dataOld.setEmail(instansiDariForm.getEmail());    
        }
        if (instansiDariForm.getWebsite() != null) {
            dataOld.setWebsite(instansiDariForm.getWebsite());
        }
        if (instansiDariForm.getLogoPath() != null) {
            dataOld.setLogoPath(instansiDariForm.getLogoPath());
        }

        try {
            instansiRepo.updatePengaturanInstansi(dataOld);
        } catch (SQLException e) {
            throw new Exception("Failed to update data: " + e.getMessage());
        }
    }

    @Override
    public void deleteInstansi(int idInstansi) throws Exception {
        if (idInstansi <= 0) {
            throw new Exception("ID Not Valid For Deleting");
        }
        try {

            instansiRepo.deletePengaturanInstansi(idInstansi);
        } catch (SQLException e) {
            throw new Exception("Failed To Delete Data: " + e.getMessage());
        }
    }

    @Override
    public List<PengaturanInstansi> getAllInstansi() throws Exception {
        try {
            return instansiRepo.getAll();
        } catch (SQLException e) {
            throw new Exception("Failed to get all data: " + e.getMessage());
        }
    }
    
    @Override
    public List<PengaturanInstansi> searchInstansi(String query) throws Exception {
        if (query == null || query.trim().isEmpty()) {
            return instansiRepo.getAll();
        }
        try {
            return instansiRepo.search(query);
        } catch (SQLException e) {
            throw new Exception("Failed to search data: " + e.getMessage());
        }
    }
}