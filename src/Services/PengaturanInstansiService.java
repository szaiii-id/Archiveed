/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Models.PengaturanInstansi;
import java.util.List;
/**
 *
 * @author szaiii
 */
public interface PengaturanInstansiService {
    
    void createInstansi(PengaturanInstansi instansi) throws Exception; 
    List<PengaturanInstansi> getAllInstansi() throws Exception; 
    void updateInstansi(PengaturanInstansi instansi) throws Exception; 
    void deleteInstansi(int idInstansi) throws Exception; 
    List<PengaturanInstansi> searchInstansi(String query) throws Exception;
    
}