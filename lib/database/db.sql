-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               8.0.19 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for pet_shop
CREATE DATABASE IF NOT EXISTS `pet_shop` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pet_shop`;

-- Dumping structure for table pet_shop.animal
CREATE TABLE IF NOT EXISTS `animal` (
  `animal_id` bigint NOT NULL,
  `clas` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pet_category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`animal_id`),
  KEY `FK3omks7kywxe73t21dpmfynkjq` (`pet_category_id`),
  CONSTRAINT `FK3omks7kywxe73t21dpmfynkjq` FOREIGN KEY (`pet_category_id`) REFERENCES `pet_category` (`pet_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pet_shop.animal: ~1 rows (approximately)
/*!40000 ALTER TABLE `animal` DISABLE KEYS */;
INSERT INTO `animal` (`animal_id`, `clas`, `name`, `pet_category_id`) VALUES
	(52, 'Canis lupus familiaris', 'Psi', 51),
	(113, 'Cattus', 'Macke', 51);
/*!40000 ALTER TABLE `animal` ENABLE KEYS */;

-- Dumping structure for table pet_shop.breed
CREATE TABLE IF NOT EXISTS `breed` (
  `breed_id` bigint NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `animal_id` bigint DEFAULT NULL,
  PRIMARY KEY (`breed_id`),
  KEY `FKitngvqdhx6p7hr5ylulcdca14` (`animal_id`),
  CONSTRAINT `FKitngvqdhx6p7hr5ylulcdca14` FOREIGN KEY (`animal_id`) REFERENCES `animal` (`animal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pet_shop.breed: ~1 rows (approximately)
/*!40000 ALTER TABLE `breed` DISABLE KEYS */;
INSERT INTO `breed` (`breed_id`, `description`, `name`, `animal_id`) VALUES
	(53, 'Gruba vanjština, meko srce: rotvajler je snažan, neustrašiv i samouvjeren pas koji, kao pas čuvar ili policijski pas, ponekad pokaže i zube kao upozorenje. No, kao obiteljski pas, nekadašnji pas mesara, pokazuje i svoju nježnu stranu, vrlo je privržen, odan i velika maza!', 'Rotvajler', 52),
	(114, 'Bezdlaka Sfinks mačka je tipičan primer vrste mačaka koje su nastale slučajno. Elizabeth, crno-bela domaća mačka u Torontu u Kanadi, donela je na svet bezdlako mače. Smatra se da je razlog tome genetska mutacija. ', 'Sfinks', 113);
/*!40000 ALTER TABLE `breed` ENABLE KEYS */;

-- Dumping structure for table pet_shop.cart
CREATE TABLE IF NOT EXISTS `cart` (
  `cart_id` bigint NOT NULL,
  `created_at` date NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `FKl70asp4l4w0jmbm1tqyofho4o` (`user_id`),
  CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pet_shop.cart: ~0 rows (approximately)
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;

-- Dumping structure for table pet_shop.cart_item
CREATE TABLE IF NOT EXISTS `cart_item` (
  `cart_item_id` bigint NOT NULL,
  `cart_id` bigint DEFAULT NULL,
  `pet_id` bigint DEFAULT NULL,
  `pet_product_id` bigint DEFAULT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`cart_item_id`),
  KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
  KEY `FKgf3jh2h63m0ahgipio0y71cte` (`pet_id`),
  KEY `FKmih2uirmweqbishbt1imp46bs` (`pet_product_id`),
  CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`),
  CONSTRAINT `FKgf3jh2h63m0ahgipio0y71cte` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`pet_id`),
  CONSTRAINT `FKh3nw61l2wi2y9g5swyi8gjwhw` FOREIGN KEY (`pet_product_id`) REFERENCES `pet_product` (`pet_product_id`),
  CONSTRAINT `FKmih2uirmweqbishbt1imp46bs` FOREIGN KEY (`pet_product_id`) REFERENCES `product` (`pet_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pet_shop.cart_item: ~0 rows (approximately)
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;

-- Dumping structure for table pet_shop.confirmation_token
CREATE TABLE IF NOT EXISTS `confirmation_token` (
  `confirmation_token_id` bigint NOT NULL,
  `confirmed_at` datetime(6) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `expires_at` datetime(6) NOT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`confirmation_token_id`),
  KEY `FKhjrtky9wbd6lbk7mu9tuddqgn` (`user_id`),
  CONSTRAINT `FKhjrtky9wbd6lbk7mu9tuddqgn` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pet_shop.confirmation_token: ~3 rows (approximately)
/*!40000 ALTER TABLE `confirmation_token` DISABLE KEYS */;
INSERT INTO `confirmation_token` (`confirmation_token_id`, `confirmed_at`, `created_at`, `expires_at`, `token`, `user_id`) VALUES
	(14, '2022-05-24 00:42:50.344921', '2022-05-24 00:40:36.042069', '2022-05-24 00:55:36.044065', '9aee7870-82d5-4fbe-9680-a49faced2afc', 12),
	(50, '2022-05-25 15:36:24.576371', '2022-05-25 15:35:58.297703', '2022-05-25 15:50:58.297703', 'eca86f0c-1032-4289-b59b-33a275fb66f0', 39),
	(105, '2022-05-28 16:09:53.955660', '2022-05-28 16:09:25.921093', '2022-05-28 16:24:25.921093', '0790e0f5-9b3d-4088-9909-3f8a971a8980', 103),
	(120, NULL, '2022-06-09 00:02:18.084622', '2022-06-09 00:17:18.086623', 'f7c1f775-fe6c-4ffb-af96-4a8cb1415757', 109),
	(121, NULL, '2022-06-09 00:02:25.726611', '2022-06-09 00:17:25.726611', 'f9391cea-c6f5-49ff-b63c-986a574ec754', 109),
	(122, NULL, '2022-06-09 00:04:01.195013', '2022-06-09 00:19:01.195013', 'e8037b39-63ec-448b-b857-ed1d21b9814f', 109),
	(123, NULL, '2022-06-09 00:06:20.379545', '2022-06-09 00:21:20.380546', '9f36d0c0-ca11-494a-8551-30af208183cd', 109);
/*!40000 ALTER TABLE `confirmation_token` ENABLE KEYS */;

-- Dumping structure for table pet_shop.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pet_shop.hibernate_sequence: ~0 rows (approximately)
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(124);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping structure for table pet_shop.order_user
CREATE TABLE IF NOT EXISTS `order_user` (
  `order_user_id` bigint NOT NULL,
  `created_at` date NOT NULL,
  `status` int DEFAULT NULL,
  `cart_id` bigint DEFAULT NULL,
  PRIMARY KEY (`order_user_id`),
  KEY `FK7lkbqtjnk6r7drd1cww5mssnm` (`cart_id`),
  CONSTRAINT `FK7lkbqtjnk6r7drd1cww5mssnm` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pet_shop.order_user: ~0 rows (approximately)
/*!40000 ALTER TABLE `order_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_user` ENABLE KEYS */;

-- Dumping structure for table pet_shop.pet
CREATE TABLE IF NOT EXISTS `pet` (
  `pet_id` bigint NOT NULL,
  `age` int NOT NULL,
  `color` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `discount` double NOT NULL,
  `excerpt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `eyes_color` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `quantity` int NOT NULL,
  `retail_price` double NOT NULL,
  `sex` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `vendor_price` double NOT NULL,
  `breed_id` bigint DEFAULT NULL,
  PRIMARY KEY (`pet_id`),
  KEY `FKiqbjbaml7gtwulqptktmsi5dc` (`breed_id`),
  CONSTRAINT `FKiqbjbaml7gtwulqptktmsi5dc` FOREIGN KEY (`breed_id`) REFERENCES `breed` (`breed_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pet_shop.pet: ~0 rows (approximately)
/*!40000 ALTER TABLE `pet` DISABLE KEYS */;
INSERT INTO `pet` (`pet_id`, `age`, `color`, `description`, `discount`, `excerpt`, `eyes_color`, `name`, `quantity`, `retail_price`, `sex`, `vendor_price`, `breed_id`) VALUES
	(58, 4, 'crno-smedja', 'Čak su i Rimljani cijenili veliku snagu okretnih pasa s ogromnom izdržljivošću. Rimski stočari držali su pretke današnjeg rotvajlera kao psa za čuvanje stoke. Rotvajler je jedna od najstarijih pasmina pasa na svijetu.', 0.1, 'Rotvajler kraljevskog pedigrea..', 'crna', 'Domaci pas', 1, 200, 'MALE', 150, 53),
	(118, 3, 'crno-siva', 'Sfinks mačke spadaju u mačke bez dlake. Dok neki ljubitelji životinja kritiziraju uzgajivače ove pasmine iz Kanade i uzgoj smatraju mučenjem životinja, drugi se dive neobičnim ljubimicama. Ako pripadnica ovoj pasmini nema brkove, u većini zemalja EU njezin uzgoj će se klasificirati kao mučenje. U nastavku se bolje upoznajte s ovom mističnom pasminom. ', 0.05, 'Sfinks mačka je srednje velika, mišićava mačka koja može težiti i do sedam kilograma. Velike, otvorene uši nalaze se na četvrtastoj glavi. ', 'plave', 'Sfinks macka', 1, 1500, 'MALE', 1000, 114);
/*!40000 ALTER TABLE `pet` ENABLE KEYS */;

-- Dumping structure for table pet_shop.pet_category
CREATE TABLE IF NOT EXISTS `pet_category` (
  `pet_category_id` bigint NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`pet_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pet_shop.pet_category: ~3 rows (approximately)
/*!40000 ALTER TABLE `pet_category` DISABLE KEYS */;
INSERT INTO `pet_category` (`pet_category_id`, `description`, `name`) VALUES
	(51, 'Kopnene domace zivotinje, sisari :)', 'Kopnene zivotinje'),
	(98, 'Vodene zivotinje :), ribe, kornjace itd..', 'Akvaristika'),
	(99, 'Insekti, pauci, bogomoljke, stonoge', 'Terarijum');
/*!40000 ALTER TABLE `pet_category` ENABLE KEYS */;

-- Dumping structure for table pet_shop.pet_product
CREATE TABLE IF NOT EXISTS `pet_product` (
  `pet_product_id` bigint NOT NULL,
  `code` int NOT NULL,
  `details` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `discount` double NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `quantity` int NOT NULL,
  `retail_price` double NOT NULL,
  `status` int DEFAULT NULL,
  `vendor_price` double NOT NULL,
  `product_category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`pet_product_id`),
  KEY `FKbad201ukrvjacens1yyb0vi42` (`product_category_id`),
  CONSTRAINT `FKbad201ukrvjacens1yyb0vi42` FOREIGN KEY (`product_category_id`) REFERENCES `product_category` (`product_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pet_shop.pet_product: ~0 rows (approximately)
/*!40000 ALTER TABLE `pet_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `pet_product` ENABLE KEYS */;

-- Dumping structure for table pet_shop.photo
CREATE TABLE IF NOT EXISTS `photo` (
  `photo_id` bigint NOT NULL,
  `path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pet_id` bigint DEFAULT NULL,
  `pet_product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`photo_id`),
  KEY `FKl3c7ljuisybcpkjcnr12gedx0` (`pet_id`),
  KEY `FK3khw3uwh4hvypbkdtshuwo241` (`pet_product_id`),
  CONSTRAINT `FK3khw3uwh4hvypbkdtshuwo241` FOREIGN KEY (`pet_product_id`) REFERENCES `product` (`pet_product_id`),
  CONSTRAINT `FKl3c7ljuisybcpkjcnr12gedx0` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`pet_id`),
  CONSTRAINT `FKp5wjqw40nifxulvy586lig4l6` FOREIGN KEY (`pet_product_id`) REFERENCES `pet_product` (`pet_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pet_shop.photo: ~0 rows (approximately)
/*!40000 ALTER TABLE `photo` DISABLE KEYS */;
INSERT INTO `photo` (`photo_id`, `path`, `pet_id`, `pet_product_id`) VALUES
	(97, '07655515933Rotvajler.jpg', 58, NULL),
	(119, '59813293461whbX0Q65lI.jpg', 118, NULL);
/*!40000 ALTER TABLE `photo` ENABLE KEYS */;

-- Dumping structure for table pet_shop.product
CREATE TABLE IF NOT EXISTS `product` (
  `pet_product_id` bigint NOT NULL,
  `code` int NOT NULL,
  `details` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `discount` double NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `quantity` int NOT NULL,
  `retail_price` double NOT NULL,
  `status` int DEFAULT NULL,
  `vendor_price` double NOT NULL,
  `product_category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`pet_product_id`),
  KEY `FKcwclrqu392y86y0pmyrsi649r` (`product_category_id`),
  CONSTRAINT `FKcwclrqu392y86y0pmyrsi649r` FOREIGN KEY (`product_category_id`) REFERENCES `product_category` (`product_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pet_shop.product: ~0 rows (approximately)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- Dumping structure for table pet_shop.product_category
CREATE TABLE IF NOT EXISTS `product_category` (
  `product_category_id` bigint NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `animal_id` bigint DEFAULT NULL,
  PRIMARY KEY (`product_category_id`),
  KEY `FK7h2sjtmqpp56y617huqyoblco` (`animal_id`),
  CONSTRAINT `FK7h2sjtmqpp56y617huqyoblco` FOREIGN KEY (`animal_id`) REFERENCES `animal` (`animal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pet_shop.product_category: ~0 rows (approximately)
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;

-- Dumping structure for table pet_shop.user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` bigint NOT NULL,
  `contact` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `first_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `locked` bit(1) NOT NULL,
  `photo_path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pet_shop.user: ~5 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `contact`, `email`, `first_name`, `last_name`, `username`, `password`, `address`, `enabled`, `locked`, `photo_path`) VALUES
	(1, '065-321/123', 'random.lik123@yahoo.com', 'Random', 'Lik', 'randomlik', '$2a$10$Mq6C/TMFoNzx4c/d/kXxTO4moQc5kpR6I9bQikMetUzejQsj/07su', NULL, b'0', b'0', NULL),
	(2, '065-418/309', 'aleksa.car1997@gmail.com', 'Aleksandar', 'Damjanovic', 'drNedeljkovic', '$2a$10$Mq6C/TMFoNzx4c/d/kXxTO4moQc5kpR6I9bQikMetUzejQsj/07su', 'Trebavskih Srpskih brigada 178, Doboj, RS BIH', b'1', b'0', 'C:\\Users\\DT User6\\eclipse-workspace\\pet-shop/src/main/resources/static//user/120320697_258588028747620_8431913182921844753_n.jpg'),
	(12, '065123321', 'cvijanovic.bojan@gmail.com', 'Bojan', 'Cvijanovic', 'cvija', '$2a$10$upAu9u9uKr0uYlbuXAgimOFHsRjEY6QSJ6hxUAeoyzQn2gC2K5xpa', 'Doboj', b'1', b'0', NULL),
	(39, '065432123', 'aleksa.dam997@gmail.com', 'Rasmus', 'Winther', 'caps', '$2a$10$Qu2DL/UDzfoRtNS/JD97XeT5kZ9rSA0.m82dflJRweUkztd7EbAPi', 'Kompenhagen, Denmark', b'1', b'0', NULL),
	(103, '065987789', 'coacarf1@gmail.com', 'Lionel', 'Messi', 'Andres', '$2a$10$vFmPEfQGu3uca8Hq5RUoPeLwOSPBLBoHbGlMqCzXMSWo1LmS.TA02', 'Rosario, Argentina', b'1', b'0', NULL),
	(109, '0826231231', 'marko.markovic@gmail.com', 'Marko', 'Markovic', NULL, '$2a$10$M/66vqZ9NVpl0c3IIKe1NePD0FZxQvqbkx/UJV8bPzzVkFJoPz/Bq', 'Doboj, Celjska 123', b'0', b'0', NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table pet_shop.user_group
CREATE TABLE IF NOT EXISTS `user_group` (
  `user_group_id` bigint NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `group_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pet_shop.user_group: ~4 rows (approximately)
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` (`user_group_id`, `description`, `group_name`) VALUES
	(1, 'Administrator of application..', 'ADMIN'),
	(3, 'Simple user, with basic privilegies', 'USER'),
	(13, 'Simple user, with basic privilegies', 'USER'),
	(40, 'Simple user, with basic privilegies', 'USER');
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;

-- Dumping structure for table pet_shop.user_order
CREATE TABLE IF NOT EXISTS `user_order` (
  `user_order_id` bigint NOT NULL,
  `created_at` date NOT NULL,
  `status` int DEFAULT NULL,
  `cart_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_order_id`),
  KEY `FKemt5yfgfgoxcpoavxo2qsvaw2` (`cart_id`),
  CONSTRAINT `FKemt5yfgfgoxcpoavxo2qsvaw2` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pet_shop.user_order: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_order` ENABLE KEYS */;

-- Dumping structure for table pet_shop.user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_user_id` bigint NOT NULL,
  `roles_user_group_id` bigint NOT NULL,
  KEY `FK7xixbj6o1d4xp41ykdabw8o7q` (`roles_user_group_id`),
  KEY `FKkv46dn3qakjvsk7ra33nd5sns` (`user_user_id`),
  CONSTRAINT `FK7xixbj6o1d4xp41ykdabw8o7q` FOREIGN KEY (`roles_user_group_id`) REFERENCES `user_group` (`user_group_id`),
  CONSTRAINT `FKkv46dn3qakjvsk7ra33nd5sns` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table pet_shop.user_roles: ~4 rows (approximately)
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` (`user_user_id`, `roles_user_group_id`) VALUES
	(2, 1),
	(1, 3),
	(12, 13),
	(39, 40);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
