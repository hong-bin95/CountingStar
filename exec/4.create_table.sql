CREATE TABLE `api_test` (
  `at_id` int NOT NULL AUTO_INCREMENT,
  `at_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`at_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `code_master` (
  `code_master_id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(2) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code_master_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `code_detail` (
  `code_detail_id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(2) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code_master_id` int DEFAULT NULL,
  PRIMARY KEY (`code_detail_id`),
  KEY `FK7h24417c1rpr7sgr9rsoargrx` (`code_master_id`),
  CONSTRAINT `FK7h24417c1rpr7sgr9rsoargrx` FOREIGN KEY (`code_master_id`) REFERENCES `code_master` (`code_master_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `constellation` (
  `constellation_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `observe_month` varchar(255) DEFAULT NULL,
  `kor_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`constellation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `star` (
  `star_id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code_detail_id` int DEFAULT NULL,
  `constellation_id` int DEFAULT NULL,
  PRIMARY KEY (`star_id`),
  KEY `FKr537im9c28uii17ru5lroy64b` (`code_detail_id`),
  KEY `FKluj6x61ba8ftqtmqb8k7qdm47` (`constellation_id`),
  CONSTRAINT `FKluj6x61ba8ftqtmqb8k7qdm47` FOREIGN KEY (`constellation_id`) REFERENCES `constellation` (`constellation_id`),
  CONSTRAINT `FKr537im9c28uii17ru5lroy64b` FOREIGN KEY (`code_detail_id`) REFERENCES `code_detail` (`code_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `spot` (
  `spot_id` int NOT NULL AUTO_INCREMENT,
  `area_code` varchar(7) DEFAULT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `spot_name` varchar(255) DEFAULT NULL,
  `x` int NOT NULL,
  `y` int NOT NULL,
  `location_name` varchar(255) DEFAULT NULL,
  `location_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`spot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `recommendation` (
  `recommendation_id` int NOT NULL AUTO_INCREMENT,
  `contents` longtext,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(2) DEFAULT NULL,
  `spot_master_id` int DEFAULT NULL,
  PRIMARY KEY (`recommendation_id`),
  KEY `FK1kk935iior2gvnxpdrw90ksn` (`spot_master_id`),
  CONSTRAINT `FK1kk935iior2gvnxpdrw90ksn` FOREIGN KEY (`spot_master_id`) REFERENCES `spot` (`spot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `star_grade` (
  `star_grade_id` int NOT NULL AUTO_INCREMENT,
  `spot_id` int DEFAULT NULL,
  `basic_date_year` varchar(4) DEFAULT NULL,
  `basic_date_month` varchar(2) DEFAULT NULL,
  `basic_date_day` varchar(2) DEFAULT NULL,
  `basic_date_hour` varchar(2) DEFAULT NULL,
  `basic_date_minute` varchar(2) DEFAULT NULL,
  `grade_1` double DEFAULT NULL,
  `grade_2` int DEFAULT NULL,
  `start_latitude` varchar(255) DEFAULT NULL,
  `start_longitude` varchar(255) DEFAULT NULL,
  `end_latitude` varchar(255) DEFAULT NULL,
  `end_longitude` varchar(255) DEFAULT NULL,
  `star_id` int DEFAULT NULL,
  PRIMARY KEY (`star_grade_id`),
  KEY `FKmmkdv9p5j31ncb26wraavgdnq` (`spot_id`),
  KEY `FK6uxy9v7pgi80dotqpx5i449li` (`star_id`),
  CONSTRAINT `FK6uxy9v7pgi80dotqpx5i449li` FOREIGN KEY (`star_id`) REFERENCES `star` (`star_id`),
  CONSTRAINT `FKmmkdv9p5j31ncb26wraavgdnq` FOREIGN KEY (`spot_id`) REFERENCES `spot` (`spot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6558 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;