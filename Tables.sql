CREATE DATABASE  IF NOT EXISTS `thebank` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `thebank`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: thebank
-- ------------------------------------------------------
-- Server version	5.6.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `access`
--

DROP TABLE IF EXISTS `access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `access` (
  `accessID` varchar(50) NOT NULL,
  `accessName` varchar(20) NOT NULL,
  `accessType` varchar(10) NOT NULL,
  `dateCreated` date NOT NULL,
  PRIMARY KEY (`accessID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `access`
--

LOCK TABLES `access` WRITE;
/*!40000 ALTER TABLE `access` DISABLE KEYS */;
INSERT INTO `access` VALUES ('1','viewTrans','NULL','2013-10-17'),('10','authorize','NULL','2013-10-17'),('11','authorizeCTrans','NULL','2013-10-17'),('2','modifyTrans','NULL','2013-10-17'),('3','addUser','NULL','2013-10-17'),('4','deleteUser','NULL','2013-10-17'),('5','addCustomer','NULL','2013-10-17'),('6','deleteCustomer','NULL','2013-10-17'),('7','addAccount','NULL','2013-10-17'),('8','deleteAccount','NULL','2013-10-17'),('9','requestForAuth','NULL','2013-10-17');
/*!40000 ALTER TABLE `access` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `accountID` varchar(50) NOT NULL,
  `accountType` varchar(10) NOT NULL,
  `customerID` varchar(50) NOT NULL,
  `balance` mediumtext NOT NULL,
  PRIMARY KEY (`accountID`),
  KEY `FK_ACCOUNT_CUSTOMER_idx` (`customerID`),
  CONSTRAINT `FK_ACCOUNT_CUSTOMER` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `addressID` varchar(50) NOT NULL,
  `addressLine` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `zip` int(11) NOT NULL,
  `phoneNumber` varchar(50) NOT NULL,
  `emailID` varchar(50) NOT NULL,
  `userID` varchar(50) NOT NULL,
  PRIMARY KEY (`addressID`),
  KEY `FK_Address_User_idx` (`userID`),
  CONSTRAINT `FK_Address_User` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES ('0c55b664-ea55-4b43-a807-540e3718e27b','null','null',0,'0000000000','hremployeesbs@gmail.com','c54109e2-672e-46b9-866f-d77274dbe1da'),('0cff4cb1-70d9-4b05-a07a-f6421219c09a','mesa','arizona',3446,'4807351723','iemployee17@gmail.com','082fdbfb-dae0-4710-a8c1-6c29725b0820'),('1cdaf028-bcd8-4752-adf6-08f52a81b1b4','null','null',0,'0000000000','customer1sbs@gmail.com','bb12a504-b64a-4685-89c3-60987930fae4'),('46bbeea1-83f4-4bce-8c77-07a970125589','null','null',0,'0000000000','hremployeesbs@gmail.com','180acade-2389-4f6a-aecf-a784e1b7af5a'),('4984a70b-274a-4d3c-ba20-e97508e5736c','Tempe Marketplace','arizona',1234,'2233445567','hrremployee@gmail.com','cd543e06-8163-41f3-932a-72e1df4b83d5'),('9162527b-b8a2-4586-9fa5-bfccd67275eb','asdf','asdasdasd',85567,'4807351723','ssaleemp5@gmail.com','6212e2f4-c1b8-4d50-be21-c2a149d59bb1'),('cb1da504-16c3-477b-88c4-21c33c3ade13','null','null',0,'0000000000','customer2sbs@gmail.com','748dcb25-34b9-46f1-9132-d60e888ad5d0'),('f71897b5-fc29-4695-aaaa-d9557f2c8838','null','null',0,'0000000000','sbscustomer3@gmail.com','12fd5c53-f8c5-44b8-85a3-871a0d8beb14'),('f98d244a-baf0-4b7c-970d-af3cfe2faa9c','null','null',0,'0000000000','hrcorpsbs@gmail.com','87d4ea91-c260-4168-8479-3388419e3690');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `audit`
--

DROP TABLE IF EXISTS `audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audit` (
  `auditID` varchar(50) NOT NULL,
  `auditTrail` varchar(2000) DEFAULT NULL,
  `userID` varchar(50) DEFAULT NULL,
  `timeOfAudit` datetime DEFAULT NULL,
  `auditType` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`auditID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit`
--

LOCK TABLES `audit` WRITE;
/*!40000 ALTER TABLE `audit` DISABLE KEYS */;
INSERT INTO `audit` VALUES ('0112f3c0-95bb-4cf0-8a04-dbfbf2ed2ad8','UserID: 6212e2f4-c1b8-4d50-be21-c2a149d59bb1User Name:ssaleemp5@gmail.comFirst Name: salesLast Name: EmployeeAUDIT STATUS: true','12fd5c53-f8c5-44b8-85a3-871a0d8beb14','2013-11-02 23:37:07','CREATE_EMPLOYEE'),('01585d0b-21c0-43c0-aa85-0dd02302c92e','UserID: d6d84544-1b50-4c50-8e18-396820056c7dUser Name:grc007@gmail.comFirst Name: salesLast Name: employeeAUDIT STATUS: true','bb12a504-b64a-4685-89c3-60987930fae4','2013-11-02 23:17:47','ADD_EMPLOYEE_REQUEST'),('032ab104-a6e1-46ff-8b5a-06ea0daf114f','UserID: d6d84544-1b50-4c50-8e18-396820056c7dUser Name:grc007@gmail.comFirst Name: salesLast Name: employeeAUDIT STATUS: true','12fd5c53-f8c5-44b8-85a3-871a0d8beb14','2013-11-02 23:38:41','DELETE_EMPLOYEE'),('25e72245-9c70-40fe-8742-3b7293a364fc','UserID: cd543e06-8163-41f3-932a-72e1df4b83d5User Name:hrremployee@gmail.comFirst Name: HumanLast Name: EmployeeAUDIT STATUS: true','bb12a504-b64a-4685-89c3-60987930fae4','2013-11-02 23:25:50','ADD_EMPLOYEE_REQUEST'),('2a5606dd-6e16-4a22-97b8-74808ef679e3','UserID: d6d84544-1b50-4c50-8e18-396820056c7dUser Name:grc007@gmail.comFirst Name: salesLast Name: employeeAUDIT STATUS: true','12fd5c53-f8c5-44b8-85a3-871a0d8beb14','2013-11-02 23:19:06','CREATE_EMPLOYEE'),('564703a5-a452-40e4-961e-b5c4cd8dabce','UserID: 082fdbfb-dae0-4710-a8c1-6c29725b0820User Name:iemployee17@gmail.comFirst Name: ITLast Name: EmployeeAUDIT STATUS: true','bb12a504-b64a-4685-89c3-60987930fae4','2013-11-02 23:32:49','ADD_EMPLOYEE_REQUEST'),('9e7df064-9d56-436e-9bd5-763730d72e7e','UserID: cd543e06-8163-41f3-932a-72e1df4b83d5User Name:hrremployee@gmail.comFirst Name: HumanLast Name: EmployeeAUDIT STATUS: true','12fd5c53-f8c5-44b8-85a3-871a0d8beb14','2013-11-02 23:26:39','CREATE_EMPLOYEE'),('e83c2df5-fce7-4f1b-85d9-cdfd87bb5638','UserID: d6d84544-1b50-4c50-8e18-396820056c7dUser Name:grc007@gmail.comFirst Name: salesLast Name: employeeAUDIT STATUS: true','bb12a504-b64a-4685-89c3-60987930fae4','2013-11-02 23:38:11','DELETE_EMPLOYEE_REQUEST'),('fb7859c3-86c7-46e3-9f8e-83e746fb44d9','UserID: 082fdbfb-dae0-4710-a8c1-6c29725b0820User Name:iemployee17@gmail.comFirst Name: ITLast Name: EmployeeAUDIT STATUS: true','12fd5c53-f8c5-44b8-85a3-871a0d8beb14','2013-11-02 23:33:59','CREATE_EMPLOYEE'),('ff8c1f0f-f30d-4177-a8cc-8fcfdd4549fe','UserID: 6212e2f4-c1b8-4d50-be21-c2a149d59bb1User Name:ssaleemp5@gmail.comFirst Name: salesLast Name: EmployeeAUDIT STATUS: true','bb12a504-b64a-4685-89c3-60987930fae4','2013-11-02 23:36:28','ADD_EMPLOYEE_REQUEST');
/*!40000 ALTER TABLE `audit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customerID` varchar(50) NOT NULL,
  `userID` varchar(50) NOT NULL,
  `customerType` varchar(30) NOT NULL,
  PRIMARY KEY (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `deptID` int(11) NOT NULL,
  `deptName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`deptID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'IT'),(2,'SALES'),(3,'ADMIN'),(4,'TRANSACTION'),(5,'HR');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `employeeID` varchar(50) NOT NULL,
  `userID` varchar(50) NOT NULL,
  `DOJ` date NOT NULL,
  `position` varchar(20) DEFAULT NULL,
  `salaryID` varchar(200) NOT NULL,
  PRIMARY KEY (`employeeID`),
  UNIQUE KEY `salaryID_UNIQUE` (`salaryID`),
  KEY `FK_Employee_User_idx` (`userID`),
  KEY `FK_Employee_Salary_idx` (`salaryID`),
  CONSTRAINT `FK_Employee_USER` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('0e5f33a0-3430-452d-b38e-96ac039bd911','748dcb25-34b9-46f1-9132-d60e888ad5d0','2013-11-03',NULL,'2fe42962-abb3-49d1-9cc6-d3578c81c7ea'),('25fa1762-3465-4fe7-9138-66cbaefc8eb6','c54109e2-672e-46b9-866f-d77274dbe1da','2013-11-03',NULL,'96e7c8e5-523b-4ea6-87a8-77fd52fdeabf'),('70926130-c5f6-4ca3-9578-377fc94ba70a','082fdbfb-dae0-4710-a8c1-6c29725b0820','2013-11-15',NULL,'cc391aec-7796-4f95-8032-af30aec38540'),('84196602-e447-428b-8927-f115aa3954cb','180acade-2389-4f6a-aecf-a784e1b7af5a','2013-11-03',NULL,'39a9f730-469f-4f90-827e-2c02d9c67dbd'),('888517e7-993a-487c-89ac-1716c1a0432c','bb12a504-b64a-4685-89c3-60987930fae4','2013-11-03',NULL,'8db8aae9-6fef-4a75-91ad-7332cb5909c6'),('a621a80b-20f3-45c8-8c4e-d32ab59120c8','12fd5c53-f8c5-44b8-85a3-871a0d8beb14','2013-11-03',NULL,'abbd6ccc-74c1-491f-8979-b064716f43f6'),('c7159ae6-fbd3-4371-9df9-213837bb3e99','87d4ea91-c260-4168-8479-3388419e3690','2013-11-03',NULL,'5a6a4ea1-8189-42ca-9079-4598ecd8e36e'),('f5fdd4fe-bb50-46e3-9ffb-66834f77ede9','6212e2f4-c1b8-4d50-be21-c2a149d59bb1','2013-11-08',NULL,'fcc87c07-06dc-416b-909c-cc5626272253'),('fa185520-1511-4723-b028-9c8c235843fe','cd543e06-8163-41f3-932a-72e1df4b83d5','2013-11-11',NULL,'a22b1454-4463-4734-bbbc-626174e2485a');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_department`
--

DROP TABLE IF EXISTS `employee_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_department` (
  `employeeID` varchar(50) NOT NULL,
  `deptID` int(11) NOT NULL,
  PRIMARY KEY (`employeeID`,`deptID`),
  CONSTRAINT `FK_EMPLOYEE_DEPARTMENT` FOREIGN KEY (`employeeID`) REFERENCES `employee` (`employeeID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_department`
--

LOCK TABLES `employee_department` WRITE;
/*!40000 ALTER TABLE `employee_department` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager_employee_mapping`
--

DROP TABLE IF EXISTS `manager_employee_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager_employee_mapping` (
  `employee_role` varchar(45) NOT NULL,
  `manager_role` varchar(45) NOT NULL,
  PRIMARY KEY (`employee_role`,`manager_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager_employee_mapping`
--

LOCK TABLES `manager_employee_mapping` WRITE;
/*!40000 ALTER TABLE `manager_employee_mapping` DISABLE KEYS */;
INSERT INTO `manager_employee_mapping` VALUES ('ADMIN','NONE'),('HR_EMPLOYEE','HR_MANAGER'),('IT_EMPLOYEE','IT_MANAGER'),('SALES_EMPLOYEE','SALES_MANAGER'),('TRANSACTION_EMPLOYEE','TRANSACTION_MANAGER');
/*!40000 ALTER TABLE `manager_employee_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payee`
--

DROP TABLE IF EXISTS `payee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payee` (
  `customername` varchar(45) NOT NULL,
  `customerid` varchar(45) NOT NULL,
  `merchantid` varchar(45) NOT NULL,
  `createdBy` varchar(45) NOT NULL,
  `creationTimeStamp` varchar(45) NOT NULL,
  PRIMARY KEY (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payee`
--

LOCK TABLES `payee` WRITE;
/*!40000 ALTER TABLE `payee` DISABLE KEYS */;
/*!40000 ALTER TABLE `payee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipient`
--

DROP TABLE IF EXISTS `recipient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipient` (
  `username` varchar(50) NOT NULL,
  `customerid` varchar(45) NOT NULL,
  `createdBy` varchar(45) NOT NULL,
  `creationTimeStamp` varchar(45) NOT NULL,
  PRIMARY KEY (`customerid`,`username`),
  KEY `cutomerid_idx` (`customerid`),
  KEY `FK_Username_idx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipient`
--

LOCK TABLES `recipient` WRITE;
/*!40000 ALTER TABLE `recipient` DISABLE KEYS */;
/*!40000 ALTER TABLE `recipient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `requestID` varchar(100) NOT NULL,
  `requestType` varchar(50) NOT NULL,
  `requestFrom` varchar(50) NOT NULL,
  `requestTo` varchar(50) NOT NULL,
  `requestStatus` varchar(10) NOT NULL DEFAULT 'PENDING',
  `requestDesc` varchar(500) DEFAULT NULL,
  `id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`requestID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES ('2eae416a-1071-4ddc-91a7-7a3e9f9ab2b6','CREATE_EMPLOYEE','bb12a504-b64a-4685-89c3-60987930fae4','12fd5c53-f8c5-44b8-85a3-871a0d8beb14','COMPLETE',NULL,'d6d84544-1b50-4c50-8e18-396820056c7d'),('5cd214ed-4940-4598-9dc4-8dc7ca7c36ae','CREATE_EMPLOYEE','bb12a504-b64a-4685-89c3-60987930fae4','12fd5c53-f8c5-44b8-85a3-871a0d8beb14','COMPLETE',NULL,'cd543e06-8163-41f3-932a-72e1df4b83d5'),('7f1b9471-cc8a-43ea-b0ca-536a8d413185','CREATE_EMPLOYEE','bb12a504-b64a-4685-89c3-60987930fae4','12fd5c53-f8c5-44b8-85a3-871a0d8beb14','COMPLETE',NULL,'6212e2f4-c1b8-4d50-be21-c2a149d59bb1'),('b64f1e84-deb1-476d-9202-e14769bfc78c','DELETE_EMPLOYEE','bb12a504-b64a-4685-89c3-60987930fae4','12fd5c53-f8c5-44b8-85a3-871a0d8beb14','COMPLETE',NULL,'d6d84544-1b50-4c50-8e18-396820056c7d'),('bd91e8a7-9f2f-46c8-8b65-54617d572318','CREATE_EMPLOYEE','bb12a504-b64a-4685-89c3-60987930fae4','12fd5c53-f8c5-44b8-85a3-871a0d8beb14','COMPLETE',NULL,'082fdbfb-dae0-4710-a8c1-6c29725b0820');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `roleID` varchar(50) NOT NULL,
  `roleName` varchar(50) NOT NULL,
  `roleType` varchar(50) NOT NULL,
  `dateCreated` date NOT NULL,
  PRIMARY KEY (`roleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('1','IT_MANAGER','EMPLOYEE','2010-10-10'),('10','MERCHANT_CUSTOMER','CUSTOMER','2010-10-10'),('11','ADMIN','EMPLOYEE','2010-10-10'),('12','CORPATE_EMPLOYEE','EMPLOYEE','2010-10-10'),('2','IT_EMPLOYEE','EMPLOYEE','2010-10-10'),('3','HR_MANAGER','EMPLOYEE','2010-10-10'),('4','HR_EMPLOYEE','EMPLOYEE','2010-10-10'),('5','TRANSACTION_MANAGER','EMPLOYEE','2010-10-10'),('6','TRANSACTION_EMPLOYEE','EMPLOYEE','2010-10-10'),('7','SALES_MANAGER','EMPLOYEE','2010-10-10'),('8','SALES_EMPLOYEE','EMPLOYEE','2010-10-10'),('9','INDIVIDUAL_CUSTOMER','CUSTOMER','2010-10-10');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_access`
--

DROP TABLE IF EXISTS `role_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_access` (
  `roleID` varchar(50) NOT NULL,
  `accessID` varchar(50) NOT NULL,
  PRIMARY KEY (`roleID`,`accessID`),
  CONSTRAINT `FK_ROLE_ACCESS` FOREIGN KEY (`roleID`) REFERENCES `role` (`roleID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_access`
--

LOCK TABLES `role_access` WRITE;
/*!40000 ALTER TABLE `role_access` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_access` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary`
--

DROP TABLE IF EXISTS `salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salary` (
  `salaryID` varchar(200) NOT NULL,
  `salary` varchar(50) NOT NULL,
  UNIQUE KEY `salaryID_UNIQUE` (`salaryID`),
  KEY `FK_Salray_Employy` (`salaryID`),
  CONSTRAINT `FK_Salary_Employee` FOREIGN KEY (`salaryID`) REFERENCES `employee` (`salaryID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary`
--

LOCK TABLES `salary` WRITE;
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
INSERT INTO `salary` VALUES ('2fe42962-abb3-49d1-9cc6-d3578c81c7ea','0'),('39a9f730-469f-4f90-827e-2c02d9c67dbd','0'),('5a6a4ea1-8189-42ca-9079-4598ecd8e36e','0'),('8db8aae9-6fef-4a75-91ad-7332cb5909c6','0'),('96e7c8e5-523b-4ea6-87a8-77fd52fdeabf','0'),('a22b1454-4463-4734-bbbc-626174e2485a','23456'),('abbd6ccc-74c1-491f-8979-b064716f43f6','0'),('cc391aec-7796-4f95-8032-af30aec38540','12345'),('fcc87c07-06dc-416b-909c-cc5626272253','34567');
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sq`
--

DROP TABLE IF EXISTS `sq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sq` (
  `SQID` int(11) NOT NULL,
  `SQ` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SQID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sq`
--

LOCK TABLES `sq` WRITE;
/*!40000 ALTER TABLE `sq` DISABLE KEYS */;
/*!40000 ALTER TABLE `sq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `transactionID` varchar(50) NOT NULL,
  `transactionType` varchar(20) NOT NULL,
  `transactionTimeStamp` time NOT NULL,
  `accountID` varchar(50) NOT NULL,
  `toaccountID` varchar(50) NOT NULL,
  `status` varchar(45) NOT NULL,
  `amount` varchar(50) NOT NULL,
  PRIMARY KEY (`transactionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` varchar(50) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `fName` varchar(50) NOT NULL,
  `mName` varchar(50) DEFAULT NULL,
  `lName` varchar(50) NOT NULL,
  `SSN` int(11) NOT NULL,
  `DoB` date NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '0',
  `createdBy` varchar(50) NOT NULL,
  `creationTimeStamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `addressID` varchar(50) DEFAULT NULL,
  `userType` varchar(10) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `defaultemployee` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('082fdbfb-dae0-4710-a8c1-6c29725b0820','iemployee17@gmail.com','IT',NULL,'Employee',1234554321,'2013-01-04',1,'bb12a504-b64a-4685-89c3-60987930fae4','2013-11-03 06:39:32','0cff4cb1-70d9-4b05-a07a-f6421219c09a',NULL,'$2a$10$T7mXM34iN7dDZ0vbxTWUjuc8CTSFKKNVcOxu.2tpeVDjl9UhdQ4A6',1),('12fd5c53-f8c5-44b8-85a3-871a0d8beb14','sbscustomer3@gmail.com','admin',NULL,'admin',0,'2013-01-01',1,'bb12a504-b64a-4685-89c3-60987930fae4','2013-11-03 06:39:32','f71897b5-fc29-4695-aaaa-d9557f2c8838',NULL,'$2a$10$T7mXM34iN7dDZ0vbxTWUjuc8CTSFKKNVcOxu.2tpeVDjl9UhdQ4A6',1),('180acade-2389-4f6a-aecf-a784e1b7af5a','hremployee@gmail.com','transaction',NULL,'transaction',0,'2013-01-01',1,'815341e6-a1cb-4297-ad39-62987b646f3c','2013-11-03 05:20:41','46bbeea1-83f4-4bce-8c77-07a970125589',NULL,'$2a$10$T7mXM34iN7dDZ0vbxTWUjuc8CTSFKKNVcOxu.2tpeVDjl9UhdQ4A6',1),('6212e2f4-c1b8-4d50-be21-c2a149d59bb1','ssaleemp5@gmail.com','sales',NULL,'Employee',1234567893,'2013-01-15',1,'bb12a504-b64a-4685-89c3-60987930fae4','2013-11-03 06:39:32','9162527b-b8a2-4586-9fa5-bfccd67275eb',NULL,'$2a$10$T7mXM34iN7dDZ0vbxTWUjuc8CTSFKKNVcOxu.2tpeVDjl9UhdQ4A6',1),('748dcb25-34b9-46f1-9132-d60e888ad5d0','jsmith','corp',NULL,'corp',0,'2013-01-01',1,'815341e6-a1cb-4297-ad39-62987b646f3c','2013-11-03 05:59:36','cb1da504-16c3-477b-88c4-21c33c3ade13',NULL,'$2a$10$T7mXM34iN7dDZ0vbxTWUjuc8CTSFKKNVcOxu.2tpeVDjl9UhdQ4A6',1),('87d4ea91-c260-4168-8479-3388419e3690','hrcorpsbs@gmail.com','sales',NULL,'sales',0,'2013-01-01',1,'815341e6-a1cb-4297-ad39-62987b646f3c','2013-11-03 05:20:41','f98d244a-baf0-4b7c-970d-af3cfe2faa9c',NULL,'$2a$10$T7mXM34iN7dDZ0vbxTWUjuc8CTSFKKNVcOxu.2tpeVDjl9UhdQ4A6',1),('bb12a504-b64a-4685-89c3-60987930fae4','customer1sbs@gmail.com','hr',NULL,'hr',0,'2013-01-01',1,'815341e6-a1cb-4297-ad39-62987b646f3c','2013-11-03 05:20:41','1cdaf028-bcd8-4752-adf6-08f52a81b1b4',NULL,'$2a$10$T7mXM34iN7dDZ0vbxTWUjuc8CTSFKKNVcOxu.2tpeVDjl9UhdQ4A6',1),('c54109e2-672e-46b9-866f-d77274dbe1da','hremployeesbs@gmail.com','it',NULL,'it',0,'2013-01-01',1,'bb12a504-b64a-4685-89c3-60987930fae4','2013-11-03 05:20:53','0c55b664-ea55-4b43-a807-540e3718e27b',NULL,'$2a$10$T7mXM34iN7dDZ0vbxTWUjuc8CTSFKKNVcOxu.2tpeVDjl9UhdQ4A6',1),('cd543e06-8163-41f3-932a-72e1df4b83d5','hrremployee@gmail.com','Human',NULL,'Employee',1223339999,'2013-01-13',1,'bb12a504-b64a-4685-89c3-60987930fae4','2013-11-03 06:39:32','4984a70b-274a-4d3c-ba20-e97508e5736c',NULL,'$2a$10$T7mXM34iN7dDZ0vbxTWUjuc8CTSFKKNVcOxu.2tpeVDjl9UhdQ4A6',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `userID` varchar(50) NOT NULL,
  `roleID` varchar(50) NOT NULL,
  PRIMARY KEY (`userID`,`roleID`),
  CONSTRAINT `FK_USER_ROLE_ID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('082fdbfb-dae0-4710-a8c1-6c29725b0820','2'),('12fd5c53-f8c5-44b8-85a3-871a0d8beb14','11'),('180acade-2389-4f6a-aecf-a784e1b7af5a','5'),('6212e2f4-c1b8-4d50-be21-c2a149d59bb1','8'),('748dcb25-34b9-46f1-9132-d60e888ad5d0','12'),('87d4ea91-c260-4168-8479-3388419e3690','7'),('bb12a504-b64a-4685-89c3-60987930fae4','3'),('c54109e2-672e-46b9-866f-d77274dbe1da','1'),('cd543e06-8163-41f3-932a-72e1df4b83d5','4');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_sq`
--

DROP TABLE IF EXISTS `user_sq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_sq` (
  `SQID` int(11) NOT NULL,
  `userID` varchar(50) NOT NULL,
  `answer` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SQID`),
  KEY `fk_User_Id_idx` (`userID`),
  CONSTRAINT `FK_USER_SQ` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_sq`
--

LOCK TABLES `user_sq` WRITE;
/*!40000 ALTER TABLE `user_sq` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_sq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-10-19 23:04:32
