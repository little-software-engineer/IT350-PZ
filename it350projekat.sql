-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 15, 2022 at 05:12 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `it350projekat`
--

-- --------------------------------------------------------

--
-- Table structure for table `adresa`
--

CREATE TABLE `adresa` (
  `ID_ADRESA` int(11) NOT NULL,
  `ID_OPSTINA` int(11) NOT NULL,
  `BROJ_ZGRADE` int(11) NOT NULL,
  `IME_ULICE` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- --------------------------------------------------------

--
-- Table structure for table `biblioteka`
--

CREATE TABLE `biblioteka` (
  `ID_BIBLIOTEKA` int(11) NOT NULL,
  `IME` varchar(200) NOT NULL,
  `ID_ADRESA` int(11) NOT NULL,
  `BROJ_ZGRADE` int(11) NOT NULL,
  `ID_OPSTINA` int(11) NOT NULL,
  `ID_REGION` int(11) NOT NULL,
  `KAPACITET` int(11) NOT NULL,
  `BROJ_IZDANJA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- --------------------------------------------------------

--
-- Table structure for table `iznajmljivanje`
--

CREATE TABLE `iznajmljivanje` (
  `ID_IZNAJMLJIVANJA` int(11) NOT NULL,
  `ID_KORISNIK` int(11) NOT NULL,
  `ROK_VRACANJA` varchar(40) NOT NULL,
  `ID_KNJIGA` int(4) NOT NULL,
  `BROJ_IZN_KNJIGA` int(11) NOT NULL,
  `ID_BIBLIOTEKA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- --------------------------------------------------------

--
-- Table structure for table `knjiga`
--

CREATE TABLE `knjiga` (
  `ID_KNJIGA` int(4) NOT NULL,
  `ID_BIBLIOTEKA` int(11) NOT NULL,
  `ID_IZNAJMLJIVANJA` int(11) NOT NULL,
  `ISBN` varchar(200) NOT NULL,
  `IME` varchar(200) NOT NULL,
  `GODINA_IZDAVANJA` int(11) NOT NULL,
  `ID_PISAC` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `ID_KORISNIK` int(11) NOT NULL,
  `KORISNICKO_IME` varchar(200) NOT NULL,
  `SIFRA` varchar(200) NOT NULL,
  `ID_BIBLIOTEKA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- --------------------------------------------------------

--
-- Table structure for table `opstina`
--

CREATE TABLE `opstina` (
  `ID_OPSTINA` int(11) NOT NULL,
  `ID_REGION` int(11) NOT NULL,
  `NAZIV` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- --------------------------------------------------------

--
-- Table structure for table `pisac`
--

CREATE TABLE `pisac` (
  `ID_PISAC` int(11) NOT NULL,
  `OPIS` varchar(4096) NOT NULL,
  `IME` varchar(30) NOT NULL,
  `BROJ_PISACA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- --------------------------------------------------------

--
-- Table structure for table `region`
--

CREATE TABLE `region` (
  `ID_REGION` int(11) NOT NULL,
  `NAZIV` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- --------------------------------------------------------

--
-- Table structure for table `zaposleni`
--

CREATE TABLE `zaposleni` (
  `ID_ZAPOSLENI` int(11) NOT NULL,
  `ID_BIBLIOTEKA` int(11) NOT NULL,
  `POZICIJA` varchar(32) NOT NULL,
  `IME` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Indexes for dumped tables
--

--
-- Indexes for table `adresa`
--
ALTER TABLE `adresa`
  ADD PRIMARY KEY (`ID_ADRESA`),
  ADD KEY `ID_OPSTINA` (`ID_OPSTINA`);

--
-- Indexes for table `biblioteka`
--
ALTER TABLE `biblioteka`
  ADD PRIMARY KEY (`ID_BIBLIOTEKA`),
  ADD KEY `ID_OPSTINA` (`ID_OPSTINA`),
  ADD KEY `ID_ADRESA` (`ID_ADRESA`),
  ADD KEY `region_ibfk_3` (`ID_REGION`);

--
-- Indexes for table `iznajmljivanje`
--
ALTER TABLE `iznajmljivanje`
  ADD PRIMARY KEY (`ID_IZNAJMLJIVANJA`),
  ADD KEY `ID_KNJIGA` (`ID_KNJIGA`),
  ADD KEY `ID_BIBLIOTEKA` (`ID_BIBLIOTEKA`),
  ADD KEY `ID_KORISNIK` (`ID_KORISNIK`);

--
-- Indexes for table `knjiga`
--
ALTER TABLE `knjiga`
  ADD PRIMARY KEY (`ID_KNJIGA`),
  ADD KEY `ID_BIBLIOTEKA` (`ID_BIBLIOTEKA`),
  ADD KEY `ID_IZNAJMLJIVANJA` (`ID_IZNAJMLJIVANJA`),
  ADD KEY `ID_PISAC` (`ID_PISAC`);

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`ID_KORISNIK`),
  ADD KEY `ID_BIBLIOTEKA` (`ID_BIBLIOTEKA`);

--
-- Indexes for table `opstina`
--
ALTER TABLE `opstina`
  ADD PRIMARY KEY (`ID_OPSTINA`),
  ADD KEY `ID_REGION` (`ID_REGION`);

--
-- Indexes for table `pisac`
--
ALTER TABLE `pisac`
  ADD PRIMARY KEY (`ID_PISAC`);

--
-- Indexes for table `region`
--
ALTER TABLE `region`
  ADD PRIMARY KEY (`ID_REGION`);

--
-- Indexes for table `zaposleni`
--
ALTER TABLE `zaposleni`
  ADD PRIMARY KEY (`ID_ZAPOSLENI`),
  ADD KEY `ID_BIBLIOTEKA` (`ID_BIBLIOTEKA`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `adresa`
--
ALTER TABLE `adresa`
  MODIFY `ID_ADRESA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `biblioteka`
--
ALTER TABLE `biblioteka`
  MODIFY `ID_BIBLIOTEKA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `iznajmljivanje`
--
ALTER TABLE `iznajmljivanje`
  MODIFY `ID_IZNAJMLJIVANJA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `knjiga`
--
ALTER TABLE `knjiga`
  MODIFY `ID_KNJIGA` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `ID_KORISNIK` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `opstina`
--
ALTER TABLE `opstina`
  MODIFY `ID_OPSTINA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `pisac`
--
ALTER TABLE `pisac`
  MODIFY `ID_PISAC` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `region`
--
ALTER TABLE `region`
  MODIFY `ID_REGION` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `zaposleni`
--
ALTER TABLE `zaposleni`
  MODIFY `ID_ZAPOSLENI` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `adresa`
--
ALTER TABLE `adresa`
  ADD CONSTRAINT `adresa_ibfk_1` FOREIGN KEY (`ID_OPSTINA`) REFERENCES `opstina` (`ID_OPSTINA`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `biblioteka`
--
ALTER TABLE `biblioteka`
  ADD CONSTRAINT `biblioteka_ibfk_1` FOREIGN KEY (`ID_OPSTINA`) REFERENCES `opstina` (`ID_OPSTINA`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `biblioteka_ibfk_2` FOREIGN KEY (`ID_ADRESA`) REFERENCES `adresa` (`ID_ADRESA`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `region_ibfk_3` FOREIGN KEY (`ID_REGION`) REFERENCES `region` (`ID_REGION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `iznajmljivanje`
--
ALTER TABLE `iznajmljivanje`
  ADD CONSTRAINT `biblioteka_ibfk_3` FOREIGN KEY (`ID_BIBLIOTEKA`) REFERENCES `biblioteka` (`ID_BIBLIOTEKA`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `iznajmljivanje_ibfk_1` FOREIGN KEY (`ID_KNJIGA`) REFERENCES `knjiga` (`ID_KNJIGA`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `iznajmljivanje_ibfk_2` FOREIGN KEY (`ID_KORISNIK`) REFERENCES `korisnik` (`ID_KORISNIK`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `knjiga`
--
ALTER TABLE `knjiga`
  ADD CONSTRAINT `knjiga_ibfk_1` FOREIGN KEY (`ID_BIBLIOTEKA`) REFERENCES `biblioteka` (`ID_BIBLIOTEKA`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `knjiga_ibfk_2` FOREIGN KEY (`ID_IZNAJMLJIVANJA`) REFERENCES `iznajmljivanje` (`ID_IZNAJMLJIVANJA`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `knjiga_ibfk_3` FOREIGN KEY (`ID_PISAC`) REFERENCES `pisac` (`ID_PISAC`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD CONSTRAINT `korisnik_ibfk_1` FOREIGN KEY (`ID_BIBLIOTEKA`) REFERENCES `biblioteka` (`ID_BIBLIOTEKA`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `opstina`
--
ALTER TABLE `opstina`
  ADD CONSTRAINT `opstina_ibfk_1` FOREIGN KEY (`ID_REGION`) REFERENCES `region` (`ID_REGION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `zaposleni`
--
ALTER TABLE `zaposleni`
  ADD CONSTRAINT `zaposleni_ibfk_1` FOREIGN KEY (`ID_BIBLIOTEKA`) REFERENCES `biblioteka` (`ID_BIBLIOTEKA`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
