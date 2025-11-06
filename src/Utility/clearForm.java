/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

/**
 *
 * @author szaiii
 */
public class clearForm {
    
    public static void clearSuratMasukForm(
        JTextField txtIdSurat,
        JTextField txtNoAgenda,
        JTextField txtAsalSurat,
        JTextField txtNoSurat,
        JTextArea areaIsi,
        JComboBox cmbKode,
        JComboBox cmbIndex,
        JDateChooser dateSurat,
        JDateChooser dateTerima,
        JTextField txtFile, 
        JTextArea areaKeterangan,
        JComboBox cmbIdUser
    ) {
        // 1. Bersihkan semua Text Field
        txtIdSurat.setText("");
        txtNoAgenda.setText("");
        txtAsalSurat.setText("");
        txtNoSurat.setText("");
        txtFile.setText("");

        // 2. Bersihkan semua Text Area
        areaIsi.setText("");
        areaKeterangan.setText("");

        // 3. Reset semua Combo Box ke item pertama (index 0)
        cmbKode.setSelectedIndex(0);
        cmbIndex.setSelectedIndex(0);
        cmbIdUser.setSelectedIndex(0);

        // 4. Reset tanggal ke hari ini
        dateSurat.setDate(new java.util.Date());
        dateTerima.setDate(new java.util.Date());
       
    }
    
    public static void clearSuratKeluarForm(
        JTextField txtIdKeluar,
        JTextField txtNoAgendaKeluar,
        JTextField txtTujuanKeluar,
        JTextField txtNoSuratKeluar,
        JTextArea areaIsiKeluar,
        JComboBox cmbKodeKeluar,
        JDateChooser dateSuratKeluar,
        JTextField txtFileKeluar,
        JTextArea areaKeteranganKeluar,
        JComboBox cmbIdUserKeluar
    ) {
        txtIdKeluar.setText("");
        txtNoAgendaKeluar.setText("");
        txtTujuanKeluar.setText("");
        txtNoSuratKeluar.setText("");
        txtFileKeluar.setText("");

        areaIsiKeluar.setText("");
        areaKeteranganKeluar.setText("");

        cmbKodeKeluar.setSelectedIndex(0);
        cmbIdUserKeluar.setSelectedIndex(0);

        dateSuratKeluar.setDate(new java.util.Date());

        txtIdKeluar.requestFocusInWindow();
    }
    

    public static void clearDisposisiForm(
        JTextField txtIdDisposisi,
        JComboBox cmbIdSuratDisposisi,
        JTextField txtTujuanDisposisi,
        JTextArea areaIsiDisposisi,
        JComboBox cmbSifatDisposisi,
        JDateChooser dateBatasWaktuDisposisi,
        JTextArea areaCatatanDisposisi,
        JComboBox cmbIdUserDisposisi
    ) {
        txtIdDisposisi.setText("");
        txtTujuanDisposisi.setText("");
        areaIsiDisposisi.setText("");
        areaCatatanDisposisi.setText("");

        cmbIdSuratDisposisi.setSelectedIndex(0);
        cmbSifatDisposisi.setSelectedIndex(0);
        cmbIdUserDisposisi.setSelectedIndex(0);

        dateBatasWaktuDisposisi.setDate(new java.util.Date());

        txtIdDisposisi.requestFocusInWindow();
    }


    public static void clearInstansiForm(
        JTextField txtIdInstansi,
        JTextField txtNamaInstansi,
        JTextArea areaAlamatInstansi,
        JTextField txtTelponInstansi,
        JTextField txtWebsiteInstansi,
        JTextField txtEmailInstansi,
        JTextField txtLogoPathInstansi
    ) {
        txtIdInstansi.setText("");
        txtNamaInstansi.setText("");
        areaAlamatInstansi.setText("");
        txtTelponInstansi.setText("");
        txtWebsiteInstansi.setText("");
        txtEmailInstansi.setText("");
        txtLogoPathInstansi.setText("");

        txtIdInstansi.requestFocusInWindow();
    }
}
