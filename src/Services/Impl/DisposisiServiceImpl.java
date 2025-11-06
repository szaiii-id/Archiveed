package Services.Impl;

import Models.Disposisi;
import Repositorys.DisposisiRepository;
import Services.DisposisiService;
import java.sql.SQLException;
import java.util.List;

public class DisposisiServiceImpl implements DisposisiService {
    
    private DisposisiRepository disposisiRepo;
    
    public DisposisiServiceImpl() {
        this.disposisiRepo = new DisposisiRepository();
    }

    @Override
    public void createDisposisi(Disposisi disposisi) throws Exception {
        
        if (disposisi.getIdDisposisi() <= 0) {
            throw new Exception("Disposition ID must be a positive number.");
        }
        if (disposisi.getIdSurat() <= 0) {
            throw new Exception("Letter ID must be selected.");
        }
        if (disposisi.getTujuan() == null || disposisi.getTujuan().trim().isEmpty()) {
            throw new Exception("Destination cannot be empty!");    
        }
        if (disposisi.getIsiDisposisi() == null || disposisi.getIsiDisposisi().trim().isEmpty()) {
            throw new Exception("Disposition Content cannot be empty!");    
        }
        if (disposisi.getSifat() == null || disposisi.getSifat().trim().isEmpty() || disposisi.getSifat().startsWith("Pilih")) {
            throw new Exception("Disposition Type must be selected!");
        }
        if (disposisi.getBatasWaktu() == null) {
            throw new Exception("Due Date cannot be empty!");
        }
        if (disposisi.getIdUser() <= 0) {
            throw new Exception("User must be selected!");
        }

        try {
            Disposisi existing = disposisiRepo.getById(disposisi.getIdDisposisi());
            if (existing != null) {
                throw new Exception("Disposition ID " + disposisi.getIdDisposisi() + " is already in use.");
            }
            disposisiRepo.createDisposisi(disposisi);
            
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                 throw new Exception("Disposition ID " + disposisi.getIdDisposisi() + " already exists (Database Error).");
            }
            throw new Exception("Failed to save to Database: " + e.getMessage());
        }
    }

    @Override
    public void updateDisposisi(Disposisi disposisiDariForm) throws Exception {

        if (disposisiDariForm.getIdDisposisi() <= 0) {
            throw new Exception("Invalid Disposition ID for update!");
        }

        Disposisi dataOld = disposisiRepo.getById(disposisiDariForm.getIdDisposisi());
        if (dataOld == null) {
            throw new Exception("Disposition with ID " + disposisiDariForm.getIdDisposisi() + " not found. Cannot update.");
        }

        if (disposisiDariForm.getIdSurat() > 0) {
            dataOld.setIdSurat(disposisiDariForm.getIdSurat());
        }
        if (disposisiDariForm.getTujuan() != null && !disposisiDariForm.getTujuan().trim().isEmpty()) {
            dataOld.setTujuan(disposisiDariForm.getTujuan());    
        }
        if (disposisiDariForm.getIsiDisposisi() != null && !disposisiDariForm.getIsiDisposisi().trim().isEmpty()) {
            dataOld.setIsiDisposisi(disposisiDariForm.getIsiDisposisi());    
        }
        if (disposisiDariForm.getSifat() != null && !disposisiDariForm.getSifat().trim().isEmpty() && !disposisiDariForm.getSifat().startsWith("Pilih")) {
            dataOld.setSifat(disposisiDariForm.getSifat());
        }
        if (disposisiDariForm.getBatasWaktu() != null) {
            dataOld.setBatasWaktu(disposisiDariForm.getBatasWaktu());
        }
        if (disposisiDariForm.getCatatan() != null) {
            dataOld.setCatatan(disposisiDariForm.getCatatan());
        }
        if (disposisiDariForm.getIdUser() > 0) {
            dataOld.setIdUser(disposisiDariForm.getIdUser());
        }

        try {
            disposisiRepo.updateDisposisi(dataOld);
        } catch (SQLException e) {
            throw new Exception("Failed to update data: " + e.getMessage());
        }
    }

    @Override
    public void deleteDisposisi(int idDisposisi) throws Exception {
        if (idDisposisi <= 0) {
            throw new Exception("ID Not Valid For Deleting");
        }
        try {
            disposisiRepo.deleteDisposisi(idDisposisi);
        } catch (SQLException e) {
            throw new Exception("Failed To Delete Data: " + e.getMessage());
        }
    }

    @Override
    public List<Disposisi> getAllDisposisi() throws Exception {
        try {
            return disposisiRepo.getAll();
        } catch (SQLException e) {
            throw new Exception("Failed to get all data: " + e.getMessage());
        }
    }
    
    @Override
    public List<Disposisi> searchDisposisi(String query) throws Exception {
        if (query == null || query.trim().isEmpty()) {
            return disposisiRepo.getAll();
        }
        try {
            return disposisiRepo.search(query);
        } catch (SQLException e) {
            throw new Exception("Failed to search data: " + e.getMessage());
        }
    }
}