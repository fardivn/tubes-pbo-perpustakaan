-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 02, 2023 at 07:00 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_perpuspbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `koleksi`
--

CREATE TABLE `koleksi` (
  `id` int(11) NOT NULL,
  `judul` varchar(255) NOT NULL,
  `kreator` varchar(100) NOT NULL,
  `tahun` year(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `koleksi`
--

INSERT INTO `koleksi` (`id`, `judul`, `kreator`, `tahun`) VALUES
(2, 'It Ends with Us', 'Colleen Hoover', 2016),
(3, 'Bumi', 'Tere Liye', 2014),
(4, 'Haikyuu!! - Fly High! Volleyball! 12', 'Haruichi Furudate', 2018),
(5, 'Spy x Family 01', 'Endou Tatsuya', 2020),
(6, 'Spy x Family 07', 'Endou Tatsuya', 2022),
(7, 'Glass', 'Shyamalan', 2019),
(9, 'Detektif Conan Vol. 100', 'Aoyama Gosho', 2022),
(10, 'Laskar Pelangi', 'Andrea Hirata', 2011),
(11, 'Blue Lock 01', 'Muneyuki Kaneshiro', 2022),
(12, 'Old', 'M. Night Syahmalan', 2021),
(13, 'Glass', 'Shyamalan', 2019),
(14, 'A Place Called Perfect', 'Helena Duggan', 2022),
(17, 'Sang-Chi and the Legend of the Ten Rings', 'Destin Daniel Cretton', 2021),
(18, 'Clifford the Big Red Dog', 'Walt Becker', 2021),
(19, 'Art Insight: Teror Mental Putu Jaya', 'Hilda Rachmawati', 2019),
(20, 'Melawan Lupa: Titik Pijak Pemajuan Kebudayaan Kita', 'Edi Ginting', 2018),
(21, 'Laut Bercerita', 'Leila S. Chudori', 2017);

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `no_member` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `isAktif` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`no_member`, `nama`, `isAktif`) VALUES
(1, 'Farah', 1),
(2, 'Norra', 1),
(3, 'Adel', 0),
(4, 'Lea', 1),
(5, 'Kevin', 0),
(6, 'Kenzo', 1);

-- --------------------------------------------------------

--
-- Table structure for table `monograf`
--

CREATE TABLE `monograf` (
  `id_koleksi` int(11) NOT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `monograf`
--

INSERT INTO `monograf` (`id_koleksi`, `isbn`, `jumlah`) VALUES
(2, '9781501110368', 4),
(3, '9786239726263', 5),
(4, '9786024288136', 5),
(5, '9786230021312', 4),
(6, '9786230035944', 5),
(9, '9786230029974', 3),
(10, '9789793062792', 4),
(11, '9786230029974', 3),
(14, '9786230402005', 0),
(21, '9786024246952', 2);

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE `pegawai` (
  `id_pegawai` int(11) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nama` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`id_pegawai`, `password`, `nama`) VALUES
(1, 'pbo123', 'Farah Diva Nabila'),
(2, '123', 'Koushi'),
(3, 'asd', 'Daichi');

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE `peminjaman` (
  `no` int(11) NOT NULL,
  `id_koleksi` int(11) NOT NULL,
  `no_member` int(11) NOT NULL,
  `tgl_pinjam` date NOT NULL,
  `tgl_tenggat` date NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `peminjaman`
--

INSERT INTO `peminjaman` (`no`, `id_koleksi`, `no_member`, `tgl_pinjam`, `tgl_tenggat`, `status`) VALUES
(2, 4, 1, '2022-12-28', '2023-01-11', 'Kembali'),
(5, 5, 2, '2023-01-02', '2023-01-09', 'Dipinjam'),
(6, 10, 6, '2023-01-01', '2023-01-08', 'Dipinjam');

-- --------------------------------------------------------

--
-- Table structure for table `penyalinan`
--

CREATE TABLE `penyalinan` (
  `no` int(11) NOT NULL,
  `id_koleksi` int(11) NOT NULL,
  `no_member` int(11) NOT NULL,
  `tgl_penyalinan` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `penyalinan`
--

INSERT INTO `penyalinan` (`no`, `id_koleksi`, `no_member`, `tgl_penyalinan`) VALUES
(2, 18, 6, '2023-01-02'),
(3, 17, 2, '2022-12-30'),
(4, 20, 4, '2022-12-23');

-- --------------------------------------------------------

--
-- Table structure for table `video`
--

CREATE TABLE `video` (
  `id_koleksi` int(11) NOT NULL,
  `durasiMenit` int(11) NOT NULL,
  `isPublik` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `video`
--

INSERT INTO `video` (`id_koleksi`, `durasiMenit`, `isPublik`) VALUES
(7, 120, 1),
(12, 75, 0),
(17, 132, 1),
(18, 97, 1),
(19, 134, 0),
(20, 94, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `koleksi`
--
ALTER TABLE `koleksi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`no_member`);

--
-- Indexes for table `monograf`
--
ALTER TABLE `monograf`
  ADD PRIMARY KEY (`id_koleksi`),
  ADD KEY `fk_monograf_koleksi_idx` (`id_koleksi`);

--
-- Indexes for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`id_pegawai`);

--
-- Indexes for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`no`),
  ADD KEY `fk_peminjaman_monograf1_idx` (`id_koleksi`),
  ADD KEY `fk_peminjaman_member1_idx` (`no_member`);

--
-- Indexes for table `penyalinan`
--
ALTER TABLE `penyalinan`
  ADD PRIMARY KEY (`no`),
  ADD KEY `fk_member_has_video_video1_idx` (`id_koleksi`),
  ADD KEY `fk_member_has_video_member1_idx` (`no_member`);

--
-- Indexes for table `video`
--
ALTER TABLE `video`
  ADD PRIMARY KEY (`id_koleksi`),
  ADD KEY `fk_video_koleksi1_idx` (`id_koleksi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `koleksi`
--
ALTER TABLE `koleksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `no_member` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `pegawai`
--
ALTER TABLE `pegawai`
  MODIFY `id_pegawai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `peminjaman`
--
ALTER TABLE `peminjaman`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `penyalinan`
--
ALTER TABLE `penyalinan`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `monograf`
--
ALTER TABLE `monograf`
  ADD CONSTRAINT `fk_monograf_koleksi` FOREIGN KEY (`id_koleksi`) REFERENCES `koleksi` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD CONSTRAINT `fk_peminjaman_member1` FOREIGN KEY (`no_member`) REFERENCES `member` (`no_member`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_peminjaman_monograf1` FOREIGN KEY (`id_koleksi`) REFERENCES `monograf` (`id_koleksi`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `penyalinan`
--
ALTER TABLE `penyalinan`
  ADD CONSTRAINT `fk_member_has_video_member1` FOREIGN KEY (`no_member`) REFERENCES `member` (`no_member`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_member_has_video_video1` FOREIGN KEY (`id_koleksi`) REFERENCES `video` (`id_koleksi`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `video`
--
ALTER TABLE `video`
  ADD CONSTRAINT `fk_video_koleksi1` FOREIGN KEY (`id_koleksi`) REFERENCES `koleksi` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
