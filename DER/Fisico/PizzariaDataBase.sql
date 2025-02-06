-- MySQL dump 10.13  Distrib 8.4.4, for Linux (x86_64)
--
-- Host: localhost    Database: PizzariaData
-- ------------------------------------------------------
-- Server version	8.4.4

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Acompanhamento`
--

DROP TABLE IF EXISTS `Acompanhamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Acompanhamento` (
  `nome` varchar(100) NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `preco` float NOT NULL,
  `obs` varchar(1000) NOT NULL,
  `idAcom` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idAcom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Acompanhamento`
--

LOCK TABLES `Acompanhamento` WRITE;
/*!40000 ALTER TABLE `Acompanhamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `Acompanhamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `nome` varchar(255) NOT NULL,
  `numeroResidencia` int NOT NULL,
  `obs` varchar(1000) DEFAULT NULL,
  `nomeRua` varchar(255) DEFAULT NULL,
  `aptoNumero` int DEFAULT NULL,
  `bairro` varchar(500) NOT NULL,
  `idCliente` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idCliente`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `idItem` int NOT NULL AUTO_INCREMENT,
  `idPizza` int DEFAULT NULL,
  `idAcom` int DEFAULT NULL,
  `idPedido` int NOT NULL,
  `idUsuario` int NOT NULL,
  PRIMARY KEY (`idItem`),
  KEY `idPizza` (`idPizza`),
  KEY `idAcom` (`idAcom`),
  KEY `idPedido` (`idPedido`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`idPizza`) REFERENCES `pizza` (`IdPizza`) ON DELETE SET NULL,
  CONSTRAINT `item_ibfk_2` FOREIGN KEY (`idAcom`) REFERENCES `Acompanhamento` (`idAcom`) ON DELETE SET NULL,
  CONSTRAINT `item_ibfk_3` FOREIGN KEY (`idPedido`) REFERENCES `pedido` (`idPedido`),
  CONSTRAINT `item_ibfk_4` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`IdUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `motoBoy`
--

DROP TABLE IF EXISTS `motoBoy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `motoBoy` (
  `nome` varchar(200) NOT NULL,
  `emRota` tinyint(1) DEFAULT '0',
  `idMotoboy` int NOT NULL AUTO_INCREMENT,
  `idPedido` int NOT NULL,
  `IdUsuario` int NOT NULL,
  PRIMARY KEY (`idMotoboy`),
  KEY `idPedido` (`idPedido`),
  KEY `IdUsuario` (`IdUsuario`),
  CONSTRAINT `motoBoy_ibfk_1` FOREIGN KEY (`idPedido`) REFERENCES `pedido` (`idPedido`),
  CONSTRAINT `motoBoy_ibfk_2` FOREIGN KEY (`IdUsuario`) REFERENCES `usuario` (`IdUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `motoBoy`
--

LOCK TABLES `motoBoy` WRITE;
/*!40000 ALTER TABLE `motoBoy` DISABLE KEYS */;
/*!40000 ALTER TABLE `motoBoy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `idPedido` int NOT NULL AUTO_INCREMENT,
  `idCliente` int NOT NULL,
  `formaDePagamento` varchar(255) NOT NULL,
  `valorTotal` float NOT NULL,
  `tempoDeEspera` time NOT NULL,
  `dinheiro` tinyint(1) DEFAULT '0',
  `flag` varchar(50) NOT NULL DEFAULT 'preparo',
  `PIX` tinyint(1) DEFAULT '0',
  `pagamentoConfirmado` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`idPedido`),
  KEY `idCliente` (`idCliente`),
  CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizza`
--

DROP TABLE IF EXISTS `pizza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pizza` (
  `IdPizza` int NOT NULL AUTO_INCREMENT,
  `Sabor` varchar(100) NOT NULL,
  `Preco` float NOT NULL,
  `tipo` varchar(100) NOT NULL,
  PRIMARY KEY (`IdPizza`),
  UNIQUE KEY `Sabor` (`Sabor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza`
--

LOCK TABLES `pizza` WRITE;
/*!40000 ALTER TABLE `pizza` DISABLE KEYS */;
/*!40000 ALTER TABLE `pizza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rota`
--

DROP TABLE IF EXISTS `rota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rota` (
  `idRota` int NOT NULL AUTO_INCREMENT,
  `idMotoBoy` int NOT NULL,
  `idCliente` int NOT NULL,
  PRIMARY KEY (`idRota`),
  KEY `idMotoBoy` (`idMotoBoy`),
  KEY `idCliente` (`idCliente`),
  CONSTRAINT `rota_ibfk_1` FOREIGN KEY (`idMotoBoy`) REFERENCES `motoBoy` (`idMotoboy`),
  CONSTRAINT `rota_ibfk_2` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rota`
--

LOCK TABLES `rota` WRITE;
/*!40000 ALTER TABLE `rota` DISABLE KEYS */;
/*!40000 ALTER TABLE `rota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `flag` varchar(255) DEFAULT 'admin',
  `nome` varchar(255) NOT NULL,
  `cargo` varchar(500) NOT NULL,
  `limiteSaborPizza` int DEFAULT '2',
  `IdUsuario` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`IdUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-06 10:30:18
