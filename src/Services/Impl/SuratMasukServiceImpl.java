package Services.Impl;

import Models.SuratMasuk;
import Repositorys.SuratMasukRepository; 
import Services.SuratMasukService;    
import java.sql.SQLException;
import java.util.List;

public class SuratMasukServiceImpl implements SuratMasukService { 
    
    private SuratMasukRepository suratMasukRepo; 
    
    public SuratMasukServiceImpl() {
        this.suratMasukRepo = new SuratMasukRepository(); 
    }

    @Override
    public void createSuratMasuk(SuratMasuk surat) throws Exception {
        
        if (surat.getIdSurat() <= 0) {
            throw new Exception("Letter ID must be a positive number (greater than 0).");
        }
        
        if (surat.getNoAgenda() == null || surat.getNoAgenda().trim().isEmpty()) {
            throw new Exception("Agenda Number cannot be empty!");    
        }
        if (surat.getAsalSurat() == null || surat.getAsalSurat().trim().isEmpty()) {
            throw new Exception("Letter Origin cannot be empty!");    
        }
        if (surat.getNoSurat() == null || surat.getNoSurat().trim().isEmpty()) {
            throw new Exception("Letter Number cannot be empty!");    
        }

        if (surat.getTglSurat() == null) {
            throw new Exception("Letter Date cannot be empty!");
        }
        if (surat.getTglDiterima() == null) {
            throw new Exception("Received Date cannot be empty!");
        }

        if (surat.getIsi() == null || surat.getIsi().trim().isEmpty()) {
            throw new Exception("Content/Body cannot be empty!");
        }

        if (surat.getKode() == null || surat.getKode().trim().isEmpty() || surat.getKode().startsWith("Pilih")) {
            throw new Exception("Classification Code must be selected!");
        }

        if (surat.getIndeks() == null || surat.getIndeks().trim().isEmpty() || surat.getIndeks().startsWith("Pilih")) {
            throw new Exception("Index must be selected!");
        }

        if (surat.getIdUser() <= 0) {
            throw new Exception("User must be selected!");
        }

        try {
            SuratMasuk suratExisting = suratMasukRepo.getById(surat.getIdSurat());

            if (suratExisting != null) {
                throw new Exception("Letter ID " + surat.getIdSurat() + " is already in use. Please use another ID.");
            }

            suratMasukRepo.createSuratMasuk(surat); 

        } catch (SQLException except) {
            if (except.getMessage().contains("Duplicate entry")) {
                 throw new Exception("Letter ID " + surat.getIdSurat() + " already exists (Database Error).");
            }
            throw new Exception("Failed to save to Database: " + except.getMessage());
        }
    }

    @Override
    public List<SuratMasuk> getAllSuratMasuk() throws Exception {
        try {
            return suratMasukRepo.getAll(); 
        } catch (SQLException e) {
            throw new Exception("Failed Get All Data " + e.getMessage());
        }
    }

    @Override
    public void updateSuratMasuk(SuratMasuk suratDariForm) throws Exception {

        if (suratDariForm.getIdSurat() <= 0) {
            throw new Exception("Invalid Letter ID for update!");
        }

        SuratMasuk dataOld = suratMasukRepo.getById(suratDariForm.getIdSurat());
        if (dataOld == null) {
            throw new Exception("Letter with ID " + suratDariForm.getIdSurat() + " not found. Cannot update.");
        }

        if (suratDariForm.getNoAgenda() != null && !suratDariForm.getNoAgenda().trim().isEmpty()) {
            dataOld.setNoAgenda(suratDariForm.getNoAgenda());
        }

        if (suratDariForm.getAsalSurat() != null && !suratDariForm.getAsalSurat().trim().isEmpty()) {
            dataOld.setAsalSurat(suratDariForm.getAsalSurat());
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

        if (suratDariForm.getIndeks() != null && !suratDariForm.getIndeks().trim().isEmpty() && !suratDariForm.getIndeks().startsWith("Pilih")) {
            dataOld.setIndeks(suratDariForm.getIndeks());
        }

        if (suratDariForm.getTglSurat() != null) {
            dataOld.setTglSurat(suratDariForm.getTglSurat());
        }
        if (suratDariForm.getTglDiterima() != null) {
            dataOld.setTglDiterima(suratDariForm.getTglDiterima());
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
            suratMasukRepo.updateSuratMasuk(dataOld); 
        } catch (SQLException except) {
            throw new Exception("Failed to update data in the database: " + except.getMessage());
        }
    }
    

    @Override
    public void deleteSuratMasuk(int idSurat) throws Exception {
        if (idSurat <= 0) {
            throw new Exception("ID Not Valid For Deleting");
        }
        
        try {
            suratMasukRepo.deleteSuratMasuk(idSurat); 
        } catch (SQLException except) {
            throw new Exception("Failed To Delete Data: " + except.getMessage());
        }
    }
    

    @Override
    public List<SuratMasuk> searchSuratMasuk(String query) throws Exception {

        if (query == null || query.trim().isEmpty()) {
            return suratMasukRepo.getAll();
        }
        try {
            return suratMasukRepo.search(query);
        } catch (SQLException e) {
            throw new Exception("Failed to search data: " + e.getMessage());
        }
    }
    
}