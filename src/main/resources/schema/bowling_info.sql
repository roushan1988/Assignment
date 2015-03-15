CREATE TABLE `bowling_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bowling_rank` int(11) DEFAULT NULL,
  `bowling_stats` longtext DEFAULT NULL,
  `created_on` datetime NOT NULL,
  `updated_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `bowling_rank` (`bowling_rank`)
);