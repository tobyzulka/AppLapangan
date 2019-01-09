-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Waktu pembuatan: 09 Jan 2019 pada 10.43
-- Versi server: 5.7.23
-- Versi PHP: 5.6.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sw_laps`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `lapangan`
--

DROP TABLE IF EXISTS `lapangan`;
CREATE TABLE IF NOT EXISTS `lapangan` (
  `kd_lapangan` varchar(5) COLLATE latin1_general_ci NOT NULL,
  `nama_lapangan` varchar(10) COLLATE latin1_general_ci NOT NULL,
  `harga_weekday` double NOT NULL,
  `harga_weekend` double NOT NULL,
  `id_user` varchar(12) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`kd_lapangan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data untuk tabel `lapangan`
--

INSERT INTO `lapangan` (`kd_lapangan`, `nama_lapangan`, `harga_weekday`, `harga_weekend`, `id_user`) VALUES
('LP001', 'Garuda', 100000, 120000, NULL),
('LP002', 'Biasa', 80000, 100000, NULL),
('LP003', 'Krakatau', 100000, 110000, 'ADM001');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pelanggan`
--

DROP TABLE IF EXISTS `pelanggan`;
CREATE TABLE IF NOT EXISTS `pelanggan` (
  `id_pelanggan` varchar(12) COLLATE latin1_general_ci NOT NULL,
  `nama_pelanggan` varchar(40) COLLATE latin1_general_ci NOT NULL,
  `email_pelanggan` varchar(20) COLLATE latin1_general_ci DEFAULT NULL,
  `telepon_pelanggan` varchar(16) COLLATE latin1_general_ci NOT NULL,
  `tgl_daftar` date DEFAULT NULL,
  `alamat_pelanggan` varchar(150) COLLATE latin1_general_ci NOT NULL,
  `id_user` varchar(12) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_pelanggan`),
  KEY `fk_id_operator_pl` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data untuk tabel `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama_pelanggan`, `email_pelanggan`, `telepon_pelanggan`, `tgl_daftar`, `alamat_pelanggan`, `id_user`) VALUES
('PL00001', 'Dwi', 'dwi@example.com', '0812323123', '2019-01-08', 'Bukit Permai Bekasi', 'ADM001'),
('PL00002', 'Andri', 'andri@example.com', '08123123123', '2019-01-08', 'Rawa Domba Duren Sawit', 'ADM001'),
('PL00003', 'Fajar', 'fajar@example.com', '0812312312', '2019-01-08', 'Lubang Buaya', 'ADM002');

-- --------------------------------------------------------

--
-- Struktur dari tabel `sewa`
--

DROP TABLE IF EXISTS `sewa`;
CREATE TABLE IF NOT EXISTS `sewa` (
  `kd_booking` varchar(16) COLLATE latin1_general_ci NOT NULL,
  `status` varchar(12) COLLATE latin1_general_ci NOT NULL,
  `kd_lapangan` varchar(5) COLLATE latin1_general_ci DEFAULT NULL,
  `nama_penyewa` varchar(30) COLLATE latin1_general_ci NOT NULL,
  `tgl_sewa` date NOT NULL,
  `jam_sewa` varchar(8) COLLATE latin1_general_ci NOT NULL,
  `lama_sewa` int(11) NOT NULL,
  `harga` double NOT NULL,
  `total_harga` double NOT NULL,
  `bayar` double DEFAULT NULL,
  `sisa` double DEFAULT NULL,
  `id_pelanggan` varchar(12) COLLATE latin1_general_ci DEFAULT NULL,
  `kd_tim` varchar(12) COLLATE latin1_general_ci DEFAULT NULL,
  `id_user` varchar(12) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`kd_booking`),
  KEY `fk_id_operator_sw` (`id_user`),
  KEY `fk_kd_lapangan_sw` (`kd_lapangan`),
  KEY `fk_kd_tim_sw` (`kd_tim`),
  KEY `fk_id_pelanggan_sw` (`id_pelanggan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data untuk tabel `sewa`
--

INSERT INTO `sewa` (`kd_booking`, `status`, `kd_lapangan`, `nama_penyewa`, `tgl_sewa`, `jam_sewa`, `lama_sewa`, `harga`, `total_harga`, `bayar`, `sisa`, `id_pelanggan`, `kd_tim`, `id_user`) VALUES
('B190109001', 'Lunas', 'LP003', 'awan', '2019-01-10', '15:00:00', 2, 110000, 220000, 220000, 0, 'null', 'null', 'ADM001'),
('B190109002', 'Lunas', 'LP001', 'Jaguar', '2019-01-13', '08:00:00', 3, 120000, 360000, 360000, 0, 'null', 'TIM00001', 'ADM001'),
('B190109003', 'Lunas', 'LP002', 'Fajar', '2019-01-12', '09:00:00', 1, 100000, 100000, 100000, 0, 'PL00003', 'null', 'ADM001'),
('B190109004', 'Belum Lunas', 'LP003', 'Fajar', '2019-01-12', '08:00:00', 1, 110000, 110000, 80000, 30000, 'PL00003', 'null', 'ADM001');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tim`
--

DROP TABLE IF EXISTS `tim`;
CREATE TABLE IF NOT EXISTS `tim` (
  `kd_tim` varchar(12) COLLATE latin1_general_ci NOT NULL,
  `nama_tim` varchar(10) COLLATE latin1_general_ci NOT NULL,
  `nama_manager_tim` varchar(10) COLLATE latin1_general_ci NOT NULL,
  `email_tim` varchar(30) COLLATE latin1_general_ci NOT NULL,
  `telepon_tim` varchar(16) COLLATE latin1_general_ci NOT NULL,
  `tgl_daftar` date NOT NULL,
  `alamat_tim` varchar(150) COLLATE latin1_general_ci NOT NULL,
  `id_user` varchar(12) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`kd_tim`),
  KEY `fk_id_operator_tm` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data untuk tabel `tim`
--

INSERT INTO `tim` (`kd_tim`, `nama_tim`, `nama_manager_tim`, `email_tim`, `telepon_tim`, `tgl_daftar`, `alamat_tim`, `id_user`) VALUES
('TIM00001', 'Jaguar', 'Dwi', 'dwi@jagauar.com', '08123123123121', '2019-01-08', 'Jati Makmur Bekasi', 'ADM002');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` varchar(12) COLLATE latin1_general_ci NOT NULL,
  `hak_akses` varchar(10) COLLATE latin1_general_ci NOT NULL,
  `nama_user` varchar(40) COLLATE latin1_general_ci NOT NULL,
  `alamat_user` varchar(150) COLLATE latin1_general_ci NOT NULL,
  `telepon_user` varchar(16) COLLATE latin1_general_ci NOT NULL,
  `username` varchar(16) COLLATE latin1_general_ci NOT NULL,
  `password` varchar(16) COLLATE latin1_general_ci NOT NULL,
  `status` varchar(10) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`id_user`),
  KEY `fk_id_pemilik_op` (`hak_akses`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id_user`, `hak_akses`, `nama_user`, `alamat_user`, `telepon_user`, `username`, `password`, `status`) VALUES
('ADM001', 'Pemilik', 'Toby', 'Cilangkap', '082212313', 'admin', 'admin', 'Aktif'),
('ADM002', 'Operator', 'Aldi', 'Cipinang Elok	', '8123123123', 'aldi', 'aldi', 'Aktif');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
