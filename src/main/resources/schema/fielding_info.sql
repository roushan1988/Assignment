CREATE TABLE `fielding_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fielding_rank` int(11) DEFAULT NULL,
  `wicket_keeping_rank` int(11) DEFAULT NULL,
  `fielding_stats` longtext DEFAULT NULL,
  `created_on` datetime NOT NULL,
  `updated_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fielding_rank` (`fielding_rank`),
  UNIQUE KEY `wicket_keeping_rank` (`wicket_keeping_rank`)
);