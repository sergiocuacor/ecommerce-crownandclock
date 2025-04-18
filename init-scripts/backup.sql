-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: localhost    Database: clockdb
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'Luxury Swiss watches of the highest quality','Audemars Piguet'),(2,'Iconic brand of refined jewelry and watches','Cartier'),(3,'Digital and analog watches with high technology','Casio'),(4,'Luxury watches and accessories with exclusive design','Hermès'),(5,'Innovation in watchmaking with unique materials','Hublot'),(6,'Swiss precision with a legacy in space exploration','Omega'),(7,'Prestigious watchmaking with artisanal tradition','Patek Philippe'),(8,'Symbol of luxury and precision in Swiss watches','Rolex'),(9,'Japanese innovation in watchmaking since 1881','Seiko'),(10,'High-end Swiss watchmaking since 1755','Vacheron Constantin'),(11,'Luxury Swiss watches of the highest quality','Audemars Piguet'),(12,'Iconic brand of refined jewelry and watches','Cartier'),(13,'Digital and analog watches with high technology','Casio'),(14,'Luxury watches and accessories with exclusive design','Hermès'),(15,'Innovation in watchmaking with unique materials','Hublot'),(16,'Swiss precision with a legacy in space exploration','Omega'),(17,'Prestigious watchmaking with artisanal tradition','Patek Philippe'),(18,'Symbol of luxury and precision in Swiss watches','Rolex'),(19,'Japanese innovation in watchmaking since 1881','Seiko'),(20,'High-end Swiss watchmaking since 1755','Vacheron Constantin');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int NOT NULL,
  `subtotal` double DEFAULT NULL,
  `unit_price` double NOT NULL,
  `order_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id`),
  KEY `FK4q98utpd73imf4yhttm3w0eax` (`product_id`),
  CONSTRAINT `FK4q98utpd73imf4yhttm3w0eax` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKjyu2qbqt8gnvno9oe9j2s2ldk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (1,4,520,130,1,114),(2,1,99,99,1,119),(3,4,520,130,2,114),(4,1,99,99,2,119),(5,1,130,130,3,114),(6,1,99,99,3,119),(7,1,130,130,4,114),(8,1,99,99,4,119),(9,1,4050,4050,5,45);
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `discount_applied` varchar(255) DEFAULT NULL,
  `discount_percentage` int DEFAULT NULL,
  `order_date` datetime(6) DEFAULT NULL,
  `status` enum('CANCELLED','COMPLETED','PENDING','SHIPPED') NOT NULL,
  `total` double NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'SUMMER_15',10,'2025-04-06 12:54:43.899318','PENDING',557.1,1),(2,'',0,'2025-04-06 12:56:10.280264','PENDING',619,1),(3,'',0,'2025-04-06 12:59:30.995470','PENDING',229,1),(4,'',0,'2025-04-14 20:12:18.577874','PENDING',229,1),(5,'CLOCKERS',15,'2025-04-14 20:12:42.209012','PENDING',3442.5,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `mask` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `stock` int NOT NULL,
  `brand_id` bigint DEFAULT NULL,
  `average_rating` double DEFAULT NULL,
  `rating_count` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl2cyj2st6mjygl2pgwd057ivu` (`brand_id`),
  CONSTRAINT `FKl2cyj2st6mjygl2pgwd057ivu` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Emblemático Royal Oak Offshore con caja de acero inoxidable de 42mm, cronógrafo automático y esfera \"Méga Tapisserie\". Resistente al agua hasta 100m y brazalete integrado de acero.','audemars-piguet-26470st-royal-oak-offshore','26470ST Royal Oak Offshore',34500,3,1,NULL,NULL),(2,'Clásico Royal Oak 15400 con caja de 41mm en acero inoxidable, icónica esfera con patrón \"Grande Tapisserie\", movimiento automático calibre 3120 y fecha a las 3 horas.','audemars-piguet-15400st-royal-oak-15400-stainless-steel','15400ST Royal Oak 15400 Stainless Steel',29800,1,1,NULL,NULL),(3,'Sofisticado cronógrafo Royal Oak de 41mm con tres subesferas, esfera azul con patrón \"Grande Tapisserie\", pulsadores integrados y calibre 2385 de alta precisión.','audemars-piguet-26331st-royal-oak-chronograph-41-stainless-steel','26331ST Royal Oak Chronograph 41 Stainless Steel',32500,4,1,NULL,NULL),(4,'Imponente Royal Oak Offshore con caja de oro rosa de 18K, bisel octogonal con tornillos visibles, cronógrafo automático y correa de caucho texturizado de alta resistencia.','audemars-piguet-26401ro-royal-oak-offshore','26401RO Royal Oak Offshore',47900,2,1,NULL,NULL),(5,'Robusto reloj de buceo con certificación ISO 6425, caja de acero de 42mm, bisel interno giratorio, luminiscencia de alta visibilidad y resistencia al agua de 300m.','audemars-piguet-15710st-royal-oak-offshore-diver-stainless-steel','15710ST Royal Oak Offshore Diver Stainless Steel',27800,2,1,NULL,NULL),(6,'Elegante Royal Oak Selfwinding con calibre automático 5900, caja de acero de 41mm, esfera con patrón \"Grande Tapisserie\" y reserva de marcha de 60 horas.','audemars-piguet-15510st-royal-oak-self','15510ST Royal Oak Self',28500,1,1,NULL,NULL),(7,'Refinado Royal Oak 15500 que actualiza al clásico 15400, con calibre 4302, mayor reserva de marcha, esfera reestructurada y acabados de alta joyería en caja y brazalete.','audemars-piguet-15500st-royal-oak-15500-stainless-steel','15500ST Royal Oak 15500 Stainless Steel',30900,4,1,NULL,NULL),(8,'Cronógrafo de última generación con nuevo calibre integrado 4401, función flyback, caja de 41mm, esfera \"Grande Tapisserie\" y fondo de cristal de zafiro.','audemars-piguet-26240st-royal-oak-chronograph-41-stainless-steel','26240ST Royal Oak Chronograph 41 Stainless Steel',36500,1,1,NULL,NULL),(9,'Deportivo Royal Oak Offshore con caja robusta de acero de 42mm, protector de corona, cronógrafo automático y esfera texturizada con contadores contrastantes.','audemars-piguet-26170st-royal-oak-offshore-chronograph','26170ST Royal Oak Offshore Chronograph',32500,3,1,NULL,NULL),(10,'Clásico Royal Oak 15300 con caja intermedia de 39mm, calibre 3120, perfecta proporción en muñeca y el inconfundible diseño octogonal creado por Gerald Genta.','audemars-piguet-15300st-royal-oak-15300-stainless-steel','15300ST Royal Oak 15300 Stainless Steel',28900,0,1,NULL,NULL),(11,'Evolución del Offshore Diver con nueva arquitectura, caja de 42mm, bisel interno giratorio con escala de 60 minutos y calibre 4308 con mayor precisión.','audemars-piguet-15720st-royal-oak-offshore-diver-stainless-steel','15720ST Royal Oak Offshore Diver Stainless Steel',29500,4,1,NULL,NULL),(12,'Impresionante Royal Oak Offshore rediseñado con caja de 43mm, construcción modular, cronógrafo flyback integrado y sistema de correas intercambiables sin herramientas.','audemars-piguet-26420so-royal-oak-offshore-43-stainless-steel','26420SO Royal Oak Offshore 43 Stainless Steel',37800,2,1,NULL,NULL),(13,'Monumental Royal Oak Offshore de 44mm para muñecas robustas, con cronógrafo automático, esfera \"Méga Tapisserie\" y caja con tratamiento especial anti-huellas.','audemars-piguet-26400so-royal-oak-offshore-44-stainless-steel','26400SO Royal Oak Offshore 44 Stainless Steel',39200,3,1,NULL,NULL),(14,'Legendario Jumbo Extra-Thin, fiel al diseño original de 1972, con caja de 39mm, ultrafino calibre 2121, esfera Petite Tapisserie y exquisita delgadez de solo 8mm.','audemars-piguet-15202st-royal-oak-extrathin-stainless-steel','15202ST Royal Oak Extra-Thin Stainless Steel',42500,1,1,NULL,NULL),(15,'Versátil cronógrafo Royal Oak con proporciones equilibradas, calibre 2385 basado en F. Piguet 1185, acabados superiores y excelente legibilidad en todas las condiciones.','audemars-piguet-26320st-royal-oak-chronograph-41-stainless-steel','26320ST Royal Oak Chronograph 41 Stainless Steel',31800,3,1,NULL,NULL),(16,'Innovador Offshore con caja de cerámica negra de 42mm, bisel y pulsadores cerámicos, cronógrafo automático y resistencia excepcional a rayaduras.','audemars-piguet-26420ce-royal-oak-offshore-selfwinding-chronograph','26420CE Royal Oak Offshore Selfwinding Chronograph',45800,2,1,NULL,NULL),(17,'Elegante cronógrafo Royal Oak de 38mm, ideal para muñecas medianas, con calibre automático, esfera armoniosamente equilibrada y acabados de alta joyería.','audemars-piguet-26715st-royal-oak-chronograph-38-stainless-steel','26715ST Royal Oak Chronograph 38 Stainless Steel',29900,4,1,NULL,NULL),(18,'Exclusivo Royal Oak Offshore con combinación de acero y cerámica, cronógrafo de alta precisión y diseño robusto reconocible al instante.','audemars-piguet-25940sk-royal-oak-offshore-chronograph','25940SK Royal Oak Offshore Chronograph',35600,2,1,NULL,NULL),(19,'Sofisticado cronógrafo Jules Audemars en oro rosa de 18K con diseño clásico atemporal, esfera plateada guillochada, cronógrafo de rueda de pilares y acabado artesanal.','audemars-piguet-26100or-jules-audemars-26100-chronograph-pink-gold','26100OR Jules Audemars 26100 Chronograph Pink Gold',48500,1,1,NULL,NULL),(20,'Elegante Jules Audemars en oro rosa con segundero pequeño, indicador de reserva de marcha, esfera opalina plateada y calibre de manufactura con acabados a mano.','audemars-piguet-25955or-jules-audemars-small-seconds-power-reserve-pink-gold','25955OR Jules Audemars Small Seconds & Power Reserve Pink Gold',42700,2,1,NULL,NULL),(21,'Avanzado reloj de buceo con función cronógrafo, bisel interno giratorio, caja robusta de acero de 42mm, resistencia al agua de 300m y legibilidad superior.','audemars-piguet-26703st-royal-oak-offshore-diver-chronograph','26703ST Royal Oak Offshore Diver Chronograph',33900,3,1,NULL,NULL),(22,'Refinado Royal Oak de tamaño intermedio (37mm) con calibre automático 3120, perfecto equilibrio entre presencia y comodidad, ideal para muñecas medianas.','audemars-piguet-15450st-royal-oak-15450-selfwinding-stainless-steel','15450ST Royal Oak 15450 Selfwinding Stainless Steel',26800,4,1,NULL,NULL),(23,'Cronógrafo Royal Oak clásico de 39mm, calibre 2385, perfectas proporciones heredadas del diseño original y excelente legibilidad.','audemars-piguet-25860st-royal-oak-chronograph-39-stainless-steel','25860ST Royal Oak Chronograph 39 Stainless Steel',29500,2,1,NULL,NULL),(24,'Lujoso Royal Oak 15300 en oro rosa de 18K, caja de 39mm, esfera con patrón \"Grande Tapisserie\", calibre 3120 y acabados excepcionales que resaltan el tono cálido del oro.','audemars-piguet-15300or-royal-oak-15300','15300OR Royal Oak 15300',56800,1,1,NULL,NULL),(25,'Elegante reloj Heure H de Hermès con caja cuadrada en forma de H, cristal de zafiro y correa de cuero de alta calidad','hermes-hh1210-heure-h','HH1.210 Heure H',3250,5,4,NULL,NULL),(26,'Reloj Clipper con esfera redonda, movimiento suizo de cuarzo y resistencia al agua, diseño clásico y atemporal','hermes-cl4210-clipper','CL4.210 Clipper',2750,8,4,NULL,NULL),(27,'Cronógrafo de lujo Clipper con tres subesferas, bisel de acero inoxidable y pulsera de metal','hermes-cl2910-clipper-chronograph','CL2.910 Clipper Chronograph',4300,3,4,NULL,NULL),(28,'Versión exclusiva del Clipper con detalles en oro y correa intercambiable, ideal para ocasiones formales','hermes-cl4220-clipper','CL4.220 Clipper',3050,4,4,NULL,NULL),(29,'Modelo femenino Heure H con caja de acero pulido, esfera de nácar y detalles en diamantes','hermes-hh1201-heure-h','HH1.201 Heure H',3750,6,4,NULL,NULL),(30,'Icónico reloj Cape Cod con su distintiva caja cuadrada con asas \"ancla de cadena\" y correa doble vuelta','hermes-cc1210-cape-cod','CC1.210 Cape Cod',3950,7,4,NULL,NULL),(31,'Reloj inspirado en el famoso bolso Kelly, con cierre característico y correa de piel de aligátor','hermes-ke1201-kelly','KE1.201 Kelly',4950,2,4,NULL,NULL),(32,'Versión minimalista del Heure H con acabado en acero pulido y correa de cuero intercambiable','hermes-hh1110-heure-h','HH1.110 Heure H',3150,9,4,NULL,NULL),(33,'Elegante reloj Croisiere con esfera rectangular, inspirado en los viajes marítimos de lujo','hermes-cr1220-croisiere','CR1.220 Croisiere',3550,4,4,NULL,NULL),(34,'Moderno reloj H08 con caja de composite y titanio, diseño contemporáneo con numerales arábigos','hermes-sp1741-h08','SP1.741 H08',5950,5,4,NULL,NULL),(35,'Edición especial del H08 con caja cuadrada redondeada, esfera negra texturizada y correa de caucho','hermes-w049430ww00-h08','W049430WW00 H08',6250,3,4,NULL,NULL),(36,'Heure H con esfera plateada guilloché, índices aplicados y funcionalidad de fecha','hermes-hh1510-heure-h','HH1.510 Heure H',3450,6,4,NULL,NULL),(37,'Sofisticado Heure H con acabados en oro rosa, esfera blanca y correa de piel de cocodrilo','hermes-hh1810-heure-h','HH1.810 Heure H',5750,2,4,NULL,NULL),(38,'Clipper Chronograph deportivo con esfera azul, función de cronómetro y resistencia al agua de 100m','hermes-cp2941-clipper-chronograph','CP2.941 Clipper Chronograph',4100,4,4,NULL,NULL),(39,'Cape Cod con correa doble tour diseñada por Martin Margiela, esfera lacada y acabados en oro','hermes-cd6710-cape-cod','CD6.710 Cape Cod',4450,3,4,NULL,NULL),(40,'Reloj Belt inspirado en las icónicas hebillas de cinturón de Hermès, con correa de piel de becerro','hermes-be1210-belt','BE1.210 Belt',3650,5,4,NULL,NULL),(41,'Elegante Barenia con caja de acero pulido, esfera plateada y correa de cuero Barenia natural','hermes-ba1510-barenia','BA1.510 Barenia',3850,4,4,NULL,NULL),(42,'Reloj Tandem con diseño rectangular alargado, movimiento de cuarzo suizo y acabados satinados','hermes-ta1710-tandem','TA1.710 Tandem',3250,6,4,NULL,NULL),(43,'Delicado Nantucket con forma de \"ancre de chaîne\", ideal para ocasiones formales femeninas','hermes-na1250-nantucket','NA1.250 Nantucket',4250,4,4,NULL,NULL),(44,'Deportivo Rallye inspirado en el mundo del automovilismo, con cronógrafo y escala taquimétrica','hermes-ra1810-rallye','RA1.810 Rallye',4750,5,4,NULL,NULL),(45,'Versión clásica del Rallye con esfera plateada, índices aplicados y correa de piel perforada','hermes-ra1220-rallye','RA1.220 Rallye',4050,6,4,5,1),(46,'Croisiere con esfera azul marino, inspirada en los relojes náuticos de precisión','hermes-cr1240-croisiere','CR1.240 Croisiere',3650,6,4,NULL,NULL),(47,'Reloj Sellier con correa de cuero con costuras a mano, inspirado en la tradición ecuestre de Hermès','hermes-se4220-sellier','SE4.220 Sellier',3850,5,4,NULL,NULL),(48,'Harnais con diseño inspirado en los arneses ecuestres, esfera guilloché y movimiento de precisión','hermes-ha3210-harnais','HA3.210 Harnais',3950,4,4,NULL,NULL),(49,'Elegante Tank Francaise de tamaño pequeño, con caja rectangular en acero inoxidable, esfera plateada con números romanos y brazalete de eslabones integrado. Un ícono de la relojería femenina.','cartier-w51008q3-tank-francaise-small','W51008Q3 Tank Francaise Small',4850,6,2,NULL,NULL),(50,'Imponente Santos de tamaño grande con caja cuadrada en acero inoxidable, tornillos expuestos característicos, esfera plateada con acabado rayado solar y brazalete SmartLink de fácil ajuste.','cartier-wssa0018-santos-large','WSSA0018 Santos Large',7950,4,2,NULL,NULL),(51,'Distintivo Ballon Bleu con su característica corona protegida por un arco integrado, caja redonda de acero, esfera plateada guilloché y manecillas azuladas tipo espada.','cartier-wsbb0028-ballon-bleu','WSBB0028 Ballon Bleu',6850,5,2,NULL,NULL),(52,'Santos de tamaño grande con acabado ADLC negro contrastante, caja de acero inoxidable, esfera negra esqueletizada y sistema QuickSwitch para cambio de correa sin herramientas.','cartier-wssa0030-santos-large','WSSA0030 Santos Large',8950,3,2,NULL,NULL),(53,'Santos de tamaño mediano, proporción perfecta entre presencia y comodidad, con caja de acero inoxidable, esfera plateada con acabado satinado y detalles pulidos.','cartier-wssa0029-santos-medium','WSSA0029 Santos Medium',7250,6,2,NULL,NULL),(54,'Lujoso Ballon Bleu con componentes en oro rosa de 18K y acero, esfera plateada con guilloché radial, cristal de zafiro y fecha a las 3 horas.','cartier-wjbb0033-ballon-bleu','WJBB0033 Ballon Bleu',11500,2,2,NULL,NULL),(55,'Refinado Tank Must tamaño pequeño, reinterpretación moderna del clásico Tank con caja de acero inoxidable, esfera plateada minimalista y correa de piel de aligátor negra.','cartier-wsta0042-tank-must-small','WSTA0042 Tank Must Small',3950,8,2,NULL,NULL),(56,'Opulento Ballon Bleu con caja y brazalete de oro rosa de 18K, esfera plateada con guilloché, números romanos negros y cristal de zafiro resistente a rayaduras.','cartier-wgbb0030-ballon-bleu','WGBB0030 Ballon Bleu',29500,1,2,NULL,NULL),(57,'Elegante Santos-Dumont, inspirado en el primer reloj de pulsera creado para Alberto Santos-Dumont, con caja ultrafina de acero, esfera champagne y correa de piel de aligátor.','cartier-wssa0046-santosdumont','WSSA0046 Santos-Dumont',6500,4,2,NULL,NULL),(58,'Deportivo Santos de tamaño grande con bisel ADLC negro, caja de acero inoxidable, esfera azul profundo con acabado rayado solar y brazalete integrado con sistema SmartLink.','cartier-wssa0039-santos-large','WSSA0039 Santos Large',8750,3,2,NULL,NULL),(59,'Refinado Santos Dumont de tamaño grande con caja ultrafina de acero, diseño inspirado en el reloj original de 1904, esfera plateada con acabado satinado y correa de piel de aligátor.','cartier-wssa0022-santos-dumont-large','WSSA0022 Santos Dumont Large',6950,5,2,NULL,NULL),(60,'Ballon Bleu tamaño mediano con caja de acero inoxidable, esfera azul lacada con acabado rayado solar, números romanos plateados y cristal de zafiro con leve efecto lupa.','cartier-wsbb0039-ballon-bleu','WSBB0039 Ballon Bleu',7250,4,2,NULL,NULL),(61,'Santos Dumont tamaño mediano, con perfil ultrafino, caja de acero inoxidable, corona con cabujón de zafiro sintético y esfera plateada con números romanos negros.','cartier-wssa0023-santos-dumont','WSSA0023 Santos Dumont',6250,6,2,NULL,NULL),(62,'Exclusivo Santos con esfera verde degradada, caja de acero inoxidable con acabados cepillados y pulidos, brazalete integrado y sistema de cambio rápido de correa.','cartier-wssa0062-santos-green-dial','WSSA0062 Santos Green Dial',8250,3,2,NULL,NULL),(63,'Icónico Panthère de tamaño pequeño, inspirado en los años 80, con caja cuadrada de acero inoxidable, esfera plateada con números romanos y brazalete flexible de eslabones.','cartier-wspn0006-panthere-small','WSPN0006 Panthere Small',5950,5,2,NULL,NULL),(64,'Imponente Ballon Bleu de 42mm con movimiento automático, caja de acero inoxidable, esfera plateada con guilloché, fechador a las 3 horas y cabujón de zafiro sintético en la corona.','cartier-w69012z4-ballon-blue-de-cartier-42-automatic','W69012Z4 Ballon Blue de Cartier 42 Automatic',7950,4,2,NULL,NULL),(65,'Santos con tratamiento ADLC y detalles en oro amarillo, caja cuadrada bicolor, esfera negra esqueletizada y sistema QuickSwitch para cambio rápido de correa.','cartier-wssa0061-santos','WSSA0061 Santos',10750,2,2,NULL,NULL),(66,'Colorido Tank Must de tamaño pequeño con esfera verde lacada, caja de acero inoxidable rectangular, correa vegana de alta tecnología y movimiento de cuarzo de alta precisión.','cartier-wsta0051-tank-must-small','WSTA0051 Tank Must Small',3650,7,2,NULL,NULL),(67,'Ballon Bleu automático de 36mm con caja de acero inoxidable y elementos en oro rosa, esfera plateada con guilloché, números romanos y brazalete de acero con acabados mixtos.','cartier-w6920033-ballon-blue-de-cartier-36-automatic','W6920033 Ballon Blue de Cartier 36 Automatic',10950,3,2,NULL,NULL),(68,'Minimalista Tank Must de tamaño grande, con caja rectangular alargada de acero inoxidable, esfera plateada con acabado satinado y correa de piel de aligátor negro.','cartier-wsta0041-tank-must-large','WSTA0041 Tank Must Large',4250,6,2,NULL,NULL),(69,'Versátil Tank Must de tamaño mediano, perfecta combinación entre tradición y modernidad, con caja de acero inoxidable y correa de piel de becerro intercambiable.','cartier-wsta0040-tank-must','WSTA0040 Tank Must',3950,7,2,NULL,NULL),(70,'Santos de tamaño grande con llamativa esfera azul, caja de acero inoxidable con acabados contrastantes, sistema EasyLink para ajuste rápido del brazalete y movimiento automático.','cartier-wssa0048-santos-large','WSSA0048 Santos Large',8150,4,2,NULL,NULL),(71,'Innovador Clé de Cartier con su característica corona en forma de llave, caja redonda de 40mm, esfera plateada guilloché con segundero pequeño y movimiento automático de manufactura.','cartier-wscl0007-cl-de-cartier-40','WSCL0007 Clé de Cartier 40',8950,3,2,NULL,NULL),(72,'Emblemático Tank Francaise de tamaño mediano, con brazalete integrado tipo \"ladrillo\", caja rectangular de acero inoxidable, esfera plateada con números romanos y movimiento de cuarzo.','cartier-w51007q4-tank-francaise','W51007Q4 Tank Francaise',5250,5,2,NULL,NULL),(73,'Legendario cronógrafo Speedmaster Professional con caja de acero de 42mm, cristal de zafiro, bisel de taquímetro y calibre manual 3861 certificado Master Chronometer. El primer reloj en la Luna, con brazalete de acero.','omega-31030425001002-speedmaster-professional-moonwatch','310.30.42.50.01.002 Speedmaster Professional Moonwatch',6700,5,6,NULL,NULL),(74,'Icónico Speedmaster Professional con cristal Hesalite, caja de acero de 42mm, calibre manual 3861 certificado Master Chronometer y brazalete de acero. El mismo diseño que fue a la Luna durante la misión Apollo 11.','omega-31030425001001-speedmaster-professional-moonwatch','310.30.42.50.01.001 Speedmaster Professional Moonwatch',6400,6,6,NULL,NULL),(75,'Elegante variante del Moonwatch Professional con esfera plateada \"Silver Snoopy\", caja de acero de 42mm, bisel de taquímetro y calibre 3861 con certificación Master Chronometer.','omega-31030425004001-speedmaster-moonwatch-professional-42-mm-steel-on-steel','310.30.42.50.04.001 Speedmaster Moonwatch Professional 42 mm, steel on steel',7800,3,6,NULL,NULL),(76,'Robusto Seamaster Diver 300M con esfera azul ondulada, bisel cerámico azul, indicador de fecha, válvula de helio y calibre 8800 Master Chronometer resistente a campos magnéticos.','omega-21030422003001-seamaster-diver-300m','210.30.42.20.03.001 Seamaster Diver 300M',5300,7,6,NULL,NULL),(77,'Edición especial 50º aniversario Silver Snoopy Award con animación especial en el fondo de caja, esfera plateada, Snoopy en el contador a las 9 y calibre 3861 Master Chronometer.','omega-silver-snoopy-31032425002001-speedmaster','Silver Snoopy 310.32.42.50.02.001 Speedmaster',10950,2,6,NULL,NULL),(78,'Ultraligero Seamaster Diver 300M en titanio grado 5, con esfera negra mate, bisel cerámico con escala en relieve, calibre 8806 Master Chronometer y brazalete de titanio.','omega-21090422001001-seamaster-diver-300m-42-mm-titanium-on-titanium','210.90.42.20.01.001 Seamaster Diver 300M 42 mm, titanium on titanium',8400,4,6,NULL,NULL),(79,'Clásico Speedmaster Moonwatch con movimiento manual calibre 1861, caja de acero de 42mm, cristal Hesalite y brazalete de acero. Modelo previo a la actualización de 2021, con herencia directa del reloj que llegó a la Luna.','omega-357050-speedmaster-moonwatch','3570.50 Speedmaster Moonwatch',5950,3,6,NULL,NULL),(80,'Emblemático Seamaster Diver 300M con esfera negra ondulada, bisel cerámico negro, válvula de helio, calibre 8800 Master Chronometer y brazalete de acero con extensión de buceo.','omega-21030422001001-seamaster-diver-300m','210.30.42.20.01.001 Seamaster Diver 300M',5300,8,6,NULL,NULL),(81,'Distintivo Seamaster Diver con esfera blanca esmaltada, bisel cerámico negro, indicador de fecha, válvula de helio automática y calibre 8800 con tecnología Co-Axial Master Chronometer.','omega-21030422004001-seamaster-diver-300m-master-coaxial-42','210.30.42.20.04.001 Seamaster Diver 300M Master Co-Axial 42',5500,6,6,NULL,NULL),(82,'Edición especial 60º aniversario de James Bond con esfera azul y motivo de espiral inspirado en los cañones de arma, fondo de caja especial y movimiento visible a través de la animación.','omega-21030422003002-seamaster-diver-300m-coaxial-master-chronometer-james-bond-60th-anniversary','210.30.42.20.03.002 Seamaster Diver 300M Co-Axial Master Chronometer \"James Bond 60th Anniversary\"',7900,3,6,NULL,NULL),(83,'Deportivo Speedmaster Racing con cronógrafo automático, caja de 40mm, esfera negra con patrón \"racing\" y escala taquimétrica en el bisel. Impulsado por el calibre 3330 con escape Co-Axial.','omega-32630405001001-speedmaster-racing','326.30.40.50.01.001 Speedmaster Racing',5250,5,6,NULL,NULL),(84,'Versátil Seamaster Diver 300M con esfera negra ondulada, bisel cerámico, correa de caucho estructurado con hebilla desplegable y calibre 8800 Master Chronometer.','omega-21032422001001-seamaster-diver-300m','210.32.42.20.01.001 Seamaster Diver 300M',5100,7,6,NULL,NULL),(85,'Clásico Seamaster 300M \"Bond\" con esfera azul ondulada, bisel azul unidireccional, válvula de helio y calibre automático 1120. El modelo que estableció al Seamaster como reloj de James Bond.','omega-253180-seamaster-300m-chronometer','2531.80 Seamaster 300M Chronometer',4250,4,6,NULL,NULL),(86,'Impresionante Speedmaster \"Dark Side of the Moon\" con caja de cerámica negra de 44.25mm, esfera y bisel de cerámica, cristal de zafiro y calibre 9300 con cronógrafo de dos contadores.','omega-31192443001001-speedmaster-moonwatch','311.92.44.30.01.001 Speedmaster Moonwatch',12500,2,6,NULL,NULL),(87,'Speedmaster Moonwatch con calibre manual 1861, caja de acero de 42mm, cristal Hesalite, minutero central y tres subesferas para cronógrafo. El reloj heredero del que acompañó a los astronautas del Apollo.','omega-31130423001005-speedmaster-moonwatch','311.30.42.30.01.005 Speedmaster Moonwatch',5800,5,6,NULL,NULL),(88,'Compacto Speedmaster Date con caja de 39mm, movimiento automático, cronógrafo, fecha a las 3 horas y bisel de taquímetro. Una alternativa versátil para uso diario.','omega-351350-speedmaster-date','3513.50 Speedmaster Date',3950,4,6,NULL,NULL),(89,'Elegante Speedmaster Racing con esfera plateada, detalles naranjas, cronógrafo automático, cristal de zafiro y calibre 3330 con escape Co-Axial y certificación cronométrica.','omega-32630405002001-speedmaster-racing','326.30.40.50.02.001 Speedmaster Racing',5350,6,6,NULL,NULL),(90,'Dinámico Seamaster Diver 300M con esfera azul ondulada, bisel cerámico azul, correa de caucho azul con textura y calibre 8800 Master Chronometer con acabados exclusivos.','omega-21032422003001-seamaster-diver-300m','210.32.42.20.03.001 Seamaster Diver 300M',5100,7,6,NULL,NULL),(91,'Codiciado Seamaster 300M \"Peter Blake\" con esfera negra mate, manecillas tipo espada, bisel de aluminio, válvula de helio y brazalete de acero con extensión de buceo. Un clásico moderno.','omega-225450-seamaster-300m-chronometer','2254.50 Seamaster 300M Chronometer',4650,3,6,NULL,NULL),(92,'Cronógrafo Speedmaster Moonwatch con caja de acero de 42mm, cristal Hesalite en el frente, fondo transparente de zafiro y cuerda manual. Calibre 1863 con acabados mejorados visibles.','omega-31130423001006-speedmaster-moonwatch','311.30.42.30.01.006 Speedmaster Moonwatch',6200,4,6,NULL,NULL),(93,'Refinado Seamaster Diver con esfera lacada blanca, bisel cerámico negro, correa de caucho negro y calibre 8800 Master Chronometer resistente a 15,000 gauss.','omega-21032422004001-seamaster-diver-300m-master-coaxial-42','210.32.42.20.04.001 Seamaster Diver 300M Master Co-Axial 42',5100,5,6,NULL,NULL),(94,'Llamativo Seamaster Diver con esfera color verde pino, bisel cerámico a juego, correa de caucho negro y calibre 8800 Master Chronometer con certificación METAS.','omega-21032422010001-seamaster-diver-300m-master-coaxial-42-stainless-steel','210.32.42.20.10.001 Seamaster Diver 300M Master Co-Axial 42 Stainless Steel',5300,4,6,NULL,NULL),(95,'Elegante Speedmaster \'57 bicolor, inspirado en el primer Speedmaster de 1957, con caja de acero y detalles en oro Sedna, calibre 9300 Co-Axial y cronógrafo de dos contadores.','omega-33122425101001-speedmaster-57-coaxial','331.22.42.51.01.001 Speedmaster 57 Co-Axial',11400,2,6,NULL,NULL),(96,'Ultraligero Seamaster Diver en titanio grado 5, con bisel y esfera negra mate, correa NATO negra con detalles en gris y calibre 8806 Master Chronometer. Resistente a 300 metros y campos magnéticos.','omega-21092422001001-seamaster-diver-300m-42-mm-titanium-on-nato-strap','210.92.42.20.01.001 Seamaster Diver 300M 42 mm, titanium on NATO strap',7600,3,6,NULL,NULL),(97,'Edición 40º aniversario G-Shock con caja completa de acero inoxidable con acabado especial \"recrystallised\", Bluetooth, energía solar, multiband 6 y resistencia al agua de 200m. Un tributo brillante a la durabilidad legendaria.','casio-gmwb5000ps-gshock-recrystallised-40th-anniversary','GMWB5000PS G-Shock Recrystallised 40th Anniversary',1200,3,3,NULL,NULL),(98,'Elegante Oceanus con carcasa de titanio ultra ligero, cristal de zafiro, tecnología Triple Sync (Bluetooth, Radio Atómica y GPS), alimentación solar y acabados de alta calidad. La línea premium de Casio.','casio-ocwt200s-oceanus-radio-solar-bluetooth','OCWT200S Oceanus Radio Solar Bluetooth',950,4,3,NULL,NULL),(99,'Icónico G-Shock con diseño original pero tecnología moderna, caja de resina con núcleo de acero inoxidable, multiband 6, energía solar y pantalla LCD mejorada con mayor contraste y visibilidad.','casio-gw5000u-gshock','GW5000U G-Shock',350,8,3,NULL,NULL),(100,'Avanzado Pro Trek con triple sensor (altímetro/barómetro, brújula digital y termómetro), alimentación solar, recepción de señal atómica y diseño robusto para actividades al aire libre extremas.','casio-prw6600yb-pro-trek','PRW6600YB Pro Trek',450,6,3,NULL,NULL),(101,'Sofisticado G-Shock serie 2100 \"CasiOak\" en versión full metal dorada, con estructura octogonal, conexión Bluetooth, resistencia extrema y un perfil sorprendentemente delgado para un G-Shock.','casio-gmb2100gd-gshock','GMB2100GD G-Shock',550,5,3,NULL,NULL),(102,'Lujoso G-Shock MT-G con estructura Core Guard, caja y brazalete de acero y resina compuesta, triple G resist, Bluetooth, solar y técnicas de manufactura inspiradas en la espada japonesa.','casio-mtgb3000d-gshock-mtg','MTGB3000D G-Shock MT-G',950,3,3,NULL,NULL),(103,'Innovador G-Shock con estructura hexagonal, sensores de ritmo cardíaco y medición de pasos, conectividad con smartphone y resistencia legendaria G-Shock para entrenamiento intensivo.','casio-dwh5600-gshock','DWH5600 G-Shock',180,7,3,NULL,NULL),(104,'Versátil Pro Trek con múltiples sensores para actividades outdoor, indicador de mareas, fases lunares, cronómetro, energía solar y recepción de ondas atómicas para precisión extrema.','casio-prw35-pro-trek','PRW35 Pro Trek',320,5,3,NULL,NULL),(105,'Conmemorativo G-Shock del 35º aniversario con caja y brazalete completamente metálicos, conectividad Bluetooth, carga solar, recepción multiband 6 y STN LCD para mejor visibilidad.','casio-gmwb5000d-gshock-35th-anniversary-full-metal','GMWB5000D G-Shock 35th Anniversary Full Metal',550,4,3,NULL,NULL),(106,'Robusto G-Shock Mudmaster con estructura resistente al barro y polvo, brújula digital, termómetro, doble LED, botones sellados y diseño para condiciones extremas y uso militar.','casio-gg1000-gshock-mudmaster','GG1000 G-Shock Mudmaster',320,6,3,NULL,NULL),(107,'Prestigioso G-Shock digital full metal con acabado en oro negro, Bluetooth, sincronización horaria multiband 6, energía solar y diseño que honra al modelo original de 1983 con tecnología del siglo XXI.','casio-gmwb5000gd-gshock','GMWB5000GD G-Shock',600,4,3,NULL,NULL),(108,'Clásico reloj de buceo analógico Casio con bisel giratorio unidireccional, esfera limpia y legible, resistencia al agua de 100m, fecha a las 3 y excelente relación calidad-precio.','casio-mtd1010-','MTD1010',85,12,3,NULL,NULL),(109,'Versátil G-Shock analógico-digital con recepción de señal atómica, energía solar, múltiples alarmas, cronómetro, hora mundial y resistencia al agua de 200m con diseño orientado a la aviación.','casio-gw2500-gshock','GW2500 G-Shock',230,7,3,NULL,NULL),(110,'Imponente G-Shock apodado \"King\" por su gran tamaño, con caja ultra resistente a golpes, sensores de luz ambiental, paneles solares ocultos y apariencia táctica en negro mate.','casio-gx56bb-gshock','GX56BB G-Shock',180,8,3,NULL,NULL),(111,'Robusto G-Shock digital con pantalla LCD negativa, cronómetro, temporizador, 5 alarmas, hora mundial, iluminación LED y resistencia al agua de 200m en un diseño de gran visibilidad.','casio-gd100-gshock','GD100 G-Shock',110,10,3,NULL,NULL),(112,'Popular G-Shock analógico-digital con gran esfera, cronógrafo de alta velocidad (1/1000 seg), agujas tridimensionales, múltiples funciones y diseño llamativo con excelente legibilidad.','casio-ga100-gshock','GA100 G-Shock',99,15,3,NULL,NULL),(113,'Versátil G-Shock analógico-digital con tecnología Tough Solar, Multiband 6, manecillas luminiscentes, funciones digitales completas y diseño compacto para uso diario.','casio-awgm100b-gshock-analogdigital','AWGM100B G-Shock Analog-Digital',150,9,3,NULL,NULL),(114,'Clásico G-Shock digital con diseño inspirado en el original DW5000, tecnología Tough Solar, recepción Multiband 6, pantalla LCD de alto contraste y funciones completas en un formato compacto.','casio-gwm5610-gshock','GWM5610 G-Shock',130,2,3,NULL,NULL),(115,'Funcional G-Shock con carcasa robusta, tecnología Tough Solar, recepción de ondas atómicas Multiband 6, alarmas, cronómetro y diseño discreto adecuado para cualquier ocasión.','casio-gw2310fb-gshock','GW2310FB G-Shock',140,7,3,NULL,NULL),(116,'Deportivo G-Shock de la línea G-LIDE orientada a deportes extremos, con funciones especiales para surf y snowboard, resistencia a bajas temperaturas y datos de mareas y lunares.','casio-gls5600cl-gshock-glide-digital','GLS5600CL G-Shock G-LIDE Digital',120,8,3,NULL,NULL),(117,'Elegante cronógrafo Edifice con inspiración en las carreras de motor, caja de acero inoxidable, cristal mineral, bisel con escala taquimétrica y precisión japonesa a precio accesible.','casio-ef503d1av-edifice','EF-503D-1AV Edifice',175,9,3,NULL,NULL),(118,'Reloj premium Oceanus con caja de titanio, cristal de zafiro, tecnología Tough Solar, recepción Multiband 6 y acabados de alta calidad que rivalizan con marcas de lujo suizas.','casio-ocws100-oceanus','OCWS100 Oceanus',750,4,3,NULL,NULL),(119,'Minimalista G-Shock con el diseño original cuadrado, pero en una discreta versión militar con pantalla negativa, funciones básicas y la resistencia legendaria de la serie a un precio asequible.','casio-dw5600ms1cr-gshock','DW5600MS-1CR G-Shock',99,7,3,NULL,NULL),(120,'Premium G-Shock con carcasa de resina y fondo de acero inoxidable atornillado, tecnología solar y multiband 6, ensamblado en la fábrica premium de Casio en Yamagata, Japón.','casio-gw5000-gshock','GW5000 G-Shock',300,6,3,NULL,NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment` varchar(500) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `rating` int DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK1nv3auyahyyy79hvtrcqgtfo9` (`product_id`,`user_id`),
  KEY `FKcgy7qjc1r99dp117y9en6lxye` (`user_id`),
  CONSTRAINT `FKcgy7qjc1r99dp117y9en6lxye` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKpl51cejpw4gy5swfar8br9ngi` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `reviews_chk_1` CHECK ((`rating` <= 5))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (1,'Una obra de arte sin precedentes. Mis dieses','2025-04-15 17:24:49.211301',5,45,1);
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (2,'ROLE_ADMIN'),(1,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'string','string','string','string','string','usuario1@usuario.com',_binary '','dff','strdfdsfing','Juan','$2a$10$zvtW2Cgql5D2afQAnFjSc.fpB.rChGmvNt01SNcEXyPzBEs145azG','573845734'),(2,'Madrid','España','28001',NULL,NULL,'manuel.garcia@example.es',_binary '','Manuel','García Pérez','Manuel','$2a$10$uV54VGwEORikRgkSLsq91Ouj1d51aEvgg3QCMIaca0M8bx5gZ98uK','634567890'),(3,'Barcelona','España','08036','Cataluña','Avenida Diagonal, 423, 5º 2ª','carmenmr@correo.es',_binary '','Carmen','Martínez Ruiz','Carmen','$2a$10$c/3WimRUejpSwrY4GsKp1eT86yE6rvPLaMHgq1GR9TADuIYPc3I9W','678912345'),(4,'Sevilla','España','41001','Andalucía','Plaza Nueva, 8, Bajo Izquierda','miguelfersoto@gmail.es',_binary '','Miguel','Fernández Soto','Miguel','$2a$10$g66wrBwH7reGC6ImrStzDuU3Mj9iNMtBK9hZtPBhrL.aA77Ui6ZZ6','645789123'),(5,'Ciudad Admin','País Admin','12345','Estado Admin','Calle Admin 123','admin@admin.com',_binary '','Admin','Administrador','Admin','$2a$10$M/4UHZ96K3X4XVRz05ZCLu7PVtOC.YkFldPkh.m2fRSSVzkGhoKvi','123456789'),(6,'Madrid','España','28013','Madrid','Calle Gran Vía 123','juan.perez@example.com',_binary '','Juan','Pérez','Juan Pérez','$2a$10$Hry9Sc3Rbs.4R2ovOz5v/OYYsUkriUNhPRq8/FnIIfR6YXUa2ECnK','+34612345678');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  UNIQUE KEY `UKq3r1u8cne2rw2hkr899xuh7vj` (`user_id`,`role_id`),
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(5,2);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-18 11:07:12
