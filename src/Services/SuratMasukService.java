/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Models.SuratMasuk;
import java.util.List;
/**
 *
 * @author szaiii
 */
public interface SuratMasukService {
    
    void createSuratMasuk(SuratMasuk surat) throws Exception; 
    List<SuratMasuk> getAllSuratMasuk() throws Exception; 
    void updateSuratMasuk(SuratMasuk surat) throws Exception; 
    void deleteSuratMasuk(int idSurat) throws Exception; 
    List<SuratMasuk> searchSuratMasuk(String query) throws Exception;
    
}