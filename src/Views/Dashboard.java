/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Services.Impl.SuratMasukServiceImpl;
import Services.SuratMasukService;
import Models.SuratMasuk;

import Models.SuratKeluar;
import Services.SuratKeluarService;
import Services.Impl.SuratKeluarServiceImpl;

import Models.Disposisi;
import Services.DisposisiService;
import Services.Impl.DisposisiServiceImpl;

import Models.PengaturanInstansi;
import Services.PengaturanInstansiService;
import Services.Impl.PengaturanInstansiServiceImpl;

import javax.swing.JOptionPane;

import java.io.InputStream;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.Connection;
import Database.ConnectionDB;

import Utility.clearForm;
import javax.swing.table.DefaultTableModel;
import java.util.List;


/**
 *
 * @author szaiii
 */
public class Dashboard extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Dashboard.class.getName());
    private SuratMasukService suratMasukService;
    private SuratKeluarService suratKeluarService;
    private DisposisiService disposisiService;
    private PengaturanInstansiService instansiService;
    
    private DefaultTableModel modelTabelSuratMasuk;
    private DefaultTableModel modelTabelSuratKeluar;
    private DefaultTableModel modelTabelDisposisi;
    private DefaultTableModel modelTabelInstansi;


    
    
    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        dateSurat.setDate(new java.util.Date());
        dateTerima.setDate(new java.util.Date());
        
        dateSuratKeluar.setDate(new java.util.Date());
        
        dateBatasWaktu.setDate(new java.util.Date());
        
        this.suratMasukService = new SuratMasukServiceImpl();
        this.suratKeluarService = new SuratKeluarServiceImpl();
        this.disposisiService = new DisposisiServiceImpl();
        this.instansiService = new PengaturanInstansiServiceImpl();
        
        this.loadDataSuratMasuk();
        this.loadDataSuratKeluar();
        this.loadDataDisposisi();
        this.loadDataInstansi();
    }
    
    private void clearSuratMasuk() {
        
        clearForm.clearSuratMasukForm(
            txtIdSurat,
            txtNoAgenda,
            txtAsalSurat,
            txtNoSurat,
            areaIsi,
            cmbKode,
            cmbIndex,
            dateSurat,
            dateTerima,
            txtFile, 
            areaKeterangan,
            cmbIdUser
        );
    }
    
    private void clearFormSuratKeluar() {
        clearForm.clearSuratKeluarForm(
            txtIdSuratKeluar,
            txtNoAgendaKeluar,
            txtTujuan,
            txtNoSuratKeluar,
            areaIsiKeluar,
            cmbKodeKeluar,
            dateSuratKeluar,
            txtFileKeluar,
            areaKeteranganKeluar,
            cmbIdUserKeluar
        );
    }
    
    


    private void clearFormDisposisi() {
        clearForm.clearDisposisiForm(
            txtIdDisposisi,
            cmbIdSuratDisposisi,
            txtTujuanDisposisi,
            areaIsiDisposisi,
            cmbSifatDisposisi,
            dateBatasWaktu,
            areaCatatanDisposisi,
            cmbIdUserDisposisi
        );
    }

    private void clearFormInstansi() {
        clearForm.clearInstansiForm(
            txtIdInstansi,
            txtNamaInstansi,
            areaAlamatINstansi,
            txtTelponInstansi,
            txtWebsiteInstansi,
            txtEmailInstansi,
            txtLogoInstansi
        );
    }
    
    
    private void loadDataSuratMasuk() {
    
        this.modelTabelSuratMasuk = new DefaultTableModel();

        this.modelTabelSuratMasuk.addColumn("ID");
        this.modelTabelSuratMasuk.addColumn("No. Agenda");
        this.modelTabelSuratMasuk.addColumn("Asal Surat");
        this.modelTabelSuratMasuk.addColumn("No. Surat");
        this.modelTabelSuratMasuk.addColumn("Perihal");
        this.modelTabelSuratMasuk.addColumn("Tgl. Diterima");

       
        tblSuratMasuk.setModel(this.modelTabelSuratMasuk);

        try {
            List<SuratMasuk> listSurat = suratMasukService.getAllSuratMasuk();

            for (SuratMasuk surat : listSurat) {
                Object[] rowData = {
                    surat.getIdSurat(),
                    surat.getNoAgenda(),
                    surat.getAsalSurat(),
                    surat.getNoSurat(),
                    surat.getIsi(), 
                    surat.getTglDiterima()
                };
                modelTabelSuratMasuk.addRow(rowData);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Failed to load incoming letter data: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
        
    private void loadDataSuratKeluar() {
    
        this.modelTabelSuratKeluar = new DefaultTableModel();

        this.modelTabelSuratKeluar.addColumn("ID");
        this.modelTabelSuratKeluar.addColumn("No. Agenda");
        this.modelTabelSuratKeluar.addColumn("Tujuan");
        this.modelTabelSuratKeluar.addColumn("No. Surat");
        this.modelTabelSuratKeluar.addColumn("Keterangan");
        this.modelTabelSuratKeluar.addColumn("Tgl. Surat");


        tblSuratKeluar.setModel(this.modelTabelSuratKeluar);

        try {
            List<SuratKeluar> listSurat = suratKeluarService.getAllSuratKeluar();

            for (SuratKeluar surat : listSurat) {
                Object[] rowData = {
                    surat.getIdSurat(),
                    surat.getNoAgenda(),
                    surat.getTujuan(),
                    surat.getNoSurat(),
                    surat.getIsi(), 
                    surat.getTglSurat()
                };
                modelTabelSuratKeluar.addRow(rowData);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Failed to load incoming letter data: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadDataDisposisi() {
        
        this.modelTabelDisposisi = new DefaultTableModel();

        this.modelTabelDisposisi.addColumn("ID");
        this.modelTabelDisposisi.addColumn("ID Surat");
        this.modelTabelDisposisi.addColumn("Tujuan");
        this.modelTabelDisposisi.addColumn("Isi");
        this.modelTabelDisposisi.addColumn("Sifat");
        this.modelTabelDisposisi.addColumn("Batas Waktu");
        this.modelTabelDisposisi.addColumn("Catatan");
        this.modelTabelDisposisi.addColumn("ID Surat");

        tblDisposisi.setModel(this.modelTabelDisposisi);

        try {
            List<Disposisi> listDisposisi = disposisiService.getAllDisposisi();

            for (Disposisi disposisi : listDisposisi) {
                Object[] rowData = {
                    disposisi.getIdDisposisi(),
                    disposisi.getIdSurat(),
                    disposisi.getTujuan(),
                    disposisi.getIsiDisposisi(),
                    disposisi.getSifat(), 
                    disposisi.getBatasWaktu(),
                    disposisi.getCatatan(),
                    disposisi.getIdUser()
                };
                modelTabelDisposisi.addRow(rowData);
            }
         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Failed to load incoming letter data: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadDataInstansi() {
        
        this.modelTabelInstansi = new DefaultTableModel();

        this.modelTabelInstansi.addColumn("ID");
        this.modelTabelInstansi.addColumn("Nama Instansi");
        this.modelTabelInstansi.addColumn("Telepon");
        this.modelTabelInstansi.addColumn("Alamat");
        this.modelTabelInstansi.addColumn("Email");
        this.modelTabelInstansi.addColumn("Website");
        this.modelTabelInstansi.addColumn("Logo");
       
        tblInstansi.setModel(this.modelTabelInstansi);

        try {
            List<PengaturanInstansi> listInstansi = instansiService.getAllInstansi();

            for (PengaturanInstansi instansi : listInstansi) {
                Object[] rowData = {
                    instansi.getIdInstansi(),
                    instansi.getNamaInstansi(),
                    instansi.getTelpon(),
                    instansi.getAlamat(),
                    instansi.getEmail(), 
                    instansi.getWebsite(),
                    instansi.getLogoPath(),
                };
                modelTabelInstansi.addRow(rowData);
            }
         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Failed to load incoming letter data: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackround = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnSuratMasuk = new javax.swing.JButton();
        btnSuratKeluar = new javax.swing.JButton();
        btnDisposisi = new javax.swing.JButton();
        btnInstansi = new javax.swing.JButton();
        Content = new javax.swing.JPanel();
        pnlSuratMasuk = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtIdSurat = new javax.swing.JTextField();
        txtNoAgenda = new javax.swing.JTextField();
        txtAsalSurat = new javax.swing.JTextField();
        txtNoSurat = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaIsi = new javax.swing.JTextArea();
        dateTerima = new com.toedter.calendar.JDateChooser();
        cmbKode = new javax.swing.JComboBox<>();
        cmbIndex = new javax.swing.JComboBox<>();
        dateSurat = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaKeterangan = new javax.swing.JTextArea();
        cmbIdUser = new javax.swing.JComboBox<>();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnRead = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtFile = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblSuratMasuk = new javax.swing.JTable();
        txtSearchSuratMasuk = new javax.swing.JTextField();
        pnlSuratKeluar = new javax.swing.JPanel();
        txtIdSuratKeluar = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtNoAgendaKeluar = new javax.swing.JTextField();
        txtTujuan = new javax.swing.JTextField();
        txtNoSuratKeluar = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaIsiKeluar = new javax.swing.JTextArea();
        cmbKodeKeluar = new javax.swing.JComboBox<>();
        dateSuratKeluar = new com.toedter.calendar.JDateChooser();
        txtFileKeluar = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        areaKeteranganKeluar = new javax.swing.JTextArea();
        cmbIdUserKeluar = new javax.swing.JComboBox<>();
        btnCreateKeluar = new javax.swing.JButton();
        btnUpdateKeluar = new javax.swing.JButton();
        btnDeleteKeluar = new javax.swing.JButton();
        btnReadKeluar = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblSuratKeluar = new javax.swing.JTable();
        txtSearchSuratKeluar = new javax.swing.JTextField();
        pnlDisposisi = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtIdDisposisi = new javax.swing.JTextField();
        cmbIdSuratDisposisi = new javax.swing.JComboBox<>();
        txtTujuanDisposisi = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        areaIsiDisposisi = new javax.swing.JTextArea();
        cmbSifatDisposisi = new javax.swing.JComboBox<>();
        dateBatasWaktu = new com.toedter.calendar.JDateChooser();
        jScrollPane6 = new javax.swing.JScrollPane();
        areaCatatanDisposisi = new javax.swing.JTextArea();
        cmbIdUserDisposisi = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        btnCreateDisposisi = new javax.swing.JButton();
        btnCUpdateDisposis = new javax.swing.JButton();
        btnDeleteDisposisi = new javax.swing.JButton();
        btnReadDisposisi = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblDisposisi = new javax.swing.JTable();
        txtSearchDisposisi = new javax.swing.JTextField();
        pnlInstansi = new javax.swing.JPanel();
        txtIdInstansi = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtEmailInstansi = new javax.swing.JTextField();
        txtNamaInstansi = new javax.swing.JTextField();
        txtWebsiteInstansi = new javax.swing.JTextField();
        txtTelponInstansi = new javax.swing.JTextField();
        txtLogoInstansi = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        areaAlamatINstansi = new javax.swing.JTextArea();
        btnCreateInstansi = new javax.swing.JButton();
        btnUpdateInstansi = new javax.swing.JButton();
        btnDeleteInstansi = new javax.swing.JButton();
        btnReadInstansi = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblInstansi = new javax.swing.JTable();
        txtSearchInstansi = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(23, 171, 60));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pnlBackround.setBackground(new java.awt.Color(60, 196, 119));

        jLabel1.setFont(new java.awt.Font("Adwaita Mono", 3, 36)); // NOI18N
        jLabel1.setText("Arsipin");

        btnSuratMasuk.setText("Surat Masuk");
        btnSuratMasuk.setPreferredSize(new java.awt.Dimension(150, 40));
        btnSuratMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuratMasukActionPerformed(evt);
            }
        });

        btnSuratKeluar.setText("Surat Keluar");
        btnSuratKeluar.setPreferredSize(new java.awt.Dimension(150, 40));
        btnSuratKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuratKeluarActionPerformed(evt);
            }
        });

        btnDisposisi.setText("Disposisi");
        btnDisposisi.setPreferredSize(new java.awt.Dimension(150, 40));
        btnDisposisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisposisiActionPerformed(evt);
            }
        });

        btnInstansi.setText("Instansi");
        btnInstansi.setPreferredSize(new java.awt.Dimension(150, 40));
        btnInstansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInstansiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBackroundLayout = new javax.swing.GroupLayout(pnlBackround);
        pnlBackround.setLayout(pnlBackroundLayout);
        pnlBackroundLayout.setHorizontalGroup(
            pnlBackroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackroundLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlBackroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(pnlBackroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnSuratMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSuratKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        pnlBackroundLayout.setVerticalGroup(
            pnlBackroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackroundLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(111, 111, 111)
                .addComponent(btnSuratMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuratKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(btnInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Content.setBackground(new java.awt.Color(255, 255, 255));
        Content.setLayout(new java.awt.CardLayout());

        pnlSuratMasuk.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Adwaita Sans", 1, 18)); // NOI18N
        jLabel2.setText("SURAT MASUK");

        txtIdSurat.setPreferredSize(new java.awt.Dimension(200, 24));

        txtNoAgenda.setPreferredSize(new java.awt.Dimension(200, 24));

        txtAsalSurat.setPreferredSize(new java.awt.Dimension(200, 24));

        txtNoSurat.setPreferredSize(new java.awt.Dimension(200, 24));

        areaIsi.setColumns(20);
        areaIsi.setRows(5);
        jScrollPane1.setViewportView(areaIsi);

        cmbKode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "K-01 - Keuangan", "P-02 - Personalia", "M-03 - Marketing", "U-04 - Umum", "L-05 - Lain-lain" }));

        cmbIndex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Keuangan", "Kepegawaian", "Pemasaran", "Umum", "Penting", "Rahasia" }));

        areaKeterangan.setColumns(20);
        areaKeterangan.setRows(5);
        jScrollPane2.setViewportView(areaKeterangan);

        cmbIdUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnRead.setText("Read");
        btnRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadActionPerformed(evt);
            }
        });

        jLabel3.setText("ID Surat");

        jLabel4.setText("No Agenda");

        jLabel5.setText("Asal Surat");

        jLabel6.setText("No Surat");

        jLabel7.setText("Isi");

        jLabel8.setText("Kode");

        jLabel9.setText("Index");

        jLabel10.setText("Tanggal Surat");

        jLabel11.setText("Tanggal Diterima");

        jLabel12.setText("File");

        jLabel13.setText("Keterangan");

        jLabel14.setText("Id User");

        txtFile.setPreferredSize(new java.awt.Dimension(200, 24));

        tblSuratMasuk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(tblSuratMasuk);

        txtSearchSuratMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchSuratMasukKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlSuratMasukLayout = new javax.swing.GroupLayout(pnlSuratMasuk);
        pnlSuratMasuk.setLayout(pnlSuratMasukLayout);
        pnlSuratMasukLayout.setHorizontalGroup(
            pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuratMasukLayout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlSuratMasukLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSuratMasukLayout.createSequentialGroup()
                        .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cmbKode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIdSurat, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                .addComponent(txtNoAgenda, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                .addComponent(txtAsalSurat, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                .addComponent(txtNoSurat, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                .addComponent(jScrollPane1)
                                .addGroup(pnlSuratMasukLayout.createSequentialGroup()
                                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSuratMasukLayout.createSequentialGroup()
                                .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(pnlSuratMasukLayout.createSequentialGroup()
                                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnRead, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                                        .addComponent(cmbIndex, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbIdUser, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap(16, Short.MAX_VALUE))
                            .addGroup(pnlSuratMasukLayout.createSequentialGroup()
                                .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(dateSurat, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(dateTerima, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtFile, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 16, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSuratMasukLayout.createSequentialGroup()
                        .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSearchSuratMasuk, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8))
                        .addGap(16, 16, 16))))
        );
        pnlSuratMasukLayout.setVerticalGroup(
            pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuratMasukLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdSurat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNoAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateSurat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAsalSurat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateTerima, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNoSurat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFile, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbKode, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbIdUser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(pnlSuratMasukLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnRead))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(txtSearchSuratMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Content.add(pnlSuratMasuk, "card2");

        pnlSuratKeluar.setBackground(new java.awt.Color(255, 255, 255));

        txtIdSuratKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdSuratKeluarActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Adwaita Sans", 1, 18)); // NOI18N
        jLabel15.setText("SURAT KELUAR");

        txtNoAgendaKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoAgendaKeluarActionPerformed(evt);
            }
        });

        areaIsiKeluar.setColumns(20);
        areaIsiKeluar.setRows(5);
        jScrollPane3.setViewportView(areaIsiKeluar);

        cmbKodeKeluar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A01", "A02", "A03", "A04", "A05" }));

        areaKeteranganKeluar.setColumns(20);
        areaKeteranganKeluar.setRows(5);
        jScrollPane4.setViewportView(areaKeteranganKeluar);

        cmbIdUserKeluar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", " " }));

        btnCreateKeluar.setText("Create");
        btnCreateKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateKeluarActionPerformed(evt);
            }
        });

        btnUpdateKeluar.setText("Update");
        btnUpdateKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateKeluarActionPerformed(evt);
            }
        });

        btnDeleteKeluar.setText("Delete");
        btnDeleteKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteKeluarActionPerformed(evt);
            }
        });

        btnReadKeluar.setText("Read");
        btnReadKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadKeluarActionPerformed(evt);
            }
        });

        jLabel16.setText("ID");

        jLabel17.setText("No Agenda");

        jLabel18.setText("Tujuan");

        jLabel19.setText("No Surat");

        jLabel20.setText("Isi");

        jLabel21.setText("Kode");

        jLabel22.setText("Tanggal  Surat");

        jLabel23.setText("File");

        jLabel24.setText("ID User");

        jLabel25.setText("Keterangan");

        tblSuratKeluar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(tblSuratKeluar);

        txtSearchSuratKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchSuratKeluarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlSuratKeluarLayout = new javax.swing.GroupLayout(pnlSuratKeluar);
        pnlSuratKeluar.setLayout(pnlSuratKeluarLayout);
        pnlSuratKeluarLayout.setHorizontalGroup(
            pnlSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuratKeluarLayout.createSequentialGroup()
                .addGroup(pnlSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSuratKeluarLayout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(jLabel15))
                    .addGroup(pnlSuratKeluarLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(pnlSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                            .addGroup(pnlSuratKeluarLayout.createSequentialGroup()
                                .addGroup(pnlSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtIdSuratKeluar)
                                        .addComponent(txtNoAgendaKeluar)
                                        .addComponent(txtTujuan)
                                        .addComponent(jScrollPane3)
                                        .addComponent(txtNoSuratKeluar)
                                        .addGroup(pnlSuratKeluarLayout.createSequentialGroup()
                                            .addComponent(btnCreateKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnUpdateKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20))
                                .addGap(51, 51, 51)
                                .addGroup(pnlSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel21)
                                    .addGroup(pnlSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(dateSuratKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtFileKeluar)
                                        .addComponent(cmbKodeKeluar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                                        .addComponent(cmbIdUserKeluar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pnlSuratKeluarLayout.createSequentialGroup()
                                            .addComponent(btnDeleteKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnReadKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(txtSearchSuratKeluar))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        pnlSuratKeluarLayout.setVerticalGroup(
            pnlSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuratKeluarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(pnlSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbKodeKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdSuratKeluar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel22))
                .addGap(2, 2, 2)
                .addGroup(pnlSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNoAgendaKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateSuratKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel23))
                .addGap(8, 8, 8)
                .addGroup(pnlSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFileKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel24))
                .addGap(8, 8, 8)
                .addGroup(pnlSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbIdUserKeluar)
                    .addComponent(txtNoSuratKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(pnlSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlSuratKeluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateKeluar)
                    .addComponent(btnUpdateKeluar)
                    .addComponent(btnDeleteKeluar)
                    .addComponent(btnReadKeluar))
                .addGap(31, 31, 31)
                .addComponent(txtSearchSuratKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        Content.add(pnlSuratKeluar, "card3");

        pnlDisposisi.setBackground(new java.awt.Color(255, 255, 255));

        jLabel26.setFont(new java.awt.Font("Adwaita Sans", 1, 18)); // NOI18N
        jLabel26.setText("DISPOSISI");

        txtIdDisposisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdDisposisiActionPerformed(evt);
            }
        });

        cmbIdSuratDisposisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));

        areaIsiDisposisi.setColumns(20);
        areaIsiDisposisi.setRows(5);
        jScrollPane5.setViewportView(areaIsiDisposisi);

        cmbSifatDisposisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Biasa", "Penting", "Rahasia", " " }));

        areaCatatanDisposisi.setColumns(20);
        areaCatatanDisposisi.setRows(5);
        jScrollPane6.setViewportView(areaCatatanDisposisi);

        cmbIdUserDisposisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));

        jLabel27.setText("ID");

        jLabel28.setText("ID Surat");

        jLabel29.setText("Tujuan");

        jLabel30.setText("Isi");

        jLabel31.setText("Sifat");

        jLabel32.setText("Batas Waktu");

        jLabel33.setText("ID User");

        jLabel34.setText("Catatan");

        btnCreateDisposisi.setText("Create");
        btnCreateDisposisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateDisposisiActionPerformed(evt);
            }
        });

        btnCUpdateDisposis.setText("Update");
        btnCUpdateDisposis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCUpdateDisposisActionPerformed(evt);
            }
        });

        btnDeleteDisposisi.setText("Delete");
        btnDeleteDisposisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteDisposisiActionPerformed(evt);
            }
        });

        btnReadDisposisi.setText("Read");
        btnReadDisposisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadDisposisiActionPerformed(evt);
            }
        });

        tblDisposisi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane10.setViewportView(tblDisposisi);

        txtSearchDisposisi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchDisposisiKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlDisposisiLayout = new javax.swing.GroupLayout(pnlDisposisi);
        pnlDisposisi.setLayout(pnlDisposisiLayout);
        pnlDisposisiLayout.setHorizontalGroup(
            pnlDisposisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDisposisiLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(pnlDisposisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10)
                    .addGroup(pnlDisposisiLayout.createSequentialGroup()
                        .addGroup(pnlDisposisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDisposisiLayout.createSequentialGroup()
                                .addGroup(pnlDisposisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTujuanDisposisi)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel29)
                                    .addComponent(jScrollPane5)
                                    .addComponent(cmbIdSuratDisposisi, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtIdDisposisi, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel28))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE))
                            .addGroup(pnlDisposisiLayout.createSequentialGroup()
                                .addComponent(btnCreateDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCUpdateDisposis, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(pnlDisposisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDisposisiLayout.createSequentialGroup()
                                .addComponent(btnDeleteDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(btnReadDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel32)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)
                            .addGroup(pnlDisposisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cmbSifatDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dateBatasWaktu, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbIdUserDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlDisposisiLayout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jLabel26))
                    .addGroup(pnlDisposisiLayout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(289, 289, 289)
                        .addComponent(jLabel31))
                    .addComponent(txtSearchDisposisi))
                .addGap(34, 34, 34))
        );
        pnlDisposisiLayout.setVerticalGroup(
            pnlDisposisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDisposisiLayout.createSequentialGroup()
                .addGroup(pnlDisposisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDisposisiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel28))
                    .addGroup(pnlDisposisiLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbSifatDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel32)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDisposisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbIdSuratDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateBatasWaktu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlDisposisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDisposisiLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(pnlDisposisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDisposisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTujuanDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbIdUserDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlDisposisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(pnlDisposisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateDisposisi)
                    .addComponent(btnCUpdateDisposis)
                    .addComponent(btnDeleteDisposisi)
                    .addComponent(btnReadDisposisi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(txtSearchDisposisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        Content.add(pnlDisposisi, "card4");

        pnlInstansi.setBackground(new java.awt.Color(255, 255, 255));

        jLabel35.setFont(new java.awt.Font("Adwaita Sans", 1, 18)); // NOI18N
        jLabel35.setText("Pengaturan Instansi");

        areaAlamatINstansi.setColumns(20);
        areaAlamatINstansi.setRows(5);
        jScrollPane7.setViewportView(areaAlamatINstansi);

        btnCreateInstansi.setText("Create");
        btnCreateInstansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateInstansiActionPerformed(evt);
            }
        });

        btnUpdateInstansi.setText("Update");
        btnUpdateInstansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateInstansiActionPerformed(evt);
            }
        });

        btnDeleteInstansi.setText("Delete");
        btnDeleteInstansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteInstansiActionPerformed(evt);
            }
        });

        btnReadInstansi.setText("Read");
        btnReadInstansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadInstansiActionPerformed(evt);
            }
        });

        jLabel36.setText("ID");

        jLabel37.setText("Nama Instansi");

        jLabel38.setText("Telpon");

        jLabel39.setText("Alamat");

        jLabel40.setText("Email");

        jLabel41.setText("Website");

        jLabel42.setText("Logo");

        tblInstansi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(tblInstansi);

        txtSearchInstansi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchInstansiKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlInstansiLayout = new javax.swing.GroupLayout(pnlInstansi);
        pnlInstansi.setLayout(pnlInstansiLayout);
        pnlInstansiLayout.setHorizontalGroup(
            pnlInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInstansiLayout.createSequentialGroup()
                .addGroup(pnlInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInstansiLayout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addComponent(jLabel35))
                    .addGroup(pnlInstansiLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(pnlInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36))
                        .addGap(39, 39, 39)
                        .addGroup(pnlInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(txtEmailInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlInstansiLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(pnlInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNamaInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))
                        .addGap(39, 39, 39)
                        .addGroup(pnlInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(txtWebsiteInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlInstansiLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(pnlInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel39)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                            .addGroup(pnlInstansiLayout.createSequentialGroup()
                                .addGroup(pnlInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(txtTelponInstansi, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                                        .addGroup(pnlInstansiLayout.createSequentialGroup()
                                            .addComponent(btnCreateInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnUpdateInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel38))
                                .addGap(39, 39, 39)
                                .addGroup(pnlInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42)
                                    .addGroup(pnlInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(pnlInstansiLayout.createSequentialGroup()
                                            .addComponent(btnDeleteInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnReadInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtLogoInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(txtSearchInstansi))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        pnlInstansiLayout.setVerticalGroup(
            pnlInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInstansiLayout.createSequentialGroup()
                .addGroup(pnlInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInstansiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel35)
                        .addGap(19, 19, 19)
                        .addGroup(pnlInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(jLabel40))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(pnlInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(jLabel41))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNamaInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtWebsiteInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(jLabel42))
                        .addGap(7, 7, 7)
                        .addGroup(pnlInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelponInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLogoInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlInstansiLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(txtEmailInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlInstansiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateInstansi)
                    .addComponent(btnUpdateInstansi)
                    .addComponent(btnDeleteInstansi)
                    .addComponent(btnReadInstansi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(txtSearchInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        Content.add(pnlInstansi, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBackround, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBackround, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuratMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuratMasukActionPerformed
        Content.removeAll();
        Content.add(pnlSuratMasuk);
        Content.repaint();
        Content.revalidate();
    }//GEN-LAST:event_btnSuratMasukActionPerformed

    private void btnSuratKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuratKeluarActionPerformed
        Content.removeAll();
        Content.add(pnlSuratKeluar);
        Content.repaint();
        Content.revalidate();
    }//GEN-LAST:event_btnSuratKeluarActionPerformed

    private void btnDisposisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisposisiActionPerformed
        Content.removeAll();
        Content.add(pnlDisposisi);
        Content.repaint();
        Content.revalidate();
    }//GEN-LAST:event_btnDisposisiActionPerformed

    private void btnInstansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInstansiActionPerformed
        Content.removeAll();
        Content.add(pnlInstansi);
        Content.repaint();
        Content.revalidate();
    }//GEN-LAST:event_btnInstansiActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        try {
            SuratMasuk surat = new SuratMasuk();

            surat.setIdSurat(Integer.parseInt(txtIdSurat.getText()));
            surat.setNoAgenda(txtNoAgenda.getText());
            surat.setAsalSurat(txtAsalSurat.getText());
            surat.setNoSurat(txtNoSurat.getText());
            surat.setIsi(areaIsi.getText());
            surat.setKode((String) cmbKode.getSelectedItem());
            surat.setIndeks((String) cmbIndex.getSelectedItem());

            java.util.Date utilDateSurat = dateSurat.getDate();
            surat.setTglSurat(new java.sql.Date(utilDateSurat.getTime()));

            java.util.Date utilDateTerima = dateTerima.getDate();
            surat.setTglDiterima(new java.sql.Date(utilDateTerima.getTime()));

            surat.setFilePath(txtFile.getText()); 
            surat.setKeterangan(areaKeterangan.getText());
            surat.setIdUser(Integer.parseInt((String) cmbIdUser.getSelectedItem()));

            suratMasukService.createSuratMasuk(surat);

            JOptionPane.showMessageDialog(null,"Letter data with ID " + surat.getIdSurat() + " has been successfully saved!");
            
            clearSuratMasuk();
            this.loadDataSuratMasuk();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Letter ID and User ID must be filled in and must be numbers.", "Validation Error", JOptionPane.ERROR_MESSAGE);

        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null,"Date fields cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            SuratMasuk surat = new SuratMasuk();

            surat.setIdSurat(Integer.parseInt(txtIdSurat.getText()));
            surat.setNoAgenda(txtNoAgenda.getText());
            surat.setAsalSurat(txtAsalSurat.getText());
            surat.setNoSurat(txtNoSurat.getText());
            surat.setIsi(areaIsi.getText());
            surat.setKode((String) cmbKode.getSelectedItem());
            surat.setIndeks((String) cmbIndex.getSelectedItem());

            java.util.Date utilDateSurat = dateSurat.getDate();
            surat.setTglSurat(new java.sql.Date(utilDateSurat.getTime()));

            java.util.Date utilDateTerima = dateTerima.getDate();
            surat.setTglDiterima(new java.sql.Date(utilDateTerima.getTime()));

            surat.setFilePath(txtFile.getText()); 
            surat.setKeterangan(areaKeterangan.getText());
            surat.setIdUser(Integer.parseInt((String) cmbIdUser.getSelectedItem()));

            suratMasukService.updateSuratMasuk(surat);

            JOptionPane.showMessageDialog(null,"Letter data with ID " + surat.getIdSurat() + " has been successfully updated!");
            
            clearSuratMasuk();
            this.loadDataSuratMasuk();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Letter ID and User ID must be filled in and must be numbers.", "Validation Error", JOptionPane.ERROR_MESSAGE);

        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null,"Date fields cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            String idSuratStr = txtIdSurat.getText();
            if (idSuratStr.trim().isEmpty()) {
                throw new Exception("Please enter a Letter ID to delete.");
            }

            int idSurat = Integer.parseInt(idSuratStr);
            if (idSurat <= 0) {
                throw new Exception("ID must be a positive number.");
            }

            int choice = JOptionPane.showConfirmDialog(
                    this, 
                    "Are you sure you want to delete Letter ID " + idSurat + "?", 
                    "Confirm Deletion", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE);

            if (choice == JOptionPane.YES_OPTION) {
                suratMasukService.deleteSuratMasuk(idSurat);

                JOptionPane.showMessageDialog(null, "Letter ID " + idSurat + " has been successfully deleted.");
            }
            
            clearSuratMasuk();
            this.loadDataSuratMasuk();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Letter ID must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadActionPerformed
        try {
            Connection conn = ConnectionDB.getConnection();

            String reportPath = "/Reports/SuratMasukReport.jasper";

            InputStream reportStream = getClass().getResourceAsStream(reportPath);

            if (reportStream == null) {
                throw new Exception("Cannot find report file at: " + reportPath);
            }

            HashMap<String, Object> parameters = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parameters, conn);

            JasperViewer.viewReport(jasperPrint, false);
            
            
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,"Failed to generate report: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnReadActionPerformed

    private void txtIdSuratKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdSuratKeluarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdSuratKeluarActionPerformed

    private void txtNoAgendaKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoAgendaKeluarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoAgendaKeluarActionPerformed

    private void btnCreateKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateKeluarActionPerformed
        try {
            SuratKeluar surat = new SuratKeluar();

            surat.setIdSurat(Integer.parseInt(txtIdSuratKeluar.getText()));
            surat.setNoAgenda(txtNoAgendaKeluar.getText());
            surat.setTujuan(txtTujuan.getText());
            surat.setNoSurat(txtNoSuratKeluar.getText());
            surat.setIsi(areaIsiKeluar.getText());
            surat.setKode((String) cmbKodeKeluar.getSelectedItem());

            java.util.Date utilDateSurat = dateSuratKeluar.getDate();
            surat.setTglSurat(new java.sql.Date(utilDateSurat.getTime()));

            surat.setFilePath(txtFileKeluar.getText()); 
            surat.setKeterangan(areaKeteranganKeluar.getText());
            surat.setIdUser(Integer.parseInt((String) cmbIdUserKeluar.getSelectedItem()));

            suratKeluarService.createSuratKeluar(surat);

            JOptionPane.showMessageDialog(null,"Outgoing letter data with ID " + surat.getIdSurat() + " has been successfully saved!");
            clearFormSuratKeluar();
            this.loadDataSuratKeluar();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Letter ID and User ID must be filled in and must be numbers.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null,"Date fields cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCreateKeluarActionPerformed

    private void btnUpdateKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateKeluarActionPerformed
        try {
            SuratKeluar surat = new SuratKeluar();

            surat.setIdSurat(Integer.parseInt(txtIdSuratKeluar.getText()));
            surat.setNoAgenda(txtNoAgendaKeluar.getText());
            surat.setTujuan(txtTujuan.getText());
            surat.setNoSurat(txtNoSuratKeluar.getText());
            surat.setIsi(areaIsiKeluar.getText());
            surat.setKode((String) cmbKodeKeluar.getSelectedItem());

            java.util.Date utilDateSurat = dateSuratKeluar.getDate();
            surat.setTglSurat(new java.sql.Date(utilDateSurat.getTime()));

            surat.setFilePath(txtFileKeluar.getText()); 
            surat.setKeterangan(areaKeteranganKeluar.getText());
            surat.setIdUser(Integer.parseInt((String) cmbIdUserKeluar.getSelectedItem()));

            suratKeluarService.updateSuratKeluar(surat);

            JOptionPane.showMessageDialog(null,"Outgoing letter data with ID " + surat.getIdSurat() + " has been successfully updated!");
            clearFormSuratKeluar();
            this.loadDataSuratKeluar();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Letter ID and User ID must be filled in and must be numbers.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null,"Date fields cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateKeluarActionPerformed

    private void btnDeleteKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteKeluarActionPerformed
        try {
            String idSuratStr = txtIdSuratKeluar.getText();
            if (idSuratStr.trim().isEmpty()) {
                throw new Exception("Please enter a Letter ID to delete.");
            }

            int idSurat = Integer.parseInt(idSuratStr);
            if (idSurat <= 0) {
                throw new Exception("ID must be a positive number.");
            }

            int choice = JOptionPane.showConfirmDialog(
                    this, 
                    "Are you sure you want to delete Outgoing Letter ID " + idSurat + "?", 
                    "Confirm Deletion", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE);

            if (choice == JOptionPane.YES_OPTION) {
                suratKeluarService.deleteSuratKeluar(idSurat);

                JOptionPane.showMessageDialog(null, "Outgoing Letter ID " + idSurat + " has been successfully deleted.");
                clearFormSuratKeluar();
                this.loadDataSuratKeluar();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Letter ID must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteKeluarActionPerformed

    private void txtIdDisposisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdDisposisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdDisposisiActionPerformed

    private void btnCreateDisposisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateDisposisiActionPerformed
        try {
            Disposisi disposisi = new Disposisi();

            disposisi.setIdDisposisi(Integer.parseInt(txtIdDisposisi.getText()));
            disposisi.setIdSurat(Integer.parseInt((String) cmbIdSuratDisposisi.getSelectedItem()));
            disposisi.setTujuan(txtTujuanDisposisi.getText());
            disposisi.setIsiDisposisi(areaIsiDisposisi.getText());
            disposisi.setSifat((String) cmbSifatDisposisi.getSelectedItem());

            java.util.Date utilDate = dateBatasWaktu.getDate();
            disposisi.setBatasWaktu(new java.sql.Date(utilDate.getTime()));

            disposisi.setCatatan(areaCatatanDisposisi.getText());
            disposisi.setIdUser(Integer.parseInt((String) cmbIdUserDisposisi.getSelectedItem()));

            disposisiService.createDisposisi(disposisi);

            JOptionPane.showMessageDialog(null,"Disposition data with ID " + disposisi.getIdDisposisi() + " has been successfully saved!");
            clearFormDisposisi();
            
            this.loadDataDisposisi();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"ID fields must be filled in and must be numbers.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null,"Date fields cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCreateDisposisiActionPerformed

    private void btnCUpdateDisposisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCUpdateDisposisActionPerformed
        try {
            Disposisi disposisi = new Disposisi();

            disposisi.setIdDisposisi(Integer.parseInt(txtIdDisposisi.getText()));
            disposisi.setIdSurat(Integer.parseInt((String) cmbIdSuratDisposisi.getSelectedItem()));
            disposisi.setTujuan(txtTujuanDisposisi.getText());
            disposisi.setIsiDisposisi(areaIsiDisposisi.getText());
            disposisi.setSifat((String) cmbSifatDisposisi.getSelectedItem());

            java.util.Date utilDate = dateBatasWaktu.getDate();
            disposisi.setBatasWaktu(new java.sql.Date(utilDate.getTime()));

            disposisi.setCatatan(areaCatatanDisposisi.getText());
            disposisi.setIdUser(Integer.parseInt((String) cmbIdUserDisposisi.getSelectedItem()));

            disposisiService.updateDisposisi(disposisi);

            JOptionPane.showMessageDialog(null,"Disposition data with ID " + disposisi.getIdDisposisi() + " has been successfully updated!");
            clearFormDisposisi();
            
            this.loadDataDisposisi();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"ID fields must be filled in and must be numbers.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null,"Date fields cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCUpdateDisposisActionPerformed

    private void btnDeleteDisposisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteDisposisiActionPerformed
        try {
            String idStr = txtIdDisposisi.getText();
            if (idStr.trim().isEmpty()) {
                throw new Exception("Please enter a Disposition ID to delete.");
            }

            int idDisposisi = Integer.parseInt(idStr);
            if (idDisposisi <= 0) {
                throw new Exception("ID must be a positive number.");
            }

            int choice = JOptionPane.showConfirmDialog(
                    this, 
                    "Are you sure you want to delete Disposition ID " + idDisposisi + "?", 
                    "Confirm Deletion", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE);

            if (choice == JOptionPane.YES_OPTION) {
                disposisiService.deleteDisposisi(idDisposisi);

                JOptionPane.showMessageDialog(null, "Disposition ID " + idDisposisi + " has been successfully deleted.");
                clearFormDisposisi();
                this.loadDataDisposisi();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Disposition ID must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteDisposisiActionPerformed

    private void btnCreateInstansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateInstansiActionPerformed
        try {
            PengaturanInstansi instansi = new PengaturanInstansi();

            instansi.setIdInstansi(Integer.parseInt(txtIdInstansi.getText()));
            instansi.setNamaInstansi(txtNamaInstansi.getText());
            instansi.setAlamat(areaAlamatINstansi.getText());
            instansi.setTelpon(txtTelponInstansi.getText());
            instansi.setWebsite(txtWebsiteInstansi.getText());
            instansi.setEmail(txtEmailInstansi.getText());
            instansi.setLogoPath(txtLogoInstansi.getText());

            instansiService.createInstansi(instansi);

            JOptionPane.showMessageDialog(null,"Institute data with ID " + instansi.getIdInstansi() + " has been successfully saved!");
            clearFormInstansi();
            this.loadDataInstansi();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Institute ID must be filled in and must be a number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCreateInstansiActionPerformed

    private void btnUpdateInstansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateInstansiActionPerformed
        try {
            PengaturanInstansi instansi = new PengaturanInstansi();

            instansi.setIdInstansi(Integer.parseInt(txtIdInstansi.getText()));
            instansi.setNamaInstansi(txtNamaInstansi.getText());
            instansi.setAlamat(areaAlamatINstansi.getText());
            instansi.setTelpon(txtTelponInstansi.getText());
            instansi.setWebsite(txtWebsiteInstansi.getText());
            instansi.setEmail(txtEmailInstansi.getText());
            instansi.setLogoPath(txtLogoInstansi.getText());

            instansiService.updateInstansi(instansi);

            JOptionPane.showMessageDialog(null,"Institute data with ID " + instansi.getIdInstansi() + " has been successfully updated!");
            clearFormInstansi();
            this.loadDataInstansi();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Institute ID must be filled in and must be a number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateInstansiActionPerformed

    private void btnDeleteInstansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteInstansiActionPerformed
        try {
            String idStr = txtIdInstansi.getText();
            if (idStr.trim().isEmpty()) {
                throw new Exception("Please enter an Institute ID to delete.");
            }

            int idInstansi = Integer.parseInt(idStr);
            if (idInstansi <= 0) {
                throw new Exception("ID must be a positive number.");
            }

            int choice = JOptionPane.showConfirmDialog(
                    this, 
                    "Are you sure you want to delete Institute ID " + idInstansi + "?", 
                    "Confirm Deletion", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE);

            if (choice == JOptionPane.YES_OPTION) {
                instansiService.deleteInstansi(idInstansi);

                JOptionPane.showMessageDialog(null, "Institute ID " + idInstansi + " has been successfully deleted.");
                clearFormInstansi();
                this.loadDataInstansi();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Institute ID must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteInstansiActionPerformed

    private void btnReadKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadKeluarActionPerformed
        try {
            Connection conn = ConnectionDB.getConnection();

            String reportPath = "/Reports/SuratKeluarReport.jasper";

            InputStream reportStream = getClass().getResourceAsStream(reportPath);

            if (reportStream == null) {
                throw new Exception("Cannot find report file at: " + reportPath);
            }

            HashMap<String, Object> parameters = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parameters, conn);

            JasperViewer.viewReport(jasperPrint, false);
            
            clearSuratMasuk();
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,"Failed to generate report: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnReadKeluarActionPerformed

    private void btnReadDisposisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadDisposisiActionPerformed
        try {
            Connection conn = ConnectionDB.getConnection();

            String reportPath = "/Reports/DisposisiReport.jasper";

            InputStream reportStream = getClass().getResourceAsStream(reportPath);

            if (reportStream == null) {
                throw new Exception("Cannot find report file at: " + reportPath);
            }

            HashMap<String, Object> parameters = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parameters, conn);

            JasperViewer.viewReport(jasperPrint, false);
            
            clearSuratMasuk();
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,"Failed to generate report: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnReadDisposisiActionPerformed

    private void btnReadInstansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadInstansiActionPerformed
        try {
            Connection conn = ConnectionDB.getConnection();

            String reportPath = "/Reports/PengaturanInstansiReport.jasper";

            InputStream reportStream = getClass().getResourceAsStream(reportPath);

            if (reportStream == null) {
                throw new Exception("Cannot find report file at: " + reportPath);
            }

            HashMap<String, Object> parameters = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parameters, conn);

            JasperViewer.viewReport(jasperPrint, false);
            
            clearSuratMasuk();
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,"Failed to generate report: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnReadInstansiActionPerformed

    private void txtSearchSuratMasukKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchSuratMasukKeyReleased

        String query = txtSearchSuratMasuk.getText();

        try {
            List<SuratMasuk> listHasil = suratMasukService.searchSuratMasuk(query);


            modelTabelSuratMasuk.setRowCount(0);


            for (SuratMasuk surat : listHasil) {
                Object[] rowData = {
                    surat.getIdSurat(),
                    surat.getNoAgenda(),
                    surat.getAsalSurat(),
                    surat.getNoSurat(),
                    surat.getIsi(), 
                    surat.getTglDiterima()
                };
                modelTabelSuratMasuk.addRow(rowData);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Search failed: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtSearchSuratMasukKeyReleased

    private void txtSearchSuratKeluarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchSuratKeluarKeyReleased
        String query = txtSearchSuratKeluar.getText();
        try {
            List<SuratKeluar> listHasil = suratKeluarService.searchSuratKeluar(query);
            modelTabelSuratKeluar.setRowCount(0);

            for (SuratKeluar surat : listHasil) {
                modelTabelSuratKeluar.addRow(new Object[]{
                    surat.getIdSurat(),
                    surat.getNoAgenda(),
                    surat.getTujuan(),
                    surat.getNoSurat(),
                    surat.getIsi(), 
                    surat.getTglSurat()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Search failed: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtSearchSuratKeluarKeyReleased

    private void txtSearchDisposisiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchDisposisiKeyReleased
        String query = txtSearchDisposisi.getText();
        try {
            List<Disposisi> listHasil = disposisiService.searchDisposisi(query);
            modelTabelDisposisi.setRowCount(0);

            for (Disposisi disposisi : listHasil) {
                modelTabelDisposisi.addRow(new Object[]{
                    disposisi.getIdDisposisi(),
                    disposisi.getIdSurat(),
                    disposisi.getTujuan(),
                    disposisi.getIsiDisposisi(),
                    disposisi.getSifat(), 
                    disposisi.getBatasWaktu(),
                    disposisi.getCatatan(),
                    disposisi.getIdUser()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Search failed: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtSearchDisposisiKeyReleased

    private void txtSearchInstansiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchInstansiKeyReleased
       String query = txtSearchInstansi.getText();
        try {
            List<PengaturanInstansi> listHasil = instansiService.searchInstansi(query);
            modelTabelInstansi.setRowCount(0);

            for (PengaturanInstansi instansi : listHasil) {
                modelTabelInstansi.addRow(new Object[]{
                    instansi.getIdInstansi(),
                    instansi.getNamaInstansi(),
                    instansi.getAlamat(),
                    instansi.getTelpon(),
                    instansi.getEmail(),
                    instansi.getWebsite(),
                    instansi.getLogoPath()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Search failed: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtSearchInstansiKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Dashboard().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content;
    private javax.swing.JTextArea areaAlamatINstansi;
    private javax.swing.JTextArea areaCatatanDisposisi;
    private javax.swing.JTextArea areaIsi;
    private javax.swing.JTextArea areaIsiDisposisi;
    private javax.swing.JTextArea areaIsiKeluar;
    private javax.swing.JTextArea areaKeterangan;
    private javax.swing.JTextArea areaKeteranganKeluar;
    private javax.swing.JButton btnCUpdateDisposis;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnCreateDisposisi;
    private javax.swing.JButton btnCreateInstansi;
    private javax.swing.JButton btnCreateKeluar;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteDisposisi;
    private javax.swing.JButton btnDeleteInstansi;
    private javax.swing.JButton btnDeleteKeluar;
    private javax.swing.JButton btnDisposisi;
    private javax.swing.JButton btnInstansi;
    private javax.swing.JButton btnRead;
    private javax.swing.JButton btnReadDisposisi;
    private javax.swing.JButton btnReadInstansi;
    private javax.swing.JButton btnReadKeluar;
    private javax.swing.JButton btnSuratKeluar;
    private javax.swing.JButton btnSuratMasuk;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateInstansi;
    private javax.swing.JButton btnUpdateKeluar;
    private javax.swing.JComboBox<String> cmbIdSuratDisposisi;
    private javax.swing.JComboBox<String> cmbIdUser;
    private javax.swing.JComboBox<String> cmbIdUserDisposisi;
    private javax.swing.JComboBox<String> cmbIdUserKeluar;
    private javax.swing.JComboBox<String> cmbIndex;
    private javax.swing.JComboBox<String> cmbKode;
    private javax.swing.JComboBox<String> cmbKodeKeluar;
    private javax.swing.JComboBox<String> cmbSifatDisposisi;
    private com.toedter.calendar.JDateChooser dateBatasWaktu;
    private com.toedter.calendar.JDateChooser dateSurat;
    private com.toedter.calendar.JDateChooser dateSuratKeluar;
    private com.toedter.calendar.JDateChooser dateTerima;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPanel pnlBackround;
    private javax.swing.JPanel pnlDisposisi;
    private javax.swing.JPanel pnlInstansi;
    private javax.swing.JPanel pnlSuratKeluar;
    private javax.swing.JPanel pnlSuratMasuk;
    private javax.swing.JTable tblDisposisi;
    private javax.swing.JTable tblInstansi;
    private javax.swing.JTable tblSuratKeluar;
    private javax.swing.JTable tblSuratMasuk;
    private javax.swing.JTextField txtAsalSurat;
    private javax.swing.JTextField txtEmailInstansi;
    private javax.swing.JTextField txtFile;
    private javax.swing.JTextField txtFileKeluar;
    private javax.swing.JTextField txtIdDisposisi;
    private javax.swing.JTextField txtIdInstansi;
    private javax.swing.JTextField txtIdSurat;
    private javax.swing.JTextField txtIdSuratKeluar;
    private javax.swing.JTextField txtLogoInstansi;
    private javax.swing.JTextField txtNamaInstansi;
    private javax.swing.JTextField txtNoAgenda;
    private javax.swing.JTextField txtNoAgendaKeluar;
    private javax.swing.JTextField txtNoSurat;
    private javax.swing.JTextField txtNoSuratKeluar;
    private javax.swing.JTextField txtSearchDisposisi;
    private javax.swing.JTextField txtSearchInstansi;
    private javax.swing.JTextField txtSearchSuratKeluar;
    private javax.swing.JTextField txtSearchSuratMasuk;
    private javax.swing.JTextField txtTelponInstansi;
    private javax.swing.JTextField txtTujuan;
    private javax.swing.JTextField txtTujuanDisposisi;
    private javax.swing.JTextField txtWebsiteInstansi;
    // End of variables declaration//GEN-END:variables
}
