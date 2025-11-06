/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Models.SuratKeluar;
import java.util.List;
/**
 *
 * @author szaiii
 */
public interface SuratKeluarService {
    
    void createSuratKeluar(SuratKeluar surat) throws Exception; 
    List<SuratKeluar> getAllSuratKeluar() throws Exception; 
    void updateSuratKeluar(SuratKeluar surat) throws Exception; 
    void deleteSuratKeluar(int idSurat) throws Exception; 
    List<SuratKeluar> searchSuratKeluar(String query) throws Exception;
    
}