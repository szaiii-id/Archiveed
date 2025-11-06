package Services.Impl;

import Models.SuratKeluar;
import Repositorys.SuratKeluarRepository;
import Services.SuratKeluarService;
import java.sql.SQLException;
import java.util.List;

public class SuratKeluarServiceImpl implements SuratKeluarService {
    
    private SuratKeluarRepository suratKeluarRepo;
    
    public SuratKeluarServiceImpl() {
        this.suratKeluarRepo = new SuratKeluarRepository();
    }

    @Override
    public void createSuratKeluar(SuratKeluar surat) throws Exception {
        
        if (surat.getIdSurat() <= 0) {
            throw new Exception("Letter ID must be a positive number.");
        }
        if (surat.getNoAgenda() == null || surat.getNoAgenda().trim().isEmpty()) {
            throw new Exception("Agenda Number cannot be empty!");    
        }
        if (surat.getTujuan() == null || surat.getTujuan().trim().isEmpty()) {
            throw new Exception("Destination cannot be empty!");    
        }
        if (surat.getNoSurat() == null || surat.getNoSurat().trim().isEmpty()) {
            throw new Exception("Letter Number cannot be empty!");    
        }
        if (surat.getIsi() == null || surat.getIsi().trim().isEmpty()) {
            throw new Exception("Content/Body cannot be empty!");
        }
        if (surat.getKode() == null || surat.getKode().trim().isEmpty() || surat.getKode().startsWith("Pilih")) {
            throw new Exception("Classification Code must be selected!");
        }
        if (surat.getTglSurat() == null) {
            throw new Exception("Letter Date cannot be empty!");
        }
        if (surat.getIdUser() <= 0) {
            throw new Exception("User must be selected!");
        }
        
        try {
            // Pengecekan internal ini TETAP ADA
            SuratKeluar existing = suratKeluarRepo.getById(surat.getIdSurat());
            if (existing != null) {
                throw new Exception("Letter ID " + surat.getIdSurat() + " is already in use.");
            }
            suratKeluarRepo.createSuratKeluar(surat);
            
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                 throw new Exception("Letter ID " + surat.getIdSurat() + " already exists (Database Error).");
            }
            throw new Exception("Failed to save to Database: " + e.getMessage());
        }
    }

    @Override
    public void updateSuratKeluar(SuratKeluar suratDariForm) throws Exception {

        if (suratDariForm.getIdSurat() <= 0) {
            throw new Exception("Invalid Letter ID for update!");
        }

        SuratKeluar dataOld = suratKeluarRepo.getById(suratDariForm.getIdSurat());
        if (dataOld == null) {
            throw new Exception("Letter with ID " + suratDariForm.getIdSurat() + " not found. Cannot update.");
        }

        if (suratDariForm.getNoAgenda() != null && !suratDariForm.getNoAgenda().trim().isEmpty()) {
            dataOld.setNoAgenda(suratDariForm.getNoAgenda());    
        }
        if (suratDariForm.getTujuan() != null && !suratDariForm.getTujuan().trim().isEmpty()) {
            dataOld.setTujuan(suratDariForm.getTujuan());    
        }
        if (suratDariForm.getNoSurat() != null && !suratDariForm.getNoSurat().trim().isEmpty()) {
            dataOld.setNoSurat(suratDariForm.getNoSurat());    
        }
        if (suratDariForm.getIsi() != null && !suratDariForm.getIsi().trim().isEmpty()) {
            dataOld.setIsi(suratDariForm.getIsi());
        }
        if (suratDariForm.getKode() != null && !suratDariForm.getKode().trim().isEmpty() && !suratDariForm.getKode().startsWith("Pilih")) {
            dataOld.setKode(suratDariForm.getKode());
        }
        if (suratDariForm.getTglSurat() != null) {
            dataOld.setTglSurat(suratDariForm.getTglSurat());
        }
        if (suratDariForm.getFilePath() != null) {
            dataOld.setFilePath(suratDariForm.getFilePath());
        }
        if (suratDariForm.getKeterangan() != null) {
            dataOld.setKeterangan(suratDariForm.getKeterangan());
        }
        if (suratDariForm.getIdUser() > 0) {
            dataOld.setIdUser(suratDariForm.getIdUser());
        }

        try {
            suratKeluarRepo.updateSuratKeluar(dataOld);
        } catch (SQLException e) {
            throw new Exception("Failed to update data: " + e.getMessage());
        }
    }

    @Override
    public void deleteSuratKeluar(int idSurat) throws Exception {
        if (idSurat <= 0) {
            throw new Exception("ID Not Valid For Deleting");
        }
        try {
            suratKeluarRepo.deleteSuratKeluar(idSurat);
        } catch (SQLException e) {
            throw new Exception("Failed To Delete Data: " + e.getMessage());
        }
    }

    @Override
    public List<SuratKeluar> getAllSuratKeluar() throws Exception {
        try {
            return suratKeluarRepo.getAll();
        } catch (SQLException e) {
            throw new Exception("Failed to get all data: " + e.getMessage());
        }
    }
    
    @Override
    public List<SuratKeluar> searchSuratKeluar(String query) throws Exception {
        if (query == null || query.trim().isEmpty()) {
            return suratKeluarRepo.getAll();
        }
        try {
            return suratKeluarRepo.search(query);
        } catch (SQLException e) {
            throw new Exception("Failed to search data: " + e.getMessage());
        }
    }

  
}