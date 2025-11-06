/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Models.Disposisi;
import java.util.List;
/**
 *
 * @author szaiii
 */
public interface DisposisiService {
    
    void createDisposisi(Disposisi disposisi) throws Exception; 
    List<Disposisi> getAllDisposisi() throws Exception; 
    void updateDisposisi(Disposisi disposisi) throws Exception; 
    void deleteDisposisi(int idDisposisi) throws Exception; 
    List<Disposisi> searchDisposisi(String query) throws Exception;
}

