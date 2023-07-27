/*
SQLyog Ultimate v12.4.3 (64 bit)
MySQL - 10.4.25-MariaDB : Database - db_toko_pbo_if21c
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_toko_pbo_if21c` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `db_toko_pbo_if21c`;

/*Table structure for table `arfrvi_barang` */

DROP TABLE IF EXISTS `arfrvi_barang`;

CREATE TABLE `arfrvi_barang` (
  `kd_brg` varchar(10) NOT NULL,
  `nm_brg` varchar(20) DEFAULT NULL,
  `diskon` float DEFAULT NULL,
  `hrg_jual` float DEFAULT NULL,
  `jumlah` float DEFAULT NULL,
  `rusak` float DEFAULT NULL,
  `sisa` float DEFAULT NULL,
  PRIMARY KEY (`kd_brg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `arfrvi_barang` */

insert  into `arfrvi_barang`(`kd_brg`,`nm_brg`,`diskon`,`hrg_jual`,`jumlah`,`rusak`,`sisa`) values 
('B001',' Sepeda',10,1000000,10,2,8),
('B002','Meja',20,200000,10,4,6),
('B003','Kursi',10,150000,10,1,9),
('B004','Buku',10,100000,10,1,9),
('B005','Handphone',15,2000000,5,0,5),
('B006','Pena',0,3000,20,2,18),
('B007',' Printer',20,1000000,9,0,9),
('B008','Laptop',15,6000000,5,0,5),
('B009','Monitor',5,4000000,5,0,5),
('B010','Masker',0,30000,15,0,15),
('B011',' Tisu Basah',5,10000,5,2,3);

/*Table structure for table `arfrvi_jual` */

DROP TABLE IF EXISTS `arfrvi_jual`;

CREATE TABLE `arfrvi_jual` (
  `arf_kd_brg` varchar(10) NOT NULL,
  `arf_jml` int(11) DEFAULT NULL,
  `arf_kd_peg` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`arf_kd_brg`),
  KEY `arf_kd_peg` (`arf_kd_peg`),
  CONSTRAINT `arfrvi_jual_ibfk_1` FOREIGN KEY (`arf_kd_peg`) REFERENCES `arfrvi_pegawai` (`arf_kd_peg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `arfrvi_jual` */

/*Table structure for table `arfrvi_login` */

DROP TABLE IF EXISTS `arfrvi_login`;

CREATE TABLE `arfrvi_login` (
  `arf_kd_peg` int(10) NOT NULL AUTO_INCREMENT,
  `arf_username` varchar(20) DEFAULT NULL,
  `arf_password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`arf_kd_peg`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `arfrvi_login` */

insert  into `arfrvi_login`(`arf_kd_peg`,`arf_username`,`arf_password`) values 
(1,'Arif','123');

/*Table structure for table `arfrvi_pegawai` */

DROP TABLE IF EXISTS `arfrvi_pegawai`;

CREATE TABLE `arfrvi_pegawai` (
  `arf_kd_peg` varchar(10) NOT NULL,
  `arf_nm_peg` varchar(30) DEFAULT NULL,
  `arf_temp_lhr` varchar(20) DEFAULT NULL,
  `arf_tgl_lahir` date DEFAULT NULL,
  `arf_pend` varchar(20) DEFAULT NULL,
  `arf_jab` varchar(20) DEFAULT NULL,
  `arf_no_telp` varchar(15) DEFAULT NULL,
  `arf_alamat` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`arf_kd_peg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `arfrvi_pegawai` */

insert  into `arfrvi_pegawai`(`arf_kd_peg`,`arf_nm_peg`,`arf_temp_lhr`,`arf_tgl_lahir`,`arf_pend`,`arf_jab`,`arf_no_telp`,`arf_alamat`) values 
('B001','Arif','Bandar Lampung','1999-09-09','- SMP','- Pegawai','099999','JL.Karet'),
('B002','Arif Rivai','Bandar Lampung','1999-09-09','- SMA','- Kasir','099999','JL.Karet'),
('B003','Andi','Bandar Lampung','2003-09-10','- SMA','- Kasir','0896722210','Jl. Mawar'),
('B004','Adi','Gunter','2000-05-05','- SMA','- Satpam','088881233','Jl Bunga'),
('B005','Jesi','Tanjung Karang','2001-08-09','- SMA','- Kasir','089233145678','Jl. Kencana'),
('B006','Asik','Tanjung Karang','2001-08-09','- SMA','- Kasir','089233145678','Jl. Kencana'),
('B008','Pio','Tanjung Karang','2001-08-09','- SMA','- Kasir','089233145678','Jl. Kencana'),
('B009','Rio','Tanjung Karang','2001-08-09','- SMA','- Kasir','089233145678','Jl. Kencana'),
('B010','Gian','Tanjung Karang','2001-08-09','- SMA','- Kasir','089233145678','Jl. Kencana'),
('P011','Supardi','Kuningan','1990-11-02','- SMA','- Pegawai','0897723566','Jl. Diponegoro');

/*Table structure for table `arfrvi_pembelian` */

DROP TABLE IF EXISTS `arfrvi_pembelian`;

CREATE TABLE `arfrvi_pembelian` (
  `arf_kd_beli` varchar(10) NOT NULL,
  `arf_tgl_beli` date DEFAULT NULL,
  `arf_kd_sup` varchar(10) DEFAULT NULL,
  `kd_brg` varchar(10) DEFAULT NULL,
  `arf_jum_beli` float DEFAULT NULL,
  `arf_hrg_beli` float DEFAULT NULL,
  PRIMARY KEY (`arf_kd_beli`),
  KEY `arf_kd_sup` (`arf_kd_sup`),
  KEY `kd_brg` (`kd_brg`),
  CONSTRAINT `arfrvi_pembelian_ibfk_1` FOREIGN KEY (`arf_kd_sup`) REFERENCES `arfrvi_supplier` (`arf_kd_sup`),
  CONSTRAINT `arfrvi_pembelian_ibfk_2` FOREIGN KEY (`kd_brg`) REFERENCES `arfrvi_barang` (`kd_brg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `arfrvi_pembelian` */

insert  into `arfrvi_pembelian`(`arf_kd_beli`,`arf_tgl_beli`,`arf_kd_sup`,`kd_brg`,`arf_jum_beli`,`arf_hrg_beli`) values 
('B001','2022-11-11','B001','B001',12,200000),
('B002','2022-05-09','B002','B002',12,500000),
('B003','2022-09-01','B003','B003',19,800000),
('B004','2022-08-01','B004','B004',10,90000),
('B005','2022-10-01','B005','B005',30,120000),
('B006','2022-12-30','B006','B006',25,50000),
('B007','2022-05-02','B007','B007',8,1000000),
('B008','2022-05-10','B007','B008',9,800000),
('B009','2022-04-04','B005','B009',15,1000000),
('B010','2022-06-09','B010','B010',5,500000);

/*Table structure for table `arfrvi_penjualan` */

DROP TABLE IF EXISTS `arfrvi_penjualan`;

CREATE TABLE `arfrvi_penjualan` (
  `arf_kd_trans` varchar(10) NOT NULL,
  `arf_tgl_trans` date DEFAULT NULL,
  `arf_kd_brg` varchar(10) DEFAULT NULL,
  `arf_jml` float DEFAULT NULL,
  `arf_kd_peg` varchar(10) DEFAULT NULL,
  `arf_tot_hrg` float DEFAULT NULL,
  PRIMARY KEY (`arf_kd_trans`),
  KEY `kd_brg` (`arf_kd_brg`),
  KEY `arf_kd_peg` (`arf_kd_peg`),
  CONSTRAINT `arfrvi_penjualan_ibfk_1` FOREIGN KEY (`arf_kd_brg`) REFERENCES `arfrvi_barang` (`kd_brg`),
  CONSTRAINT `arfrvi_penjualan_ibfk_2` FOREIGN KEY (`arf_kd_peg`) REFERENCES `arfrvi_pegawai` (`arf_kd_peg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `arfrvi_penjualan` */

insert  into `arfrvi_penjualan`(`arf_kd_trans`,`arf_tgl_trans`,`arf_kd_brg`,`arf_jml`,`arf_kd_peg`,`arf_tot_hrg`) values 
('112030808','2022-09-02','B010',4,'B010',100000),
('1122233','2022-10-21','B001',5,'B001',500000),
('11992200','2022-09-10','B002',2,'B002',200000),
('123077','2022-01-25','B009',2,'B008',3000000),
('1239900','2022-03-09','B006',10,'B006',100000),
('22119900','2022-09-10','B005',1,'B005',2000000),
('88779911','2022-01-10','B004',10,'B004',900000),
('99008822','2022-09-10','B003',5,'B003',1500000),
('9987612','2022-12-20','B007',1,'B008',3000000);

/*Table structure for table `arfrvi_supplier` */

DROP TABLE IF EXISTS `arfrvi_supplier`;

CREATE TABLE `arfrvi_supplier` (
  `arf_kd_sup` varchar(10) NOT NULL,
  `arf_nm_per` varchar(20) DEFAULT NULL,
  `arf_nm_pen` varchar(20) DEFAULT NULL,
  `arf_no_telp` varchar(15) DEFAULT NULL,
  `arf_email` varchar(20) DEFAULT NULL,
  `arf_alamat` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`arf_kd_sup`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `arfrvi_supplier` */

insert  into `arfrvi_supplier`(`arf_kd_sup`,`arf_nm_per`,`arf_nm_pen`,`arf_no_telp`,`arf_email`,`arf_alamat`) values 
('B001','Close The Door','Dedy','081275243395','dedy@gmail.com','Jl.Kepang sari'),
('B002','Rans','Raffi','089723457791','raffi@yahoo.com','Jl.Andara No18 A'),
('B003','PT Ruang Angkasa','Juwanto','089722143565','jwnt@gmail.com','Jl. Hajimena'),
('B004','PT Angkasa','Juan','089722143565','juan@gmail.com','Jl. Hajimena'),
('B005','PT Pendidikan','Sri','0896612398','sr@gmail.com','Teluk'),
('B006','PT Sutra','Sugfeng','0896612398','Sugeng@gmail.com','Teluk Betung'),
('B007','PT Guanteng','Siti','0896612398','siti@gmail.com','Teluk Betung'),
('B009','PT Locy','Chikal','089661239098','Locy@gmail.com','Kaliawi'),
('B010','PT Hindia','Aldi','089661230','Aldi@gmail.com','Palapa');

/*Table structure for table `arfrvi_vgabungan3tbl` */

DROP TABLE IF EXISTS `arfrvi_vgabungan3tbl`;

/*!50001 DROP VIEW IF EXISTS `arfrvi_vgabungan3tbl` */;
/*!50001 DROP TABLE IF EXISTS `arfrvi_vgabungan3tbl` */;

/*!50001 CREATE TABLE  `arfrvi_vgabungan3tbl`(
 `arf_kd_peg` varchar(10) ,
 `arf_nm_peg` varchar(30) ,
 `kd_brg` varchar(10) ,
 `nm_brg` varchar(20) ,
 `hrg_jual` float ,
 `arf_tgl_beli` date ,
 `arf_jum_beli` float ,
 `arf_total_beli` double 
)*/;

/*Table structure for table `arfrvi_vgabungan4tbl` */

DROP TABLE IF EXISTS `arfrvi_vgabungan4tbl`;

/*!50001 DROP VIEW IF EXISTS `arfrvi_vgabungan4tbl` */;
/*!50001 DROP TABLE IF EXISTS `arfrvi_vgabungan4tbl` */;

/*!50001 CREATE TABLE  `arfrvi_vgabungan4tbl`(
 `arf_kd_peg` varchar(10) ,
 `arf_nm_peg` varchar(30) ,
 `kd_brg` varchar(10) ,
 `nm_brg` varchar(20) ,
 `hrg_jual` float ,
 `diskon` float ,
 `arf_kd_trans` varchar(10) ,
 `arf_jum_beli` float ,
 `arf_tgl_beli` date ,
 `arfrvi_total_bayar` double 
)*/;

/*Table structure for table `arfrvi_vgabungan5tbl` */

DROP TABLE IF EXISTS `arfrvi_vgabungan5tbl`;

/*!50001 DROP VIEW IF EXISTS `arfrvi_vgabungan5tbl` */;
/*!50001 DROP TABLE IF EXISTS `arfrvi_vgabungan5tbl` */;

/*!50001 CREATE TABLE  `arfrvi_vgabungan5tbl`(
 `arf_kd_peg` varchar(10) ,
 `arf_nm_peg` varchar(30) ,
 `arf_kd_trans` varchar(10) ,
 `kd_brg` varchar(10) ,
 `hrg_jual` float ,
 `diskon` float ,
 `arf_jum_beli` float ,
 `arf_tgl_beli` date ,
 `arf_kd_sup` varchar(10) ,
 `arf_no_telp` varchar(15) ,
 `arfrvi_diskon` double ,
 `arfrvi_total_bayar` double 
)*/;

/*Table structure for table `arfrvi_vsupplierdanpembelian` */

DROP TABLE IF EXISTS `arfrvi_vsupplierdanpembelian`;

/*!50001 DROP VIEW IF EXISTS `arfrvi_vsupplierdanpembelian` */;
/*!50001 DROP TABLE IF EXISTS `arfrvi_vsupplierdanpembelian` */;

/*!50001 CREATE TABLE  `arfrvi_vsupplierdanpembelian`(
 `arf_kd_sup` varchar(10) ,
 `arf_nm_per` varchar(20) ,
 `arf_nm_pen` varchar(20) ,
 `arf_no_telp` varchar(15) ,
 `arf_email` varchar(20) ,
 `arf_alamat` varchar(30) ,
 `arf_kd_beli` varchar(10) ,
 `arf_tgl_beli` date ,
 `arf_jum_beli` float ,
 `arf_hrg_beli` float ,
 `arf_total` double 
)*/;

/*View structure for view arfrvi_vgabungan3tbl */

/*!50001 DROP TABLE IF EXISTS `arfrvi_vgabungan3tbl` */;
/*!50001 DROP VIEW IF EXISTS `arfrvi_vgabungan3tbl` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `arfrvi_vgabungan3tbl` AS (select `arfrvi_pegawai`.`arf_kd_peg` AS `arf_kd_peg`,`arfrvi_pegawai`.`arf_nm_peg` AS `arf_nm_peg`,`arfrvi_barang`.`kd_brg` AS `kd_brg`,`arfrvi_barang`.`nm_brg` AS `nm_brg`,`arfrvi_barang`.`hrg_jual` AS `hrg_jual`,`arfrvi_pembelian`.`arf_tgl_beli` AS `arf_tgl_beli`,`arfrvi_pembelian`.`arf_jum_beli` AS `arf_jum_beli`,`arfrvi_barang`.`hrg_jual` * `arfrvi_pembelian`.`arf_jum_beli` AS `arf_total_beli` from ((`arfrvi_pegawai` join `arfrvi_pembelian` on(`arfrvi_pegawai`.`arf_kd_peg` = `arfrvi_pembelian`.`kd_brg`)) join `arfrvi_barang` on(`arfrvi_barang`.`kd_brg` = `arfrvi_pembelian`.`kd_brg`))) */;

/*View structure for view arfrvi_vgabungan4tbl */

/*!50001 DROP TABLE IF EXISTS `arfrvi_vgabungan4tbl` */;
/*!50001 DROP VIEW IF EXISTS `arfrvi_vgabungan4tbl` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `arfrvi_vgabungan4tbl` AS (select `arfrvi_pegawai`.`arf_kd_peg` AS `arf_kd_peg`,`arfrvi_pegawai`.`arf_nm_peg` AS `arf_nm_peg`,`arfrvi_barang`.`kd_brg` AS `kd_brg`,`arfrvi_barang`.`nm_brg` AS `nm_brg`,`arfrvi_barang`.`hrg_jual` AS `hrg_jual`,`arfrvi_barang`.`diskon` AS `diskon`,`arfrvi_penjualan`.`arf_kd_trans` AS `arf_kd_trans`,`arfrvi_pembelian`.`arf_jum_beli` AS `arf_jum_beli`,`arfrvi_pembelian`.`arf_tgl_beli` AS `arf_tgl_beli`,(`arfrvi_barang`.`hrg_jual` - `arfrvi_barang`.`diskon` / 100 * `arfrvi_barang`.`hrg_jual`) * `arfrvi_pembelian`.`arf_jum_beli` AS `arfrvi_total_bayar` from (((`arfrvi_pegawai` join `arfrvi_barang`) join `arfrvi_penjualan`) join `arfrvi_pembelian`) where `arfrvi_pegawai`.`arf_kd_peg` = `arfrvi_penjualan`.`arf_kd_peg` and `arfrvi_barang`.`kd_brg` = `arfrvi_penjualan`.`arf_kd_brg` and `arfrvi_barang`.`kd_brg` = `arfrvi_pembelian`.`kd_brg` order by `arfrvi_pembelian`.`arf_kd_beli`) */;

/*View structure for view arfrvi_vgabungan5tbl */

/*!50001 DROP TABLE IF EXISTS `arfrvi_vgabungan5tbl` */;
/*!50001 DROP VIEW IF EXISTS `arfrvi_vgabungan5tbl` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `arfrvi_vgabungan5tbl` AS (select `arfrvi_pegawai`.`arf_kd_peg` AS `arf_kd_peg`,`arfrvi_pegawai`.`arf_nm_peg` AS `arf_nm_peg`,`arfrvi_penjualan`.`arf_kd_trans` AS `arf_kd_trans`,`arfrvi_barang`.`kd_brg` AS `kd_brg`,`arfrvi_barang`.`hrg_jual` AS `hrg_jual`,`arfrvi_barang`.`diskon` AS `diskon`,`arfrvi_pembelian`.`arf_jum_beli` AS `arf_jum_beli`,`arfrvi_pembelian`.`arf_tgl_beli` AS `arf_tgl_beli`,`arfrvi_supplier`.`arf_kd_sup` AS `arf_kd_sup`,`arfrvi_supplier`.`arf_no_telp` AS `arf_no_telp`,`arfrvi_barang`.`hrg_jual` * `arfrvi_pembelian`.`arf_jum_beli` * `arfrvi_barang`.`diskon` / 100 AS `arfrvi_diskon`,(`arfrvi_barang`.`hrg_jual` - `arfrvi_barang`.`diskon` / 100 * `arfrvi_barang`.`hrg_jual`) * `arfrvi_pembelian`.`arf_jum_beli` AS `arfrvi_total_bayar` from ((((`arfrvi_pegawai` join `arfrvi_barang`) join `arfrvi_penjualan`) join `arfrvi_pembelian`) join `arfrvi_supplier`) where `arfrvi_pegawai`.`arf_kd_peg` = `arfrvi_penjualan`.`arf_kd_peg` and `arfrvi_barang`.`kd_brg` = `arfrvi_penjualan`.`arf_kd_brg` and `arfrvi_barang`.`kd_brg` = `arfrvi_pembelian`.`kd_brg` and `arfrvi_supplier`.`arf_kd_sup` = `arfrvi_pembelian`.`arf_kd_sup` order by `arfrvi_pembelian`.`arf_kd_beli`) */;

/*View structure for view arfrvi_vsupplierdanpembelian */

/*!50001 DROP TABLE IF EXISTS `arfrvi_vsupplierdanpembelian` */;
/*!50001 DROP VIEW IF EXISTS `arfrvi_vsupplierdanpembelian` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `arfrvi_vsupplierdanpembelian` AS (select `arfrvi_supplier`.`arf_kd_sup` AS `arf_kd_sup`,`arfrvi_supplier`.`arf_nm_per` AS `arf_nm_per`,`arfrvi_supplier`.`arf_nm_pen` AS `arf_nm_pen`,`arfrvi_supplier`.`arf_no_telp` AS `arf_no_telp`,`arfrvi_supplier`.`arf_email` AS `arf_email`,`arfrvi_supplier`.`arf_alamat` AS `arf_alamat`,`arfrvi_pembelian`.`arf_kd_beli` AS `arf_kd_beli`,`arfrvi_pembelian`.`arf_tgl_beli` AS `arf_tgl_beli`,`arfrvi_pembelian`.`arf_jum_beli` AS `arf_jum_beli`,`arfrvi_pembelian`.`arf_hrg_beli` AS `arf_hrg_beli`,`arfrvi_pembelian`.`arf_jum_beli` * `arfrvi_pembelian`.`arf_hrg_beli` AS `arf_total` from (`arfrvi_supplier` join `arfrvi_pembelian` on(`arfrvi_supplier`.`arf_kd_sup` = `arfrvi_pembelian`.`arf_kd_sup`))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
