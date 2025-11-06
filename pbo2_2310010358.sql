/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-12.0.2-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: pbo2_2310010358
-- ------------------------------------------------------
-- Server version	12.0.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `disposisi`
--

DROP TABLE IF EXISTS `disposisi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `disposisi` (
  `id_disposisi` int(11) NOT NULL AUTO_INCREMENT,
  `id_surat` int(11) NOT NULL,
  `tujuan` varchar(150) DEFAULT NULL,
  `isi_disposisi` text DEFAULT NULL,
  `sifat` varchar(50) DEFAULT NULL,
  `batas_waktu` date DEFAULT NULL,
  `catatan` text DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_disposisi`),
  KEY `id_surat` (`id_surat`),
  CONSTRAINT `disposisi_ibfk_1` FOREIGN KEY (`id_surat`) REFERENCES `surat_masuk` (`id_surat`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disposisi`
--

LOCK TABLES `disposisi` WRITE;
/*!40000 ALTER TABLE `disposisi` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `disposisi` VALUES
(1,1,'Neraka','laknsd','Penting','2025-11-28','wefrgfg',1),
(2,2,'Bahlil','Sari Roti','Rahasia','2025-11-18','Aku',3),
(3,2,'Luhut','Kentang Sayur',NULL,'2025-11-05','Kamu',1);
/*!40000 ALTER TABLE `disposisi` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `pengaturan_instansi`
--

DROP TABLE IF EXISTS `pengaturan_instansi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `pengaturan_instansi` (
  `id_instansi` int(11) NOT NULL AUTO_INCREMENT,
  `nama_instansi` varchar(150) DEFAULT NULL,
  `alamat` text DEFAULT NULL,
  `telpon` varchar(50) DEFAULT NULL,
  `website` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `logo_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_instansi`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pengaturan_instansi`
--

LOCK TABLES `pengaturan_instansi` WRITE;
/*!40000 ALTER TABLE `pengaturan_instansi` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `pengaturan_instansi` VALUES
(1,'Owi','asdfg','087656','Projo','JokowiAs@666gmail.com','banteng');
/*!40000 ALTER TABLE `pengaturan_instansi` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `surat_keluar`
--

DROP TABLE IF EXISTS `surat_keluar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `surat_keluar` (
  `id_surat` int(11) NOT NULL AUTO_INCREMENT,
  `no_agenda` varchar(100) DEFAULT NULL,
  `tujuan` varchar(150) DEFAULT NULL,
  `no_surat` varchar(100) DEFAULT NULL,
  `isi` text DEFAULT NULL,
  `kode` varchar(50) DEFAULT NULL,
  `tgl_surat` date DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `keterangan` text DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_surat`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `surat_keluar`
--

LOCK TABLES `surat_keluar` WRITE;
/*!40000 ALTER TABLE `surat_keluar` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `surat_keluar` VALUES
(1,'Asdfgh','Neraka','B826','akdsa','A04','2025-11-19','Doc','sdfg',5),
(2,'Asjdn','Surga','A08978','abc lima uncle muthu','A03','2025-11-26','Doc','Ballil',2);
/*!40000 ALTER TABLE `surat_keluar` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `surat_masuk`
--

DROP TABLE IF EXISTS `surat_masuk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `surat_masuk` (
  `id_surat` int(11) NOT NULL,
  `no_agenda` varchar(100) DEFAULT NULL,
  `asal_surat` varchar(150) DEFAULT NULL,
  `no_surat` varchar(100) DEFAULT NULL,
  `isi` text DEFAULT NULL,
  `kode` varchar(50) DEFAULT NULL,
  `indeks` varchar(100) DEFAULT NULL,
  `tgl_surat` date DEFAULT NULL,
  `tgl_diterima` date DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `keterangan` text DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_surat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `surat_masuk`
--

LOCK TABLES `surat_masuk` WRITE;
/*!40000 ALTER TABLE `surat_masuk` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `surat_masuk` VALUES
(1,'A01','Solo','B12','Ayam Suir','K-01 - Keuangan','Rahasia','2025-10-22','2025-10-31','Document Rahasia','Burik',2),
(2,'A736','DPR','K076','Kasus Korupsi','U-04 - Umum','Pemasaran','2025-10-01','2026-10-09','Document','No Money No Work',3);
/*!40000 ALTER TABLE `surat_masuk` ENABLE KEYS */;
UNLOCK TABLES;
commit;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2025-11-06 23:25:01
